package com.example.artemisconsumer.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "api_audit", schema = "eair", catalog = "tspdb")
public class ApiAuditEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "a_id")
    private int aId;
    @Column(name = "a_msg_id")
    private String aMsgId;
    @Column(name = "a_req_id")
    private String aReqId;
    @Column(name = "a_api_name")
    private String aApiName;
    @Column(name = "a_api_version")
    private String aApiVersion;
    @Column(name = "a_api_tp")
    private String aApiTp;
    @Column(name = "a_http_method")
    private String aHttpMethod;
    @Column(name = "a_protocol")
    private String aProtocol;
    @Column(name = "a_api_root")
    private String aApiRoot;
    @Column(name = "a_operation_id")
    private String aOperationId;
    @Column(name = "a_api_path")
    private String aApiPath;
    @Column(name = "a_catalogue_id")
    private String aCatalogueId;
    @Column(name = "a_catalogue_name")
    private String aCatalogueName;
    @Column(name = "a_client_org_id")
    private String aClientOrgId;
    @Column(name = "a_client_org_name")
    private String aClientOrgName;
    @Column(name = "a_client_app_id")
    private String aClientAppId;
    @Column(name = "a_client_app_name")
    private String aClientAppName;
    @Column(name = "a_tmstmp_1")
    private Timestamp aTmstmp1;
    @Column(name = "a_tmstmp_2")
    private Timestamp aTmstmp2;
    @Column(name = "a_tmstmp_3")
    private Timestamp aTmstmp3;
    @Column(name = "a_tmstmp_4")
    private Timestamp aTmstmp4;
    @Column(name = "a_tmstmp_x")
    private String aTmstmpX;
    @Column(name = "a_rjctn_rsn")
    private String aRjctnRsn;
    @Column(name = "a_http_status_code")
    private String aHttpStatusCode;
    @Column(name = "a_usr_dfn_1")
    private String aUsrDfn1;
    @Column(name = "a_usr_dfn_2")
    private String aUsrDfn2;
    @Column(name = "a_usr_dfn_3")
    private String aUsrDfn3;
    @Column(name = "a_usr_dfn_4")
    private String aUsrDfn4;
    @Column(name = "a_usr_dfn_5")
    private String aUsrDfn5;
    @Column(name = "a_usr_dfn_6")
    private String aUsrDfn6;
    @Column(name = "a_usr_dfn_7")
    private String aUsrDfn7;
    @Column(name = "a_usr_dfn_8")
    private String aUsrDfn8;
    @Column(name = "a_usr_dfn_9")
    private String aUsrDfn9;
    @Column(name = "a_usr_dfn_10")
    private String aUsrDfn10;
    @Column(name = "a_usr_dfn_11")
    private String aUsrDfn11;
    @Column(name = "a_usr_dfn_12")
    private String aUsrDfn12;
    @Column(name = "a_usr_dfn_13")
    private String aUsrDfn13;
    @Column(name = "a_usr_dfn_14")
    private String aUsrDfn14;
    @Column(name = "a_usr_dfn_15")
    private String aUsrDfn15;

    public ApiAuditEntity(String aMsgId, String aReqId, String aApiName, String aApiVersion, String aApiTp, String aHttpMethod, String aProtocol, String aApiRoot, String aOperationId, String aApiPath, String aCatalogueId, String aCatalogueName, String aClientOrgId, String aClientOrgName, String aClientAppId, String aClientAppName, Timestamp aTmstmp1, Timestamp aTmstmp2, Timestamp aTmstmp3, Timestamp aTmstmp4, String aTmstmpX, String aRjctnRsn, String aHttpStatusCode, String aUsrDfn1, String aUsrDfn2, String aUsrDfn3, String aUsrDfn4, String aUsrDfn5, String aUsrDfn6, String aUsrDfn7, String aUsrDfn8, String aUsrDfn9, String aUsrDfn10, String aUsrDfn11, String aUsrDfn12, String aUsrDfn13, String aUsrDfn14, String aUsrDfn15) {
        this.aMsgId = aMsgId;
        this.aReqId = aReqId;
        this.aApiName = aApiName;
        this.aApiVersion = aApiVersion;
        this.aApiTp = aApiTp;
        this.aHttpMethod = aHttpMethod;
        this.aProtocol = aProtocol;
        this.aApiRoot = aApiRoot;
        this.aOperationId = aOperationId;
        this.aApiPath = aApiPath;
        this.aCatalogueId = aCatalogueId;
        this.aCatalogueName = aCatalogueName;
        this.aClientOrgId = aClientOrgId;
        this.aClientOrgName = aClientOrgName;
        this.aClientAppId = aClientAppId;
        this.aClientAppName = aClientAppName;
        this.aTmstmp1 = aTmstmp1;
        this.aTmstmp2 = aTmstmp2;
        this.aTmstmp3 = aTmstmp3;
        this.aTmstmp4 = aTmstmp4;
        this.aTmstmpX = aTmstmpX;
        this.aRjctnRsn = aRjctnRsn;
        this.aHttpStatusCode = aHttpStatusCode;
        this.aUsrDfn1 = aUsrDfn1;
        this.aUsrDfn2 = aUsrDfn2;
        this.aUsrDfn3 = aUsrDfn3;
        this.aUsrDfn4 = aUsrDfn4;
        this.aUsrDfn5 = aUsrDfn5;
        this.aUsrDfn6 = aUsrDfn6;
        this.aUsrDfn7 = aUsrDfn7;
        this.aUsrDfn8 = aUsrDfn8;
        this.aUsrDfn9 = aUsrDfn9;
        this.aUsrDfn10 = aUsrDfn10;
        this.aUsrDfn11 = aUsrDfn11;
        this.aUsrDfn12 = aUsrDfn12;
        this.aUsrDfn13 = aUsrDfn13;
        this.aUsrDfn14 = aUsrDfn14;
        this.aUsrDfn15 = aUsrDfn15;
    }

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
        return aReqId;
    }

    public void setaReqId(String aReqId) {
        this.aReqId = aReqId;
    }

    public String getaApiName() {
        return aApiName;
    }

    public void setaApiName(String aApiName) {
        this.aApiName = aApiName;
    }

    public String getaApiVersion() {
        return aApiVersion;
    }

    public void setaApiVersion(String aApiVersion) {
        this.aApiVersion = aApiVersion;
    }

    public String getaApiTp() {
        return aApiTp;
    }

    public void setaApiTp(String aApiTp) {
        this.aApiTp = aApiTp;
    }

    public String getaHttpMethod() {
        return aHttpMethod;
    }

    public void setaHttpMethod(String aHttpMethod) {
        this.aHttpMethod = aHttpMethod;
    }

    public String getaProtocol() {
        return aProtocol;
    }

    public void setaProtocol(String aProtocol) {
        this.aProtocol = aProtocol;
    }

    public String getaApiRoot() {
        return aApiRoot;
    }

    public void setaApiRoot(String aApiRoot) {
        this.aApiRoot = aApiRoot;
    }

    public String getaOperationId() {
        return aOperationId;
    }

    public void setaOperationId(String aOperationId) {
        this.aOperationId = aOperationId;
    }

    public String getaApiPath() {
        return aApiPath;
    }

    public void setaApiPath(String aApiPath) {
        this.aApiPath = aApiPath;
    }

    public String getaCatalogueId() {
        return aCatalogueId;
    }

    public void setaCatalogueId(String aCatalogueId) {
        this.aCatalogueId = aCatalogueId;
    }

    public String getaCatalogueName() {
        return aCatalogueName;
    }

    public void setaCatalogueName(String aCatalogueName) {
        this.aCatalogueName = aCatalogueName;
    }

    public String getaClientOrgId() {
        return aClientOrgId;
    }

    public void setaClientOrgId(String aClientOrgId) {
        this.aClientOrgId = aClientOrgId;
    }

    public String getaClientOrgName() {
        return aClientOrgName;
    }

    public void setaClientOrgName(String aClientOrgName) {
        this.aClientOrgName = aClientOrgName;
    }

    public String getaClientAppId() {
        return aClientAppId;
    }

    public void setaClientAppId(String aClientAppId) {
        this.aClientAppId = aClientAppId;
    }

    public String getaClientAppName() {
        return aClientAppName;
    }

    public void setaClientAppName(String aClientAppName) {
        this.aClientAppName = aClientAppName;
    }

    public Timestamp getaTmstmp1() {
        return aTmstmp1;
    }

    public void setaTmstmp1(Timestamp aTmstmp1) {
        this.aTmstmp1 = aTmstmp1;
    }

    public Timestamp getaTmstmp2() {
        return aTmstmp2;
    }

    public void setaTmstmp2(Timestamp aTmstmp2) {
        this.aTmstmp2 = aTmstmp2;
    }

    public Timestamp getaTmstmp3() {
        return aTmstmp3;
    }

    public void setaTmstmp3(Timestamp aTmstmp3) {
        this.aTmstmp3 = aTmstmp3;
    }

    public Timestamp getaTmstmp4() {
        return aTmstmp4;
    }

    public void setaTmstmp4(Timestamp aTmstmp4) {
        this.aTmstmp4 = aTmstmp4;
    }

    public String getaTmstmpX() {
        return aTmstmpX;
    }

    public void setaTmstmpX(String aTmstmpX) {
        this.aTmstmpX = aTmstmpX;
    }

    public String getaRjctnRsn() {
        return aRjctnRsn;
    }

    public void setaRjctnRsn(String aRjctnRsn) {
        this.aRjctnRsn = aRjctnRsn;
    }

    public String getaHttpStatusCode() {
        return aHttpStatusCode;
    }

    public void setaHttpStatusCode(String aHttpStatusCode) {
        this.aHttpStatusCode = aHttpStatusCode;
    }

    public String getaUsrDfn1() {
        return aUsrDfn1;
    }

    public void setaUsrDfn1(String aUsrDfn1) {
        this.aUsrDfn1 = aUsrDfn1;
    }

    public String getaUsrDfn2() {
        return aUsrDfn2;
    }

    public void setaUsrDfn2(String aUsrDfn2) {
        this.aUsrDfn2 = aUsrDfn2;
    }

    public String getaUsrDfn3() {
        return aUsrDfn3;
    }

    public void setaUsrDfn3(String aUsrDfn3) {
        this.aUsrDfn3 = aUsrDfn3;
    }

    public String getaUsrDfn4() {
        return aUsrDfn4;
    }

    public void setaUsrDfn4(String aUsrDfn4) {
        this.aUsrDfn4 = aUsrDfn4;
    }

    public String getaUsrDfn5() {
        return aUsrDfn5;
    }

    public void setaUsrDfn5(String aUsrDfn5) {
        this.aUsrDfn5 = aUsrDfn5;
    }

    public String getaUsrDfn6() {
        return aUsrDfn6;
    }

    public void setaUsrDfn6(String aUsrDfn6) {
        this.aUsrDfn6 = aUsrDfn6;
    }

    public String getaUsrDfn7() {
        return aUsrDfn7;
    }

    public void setaUsrDfn7(String aUsrDfn7) {
        this.aUsrDfn7 = aUsrDfn7;
    }

    public String getaUsrDfn8() {
        return aUsrDfn8;
    }

    public void setaUsrDfn8(String aUsrDfn8) {
        this.aUsrDfn8 = aUsrDfn8;
    }

    public String getaUsrDfn9() {
        return aUsrDfn9;
    }

    public void setaUsrDfn9(String aUsrDfn9) {
        this.aUsrDfn9 = aUsrDfn9;
    }

    public String getaUsrDfn10() {
        return aUsrDfn10;
    }

    public void setaUsrDfn10(String aUsrDfn10) {
        this.aUsrDfn10 = aUsrDfn10;
    }

    public String getaUsrDfn11() {
        return aUsrDfn11;
    }

    public void setaUsrDfn11(String aUsrDfn11) {
        this.aUsrDfn11 = aUsrDfn11;
    }

    public String getaUsrDfn12() {
        return aUsrDfn12;
    }

    public void setaUsrDfn12(String aUsrDfn12) {
        this.aUsrDfn12 = aUsrDfn12;
    }

    public String getaUsrDfn13() {
        return aUsrDfn13;
    }

    public void setaUsrDfn13(String aUsrDfn13) {
        this.aUsrDfn13 = aUsrDfn13;
    }

    public String getaUsrDfn14() {
        return aUsrDfn14;
    }

    public void setaUsrDfn14(String aUsrDfn14) {
        this.aUsrDfn14 = aUsrDfn14;
    }

    public String getaUsrDfn15() {
        return aUsrDfn15;
    }

    public void setaUsrDfn15(String aUsrDfn15) {
        this.aUsrDfn15 = aUsrDfn15;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiAuditEntity that = (ApiAuditEntity) o;
        return aId == that.aId && Objects.equals(aMsgId, that.aMsgId) && Objects.equals(aReqId, that.aReqId) && Objects.equals(aApiName, that.aApiName) && Objects.equals(aApiVersion, that.aApiVersion) && Objects.equals(aApiTp, that.aApiTp) && Objects.equals(aHttpMethod, that.aHttpMethod) && Objects.equals(aProtocol, that.aProtocol) && Objects.equals(aApiRoot, that.aApiRoot) && Objects.equals(aOperationId, that.aOperationId) && Objects.equals(aApiPath, that.aApiPath) && Objects.equals(aCatalogueId, that.aCatalogueId) && Objects.equals(aCatalogueName, that.aCatalogueName) && Objects.equals(aClientOrgId, that.aClientOrgId) && Objects.equals(aClientOrgName, that.aClientOrgName) && Objects.equals(aClientAppId, that.aClientAppId) && Objects.equals(aClientAppName, that.aClientAppName) && Objects.equals(aTmstmp1, that.aTmstmp1) && Objects.equals(aTmstmp2, that.aTmstmp2) && Objects.equals(aTmstmp3, that.aTmstmp3) && Objects.equals(aTmstmp4, that.aTmstmp4) && Objects.equals(aTmstmpX, that.aTmstmpX) && Objects.equals(aRjctnRsn, that.aRjctnRsn) && Objects.equals(aHttpStatusCode, that.aHttpStatusCode) && Objects.equals(aUsrDfn1, that.aUsrDfn1) && Objects.equals(aUsrDfn2, that.aUsrDfn2) && Objects.equals(aUsrDfn3, that.aUsrDfn3) && Objects.equals(aUsrDfn4, that.aUsrDfn4) && Objects.equals(aUsrDfn5, that.aUsrDfn5) && Objects.equals(aUsrDfn6, that.aUsrDfn6) && Objects.equals(aUsrDfn7, that.aUsrDfn7) && Objects.equals(aUsrDfn8, that.aUsrDfn8) && Objects.equals(aUsrDfn9, that.aUsrDfn9) && Objects.equals(aUsrDfn10, that.aUsrDfn10) && Objects.equals(aUsrDfn11, that.aUsrDfn11) && Objects.equals(aUsrDfn12, that.aUsrDfn12) && Objects.equals(aUsrDfn13, that.aUsrDfn13) && Objects.equals(aUsrDfn14, that.aUsrDfn14) && Objects.equals(aUsrDfn15, that.aUsrDfn15);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aId, aMsgId, aReqId, aApiName, aApiVersion, aApiTp, aHttpMethod, aProtocol, aApiRoot, aOperationId, aApiPath, aCatalogueId, aCatalogueName, aClientOrgId, aClientOrgName, aClientAppId, aClientAppName, aTmstmp1, aTmstmp2, aTmstmp3, aTmstmp4, aTmstmpX, aRjctnRsn, aHttpStatusCode, aUsrDfn1, aUsrDfn2, aUsrDfn3, aUsrDfn4, aUsrDfn5, aUsrDfn6, aUsrDfn7, aUsrDfn8, aUsrDfn9, aUsrDfn10, aUsrDfn11, aUsrDfn12, aUsrDfn13, aUsrDfn14, aUsrDfn15);
    }
}
