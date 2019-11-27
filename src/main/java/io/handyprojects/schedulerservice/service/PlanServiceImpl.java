package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.exception.DuplicatePlanNameException;
import io.handyprojects.schedulerservice.exception.PlanNotFoundException;
import io.handyprojects.schedulerservice.repository.PlanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final RunnerServiceImpl runnerService;

    public PlanServiceImpl(PlanRepository planRepository,
                           RunnerServiceImpl runnerService) {
        this.planRepository = planRepository;
        this.runnerService = runnerService;
    }

    @Override
    public Plan create(String name, String cronString, Boolean active) {
        planRepository.findByName(name).ifPresent(plan -> {
            throw new DuplicatePlanNameException();
        });
        Plan plan = new Plan(name, cronString, active);
        return planRepository.save(plan);
    }

    @Override
    public void remove(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        planRepository.delete(plan);
    }

    @Override
    public Plan edit(Long planId, String name, String cronString) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setName(name);
        plan.setCronString(cronString);
        return planRepository.save(plan);
    }

    @Override
    public void activate(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(true);
    }

    @Override
    public void deactivate(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(PlanNotFoundException::new);
        plan.setActive(false);
    }
}
