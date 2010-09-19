package com.mooo.mycoz.test;

import javax.swing.*;

public class ProgressThread extends Thread {
	JProgressBar pbar;
	JLabel label;

	public ProgressThread(JProgressBar pbar, JLabel label) {
		this.pbar = pbar;
		this.label = label;
	}

	public void run() {

		int min = 0;
		int max = 100;
		int free = 0;
		int totle = 0;
		int status = 0;

		pbar.setMinimum(min);
		pbar.setMaximum(max);
		pbar.setValue(status);
		while (true) {
			try {
				totle = (int) (Runtime.getRuntime().totalMemory() / 1024);
				free = (int) (Runtime.getRuntime().freeMemory() / 1024);
			} catch (Exception e) {
				e.printStackTrace();
			}
			label.setText("Free Memory :"
					+ (int) (Runtime.getRuntime().freeMemory() / 1024) + "K"
					+ " Totle Memory Allo cated :"
					+ (int) (Runtime.getRuntime().totalMemory() / 1024) + "K");
			status = (int) (free * 100 / totle);
			pbar.setValue(status);
			pbar.setString("Free Momery " + status + "%");
			try {
				this.sleep(1000);
			} catch (InterruptedException err) {
			}
		}

	}

}
