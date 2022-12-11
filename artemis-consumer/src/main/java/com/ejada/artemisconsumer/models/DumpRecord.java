package com.ejada.artemisconsumer.models;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DumpRecord implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(isAttribute = true)
    List<Msgs> Msg = new ArrayList<>();

    public List<Msgs> getMsgs() {
        return Msg;
    }

    public void setMsgs(List<Msgs> dumpRecords) {
        Msg = dumpRecords;
    }

    @Override
    public String toString() {
        return "DumpRecord{" +
                "DumpRecords=" + Msg +
                '}';
    }
}
