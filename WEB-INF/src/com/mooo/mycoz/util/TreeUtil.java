package com.mooo.mycoz.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileTree;

public class TreeUtil {
	private static Log log = LogFactory.getLog(TreeUtil.class);

//	private FileInfo root;
//	private int count;
//	
//	public FileInfo getRoot() {
//		return root;
//	}
//	
//	public void setRoot(FileInfo root) {
//		this.root = root;
//	}
//	
//	public int getCount() {
//		return count;
//	}
//	
//	public void setCount(int count) {
//		this.count = count;
//	}
//	////////////////////////
//	public FileTree() {
//		count = 0;
//		root = new FileInfo();
//		
//	}
//	/* first checkNode(root,checkNode) */
//	public boolean inTreeNode(FileInfo treeNode, FileInfo checkNode) {
//
//		if (treeNode == null || checkNode == null)
//			return false;
//
//		if (treeNode.getId() == checkNode.getId())
//			return true;
//
//		if (treeNode.getChildCount() > 0) {
//			Iterator<?> iterator = treeNode.getChildList().iterator();
//			while (iterator.hasNext()) {
//				boolean isHave = inTreeNode((FileInfo) iterator.next(),
//						checkNode);
//				if (isHave)
//					return true;
//
//			}
//		}
//
//		return false;
//
//	}
//
//	public int getMax(FileInfo treeNode) {
//
//		count += treeNode.getChildCount();
//
//		if (treeNode.getChildCount() > 0) {
//			Iterator<?> iterator = treeNode.getChildList().iterator();
//			while (iterator.hasNext()) {
//				getMax((FileInfo) iterator.next());
//			}
//		}
//		return count;
//	}
//
//	public int getMax() {
//		setCount(0);
//		return getMax(root);
//	}
//	
//	/////////////////////////////
//	public FileTree() {
//		count = 0;
//		root = new FileInfo();
//	}
//
//	public void addChild(FileInfo treeNode, FileInfo child) {
//		if (inTreeNode(root, treeNode)) {
//			child.setParent(treeNode);
//			child.setId(getMax() + 1);
//			//child.setParentId(treeNode.getId());
//			//child.setLevelId(treeNode.getLevelId() + 1);
//
//			treeNode.addChild(child);
//		} else {
//			child.setParent(root);
//			child.setId(getMax() + 1);
//			//child.setParentId(root.getId());
//			//child.setLevelId(root.getLevelId() + 1);
//
//			root.addChild(child);
//		}
//
//	}
//
//	public void removeChild(FileInfo treeNode, FileInfo child) {
//		clearAll(child);
//		treeNode.removeChild(child);
//	}
//
//	/* first checkNode(root,checkNode) */
//	public boolean inTreeNode(FileInfo treeNode, FileInfo checkNode) {
//
//		if (treeNode == null || checkNode == null)
//			return false;
//
//		if (treeNode.getId() == checkNode.getId())
//			return true;
//
//		if (treeNode.getChildCount() > 0) {
//			Iterator<?> iterator = treeNode.getChildList().iterator();
//			while (iterator.hasNext()) {
//				boolean isHave = inTreeNode((FileInfo) iterator.next(),
//						checkNode);
//				if (isHave)
//					return true;
//
//			}
//		}
//
//		return false;
//
//	}
//
//	public void clearAll(FileInfo treeNode) {
//		if (inTreeNode(root, treeNode)) {
//
//			if (treeNode.getChildCount() > 0) {
//				Iterator<?> iterator = treeNode.getChildList().iterator();
//				while (iterator.hasNext()) {
//					clearAll((FileInfo) iterator.next());
//				}
//			}
//			treeNode.remove();
//		}
//	}
//
//	public int getMax(FileInfo treeNode) {
//
//		count += treeNode.getChildCount();
//
//		if (treeNode.getChildCount() > 0) {
//			Iterator<?> iterator = treeNode.getChildList().iterator();
//			while (iterator.hasNext()) {
//				getMax((FileInfo) iterator.next());
//			}
//		}
//		return count;
//	}
//
//	public int getMax() {
//		setCount(0);
//		return getMax(root);
//	}
//
//	public FileInfo getTreeNode(FileInfo treeNode, int index) {
//
//		if (treeNode.getId() == index && index != 0)
//			return treeNode;
//
//		if (treeNode.getChildCount() > 0) {
//			Iterator<?> iterator = treeNode.getChildList().iterator();
//
//			while (iterator.hasNext()) {
//				FileInfo tn = getTreeNode((FileInfo) iterator.next(), index);
//
//				if (tn != null)
//					return tn;
//			}
//		}
//
//		return null;
//	}
//
//	public FileInfo getTreeNode(int index) {
//		return getTreeNode(root, index);
//	}
	
//	private FileInfo parent;
//	private List<FileInfo> childList;
	
//	public List<FileInfo> getChildList() {
//		childList = new ArrayList<FileInfo>();
//		try {
//			DbProcess dbProcess = DbFactory.getInstance();
//
//			FileTreeNode fileTree = new FileTreeNode();
//			fileTree.setParentId(getId());
//
//			List<Object> retrives = dbProcess.searchAndRetrieveList(fileTree);
//			FileTreeNode ft;
//
//			for (Iterator<Object> it = retrives.iterator(); it.hasNext();) {
//				ft = (FileTreeNode) it.next();
//				FileInfo fi = new FileInfo();
//				fi.setId(ft.getChildId());
//				dbProcess.retrieve(fi);
//
//				if (log.isDebugEnabled()) log.debug("ft id =======" + ft.getId());
//				childList.add(fi);
//			}
//			if (log.isDebugEnabled()) log.debug("Size =======" + retrives.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return childList;
//	}
//	
//	public FileInfo getParent() {
//		parent = new FileInfo();
//
//		try {
//			DbProcess dbProcess = DbFactory.getInstance();
//
//			FileTreeNode fileTree = new FileTreeNode();
//			fileTree.setChildId(getId());
//			dbProcess.retrieve(fileTree);
//
//			parent.setId(fileTree.getParentId());
//			dbProcess.retrieve(parent);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return parent;
//	}
//	
//	public void addChild(FileInfo child) {
//		Transaction tx = new Transaction();
//
//		try {
//			tx.start();
//			DbProcess dbProcess = DbFactory.getInstance();
//			
//			dbProcess.add(child);
//
//			FileTreeNode fileTree = new FileTreeNode();
//			fileTree.setParentId(getId());
//			fileTree.setChildId(child.getId());
//
//			dbProcess.add(fileTree);
//
//			dbProcess.retrieve(child);
//			
//			if(childList != null)
//				childList.add(child);
//			tx.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//		} finally {
//			tx.end();
//		}
//		
//	}
//
//	public void removeChild(FileInfo child) {
//		Transaction tx = new Transaction();
//
//		try {
//			tx.start();
//
//			DbProcess dbProcess = DbFactory.getInstance();
//			
//			FileTreeNode fileTree = new FileTreeNode();
//			fileTree.setParentId(getId());
//			fileTree.setChildId(child.getId());
//			
//			dbProcess.retrieve(fileTree);
//			dbProcess.delete(fileTree);
//
//			dbProcess.delete(child);
//
//			if(childList != null)
//				childList.remove(child);
//			
//			tx.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			tx.rollback();
//		} finally {
//			tx.end();
//		}
//	}
//	
//	public Integer getChildCount() {
//		return childList.size();
//	}
//
//	public boolean haveChild() {
//		if (getChildCount() > 0)
//			return true;
//		else
//			return false;
//	}
	
