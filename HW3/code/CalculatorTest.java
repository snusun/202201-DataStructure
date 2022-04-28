import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorTest
{
	static String resultPostfix = "";
	static Long result = null;

	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;
				command(input);
			}
			catch (Exception e)
			{
				resultPostfix = "ERROR";
			}
			if(result==null || resultPostfix.equals("ERROR")) {
				System.out.println("ERROR");
			} else {
				System.out.println(resultPostfix);
				System.out.println(result);
			}
		}
	}

	private static void command(String input){
		// split input
		String[] infix = splitInfix(input);
		if(infix[0].equals("ERROR")) {
			resultPostfix = "ERROR";
			return;
		}

		// make postfix from infix array
		String postfix = infixToPostfix(infix);
		resultPostfix = postfix;
		//System.out.println(postfix);

		// operate using postfix array
		result = operate(postfix);
	}

	// make input string to string array contains operand and operator
	private static String[] splitInfix(String input){
		String[] checkInfix = input.split("[+\\-*/%^()]|(\\s+)");
		int numOfOperand = 0;
		for(String s : checkInfix){
			if(!s.equals("")) numOfOperand++;
		}

		// remove blank
		String removeBlank = input.replaceAll("\\s+","");

		// string to string array using string tokenizer
		StringTokenizer st = new StringTokenizer(removeBlank, "+-*/%^()", true);
		String[] infix = new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens()){
			infix[i] = st.nextToken();
			i++;
		}

		for(String s: infix){
			if(!isOperator(s) && !isParenthesis(s)){
				numOfOperand--;
			}
		}
		
		if(numOfOperand!=0){
			return new String[]{"ERROR"};
		}

		// change unary '-' to '~'
		for(int j=0; j<infix.length-1; j++){
			if(infix[j].equals("-")){
				if(j==0 || isOperator(infix[j-1]) || infix[j-1].equals("(")) {
					infix[j] = "~";
				}
			}
		}


		return infix;
	}

	// make postfix from infix
	private static String infixToPostfix(String[] infix){
		Stack<String> operatorStack = new Stack<>();
		String postfix = "";

		for(String oper: infix){
			if(!isOperator(oper) && !isParenthesis(oper)){
				postfix += " " + oper;
			} else if(isOperator(oper)) {
				while (true){
					if(operatorStack.isEmpty() || operatorStack.peek().equals("(")) {
						break;
					}
					String peekOperator = operatorStack.peek();
					if(!isLeft(oper) && (priority(oper) < priority(peekOperator))){
						postfix += " " + operatorStack.pop();
					} else if(isLeft(oper) && priority(oper) <= priority(peekOperator)){
						postfix += " " + operatorStack.pop();
					}
					else break;
				}
				operatorStack.push(oper);
			} else if(oper.equals("(")){
				operatorStack.push(oper);
			} else if(oper.equals(")")) {
				while(!operatorStack.peek().equals("(")) {
					postfix += " " + operatorStack.pop();
				}
				operatorStack.pop();
			}
		}

		while (!operatorStack.isEmpty()) {
			postfix += " " + operatorStack.pop();
		}

		return postfix.trim();
	}

	private static Long operate(String postfix){
		String[] operation = postfix.split(" ");
		Stack<Long> stack = new Stack<>();
		for(int i=0; i<operation.length; i++){
			String ch = operation[i];
			if(isOperator(ch)){
				if(ch.equals("~")) {
					long n1 = stack.pop();
					stack.push(n1*(-1));
				} else {
					long n2 = stack.pop();
					long n1 = stack.pop();
					if(calculateByOperator(ch, n1, n2)==null) return null;
					stack.push(calculateByOperator(ch, n1, n2));
				}
			} else {
				stack.push(Long.parseLong(ch));
			}
		}

		return stack.pop();
	}

	private static boolean isOperator(String oper)
	{
		return oper.equals("^") || oper.equals("~") || oper.equals("*") ||
				oper.equals("/") || oper.equals("%") || oper.equals("+") ||
				oper.equals("-");
	}

	private static boolean isParenthesis(String oper){
		return oper.equals("(") || oper.equals(")");
	}

	private static int priority(String oper){
		if(oper.equals("(") || oper.equals(")")) return 5;
		else if(oper.equals("^")) return 4;
		else if(oper.equals("~")) return 3;
		else if(oper.equals("*") || oper.equals("/") || oper.equals("%")) return 2;
		else if (oper.equals("+") || oper.equals("-")) return 1;
		else return -1;
	}

	private static boolean isLeft(String oper){
		return !oper.equals("^") && !oper.equals("~");
	}

	private static Long calculateByOperator(String oper, long n1, long n2){
		switch (oper) {
			case "^": // n1 == 0 && n2 < 0
				if(n1==0 && n2<0) return null;
				return (long) Math.pow(n1, n2);
			case "*":
				return n1 * n2;
			case "/": // n2 == 0;
				if(n2==0) return null;
				return n1 / n2;
			case "%": // n2 == 0;
				if(n2==0) return null;
				return n1 % n2;
			case "+":
				return n1 + n2;
			case "-":
				return n1 - n2;
			default:
				return null;
		}
	}
}
