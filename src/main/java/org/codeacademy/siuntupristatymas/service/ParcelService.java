package org.codeacademy.siuntupristatymas.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.codeacademy.siuntupristatymas.enums.Status;
import org.codeacademy.siuntupristatymas.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    public Parcel saveParcel(Parcel parcel) {
        return parcelRepository.saveAndFlush(parcel);
    }

    public void addTestData() {
        Parcel parcel1 = new Parcel();
        parcel1.setCourier(new Courier()); // Assign a valid courier if needed
        parcel1.setStatus(Status.PENDING);
        parcel1.setWeightKg(0.5);
        parcel1.setTrackingNumber("CN123456789LT");
        saveParcel(parcel1);

        Parcel parcel2 = new Parcel();
        parcel2.setCourier(new Courier());
        parcel2.setStatus(Status.IN_TRANSIT);
        parcel2.setWeightKg(1.2);
        parcel2.setTrackingNumber("CN987654321LT");
        saveParcel(parcel2);

        Parcel parcel3 = new Parcel();
        parcel3.setCourier(new Courier());
        parcel3.setStatus(Status.DELIVERED);
        parcel3.setWeightKg(2.3);
        parcel3.setTrackingNumber("CN456789123LT");
        saveParcel(parcel3);

        Parcel parcel4 = new Parcel();
        parcel4.setCourier(new Courier());
        parcel4.setStatus(Status.PENDING);
        parcel4.setWeightKg(0.8);
        parcel4.setTrackingNumber("CN741852963LT");
        saveParcel(parcel4);

        Parcel parcel5 = new Parcel();
        parcel5.setCourier(new Courier());
        parcel5.setStatus(Status.IN_TRANSIT);
        parcel5.setWeightKg(3.5);
        parcel5.setTrackingNumber("CN369258147LT");
        saveParcel(parcel5);

        Parcel parcel6 = new Parcel();
        parcel6.setCourier(new Courier());
        parcel6.setStatus(Status.DELIVERED);
        parcel6.setWeightKg(4.0);
        parcel6.setTrackingNumber("CN159753468LT");
        saveParcel(parcel6);

        Parcel parcel7 = new Parcel();
        parcel7.setCourier(new Courier());
        parcel7.setStatus(Status.PENDING);
        parcel7.setWeightKg(2.0);
        parcel7.setTrackingNumber("CN852741963LT");
        saveParcel(parcel7);

        Parcel parcel8 = new Parcel();
        parcel8.setCourier(new Courier());
        parcel8.setStatus(Status.IN_TRANSIT);
        parcel8.setWeightKg(1.1);
        parcel8.setTrackingNumber("CN753951486LT");
        saveParcel(parcel8);

        Parcel parcel9 = new Parcel();
        parcel9.setCourier(new Courier());
        parcel9.setStatus(Status.DELIVERED);
        parcel9.setWeightKg(5.5);
        parcel9.setTrackingNumber("CN369147258LT");
        saveParcel(parcel9);

        Parcel parcel10 = new Parcel();
        parcel10.setCourier(new Courier());
        parcel10.setStatus(Status.PENDING);
        parcel10.setWeightKg(0.3);
        parcel10.setTrackingNumber("CN987321654LT");
        saveParcel(parcel10);
    }

}
