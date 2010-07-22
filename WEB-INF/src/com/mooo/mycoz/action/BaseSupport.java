package com.mooo.mycoz.action;

import java.util.List;

import com.mooo.mycoz.commons.Pager;

/**
 * Base Action class for the controller package.
 */

public class BaseSupport {
	private static final long serialVersionUID = 1L;

	// public Authentication auth;
	
	public BaseSupport() {
		
		pager = new Pager();
		
		authState();

		//this.setLayout("/xpc-wd/skin/office/default");
	}

	/*
	 * configure general action
	 */
	public Pager pager;
	public List<?> generalItems;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<?> getGeneralItems() {
		return generalItems;
	}

	public void setGeneralItems(List<?> generalItems) {
		this.generalItems = generalItems;
	}

	/*
	 * configure state
	 */

	public Boolean list;
	public Boolean promptAdd;
	public Boolean processAdd;
	public Boolean promptUpdate;
	public Boolean processUpdate;
	public Boolean processDelete;
	public Boolean preview;

	public Boolean getList() {
		return list;
	}

	public void setList(Boolean list) {
		this.list = list;
	}

	public Boolean getPromptAdd() {
		return promptAdd;
	}

	public void setPromptAdd(Boolean promptAdd) {
		this.promptAdd = promptAdd;
	}

	public Boolean getProcessAdd() {
		return processAdd;
	}

	public void setProcessAdd(Boolean processAdd) {
		this.processAdd = processAdd;
	}

	public Boolean getPromptUpdate() {
		return promptUpdate;
	}

	public void setPromptUpdate(Boolean promptUpdate) {
		this.promptUpdate = promptUpdate;
	}

	public Boolean getProcessUpdate() {
		return processUpdate;
	}

	public void setProcessUpdate(Boolean processUpdate) {
		this.processUpdate = processUpdate;
	}

	public Boolean getProcessDelete() {
		return processDelete;
	}

	public void setProcessDelete(Boolean processDelete) {
		this.processDelete = processDelete;
	}

	public Boolean getPreview() {
		return preview;
	}

	public void setPreview(Boolean preview) {
		this.preview = preview;
	}

	public void authState() {
		setList(true);
		setPromptAdd(true);
		setProcessAdd(true);
		setPromptUpdate(true);
		setProcessUpdate(true);
		setProcessDelete(true);
		setPreview(true);
	}

	public String layout;

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
}
