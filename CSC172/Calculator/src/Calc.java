public class Calc 
{
	public String[] input;
	//the operators
	public String[] operators = {"(",")","*","/","+","-","<",">","=","!","&","|"};
	//their respective precedences
	public int[] precedence =   { 1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6 };
	//their respective number of operands needed for each
	public int[] numOperands =  { 0,  0,  2,  2,  2,  2,  2,  2,  2,  1,  2,  2 };
	//the postfixed string
	public Queue<String> postfx;
	//the stack used to hold variables
	public Stack<String> stack;
	//the total sum
	public Stack<Double> sum;
	
	//constructor
	public Calc(String[] in)
	{
		input = in;
		stack = new Stack<String>();
		postfx = new Queue<String>();
		sum = new Stack<Double>();
		
	}
	
	public Queue<String> toPost(String[] input,Queue<String> postfx)
	{
		//for each input character
		for(int i = 0; i < input.length; i++)
		{
			//if it's an operator
			if(checkIn(input[i],operators) != -1)
			{
				//if stacks empty, queue it
				if(stack.read() == null)
					stack.push(input[i]);
				
				//if its a (, queue it
				else if(input[i].equals("("))
					stack.push(input[i]);
				
				//if it's a closed parenthesis
				else if(input[i].equals(")"))
				{
					while(!(stack.read().toString().equals("(")))
						postfx.enqueue(stack.pop().toString());
					stack.pop();
				}
				
				//if its got a lower precedence than queue stuff from the top of the stack
				else if ((stack.read() != null) 
						&& (!stack.read().toString().equals("("))
						&& (precedence[checkIn(input[i], operators)] >= precedence[checkIn(stack.read().toString(), operators)]))
				{
					while((stack.read() != null) 
							&& (!stack.read().toString().equals("("))
							&& (precedence[checkIn(input[i], operators)] >= precedence[checkIn(stack.read().toString(), operators)]))
						postfx.enqueue(stack.pop().toString());
					stack.push(input[i]);
				}
				//if its higher precedence, add it to the stack
				else
					stack.push(input[i]);
			}
			//if it's an operand, queue it
			else
				postfx.enqueue(input[i]);
		}
		//queue everything in the stack
		while((stack.read() != null))
			postfx.enqueue(stack.pop().toString());
		return postfx;
	}
	
	//do the calculation
	public Double doCalc(Queue<String> post, Stack<Double> sum)
	{
		//while the list isn't empty
		while(post.callHead().next.data != null)
		{
			//get the data
			String data = post.callHead().next.data;
			//if its an operand, push it immediately as a double
			if(checkIn(data,operators) == -1)
			{
				sum.push(Double.valueOf(data).doubleValue());
				post.dequeue();
			}
			
			//if its an operator that needs 1, pop 1 element from the stack
			else if(numOperands[checkIn(data,operators)] == 1)
			{
				sum.push(doFunc1(data,sum.pop()));
				post.dequeue();
			}
			
			//if its anything else, pop 2
			else
			{
				Double pop1 = sum.pop();
				Double pop2= sum.pop();
				sum.push(doFunc2(data,pop2,pop1));
				post.dequeue();
			}
		}
		//return the sum at the end of the calculation
		return sum.read();
	}
	
	//find the indecy of an element in an array
	public int checkIn(String o, String[] p)
	{
		for(int i = 0; i < p.length; i++)
			if(o.equals(p[i]))
				return i;
		return -1;
	}
	
	//if the operator requires 2 operands do this
	public double doFunc2(String operator, Double op1,Double op2)
	{
		switch (operator) 
		{
			//each operator has its corresponding operation
			case "+": return op1 + op2;
			case "-": return op1 - op2;
			case "*": return op1 * op2;
			case "/": return op1 / op2;
			case "<": 
				if(op1 < op2)
					return 1;
				else
					return 0;
			case ">": 
				if(op1 > op2)
					return 1;
			  	else
			        return 0;
			case "=":
				if(op1.equals(op2))
 	  		  		return 1;
	  		  	else
	  		  		return 0;
			case "&":
				if(op1.equals(op2) && !op1.equals(0.0))
 	  		  		return 1;
	  		  	else
	  		  		return 0;
			case "|":
				if(!(op1.equals(op2)))
					return 1;
				else
					return 0;
		}
		return 0;
	}
	
	//! only needs one operand
	public double doFunc1(String operator, Double op1)
	{
		switch (operator) 
		{
			case "!":
				if((op1.equals(1.0)))
					return 0;
				else
					return 1;
		}
		return 0;
	}
}
