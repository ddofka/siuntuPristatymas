package org.codeacademy.siuntupristatymas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Status {

    PENDING,
    IN_TRANSIT,
    DELIVERED;

    @JsonCreator
    public static Status fromValue(String value) {
        return value == null ? null : Status.valueOf(value.toUpperCase().replace(" ", "_"));
    }
}
