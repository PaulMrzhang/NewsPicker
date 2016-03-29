package edu.lbz207.model;

public class Comments {
	private String link;
	private String fCount;	
	private String joinCount;
	private String hotjsonurl;
	private String commonjsonurl;
	private String commonjsonpage;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return link+"\n"+ fCount+"\n"+ joinCount+"\n"+  hotjsonurl +"\n"+commonjsonurl   +"\n"+ commonjsonpage ;
//		private String hotjsonurl;
//		private String commonjsonurl;
//		private String commonjsonpage;;
	}
	public Comments() {
		super();
	}
	public Comments(String link, String replyCount, String totalCount,
			String hotjsonurl, String commonjsonurl, String commonjsonpage) {
		super();
		this.link = link;
		this.fCount = replyCount;
		this.joinCount = totalCount;
		this.hotjsonurl = hotjsonurl;
		this.commonjsonurl = commonjsonurl;
		this.commonjsonpage = commonjsonpage;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getHotjsonurl() {
		return hotjsonurl;
	}
	public void setHotjsonurl(String hotjsonurl) {
		this.hotjsonurl = hotjsonurl;
	}
	public String getCommonjsonurl() {
		return commonjsonurl;
	}
	public void setCommonjsonurl(String commonjsonurl) {
		this.commonjsonurl = commonjsonurl;
	}
	public String getCommonjsonpage() {
		return commonjsonpage;
	}
	public void setCommonjsonpage(String commonjsonpage) {
		this.commonjsonpage = commonjsonpage;
	}
	public String getfCount() {
		return fCount;
	}
	public void setfCount(String fCount) {
		this.fCount = fCount;
	}
	public String getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(String joinCount) {
		this.joinCount = joinCount;
	}

}
