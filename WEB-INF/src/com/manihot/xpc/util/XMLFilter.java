/*
 * Created on Aug 9, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.manihot.xpc.util;

/**
 * @author xxue
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class XMLFilter {

	public static String applyXMLFilters(String input) {
		input = encodingAmpsand(input);
		input = encodingLT(input);
		input = encodingGT(input);
		return input;
	}

	public static String encodingAmpsand(String input) {
		if (input == null || input.indexOf("&") < 0) {
			return input;
		} else {
			int index;
			String output = "";

			while ((index = input.indexOf("&")) >= 0) {
				if (index == input.length() - 1) {
					output += input.substring(0, index) + "&amp;";
					input = "";
					break;
				} else {
					// check if already encoded with "&amp;"
					if (input.indexOf("&amp;") == index) {
						if (index + 4 == input.length() - 1) {
							output += input;
							input = "";
							break;
						} else {
							output += input.substring(0, index + 5);
							input = input.substring(index + 5);
						}
					} else if (input.indexOf("&lt;") == index
							|| input.indexOf("&gt;") == index) {
						if (index + 3 == input.length() - 1) {
							output += input;
							input = "";
							break;
						} else {
							output += input.substring(0, index + 4);
							input = input.substring(index + 4);
						}
					} else {
						output += input.substring(0, index) + "&amp;";
						input = input.substring(index + 1);
					}
				}
			}

			output += input;
			return output;
		}
	}

	public static String encodingLT(String input) {
		if (input == null || input.indexOf("<") < 0) {
			return input;
		} else {
			int index;
			String output = "";
			while ((index = input.indexOf("<")) >= 0) {
				if (index == input.length() - 1) {
					output += input.substring(0, index) + "&lt;";
					input = "";
					break;
				}

				output += input.substring(0, index) + "&lt;";
				input = input.substring(index + 1);
			}

			output += input;
			return output;
		}
	}

	public static String encodingGT(String input) {
		if (input == null || input.indexOf(">") < 0) {
			return input;
		} else {
			int index;
			String output = "";
			while ((index = input.indexOf("<")) >= 0) {
				if (index == input.length() - 1) {
					output += input.substring(0, index) + "&gt;";
					input = "";
					break;
				}

				output += input.substring(0, index) + "&gt;";
				input = input.substring(index + 1);
			}
			output += input;
			return output;
		}
	}

	public static String encodingAmpsands(String input) {// add by gchen at
															// 07,07,25
		if (input == null || input.indexOf("&") < 0 || input.indexOf("(") < 0
				|| input.indexOf(")") < 0) {
			return input;
		} else {
			int index;
			String output = "";
			while ((index = input.indexOf("&")) >= 0) {
				if (index == input.length() - 1) {
					output += input.substring(0, index);
					input = "";
				} else {
					output += input.substring(0, index);
					input = input.substring(index + 1);
				}
			}
			output += input;
			input = "";
			while ((index = output.indexOf("(")) >= 0) {
				if (index == output.length() - 1) {
					input += output.substring(0, index);
					output = "";
				} else {
					input += output.substring(0, index);
					output = output.substring(index + 1);
				}
			}
			input += output;
			output = "";
			while ((index = input.indexOf(")")) >= 0) {
				if (index == input.length() - 1) {
					output += input.substring(0, index);
					input = "";
				} else {
					output += input.substring(0, index);
					input = input.substring(index + 1);
				}
			}
			output += input;
			return output;
		}
	}

}
