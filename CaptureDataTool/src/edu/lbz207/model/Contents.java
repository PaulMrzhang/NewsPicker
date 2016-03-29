package edu.lbz207.model;

public class Contents {
	private String link;
	private String title;
	private String passage;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.link+"\n"+this.title+"\n"+this.passage;
	}
	public Contents() {
		super();
	}
	public Contents(String link, String title, String passage) {
		super();
		this.link = link;
		this.title = title;
		this.passage = passage;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
	}
	
}
