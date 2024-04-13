package com.server.codewarriors.repository;

import com.server.codewarriors.model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventsModel, Long> {
}

