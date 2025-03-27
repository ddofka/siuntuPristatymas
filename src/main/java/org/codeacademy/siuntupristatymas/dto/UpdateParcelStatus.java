package org.codeacademy.siuntupristatymas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.codeacademy.siuntupristatymas.enums.Status;

public record UpdateParcelStatus(
        @Size(max = 10, message = "Status must not exceed 10 characters")
        @NotNull
        Status status
){}
