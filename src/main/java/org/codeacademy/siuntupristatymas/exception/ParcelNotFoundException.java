package org.codeacademy.siuntupristatymas.exception;

public class ParcelNotFoundException extends RuntimeException {
    public ParcelNotFoundException(String criteria) {
        super("Parcel not found by: " + criteria);
    }
}
