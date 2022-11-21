package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msgs implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    String MsgType;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp;
    @JacksonXmlProperty(isAttribute = true)
    String ProviderName;
    @JacksonXmlProperty(isAttribute = true)
    String HeaderParams;
    @JacksonXmlProperty(isAttribute = true)
    String QueryParams;
    @JacksonXmlProperty(isAttribute = true)
    String PathParams;
    @JacksonXmlProperty(isAttribute = true)
    String Payload;

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Timestamp getTmstmp() {
        return Tmstmp;
    }

    public void setTmstmp(Timestamp tmstmp) {
        Tmstmp = tmstmp;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String providerName) {
        ProviderName = providerName;
    }

    public String getHeaderParams() {
        return HeaderParams;
    }

    public void setHeaderParams(String headerParams) {
        HeaderParams = headerParams;
    }

    public String getQueryParams() {
        return QueryParams;
    }

    public void setQueryParams(String queryParams) {
        QueryParams = queryParams;
    }

    public String getPathParams() {
        return PathParams;
    }

    public void setPathParams(String pathParams) {
        PathParams = pathParams;
    }

    public String getPayload() {
        return Payload;
    }

    public void setPayload(String payload) {
        Payload = payload;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "MsgType='" + MsgType + '\'' +
                ", Tmstmp=" + Tmstmp +
                ", ProviderName=" + ProviderName +
                ", HeaderParams='" + HeaderParams + '\'' +
                ", QueryParams=" + QueryParams +
                ", PathParams='" + PathParams + '\'' +
                ", Payload='" + Payload + '\'' +
                '}';
    }
}
