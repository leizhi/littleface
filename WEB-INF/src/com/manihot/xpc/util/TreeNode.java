package com.manihot.xpc.util;

import java.util.List;

public interface TreeNode {

	void addChild(TreeNode child);

	void removeChild(TreeNode child);

	Integer getChildCount();

	List<TreeNode> getChildList();

	void setParent(TreeNode parent);

	void setParentId(Integer parentId);

	void setId(Integer id);

	Integer getId();

	Integer getParentId();

	Integer getLevelId();

	void setLevelId(Integer levelId);

	void remove();
}
