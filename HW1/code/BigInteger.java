import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다."; // 에러날 시 영어로 바꾸기 가능

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
        // 사이즈 작은거 for 문 해서 뒤에서부터 더하기, 올림 수 저장
        // 101 자리
        BigInteger ans = new BigInteger(0);

        int carry = 0;
        for(int i=199; i>=100; i--){
            int sum = val[i] + big.val[i];
            if(sum/10==1){
                carry = 1;
                sum %= 10;
            } else {
                carry = 0;
            }
            ans.val[i] += sum;
            ans.val[i-1] += carry;
        }
        return ans;
    }
  
    public BigInteger subtract(BigInteger big)
    {
        // // 사이즈 작은거 for 문 해서 뒤에서부터 빼기, 내림 수 저장
        // 101 자리
        BigInteger ans = new BigInteger(0);
        return ans;
    }

    public BigInteger multiply(BigInteger big)
    {
        // 200 자리
        BigInteger ans = new BigInteger(0);

        int carry;
        for(int i=199; i>=100; i--){
            for(int j=199; j>=100; j--){
                int mul = val[i] * big.val[j];
                if(mul>9){
                    carry = mul/10;
                    mul %= 10;
                } else {
                    carry = 0;
                }
                ans.val[i+j-199] += mul;
                ans.val[i+j-200] += carry;
            }
        }

        return ans;
    }

    /*public void printResult(boolean sign){
        // true -> +
        // false -> -
        int idx=-1;
        for(int i=0; i<val.length; i++){
            if(val[i]!=0){
                idx = i;
                break;
            }
        }
        if(idx==-1){
            System.out.println(0);
        } else {
            if(!sign) System.out.print('-');
            for(; idx<val.length; idx++){
                System.out.print(val[idx]);
            }
            System.out.println();
        }
    }*/
  
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
            System.out.println(0);
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

        System.out.println(sign1 + " " + BigStr1 + " " + operator + " " + sign2 + " " + BigStr2);

        BigInteger bigInt1 = new BigInteger(sign1, BigStr1);
        BigInteger bigInt2 = new BigInteger(sign2, BigStr2);
//        for(int k=0; k<bigInt1.val.length; k++){
//            System.out.print(bigInt1.val[k]);
//        }
//        System.out.println();
//        for(int n: bigInt2.val){
//            System.out.print(n);
//        }
//        System.out.println();

        BigInteger answer = null;
        // sign 도 고려해서 해야함
        // + - / - + / + + / - -
        //System.out.println(operator);
        char s1 = bigInt1.sign;
        char s2 = bigInt2.sign;
        if((s1=='+' && s2=='+' && operator=='+') || (s1=='+' && s2=='-' && operator=='-')){
            // add +
            System.out.println("add");
            answer = bigInt1.add(bigInt2);
        } else if((s1=='-' && s2=='-' && operator=='+') || (s1=='-' && s2=='+' && operator=='-')){
            // add -
            System.out.println("add");
            answer = bigInt1.add(bigInt2);
            // 부호 붙이기
        } else if((s1!=s2 && operator=='+') || (s1==s2 && operator=='-')){
            // subtract
            answer = bigInt1.subtract(bigInt2);
        } else if(s1==s2 && operator=='*'){
            // equal sign *
            System.out.println("multiply");
            answer = bigInt1.multiply(bigInt2);
        } else if(s1!=s2 && operator=='*'){
            // different sign *
            System.out.println("multiply");
            answer = bigInt1.multiply(bigInt2);
            // 부호 붙이기
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
//            for(int n: result.val){
//                System.out.print(n);
//            }
//            System.out.println();
            System.out.println(result.toString());
  
            return false;
        }
    }
  
    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
