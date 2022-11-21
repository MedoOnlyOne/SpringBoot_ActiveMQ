package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "Msg")
public class MsgOld implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    String RqID;

    public String getRqID() {
        return RqID;
    }

    public void setRqID(String rqID) {
        RqID = rqID;
    }
}
