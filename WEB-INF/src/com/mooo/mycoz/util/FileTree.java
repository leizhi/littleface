package com.mooo.mycoz.util;

import java.util.Iterator;

import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileNode;

public class FileTree {
	private FileNode root;
	private int count;
	
	public FileNode getRoot() {
		FileInfo fi = new FileInfo();
		
		return root;
	}
	public void setRoot(FileNode root) {
		this.root = root;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/////////////////////////////
	public FileTree() {
		count = 0;
		root = new FileNode();
	}

	public void addChild(FileNode treeNode, FileNode child) {
		if (inTreeNode(root, treeNode)) {
			child.setParent(treeNode);
			child.setId(getMax() + 1);
			child.setParentId(treeNode.getId());
			child.setLevelId(treeNode.getLevelId() + 1);

			treeNode.addChild(child);
		} else {
			child.setParent(root);
			child.setId(getMax() + 1);
			child.setParentId(root.getId());
			child.setLevelId(root.getLevelId() + 1);

			root.addChild(child);
		}

	}

	public void removeChild(FileNode treeNode, FileNode child) {
		clearAll(child);
		treeNode.removeChild(child);
	}

	/* first checkNode(root,checkNode) */
	public boolean inTreeNode(FileNode treeNode, FileNode checkNode) {

		if (treeNode == null || checkNode == null)
			return false;

		if (treeNode.getId() == checkNode.getId())
			return true;

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();
			while (iterator.hasNext()) {
				boolean isHave = inTreeNode((FileNode) iterator.next(),
						checkNode);
				if (isHave)
					return true;

			}
		}

		return false;

	}

	public void clearAll(FileNode treeNode) {
		if (inTreeNode(root, treeNode)) {

			if (treeNode.getChildCount() > 0) {
				Iterator<?> iterator = treeNode.getChildList().iterator();
				while (iterator.hasNext()) {
					clearAll((FileNode) iterator.next());
				}
			}
			treeNode.remove();
		}
	}

	public int getMax(FileNode treeNode) {

		count += treeNode.getChildCount();

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();
			while (iterator.hasNext()) {
				getMax((FileNode) iterator.next());
			}
		}
		return count;
	}

	public int getMax() {
		setCount(0);
		return getMax(root);
	}

	public FileNode getTreeNode(FileNode treeNode, int index) {

		if (treeNode.getId() == index && index != 0)
			return treeNode;

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();

			while (iterator.hasNext()) {
				FileNode tn = getTreeNode((FileNode) iterator.next(), index);

				if (tn != null)
					return tn;
			}
		}

		return null;
	}

	public FileNode getTreeNode(int index) {
		return getTreeNode(root, index);
	}
	
	
}
