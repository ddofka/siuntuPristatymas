package org.codeacademy.siuntupristatymas.exception;

public class SameCourierIdProvidedException extends RuntimeException {
    public SameCourierIdProvidedException(Long courierId) {
        super("Courier is already assigned to this parcel. Courier: " + courierId);
    }
}
