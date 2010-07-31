package com.mooo.mycoz.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

	public static boolean copy(java.io.File filefrom, java.io.File fileto,
			boolean rewrite) {
		if (!filefrom.exists()) {
			System.out.println("文件不存在");
			return false;
		}
		if (!filefrom.isFile()) {
			System.out.println("不能够复制文件夹");
			return false;
		}
		if (!filefrom.canRead()) {
			System.out.println("不能够读取需要复制的文件");
			return false;
		}
		if (!fileto.getParentFile().exists()) {
			fileto.getParentFile().mkdirs();
		}
		if (fileto.exists() && rewrite) {
			fileto.delete();
		}

		try {
			FileInputStream fosfrom = new FileInputStream(filefrom);
			FileOutputStream fosto = new FileOutputStream(fileto);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public static boolean copy(String from, String to) {
		java.io.File filefrom = new java.io.File(from);
		java.io.File fileto = new java.io.File(to);
		return copy(filefrom, fileto, true);
	}
}
