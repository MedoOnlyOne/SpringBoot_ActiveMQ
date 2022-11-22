package com.example.artemisconsumer.repositpries;

import com.example.artemisconsumer.models.ApiDumpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiDumpEntityRepository extends JpaRepository<ApiDumpEntity, Integer> {
}
