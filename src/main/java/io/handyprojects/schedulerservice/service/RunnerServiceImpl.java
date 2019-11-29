package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.util.CronUtil;
import io.handyprojects.schedulerservice.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class RunnerServiceImpl implements RunnerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PlanManagementService planManagementService;

    public RunnerServiceImpl(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @Override
    public void run(Plan plan) {
        logger.info("run() with jobs{}", plan.getJobs());
    }

    @Scheduled(cron = "* * * * * *")
    private void loop() {
        planManagementService.getAllWithJobs()
                .stream()
                .filter(Plan::isActive)
                .forEach(plan -> {
                    CronUtil.makeCronExpression(plan.getCronString()).ifPresent(expression -> {
                        if (expression.isSatisfiedBy(DateUtil.now()))
                            run(plan);
                    });
                });
    }


}
