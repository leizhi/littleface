package com.mooo.mycoz.action.operation;

import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.dbaccess.DBStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileTree;
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;
import com.mooo.mycoz.util.FileUtil;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.Transaction;
import com.mooo.mycoz.util.TreeUtil;
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
			FileInfo fileInfo = new FileInfo();
			List retrives = dbProcess.searchAndRetrieveList(fileInfo);
			
			List files = new ArrayList();
			FileInfo fi;
			File checkFile;
			
			for (Iterator it = retrives.iterator(); it.hasNext();) {
				fi = (FileInfo)it.next();
				
				value = fi.getFilepath();

				checkFile = new File(uploadPath + value);
				if (log.isDebugEnabled()) log.debug("checkFile = "+(checkFile.exists()));

				if (!checkFile.exists()){ 
					
				}
				
				fileBit = checkFile.length();
				fileK = fileBit / 1024;
				fileM = fileK / 1024;
				fi.setSize(df.format(fileM) + "M");
				
				files.add(fi);
			}
/*
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
					
//					Blob fileBlob = rs.getBlob("fi.file");
//					InputStream in = fileBlob.getBinaryStream();
//					FileOutputStream out = new FileOutputStream(checkFile);
//					int len=(int)fileBlob.length();
//				     byte[] buffer=new byte[len];  // 建立缓冲区
//				     while((len=in.read(buffer)) != -1){
//				    	 out.write(buffer,0,len);
//				     }
//				     out.close();      
//				     in.close();
				     
				}
				fileBit = checkFile.length();
				fileK = fileBit / 1024;
				fileM = fileK / 1024;
				fi.setSize(df.format(fileM) + "M");
				
				files.add(fi);
			}
			*/
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
			if (log.isDebugEnabled()) log.debug("processUpload");

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

			FileUtil.copy(new File(tmpPath + value),new File(uploadPath + value), true);

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

	public String download(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("fileName", request.getParameter("fileName"));
		return "download";
	}

	public String retrieve(HttpServletRequest request,HttpServletResponse response) {
		String fileId = request.getParameter("fileId");
		System.out.println("fileId ====== " + request.getParameter("fileId"));

		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(new Integer(fileId));
		try {
			dbProcess.retrieve(fileInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("fileInfo", fileInfo);

		return "retrieve";
	}
	
	public String mkdir(HttpServletRequest request,HttpServletResponse response) {
		String parentId = request.getParameter("parentId");
		System.out.println("fileId ====== " + request.getParameter("parentId"));
		Transaction tx = new Transaction();

		try {
			tx.start();
			
			FileInfo folder = new FileInfo();
		   folder.setId(IDGenerator.getNextID(tx.getConnection(), "FileInfo"));
		   folder.setName(request.getParameter("folderName"));
		   folder.setFolder("Y");
			dbProcess.add(tx.getConnection(),folder);

			FileTree fileTree = new FileTree();
			fileTree.setId(IDGenerator.getNextID(tx.getConnection(), "FileTree"));
			
			if(parentId == null || parentId.equals(""))
				fileTree.setParentId(0);
			else
				fileTree.setParentId(new Integer(parentId));
			
			fileTree.setChildId(folder.getId());

			dbProcess.add(tx.getConnection(),fileTree);

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.end();
		}

		return "tree";
	}
	
	public String upload(HttpServletRequest request, HttpServletResponse response) {
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

		StringUtils.noNull(value);

		// Finally, delete the forum itself and all permissions and
		// properties
		// associated with it.
		String parentId = uf.getParameter("parentId").trim();
		
		System.out.println("parentId ====== " + parentId);

		Transaction tx = new Transaction();

		try {
			tx.start();

			FileInfo fileInfo = new FileInfo();
			fileInfo.setId(IDGenerator.getNextID(tx.getConnection(), "FileInfo"));
			fileInfo.setName(uf.getParameter("name").trim());
			fileInfo.setFolder("N");
			fileInfo.setDatetime(new Date());
			
			Iterator<?> fileList = uf.getFileIterator();
			int i = 0;

			while (fileList.hasNext()) {
				value = (String) fileList.next();
				fileInfo.setFilepath(value);
				i++;
			}
			
			dbProcess.add(tx.getConnection(), fileInfo);
			
			FileTree fileTree = new FileTree();
			fileTree.setId(IDGenerator.getNextID(tx.getConnection(), "FileTree"));
			if(parentId == null || parentId.equals(""))
				fileTree.setParentId(0);
			else
				fileTree.setParentId(Integer.valueOf(parentId.trim()).intValue());
			
			fileTree.setChildId(fileInfo.getId());
			
			if (log.isDebugEnabled())log.debug("parentId ====== " + fileTree.getId());
			if (log.isDebugEnabled())log.debug("parentId ====== " + fileTree.getParentId());
			if (log.isDebugEnabled())log.debug("parentId ====== " + fileTree.getChildId());
			if (log.isDebugEnabled())log.debug("parentId ====== " + fileTree.getLevelId());

			
			dbProcess.add(tx.getConnection(), fileTree);

			FileUtil.copy(new File(tmpPath + value), new File(uploadPath	+ value), true);

			tx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			tx.rollback();

		} finally {
			tx.end();
		}

		System.out.print("upload succeed");

		return "tree";
	}
	
	public String tree(HttpServletRequest request,HttpServletResponse response) {
		TreeUtil fileTreeNode = new TreeUtil();
		String stringTree = fileTreeNode.buildTree();
		//System.out.println("treeString ====== " + stringTree);
		request.setAttribute("stringTree", stringTree);

		return "success";
	}
}
