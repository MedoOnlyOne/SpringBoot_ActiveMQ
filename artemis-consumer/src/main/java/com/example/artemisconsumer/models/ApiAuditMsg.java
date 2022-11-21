package com.example.artemisconsumer.models;

import java.io.Serializable;

public class ApiAuditMsg implements Serializable {
    String ReqID;
    AuditRecord AuditRecord;
    String json;
    String text;

    public ApiAuditMsg() {
    }

    public ApiAuditMsg(String reqID, AuditRecord auditRecord, String json, String text) {
        ReqID = reqID;
        AuditRecord = auditRecord;
        this.json = json;
        this.text = text;
    }

    public String getReqID() {
        return ReqID;
    }

    public void setReqID(String reqID) {
        ReqID = reqID;
    }

    public AuditRecord getAuditRecord() {
        return AuditRecord;
    }

    public void setAuditRecord(AuditRecord auditRecord) {
        AuditRecord = auditRecord;
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
        return "AuditMsg{" +
                "ReqID='" + ReqID + '\'' +
                ", AuditRecord=" + AuditRecord +
                ", json='" + json + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

