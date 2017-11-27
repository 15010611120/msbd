package com.yxd.util;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class Page {
	private int pageNo = 1;
	private int pageSize = 0;
	private int pageCount = 0;
	private int itemCount = 0;
	private String action = "";
	private String searchKeys = "";
	private int pNo = 1;
	private int pSize = 0;

	private Integer[] arrPageSize = { Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(15), Integer.valueOf(20),
			Integer.valueOf(25), Integer.valueOf(30), Integer.valueOf(50), Integer.valueOf(100), Integer.valueOf(200),
			Integer.valueOf(500) };
	public static final String PAGE_INFO = "pageInfo";

	public Page() {
	}

	public Page(int pageNo, int pageSize, int itemCount, String action, String searchKeys) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.itemCount = itemCount;
		this.pageCount = getAllPage();
		this.action = action;
		this.searchKeys = searchKeys;
	}


	/**
	 * @param mapParam 为页面中的查询条件
	 */
	public Page(Map mapParam) {
		String queryString = mapParam.toString();
		queryString = queryString.replace("{", "");
		queryString = queryString.replace("}", "");
		initQueryString(queryString);
		initPageSize();
		initPageList();
		pageSum();
	}
	
	/**
	 * 
	 * @param actionName reuestMapping值
	 * @param mapParam 页面中的查询条件
	 */
	public Page(String actionName, Map mapParam) {
		String queryString = mapParam.toString();
		queryString = queryString.replace("{", "");
		queryString = queryString.replace("}", "");
		this.action = actionName;
		initQueryString(queryString);
		initPageSize();
		initPageList();
		pageSum();
	}

	/**
	 * 计算分页--每页显示条数
	 */
	public void pageSum() {
		this.pNo = (this.pageNo - 1) * this.pageSize+1;
		this.pSize = this.pageNo * this.pageSize;
	}

	public void initPageSize() {
		if (this.pageSize == 0)
			this.pageSize = 10;
	}

	public void initPageList() {
		if (this.arrPageSize.length == 0)
			this.arrPageSize = new Integer[] { Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(15),
					Integer.valueOf(20), Integer.valueOf(25), Integer.valueOf(30), Integer.valueOf(50),
					Integer.valueOf(100) };
	}

	/**
	 * 初始化查询条件
	 * @param queryString
	 */
	private void initQueryString(String queryString) {
		if (queryString != null) {
			String[] qs = queryString.split(",");
			for (String s : qs) {
				String[] qsSub = s.split("=");
				if ("pageNo".equals(qsSub[0]))
					this.pageNo = Integer.parseInt(qsSub[1]);
				else if ("pageSize".equals(qsSub[0].trim()))
					this.pageSize = Integer.parseInt(qsSub[1].trim());
				else if ("itemCount".equals(qsSub[0].trim()))
					this.itemCount = Integer.parseInt(qsSub[1].trim());
				else if ("pageCount".equals(qsSub[0].trim())) {
					this.pageCount = Integer.parseInt(qsSub[1].trim());
				} else if ((qsSub.length > 1) && (qsSub[1] != null))
					this.searchKeys = (this.searchKeys + "&" + qsSub[0].trim() + "=" + qsSub[1]);
				else
					this.searchKeys = (this.searchKeys + "&" + qsSub[0].trim() + "=");
			}
		}
	}


	
	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getpSize() {
		return pSize;
	}

	public void setpSize(int pSize) {
		this.pSize = pSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
		this.pageCount = getAllPage();
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSearchKeys() {
		return this.searchKeys;
	}

	public void setSearchKeys(String searchKeys) {
		this.searchKeys = searchKeys;
	}

	public void setSearchKeys(String key, Object value) {
		String val = value == null ? "" : value.toString();

		String schKey = "&" + key + "=" + val;
		this.searchKeys += schKey;
	}

	public boolean isFirstLoad() {
		if ((this.pageNo == 1) && (this.itemCount == 0)) {
			this.searchKeys = "";
			return true;
		}
		return false;
	}

	public boolean isFirst() {
		if ((this.pageNo == 1) || (this.itemCount == 0)) {
			return true;
		}
		return false;
	}

	public boolean isEnd() {
		if (this.pageNo >= this.pageCount) {
			return true;
		}
		return false;
	}

	public int getPrevPage() {
		int pageNo = this.pageNo;
		if (pageNo <= 1) {
			return 1;
		}
		pageNo--;
		return pageNo;
	}

	public int getNextPage() {
		int pageNo = this.pageNo;
		if (pageNo >= this.pageCount)
			pageNo = this.pageCount;
		else {
			pageNo = this.pageNo + 1;
		}
		return pageNo;
	}

	public int getFirstPage() {
		return 1;
	}

	public int getEndPage() {
		return this.pageCount;
	}

	private int getAllPage() {
		if (this.pageSize == 0)
			return 0;
		if (this.itemCount <= this.pageSize)
			return 1;
		if (this.itemCount % this.pageSize != 0) {
			return (int) Math.floor(this.itemCount / this.pageSize + 1.0D);
		}
		return (int) Math.floor(this.itemCount / this.pageSize);
	}

	public int getStartNo() {
		if (this.pageNo == 1) {
			return 0;
		}
		return (this.pageNo - 1) * this.pageSize;
	}
	
	/**↓↓ the start ↓↓**/
	/*	组装页面展示的分页*/
	public String getPageInfo() {
		StringBuffer s = new StringBuffer();

		s.append("项目数&nbsp;<b>" + getItemCount() + "</b>&nbsp;条&nbsp;&nbsp;|&nbsp;&nbsp;");
		s.append("页数&nbsp;<b>" + this.pageNo + "/" + this.pageCount + "</b>&nbsp;&nbsp;|&nbsp;&nbsp;");
		s.append("每页&nbsp;" + createPageSizeList() + "&nbsp;条&nbsp;&nbsp;|&nbsp;&nbsp;");

		if (isFirst()) {
			s.append("<a disabled=\"disabled\">首页</a>&nbsp;");
			s.append("<a disabled=\"disabled\">上页</a>&nbsp;");
		} else {
			s.append("<a href=\"" + this.action);
			s.append("?pageNo=" + getFirstPage());
			s.append("&pageSize=" + getPageSize());
			s.append("&pageCount=" + getPageCount());
			s.append("&itemCount=" + getItemCount() + this.searchKeys);
			s.append("\">首页</a>&nbsp;");

			s.append("<a href=\"" + this.action);
			s.append("?pageNo=" + getPrevPage());
			s.append("&pageSize=" + getPageSize());
			s.append("&pageCount=" + getPageCount());
			s.append("&itemCount=" + getItemCount() + this.searchKeys);
			s.append("\">上页</a>&nbsp;");
		}
		if (isEnd()) {
			s.append("<a disabled=\"disabled\">下页</a>&nbsp;");
			s.append("<a disabled=\"disabled\">尾页</a>");
		} else {
			s.append("<a href=\"" + this.action);
			s.append("?pageNo=" + getNextPage());
			s.append("&pageSize=" + getPageSize());
			s.append("&pageCount=" + getPageCount());
			s.append("&itemCount=" + getItemCount() + this.searchKeys);
			s.append("\">下页</a>&nbsp;");

			s.append("<a href=\"" + this.action);
			s.append("?pageNo=" + getEndPage());
			s.append("&pageSize=" + getPageSize());
			s.append("&pageCount=" + getPageCount());
			s.append("&itemCount=" + getItemCount() + this.searchKeys);
			s.append("\">尾页</a>");
		}

		s.append("&nbsp;&nbsp;|&nbsp;&nbsp;转到&nbsp;");
		s.append(createPageList());
		return s.toString();
	}

	private StringBuffer createPageList() {
		StringBuffer s = new StringBuffer();
		s.append("<input type=\"text/javascript\"  size=\"1\" name=\"_pageList\" id=\"_pageList\"");
		s.append(getItemCount() == 0 ? " disabled=\"disabled\" " : "");
		s.append(this.pageNo > this.pageCount ? " disabled=\"disabled\" " : "");
		s.append("value=" + this.pageNo);
		s.append(" onkeyup=\"this.value > " + this.pageCount + " ? this.value =" + this.pageCount + " : ' '\" ");
		s.append(" onchange=\"window.location='" + this.action + "?");
		s.append("pageNo='+ this.value +'");
		s.append("&pageSize=" + this.pageSize);
		s.append("&pageCount=" + this.pageCount);
		s.append("&itemCount=" + this.itemCount);
		s.append(this.searchKeys + "';\">");
		s.append("</input>&nbsp;页&nbsp;&nbsp;<a>跳转</a>");
		return s;
	}

	private StringBuffer createPageSizeList() {
		StringBuffer s = new StringBuffer();
		s.append("<select name=\"_pageSizeList\" id=\"_pageSizeList\" ");
		s.append(getItemCount() == 0 ? " disabled=\"disabled\" " : "");
		s.append("onchange=\"window.location='" + this.action + "?");
		s.append("pageNo=1");
		s.append("&pageSize='+ this[this.selectedIndex].value +'");
		s.append("&pageCount=" + this.pageCount);
		s.append("&itemCount=0");
		s.append(this.searchKeys + "';\">");

		for (int i = 0; i < this.arrPageSize.length; i++) {
			if (this.pageSize == this.arrPageSize[i].intValue())
				s.append("<option value=" + this.arrPageSize[i] + " selected=\"selected\">" + this.arrPageSize[i]
						+ "</option>");
			else {
				s.append("<option value=" + this.arrPageSize[i] + ">" + this.arrPageSize[i] + "</option>");
			}
		}
		s.append("</select>");
		return s;
	}
	/**↑↑ the end ↑↑**/

}
