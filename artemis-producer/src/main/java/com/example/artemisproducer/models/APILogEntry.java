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

     @Override
     public String toString() {
          return "APILogEntry{" +
                  "ReqID='" + ReqID + '\'' +
                  ", AuditRecord=" + AuditRecord +
                  ", DumpRecords=" + DumpRecords +
                  '}';
     }
}
