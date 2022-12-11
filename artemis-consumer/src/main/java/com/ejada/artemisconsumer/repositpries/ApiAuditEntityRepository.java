package com.ejada.artemisconsumer.repositpries;

import com.ejada.artemisconsumer.models.ApiAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAuditEntityRepository extends JpaRepository<ApiAuditEntity, Integer> {
}
