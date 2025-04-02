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

    public List<Courier> getCouriersByName(String name) {
        return courierRepository.findAllByName(name);
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


}
