package org.codeacademy.siuntupristatymas.dto;

import org.codeacademy.siuntupristatymas.enums.Status;

public record GetCourierParcelsResponse(
        String trackingNumber,
        Double weightKg,
        Status status
) {}
