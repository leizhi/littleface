package com.mooo.mycoz.action.operation;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;
import com.mooo.mycoz.util.FileUtil;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.UploadFile;

public class FileAction extends BaseSupport {
	private static Log log = LogFactory.getLog(FileAction.class);

	public String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("list");

			long fileBit = 0;
			double fileK = 0.0;
			double fileM = 0.0;
			String uploadPath = request.getRealPath("/")+"upload/";
			
			String value = null;

			DecimalFormat df = new DecimalFormat("###0.00");

			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable(CodeType.class, "ct");
			mdb.addTable(LinearCode.class, "lc");
			mdb.addTable(FileInfo.class, "fi");

			mdb.setForeignKey("ct", "id", "lc", "typeid");
			mdb.setForeignKey("fi", "typeId", "lc", "id");
			mdb.setField("ct.id", "1");
			
			mdb.setRetrieveField("fi", "id");
			mdb.setRetrieveField("fi", "name");
			mdb.setRetrieveField("fi", "datetime");
			mdb.setRetrieveField("fi", "filePath");
			mdb.setRetrieveField("fi", "id");
			mdb.setRetrieveField("lc", "name");

			value = request.getParameter("Key");
			
			if (value != null && !value.equals("")) {
				mdb.setField("ct.id", "1");
			}

			List retrives = mdb.searchAndRetrieveList();
			
			List files = new ArrayList();
			FileInfo fi;
			File checkFile;
			
			for (Iterator it = retrives.iterator(); it.hasNext();) {
				Map map = (Map)it.next();
				//CodeType ct = (CodeType)map.get("ct");
				LinearCode lc = (LinearCode)map.get("lc");
				fi = (FileInfo)map.get("fi");
				fi.setTypename(lc.getName());

				value = fi.getFilepath();

				checkFile = new File(uploadPath + value);
				if (log.isDebugEnabled()) log.debug("checkFile = "+(checkFile.exists()));

				if (!checkFile.exists()){ 
					/*
					Blob fileBlob = rs.getBlob("fi.file");
					InputStream in = fileBlob.getBinaryStream();
					FileOutputStream out = new FileOutputStream(checkFile);
					int len=(int)fileBlob.length();
				     byte[] buffer=new byte[len];  // 建立缓冲区
				     while((len=in.read(buffer)) != -1){
				    	 out.write(buffer,0,len);
				     }
				     out.close();      
				     in.close();
				     */
				}
				fileBit = checkFile.length();
				fileK = fileBit / 1024;
				fileM = fileK / 1024;
				fi.setSize(df.format(fileM) + "M");
				
				files.add(fi);
			}
			request.setAttribute("files", files);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String promptUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");

			Map fileTypes = new HashMap();
			
			LinearCode lc = new LinearCode();
			lc.setTypeId(1);
			LinearCode bean;
			List cts = dbProcess.searchAndRetrieveList(lc);

			for(Iterator it = cts.iterator(); it.hasNext();){
				bean = (LinearCode)it.next();
				fileTypes.put(bean.getId(), bean.getName());
			}
			
			request.setAttribute("fileTypes", fileTypes );

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("processUpload");

			String value = "";
			String uploadDirectory = "upload/";
			String tmpDirectory = "tmp/";
			String uploadPath = request.getRealPath("/") + uploadDirectory;
			String tmpPath = request.getRealPath("/") + tmpDirectory;

			if (log.isDebugEnabled())
				log.debug("uploadPath=" + uploadPath);

			File tmpFile = new File(tmpPath);
			File uploadFile = new File(uploadPath);

