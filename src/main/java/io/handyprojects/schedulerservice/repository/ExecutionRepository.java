package io.handyprojects.schedulerservice.repository;

import io.handyprojects.schedulerservice.domain.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
