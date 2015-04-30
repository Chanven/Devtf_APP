package com.devtf_l.app.entry;

public class RssHead {
	private String title;//开发技术前线
	private String atomLink;
	private String link;//http://www.devtf.cn
	private String description;//高质量技术文章的聚合网站
	private String lastBuildDate;//Thu, 23 Apr 2015 12:44:11 +0000
	private String language;//zh-CN
	private String sy_updatePeriod;//hourly
	private String sy_updateFrequency;//1
	private String generator;//http://wordpress.org/?v=4.1.2

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAtomLink() {
		return atomLink;
	}

	public void setAtomLink(String atomLink) {
		this.atomLink = atomLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSy_updatePeriod() {
		return sy_updatePeriod;
	}

	public void setSy_updatePeriod(String sy_updatePeriod) {
		this.sy_updatePeriod = sy_updatePeriod;
	}

	public String getSy_updateFrequency() {
		return sy_updateFrequency;
	}

	public void setSy_updateFrequency(String sy_updateFrequency) {
		this.sy_updateFrequency = sy_updateFrequency;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	@Override
	public String toString() {
		return "RssHead [title=" + title + ", atomLink=" + atomLink + ", link=" + link + ", description=" + description + ", lastBuildDate=" + lastBuildDate + ", language=" + language + ", sy_updatePeriod="
				+ sy_updatePeriod + ", sy_updateFrequency=" + sy_updateFrequency + ", generator=" + generator + "]";
	}
}
