package ru.kpfu.itis.kashapova.validation.validator;


import ru.kpfu.itis.kashapova.validation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String mailPattern = "[0-9a-zA-ZА-Яа-я!@#$%^&*]{6,}";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) {
            return false;
        }
        return s.matches(mailPattern);
    }
}
