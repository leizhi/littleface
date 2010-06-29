package com.mooo.mycoz.sockt;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;

class Client {
	public Client() {
		try {
			Socket s = new Socket("127.0.0.1", 10000);

			InputStream in = s.getInputStream();
			DataInputStream din = new DataInputStream(in);

			OutputStream out = s.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);

			dout.writeUTF("服务器你好！我是客户端");
			System.out.println(din.readUTF());

			new Thread(new SenderMessage(dout)).start();
			new Thread(new ReaderMessage(din)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}

class ReaderMessage implements Runnable {
	private DataInputStream din;

	public ReaderMessage(DataInputStream din) {
		this.din = din;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		String str;
		try {
			while (true) {
				str = din.readUTF();
				System.out.println(new Date().toLocaleString() + "服务器说：" + str);
				if (str.equals("bye")) {
					System.out.println("服务器已经关闭，此程序自动退出！");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SenderMessage implements Runnable {
	private DataOutputStream dout;

	public SenderMessage(DataOutputStream dout) {
		this.dout = dout;
	}

	public void run() {
		String str;
		InputStreamReader inf = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(inf);
		try {
			while (true) {
				str = buf.readLine();
				dout.writeUTF(str);
				if (str.equals("bye")) {
					System.out.println("客户端自己退出！");
					System.exit(1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
