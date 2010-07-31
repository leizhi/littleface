package com.mooo.mycoz.util;

import java.sql.*;

import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class ImageUtil {

	private Connection myConnection = null;

	/* 图片表名 */
	private String strTabName;
	/* 图片ID字段名 */
	private String strIDName;
	/* 图片字段名 */
	private String strImgName;

	/**
	 * 
	 * 加载java连接Oracle的jdbc驱动
	 */

	public ImageUtil() {

	}

	/**
	 * 
	 * 根据图片在数据库中的ID进行读取
	 * 
	 * @param strID
	 *            图片字段ID
	 * 
	 * @param w
	 *            需要缩到的宽度
	 * 
	 * @param h
	 *            需要缩到高度
	 * 
	 * @return 缩放后的图片的byte数据
	 */

	public byte[] GetImgByteById(String strID, int w, int h) {

		// System.out.println("Get img data which id is " + nID);

		byte[] data = null;

		try {

			Statement stmt = myConnection.createStatement();

			ResultSet myResultSet = stmt.executeQuery("select "
					+ this.strIDName + " from " + this.strTabName + " where "
					+ this.strIDName + "=" + strID);

			StringBuffer myStringBuffer = new StringBuffer();

			if (myResultSet.next()) {

				java.sql.Blob blob = myResultSet.getBlob(this.strImgName);

				InputStream inStream = blob.getBinaryStream();

				try {

					long nLen = blob.length();

					int nSize = (int) nLen;

					// System.out.println("img data size is :" + nSize);

					data = new byte[nSize];

					inStream.read(data);

					inStream.close();

				} catch (IOException e) {

					System.out.println("获取图片数据失败,原因:" + e.getMessage());

				}

				data = ChangeImgSize(data, w, h);

			}

			System.out.println(myStringBuffer.toString());

			myConnection.commit();

			myConnection.close();

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());

		}

		return data;

	}

	/**
	 * 
	 * 根据图片在数据库中的ID进行读取，显示原始大小的图片
	 * 
	 * @param strID
	 *            图片字段ID
	 * 
	 * @return 读取后的图片byte数据
	 */

	public byte[] GetImgByteById(String strID) {

		// System.out.println("Get img data which id is " + nID);

		byte[] data = null;

		try {

			Statement stmt = myConnection.createStatement();

			ResultSet myResultSet = stmt.executeQuery("select "
					+ this.strIDName + " from " + this.strTabName + " where "
					+ this.strIDName + "=" + strID);

			StringBuffer myStringBuffer = new StringBuffer();

			if (myResultSet.next()) {

				java.sql.Blob blob = myResultSet.getBlob(this.strImgName);

				InputStream inStream = blob.getBinaryStream();

				try {

					long nLen = blob.length();

					int nSize = (int) nLen;

					data = new byte[nSize];

					inStream.read(data);

					inStream.close();

				} catch (IOException e) {

					System.out.println("获取图片数据失败,原因:" + e.getMessage());

				}

			}

			System.out.println(myStringBuffer.toString());

			myConnection.commit();

			myConnection.close();

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());

		}

		return data;

	}

	/**
	 * 
	 * 缩小或放大图片
	 * 
	 * @param data
	 *            图片的byte数据
	 * 
	 * @param w
	 *            需要缩到的宽度
	 * 
	 * @param h
	 *            需要缩到高度
	 * 
	 * @return
	 */

	private byte[] ChangeImgSize(byte[] data, int nw, int nh) {

		byte[] newdata = null;

		try {

			BufferedImage bis = ImageIO.read(new ByteArrayInputStream(data));

			int w = bis.getWidth();

			int h = bis.getHeight();

			double sx = (double) nw / w;

			double sy = (double) nh / h;

			AffineTransform transform = new AffineTransform();

			transform.setToScale(sx, sy);

			AffineTransformOp ato = new AffineTransformOp(transform, null);

			// 原始颜色

			BufferedImage bid = new BufferedImage(nw, nh,
					BufferedImage.TYPE_3BYTE_BGR);

			ato.filter(bis, bid);

			// 转换成byte字节

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ImageIO.write(bid, "jpeg", baos);

			newdata = baos.toByteArray();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return newdata;

	}

}
