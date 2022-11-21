package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "MsgRqHdr")
public class MsgRqHdr implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    MsgOld MsgOld;

    public MsgOld getMsg() {
        return MsgOld;
    }

    public void setMsg(MsgOld msgOld) {
        MsgOld = msgOld;
    }
}
