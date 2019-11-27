package io.handyprojects.schedulerservice.config.constraint;

import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronStringValidator implements ConstraintValidator<CronString, String> {

    @Override
    public void initialize(CronString constraintAnnotation) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return CronExpression.isValidExpression(string);
    }
}
