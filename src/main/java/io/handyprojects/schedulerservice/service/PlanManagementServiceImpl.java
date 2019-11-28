package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.exception.DuplicatePlanNameException;
import io.handyprojects.schedulerservice.exception.JobNotFoundException;
import io.handyprojects.schedulerservice.exception.PlanNotFoundException;
import io.handyprojects.schedulerservice.repository.JobRepository;
import io.handyprojects.schedulerservice.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlanManagementServiceImpl implements PlanManagementService {

    private final PlanRepository planRepository;
    private final JobRepository jobRepository;
    private final RunnerServiceImpl runnerService;

    public PlanManagementServiceImpl(PlanRepository planRepository,
                                     JobRepository jobRepository,
                                     RunnerServiceImpl runnerService) {
        this.planRepository = planRepository;
        this.jobRepository = jobRepository;
        this.runnerService = runnerService;
    }

    @Override
    public Plan createPlan(String name, String cronString, Boolean active) {
        planRepository.findByName(name).ifPresent(plan -> {
            throw new DuplicatePlanNameException();
        });
        Plan plan = new Plan(name, cronString, active);
        return planRepository.save(plan);
    }

    @Override
    public void removePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        planRepository.delete(plan);
    }

    @Override
    public Plan editPlan(Long planId, String name, String cronString) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setName(name);
        plan.setCronString(cronString);
        return planRepository.save(plan);
    }

    @Override
    public void activatePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(true);
    }

    @Override
    public void deactivatePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(false);
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
    public void activateJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setActive(true);
    }

    @Override
    public Job createJob(Long planId, String curlCommand, int order, boolean active) {
        Plan plan = getPlan(planId);
        Job job = new Job(curlCommand, order, plan, active);
        return jobRepository.save(job);
    }

    @Override
    public void deactivateJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setActive(false);
    }

    @Override
    public Job editJob(Long jobId, String curlCommand, int order) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setCurlCommand(curlCommand);
        job.setOrder(order);
        return jobRepository.save(job);
    }

    @Override
    public Job getJob(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
    }

    @Override
    public Page<Job> getPageableJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public void removeJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        jobRepository.delete(job);
    }
}
