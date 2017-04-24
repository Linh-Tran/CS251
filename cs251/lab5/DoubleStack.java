import java.util.*;

/**
 * @author Linh Tran
 * CS251.004 
 * March 31, 2014
 * Double stack countains the implemententions for Stack. The class contains four methods
 * isEmpty, push, and pop. All these classes uses methods pre-written inside the collections class of 
 * linkedList. 
 */

public class DoubleStack implements Stack <Double>{

	 LinkedList stack = new LinkedList(); //creates a LinkedList named stack.

    /**
    * This method checks if the LinkedList is empty using method of the 
    * collections class .isEmpty().
    * @return boolean true if stack is empty.
    */
    public boolean isEmpty(){

    	return stack.isEmpty();	
    }

    /**
    * This method push an object of type Double called val into the stack or 
    * LinkedList of type Double objects. This method also doesn't return any values.
    * collections class .push(val).
    * @param Double obeject called val.
    */
    public void push( Double val){

        stack.push(val);

    }

    /**
    * This method pop the first iteam off the stack and return it. 
    * LinkedList of type Double objects using collections class .pop().
    * @return Double obeject from the stack.
    */
    public Double pop(){
       return (Double)stack.pop();
       

    }

    /**
    * This method return the item at the top of the stack
    * using collections class .peek().
    * @return Double obeject from the stack.
    */
    public Double peek(){
        return (Double)stack.peek();

    }
}
