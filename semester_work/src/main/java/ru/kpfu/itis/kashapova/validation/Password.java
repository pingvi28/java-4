package ru.kpfu.itis.kashapova.validation;

import ru.kpfu.itis.kashapova.validation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * В message() методе мы можем указать сообщение которое будет выводится при валидации, мы это будем указывать с помощью Spring resource bundle.
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface Password {

    String message() default "{sign_up_page.wrong.password}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
