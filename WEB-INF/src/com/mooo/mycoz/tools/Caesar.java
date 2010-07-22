package com.mooo.mycoz.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Caesar {
	public void key() {
		KeyGenerator kg;

		try {
			// 创建密钥生成器
			kg = KeyGenerator.getInstance("DESede");

			// 初始化密钥生成器
			kg.init(168);

			// 生成密钥
			SecretKey k = kg.generateKey();

			// 通过对象序列化方式将密钥保存在文件中
			FileOutputStream f;
			f = new FileOutputStream("key1.dat");
			ObjectOutputStream b = new ObjectOutputStream(f);
			b.writeObject(k);

			FileInputStream f1 = new FileInputStream("key1.dat");// 首先获取密钥
			ObjectInputStream b1 = new ObjectInputStream(f1);
			Key ke = (Key) b1.readObject();

			byte[] kb = ke.getEncoded();// 获取主要编码格式

			// 保存密钥编码格式
			FileOutputStream f2 = new FileOutputStream("keykb1.dat");
			f2.write(kb);
			// 打印密钥编码中的内容
			for (int i = 0; i < kb.length; i++) {
				System.out.print(kb[i] + ",");
			}
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 凯撒密码 ,移动的位数 2就是加密和解密所用的密钥 
	// String data 读取要加密的字符串 (明文 )
	// int key 读取密钥 (移动的位数 ,负数表示向左移动，正数表示向右移动 )
	public String encryption(String data,int key){
		String es = "";
		for (int i = 0; i < data.length(); i++) {// 取出字符串中每个字符
			char c = data.charAt(i);
			if (c >= 'a' && c <= 'z') {// 对每个字符进行移位，是小写字母
				c += key % 26;// 移动 key%26位
				if (c < 'a')
					c += 26;// 向左越界
				if (c > 'z')
					c -= 26;// 向右越界
			} else if (c >= 'A' && c <= 'Z') {// 大写字母

				c += key % 26;
				if (c < 'A')
					c += 26;
				if (c > 'Z')
					c -= 26;
			}
			es += c;
		}
		
		return es;
	}
	
	public String decryption(String data,int key){
		key = key * -1;
		return encryption(data,key);
	}
	
}