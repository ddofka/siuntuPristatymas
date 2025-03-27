package org.codeacademy.siuntupristatymas.controller;

import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.dto.CreateCourierRequest;
import org.codeacademy.siuntupristatymas.dto.GetCourierParcelsResponse;
import org.codeacademy.siuntupristatymas.dto.GetCourierResponse;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.mapper.CourierMapper;
import org.codeacademy.siuntupristatymas.mapper.ParcelMapper;
import org.codeacademy.siuntupristatymas.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/couriers")
@RestController
public class CourierController {

    private final CourierService courierService;
    private final CourierMapper courierMapper;
    private final ParcelMapper parcelMapper;

    @GetMapping
    public ResponseEntity<List<GetCourierResponse>> getAllCouriers(@RequestParam(required = false) Long id) {
        List<GetCourierResponse> couriers = courierMapper.courierListToDto(courierService.getAllCouriers());
        if (couriers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(couriers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCourierResponse> getCourierById(@PathVariable Long id) {
        Courier courierById = courierService.getCourierById(id);
        GetCourierResponse response = courierMapper.courierToDto(courierById);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/parcels/{id}")
    public ResponseEntity<List<GetCourierParcelsResponse>> getAllCouriersParcel(@PathVariable Long id) {
        List<GetCourierParcelsResponse> couriersParcels =
            parcelMapper.courierParcelListToDto(courierService.getParcelsByCourier(id));
        if (couriersParcels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(couriersParcels);
    }

    @PatchMapping("/{id}")
    public GetCourierResponse patchCourierById(@PathVariable Long id, @RequestBody CreateCourierRequest request) {
        Courier courier = courierMapper.dtoToCourier(request);
        Courier updatedCourier = courierService.patchCourierById(id, courier);
        return courierMapper.courierToDto(updatedCourier);
    }

    @PostMapping
    public GetCourierResponse createCourier(@RequestBody CreateCourierRequest request) {
        Courier courier = courierMapper.dtoToCourier(request);
        Courier savedCourier = courierService.addCourier(courier);
        return courierMapper.courierToDto(savedCourier);
    }

//    @DeleteMapping("/{id}")
//    public void deleteCourierById(@PathVariable Long id) {
//        courierService.deleteCourierById(id);
//    }

}
