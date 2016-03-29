package edu.lbz207.model;

public class News {
	private String newid;
	private String source;
	private String clas;
	private String date;
	private String link;

	private Contents contents;
	private Comments comments;

	public News() {
		super();
	}

	public News(String newid, String source, String clas, String date,
			Contents contents, Comments comments) {
		super();
		this.newid = newid;
		this.source = source;
		this.clas = clas;
		this.date = date;
		this.contents = contents;
		this.comments = comments;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.newid +"\n"+  this.source+"\n" + this.clas +"\n"+ this.date+"\n" + this.contents
				+"\n"+ this.comments;
	}

	public String getNewid() {
		return newid;
	}

	public void setNewid(String newid) {
		this.newid = newid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Contents getContents() {
		return contents;
	}

	public void setContents(Contents contents) {
		this.contents = contents;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
/*
 * 基本属性的介绍 { 'newid' :'新闻的id' 'source' : '来源网站', 'clas' : '新闻类型', 'date' :
 * '时间（长度8位，如20150930）', 'contents' : { 'link' : '新闻详细内容页面的地址', 'title' :
 * '新闻标题', 'passage' : '新闻正文', }, 'comments' : { 'link' : '新闻对应的评论页面地址',
 * 'replycount': '跟帖数目', 'totalcount':'参与人数', 'hotjsonurl':'热门评论',
 * 'commonjsonurl':'最新评论', 'commonjsonpage':'最新评论的页码', },
 * 
 * }
 */