package com.manihot.xpc.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Pager {

	private static Log log = LogFactory.getLog(Pager.class);

	private Integer totalRows;
	private Integer pageSize;
	private Integer currentPage;
	private Integer totalPages;
	private Integer startRow;
	private String pagerMethod;

	public void init() {
		totalRows = 0;
		pageSize = 15;
		currentPage = 0;
		totalPages = 0;
		startRow = 0;
		pagerMethod = "";
	}

	public void make() {
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}

		if (!(currentPage > 0))
			currentPage = 1;
		if (!(currentPage < totalPages))
			currentPage = totalPages;

		startRow = 0;

		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (currentPage != 0) {
			refresh(currentPage);
		}
		// 获取当前执行的方法，首页，前一页，后一页，尾页.
		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				first();
			} else if (pagerMethod.equals("previous")) {
				previous();
			} else if (pagerMethod.equals("next")) {
				next();
			} else if (pagerMethod.equals("last")) {
				last();
			} else if (pagerMethod.equals("jump")) {
				jump();
			}
		}
		// end if
	}

	public void make(Integer totalRows) {
		setTotalRows(totalRows);
		make();
	}

	public Pager() {
		init();
	}

	public Pager(Integer totalRows) {
		init();
		setTotalRows(totalRows);
		make();
	}

	public Pager(Integer totalRows, Integer pageSize) {
		init();
		setTotalRows(totalRows);
		setPageSize(pageSize);
		make();
	}

	public Pager(Integer totalRows, Integer pageSize, String pagerMethod) {
		init();
		setTotalRows(totalRows);
		setPageSize(pageSize);
		setPagerMethod(pagerMethod);
		make();
	}

	public Pager(Integer totalRows, Integer pageSize, String pagerMethod,
			Integer currentPage) {
		init();
		setTotalRows(totalRows);
		setPageSize(pageSize);
		setPagerMethod(pagerMethod);
		setCurrentPage(currentPage);
		make();
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	public void next() {
		if (log.isDebugEnabled())
			log.debug(currentPage);
		if (log.isDebugEnabled())
			log.debug(totalPages);

		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		if (log.isDebugEnabled())
			log.debug(currentPage);
		if (log.isDebugEnabled())
			log.debug(totalPages);

		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}

	public void jump() {
		// currentPage = totalPages;
		if (currentPage < totalPages + 1 && currentPage > 0)
			startRow = (currentPage - 1) * pageSize;
	}

	public void refresh(int currentPage) {
		this.currentPage = currentPage;
		if (currentPage > totalPages) {
			last();
		}

	}
}
