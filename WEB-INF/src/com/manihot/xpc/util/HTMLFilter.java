package com.manihot.xpc.util;

public class HTMLFilter {

	public String filter(StringBuffer input) {
		return new String(privateHelpMethod(new String(input)));
	}

	public String filter(String input) {
		return new String(privateHelpMethod(input));
	}

	private String privateHelpMethod(String input) {

		StringBuffer clean = new StringBuffer();
		boolean add = true;

		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) == '<') {
				add = false;
			} else if (input.charAt(i) == '>') {
				add = true;
				clean.append(' ');
			} else if (add == true) {
				clean.append(input.charAt(i));
			}

		}

		return new String(clean);
	}

}
