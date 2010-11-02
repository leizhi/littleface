package com.mooo.mycoz.example;

import javax.swing.UIManager;

import com.mooo.mycoz.tools.MainFrame;

import java.awt.*;

public class MemoryDemo {
	private boolean packFrame = false;

	public MemoryDemo() {
		MainFrame frame = new MainFrame();

		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MemoryDemo();
	}
}
