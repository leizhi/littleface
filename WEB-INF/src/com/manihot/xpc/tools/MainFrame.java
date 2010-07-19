package com.manihot.xpc.tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	private BorderLayout borderLayout1 = new BorderLayout();
	private JProgressBar jProgressBar1 = new JProgressBar();
	private JLabel jLabel1 = new JLabel();
	private JLabel jLabel2 = new JLabel();

	public MainFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		MainFrame.class.getResource("[Your Icon]");

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(304, 215));
		this.setTitle("JMemoryDemo");
		jLabel1.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Memory Monitor");
		jProgressBar1.setOrientation(JProgressBar.VERTICAL);
		jProgressBar1.setFont(new java.awt.Font("Dialog", 0, 14));
		jProgressBar1.setToolTipText("");
		jProgressBar1.setStringPainted(true);
		jLabel2.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel2.setText("");
		contentPane.add(jProgressBar1, BorderLayout.CENTER);
		contentPane.add(jLabel1, BorderLayout.NORTH);
		contentPane.add(jLabel2, BorderLayout.SOUTH);

		ProgressThread pThread = new ProgressThread(this.jProgressBar1,
				this.jLabel2);
		pThread.start();
	}

	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
}
