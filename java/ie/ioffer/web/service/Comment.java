package ie.ioffer.web.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	public String id;
	public String comment;
	public String date;
	public String author;
	
	public Comment(){
		
	}
	
	public Comment(String id, String comment, String date, String author){
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.author = author;
	}
	
	public Comment(String comment, String date, String author){
		this.comment = comment;
		this.date = date;
		this.author = author;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
}
