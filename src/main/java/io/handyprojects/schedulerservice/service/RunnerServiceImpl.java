package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.util.DateUtil;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class RunnerServiceImpl implements RunnerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<Plan, CronExpression> plans = new HashMap<>();

    @Override
    public void run(Plan plan) {
        logger.info("run()");
    }

    @Scheduled(cron = "* * * * * *")
    private void loop() {
        plans.forEach((plan, cronExpression) -> {
            if (cronExpression.isSatisfiedBy(DateUtil.now())) {
                run(plan);
            }
        });
    }
}
