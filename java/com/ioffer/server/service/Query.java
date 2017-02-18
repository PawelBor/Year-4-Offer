/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

public class Query {
    private String name;
    private String category;
    private String minPrice;
    private String maxPrice;
    
    public Query(){
        super();
    }
    
    public Query(String name, String category, String minPrice, String maxPrice){
        this();
        this.name = name;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    } 
}