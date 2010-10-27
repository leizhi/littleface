package com.mooo.mycoz.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private TreeNode parent;
	private List<TreeNode> childList;
	
	public TreeNode(){
		parent = null;
		childList = new ArrayList<TreeNode>();
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildList() {
		return childList;
	}

	public void setChildList(List<TreeNode> childList) {
		this.childList = childList;
	}

	public void addChild(TreeNode child) {
		childList.add(child);
	}

	public void removeChild(TreeNode child) {
		childList.remove(child);
	}
	
	public void remove() {
		parent = null;
		childList.clear();
		childList = null;
	}

	public Integer getChildCount() {
		return childList.size();
	}

	public boolean haveChild() {
		if (getChildCount() > 0)
			return true;
		else
			return false;
	}
}
