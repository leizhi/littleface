package com.manihot.xpc.tools;

import javax.swing.UIManager;
import java.awt.*;

public class JMemoryDemo {
	private boolean packFrame = false;

	public JMemoryDemo() {
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
		new JMemoryDemo();
	}
}
