package com.common.dbaccessframework.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

/**
 * Servlet封装类...
 * @author eclipse
 *
 */
@SuppressWarnings("serial")
public abstract class BaseHttpServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String base = req.getContextPath();
		String uri = req.getRequestURI();
		if (uri.contains(".")) {
			uri = uri.split("\\.")[0];
		}
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		String m = uri.substring(uri.indexOf("_") + 1);
		Method method = null;
		try {
			method = getClass().getMethod(m, HttpServletRequest.class, HttpServletResponse.class);			
		} catch (Exception e) {
			throw new RuntimeException("Servlet封装类: \"" + m + "\"方法，不存在", e);
		}
		try {
			String type = "f";
			String path = (String) method.invoke(this, req, resp);
			if (path != null && !"".equals(path = path.trim())) {
				int idx = path.indexOf(":");
				if (idx >= 0) {
					type = path.substring(0, idx);
					path = path.substring(idx + 1);
				}
				if ("f".equals(type)) {
					req.getRequestDispatcher(path).forward(req, resp);
				} else {
					resp.sendRedirect(base + path);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Servlet封装类: \"" + m + "\"方法执行失败", e);
		}
	}
	
	public String forward(String url) {
		return ("f:/" + url).replace("//", "/");
	}
	
	public String redirect(String url) {
		return ("r:/" + url).replace("//", "/");
	}
	
	public boolean isPost(HttpServletRequest req) {
		return "POST".equalsIgnoreCase(req.getMethod());
	}
	
	public void respJson(String json, HttpServletResponse resp) {
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
	
	public void jsAlert(String msg, String url, HttpServletResponse resp) {
		respJson("<script>alert('" + msg + "');window.location.href='" + url + "'</script>", resp);
	}
	
	public void outRespJson(Object obj, HttpServletResponse resp) {
		String json = obj instanceof String ? (String) obj : new Gson().toJson(obj);
		respJson(json, resp);
	}
	
	public String copyUpload(HttpServletRequest req, String CDdirPath) {
		return copyUpload(req, CDdirPath, "file");
	}
	
	public String copyUpload(HttpServletRequest req, String CDdirPath, String col) {
		String CD = CDdirPath.split(":")[0] + ":";
		String dirPath = CDdirPath.split(":")[1];
		String filePath = null;
		try {
			Part part = req.getPart(col);
			filePath = dirPath + "/" + System.currentTimeMillis() + part.getSubmittedFileName();
			part.write(CD + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	
	protected <T> T getParameter(HttpServletRequest req, Class<T> clazz) {
		Map<String, String[]> map = req.getParameterMap();
		Map<String, String> ret = new HashMap<String, String>();
		Set<String> set = map.keySet();
		for (String key : set) {
			String[] vals = (String[]) map.get(key);
			String value = "";
			for (String val : vals) {
				if (!"".equals(value)) {
					value += ",";
				}
				value += val;
			}
			ret.put(key, value);
		}
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(ret), clazz);
	}
	
	protected Integer getId(HttpServletRequest req) {
		String id = req.getParameter("id");
		return id == null ? null : Integer.parseInt(id);
	}

	protected Integer getCurpage(HttpServletRequest req) {
		String curpage = req.getParameter("curpage");
		int cpage = curpage == null ? 1 : Integer.parseInt(curpage);
		if (cpage <= 0) {
			cpage = 1;
		}
		req.setAttribute("curpage", cpage);
		return cpage;
	}
	
}
