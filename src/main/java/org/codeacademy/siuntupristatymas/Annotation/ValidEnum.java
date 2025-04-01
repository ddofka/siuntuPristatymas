package org.codeacademy.siuntupristatymas.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.codeacademy.siuntupristatymas.Validator.EnumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)  // Links to the validation logic
@Target({ElementType.FIELD, ElementType.PARAMETER})  // Can be used on fields or method parameters
@Retention(RetentionPolicy.RUNTIME)  // Available at runtime for validation
public @interface ValidEnum {
    String message() default "Invalid value for enum";  // Default error message

    Class<?>[] groups() default {};  // Required for Jakarta Validation groups (not commonly used)

    Class<? extends Payload>[] payload() default {};  // Allows adding metadata

    Class<? extends Enum<?>> enumClass();  // The enum type to validate
}