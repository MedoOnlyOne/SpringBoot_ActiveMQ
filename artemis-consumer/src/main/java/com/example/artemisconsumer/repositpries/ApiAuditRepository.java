package com.example.artemisconsumer.repositpries;

import java.sql.PreparedStatement;
import java.util.List;
import com.example.artemisconsumer.models.ApiAuditEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ApiAuditRepository {
    private JdbcTemplate jdbcTemplate;
    private List<ApiAuditEntity> apiAuditList;
    public ApiAuditRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void saveAll(List<ApiAuditEntity> apiAuditList) {
        this.apiAuditList = apiAuditList;
        jdbcTemplate.batchUpdate(
                "INSERT INTO api_audit (a_msg_id, a_req_id, a_api_name, a_api_version, a_api_tp, a_http_method, a_protocol, a_api_root, a_operation_id, a_api_path, a_catalogue_id, a_catalogue_name, a_client_org_id, a_client_org_name, a_client_app_id, a_client_app_name, a_tmstmp_1, a_tmstmp_2, a_tmstmp_3, a_tmstmp_4, a_tmstmp_x, a_rjctn_rsn, a_http_status_code, a_usr_dfn_1, a_usr_dfn_2, a_usr_dfn_3, a_usr_dfn_4, a_usr_dfn_5, a_usr_dfn_6, a_usr_dfn_7, a_usr_dfn_8, a_usr_dfn_9, a_usr_dfn_10, a_usr_dfn_11, a_usr_dfn_12, a_usr_dfn_13, a_usr_dfn_14, a_usr_dfn_15) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                this.apiAuditList,
                this.apiAuditList.size() , //Batch Size
                (PreparedStatement ps, ApiAuditEntity apiAuditEntity) -> {
                    ps.setString(1, apiAuditEntity.getaMsgId());
                    ps.setString(2, apiAuditEntity.getaReqId());
                    ps.setString(3, apiAuditEntity.getaApiName());
                    ps.setString(4, apiAuditEntity.getaApiVersion());
                    ps.setString(5, apiAuditEntity.getaApiTp());
                    ps.setString(6, apiAuditEntity.getaHttpMethod());
                    ps.setString(7, apiAuditEntity.getaProtocol());
                    ps.setString(8, apiAuditEntity.getaApiRoot());
                    ps.setString(9, apiAuditEntity.getaOperationId());
                    ps.setString(10, apiAuditEntity.getaApiPath());
                    ps.setString(11, apiAuditEntity.getaCatalogueId());
                    ps.setString(12, apiAuditEntity.getaCatalogueName());
                    ps.setString(13, apiAuditEntity.getaClientOrgId());
                    ps.setString(14, apiAuditEntity.getaClientOrgName());
                    ps.setString(15, apiAuditEntity.getaClientAppId());
                    ps.setString(16, apiAuditEntity.getaClientAppName());
                    ps.setTimestamp(17, apiAuditEntity.getaTmstmp1());
                    ps.setTimestamp(18, apiAuditEntity.getaTmstmp2());
                    ps.setTimestamp(19, apiAuditEntity.getaTmstmp3());
                    ps.setTimestamp(20, apiAuditEntity.getaTmstmp4());
                    ps.setString(21, apiAuditEntity.getaTmstmpX());
                    ps.setString(22, apiAuditEntity.getaRjctnRsn());
                    ps.setString(23, apiAuditEntity.getaHttpStatusCode());
                    ps.setString(24, apiAuditEntity.getaUsrDfn1());
                    ps.setString(25, apiAuditEntity.getaUsrDfn2());
                    ps.setString(26, apiAuditEntity.getaUsrDfn3());
                    ps.setString(27, apiAuditEntity.getaUsrDfn4());
                    ps.setString(28, apiAuditEntity.getaUsrDfn5());
                    ps.setString(29, apiAuditEntity.getaUsrDfn6());
                    ps.setString(30, apiAuditEntity.getaUsrDfn7());
                    ps.setString(31, apiAuditEntity.getaUsrDfn8());
                    ps.setString(32, apiAuditEntity.getaUsrDfn9());
                    ps.setString(33, apiAuditEntity.getaUsrDfn10());
                    ps.setString(34, apiAuditEntity.getaUsrDfn11());
                    ps.setString(35, apiAuditEntity.getaUsrDfn12());
                    ps.setString(36, apiAuditEntity.getaUsrDfn13());
                    ps.setString(37, apiAuditEntity.getaUsrDfn14());
                    ps.setString(38, apiAuditEntity.getaUsrDfn15());

                });
    }
}
