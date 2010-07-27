package com.mooo.mycoz.action.operation;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozBranch.Download;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.UploadFile;

public class FileAction extends BaseSupport {
	private static Log log = LogFactory.getLog(FileAction.class);

	public String list(HttpServletRequest request, HttpServletResponse response) {
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if (log.isDebugEnabled()) log.debug("list");

			long fileBit = 0;
			double fileK = 0.0;
			double fileM = 0.0;
			String uploadPath = request.getRealPath("/")+"upload";
			
			if (log.isDebugEnabled()) log.debug("uploadPath = "+uploadPath);

			String value = null;
			String sql = null;
			File DownloadFile = null;

			DecimalFormat df = new DecimalFormat("###0.00");

			//ResultSet rs = null;

			sql = "SELECT dl.ID,dt.Name,dl.Name,dl.Date,dl.Description,dl.DownloadPath,dl.ImagePath FROM Download AS dl,mycozShared.DownloadType AS dt WHERE dl.TypeID=dt.ID";

			value = request.getParameter("Key");
			
			if (value != null && !value.equals("")) {
				sql += " AND dt.ID=" + value;
			}


			List files = new ArrayList();
			
			//List downs = down.searchAndRetrieveList();
			connection = DbConnectionManager.getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			Download down;

			while (rs.next()) {
				down = new Download();
				
				if (log.isDebugEnabled()) log.debug("start ");
				
				down.setId(rs.getInt("dl.ID"));
				if (log.isDebugEnabled()) log.debug("start ");

				down.setTypename(rs.getString("dt.Name"));
				if (log.isDebugEnabled()) log.debug("start ");

				down.setName(rs.getString("dl.Name"));
				if (log.isDebugEnabled()) log.debug("start ");

				down.setDate(rs.getString("dl.Date"));
				if (log.isDebugEnabled()) log.debug("start ");

				down.setDownloadpath(rs.getString("dl.DownloadPath"));
				down.setImagepath(rs.getString("dl.ImagePath"));
				/*
				DownloadFile = new File(uploadPath + value);
				if (!DownloadFile.exists())
					throw new IOException("Not File for " + uploadPath + value);
				
				fileBit = DownloadFile.length();
				fileK = fileBit / 1024;
				fileM = fileK / 1024;
				 */
				down.setFilelength(df.format(fileM) + "M");
				files.add(down);
			}
			
			request.setAttribute("files", files);
		} catch (SQLException e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return "success";
	}

	public String promptUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processUpload");
			    
			    	String value ="";
			    	String uploadDirectory = "upload/";
			    	String uploadPath = request.getRealPath("/")+uploadDirectory;
			    	
				    File uploadFile = new File(uploadPath);
				       if (!uploadFile.exists()) {
				           uploadFile.mkdirs();
				       }
				       
			    	UploadFile uf = new UploadFile();
			    	uf.setRequest(request);
			    	uf.setUploadPath(uploadPath);
			    	uf.process();

			    	value = uf.getParameter("name");
			    	
			    	if (value==null || value.equals("")) 
			    		throw new Exception("Input Name NULL");
			    	
					DBSession session = DBSession.getInstance();

			    	Download down = new Download();
			    	down.setId( new Integer(IDGenerator.getNextID("Download")));
			    	down.setTypeid(new Integer(uf.getParameter("typeid").trim()));
			    	down.setName(uf.getParameter("name").trim());
			    	down.setDate(uf.getParameter("date").trim());
			    	down.setDescription(uf.getParameter("description").trim());
			    	
			    	Iterator fileList = uf.getFileIterator();
			    	int i=0;
			    	while(fileList.hasNext()){
			    		if(i==0)
			    			down.setDownloadpath((String)fileList.next());
			    		else
			    			down.setImagepath((String)fileList.next());
			    		i++;
			    		}
			    	
			    	session.save(down);
			    	
	           System.out.print("upload succeed");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
			return "promptUpload";
		}
		return "success";
	}

	public String processDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processDownload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
}
