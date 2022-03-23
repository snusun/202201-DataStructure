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


    public BigInteger(int i)
    {
    }
  
    public BigInteger(int[] num1)
    {
    }
  
    public BigInteger(String s)
    {
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
        //System.out.println(str);
        Matcher m = EXPRESSION_PATTERN.matcher(str);
        char sign1, sign2, operator;
        String BigInt1, BigInt2;
        String[] expression = new String[5];

        int i=0;
        while (m.find()){
            expression[i] = m.group();
            i++;
            if(i==5) break;
        }

        for(int k=0; k<expression.length; k++){
            //if()
            // idx == 0 일 때 숫자면 sign + , bigint1 저장
            // 부호면 sign 저장, k++
            // idx == 1 일 때
        }


        /*switch (m.group().length()){
            case 3:
                sign1 = sign2 = '+';
                BigInt1
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                // invalid input
                break;
        }*/

        /*String[] parse = input.split("-*+ ");
        for(String s: parse){
            System.out.println(s);
        }*/
  
        // One possible implementation
        // BigInteger num1 = new BigInteger(arg1);
        // BigInteger num2 = new BigInteger(arg2);
        // BigInteger result = num1.add(num2);
        //return result;
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
