package com.fsh.collections;

import java.util.List;

public class SearchReply {
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 每页的数量
	 */
	private int pageAmount;
	/**
	 * 总页数
	 */
	private int totalPage;
	private List<Student> result;
	/**
	 * 当前页数
	 */
	public int getPage() {
		return page;
	}
	/**
	 * 当前页数
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 每页的数量
	 */
	public int getPageAmount() {
		return pageAmount;
	}
	/**
	 * 每页的数量
	 */
	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}
	/**
	 * 总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 总页数
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<Student> getResult() {
		return result;
	}
	
	public void setResult(List<Student> result) {
		this.result = result;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[page=" + page+",");
		sb.append(" pageAmount=" + pageAmount+",");
		sb.append(" totalPage=" + totalPage+",");
		sb.append(" [");
		for (Student student : result) {
			sb.append("{id:"+student.getId()+",");
			sb.append("name:"+student.getName()+",");
			sb.append("SimplePY:"+student.getSimplePY()+"},");
		}
		sb.append("]]");
		return sb.toString();
	}
	public SearchReply(int page, int pageAmount, int totalPage, List<Student> result) {
		this.page = page;
		this.pageAmount = pageAmount;
		this.totalPage = totalPage;
		this.result = result;
	}
	
}
