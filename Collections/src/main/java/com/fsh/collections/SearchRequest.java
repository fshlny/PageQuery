package com.fsh.collections;

public class SearchRequest {
	private String key;
	/**
	 * 请求页数
	 */
	private int page;
	/**
	 * 每页的数量
	 */
	private int pageAmount;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 请求页数
	 */
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 每页的数量
	 */
	public int getPageAmount() {
		return pageAmount;
	}
	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}
	@Override
	public String toString() {
		return "SearchRequest [key=" + key + ", page=" + page + ", pageAmount=" + pageAmount + "]";
	}
	public SearchRequest(String key, int page, int pageAmount) {
		this.key = key;
		this.page = page;
		this.pageAmount = pageAmount;
	}
	
	
}
