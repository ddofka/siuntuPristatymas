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

    //TODO: arba delete arba pridet ParcelController updateParcelStatus endpoint
    public Parcel updateParcelStatus(Long id, Status status) {
        Parcel parcelFromDb = parcelRepository.findById(id)
                .orElseThrow(() -> new ParcelNotFoundException("id=" + id));
        if (status != null){
            parcelFromDb.setStatus(status);
        }
        return parcelRepository.saveAndFlush(parcelFromDb);
    }

    public List<Parcel> getParcelsByCourier(Long id) {
//        return new ArrayList<>(parcelRepository.findAll().stream()
//                .filter(parcel -> parcel.getCourier() != null
//                        && parcel.getCourier().getId().equals(id))
//                .toList());
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



/*
    public void addTestData() {
        Courier courier6 = new Courier();
        courier6.setName("Sophia");
        courier6.setLastName("Miller");
        courier6.setPersonalCode("67890123456");
        courier6.setVehicleNumber("GGG111");

        Courier courier7 = new Courier();
        courier7.setName("Liam");
        courier7.setLastName("Davis");
        courier7.setPersonalCode("78901234567");
        courier7.setVehicleNumber("HHH222");

        Courier courier8 = new Courier();
        courier8.setName("Olivia");
        courier8.setLastName("Wilson");
        courier8.setPersonalCode("89012345678");
        courier8.setVehicleNumber("III333");

        Courier courier9 = new Courier();
        courier9.setName("Noah");
        courier9.setLastName("Anderson");
        courier9.setPersonalCode("90123456789");
        courier9.setVehicleNumber("JJJ444");

        Courier courier10 = new Courier();
        courier10.setName("Ava");
        courier10.setLastName("Taylor");
        courier10.setPersonalCode("01234567890");
        courier10.setVehicleNumber("KKK555");

        Courier courier11 = new Courier();
        courier11.setName("William");
        courier11.setLastName("Martinez");
        courier11.setPersonalCode("11223344556");
        courier11.setVehicleNumber("LLL666");

        Courier courier12 = new Courier();
        courier12.setName("Isabella");
        courier12.setLastName("Harris");
        courier12.setPersonalCode("22334455667");
        courier12.setVehicleNumber("MMM777");

        Courier courier13 = new Courier();
        courier13.setName("James");
        courier13.setLastName("Clark");
        courier13.setPersonalCode("33445566778");
        courier13.setVehicleNumber("NNN888");

        Courier courier14 = new Courier();
        courier14.setName("Charlotte");
        courier14.setLastName("Lewis");
        courier14.setPersonalCode("44556677889");
        courier14.setVehicleNumber("OOO999");

        Courier courier15 = new Courier();
        courier15.setName("Benjamin");
        courier15.setLastName("Walker");
        courier15.setPersonalCode("55667788990");
        courier15.setVehicleNumber("PPP000");

        Parcel parcel1 = new Parcel();
        parcel1.setCourier(courier6);
        parcel1.setStatus(Status.PENDING);
        parcel1.setWeightKg(0.5);
        parcel1.setTrackingNumber("CN123456789LT");
        saveParcel(parcel1);

        Parcel parcel2 = new Parcel();
        parcel2.setCourier(courier7);
        parcel2.setStatus(Status.IN_TRANSIT);
        parcel2.setWeightKg(1.2);
        parcel2.setTrackingNumber("CN987654321LT");
        saveParcel(parcel2);

        Parcel parcel3 = new Parcel();
        parcel3.setCourier(courier8);
        parcel3.setStatus(Status.DELIVERED);
        parcel3.setWeightKg(2.3);
        parcel3.setTrackingNumber("CN456789123LT");
        saveParcel(parcel3);

        Parcel parcel4 = new Parcel();
        parcel4.setCourier(courier9);
        parcel4.setStatus(Status.PENDING);
        parcel4.setWeightKg(0.8);
        parcel4.setTrackingNumber("CN741852963LT");
        saveParcel(parcel4);

        Parcel parcel5 = new Parcel();
        parcel5.setCourier(courier10);
        parcel5.setStatus(Status.IN_TRANSIT);
        parcel5.setWeightKg(3.5);
        parcel5.setTrackingNumber("CN369258147LT");
        saveParcel(parcel5);

        Parcel parcel6 = new Parcel();
        parcel6.setCourier(courier11);
        parcel6.setStatus(Status.DELIVERED);
        parcel6.setWeightKg(4.0);
        parcel6.setTrackingNumber("CN159753468LT");
        saveParcel(parcel6);

        Parcel parcel7 = new Parcel();
        parcel7.setCourier(courier12);
        parcel7.setStatus(Status.PENDING);
        parcel7.setWeightKg(2.0);
        parcel7.setTrackingNumber("CN852741963LT");
        saveParcel(parcel7);

        Parcel parcel8 = new Parcel();
        parcel8.setCourier(courier13);
        parcel8.setStatus(Status.IN_TRANSIT);
        parcel8.setWeightKg(1.1);
        parcel8.setTrackingNumber("CN753951486LT");
        saveParcel(parcel8);

        Parcel parcel9 = new Parcel();
        parcel9.setCourier(courier14);
        parcel9.setStatus(Status.DELIVERED);
        parcel9.setWeightKg(5.5);
        parcel9.setTrackingNumber("CN369147258LT");
        saveParcel(parcel9);

        Parcel parcel10 = new Parcel();
        parcel10.setCourier(courier15);
        parcel10.setStatus(Status.PENDING);
        parcel10.setWeightKg(0.3);
        parcel10.setTrackingNumber("CN987321654LT");
        saveParcel(parcel10);
    }
*/

}
