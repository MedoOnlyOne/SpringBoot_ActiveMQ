package com.example.artemisconsumer.models;

import javax.persistence.*;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "api_audit", schema = "eair", catalog = "tspdb")
public class ApiAuditEntity implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "a_id")
    private int aId;
    @Column(name = "a_msg_id")
    private String aMsgId;
    @Column(name = "a_req_id")
    @JacksonXmlProperty(isAttribute = true)
    private String ReqID;
    @Column(name = "a_api_name")
    @JacksonXmlProperty(isAttribute = true)
    private String APIName;
    @Column(name = "a_api_version")
    @JacksonXmlProperty(isAttribute = true)
    private String APIVersion;
    @Column(name = "a_api_tp")
    @JacksonXmlProperty(isAttribute = true)
    private String APIType;
    @Column(name = "a_http_method")
    @JacksonXmlProperty(isAttribute = true)
    private String HTTPMethod;
    @Column(name = "a_protocol")
    @JacksonXmlProperty(isAttribute = true)
    private String Protocol;
    @Column(name = "a_api_root")
    @JacksonXmlProperty(isAttribute = true)
    private String APIRoot;
    @Column(name = "a_operation_id")
    @JacksonXmlProperty(isAttribute = true)
    private String OperationID;
    @Column(name = "a_api_path")
    @JacksonXmlProperty(isAttribute = true)
    private String APIPath;
    @Column(name = "a_catalogue_id")
    @JacksonXmlProperty(isAttribute = true)
    private String CatalogID;
    @Column(name = "a_catalogue_name")
    @JacksonXmlProperty(isAttribute = true)
    private String CatalogName;
    @Column(name = "a_client_org_id")
    @JacksonXmlProperty(isAttribute = true)
    private String ClientOrgID;
    @Column(name = "a_client_org_name")
    @JacksonXmlProperty(isAttribute = true)
    private String ClientOrgName;
    @Column(name = "a_client_app_id")
    @JacksonXmlProperty(isAttribute = true)
    private String ClientAppID;
    @Column(name = "a_client_app_name")
    @JacksonXmlProperty(isAttribute = true)
    private String ClientAppName;
    @Column(name = "a_tmstmp_1")
    @JacksonXmlProperty(isAttribute = true)
    private Timestamp Tmstmp1;
    @Column(name = "a_tmstmp_2")
    @JacksonXmlProperty(isAttribute = true)
    private Timestamp Tmstmp2;
    @Column(name = "a_tmstmp_3")
    @JacksonXmlProperty(isAttribute = true)
    private Timestamp Tmstmp3;
    @Column(name = "a_tmstmp_4")
    @JacksonXmlProperty(isAttribute = true)
    private Timestamp Tmstmp4;
    @Column(name = "a_tmstmp_x")
    @JacksonXmlProperty(isAttribute = true)
    private String TmstmpX;
    @Column(name = "a_rjctn_rsn")
    @JacksonXmlProperty(isAttribute = true)
    private String RejectionReason;
    @Column(name = "a_http_status_code")
    @JacksonXmlProperty(isAttribute = true)
    private String HTTPStatusCode;
    @Column(name = "a_usr_dfn_1")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef1;
    @Column(name = "a_usr_dfn_2")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef2;
    @Column(name = "a_usr_dfn_3")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef3;
    @Column(name = "a_usr_dfn_4")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef4;
    @Column(name = "a_usr_dfn_5")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef5;
    @Column(name = "a_usr_dfn_6")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef6;
    @Column(name = "a_usr_dfn_7")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef7;
    @Column(name = "a_usr_dfn_8")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef8;
    @Column(name = "a_usr_dfn_9")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef9;
    @Column(name = "a_usr_dfn_10")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef10;
    @Column(name = "a_usr_dfn_11")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef11;
    @Column(name = "a_usr_dfn_12")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef12;
    @Column(name = "a_usr_dfn_13")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef13;
    @Column(name = "a_usr_dfn_14")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef14;
    @Column(name = "a_usr_dfn_15")
    @JacksonXmlProperty(isAttribute = true)
    private String UsrDef15;

    public ApiAuditEntity() {}
