package com.server.codewarriors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.codewarriors.model.CompetitiveProblemsModel;

public interface CompetitiveProblemsRepository extends JpaRepository<CompetitiveProblemsModel, Long> {
}
