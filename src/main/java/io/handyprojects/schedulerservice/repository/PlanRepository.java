package io.handyprojects.schedulerservice.repository;

import io.handyprojects.schedulerservice.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByName(String name);
    @Query("from Plan p left join fetch p.jobs where p.id = :id")
    Optional<Plan> findByIdHavingJobs(@Param("id") Long id);
    @Query("from Plan p left join fetch p.jobs")
    List<Plan> findAllWithJobs();
}
