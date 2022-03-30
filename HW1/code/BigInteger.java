import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "INVALID INPUT";

    // implement this
    public static final Pattern EXPRESSION_PATTERN = Pattern.compile("[0-9]+|[-*+]?");

    public char sign = '+';
    public int[] val = new int[200];

    public BigInteger(int i)
    {
    }
  
    public BigInteger(int[] ans)
    {
    }
  
    public BigInteger(char sign, String s)
    {
        this.sign = sign;
        char[] temp = s.toCharArray();

        int idx=200-temp.length;
        for(char c: temp){
            val[idx] = c - '0';
            idx++;
        }
    }
  
    public BigInteger add(BigInteger big)
    {
        BigInteger ans = new BigInteger(0);

        int carry;
        for(int i=199; i>=100; i--){
            int sum = val[i] + big.val[i];
            if(sum/10>=1){
                carry = sum/10;
                sum %= 10;
            } else {
                carry = 0;
            }
            if((ans.val[i]+sum)>=10){
                ans.val[i] = (ans.val[i]+sum)%10;
                ans.val[i-1] += (ans.val[i]+sum)/10;
                carry = 1;
            } else ans.val[i] += sum;
            ans.val[i-1] += carry;
        }
        return ans;
    }
  
    public BigInteger subtract(BigInteger big)
    {
        BigInteger ans = new BigInteger(0);

        int idx=-1;
        for(int i=0; i<val.length; i++){
            if(val[i]!=0){
                idx = i;
                break;
            }
        }

        for(int i=199; i>=idx; i--){
            if(val[i] >= big.val[i]) {
                ans.val[i] = val[i] - big.val[i];
            } else {
                int j = i-2;
                if(val[i-1]!=0){
                    val[i-1] -=1;
                } else {
                    val[i-1] = 9;
                    while (val[j]==0){
                        val[j] = 9;
                        j-=1;
                    }
                    val[j]-=1;
                }
                ans.val[i] = 10 + val[i] - big.val[i];
            }
        }

        return ans;
    }

    public BigInteger multiply(BigInteger big)
    {
        BigInteger ans = new BigInteger(0);

        for(int i=199;i>=100;i--){
            for(int j=199;j>=100;j--){
                ans.val[i+j-199] += val[j] * big.val[i];
            }
        }

        for(int i=199;i>=0;i--){
            while( ans.val[i] >= 10 ){
                ans.val[i-1] += ans.val[i]/10;
                ans.val[i] = ans.val[i]%10;
            }
        }

        return ans;
    }

    public Boolean isBigger(BigInteger big){
        for(int i=0; i<val.length; i++){
            if(val[i] < big.val[i]){
                return false;
            } else if(val[i] > big.val[i]) {
                return true;
            }
        }
        return true;
    }
  
    @Override
    public String toString()
    {
        String result = "";
        int idx=-1;
        for(int i=0; i<val.length; i++){
            if(val[i]!=0){
                idx = i;
                break;
            }
        }
        if(idx==-1){
            result="0";
            return result;
        } else {
            for(; idx<val.length; idx++){
                result += val[idx];
            }
        }
        return result;
    }
  
    static BigInteger evaluate(String input) throws IllegalArgumentException
    {
        // implement here
        // parse input
        // using regex is allowed
        String str = input.replaceAll("\\s", "");
        Matcher m = EXPRESSION_PATTERN.matcher(str);
        char sign1, sign2, operator;
        String BigStr1, BigStr2;
        String[] expression = new String[6];

        int i=0;
        while (m.find()){
            expression[i] = m.group();
            i++;
        }

        // parse input to operands and operator
        if(expression[0].matches("[0-9]+")){
            sign1 = '+';
            BigStr1 = expression[0];
            operator = expression[1].charAt(0);
            if(expression[2].matches("[0-9]+")){
                sign2 = '+';
                BigStr2 = expression[2];
            } else {
                sign2 = expression[2].charAt(0);
                BigStr2 = expression[3];
            }
        } else {
            sign1 = expression[0].charAt(0);
            BigStr1 = expression[1];
            operator = expression[2].charAt(0);
            if(expression[3].matches("[0-9]+")){
                sign2 = '+';
                BigStr2 = expression[3];
            } else {
                sign2 = expression[3].charAt(0);
                BigStr2 = expression[4];
            }
        }

        BigInteger bigInt1 = new BigInteger(sign1, BigStr1);
        BigInteger bigInt2 = new BigInteger(sign2, BigStr2);
        BigInteger answer = null;

        char s1 = bigInt1.sign;
        char s2 = bigInt2.sign;
        if((s1=='+' && s2=='+' && operator=='+') || (s1=='+' && s2=='-' && operator=='-')){
             answer = bigInt1.add(bigInt2);
        } else if((s1=='-' && s2=='-' && operator=='+') || (s1=='-' && s2=='+' && operator=='-')){
            answer = bigInt1.add(bigInt2);
            answer.sign = '-';
        } else if((s1!=s2 && operator=='+') || (s1==s2 && operator=='-')){
             if(bigInt1.isBigger(bigInt2)){
                answer = bigInt1.subtract(bigInt2);
                answer.sign = bigInt1.sign;
            } else {
                answer = bigInt2.subtract(bigInt1);
                answer.sign = bigInt2.sign;
                if(operator=='-' && bigInt2.sign=='+') answer.sign = '-';
                if(operator=='-' && bigInt2.sign=='-') answer.sign = '+';
            }
        } else if(s1==s2 && operator=='*'){
            answer = bigInt1.multiply(bigInt2);
        } else if(s1!=s2 && operator=='*'){
            answer = bigInt1.multiply(bigInt2);
            answer.sign = '-';
        }

        return answer;
    }
  
    public static void main(String[] args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in))
        {
            try (BufferedReader reader = new BufferedReader(isr))
            {
                boolean done = false;
                while (!done)
                {
                    String input = reader.readLine();
  
                    try
                    {
                        done = processInput(input);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }
  
    static boolean processInput(String input) throws IllegalArgumentException
    {
        boolean quit = isQuitCmd(input);
  
        if (quit)
        {
            return true;
        }
        else
        {
            BigInteger result = evaluate(input);
            if(result.toString().equals("0")) result.sign='+';
            if(result.sign=='-') System.out.print('-');
            System.out.println(result);
  
            return false;
        }
    }
  
    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
