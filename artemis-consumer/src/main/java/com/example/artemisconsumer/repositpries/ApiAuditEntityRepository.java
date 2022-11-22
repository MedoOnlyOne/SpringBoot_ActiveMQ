package com.example.artemisconsumer.repositpries;

import com.example.artemisconsumer.models.ApiAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAuditEntityRepository extends JpaRepository<ApiAuditEntity, Integer> {
}
