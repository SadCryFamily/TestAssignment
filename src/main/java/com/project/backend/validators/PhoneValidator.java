package com.project.backend.validators;

import com.project.backend.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_NUMBER_REGEX = "^(\\+\\d{12})$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return (phone != null) ? (Pattern.matches(PHONE_NUMBER_REGEX, phone)) : (false);
    }

    @Override
    public void initialize(Phone constraintAnnotation) {}
}
