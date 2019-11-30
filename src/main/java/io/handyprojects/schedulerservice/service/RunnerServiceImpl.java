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
    private final Executor executor;

    public RunnerServiceImpl(PlanManagementService planManagementService,
                             Executor executor) {
        this.planManagementService = planManagementService;
        this.executor = executor;
    }

    @Override
    public void run(Plan plan) {
        executor.execute(plan);
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
