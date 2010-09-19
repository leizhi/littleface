package com.mooo.mycoz.util;

import java.io.File;

public class FileWrapper {
	private File file;

	public FileWrapper(String path) {
		file = new File(path);
	}

	public FileWrapper(File file) {
		this.file = file;
	}

	public String getId() {
		return "file_" + file.hashCode();
	}

	public String getName() {
		return file.getName();
	}

	public String getAbsolutePath() {
		return file.getAbsolutePath();
	}

	public FileWrapper[] getChildren() {
		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			int length = files.length;
			FileWrapper[] wrappers = new FileWrapper[length];
			for (int i = 0; i < length; ++i) {
				wrappers[i] = new FileWrapper(files[i]);
			}
			return wrappers;
		}
		return new FileWrapper[0];
	}
}