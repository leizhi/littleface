package com.mooo.mycoz.test;

public class PeopleMain {

	public static void main(String[] args) {

		try {
				People students = null;

				for(int i=0;i<10;i++) {
					System.out.println("i = " + i);
					students = PeopleFactory.getInstance(""+i);
					System.out.println("People = " + students);
					students.think();
					students.say();				
				}

  			
		} catch (Exception e) {
			System.err.println("Error in DbForum.groupsWithPermission:" + e);
			e.printStackTrace();
		}
	}
	}
