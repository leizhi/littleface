package com.mooo.mycoz.util;

import java.util.Iterator;

public class MultipleTree {
	private TreeNode root;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public MultipleTree() {
		root = new MultipleTreeNode();
	}

	public void addChild(TreeNode treeNode, TreeNode child) {
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

	public void removeChild(TreeNode treeNode, TreeNode child) {
		clearAll(child);
		treeNode.removeChild(child);
	}

	/* first checkNode(root,checkNode) */
	public boolean inTreeNode(TreeNode treeNode, TreeNode checkNode) {

		if (treeNode == null || checkNode == null)
			return false;

		if (treeNode.getId() == checkNode.getId())
			return true;

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();
			while (iterator.hasNext()) {
				boolean isHave = inTreeNode((TreeNode) iterator.next(),
						checkNode);
				if (isHave)
					return true;

			}
		}

		return false;

	}

	public void clearAll(TreeNode treeNode) {
		if (inTreeNode(root, treeNode)) {

			if (treeNode.getChildCount() > 0) {
				Iterator<?> iterator = treeNode.getChildList().iterator();
				while (iterator.hasNext()) {
					clearAll((TreeNode) iterator.next());
				}
			}
			treeNode.remove();
		}
	}

	public int getMax(TreeNode treeNode) {

		count += treeNode.getChildCount();

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();
			while (iterator.hasNext()) {
				getMax((TreeNode) iterator.next());
			}
		}
		return count;
	}

	public int getMax() {
		setCount(0);
		return getMax(root);
	}

	public TreeNode getTreeNode(TreeNode treeNode, int index) {

		if (treeNode.getId() == index && index != 0)
			return treeNode;

		if (treeNode.getChildCount() > 0) {
			Iterator<?> iterator = treeNode.getChildList().iterator();

			while (iterator.hasNext()) {
				TreeNode tn = getTreeNode((TreeNode) iterator.next(), index);

				if (tn != null)
					return tn;
			}
		}

		return null;
	}

	public TreeNode getTreeNode(int index) {
		return getTreeNode(root, index);
	}

}
