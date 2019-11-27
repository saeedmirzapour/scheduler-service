package io.handyprojects.schedulerservice.config.constraint;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronStringValidator implements ConstraintValidator<CronString, String> {

    boolean nullable;

    @Override
    public void initialize(CronString constraintAnnotation) {
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable && StringUtils.isBlank(string))
            return true;
        return CronExpression.isValidExpression(string);
    }
}