			if (!tmpFile.exists()) {
				tmpFile.mkdirs();
			}

			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}

			UploadFile uf = new UploadFile();
			uf.setRequest(request);
			uf.setUploadPath(tmpPath);
			uf.process();

			value = uf.getParameter("name").trim();

			if (log.isDebugEnabled())
				log.debug("name:" + value);

			if (value == null || value.equals(""))
				throw new Exception("Input Name NULL");

			// Finally, delete the forum itself and all permissions and
			// properties
			// associated with it.
			FileInfo fi = new FileInfo();
			fi.setId(new Integer(IDGenerator.getNextID("FileInfo").intValue()));
			fi.setTypeid(new Integer(uf.getParameter("typeid").trim()));
			fi.setName(uf.getParameter("name").trim());
			// fi.setDatetime(new Date(uf.getParameter("date").trim()));
			fi.setDatetime(new Date());

			Iterator<?> fileList = uf.getFileIterator();
			int i = 0;

			while (fileList.hasNext()) {
				value = (String) fileList.next();
				fi.setFilepath(value);
				i++;
			}

			dbProcess.add(fi);

			FileUtil.copy(new File(tmpPath + value),
					new File(uploadPath + value), true);

			// User perms
			// pstmt = con.prepareStatement(DELETE_FORUM_USER_PERMS);
			// pstmt.setInt(1,forum.getID());
			// pstmt.execute();
			// pstmt.close();

			System.out.print("upload succeed");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
			return "promptUpload";
		}
		return "list";
	}

	public String processDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processDeleteCode");
	    	String uploadDirectory = "upload/";
	    	String uploadPath = request.getRealPath("/")+uploadDirectory;
	    	
			String[] ids =  request.getParameterValues("id");
			
			for(int i=0;i<ids.length;i++){
				if (log.isDebugEnabled()) log.debug("ids="+ids[i]);
				FileInfo bean = new FileInfo();
				bean.setId( new Integer(ids[i]));
				dbProcess.retrieve(bean);
				
		    	File file = new File(uploadPath+bean.getFilepath());
				if (log.isDebugEnabled()) log.debug("filePath="+uploadPath+bean.getFilepath());

		    	if(file.exists())
		    		file.delete();
		    	
		    	dbProcess.delete(bean);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "list";
	}

	public String processDownload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processDeleteCode");
	    	String uploadDirectory = "upload/";
	    	String uploadPath = request.getRealPath("/")+uploadDirectory;
	    	
			String[] ids =  request.getParameterValues("id");
			
			for(int i=0;i<ids.length;i++){
				if (log.isDebugEnabled()) log.debug("ids="+ids[i]);
				FileInfo bean = new FileInfo();
				bean.setId( new Integer(ids[i]));
				dbProcess.retrieve(bean);
				
		    	File file = new File(uploadPath+bean.getFilepath());
		    	//response.setContentType("*.*");
		    	
				if (log.isDebugEnabled()) log.debug("filePath="+uploadPath+bean.getFilepath());
			}

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}

	try {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileName = (String)request.getAttribute("fileName");

		String dDirectory = request.getRealPath("/")+"/upload/";
		File downfile = new File(dDirectory+fileName);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		
		FileInputStream inStream = new FileInputStream(downfile);

		System.out.println("filename="+fileName);
		// 解决编码
		String agent = request.getHeader("User-Agent");
		String info = agent.toUpperCase();
		if ((info.indexOf("MSIE")) > -1) {
			System.out.println("ie");
			fileName = java.net.URLEncoder.encode(new String(fileName.getBytes(),"UTF-8"));
			} else {/*   
    			if (info.indexOf("NAVIGATOR") < 0 && info.indexOf("FIREFOX") > -1) {   
    			} else if ((info.indexOf("OPERA")) > -1) {   
    			} else if (info.indexOf("CHROME") < 0  && info.indexOf("SAFARI") > -1) {   
    			} else if (info.indexOf("CHROME") > -1) {   
    			} else if (info.indexOf("NAVIGATOR") > -1) {   
    			} else {   
    			}*/
				System.out.println("firefxo");
    			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			}
		response.reset();
		response.setContentType("application/x-msdownload;charset=utf-8");
		response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		
		OutputStream outputStream =response.getOutputStream();
		
		while ((len = inStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		
		inStream.close();

		outputStream.flush();
		outputStream.close();
		outputStream=null;
		response.flushBuffer();
		out.clear();
		out = pageContext.pushBody();
		
	} catch (Exception ex) {
		ex.printStackTrace();
		throw new ServletException(ex);
	}
		return "list";
	}
}
