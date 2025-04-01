package org.codeacademy.siuntupristatymas.dto;

public record GetCourierResponse(
   Long id,
   String personalCode,
   String name,
   String lastName,
   String vehicleNumber
) {}
