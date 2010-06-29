package com.manihot.xpc.util;

import java.util.LinkedList;

public class Stack {
	private LinkedList<Object> list = new LinkedList<Object>();

	public void push(Object v) {
		list.addFirst(v);
	}

	public Object top() {
		return list.getFirst();
	}

	public Object pop() {

		return list.removeFirst();

	}

}
