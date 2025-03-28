package org.codeacademy.siuntupristatymas.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateCourierRequest(
        @Length(min = 5, max = 20)
        @NotBlank
        String personalCode,
        @Length(min=5, max=50)
        @NotBlank
        String name,
        @Length(min=5, max=100)
        @NotBlank
        String lastName,
        @Length(min=6, max=6)
        @NotBlank
        String vehicleNumber
) {
}
