package com.manihot.xpc.tools;

/**
 * 
 */

/**
 * @author xpc
 * 
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mooo.mycoz.bean.accounting.*;

public class TestAccounting {
	private static Log log = LogFactory.getLog(TestAccounting.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ARBean ab = ARBean.getInstance();
			int[][] gl = ab.getJobNoteNo();
			for (int i = 0; i < gl.length; i++) {
				System.out.println("ID=" + gl[i][0] + " BranchI=" + gl[i][1]);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());

		}
	}

}
