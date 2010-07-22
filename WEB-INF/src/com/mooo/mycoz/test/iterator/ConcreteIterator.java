package com.mooo.mycoz.test.iterator;


public class ConcreteIterator implements Iterator {
	private ConcreteTeacher teacher;

	private int index = 0;

	private int size = 0;

	public ConcreteIterator(ConcreteTeacher teacher) {

		this.teacher = teacher;

		size = teacher.getSize(); // 得到同学的数目

		index = 0;

	}

	public void first() { // 第一个

		index = 0;

	}

	public void next() { // 下一个

		if (index < size) {

			index++;

		}

	}

	public boolean isDone() { // 是否点名完毕

		return (index >= size);

	}

	public Object currentItem() { // 当前同学的出勤情况

		return teacher.getElement(index);

	}

}
