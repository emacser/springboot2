package com.arch.demo004.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidateClass implements ConstraintValidator<UserValidate, String> {

   private String values;

   public void initialize(UserValidate constraint) {
      this.values = constraint.value();
}

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return this.values.indexOf(obj)>-1;
   }
}
