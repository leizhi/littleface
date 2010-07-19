package com.mooo.mycoz.sockt;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.util.PropertyManager;

public class Server extends ServerSocket {
	private static Log log = LogFactory.getLog(Server.class);

	private static ArrayList<CreateServerThread> Threader = new ArrayList<CreateServerThread>();
	protected static final int SERVER_PORT = Integer.valueOf(PropertyManager.getProperty("serverPort")).intValue();

	public Server() throws IOException {
		super(SERVER_PORT);

		if(log.isDebugEnabled()) log.debug("server start ok");
		
		System.out.println("服务器启动");
		
		try {
			while (true) {
				Socket socket = accept();
				new CreateServerThread(socket);
			}
		} finally {
			close();
		}
		
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

	// --- Broadcast

	class Broadcast extends Thread {

		public void run() {
			try {
				byte[] bytes = new byte[1024];
				String str="";
				int readSize=0;

				while (true) {
					readSize = System.in.read(bytes);
					
					System.out.println("Threader Size=" + Threader.size());

					for (int i = 0; i < Threader.size(); i++) {
						CreateServerThread client = (CreateServerThread) Threader.get(i);
						client.out.write(bytes);
					}
					
					str = new String(bytes).toString();
					str= str.substring(0,readSize-2);
					
					if (str.equals("bye")) {

						for (int i = 0; i < Threader.size(); i++) {
							CreateServerThread client = (CreateServerThread) Threader.get(i);
							Threader.remove(client);
						}

						System.out.println("服务器退出！");
						System.exit(1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// --- CreateServerThread
	class CreateServerThread extends Thread {
		private Socket socket;
		private OutputStream out;
		private InputStream in;

		// private String Username;

		public CreateServerThread(Socket client) throws IOException {
			socket = client;

			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			String value="--- Welcome to this chatroom 欢迎---\n";
			out.write(value.getBytes());
			value="input your nickname:\n";
			out.write(value.getBytes());

			start();
		}

		public void run() {
			synchronized (in) {
				Threader.add(this);
				new ReadMessage(this).start();
			}
			
			new Broadcast().start();

		}
		
	}

	// 接受客户端信息
	class ReadMessage extends Thread {
		// private DataInputStream din;
		CreateServerThread st;

		public ReadMessage(CreateServerThread st) {
			this.st = st;
		}

		public void run() {
			synchronized(st.in){
			try {
				String str="";
				String cmd="";

				int i=0;
				
				while (true) {
					/*byte bt = (byte)st.in.read();
					System.out.printf("read 0x%02X 0d%3d", bt,bt);
					System.out.println();
					
					//st.out.write(bt);
					//st.out.flush();
					*/
					byte bt = (byte)st.in.read();
					System.out.printf("\tread 0x%02X 0d%3d", bt,bt);
					System.out.flush();
					System.out.println();

					if(bt==(byte)0xAA){
						//
						System.out.println("客户端查询数据库...");
						str = new Service().list();
						//st.out.write(str.getBytes());
						System.out.println("sql ruest:"+str);
						//
						
						bt = (byte)st.in.read();
						
						System.out.printf("\tcomammd 0x%02X 0d%3d", bt,bt);
						
						byte hSize=(byte)st.in.read();
						byte lSize=(byte)st.in.read();
						
						//System.out.printf("\thSize 0x%02X lSize 0x%02X", hSize,lSize);
						
						//System.out.printf("\thSize<<8 0x%04X", ((int)hSize<<8)+lSize);

						System.out.printf("\tSize 0x%04X 0d%3d", ((int)(hSize<<8)+lSize),((int)(hSize<<8)+lSize));

						for(i=0;i<((int)(hSize<<8)+lSize);i++){
							bt = (byte)st.in.read();
							System.out.printf("\tdate 0x%02X 0d%3d", bt,bt);
						}
						
						bt = (byte)st.in.read();
						System.out.printf("\tend 0x%02X 0d%3d", bt,bt);

						System.out.println();
					} else {

						if(bt==13){
							cmd +=(char)bt;
							bt = (byte)st.in.read();
							if(bt==10){
								cmd +=(char)bt;
								System.out.println("cmd ="+cmd);
								cmd = "";
							}
						} else {
							cmd +=(char)bt;
						}
						//st.out.write(cmd.getBytes());
						//st.out.flush();
						
						if (cmd.equals("search")) {
							System.out.println("客户端查询数据库...");
							str = new Service().list();
							st.out.write(str.getBytes());
						}
						
						if (cmd.equals("bye")) {
							System.out.println("客户端下线！");
							st.in.close();
							st.out.close();
							st.socket.close();
							Threader.remove(st);
							break;
						}
					} 
				}//end while
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					st.in.close();
					st.out.close();
					st.socket.close();
					Threader.remove(st);
					
					System.out.println("客户失去连接...");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	 }//synchronized
	}

	// 发出服务器信息
	class SendMessage extends Thread {
		private Socket socket;

		public SendMessage(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			
			synchronized(socket){

			try {
				byte[] bytes = new byte[1024];
				String str="";
				int readSize=0;
				
				while (socket.isConnected()) {
					
					readSize = System.in.read(bytes,0,readSize);
					
					str = new String(bytes).toString();
					str = str.substring(0,readSize);
					
					socket.getOutputStream().write(str.getBytes());
					
					str= str.substring(0,readSize-2);
					
					if (str.equals("bye")) {
						Threader.remove(this);
						System.out.println("服务器退出！");
						System.exit(1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 }//synchronized

}
