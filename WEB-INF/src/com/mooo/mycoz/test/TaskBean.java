package com.mooo.mycoz.test;

import java.io.*;

public class TaskBean implements Runnable, Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -6997550989236501377L;

	private int counter;
	private int sum;
	private boolean started;
	private boolean running;
	private int sleep;

	public TaskBean() {
		counter = 0;
		sum = 0;
		started = false;
		running = false;
		sleep = 100;
	}

	protected void work() {
		try {
			Thread.sleep(sleep);
			counter++;
			sum += counter;
		} catch (InterruptedException e) {
			setRunning(false);
		}
	}

	public synchronized int getPercent() {
		return counter;
	}

	public synchronized boolean isStarted() {
		return started;
	}

	public boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
		if (running)
			started = true;
	}

	public synchronized Object getResult() {
		if (isCompleted())
			return new Integer(sum);
		else
			return null;
	}

	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return counter == 100;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			setRunning(true);
			while (isRunning() && !isCompleted())
				work();
		} finally {
			setRunning(false);
		}
	}

}