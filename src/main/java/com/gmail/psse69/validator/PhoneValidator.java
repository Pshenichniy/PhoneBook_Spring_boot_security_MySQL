package com.gmail.psse69.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone paramA) {

    }


    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        // \+?([0-9]{2})?\(?[0-9]{3}\)?[0-9]{3}\-?[0-9]{2}\-?[0-9]{2}
        if (phoneNumber.matches("\\d{10}")){ return true;
        }
        else if(phoneNumber.matches("\\+?([0-9]{2})?\\(?[0-9]{3}\\)?[0-9]{7}")) {
            return true;
        }
        else if(phoneNumber.matches("\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}")) {
            return true;
        }
        else if(phoneNumber.matches("\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}")) {

            return true;
        }
        else if(phoneNumber.matches("\\(?([0-9]{3})\\)?[0-9]{7}")) {
            return true;
        }


        else return false;

    }
}
