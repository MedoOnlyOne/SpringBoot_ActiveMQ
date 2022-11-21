package com.example.artemisconsumer.models;

import javax.persistence.*;
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
    private String mdMsgId;
    @Column(name = "md_req_id")
    private String mdReqId;
    @Column(name = "md_api_name")
    private String mdApiName;
    @Column(name = "md_api_root")
    private String mdApiRoot;
    @Column(name = "md_operation_id")
    private String mdOperationId;
    @Column(name = "md_api_path")
    private String mdApiPath;
    @Column(name = "md_msg_tp")
    private String mdMsgTp;
    @Column(name = "md_payload")
    private String mdPayload;
    @Column(name = "md_hdr_params")
    private String mdHdrParams;
    @Column(name = "md_query_params")
    private String mdQueryParams;
    @Column(name = "md_path_params")
    private String mdPathParams;
    @Column(name = "md_creation_tmstmp")
    private Timestamp mdCreationTmstmp;
    @Column(name = "md_prvdr_name")
    private String mdPrvdrName;

    public ApiDumpEntity(String mdMsgId, String mdReqId, String mdApiName, String mdApiRoot, String mdOperationId, String mdApiPath, String mdMsgTp, String mdPayload, String mdHdrParams, String mdQueryParams, String mdPathParams, Timestamp mdCreationTmstmp, String mdPrvdrName) {
        this.mdMsgId = mdMsgId;
        this.mdReqId = mdReqId;
        this.mdApiName = mdApiName;
        this.mdApiRoot = mdApiRoot;
        this.mdOperationId = mdOperationId;
        this.mdApiPath = mdApiPath;
        this.mdMsgTp = mdMsgTp;
        this.mdPayload = mdPayload;
        this.mdHdrParams = mdHdrParams;
        this.mdQueryParams = mdQueryParams;
        this.mdPathParams = mdPathParams;
        this.mdCreationTmstmp = mdCreationTmstmp;
        this.mdPrvdrName = mdPrvdrName;
    }

    public int getMdId() {
        return mdId;
    }

    public void setMdId(int mdId) {
        this.mdId = mdId;
    }

    public String getMdMsgId() {
        return mdMsgId;
    }

    public void setMdMsgId(String mdMsgId) {
        this.mdMsgId = mdMsgId;
    }

    public String getMdReqId() {
        return mdReqId;
    }

    public void setMdReqId(String mdReqId) {
        this.mdReqId = mdReqId;
    }

    public String getMdApiName() {
        return mdApiName;
    }

    public void setMdApiName(String mdApiName) {
        this.mdApiName = mdApiName;
    }

    public String getMdApiRoot() {
        return mdApiRoot;
    }

    public void setMdApiRoot(String mdApiRoot) {
        this.mdApiRoot = mdApiRoot;
    }

    public String getMdOperationId() {
        return mdOperationId;
    }

    public void setMdOperationId(String mdOperationId) {
        this.mdOperationId = mdOperationId;
    }

    public String getMdApiPath() {
        return mdApiPath;
    }

    public void setMdApiPath(String mdApiPath) {
        this.mdApiPath = mdApiPath;
    }

    public String getMdMsgTp() {
        return mdMsgTp;
    }

    public void setMdMsgTp(String mdMsgTp) {
        this.mdMsgTp = mdMsgTp;
    }

    public String getMdPayload() {
        return mdPayload;
    }

    public void setMdPayload(String mdPayload) {
        this.mdPayload = mdPayload;
    }

    public String getMdHdrParams() {
        return mdHdrParams;
    }

    public void setMdHdrParams(String mdHdrParams) {
        this.mdHdrParams = mdHdrParams;
    }

    public String getMdQueryParams() {
        return mdQueryParams;
    }

    public void setMdQueryParams(String mdQueryParams) {
        this.mdQueryParams = mdQueryParams;
    }

    public String getMdPathParams() {
        return mdPathParams;
    }

    public void setMdPathParams(String mdPathParams) {
        this.mdPathParams = mdPathParams;
    }

    public Timestamp getMdCreationTmstmp() {
        return mdCreationTmstmp;
    }

    public void setMdCreationTmstmp(Timestamp mdCreationTmstmp) {
        this.mdCreationTmstmp = mdCreationTmstmp;
    }

    public String getMdPrvdrName() {
        return mdPrvdrName;
    }

    public void setMdPrvdrName(String mdPrvdrName) {
        this.mdPrvdrName = mdPrvdrName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiDumpEntity that = (ApiDumpEntity) o;
        return mdId == that.mdId && Objects.equals(mdMsgId, that.mdMsgId) && Objects.equals(mdReqId, that.mdReqId) && Objects.equals(mdApiName, that.mdApiName) && Objects.equals(mdApiRoot, that.mdApiRoot) && Objects.equals(mdOperationId, that.mdOperationId) && Objects.equals(mdApiPath, that.mdApiPath) && Objects.equals(mdMsgTp, that.mdMsgTp) && Objects.equals(mdPayload, that.mdPayload) && Objects.equals(mdHdrParams, that.mdHdrParams) && Objects.equals(mdQueryParams, that.mdQueryParams) && Objects.equals(mdPathParams, that.mdPathParams) && Objects.equals(mdCreationTmstmp, that.mdCreationTmstmp) && Objects.equals(mdPrvdrName, that.mdPrvdrName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdId, mdMsgId, mdReqId, mdApiName, mdApiRoot, mdOperationId, mdApiPath, mdMsgTp, mdPayload, mdHdrParams, mdQueryParams, mdPathParams, mdCreationTmstmp, mdPrvdrName);
    }
}
