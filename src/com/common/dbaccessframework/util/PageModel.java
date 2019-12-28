package com.common.dbaccessframework.util;

import java.util.List;

public class PageModel<E>{

	private int itemCount;
	private int totalPage;
	private int curPage;
	private int pageSize;
	private List<E> retList;
	
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<E> getRetList() {
		return retList;
	}
	public void setRetList(List<E> retList) {
		this.retList = retList;
	}
}
