package com.mooo.mycoz.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import com.mooo.mycoz.util.Transition;

public class PaginationComponent {

	/**
	 * Utility method to set up the log for this object
	 */
	private static Log log = LogFactory.getLog(PaginationComponent.class);

	int total = 0;
	int currentPage = 1;

	public int getCurrentPage() {
		return currentPage;
	}

	int offset = 1;

	public int getOffset() {
		return offset;
	}

	int countPerPage = 25;

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int i) {
		this.countPerPage = i;
	}

	String listState = "";

	public String getListState() {
		return listState;
	}

	public void setListState(String listState) {
		this.listState = listState;
	}

	String listController = "";

	public String getListController() {
		return listController;
	}

	public void setListController(String listController) {
		this.listController = listController;
	}

	HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	HttpServletResponse response;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest buildComponent(HttpServletRequest request,
			String listController, String listState, int total)
			throws ServletException, IOException {

		this.total = total;
		this.listController = listController;
		this.listState = listState;
		// allow to set countPerPage by callers
		// this.countPerPage = XpcConstants.RecordsPerPage;
		if (request.getParameter("CurrentPage") != null)
			currentPage = Integer.parseInt(request.getParameter("CurrentPage"));
		if (request.getParameter("CountPerPage") != null)
			countPerPage = Integer.parseInt(request
					.getParameter("CountPerPage"));

		try {
			// handling pagination
			int totalPage = total / countPerPage;
			if (countPerPage * totalPage < total)
				totalPage++;
			if (currentPage > totalPage)
				currentPage = totalPage;
			if (currentPage < 1)
				currentPage = 1;
			offset = (currentPage - 1) * countPerPage;

			//Input in = new Input();
			//in.addValue(request, "CurrentPage", currentPage);
			//in.addValue(request, "CountPerPage", countPerPage);
			//in.addValue(request, "TotalPage", totalPage);

			//in.addSubmit(request, "Next", listController, listState);
			//in.addSubmit(request, "Previous", listController, listState);
			//in.addSubmit(request, "JumpTo", listController, listState);

			if (log.isDebugEnabled()) {
				log.debug("buildCom class name = " + getClass().getName());
				log.debug("buildCom currentPage = " + currentPage);
				log.debug("buildCom totalPage = " + totalPage);
				log.debug("buildCom offset = " + offset);
			}
			/*
			 * foward state
			 */
			int pageCurrent = 1;

			if ((request.getParameter("FowardName")).equals("Next")) {
				if (request.getParameter("CurrentPage") != null) {
					try {
						pageCurrent = Integer.parseInt(request
								.getParameter("CurrentPage"));
						pageCurrent++;
					} catch (Exception e) {
						pageCurrent = 1;
					}
				}
			} else if ((request.getParameter("FowardName")).equals("Previous")) {
				if (request.getParameter("CurrentPage") != null) {
					try {
						pageCurrent = Integer.parseInt(request
								.getParameter("CurrentPage"));
						pageCurrent--;
					} catch (Exception e) {
						pageCurrent = 1;
					}
				}

				if (pageCurrent < 1)
					pageCurrent = 1;
			}// end if

		} catch (Exception e) {
			throw new ServletException("Exception:" + e.getMessage());
		}
		return request;
	}

}
