package com.devtf_l.app.entry;

public class RssItem {
	private String title;//Android Support库 22.1
	private String link;//http://www.devtf.cn/?p=229
	private String comments;//http://www.devtf.cn/?p=229#comments
	private String pubDate;	//Thu, 23 Apr 2015 12:44:11 +0000
	private String dc_creator;//<![CDATA[ MrSimple ]]>
	private String category;//<![CDATA[ Android ]]>
	private String guid;//http://www.devtf.cn/?p=229
	private String description;//<![CDATA[原文链接 : Android Support Library 22.1 原文作者 : Ian Lake 译文出自 : 开发技术前线 www.dev.. <a href="http://www.devtf.cn/?p=229">Read More</a>]]>
	private String content_encoded;
	//
	private String wfw_commentRss;//http://www.devtf.cn/?feed=rss2&p=229
	private String slash_comments;//0

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDc_creator() {
		return dc_creator;
	}

	public void setDc_creator(String dc_creator) {
		this.dc_creator = dc_creator;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent_encoded() {
		return content_encoded;
	}

	public void setContent_encoded(String content_encoded) {
		this.content_encoded = content_encoded;
	}

	public String getWfw_commentRss() {
		return wfw_commentRss;
	}

	public void setWfw_commentRss(String wfw_commentRss) {
		this.wfw_commentRss = wfw_commentRss;
	}

	public String getSlash_comments() {
		return slash_comments;
	}

	public void setSlash_comments(String slash_comments) {
		this.slash_comments = slash_comments;
	}

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", link=" + link + ", comments=" + comments + ", pubDate=" + pubDate + ", dc_creator=" + dc_creator + ", category=" + category + ", guid=" + guid
				+ ", description=" + description + ", content_encoded=" + content_encoded + ", wfw_commentRss=" + wfw_commentRss + ", slash_comments=" + slash_comments + "]";
	}
}
