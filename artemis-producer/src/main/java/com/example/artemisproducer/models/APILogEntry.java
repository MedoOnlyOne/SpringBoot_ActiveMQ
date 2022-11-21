package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "APILogEntry")
public class APILogEntry implements Serializable {
     @JacksonXmlProperty(isAttribute = true)
     String ReqID;
     @JacksonXmlProperty(isAttribute = true)
     AuditRecord AuditRecord;
     @JacksonXmlProperty(isAttribute = true)
     DumpRecord DumpRecords;
     @JacksonXmlProperty(isAttribute = true)
     String json;
     @JacksonXmlProperty(isAttribute = true)
     String text;

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

     public DumpRecord getDumpRecords() {
          return DumpRecords;
     }

     public void setDumpRecords(DumpRecord dumpRecord) {
          DumpRecords = dumpRecord;
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
          return "APILogEntry{" +
                  "ReqID='" + ReqID + '\'' +
                  ", AuditRecord=" + AuditRecord +
                  ", DumpRecords=" + DumpRecords +
                  ", json='" + json + '\'' +
                  ", text='" + text + '\'' +
                  '}';
     }
}
