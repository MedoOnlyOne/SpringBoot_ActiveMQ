package com.ejada.artemisconsumer.models;

import java.io.Serializable;

public class ApiDumpMsg implements Serializable {
    String ReqID;
    DumpRecord DumpRecords;
    String json;
    String text;

    public ApiDumpMsg() {
    }

    public ApiDumpMsg(String reqID, DumpRecord dumpRecords, String json, String text) {
        ReqID = reqID;
        DumpRecords = dumpRecords;
        this.json = json;
        this.text = text;
    }

    public String getReqID() {
        return ReqID;
    }

    public void setReqID(String reqID) {
        ReqID = reqID;
    }

    public DumpRecord getDumpRecords() {
        return DumpRecords;
    }

    public void setDumpRecords(DumpRecord dumpRecords) {
        DumpRecords = dumpRecords;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DumpMesg{" +
                "ReqID='" + ReqID + '\'' +
                ", DumpRecords=" + DumpRecords +
                ", json='" + json + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
