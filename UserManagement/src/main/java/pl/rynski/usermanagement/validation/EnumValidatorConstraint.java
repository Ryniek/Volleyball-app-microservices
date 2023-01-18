package pl.rynski.usermanagement.validation;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {

	private Set<String> acceptedValues;

	@Override
	public void initialize(EnumValidator constraintAnnotation) {
		acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
	            .map(Enum::name)
	            .collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) return true;
	    return acceptedValues.contains(value);
	}
}
	