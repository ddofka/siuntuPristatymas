package org.codeacademy.siuntupristatymas.dto;

import org.codeacademy.siuntupristatymas.enums.Status;

public record GetParcelResponse(
        Long id,
        String trackingNumber,
        Double weightKg,
        Status status,
        GetCourierResponse courier
) {}
