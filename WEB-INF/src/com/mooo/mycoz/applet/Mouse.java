package com.mooo.mycoz.applet;

//Mouse.java
import java.awt.*;
import java.applet.*;

public class Mouse extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text = "";

	public void paint(Graphics g) {
		g.drawString(text, 20, 20);
	}

	public boolean mouseDown(Event evt, int x, int y)// 鼠标按下处理函数
	{
		text = "Mouse Down";
		repaint();
		return true;
	}

	public boolean mouseUp(Event evt, int x, int y)// 鼠标松开处理函数
	{
		text = "";
		repaint();
		return true;
	}

	public boolean keyDown(Event evt, int x)// 键盘被按下的处理函数
	{
		text = "Key Down";
		repaint();
		return true;
	}

	public boolean keyUp(Event evt, int x)// 键盘被松开的处理函数
	{
		text = "";
		repaint();
		return true;
	}
}