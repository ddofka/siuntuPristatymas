package org.codeacademy.siuntupristatymas.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.codeacademy.siuntupristatymas.exception.CourierNotFoundException;
import org.codeacademy.siuntupristatymas.repository.CourierRepository;
import org.codeacademy.siuntupristatymas.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier addCourier(Courier courier) {
        return courierRepository.saveAndFlush(courier);
    }

    public Courier getCourierById(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("id=" + id));
    }

    public Courier patchCourierById(Long id, Courier courierFromRequest) {
        Courier courierFromDb = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("id=" + id));

        if(StringUtils.isNotBlank(courierFromRequest.getName()) &&
        !courierFromRequest.getName().equals(courierFromDb.getName())) {
            courierFromDb.setName(courierFromRequest.getName());
        }

        if(StringUtils.isNotBlank(courierFromRequest.getLastName()) &&
                !courierFromRequest.getLastName().equals(courierFromDb.getLastName())) {
            courierFromDb.setLastName(courierFromRequest.getLastName());
        }

        if(StringUtils.isNotBlank(courierFromRequest.getPersonalCode()) &&
                !courierFromRequest.getPersonalCode().equals(courierFromDb.getPersonalCode())) {
            courierFromDb.setPersonalCode(courierFromRequest.getPersonalCode());
        }

        if(StringUtils.isNotBlank(courierFromRequest.getVehicleNumber()) &&
                !courierFromRequest.getVehicleNumber().equals(courierFromDb.getVehicleNumber())) {
            courierFromDb.setVehicleNumber(courierFromRequest.getVehicleNumber());
        }
        return courierRepository.saveAndFlush(courierFromDb);
    }

//    public void deleteCourierById(Long id) {
//        Optional<Courier> maybeCourierFromDb = courierRepository.findById(id);
//        if (maybeCourierFromDb.isEmpty()){
//            throw new CourierNotFoundException("id=" + id);
//        }
//        courierRepository.deleteById(id);
//    }
/*
    public void addTestData() {
        Courier courier1 = new Courier();
        courier1.setName("John");
        courier1.setLastName("Doe");
        courier1.setPersonalCode("12345678901");
        courier1.setVehicleNumber("LOL777");
        saveCourier(courier1);

        Courier courier2 = new Courier();
        courier2.setName("Alice");
        courier2.setLastName("Smith");
        courier2.setPersonalCode("23456789012");
        courier2.setVehicleNumber("ABC123");
        saveCourier(courier2);

        Courier courier3 = new Courier();
        courier3.setName("Bob");
        courier3.setLastName("Johnson");
        courier3.setPersonalCode("34567890123");
        courier3.setVehicleNumber("XYZ987");
        saveCourier(courier3);

        Courier courier4 = new Courier();
        courier4.setName("Emma");
        courier4.setLastName("Brown");
        courier4.setPersonalCode("45678901234");
        courier4.setVehicleNumber("LMN456");
        saveCourier(courier4);

        Courier courier5 = new Courier();
        courier5.setName("Michael");
        courier5.setLastName("Williams");
        courier5.setPersonalCode("56789012345");
        courier5.setVehicleNumber("QWE852");
        saveCourier(courier5);
    }
*/

}
