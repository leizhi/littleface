package com.mooo.mycoz.util;

import java.util.List;

public interface TreeNode {

	public Integer getParentId();

	public void setParentId(Integer parentId);

	public TreeNode getParent();

	public void setParent(TreeNode parent);

	public Integer getId();

	public void setId(Integer id);

	public Integer getLevelId();

	public void setLevelId(Integer levelId);

	public Object getObj();

	public void setObj(Object obj);

	public void addChild(TreeNode child);

	public void removeChild(TreeNode child);

	public void remove();

	public Integer getChildCount();

	public List<TreeNode> getChildList();

	public void setChildList(List<TreeNode> childList);

	public boolean haveChild();
}
