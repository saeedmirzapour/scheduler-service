package io.handyprojects.schedulerservice.config.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention( RetentionPolicy.RUNTIME)
@Constraint( validatedBy = CronStringValidator.class)
public @interface CronString {
    String message() default "This is not a valid cron expression string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
