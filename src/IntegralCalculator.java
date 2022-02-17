import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class IntegralCalculator {
	
	ArrayList<String> postfix;
	
	public  void runcalculator() {  
		Scanner input = new Scanner(System.in);
		ToPostfixChanger post= new ToPostfixChanger();
		System.out.println("Please write the function");
		Calculator calc =new Calculator();
		String in= input.nextLine();
		
		System.out.print("The Function You have entered: \n"+in); //x^2+7*x-1/x    x^3+2*x^2+3*x-4
		System.out.println();
		 postfix =post.postfix(in);
		boolean cheack= cheacker(postfix);
		int n= 6000;
		System.out.println("Please write the boundaries of integration: ");
		System.out.print("a = ");double a= input.nextDouble();
		System.out.print("b = ");double b= input.nextDouble();
		System.out.println();
		System.out.println("Integration by Trapezoidal method: " + integralTrapez(a,b,n));
		System.out.println("Integration by Simpson's method: " + integralSimpson(a, b, n));
		if(cheack) 
		{
			Double result = EvaluatePostfix(10);
			System.out.println(result);
		}
		else
		{
			System.out.println("number of operand and operators is not correct");
			runcalculator();
		}
		input.close();
	}
	public double integralTrapez(double a, double b, int n)
	{
		double h= (b-a)/n;
		double I = h/2 * (EvaluatePostfix(a)+EvaluatePostfix(b));
		for(int i=1; i<n;i++) {
			I += h * EvaluatePostfix(a +i*h);
		}
		return I;
	}
	public double integralSimpson(double a, double b, int n) 
	{
		double h = (b-a)/n;
		double I = EvaluatePostfix(a) + EvaluatePostfix(b);
		for(int i = 1; i< n; i++)
		{
			int coefficient = i% 2 == 0 ? 2 : 4 ;
			I += coefficient * EvaluatePostfix(a+i*h);
			
		}
		I *= h/3;
		return I;
	}
	public static boolean cheacker(ArrayList<String> postfix) {
		int operatatorCount=0,operandCount=0;
		for(int i=0; i<postfix.size();i++) {
			if (tryParseDouble(postfix.get(i))||postfix.get(i).equals("x")) {
				operandCount++;
			}
			else
				operatatorCount++;
		}
		if(operatatorCount+1==operandCount) {
			return true;
        }
		else {
			return false;
		}		
	}
	public static boolean tryParseDouble(String s) {  
		try {  
			Double.parseDouble(s);  
			return true;  
		} catch (NumberFormatException e) {  
			return false;  
		}  
	}
	public double EvaluatePostfix(double x) {

		Stack<Double> result = new Stack<>();
		for (int i = 0; i < postfix.size(); i++) {
			String sign=postfix.get(i);
			if(tryParseDouble(sign)){
				result.push(Double.parseDouble(sign));
			}
			else if(sign.equals("x"))
				result.push(x);
			else {
				
				double num1=result.pop();
				
				double num2=result.pop();
				double expression= Calculator.calculat(num1, num2, sign);
				result.push(expression);
			}

		}
		double final_result=result.pop();

		return final_result;
	}

}
