package com.example.workshop1.repositories;

import com.example.workshop1.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);

    List<Project> findByIsFinishedTrueOrderByPaymentDesc();
}
