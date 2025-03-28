package org.codeacademy.siuntupristatymas.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException(String criteria) {
        super("Courier not found by: " + criteria);
    }
}
