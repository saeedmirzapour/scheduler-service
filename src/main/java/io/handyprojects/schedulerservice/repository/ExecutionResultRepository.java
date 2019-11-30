package io.handyprojects.schedulerservice.repository;

import io.handyprojects.schedulerservice.domain.ExecutionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionResultRepository extends JpaRepository<ExecutionResult, Long> {
}
