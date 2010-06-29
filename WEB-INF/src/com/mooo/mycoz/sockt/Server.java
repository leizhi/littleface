package com.mooo.mycoz.sockt;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends ServerSocket {
	// private static ArrayList<String> User_List = new ArrayList<String>();
	private static ArrayList<CreateServerThread> Threader = new ArrayList<CreateServerThread>();
	// private static LinkedList<String> Message_Array = new
	// LinkedList<String>();
	// private static int Thread_Counter = 0;
	// private static boolean isClear = true;
	protected static final int SERVER_PORT = 10000;
	protected FileOutputStream LOG_FILE = new FileOutputStream(
			"logs/connect.log", true);

	public Server() throws FileNotFoundException, IOException {
		super(SERVER_PORT);
		// new Broadcast();

		// append connection log
		Calendar now = Calendar.getInstance();
		String str = "[" + now.getTime().toString()
				+ "] Accepted a connection\015\012";
		byte[] tmp = str.getBytes();
		LOG_FILE.write(tmp);

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
			InputStreamReader inr = new InputStreamReader(System.in);
			BufferedReader buf = new BufferedReader(inr);
			String str;
			try {
				while (true) {
					str = buf.readLine();
					System.out.println("Threader Size=" + Threader.size());

					for (int i = 0; i < Threader.size(); i++) {
						CreateServerThread client = (CreateServerThread) Threader
								.get(i);
						client.dout.writeUTF(str);
					}

					if (str.equals("bye")) {

						for (int i = 0; i < Threader.size(); i++) {
							CreateServerThread client = (CreateServerThread) Threader
									.get(i);
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
		DataOutputStream dout;
		private InputStream in;
		DataInputStream din;

		// private String Username;

		public CreateServerThread(Socket client) throws IOException {
			socket = client;

			in = socket.getInputStream();
			din = new DataInputStream(in);

			out = socket.getOutputStream();
			dout = new DataOutputStream(out);

			dout.writeUTF("--- Welcome to this chatroom ---");
			dout.writeUTF("Input your nickname:");

			start();
		}

		/*
		 * public void sendMessage(String msg) { try { dout.writeUTF(msg);
		 * }catch(IOException e){ } }
		 * 
		 * public void readMessage(DataInputStream din) { new ReadMessage(din);
		 * }
		 * 
		 * public void sendMessage(DataOutputStream dout) { new
		 * SendMessage(dout); }
		 */
		public void run() {

			Threader.add(this);

			new ReadMessage(this).start();
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

		@SuppressWarnings("deprecation")
		public void run() {
			String str;
			try {
				while (true) {
					str = st.din.readUTF();
					System.out.println(new Date().toLocaleString() + "客户端说："
							+ str);
					if (str.equals("bye")) {
						System.out.println("客户端下线！");
						Threader.remove(this);
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 发出服务器信息
	class SendMessage extends Thread {
		// private DataOutputStream dout;
		CreateServerThread st;

		public SendMessage(CreateServerThread st /* DataOutputStream dout */) {
			this.st = st;
		}

		public void run() {
			InputStreamReader inr = new InputStreamReader(System.in);
			BufferedReader buf = new BufferedReader(inr);
			String str;
			try {
				while (true) {
					str = buf.readLine();
					st.dout.writeUTF(str);

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
}
