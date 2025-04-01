package org.codeacademy.siuntupristatymas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.dto.CreateCourierRequest;
import org.codeacademy.siuntupristatymas.dto.GetCourierParcelsResponse;
import org.codeacademy.siuntupristatymas.dto.GetCourierResponse;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.codeacademy.siuntupristatymas.mapper.CourierMapper;
import org.codeacademy.siuntupristatymas.mapper.ParcelMapper;
import org.codeacademy.siuntupristatymas.service.CourierService;
import org.codeacademy.siuntupristatymas.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/couriers")
@RestController
public class CourierController {

    private final CourierService courierService;
    private final ParcelService parcelService;
    private final CourierMapper courierMapper;
    private final ParcelMapper parcelMapper;

    @GetMapping
    public ResponseEntity<List<GetCourierResponse>> getAllCouriers() {
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

    @GetMapping("/{id}/parcels")
    public ResponseEntity<List<GetCourierParcelsResponse>> getAllCouriersParcel(@PathVariable Long id) {
        List<GetCourierParcelsResponse> couriersParcels =
            parcelMapper.courierParcelListToDto(parcelService.getParcelsByCourier(id));
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

    @Operation(summary = "Add a new courier", description = "Creates a new courier and returns the created entity.")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PostMapping
    public ResponseEntity<GetCourierResponse> createCourier(@RequestBody CreateCourierRequest request) {
        Courier courier = courierMapper.dtoToCourier(request);
        Courier savedCourier = courierService.addCourier(courier);
        GetCourierResponse response = courierMapper.courierToDto(savedCourier);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
