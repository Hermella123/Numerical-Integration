import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

	
	public static double calculat(double x, double y, String op ) {
		switch(op){
		case "+":
			return y + x;
		case "-":
			return y - x;
		case "/":
			return y / x;
		case "*":
			return y * x;
		case "^":
			return Math.pow(y, x);
		default:
			System.out.println("something wrong");
			return 0;
		}
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

