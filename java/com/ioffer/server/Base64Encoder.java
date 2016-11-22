package com.ioffer.server;

import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.binary.*;

public class Base64Encoder {
    public byte[] message;

    public Base64Encoder(String message){
            this.message = message.getBytes();
    }

    public Base64Encoder(byte[] message){
            this.message = message;
    }

    public String getEncoded() {
            return new String(message);
    }

    public boolean encode(){
            try{
                message = Base64.encodeBase64(message);
                return true;
            }catch(Exception e){
                return false;
            }
    }
}

