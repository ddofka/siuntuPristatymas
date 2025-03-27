package org.codeacademy.siuntupristatymas.dto;

import jakarta.validation.constraints.*;
import org.codeacademy.siuntupristatymas.enums.Status;
import org.hibernate.validator.constraints.Length;

public record CreateParcelRequest (
        @Length(min = 5, max = 15)
        @NotBlank
        String trackingNumber,
        @NotNull
        @DecimalMin(value = "0.1", message = "Weight must be at least 0.1 kg")
        @DecimalMax(value = "20.0", message = "Weight must not exceed 20.0 kg")
        Double weightKg,
        @Size(max = 10, message = "Status must not exceed 10 characters")
        Status status,
        CreateCourierRequest courier
){}
