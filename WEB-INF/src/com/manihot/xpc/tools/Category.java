package com.manihot.xpc.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
	private static Map<Long, Category> catMap = new HashMap<Long, Category>();
	static {
		new Category(1, "省总公司", new Category(2, "地市1分公司", new Category(3,
				"桥东区办事处", new Category(4, "市场组"), new Category(7, "客服组"),
				new Category(8, "技术组")), new Category(9, "开发区办事处",
				new Category(10, "市场组"), new Category(11, "客服组"), new Category(
						12, "技术组"), new Category(13, "后勤组"))), new Category(14,
				"地市2分公司", new Category(15, "软件开发组"), new Category(16, "软件测试组"),
				new Category(17, "售后服务组"), new Category(18, "市场拓展组"),
				new Category(19, "财物组")));
	}

	public static Category getById(long id) {
		return catMap.get(id);
	}

	private long id;
	private String name;
	private List<Category> children;
	private boolean toggle;

	public Category(long id, String name, Category... children) {
		this.id = id;
		this.name = name;
		this.children = new ArrayList<Category>();
		for (Category child : children) {
			this.children.add(child);
		}
		catMap.put(id, this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public void toggle() {
		toggle = !toggle;
	}

	public boolean isToggle() {
		return toggle;
	}
}