	////////////search any node
	public List<FileInfo> getChildList(FileInfo fileInfo) {
		List<FileInfo> childList = new ArrayList<FileInfo>();
		try {
			DbProcess dbProcess = DbFactory.getInstance();

			FileTree fileTree = new FileTree();
			fileTree.setParentId(fileInfo.getId());

			List<Object> retrives = dbProcess.searchAndRetrieveList(fileTree);
			FileTree ft;

			for (Iterator<Object> it = retrives.iterator(); it.hasNext();) {
				ft = (FileTree) it.next();
				FileInfo fi = new FileInfo();
				fi.setId(ft.getChildId());
				
				dbProcess.retrieve(fi);

				if (log.isDebugEnabled()) log.debug("ft id =======" + ft.getId());
				childList.add(fi);
			}
			if (log.isDebugEnabled()) log.debug("Size =======" + retrives.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childList;
	}
	
	private StringBuilder treeString;
	
	public String buildTree(){
		treeString = new StringBuilder();
		
		treeString.append("<ul id=\"dhtmlgoodies_tree2\" class=\"dhtmlgoodies_tree\">\n");
		treeString.append("<li  id=\"node0\" noDrag=\"true\" noSiblings=\"true\" noDelete=\"true\" noRename=\"true\" > <a href=\"#\">"+"root"+"</a>\n");
		treeString.append("<ul>\n");
		
		try {
		DbProcess dbProcess = DbFactory.getInstance();
		
		FileTree fileTree = new FileTree();
		fileTree.setParentId(0);
		List<Object> retrives = dbProcess.searchAndRetrieveList(fileTree);

		FileTree ft;
		for(Iterator<Object> it = retrives.iterator(); it.hasNext();){
			ft = (FileTree)it.next();

			FileInfo fi = new FileInfo();
			fi.setId(ft.getChildId());
			dbProcess.retrieve(fi);
			
			buildTree(fi);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		treeString.append("</ul>\n");
		treeString.append("</li>\n");
		treeString.append("</ul>\n");
		
		return treeString.toString();
	}
	
	public void buildTree(FileInfo fileInfo){
		
		String isFolder = fileInfo.getFolder();

		if(isFolder != null && isFolder.equals("Y")){
			treeString.append("<li id=\"node"+fileInfo.getId()+"\"><a href=\"#\" onclick=\"setUpload("+fileInfo.getId()+",'"+fileInfo.getName()+"');\">"
					+fileInfo.getName()+"</a>\n");
			treeString.append("<ul>\n");
		} else {
			treeString.append("<li id=\"node"+fileInfo.getId()+"\""+" noChildren=\"true\" isFolder=\"false\"><a href=\"#\" onclick=\"ajaxRetrieve("+fileInfo.getId()+");\">"
					+fileInfo.getName()+"</a></li>\n");
		}
		
		System.out.println("childs.size() ====== ");

		List<FileInfo> childs  = getChildList(fileInfo);
		if (childs.size() > 0) {
			for (int i = 0; i < childs.size(); i++) {
				buildTree(childs.get(i));
			}
		}
		
		if(isFolder != null && isFolder.equals("Y")){
			treeString.append("</ul>\n");
			treeString.append("</li>\n");
		}
	}
}
