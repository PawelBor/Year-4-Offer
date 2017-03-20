package com.ioffer.gediminas.ioffer_android;

import android.location.Location;

import org.w3c.dom.Comment;

import java.util.List;

/**
 * Created by Gediminas on 13/03/2017.
 */

public class Product {

    // Member variables for all products
    private String name;
    private double price;
    private String description;
    private String image;
    private Location location;
    private String county;
    private String author;
    private String category;
    private String productId;
    private String mobileNo;
    private List<Comment> comment;

    public Product(){
    }

    public Product (String name, Double price, String description, String image, Location loc,
                    String county, String author, String category, String productId,
                    String mobileNo, List<Comment> comment){
        this();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.location = new Location(loc);
        this.county = county;
        this.author = author;
        this.category = category;
        this.mobileNo = mobileNo;
        this.comment = comment;
    }

    public Product (String name,String description){
        this();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setImage(Location location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
