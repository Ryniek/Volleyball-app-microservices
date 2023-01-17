package pl.rynski.usermanagement.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EnumValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumValidator {
	Class<? extends Enum<?>> enumClass();
	String message() default "must be any of enum {enumClass}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
