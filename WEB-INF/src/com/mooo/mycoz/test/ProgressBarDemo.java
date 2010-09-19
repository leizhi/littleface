package com.mooo.mycoz.test;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ProgressBarDemo implements ActionListener, ChangeListener {
	JFrame f = null;
	JProgressBar progressbar;
	JLabel label;
	Timer timer;
	JButton b;

	public ProgressBarDemo() {
		f = new JFrame("progressbar   Example");
		Container contentPane = f.getContentPane();

		label = new JLabel("   ", JLabel.CENTER);
		progressbar = new JProgressBar();
		progressbar.setOrientation(JProgressBar.HORIZONTAL);
		progressbar.setMinimum(0);
		progressbar.setMaximum(100);
		progressbar.setValue(0);
		progressbar.setStringPainted(true);
		progressbar.addChangeListener(this);
		progressbar.setPreferredSize(new Dimension(200, 30));
		progressbar.setBorderPainted(false);

		JPanel panel = new JPanel();
		b = new JButton("Start");
		b.addActionListener(this);
		panel.add(b);

		timer = new Timer(50, this);

		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(progressbar, BorderLayout.CENTER);
		contentPane.add(label, BorderLayout.SOUTH);

		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new ProgressBarDemo();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b) {
			timer.start();
		}

		if (e.getSource() == timer) {
			int value = progressbar.getValue();

			if (value < 100) {
				value++;
				progressbar.setValue(value);
			} else {
				timer.stop();
				progressbar.setValue(0);
			}
		}
	}

	public void stateChanged(ChangeEvent e1) {
		int value = progressbar.getValue();

		if (e1.getSource() == progressbar) {
			label.setText("\u76ee\u524d\u5df2\u5b8c\u6210\u8fdb\u5ea6\uff1a" + Integer.toString(value) + "   %");
		}
	}
}
