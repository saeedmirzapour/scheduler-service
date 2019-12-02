package io.handyprojects.schedulerservice.util;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Optional;

public class CronUtil {
    private final static Logger logger = LoggerFactory.getLogger(CronUtil.class);

    public static boolean isValidCronString(String cronString) {
        return CronExpression.isValidExpression(cronString);
    }

    public static Optional<CronExpression> makeCronExpression(String cronString) {
        try {
            return Optional.of(new CronExpression(cronString));
        } catch (ParseException e) {
            logger.error("problem in converting String to cron expression", e);
            return Optional.empty();
        }
    }
}
