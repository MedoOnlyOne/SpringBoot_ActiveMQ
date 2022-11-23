package com.example.artemisconsumer.repositpries;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.example.artemisconsumer.models.ApiDumpEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ApiDumpRepository {
    private JdbcTemplate jdbcTemplate;
    private List<ApiDumpEntity> apiDumpList;

    public ApiDumpRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void saveAll(List<ApiDumpEntity> apiDumpList) {
        this.apiDumpList = apiDumpList;
        jdbcTemplate.batchUpdate(
                "INSERT INTO api_dump (md_api_name, md_api_path, md_api_root, md_creation_tmstmp, md_hdr_params, md_msg_id, md_msg_tp, md_operation_id, md_path_params, md_payload, md_prvdr_name, md_query_params, md_req_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                this.apiDumpList,
                this.apiDumpList.size() , //Batch Size
                (PreparedStatement ps, ApiDumpEntity ApiDumpEntity) -> {
                    ps.setString(1, ApiDumpEntity.getMdApiName());
                    ps.setString(2, ApiDumpEntity.getMdApiPath());
                    ps.setString(3, ApiDumpEntity.getMdApiRoot());
                    ps.setTimestamp(4,ApiDumpEntity.getMdCreationTmstmp());
                    ps.setString(5,ApiDumpEntity.getMdHdrParams());
                    ps.setString(6,ApiDumpEntity.getMdMsgId() );
                    ps.setString(7,ApiDumpEntity.getMdMsgTp() );
                    ps.setString(8,ApiDumpEntity.getMdOperationId() );
                    ps.setString(9,ApiDumpEntity.getMdPathParams());
                    ps.setString(10,ApiDumpEntity.getMdPayload() );
                    ps.setString(11,ApiDumpEntity.getMdPrvdrName() );
                    ps.setString(12,ApiDumpEntity.getMdQueryParams() );
                    ps.setString(13,ApiDumpEntity.getMdReqId() );
                });
    }
}
