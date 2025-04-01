package org.codeacademy.siuntupristatymas.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.codeacademy.siuntupristatymas.enums.Status;
import org.codeacademy.siuntupristatymas.exception.CourierNotFoundException;
import org.codeacademy.siuntupristatymas.exception.ParcelNotFoundException;
import org.codeacademy.siuntupristatymas.exception.SameCourierIdProvidedException;
import org.codeacademy.siuntupristatymas.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final CourierService courierService;

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    public Parcel addParcel(Parcel parcel) {
        if (parcel.getStatus() == null){
            parcel.setStatus(Status.PENDING);
        }
        return parcelRepository.saveAndFlush(parcel);
    }

    public Parcel getParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    public Parcel updateParcelStatus(Long id, Status status) {
        Parcel parcelFromDb = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException("id=" + id));
        if (status != null){
            parcelFromDb.setStatus(status);
        }
        return parcelRepository.saveAndFlush(parcelFromDb);
    }

    public List<Parcel> getParcelsByCourier(Long id) {
        return parcelRepository.getParcelsByCourier(id);
    }

    public Parcel updateParcelCourierById(Long id, Long courierId) {
        Parcel parcel = getParcelById(id);
        Courier courier = courierService.getCourierById(courierId);
        if (parcel.getCourier() != null && parcel.getCourier().getId().equals(courier.getId())){
            throw new SameCourierIdProvidedException(courierId);
        }
        parcel.setCourier(courier);
        return parcelRepository.saveAndFlush(parcel);
    }

    public void deleteParcelById(Long id) {
        Optional<Parcel> maybeParcelFromDb = parcelRepository.findById(id);
        if (maybeParcelFromDb.isEmpty()){
            throw new ParcelNotFoundException("id=" + id);
        }
        parcelRepository.deleteById(id);
    }

    public Parcel patchParcelById(Long id, Parcel parcelFromRequest) {
        Parcel parcelFromDb = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException("id=" + id));

        if(StringUtils.isNotBlank(parcelFromRequest.getTrackingNumber()) &&
                !parcelFromRequest.getTrackingNumber().equals(parcelFromDb.getTrackingNumber())) {
            parcelFromDb.setTrackingNumber(parcelFromRequest.getTrackingNumber());
        }

        if(parcelFromRequest.getWeightKg() != null &&
                !parcelFromRequest.getWeightKg().equals(parcelFromDb.getWeightKg())) {
            parcelFromDb.setWeightKg(parcelFromRequest.getWeightKg());
        }

        if(parcelFromRequest.getStatus() != null &&
                parcelFromRequest.getStatus() != parcelFromDb.getStatus()) {
            parcelFromDb.setStatus(parcelFromRequest.getStatus());
        }

        if(parcelFromRequest.getCourier() != null &&
                !parcelFromRequest.getCourier().equals(parcelFromDb.getCourier())) {
            parcelFromDb.setCourier(parcelFromRequest.getCourier());
        }

        return parcelRepository.saveAndFlush(parcelFromDb);
    }

}
