package edu.lbz207.model;

public class HotCommentData {
	
	private String newsid;///新闻id
	private String source;///来源
	private String username;///用户名
	private String location;//地址
	private String content;///内容
	private String dateTime;///时间
	private String support;///支持
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return dateTime+"|"+username+"|"+content+"|"+location+"|";
	}
	public HotCommentData() {
		super();
	}
	public HotCommentData(String newsid, String source, String username,
			String location, String content, String dateTime, String support) {
		super();
		this.newsid = newsid;
		this.source = source;
		this.username = username;
		this.location = location;
		this.content = content;
		this.dateTime = dateTime;
		this.support = support;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}

	
	




}
