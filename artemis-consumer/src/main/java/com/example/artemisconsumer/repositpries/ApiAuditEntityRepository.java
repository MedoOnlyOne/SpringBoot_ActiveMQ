package com.example.artemisconsumer.repositpries;

import com.example.artemisconsumer.models.ApiAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAuditEntityRepository extends JpaRepository<ApiAuditEntity, Integer> {
}
