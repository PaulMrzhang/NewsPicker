package edu.lbz207.model;

public class CommentData {
	
	private String newsid;
	private String source;
	private String username;
	private String location;
	private String content;
	private String dateTime;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return newsid+"|" + username+"|" + location+"|" + content+"|" + dateTime+"|" + source;
	}
	
	public CommentData() {
		super();
	}


	public CommentData(String newsid, String source, String username,
			String location, String content, String support) {
		super();
		this.newsid = newsid;
		this.source = source;
		this.username = username;
		this.location = location;
		this.content = content;
		this.dateTime = support;
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


	
	
	

}
/*
{
 'newsid': '新闻的id'
 'source': '来源网站',
 'username':'用户名',
 'datetime':'发表时间',
 'location':'用户位置',
 'content':'评论内容',
 'support':'支持计数',
}  
 * */
 