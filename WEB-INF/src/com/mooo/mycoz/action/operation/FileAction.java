package com.mooo.mycoz.action.operation;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileTree;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.Transaction;
import com.mooo.mycoz.util.TreeUtil;
import com.mooo.mycoz.util.UploadFile;

public class FileAction extends BaseSupport {
	private static Log log = LogFactory.getLog(FileAction.class);

	public String delete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processDeleteCode");
	    	String uploadDirectory = "upload/";
	    	String uploadPath = request.getSession().getServletContext().getRealPath("/")+uploadDirectory;
	    	
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
		return "success";
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

		return "success";
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

		return "success";
	}
	
	public String ajaxUpload(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("processUpload");

		String value = "";
		String uploadDirectory = "upload/";
		//String tmpDirectory = "tmp/";
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + uploadDirectory;
		//String tmpPath = request.getSession().getServletContext().getRealPath("/") + tmpDirectory;

		if (log.isDebugEnabled()) log.debug("uploadPath=" + uploadPath);

		//File tmpFile = new File(tmpPath);
		File uploadFile = new File(uploadPath);

		//if (!tmpFile.exists()) {
		//	tmpFile.mkdirs();
		//}

		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		HttpSession session = request.getSession();
		session.setAttribute("totalSize",request.getContentLength());
		
		UploadFile uf = new UploadFile();
		uf.setRequest(request);
		uf.setUploadPath(uploadPath);
		uf.process();

		session.removeAttribute("totalSize");

		value = uf.getParameter("name").trim();
		if (log.isDebugEnabled()) log.debug("name:" + value);

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

			//FileUtil.copy(new File(tmpPath + value), new File(uploadPath	+ value), true);

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

		System.out.println("upload succeed");

		return "tree";
	}
	
	public String tree(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("tree start");

		TreeUtil fileTreeNode = new TreeUtil();
		String stringTree = fileTreeNode.buildTree();
		//System.out.println("treeString ====== " + stringTree);
		request.setAttribute("stringTree", stringTree);
		
		System.out.println("tree succeed");

		return "success";
	}
	
	public String callXML(HttpServletRequest request,HttpServletResponse response) {
		try {
			Object obj;
			String fileName = request.getParameter("fileName");
			File tmpfile = new File(request.getSession().getServletContext().getRealPath("/")+"upload/"+fileName);
			System.out.println("tmpName->"+request.getSession().getServletContext().getRealPath("/")+"upload/"+fileName);

			PrintWriter out = response.getWriter();
			
			Long bytesRead = -1L;
			Integer totalSize = 0;

			HttpSession session = request.getSession();
			
			System.out.println("File Object ====== " + session.getAttribute("totalSize"));

			if( (obj = session.getAttribute("totalSize")) != null){
				totalSize = (Integer)obj;
			}
			
			bytesRead = tmpfile.length();

			System.out.println("File Call size ====== " + bytesRead);
			System.out.println("File Call totalSize ====== " + totalSize);

			response.reset();

			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			out.print("<response>");
			out.print("<bytesRead>" + bytesRead + "</bytesRead>");
			out.print("<totalSize>" + totalSize + "</totalSize>");
			out.print("</response>");

			response.flushBuffer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}
}
