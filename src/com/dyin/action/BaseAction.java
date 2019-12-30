package com.dyin.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

public abstract class BaseAction {
	protected Integer id;
	protected Integer curpage;
	protected String cururl;
	protected File file;
	protected String fileFileName;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurpage() {
		return curpage == null || curpage <= 0 ? 1 : curpage;
	}
	public void setCurpage(Integer curpage) {
		this.curpage = curpage;
	}
	public String getCururl() {
		return cururl;
	}
	public void setCururl(String cururl) {
		this.cururl = cururl;
	}

	public boolean isPost() {
		return "POST".equalsIgnoreCase(ServletActionContext.getRequest().getMethod());
	}
	
	public void respJson(String json) {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			pw.print(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void jsAlert(String msg, String url) {
		respJson("<script>alert('" + msg + "');window.location.href='" + url + "'</script>");
	}
	
	public void outRespJson(Object obj) {
		String json = obj instanceof String ? (String) obj : new Gson().toJson(obj);
		respJson(json);
	}
	
}
