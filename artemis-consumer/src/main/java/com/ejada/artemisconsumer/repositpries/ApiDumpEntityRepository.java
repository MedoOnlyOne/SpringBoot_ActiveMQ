package com.ejada.artemisconsumer.repositpries;

import com.ejada.artemisconsumer.models.ApiDumpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiDumpEntityRepository extends JpaRepository<ApiDumpEntity, Integer> {
}
