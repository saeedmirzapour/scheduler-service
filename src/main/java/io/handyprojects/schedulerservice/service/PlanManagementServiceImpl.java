package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.exception.DuplicatePlanNameException;
import io.handyprojects.schedulerservice.exception.JobNotFoundException;
import io.handyprojects.schedulerservice.exception.PlanNotFoundException;
import io.handyprojects.schedulerservice.repository.JobRepository;
import io.handyprojects.schedulerservice.repository.PlanRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = {"plans", "jobs"})
public class PlanManagementServiceImpl implements PlanManagementService {

    private final PlanRepository planRepository;
    private final JobRepository jobRepository;

    public PlanManagementServiceImpl(PlanRepository planRepository,
                                     JobRepository jobRepository) {
        this.planRepository = planRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Plan createPlan(String name, String cronString, Boolean active) {
        planRepository.findByName(name).ifPresent(plan -> {
            throw new DuplicatePlanNameException();
        });
        Plan plan = new Plan(name, cronString, active);
        return planRepository.save(plan);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void removePlan(Long planId) {
        //todo: Bug - we have to delete all related jobs first
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        planRepository.delete(plan);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Plan editPlan(Long planId, String name, String cronString) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setName(name);
        plan.setCronString(cronString);
        return planRepository.save(plan);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Plan activatePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(true);
        return plan;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Plan deactivatePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(false);
        return plan;
    }

    @Override
    public Plan getPlan(Long planId) {
        return planRepository.findByIdHavingJobs(planId).orElseThrow(PlanNotFoundException::new);
    }

    @Override
    public Page<Plan> getPageablePlan(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Job activateJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setActive(true);
        return job;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Job createJob(Long planId, String curlCommand, int order, boolean active) {
        Plan plan = getPlan(planId);
        Job job = new Job(curlCommand, order, plan, active);
        return jobRepository.save(job);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Job deactivateJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setActive(false);
        return job;
    }

    @Override
    @CacheEvict(allEntries = true)
    public Job editJob(Long jobId, String curlCommand, int order) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setCurlCommand(curlCommand);
        job.setOrder(order);
        return jobRepository.save(job);
    }

    @Override
    @Cacheable("jobs")
    public Job getJob(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
    }

    @Override
    public Page<Job> getPageableJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void removeJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        jobRepository.delete(job);
    }

    @Cacheable("plans")
    public List<Plan> getAllWithJobs() {
        return planRepository.findAllWithJobs();
    }

    @Override
    public void runPlan(Long planId) {

    }


}
