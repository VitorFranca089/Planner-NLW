package com.rocketseat.planner.validation;

import com.rocketseat.planner.trip.TripRequestPayload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndsAfterStartsValidator implements ConstraintValidator<EndsAfterStarts, TripRequestPayload> {

    @Override
    public boolean isValid(TripRequestPayload value, ConstraintValidatorContext context) {
        return value.starts_at().isBefore(value.ends_at());
    }
}
