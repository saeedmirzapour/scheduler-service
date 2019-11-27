package io.handyprojects.schedulerservice.repository;

import io.handyprojects.schedulerservice.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
