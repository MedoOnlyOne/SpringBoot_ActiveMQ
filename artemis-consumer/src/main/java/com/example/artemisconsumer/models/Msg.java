package com.example.artemisconsumer.models;

import java.io.Serializable;

public class Msg implements Serializable {
    String RqID;

    public String getRqID() {
        return RqID;
    }

    public void setRqID(String rqID) {
        RqID = rqID;
    }
}
