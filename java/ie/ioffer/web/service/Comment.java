package ie.ioffer.web.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	public String id;
	public String comment;
	public String date;
	
	public Comment(){
		
	}
	
	public Comment(String id, String comment, String date){
		this.id = id;
		this.comment = comment;
		this.date = date;
	}
	
	public Comment(String comment, String date){
		this.comment = comment;
		this.date = date;
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
}
