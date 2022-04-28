import java.io.*;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorTest
{
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
				System.out.println("ERROR");
				//System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		// split input
		String[] infix = splitInfix(input);

//		for(String s : infix){
//			System.out.print(s + " ");
//		}
//		System.out.println();

		// make postfix from infix array
		String postfix = infixToPostfix(infix);
		System.out.println(postfix);

		// operate using postfix array
		System.out.println(operate(postfix));
	}

	// make input string to string array contains operand and operator
	private static String[] splitInfix(String input){
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

		// change unary '-' to '~'
		for(int j=0; j<infix.length-1; j++){
			if(infix[j].equals("-")){
				if(j==0 || isOperator(infix[j-1]) || infix[j-1].equals("(")) {
					infix[j] = "~"; // 2번째 경우 ~가 있으면 안되긴 함 -> 나중에 수정
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
			//System.out.println(oper);
			if(!isOperator(oper) && !isParenthesis(oper)){
				postfix += " " + oper;
			} else if(isOperator(oper)) {
				//System.out.println("isOperator");
				while (true){
					if(operatorStack.isEmpty() || operatorStack.peek().equals("(")) {
						//operatorStack.push(oper);
						break;
					}
					String peekOperator = operatorStack.peek();
					if(!isLeft(oper) && (priority(oper) < priority(peekOperator))){
						postfix += " " + operatorStack.pop();
					} else if(isLeft(oper) && priority(oper) <= priority(peekOperator)){
						postfix += " " + operatorStack.pop();
					}
//					if((priority(oper) <= priority(peekOperator) ||
//									(priority(oper)==priority(peekOperator) && !isLeft(peekOperator)))){
//						postfix += " " + operatorStack.pop();
//					}
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

	private static long operate(String postfix){
		String[] operation = postfix.split(" ");
		Stack<Long> stack = new Stack<Long>();
		for(int i=0; i<operation.length; i++){
			String ch = operation[i];
			if(isOperator(ch)){
				if(ch.equals("~")) {
					long n1 = stack.pop();
					stack.push(n1*(-1));
				} else {
					long n2 = stack.pop();
					long n1 = stack.pop();
					long result = calculateByOperator(ch, n1, n2);
					stack.push(result);
				}
			} else { // 숫자여야 함
				stack.push(Long.parseLong(ch));
			}
		}

		return stack.pop(); // 오류가 안난다면 하나만 남음
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

	private static long calculateByOperator(String oper, long n1, long n2){
		switch (oper) {
			case "^":
				return (long) Math.pow(n1, n2);
			case "*":
				return n1 * n2;
			case "/":
				return n1 / n2;
			case "%":
				return n1 % n2;
			case "+":
				return n1 + n2;
			case "-":
				return n1 - n2;
			default:
				return -1;
		}
	}

}
