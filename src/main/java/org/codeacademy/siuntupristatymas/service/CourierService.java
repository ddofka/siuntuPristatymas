package org.codeacademy.siuntupristatymas.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier saveCourier(Courier courier) {
        return courierRepository.saveAndFlush(courier);
    }


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


}
