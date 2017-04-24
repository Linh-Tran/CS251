import java.util.*;

/**
 * @author Linh Tran
 * CS251.004 
 * March 31, 2014

 * PostFixCalcultor countains the implemententions for the Operator interface for it of 
 * +, -, * , / , and = . using four anonymous classes. The implementations for each operator is 
 * done in the class's constructor. The class also creates a stack of type DoubleStack 
 * and a map that maps the strings operations with its mathematical expressions. This class 
 * has two classes one just stores a operator on a stack and one evaluates the expression.
 * 
 */

public class PostFixCalculator {
	private DoubleStack stack = new DoubleStack(); //creates a stack of type DoubleStack.
	/* Creates a HashMap that maps strings to objects of type Operator*/
	private Map <String, Operator> operatorMap = new HashMap  <String, Operator> ();

	/**
	* The PostCalculator constructor assign the operators +, -, *, /, = to their 
	* functions. Each of the operators are implements numArgs and eval of Operator interface.
	* numArgs returns an int telling how many arguments does this operator takes, for instance
	* all other 4 operators takes two arguements except for = operator. The seond method that they 
	* implement is eval which takes in a list of Double objects they return result of the arguments 
	* evaluated from the list. 
    */
	PostFixCalculator (){ 
		/**
	    * This anonymmous class creates a new operator and 
	    * implements two methods of the Operator interface numArgs and eval.
	    */
		Operator plus = new Operator (){
			/** 
		     * Takes 2 arguements 
		     * @return 2 arguments required by this operator.
		     */
			public int numArgs(){ return 2;}
			/**
		     * Evaluate this operator using the given arguments.
		     * @param args Argument list.
		     * @return Result of the operation.
		     */
			public double eval(List <Double> args){
				return args.get(0)+ args.get(1); 
			}
		};
		Operator sub = new Operator(){ 
			public int numArgs(){ 	return 2;}
			public double eval(List <Double> args){
				return args.get(0)-args.get(1);}
		};
		Operator mult = new Operator(){
			public int numArgs(){ 	return 2;}
			public double eval(List <Double> args){ return args.get(0)*args.get(1);}
		};
		Operator div = new Operator(){
			public int numArgs(){ 	return 2;}
			public double eval(List <Double> args){ return args.get(0)/args.get(1);}
		};

		Operator print = new Operator(){
			/** 
		     * Takes 1 arguement 
		     * @return 1 argument required by this operator.
		     */
			public int numArgs(){ 	return 1;}
			/**
		     * Print out the arguement in the list as a double and return
		     * the 1st item in the list.
		     * @param args Argument list.
		     * @return Result of the operation.
		     */
			public double eval(List <Double> args){ 
				Double a = args.get(0);

				System.out.println(a.doubleValue());
				return args.get(0);
			}
		};

		//fill the operator map with assocations of symbols to operator objects both the
		// symbol and the string term to their functions. 
		operatorMap.put("+", plus);
		operatorMap.put("add", plus);
		operatorMap.put("-", sub);
		operatorMap.put("sub", sub);
		operatorMap.put("*", mult);
		operatorMap.put("mult", mult);
		operatorMap.put("/", div);
		operatorMap.put("div", div);
		operatorMap.put("=", print);
		operatorMap.put("print", print);

	}
	/**
	 * storeOperand method takes a double and pushes it onto the operand stack.
	 * It does not return anything. 
	 * @param Double x
	 */
	void storeOperand(Double x){
		stack.push(x);

	}
	/**
	* The evalOperator takes an operator string, looks up the corresponding operator
	* object in the operator map, pops the appropriate number of operands (as given
	* by the numArgs method) and places them into a list, evaluates the operator with
	* the operands in the list, and pushes the result onto the operand stack. It does
	* not return anything.
	*/
	void evalOperator(String x){
		Double obj1, obj2, result;
		Operator op = operatorMap.get(x);
		/* creates a LinkedList of Double called args */
		LinkedList <Double> args = new LinkedList <Double>();

		/* if the numArgs is 2 then pop of two of the operands and push them onto the args
		   list then eval the args with the two operands in the list and store them as
		   a Double object called result then push the result on the stack
		 */

		if( op.numArgs() ==2) { 
			obj1 = stack.pop();
			obj2 = stack.pop();
			args.push(obj1);
			args.push(obj2);
			result = op.eval(args);
			stack.push(result);
		}
		/*if the numArgs is 1 then it will only pop off one operand from the stack
		  and push onto args list, then evalute and store into result as a Double. 
		  and push the reuslt back onto the stack.
		  */ 
		else if (op.numArgs() ==1){
			obj1 = stack.pop();
			args.push(obj1);
			result = op.eval(args);
			stack.push(result);
		}
	}
}
	
