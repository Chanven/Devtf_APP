package com.devtf_l.app.entry;

/**
 * @Desc: 招聘信息，数据来源使用Jsoup解析网站 URL 抓取数据
 * @author ljh
 * @date 2015-5-6 下午1:35:18
 */
public class EmploymentItem {
	private String companyName; // 公司名
	private String jobName; // 职位
	private String jobDescAddress; // JD地址
	private String postTempt; // 一句话职位诱惑
	private String email; // 邮箱
	private boolean isRead;	//是否阅读过  后置添加阅读过变灰色需要使用

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescAddress() {
		return jobDescAddress;
	}

	public void setJobDescAddress(String jobDescAddress) {
		this.jobDescAddress = jobDescAddress;
	}

	public String getPostTempt() {
		return postTempt;
	}

	public void setPostTempt(String postTempt) {
		this.postTempt = postTempt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmploymentItem [companyName=" + companyName + ", jobName=" + jobName + ", jobDescAddress=" + jobDescAddress + ", postTempt=" + postTempt + ", email=" + email + "]";
	}
}
