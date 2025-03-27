package org.codeacademy.siuntupristatymas.dto;

public record CreateCourierRequest (
       String personalCode,
       String name,
       String lastName,
       String vehicleNumber
){}
