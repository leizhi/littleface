package com.manihot.xpc.util;

public class AmountWithUnit {

	double amount = 0;
	String samount = null;
	String unit = null;

	public AmountWithUnit(String samount, String unit) {
		this.samount = samount;
		this.unit = unit;
	}

	public AmountWithUnit(double amount, String unit) {
		this.amount = amount;
		this.samount = Double.toString(amount);
		this.unit = unit;
	}

	public AmountWithUnit() {
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String s) {
		unit = s;
	}

	public String getSAmount() {
		return samount;
	}

	public void setAmount(String s) {
		samount = s;
		amount = Double.parseDouble(s);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double d) {
		amount = d;
		samount = Double.toString(d);
	}
}
