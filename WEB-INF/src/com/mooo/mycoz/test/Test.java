package com.mooo.mycoz.test;

import com.mooo.mycoz.util.Transaction;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileNode;

public class Test {

	public static void main(String[] args) {

		long maxLong = 9223372036854775807L;
		int maxInt = 2147483647;

		Transaction tx = new Transaction();

		try {
			tx.start();
			
			DbProcess dbAction = DbFactory.getInstance();
			
			//FileTree fileTree = new FileTree();
			
//			FileNode rootNode = fileTree.getRoot();
//			System.out.println("max======="+fileTree.getMax());
//
//			fileTree.addChild(rootNode, new FileNode(1,"root1") );
//			System.out.println("max======="+fileTree.getMax());
//
//			fileTree.addChild(rootNode, new FileNode(2,"root2") );
//			System.out.println("max======="+fileTree.getMax());
//			
//			fileTree.addChild(fileTree.getTreeNode(2), new FileNode(20,"root2") );
//			fileTree.addChild(fileTree.getTreeNode(2), new FileNode(21,"root2") );
//			fileTree.addChild(fileTree.getTreeNode(2), new FileNode(22,"root2") );
//			fileTree.addChild(fileTree.getTreeNode(2), new FileNode(23,"root2") );
//
//			fileTree.addChild(rootNode, new FileNode(3,"root3") );
//			System.out.println("max======="+fileTree.getMax());
//
//			fileTree.addChild(rootNode, new FileNode(4,"root4") );
//			System.out.println("max======="+fileTree.getMax());
//
//			System.out.println("ChildCount======="+fileTree.getRoot().getChildCount());
//			for (int i = 1; i < fileTree.getRoot().getChildCount()+1;i++) {
//				FileNode fn = fileTree.getTreeNode(i);
//				System.out.println("name:" + fn.getData());
//			}
//			
//			fileTree.removeChild(rootNode, fileTree.getTreeNode(1));
			
			
			//System.out.println("ChildCount======="+fileTree.getTreeNode(2).getChildCount());

			//System.out.println("ChildCount======="+fileTree.getRoot().getChildCount());
			
			// add Folder
			FileInfo fi = new FileInfo();
			fi.setFolder("Y");
			fi.setId(IDGenerator.getNextID(tx.getConnection(),"FileInfo"));
			fi.setName("醉拳");
			dbAction.add(tx.getConnection(),fi);

			//dbAction.searchAndRetrieveList(fi);
			dbAction.retrieve(tx.getConnection(),fi);

			FileNode fNode = new FileNode();
			//System.out.println("setChildId======="+fi.getId());

			fNode.setId(IDGenerator.getNextID(tx.getConnection(),"FileNode"));
			System.out.println("FileNode Id======="+IDGenerator.getNextID(tx.getConnection(),"FileNode"));
			System.out.println("FileNode setChildId======="+fi.getId());

			fNode.setParentId(9);
			fNode.setChildId(fi.getId());
			dbAction.add(tx.getConnection(),fNode);
			
			tx.commit();

//			fNode.setId(IDGenerator.getNextID("FileNode").intValue());
//			fNode.setParentId(0);
//			fNode.setChildId(fi.getId());
//			dbAction.add(fNode);

			//dbAction.searchAndRetrieveList(fNode);

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.end();
		}
	}
}