package test;

import java.util.LinkedList;
import java.util.List;

public class Stack<T> {
	
	private List<T> stack;
	
	public Stack() {
		stack = new LinkedList<>();
	}
	
	public void push (T val) {
		stack.addFirst(val);
	}
	
	public T pop() {
		if (stack.isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		} else {
			return stack.removeFirst();
		}
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}

}
