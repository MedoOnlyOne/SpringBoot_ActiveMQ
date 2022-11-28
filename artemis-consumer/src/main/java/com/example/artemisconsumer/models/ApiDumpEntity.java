package com.example.artemisconsumer.models;

import javax.persistence.*;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "api_dump", schema = "eair", catalog = "tspdb")
public class ApiDumpEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "md_id")
    private int mdId;
    @Column(name = "md_msg_id")
    @JacksonXmlProperty(isAttribute = true)
    private String MsgId;
    @Column(name = "md_req_id")
    @JacksonXmlProperty(isAttribute = true)
    private String ReqID;
    @Column(name = "md_api_name")
    @JacksonXmlProperty(isAttribute = true)
    private String APIName;
    @Column(name = "md_api_root")
    @JacksonXmlProperty(isAttribute = true)
    private String APIRoot;
    @Column(name = "md_operation_id")
    @JacksonXmlProperty(isAttribute = true)
    private String OperationID;
    @Column(name = "md_api_path")
    @JacksonXmlProperty(isAttribute = true)
    private String APIPath;
    @Column(name = "md_msg_tp")
    @JacksonXmlProperty(isAttribute = true)
    private String MsgType;
    @Column(name = "md_payload")
    @JacksonXmlProperty(isAttribute = true)
    private String Payload;
    @Column(name = "md_hdr_params")
    @JacksonXmlProperty(isAttribute = true)
    private String HeaderParams;
    @Column(name = "md_query_params")
    @JacksonXmlProperty(isAttribute = true)
    private String QueryParams;
    @Column(name = "md_path_params")
    @JacksonXmlProperty(isAttribute = true)
    private String PathParams;
    @Column(name = "md_creation_tmstmp")
    @JacksonXmlProperty(isAttribute = true)
    private Timestamp Tmstmp;
    @Column(name = "md_prvdr_name")
    @JacksonXmlProperty(isAttribute = true)
    private String ProviderName;

//    public ApiDumpEntity(String mdMsgId, String mdReqId, String mdApiName, String mdApiRoot, String mdOperationId, String mdApiPath, String mdMsgTp, String mdPayload, String mdHdrParams, String mdQueryParams, String mdPathParams, Timestamp mdCreationTmstmp, String mdPrvdrName) {
//        this.mdMsgId = mdMsgId;
//        this.mdReqId = mdReqId;
//        this.mdApiName = mdApiName;
//        this.mdApiRoot = mdApiRoot;
//        this.mdOperationId = mdOperationId;
//        this.mdApiPath = mdApiPath;
//        this.mdMsgTp = mdMsgTp;
//        this.mdPayload = mdPayload;
//        this.mdHdrParams = mdHdrParams;
//        this.mdQueryParams = mdQueryParams;
//        this.mdPathParams = mdPathParams;
//        this.mdCreationTmstmp = mdCreationTmstmp;
//        this.mdPrvdrName = mdPrvdrName;
//    }

    public int getMdId() {
        return mdId;
    }

    public void setMdId(int mdId) {
        this.mdId = mdId;
    }

    public String getMdMsgId() {
        return MsgId;
    }

    public void setMdMsgId(String mdMsgId) {
        this.MsgId = mdMsgId;
    }

    public String getMdReqId() {
        return ReqID;
    }

    public void setMdReqId(String mdReqId) {
        this.ReqID = mdReqId;
    }

    public String getMdApiName() {
        return APIName;
    }

    public void setMdApiName(String mdApiName) {
        this.APIName = mdApiName;
    }

    public String getMdApiRoot() {
        return APIRoot;
    }

    public void setMdApiRoot(String mdApiRoot) {
        this.APIRoot = mdApiRoot;
    }

    public String getMdOperationId() {
        return OperationID;
    }

    public void setMdOperationId(String mdOperationId) {
        this.OperationID = mdOperationId;
    }

    public String getMdApiPath() {
        return APIPath;
    }

    public void setMdApiPath(String mdApiPath) {
        this.APIPath = mdApiPath;
    }

    public String getMdMsgTp() {
        return MsgType;
    }

    public void setMdMsgTp(String mdMsgTp) {
        this.MsgType = mdMsgTp;
    }

    public String getMdPayload() {
        return Payload;
    }

    public void setMdPayload(String mdPayload) {
        this.Payload = mdPayload;
    }

    public String getMdHdrParams() {
        return HeaderParams;
    }

    public void setMdHdrParams(String mdHdrParams) {
        this.HeaderParams = mdHdrParams;
    }

    public String getMdQueryParams() {
        return QueryParams;
    }

    public void setMdQueryParams(String mdQueryParams) {
        this.QueryParams = mdQueryParams;
    }

    public String getMdPathParams() {
        return PathParams;
    }

    public void setMdPathParams(String mdPathParams) {
        this.PathParams = mdPathParams;
    }

    public Timestamp getMdCreationTmstmp() {
        return Tmstmp;
    }

    public void setMdCreationTmstmp(Timestamp mdCreationTmstmp) {
        this.Tmstmp = mdCreationTmstmp;
    }

    public String getMdPrvdrName() {
        return ProviderName;
    }

    public void setMdPrvdrName(String mdPrvdrName) {
        this.ProviderName = mdPrvdrName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ApiDumpEntity that = (ApiDumpEntity) o;
//        return mdId == that.mdId && Objects.equals(mdMsgId, that.mdMsgId) && Objects.equals(mdReqId, that.mdReqId) && Objects.equals(mdApiName, that.mdApiName) && Objects.equals(mdApiRoot, that.mdApiRoot) && Objects.equals(mdOperationId, that.mdOperationId) && Objects.equals(mdApiPath, that.mdApiPath) && Objects.equals(mdMsgTp, that.mdMsgTp) && Objects.equals(mdPayload, that.mdPayload) && Objects.equals(mdHdrParams, that.mdHdrParams) && Objects.equals(mdQueryParams, that.mdQueryParams) && Objects.equals(mdPathParams, that.mdPathParams) && Objects.equals(mdCreationTmstmp, that.mdCreationTmstmp) && Objects.equals(mdPrvdrName, that.mdPrvdrName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(mdId, mdMsgId, mdReqId, mdApiName, mdApiRoot, mdOperationId, mdApiPath, mdMsgTp, mdPayload, mdHdrParams, mdQueryParams, mdPathParams, mdCreationTmstmp, mdPrvdrName);
//    }
}
