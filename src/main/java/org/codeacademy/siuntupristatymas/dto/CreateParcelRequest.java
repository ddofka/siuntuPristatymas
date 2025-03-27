package org.codeacademy.siuntupristatymas.dto;

import org.codeacademy.siuntupristatymas.enums.Status;

public record CreateParcelRequest (
        String trackingNumber,
        Double weightKg,
        Status status,
        CreateCourierRequest courier
){}
