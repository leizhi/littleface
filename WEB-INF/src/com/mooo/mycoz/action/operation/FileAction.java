package com.mooo.mycoz.action.operation;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.Download;

public class FileAction extends BaseSupport {
	private static Log log = LogFactory.getLog(FileAction.class);

	public String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			long fileBit = 0;
			double fileK = 0.0;
			double fileM = 0.0;
			String uploadPath = "/home/xpc/webapps";
			String value = null;
			String sql = null;
			File DownloadFile = null;

			DecimalFormat df = new DecimalFormat("###0.00");

			Download down = new Download();
			ResultSet rs = null;

			sql = "SELECT dl.ID,dt.Name,dl.Name,dl.Date,dl.Description,dl.DownloadPath,dl.ImagePath FROM Download AS dl,mycozShared.DownloadType AS dt WHERE dl.TypeID=dt.ID";

			value = request.getParameter("Key");
			if (value != null && !value.equals("")) {
				sql += " AND dt.ID=" + value;
			}

			rs = down.getResultSet(sql);

			List files = new ArrayList();

			while (rs.next()) {
				down.setId(rs.getInt("dl.ID"));
				down.setTypename(rs.getString("dt.Name"));
				down.setName(rs.getString("dl.Name"));
				down.setDate(rs.getString("dl.Date"));
				down.setDownloadpath(rs.getString("dl.DownloadPath"));

				DownloadFile = new File(uploadPath + value);
				if (!DownloadFile.exists())
					throw new IOException("Not File for " + uploadPath + value);

				fileBit = DownloadFile.length();
				fileK = fileBit / 1024;
				fileM = fileK / 1024;

				down.setFilelength(df.format(fileM) + "M");
				files.add(down);
			}
			request.setAttribute("files", files);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String promptUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processDownload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("list");

			request.setAttribute("name", request.getParameter("name"));

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
}
