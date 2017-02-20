/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

public class Location {
    private float latitude;
    private float longitude;
    
    public Location(){
        super();
    }
    
    public Location(float lat, float lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    public void setLatitude(float latitutde) {
        this.latitude = latitutde;
    }

    public String toString() {
        return latitude + "," + longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
