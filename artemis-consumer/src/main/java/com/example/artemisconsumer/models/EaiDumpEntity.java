package com.example.artemisconsumer.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "eai_dump", schema = "eair", catalog = "tspdb")
public class EaiDumpEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "md_id")
    private int mdId;
    @Column(name = "md_msg_id")
    private String mdMsgId;
    @Column(name = "md_flow_id")
    private String mdFlowId;
    @Column(name = "md_msg_tp")
    private String mdMsgTp;
    @Column(name = "md_chnl_id")
    private String mdChnlId;
    @Column(name = "md_fun_id")
    private String mdFunId;
    @Column(name = "md_msg_data")
    private String mdMsgData;
    @Column(name = "md_srvr_id")
    private String mdSrvrId;
    @Column(name = "md_bank_id")
    private String mdBankId;
    @Column(name = "md_branch_id")
    private String mdBranchId;
    @Column(name = "md_terminal_id")
    private String mdTerminalId;
    @Column(name = "md_card_num")
    private String mdCardNum;
    @Column(name = "md_terminal_desc")
    private String mdTerminalDesc;
    @Column(name = "md_request_id")
    private String mdRequestId;
    @Column(name = "md_creation_tmstmp")
    private Timestamp mdCreationTmstmp;
    @Column(name = "md_svc_prvdr_id")
    private String mdSvcPrvdrId;

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

    public String getMdFlowId() {
        return mdFlowId;
    }

    public void setMdFlowId(String mdFlowId) {
        this.mdFlowId = mdFlowId;
    }

    public String getMdMsgTp() {
        return mdMsgTp;
    }

    public void setMdMsgTp(String mdMsgTp) {
        this.mdMsgTp = mdMsgTp;
    }

    public String getMdChnlId() {
        return mdChnlId;
    }

    public void setMdChnlId(String mdChnlId) {
        this.mdChnlId = mdChnlId;
    }

    public String getMdFunId() {
        return mdFunId;
    }

    public void setMdFunId(String mdFunId) {
        this.mdFunId = mdFunId;
    }

    public String getMdMsgData() {
        return mdMsgData;
    }

    public void setMdMsgData(String mdMsgData) {
        this.mdMsgData = mdMsgData;
    }

    public String getMdSrvrId() {
        return mdSrvrId;
    }

    public void setMdSrvrId(String mdSrvrId) {
        this.mdSrvrId = mdSrvrId;
    }

    public String getMdBankId() {
        return mdBankId;
    }

    public void setMdBankId(String mdBankId) {
        this.mdBankId = mdBankId;
    }

    public String getMdBranchId() {
        return mdBranchId;
    }

    public void setMdBranchId(String mdBranchId) {
        this.mdBranchId = mdBranchId;
    }

    public String getMdTerminalId() {
        return mdTerminalId;
    }

    public void setMdTerminalId(String mdTerminalId) {
        this.mdTerminalId = mdTerminalId;
    }

    public String getMdCardNum() {
        return mdCardNum;
    }

    public void setMdCardNum(String mdCardNum) {
        this.mdCardNum = mdCardNum;
    }

    public String getMdTerminalDesc() {
        return mdTerminalDesc;
    }

    public void setMdTerminalDesc(String mdTerminalDesc) {
        this.mdTerminalDesc = mdTerminalDesc;
    }

    public String getMdRequestId() {
        return mdRequestId;
    }

    public void setMdRequestId(String mdRequestId) {
        this.mdRequestId = mdRequestId;
    }

    public Timestamp getMdCreationTmstmp() {
        return mdCreationTmstmp;
    }

    public void setMdCreationTmstmp(Timestamp mdCreationTmstmp) {
        this.mdCreationTmstmp = mdCreationTmstmp;
    }

    public String getMdSvcPrvdrId() {
        return mdSvcPrvdrId;
    }

    public void setMdSvcPrvdrId(String mdSvcPrvdrId) {
        this.mdSvcPrvdrId = mdSvcPrvdrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EaiDumpEntity eaiDump = (EaiDumpEntity) o;
        return mdId == eaiDump.mdId && Objects.equals(mdMsgId, eaiDump.mdMsgId) && Objects.equals(mdFlowId, eaiDump.mdFlowId) && Objects.equals(mdMsgTp, eaiDump.mdMsgTp) && Objects.equals(mdChnlId, eaiDump.mdChnlId) && Objects.equals(mdFunId, eaiDump.mdFunId) && Objects.equals(mdMsgData, eaiDump.mdMsgData) && Objects.equals(mdSrvrId, eaiDump.mdSrvrId) && Objects.equals(mdBankId, eaiDump.mdBankId) && Objects.equals(mdBranchId, eaiDump.mdBranchId) && Objects.equals(mdTerminalId, eaiDump.mdTerminalId) && Objects.equals(mdCardNum, eaiDump.mdCardNum) && Objects.equals(mdTerminalDesc, eaiDump.mdTerminalDesc) && Objects.equals(mdRequestId, eaiDump.mdRequestId) && Objects.equals(mdCreationTmstmp, eaiDump.mdCreationTmstmp) && Objects.equals(mdSvcPrvdrId, eaiDump.mdSvcPrvdrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdId, mdMsgId, mdFlowId, mdMsgTp, mdChnlId, mdFunId, mdMsgData, mdSrvrId, mdBankId, mdBranchId, mdTerminalId, mdCardNum, mdTerminalDesc, mdRequestId, mdCreationTmstmp, mdSvcPrvdrId);
    }

    @Override
    public String toString() {
        return "EaiDumpEntity{" +
                "mdId=" + mdId +
                ", mdMsgId='" + mdMsgId + '\'' +
                ", mdFlowId='" + mdFlowId + '\'' +
                ", mdMsgTp='" + mdMsgTp + '\'' +
                ", mdChnlId='" + mdChnlId + '\'' +
                ", mdFunId='" + mdFunId + '\'' +
                ", mdMsgData='" + mdMsgData + '\'' +
                ", mdSrvrId='" + mdSrvrId + '\'' +
                ", mdBankId='" + mdBankId + '\'' +
                ", mdBranchId='" + mdBranchId + '\'' +
                ", mdTerminalId='" + mdTerminalId + '\'' +
                ", mdCardNum='" + mdCardNum + '\'' +
                ", mdTerminalDesc='" + mdTerminalDesc + '\'' +
                ", mdRequestId='" + mdRequestId + '\'' +
                ", mdCreationTmstmp=" + mdCreationTmstmp +
                ", mdSvcPrvdrId='" + mdSvcPrvdrId + '\'' +
                '}';
    }
}
