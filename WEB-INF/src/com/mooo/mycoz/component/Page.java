package com.mooo.mycoz.component;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.util.ParamUtil;

public class Page {

	private static Log log = LogFactory.getLog(Page.class);
	
	private Integer totalRows;
	private Integer pageSize;
	private Integer currentPage;
	private Integer totalPages;
	private Integer offset;

	private String forward;
	
	public Page() {
		totalRows = 0;
		pageSize = 25;
		currentPage = 0;
		totalPages = 0;
		offset = 1;
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
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public void buildComponent(HttpServletRequest request,Integer totalRows) {
		ParamUtil.bindData(request, this, "page");

		// handling pagination
		this.totalRows = totalRows;
		
		totalPages = totalRows / pageSize;

		if ((totalRows % pageSize) > 0) {
			totalPages++;
		}

		if (!(currentPage > 0))
			currentPage = 1;
		if (!(currentPage < totalPages))
			currentPage = totalPages;

		offset = (currentPage - 1) * pageSize;

		// 获取当前执行的方法，首页，前一页，后一页，尾页.
		System.out.println("forward=>"+forward);
		if (forward != null) {
			if (forward.equals("first")) {
				first();
			} else if (forward.equals("previous")) {
				previous();
			} else if (forward.equals("next")) {
				next();
			} else if (forward.equals("last")) {
				last();
			} else if (forward.equals("jump")) {
				jump();
			}
		}
		
		request.setAttribute("page", this);

		// end if
	}
	
	public void first() {
		currentPage = 1;
		offset = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		offset = (currentPage - 1) * pageSize;
	}

	public void next() {

		if (currentPage < totalPages) {
			currentPage++;
		}
		offset = (currentPage - 1) * pageSize;
		
		System.out.println("currentPage=>"+currentPage);
		System.out.println("totalPages=>"+totalPages);
		System.out.println("pageSize=>"+pageSize);
		System.out.println("offset=>"+offset);

	}

	public void last() {
		if (log.isDebugEnabled()) log.debug(currentPage);
		if (log.isDebugEnabled()) log.debug(totalPages);
		currentPage = totalPages;
		offset = (currentPage - 1) * pageSize;
	}

	public void jump() {
		if (currentPage < totalPages + 1 && currentPage > 0)
			offset = (currentPage - 1) * pageSize;
	}

}