//    public ApiAuditEntity(String aMsgId, String aReqId, String aApiName, String aApiVersion, String aApiTp, String aHttpMethod, String aProtocol, String aApiRoot, String aOperationId, String aApiPath, String aCatalogueId, String aCatalogueName, String aClientOrgId, String aClientOrgName, String aClientAppId, String aClientAppName, Timestamp aTmstmp1, Timestamp aTmstmp2, Timestamp aTmstmp3, Timestamp aTmstmp4, String aTmstmpX, String aRjctnRsn, String aHttpStatusCode, String aUsrDfn1, String aUsrDfn2, String aUsrDfn3, String aUsrDfn4, String aUsrDfn5, String aUsrDfn6, String aUsrDfn7, String aUsrDfn8, String aUsrDfn9, String aUsrDfn10, String aUsrDfn11, String aUsrDfn12, String aUsrDfn13, String aUsrDfn14, String aUsrDfn15) {
//        this.aMsgId = aMsgId;
//        this.aReqId = aReqId;
//        this.aApiName = aApiName;
//        this.aApiVersion = aApiVersion;
//        this.aApiTp = aApiTp;
//        this.aHttpMethod = aHttpMethod;
//        this.aProtocol = aProtocol;
//        this.aApiRoot = aApiRoot;
//        this.aOperationId = aOperationId;
//        this.aApiPath = aApiPath;
//        this.aCatalogueId = aCatalogueId;
//        this.aCatalogueName = aCatalogueName;
//        this.aClientOrgId = aClientOrgId;
//        this.aClientOrgName = aClientOrgName;
//        this.aClientAppId = aClientAppId;
//        this.aClientAppName = aClientAppName;
//        this.aTmstmp1 = aTmstmp1;
//        this.aTmstmp2 = aTmstmp2;
//        this.aTmstmp3 = aTmstmp3;
//        this.aTmstmp4 = aTmstmp4;
//        this.aTmstmpX = aTmstmpX;
//        this.aRjctnRsn = aRjctnRsn;
//        this.aHttpStatusCode = aHttpStatusCode;
//        this.aUsrDfn1 = aUsrDfn1;
//        this.aUsrDfn2 = aUsrDfn2;
//        this.aUsrDfn3 = aUsrDfn3;
//        this.aUsrDfn4 = aUsrDfn4;
//        this.aUsrDfn5 = aUsrDfn5;
//        this.aUsrDfn6 = aUsrDfn6;
//        this.aUsrDfn7 = aUsrDfn7;
//        this.aUsrDfn8 = aUsrDfn8;
//        this.aUsrDfn9 = aUsrDfn9;
//        this.aUsrDfn10 = aUsrDfn10;
//        this.aUsrDfn11 = aUsrDfn11;
//        this.aUsrDfn12 = aUsrDfn12;
//        this.aUsrDfn13 = aUsrDfn13;
//        this.aUsrDfn14 = aUsrDfn14;
//        this.aUsrDfn15 = aUsrDfn15;
//    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getaMsgId() {
        return aMsgId;
    }

    public void setaMsgId(String aMsgId) {
        this.aMsgId = aMsgId;
    }

    public String getaReqId() {
        return ReqID;
    }

    public void setaReqId(String aReqId) {
        this.ReqID = aReqId;
    }

    public String getaApiName() {
        return APIName;
    }

    public void setaApiName(String aApiName) {
        this.APIName = aApiName;
    }

    public String getaApiVersion() {
        return APIVersion;
    }

    public void setaApiVersion(String aApiVersion) {
        this.APIVersion = aApiVersion;
    }

    public String getaApiTp() {
        return APIType;
    }

    public void setaApiTp(String aApiTp) {
        this.APIVersion = aApiTp;
    }

    public String getaHttpMethod() {
        return HTTPMethod;
    }

    public void setaHttpMethod(String aHttpMethod) {
        this.HTTPMethod = aHttpMethod;
    }

    public String getaProtocol() {
        return  Protocol;
    }

    public void setaProtocol(String aProtocol) {
        this.Protocol = aProtocol;
    }

    public String getaApiRoot() {
        return APIRoot;
    }

    public void setaApiRoot(String aApiRoot) {
        this.APIRoot = aApiRoot;
    }

    public String getaOperationId() {
        return OperationID;
    }

    public void setaOperationId(String aOperationId) {
        this.OperationID = aOperationId;
    }

    public String getaApiPath() {
        return APIPath;
    }

    public void setaApiPath(String aApiPath) {
        this.APIPath = aApiPath;
    }

    public String getaCatalogueId() {
        return CatalogID;
    }

    public void setaCatalogueId(String aCatalogueId) {
        this.CatalogID = aCatalogueId;
    }

    public String getaCatalogueName() {
        return CatalogName;
    }

    public void setaCatalogueName(String aCatalogueName) {
        this.CatalogName = aCatalogueName;
    }

    public String getaClientOrgId() {
        return ClientOrgID;
    }

    public void setaClientOrgId(String aClientOrgId) {
        this.ClientOrgID = aClientOrgId;
    }

    public String getaClientOrgName() {
        return ClientOrgName;
    }

    public void setaClientOrgName(String aClientOrgName) {
        this.ClientOrgName = aClientOrgName;
    }

    public String getaClientAppId() {
        return ClientAppID;
    }

    public void setaClientAppId(String aClientAppId) {
        this.ClientAppID = aClientAppId;
    }

    public String getaClientAppName() {
        return ClientAppName;
    }

    public void setaClientAppName(String aClientAppName) {
        this.ClientAppName = aClientAppName;
    }

    public Timestamp getaTmstmp1() {
        return Tmstmp1;
    }

    public void setaTmstmp1(Timestamp aTmstmp1) {
        this.Tmstmp1 = aTmstmp1;
    }

    public Timestamp getaTmstmp2() {
        return Tmstmp2;
    }

    public void setaTmstmp2(Timestamp aTmstmp2) {
        this.Tmstmp2 = aTmstmp2;
    }

    public Timestamp getaTmstmp3() {
        return Tmstmp3;
    }

    public void setaTmstmp3(Timestamp aTmstmp3) {
        this.Tmstmp3 = aTmstmp3;
    }

    public Timestamp getaTmstmp4() {
        return Tmstmp4;
    }

    public void setaTmstmp4(Timestamp aTmstmp4) {
        this.Tmstmp4 = aTmstmp4;
    }

    public String getaTmstmpX() {
        return TmstmpX;
    }

    public void setaTmstmpX(String aTmstmpX) {
        this.TmstmpX = aTmstmpX;
    }

    public String getaRjctnRsn() {
        return RejectionReason;
    }

    public void setaRjctnRsn(String aRjctnRsn) {
        this.RejectionReason = aRjctnRsn;
    }

    public String getaHttpStatusCode() {
        return HTTPStatusCode;
    }

    public void setaHttpStatusCode(String aHttpStatusCode) {
        this.HTTPStatusCode = aHttpStatusCode;
    }

    public String getaUsrDfn1() {
        return UsrDef1;
    }

    public void setaUsrDfn1(String aUsrDfn1) {
        this.UsrDef1 = aUsrDfn1;
    }

    public String getaUsrDfn2() {
        return UsrDef2;
    }

    public void setaUsrDfn2(String aUsrDfn2) {
        this.UsrDef2 = aUsrDfn2;
    }

    public String getaUsrDfn3() {
        return UsrDef3;
    }

    public void setaUsrDfn3(String aUsrDfn3) {
        this.UsrDef3 = aUsrDfn3;
    }

    public String getaUsrDfn4() {
        return UsrDef4;
    }

    public void setaUsrDfn4(String aUsrDfn4) {
        this.UsrDef4 = aUsrDfn4;
    }

    public String getaUsrDfn5() {
        return UsrDef5;
    }

    public void setaUsrDfn5(String aUsrDfn5) {
        this.UsrDef5 = aUsrDfn5;
    }

    public String getaUsrDfn6() {
        return UsrDef6;
    }

    public void setaUsrDfn6(String aUsrDfn6) {
        this.UsrDef6 = aUsrDfn6;
    }

    public String getaUsrDfn7() {
        return UsrDef7;
    }

    public void setaUsrDfn7(String aUsrDfn7) {
        this.UsrDef7 = aUsrDfn7;
    }

    public String getaUsrDfn8() {
        return UsrDef8;
    }

    public void setaUsrDfn8(String aUsrDfn8) {
        this.UsrDef8 = aUsrDfn8;
    }

    public String getaUsrDfn9() {
        return UsrDef9;
    }

    public void setaUsrDfn9(String aUsrDfn9) {
        this.UsrDef9 = aUsrDfn9;
    }

    public String getaUsrDfn10() {
        return UsrDef10;
    }

    public void setaUsrDfn10(String aUsrDfn10) {
        this.UsrDef10 = aUsrDfn10;
    }

    public String getaUsrDfn11() {
        return UsrDef11;
    }

    public void setaUsrDfn11(String aUsrDfn11) {
        this.UsrDef11 = aUsrDfn11;
    }

    public String getaUsrDfn12() {
        return UsrDef12;
    }

    public void setaUsrDfn12(String aUsrDfn12) {
        this.UsrDef12 = aUsrDfn12;
    }

    public String getaUsrDfn13() {
        return UsrDef13;
    }

    public void setaUsrDfn13(String aUsrDfn13) {
        this.UsrDef13 = aUsrDfn13;
    }

    public String getaUsrDfn14() {
        return UsrDef14
        	;
    }

    public void setaUsrDfn14(String aUsrDfn14) {
        this.UsrDef14 = aUsrDfn14;
    }

    public String getaUsrDfn15() {
        return UsrDef15;
    }

    public void setaUsrDfn15(String aUsrDfn15) {
        this.UsrDef15 = aUsrDfn15;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ApiAuditEntity that = (ApiAuditEntity) o;
//        return aId == that.aId && Objects.equals(aMsgId, that.aMsgId) && Objects.equals(aReqId, that.aReqId) && Objects.equals(aApiName, that.aApiName) && Objects.equals(aApiVersion, that.aApiVersion) && Objects.equals(aApiTp, that.aApiTp) && Objects.equals(aHttpMethod, that.aHttpMethod) && Objects.equals(aProtocol, that.aProtocol) && Objects.equals(aApiRoot, that.aApiRoot) && Objects.equals(aOperationId, that.aOperationId) && Objects.equals(aApiPath, that.aApiPath) && Objects.equals(aCatalogueId, that.aCatalogueId) && Objects.equals(aCatalogueName, that.aCatalogueName) && Objects.equals(aClientOrgId, that.aClientOrgId) && Objects.equals(aClientOrgName, that.aClientOrgName) && Objects.equals(aClientAppId, that.aClientAppId) && Objects.equals(aClientAppName, that.aClientAppName) && Objects.equals(aTmstmp1, that.aTmstmp1) && Objects.equals(aTmstmp2, that.aTmstmp2) && Objects.equals(aTmstmp3, that.aTmstmp3) && Objects.equals(aTmstmp4, that.aTmstmp4) && Objects.equals(aTmstmpX, that.aTmstmpX) && Objects.equals(aRjctnRsn, that.aRjctnRsn) && Objects.equals(aHttpStatusCode, that.aHttpStatusCode) && Objects.equals(aUsrDfn1, that.aUsrDfn1) && Objects.equals(aUsrDfn2, that.aUsrDfn2) && Objects.equals(aUsrDfn3, that.aUsrDfn3) && Objects.equals(aUsrDfn4, that.aUsrDfn4) && Objects.equals(aUsrDfn5, that.aUsrDfn5) && Objects.equals(aUsrDfn6, that.aUsrDfn6) && Objects.equals(aUsrDfn7, that.aUsrDfn7) && Objects.equals(aUsrDfn8, that.aUsrDfn8) && Objects.equals(aUsrDfn9, that.aUsrDfn9) && Objects.equals(aUsrDfn10, that.aUsrDfn10) && Objects.equals(aUsrDfn11, that.aUsrDfn11) && Objects.equals(aUsrDfn12, that.aUsrDfn12) && Objects.equals(aUsrDfn13, that.aUsrDfn13) && Objects.equals(aUsrDfn14, that.aUsrDfn14) && Objects.equals(aUsrDfn15, that.aUsrDfn15);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(aId, aMsgId, aReqId, aApiName, aApiVersion, aApiTp, aHttpMethod, aProtocol, aApiRoot, aOperationId, aApiPath, aCatalogueId, aCatalogueName, aClientOrgId, aClientOrgName, aClientAppId, aClientAppName, aTmstmp1, aTmstmp2, aTmstmp3, aTmstmp4, aTmstmpX, aRjctnRsn, aHttpStatusCode, aUsrDfn1, aUsrDfn2, aUsrDfn3, aUsrDfn4, aUsrDfn5, aUsrDfn6, aUsrDfn7, aUsrDfn8, aUsrDfn9, aUsrDfn10, aUsrDfn11, aUsrDfn12, aUsrDfn13, aUsrDfn14, aUsrDfn15);
//    }
}
