package com.example.artemisconsumer.models;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditVars implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp1;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp2;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp3;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp Tmstmp4;
    @JacksonXmlProperty(isAttribute = true)
    Timestamp TmstmpX;
    @JacksonXmlProperty(isAttribute = true)
    String RejectionReason;
    @JacksonXmlProperty(isAttribute = true)
    String HTTPStatusCode;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef1;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef2;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef3;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef4;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef5;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef6;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef7;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef8;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef9;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef10;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef11;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef12;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef13;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef14;
    @JacksonXmlProperty(isAttribute = true)
    String UsrDef15;

    public Timestamp getTmstmp1() {
        return Tmstmp1;
    }

    public void setTmstmp1(Timestamp tmstmp1) {
        Tmstmp1 = tmstmp1;
    }

    public Timestamp getTmstmp2() {
        return Tmstmp2;
    }

    public void setTmstmp2(Timestamp tmstmp2) {
        Tmstmp2 = tmstmp2;
    }

    public Timestamp getTmstmp3() {
        return Tmstmp3;
    }

    public void setTmstmp3(Timestamp tmstmp3) {
        Tmstmp3 = tmstmp3;
    }

    public Timestamp getTmstmp4() {
        return Tmstmp4;
    }

    public void setTmstmp4(Timestamp tmstmp4) {
        Tmstmp4 = tmstmp4;
    }

    public Timestamp getTmstmpX() {
        return TmstmpX;
    }

    public void setTmstmpX(Timestamp tmstmpX) {
        TmstmpX = tmstmpX;
    }

    public String getRejectionReason() {
        return RejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        RejectionReason = rejectionReason;
    }

    public String getHTTPStatusCode() {
        return HTTPStatusCode;
    }

    public void setHTTPStatusCode(String HTTPStatusCode) {
        this.HTTPStatusCode = HTTPStatusCode;
    }

    public String getUsrDef1() {
        return UsrDef1;
    }

    public void setUsrDef1(String usrDef1) {
        UsrDef1 = usrDef1;
    }

    public String getUsrDef2() {
        return UsrDef2;
    }

    public void setUsrDef2(String usrDef2) {
        UsrDef2 = usrDef2;
    }

    public String getUsrDef3() {
        return UsrDef3;
    }

    public void setUsrDef3(String usrDef3) {
        UsrDef3 = usrDef3;
    }

    public String getUsrDef4() {
        return UsrDef4;
    }

    public void setUsrDef4(String usrDef4) {
        UsrDef4 = usrDef4;
    }

    public String getUsrDef5() {
        return UsrDef5;
    }

    public void setUsrDef5(String usrDef5) {
        UsrDef5 = usrDef5;
    }

    public String getUsrDef6() {
        return UsrDef6;
    }

    public void setUsrDef6(String usrDef6) {
        UsrDef6 = usrDef6;
    }

    public String getUsrDef7() {
        return UsrDef7;
    }

    public void setUsrDef7(String usrDef7) {
        UsrDef7 = usrDef7;
    }

    public String getUsrDef8() {
        return UsrDef8;
    }

    public void setUsrDef8(String usrDef8) {
        UsrDef8 = usrDef8;
    }

    public String getUsrDef9() {
        return UsrDef9;
    }

    public void setUsrDef9(String usrDef9) {
        UsrDef9 = usrDef9;
    }

    public String getUsrDef10() {
        return UsrDef10;
    }

    public void setUsrDef10(String usrDef10) {
        UsrDef10 = usrDef10;
    }

    public String getUsrDef11() {
        return UsrDef11;
    }

    public void setUsrDef11(String usrDef11) {
        UsrDef11 = usrDef11;
    }

    public String getUsrDef12() {
        return UsrDef12;
    }

    public void setUsrDef12(String usrDef12) {
        UsrDef12 = usrDef12;
    }

    public String getUsrDef13() {
        return UsrDef13;
    }

    public void setUsrDef13(String usrDef13) {
        UsrDef13 = usrDef13;
    }

    public String getUsrDef14() {
        return UsrDef14;
    }

    public void setUsrDef14(String usrDef14) {
        UsrDef14 = usrDef14;
    }

    public String getUsrDef15() {
        return UsrDef15;
    }

    public void setUsrDef15(String usrDef15) {
        UsrDef15 = usrDef15;
    }

    @Override
    public String toString() {
        return "AuditVars{" +
                "Tmstmp1=" + Tmstmp1 +
                ", Tmstmp2=" + Tmstmp2 +
                ", Tmstmp3=" + Tmstmp3 +
                ", Tmstmp4=" + Tmstmp4 +
                ", TmstmpX=" + TmstmpX +
                ", RejectionReason='" + RejectionReason + '\'' +
                ", HTTPStatusCode='" + HTTPStatusCode + '\'' +
                ", UsrDef1='" + UsrDef1 + '\'' +
                ", UsrDef2='" + UsrDef2 + '\'' +
                ", UsrDef3='" + UsrDef3 + '\'' +
                ", UsrDef4='" + UsrDef4 + '\'' +
                ", UsrDef5='" + UsrDef5 + '\'' +
                ", UsrDef6='" + UsrDef6 + '\'' +
                ", UsrDef7='" + UsrDef7 + '\'' +
                ", UsrDef8='" + UsrDef8 + '\'' +
                ", UsrDef9='" + UsrDef9 + '\'' +
                ", UsrDef10='" + UsrDef10 + '\'' +
                ", UsrDef11='" + UsrDef11 + '\'' +
                ", UsrDef12='" + UsrDef12 + '\'' +
                ", UsrDef13='" + UsrDef13 + '\'' +
                ", UsrDef14='" + UsrDef14 + '\'' +
                ", UsrDef15='" + UsrDef15 + '\'' +
                '}';
    }
}
