package com.mooo.mycoz.test.iterator;


public class Test {
	private Iterator it;

	private Teacher teacher = new ConcreteTeacher();

	public void operation() {

		it = teacher.createIterator(); // 老师开始点名

		while (!it.isDone()) { // 如果没点完

			System.out.println(it.currentItem().toString()); // 获得被点到同学的情况

			it.next(); // 点下一个

		}

	}

	public static void main(String agrs[]) {

		Test test = new Test();

		test.operation();

	}

}
