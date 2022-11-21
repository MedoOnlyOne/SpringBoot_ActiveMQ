package com.example.artemisconsumer.repositpries;

import com.example.artemisconsumer.models.EaiDumpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EaiDumpRepository extends JpaRepository<EaiDumpEntity, Integer> {
}
