package org.codeacademy.siuntupristatymas.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateParcelCourier(
        @NotNull
        Long courierId
){}
