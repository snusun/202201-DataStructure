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
    //public static char[] BigInt1, BigInt2;

    public char sign = '+';
    public char[] val = new char[100];


    public BigInteger(int i)
    {
    }
  
    public BigInteger(int[] num1)
    {
    }
  
    public BigInteger(char sign, String s)
    {
        this.sign = sign;
        char[] temp = s.toCharArray();

        int idx=100-temp.length;
        for(char c: temp){
            val[idx] = c;
            idx++;
        }
    }
  
    /*public BigInteger add(BigInteger big)
    {
        // 사이즈 작은거 for 문 해서 뒤에서부터 더하기, 올림 수 저장
        // 101 자리
    }
  
    public BigInteger subtract(BigInteger big)
    {
        // // 사이즈 작은거 for 문 해서 뒤에서부터 빼기, 내림 수 저장
        // 101 자리
    }

    public BigInteger multiply(BigInteger big)
    {
        // 200 자리
    }
  
    @Override
    public String toString()
    {
    }*/
  
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

        //BigInt1 = BigStr1.toCharArray();
        //BigInt2 = BigStr2.toCharArray();
        System.out.println(sign1 + " " + BigStr1 + " " + operator + " " + sign2 + " " + BigStr2);

        BigInteger bigInt1 = new BigInteger(sign1, BigStr1);
        BigInteger bigInt2 = new BigInteger(sign2, BigStr2);
        for(int k=0; k<bigInt1.val.length; k++){
            System.out.print(bigInt1.val[k]);
        }
        System.out.println();
        System.out.println(bigInt1.val.length);
        for(char c: bigInt2.val){
            System.out.print(c);
        }
        System.out.println();
        System.out.println(bigInt2.val.length);

        return new BigInteger(10);
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
            System.out.println(result.toString());
  
            return false;
        }
    }
  
    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
