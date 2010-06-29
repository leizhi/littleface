package com.manihot.xpc.tools;

import java.sql.*;
import java.io.*;

/**
 * author@tongxiao
 * 
 * time:2007.09.14
 */
public class TestImage {
	private static final String URL = "jdbc:mysql://localhost/Image?user=root&password=root&useUnicode=true";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private File file = null;
	private InputStream inputImage = null;
	private OutputStream outputImage = null;

	public void blobInsert(String infile) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");

			conn = DriverManager.getConnection(URL);
			System.out.println("Database connected!");

			pstmt = conn
					.prepareStatement("insert into Country (name,flag,description) values (?,?,?)");
			pstmt.setString(1, "china");

			file = new File(infile);
			try {
				inputImage = new FileInputStream(file);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			pstmt.setBinaryStream(2, inputImage, (int) (file.length()));

			pstmt.setString(3, "A flag of China");
			pstmt.executeUpdate();
			System.out.println("commit successfully");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				inputImage.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void readBolb(String path, int id) {
		InputStream is = null;
		byte[] buffer = new byte[4096];

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");

			conn = DriverManager.getConnection(URL);
			System.out.println("Database connected!");

			pstmt = conn
					.prepareStatement("select flag from Country where id =?");
			System.out.println("select ok");

			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			rs.next();
			file = new File(path);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
			try {
				outputImage = new FileOutputStream(file);
				System.out.println(outputImage.toString());
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

			Blob blob = rs.getBlob("flag");
			is = blob.getBinaryStream();
			try {
				System.out.println(is.available());
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			try {
				System.out.println(is.available());
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			int size = 0;
			try {
				while ((size = is.read(buffer)) != -1) {
					System.out.println(size);
					outputImage.write(buffer, 0, size);

				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				is.close();
				outputImage.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		new TestImage().blobInsert("c:\\TDdownload\\china.gif");
		new TestImage().readBolb("c:\\1.gif", 1);
	}
}
