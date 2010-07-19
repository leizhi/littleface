package com.mooo.mycoz.sockt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;

import com.mooo.mycoz.util.PropertyManager;

public class Client {
	public Client() {
		try {
			Socket s = new Socket(PropertyManager.getProperty("serverHost"), Integer.valueOf(PropertyManager.getProperty("serverPort")).intValue());

			OutputStream out = s.getOutputStream();

			//out.write("服务器你好！我是客户端".getBytes());
			
			out.write((byte)0xAA);

			new Thread(new SenderMessage(out)).start();
			new Thread(new ReaderMessage(s)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}

class ReaderMessage implements Runnable {
	private Socket socket;

	public ReaderMessage(Socket socket) {
		this.socket = socket;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		try {
			byte[] buf = new byte[1024];
			String str;
			InputStream in = socket.getInputStream();

			//while (true) {
			while (socket.isConnected()) {

				int byteSize = in.read(buf);
				str = new String(buf).toString();
				
				str= str.substring(0,byteSize);
				
				str = new String(str.getBytes(), "UTF-8");
				
				System.out.println("read length:"+byteSize);
				System.out.println("server say length:"+str.length());
				
				System.out.println(new Date().toLocaleString() + "服务器说：" + str);
				if (str.equals("bye")) {
					System.out.println("服务器已经关闭，此程序自动退出！");
					break;
				}
			}
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SenderMessage implements Runnable {
	private OutputStream out;

	public SenderMessage(OutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			byte[] buf = new byte[1024];
			String str;

			while (true) {
				int byteSize =System.in.read(buf);
				str = new String(buf).toString();
				str= str.substring(0,byteSize-2);// rm \r\n
				
				out.write(str.getBytes());
				out.flush();
				
				System.out.println(str);
				System.out.println("input length:"+str.length());

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