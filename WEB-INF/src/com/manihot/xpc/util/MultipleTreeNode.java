package com.manihot.xpc.util;

import java.util.ArrayList;
import java.util.List;

public class MultipleTreeNode implements TreeNode {
	private TreeNode parent;
	private List<TreeNode> childList;

	private Integer id;
	private Integer parentId;
	private Integer levelId;
	private Object obj;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public MultipleTreeNode() {
		parent = null;
		childList = new ArrayList<TreeNode>();

		id = 0;
		parentId = 0;
		levelId = 0;
		obj = null;
	}

	public MultipleTreeNode(Object obj) {
		this.obj = obj;
		childList = new ArrayList<TreeNode>();
	}

	public MultipleTreeNode(Integer id, Object obj) {
		this.id = id;
		this.obj = obj;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
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

		id = null;
		levelId = null;
		obj = null;
	}

	public Integer getChildCount() {
		return childList.size();
	}

	public List<TreeNode> getChildList() {
		return childList;
	}

	public void setChildList(List<TreeNode> childList) {
		this.childList = childList;
	}

	public boolean haveChild() {
		if (getChildCount() > 0)
			return true;
		else
			return false;
	}

}
