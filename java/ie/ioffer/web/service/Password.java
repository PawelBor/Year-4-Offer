package ie.ioffer.web.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Password {
	public String email;
	public String oldPass;
	public String newPass;
	
	public Password(){
		
	}
	
	public Password(String email, String oldPass, String newPass){
		this.email = email;
		this.oldPass = oldPass;
		this.newPass = newPass;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getOldPass() {
		return oldPass;
	}
	
	public String getNewPass() {
		return newPass;
	}
}
