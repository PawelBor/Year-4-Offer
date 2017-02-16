/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

import javax.ejb.Singleton;

/**
 *
 * @author Niks
 */
@Singleton
public class UserInstance {
    private static UserInstance user = new UserInstance();
    
    private String email;
    private String name;
    private String county;
    
    protected UserInstance(){
        
    }
    
    public static UserInstance getInstance(){
        return user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCounty() {
        return county;
    }
}
