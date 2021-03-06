package com.mooo.mycoz.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import com.mooo.mycoz.db.pool.DbConnectionManager;

public class IDGenerator {
	private static final String SELECT_MAX_BY_TABLE="SELECT MAX(ID) maxid FROM ";

	public static HashMap<String, String> getReportTypes() {

		HashMap<String, String> lMap = new HashMap<String, String>();
		lMap.put(null, "--select--");
		lMap.put("Debit", "Debit");
		lMap.put("Credit", "Credit");
		lMap.put("Receipt", "Receipt");
		lMap.put("Defrayment", "Defrayment");

		return lMap;
	} // getDCValues()

	public synchronized static Double getNextID(String table) {
		
		Connection connection=null;
        PreparedStatement pstmt = null;
        Double nextId=0d;
        try {
			connection = DbConnectionManager.getConnection();
            pstmt = connection.prepareStatement(SELECT_MAX_BY_TABLE+table);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	nextId = rs.getInt("maxid") * 1d;
            }
            
            if(nextId != null)
            	nextId ++;
            
		}catch (SQLException e) {
			e.printStackTrace();
	   }finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return nextId;
	} // getNextID(String table)

	public synchronized static int getNextID(Connection connection,String table) {
		
        PreparedStatement pstmt = null;
        ResultSet result = null;
        int nextId=0;
        try {
			pstmt = connection.prepareStatement(SELECT_MAX_BY_TABLE + table);
			result = pstmt.executeQuery();
			while (result.next()) {
				nextId = result.getInt("maxid");
			}

			nextId++;
		}catch (SQLException e) {
			e.printStackTrace();
	   }finally {
			try {
				if(result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nextId;
	} // getNextID(String table)
	
	public static List<?> search(Class<?> clazz){
		List<?> searchList = null;
		try {
			Object obj = clazz.newInstance();
			Method execMethod = clazz.getMethod("searchAndRetrieveList");
			searchList = (List<?>) execMethod.invoke(obj);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
		}
		return searchList;
	}
	
	public static Object randomNo(Class<?> clazz){
		List<?> searchList = search(clazz);
		Random random = new Random();
		int randomId = searchList.size();
		if (randomId > 0) {
			random.nextInt(randomId);
			return searchList.get(randomId);
		} else {
			randomId=0;
		}
		return 0;
	}
	
	public static int randomInt(int max){
		if(max > 0)
			return new Random().nextInt(max);
		
		return 0;
	}
	
	public static Object randomNo(List<?> retrieveList){
		Random random = new Random();
		int randomId = retrieveList.size();
		if (randomId > 0) {
			random.nextInt(randomId);
			return retrieveList.get(randomId);
		} else {
			randomId=0;
		}
		return 0;
	}

	public static Date randomDate(){
		Date randomDate = new Date();
		Random random = new Random();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 8, 1);
		long start = cal.getTimeInMillis();
		cal.set(2010, 9, 13);
		long end = cal.getTimeInMillis();
		for (int i = 0; i < 10; i++) {
			randomDate = new Date(start + (long) (random.nextDouble() * (end - start)));
		}
		
		return randomDate;
	}
	
	public static Map<?, ?> getSexs() {
		HashMap<Object, Object> sexs = new HashMap<Object, Object>();
		sexs.put("Male", "Male");
		sexs.put("Female", "Female");
		return sexs;
	}

	public static Map<?, ?> getEnables() {
		HashMap<Object, Object> sexs = new HashMap<Object, Object>();
		sexs.put("Yes", "Yes");
		sexs.put("No", "No");
		return sexs;
	}

	public static String getIdPrifix() {
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = (tmp + "").substring(2, 4);
		tmp = now.get(Calendar.MONTH) + 1;
		if (tmp < 10)
			prefix += "0" + tmp;
		else
			prefix += tmp;

		return prefix;
	}

	public static String getYYMM() {
		return getIdPrifix();
	}

	public static String getYY() {
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = (tmp + "").substring(2, 4);

		return prefix;
	}
	
	public static Date getDate() {
		
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		Date date = now.getTime();

		return date;
	}
	
	public static String getNowTime() {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
	
		return ft.format(getDate());
	}
	
	public static String getNowYear() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
	
		return ft.format(getDate());
	}
	
	public static String getNow() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return ft.format(getDate());
	}
	
	public static String getLastMonthToday() {

		// get default date in yyyy-mm-dd format
		TimeZone tz = TimeZone.getDefault();
		// tz.setID("Asia/Jakarta");
		Calendar now = Calendar.getInstance(tz);
		String prefix;
		int yy = now.get(Calendar.YEAR);
		int tmp = now.get(Calendar.MONTH);
		if (tmp == 0) {
			prefix = (yy - 1) + "";
			tmp = 12;
		} else {
			prefix = yy + "";
		}
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		tmp = now.get(Calendar.DATE);
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		return prefix;
	} /* getLastMonthToday() */

	public static String getToday() {

		// get default date in yyyy-mm-dd format
		TimeZone tz = TimeZone.getDefault();
		Calendar now = Calendar.getInstance(tz);
		String prefix = now.toString();
		int tmp = now.get(Calendar.YEAR);
		prefix = tmp + "";
		tmp = now.get(Calendar.MONTH) + 1;
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		tmp = now.get(Calendar.DATE);
		if (tmp < 10)
			prefix += "-0" + tmp;
		else
			prefix += "-" + tmp;

		return prefix;
	} /* getToday() */
}
