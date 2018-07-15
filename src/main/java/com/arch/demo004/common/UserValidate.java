package com.arch.demo004.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = UserValidateClass.class)
public @interface UserValidate {
    String value();
    String message() default "值非法！";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
