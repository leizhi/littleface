package com.mooo.mycoz.tools;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbTools {
	private static Log log = LogFactory.getLog(DbTools.class);

	public void exportDb(){
		try{
			FileOutputStream fout;
			String backupPath="/tmp";

			Runtime rt = Runtime.getRuntime();
			
			String[] cmd = new String[3];
			cmd[0] = new String("/home/zlei/apache-tomcat-5.5.30/webapps/littleface/WEB-INF/src/scripts/bkcmd.sh");
			// parameterize variables
			cmd[1] = new String("/home/zlei/sddb");//update path
			cmd[2] = new String();//date 
			
			Process pr = rt.exec(cmd);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			String res;
			while ((res = br.readLine()) != null) {
				if (log.isDebugEnabled()) log.debug("print command stderr=" + res);
				System.out.println("print command stderr=" + res);
			}
			br.close();
			
			br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while ((res = br.readLine()) != null) {
				if (log.isDebugEnabled()) log.debug("print command stdout=" + res);
				System.out.println("print command stdout=" + res);
			}
			br.close();
			
			int ret = pr.waitFor();
			if (log.isDebugEnabled()) log.debug("print command exitValue= " + ret);
			System.out.println("print command exitValue= " + ret);

		}catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		DbTools dbTools = new DbTools();
		dbTools.exportDb();
	}
}
