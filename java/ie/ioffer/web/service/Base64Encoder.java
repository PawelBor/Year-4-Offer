package ie.ioffer.web.service;

import org.apache.commons.codec.binary.*;

public class Base64Encoder {
    
    public Base64Encoder(){
    }
    
    public String encode(byte[] file){
        byte[] message = Base64.encodeBase64(file);
        return new String(message);
    }
    
    public String decode(byte[] file){
        byte[] message = Base64.decodeBase64(file);
        return new String(message);
    }
}

