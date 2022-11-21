package com.example.artemisproducer.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.sql.Timestamp;

@JacksonXmlRootElement(localName = "DumpMsg")
public class DumpMsg implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    String EAIMsgCCSID;
    @JacksonXmlProperty(isAttribute = true)
    String EAIMsgEncoding;
    @JacksonXmlProperty(isAttribute = true)
    String EAIMsg;
    @JacksonXmlProperty(isAttribute = true)
    MsgRqHdr MsgRqHdr;
    @JacksonXmlProperty(isAttribute = true)
    String DumpMsgType;
    @JacksonXmlProperty(isAttribute = true)
    String FlowId;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp;
    @JacksonXmlProperty(isAttribute = true)
    String SvcPrvdrID;

    public String getEAIMsgCCSID() {
        return EAIMsgCCSID;
    }

    public void setEAIMsgCCSID(String EAIMsgCCSID) {
        this.EAIMsgCCSID = EAIMsgCCSID;
    }

    public String getEAIMsgEncoding() {
        return EAIMsgEncoding;
    }

    public void setEAIMsgEncoding(String EAIMsgEncoding) {
        this.EAIMsgEncoding = EAIMsgEncoding;
    }

    public String getEAIMsg() {
        return EAIMsg;
    }

    public void setEAIMsg(String EAIMsg) {
        this.EAIMsg = EAIMsg;
    }

    public MsgRqHdr getMsgRqHdr() {
        return MsgRqHdr;
    }

    public void setMsgRqHdr(MsgRqHdr msgRqHdr) {
        MsgRqHdr = msgRqHdr;
    }

    public String getDumpMsgType() {
        return DumpMsgType;
    }

    public void setDumpMsgType(String dumpMsgType) {
        DumpMsgType = dumpMsgType;
    }

    public String getFlowId() {
        return FlowId;
    }

    public void setFlowId(String flowId) {
        FlowId = flowId;
    }

    public Timestamp getTmstmp() {
        return Tmstmp;
    }

    public void setTmstmp(Timestamp tmstmp) {
        Tmstmp = tmstmp;
    }

    public String getSvcPrvdrID() {
        return SvcPrvdrID;
    }

    public void setSvcPrvdrID(String svcPrvdrID) {
        SvcPrvdrID = svcPrvdrID;
    }

    @Override
    public String toString() {
        return "DumpMsg{" +
                "EAIMsgCCSID='" + EAIMsgCCSID + '\'' +
                ", EAIMsgEncoding='" + EAIMsgEncoding + '\'' +
                ", EAIMsg='" + EAIMsg + '\'' +
                ", MsgRqHdr=" + MsgRqHdr +
                ", DumpMsgType='" + DumpMsgType + '\'' +
                ", FlowId='" + FlowId + '\'' +
                ", Tmstmp=" + Tmstmp +
                ", SvcPrvdrID='" + SvcPrvdrID + '\'' +
                '}';
    }
}
