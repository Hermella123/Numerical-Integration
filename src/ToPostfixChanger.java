import java.util.ArrayList;
import java.util.Stack;

public class ToPostfixChanger {

	private static final String[] operators = {"(", ")", "+", "-", "/", "*", "%", "^", " "};
	/** in stack sign value **/
	private static final int[] insp = {0, 19, 12, 12, 13, 13, 13, 14, 0};
	/** incoming sign value **/
	private static final int[] icmp = {20, 19, 12, 12, 13, 13, 13, 14, 0};
	public ArrayList<String> postfix(String infix)
	{
		ArrayList<String> postfix =new ArrayList<String>();
		Stack<Integer> stack ;
		stack= new Stack<Integer>();
		stack.push(8);
		String[] t = infix.split("(?<=[-+^*/()])|(?=[-+^*/()])");
		
		int index,first;
		for (int i = 0; i < t.length; i++)
		{
			String c= t[i];
			index=indexof(c);
			//5+6*(5+2)-3(System.out.println(c+" "+index);
			/** if token is operand append to postfix **/
			if (tryParseDouble(t[i])||t[i].equals("x")) {
				postfix.add( t[i]);
			}
			else if (index == 1)
			{
				while ( insp[stack.peek()] != insp[0])
				{
					postfix.add(operators[stack.pop()]);
				}
				/** discard left parenthesis **/
				stack.pop();
			}
			else
			{

				while (!stack.isEmpty() && insp[stack.peek()] >= icmp[index])
				{
					postfix.add(operators[stack.pop()]);
				}	
				stack.push(index);

			}

		}
		while ((first = stack.pop()) != 8) 
		{
			postfix.add(operators[first]);
		}
		return postfix;
	}
	public int indexof(String str) 
	{
		for(int i = 0;i<operators.length;i++) {
			if(operators[i].equals(str))
				return i;
		}
		return 8;
	}
	public boolean tryParseDouble(String s) {  
		try {  
			Double.parseDouble(s);  
			return true;  
		} catch (NumberFormatException e) {  
			return false;  
		}  
	}
}
