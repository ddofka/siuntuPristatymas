package org.codeacademy.siuntupristatymas.controller;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/couriers")
@RestController
public class CourierController {

    private final CourierService courierService;
    private final ParcelService parcelService;
    private final CourierMapper courierMapper;
    private final ParcelMapper parcelMapper;

    @Operation(summary = "Get all couriers", description = "Retrieves a list of all couriers.")
    @ApiResponse(responseCode = "200", description = "List of couriers retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No couriers found")
    @GetMapping
    public ResponseEntity<List<GetCourierResponse>> getAllCouriers(@RequestParam(required = false) String name) {
        if (name != null) {
            log.info("Get all couriers with name {}", name);
            List<GetCourierResponse> couriersByName = courierMapper.courierListToDto(courierService.getCouriersByName(name));
            return ResponseEntity.ok(couriersByName);
        }
        log.info("Get all couriers");
        List<GetCourierResponse> couriers = courierMapper.courierListToDto(courierService.getAllCouriers());
        if (couriers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(couriers);
    }

    @Operation(summary = "Get courier by id", description = "Retrieves a courier by id.")
    @ApiResponse(responseCode = "200", description = "Courier retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No courier found")
    @GetMapping("/{id}")
    public ResponseEntity<GetCourierResponse> getCourierById(@PathVariable Long id) {
        Courier courierById = courierService.getCourierById(id);
        GetCourierResponse response = courierMapper.courierToDto(courierById);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get courier parcels by id", description = "Retrieves all courier parcel by courier id.")
    @ApiResponse(responseCode = "200", description = "Courier parcels retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No courier parcels found")
    @GetMapping("/{id}/parcels")
    public ResponseEntity<List<GetCourierParcelsResponse>> getAllCouriersParcel(@PathVariable Long id) {
        List<GetCourierParcelsResponse> couriersParcels =
            parcelMapper.courierParcelListToDto(parcelService.getParcelsByCourier(id));
        if (couriersParcels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(couriersParcels);
    }

    @Operation(summary = "Update courier", description = "Update courier info.")
    @ApiResponse(responseCode = "200", description = "Courier updated successfully")
    @ApiResponse(responseCode = "404", description = "Courier not found")
    @PatchMapping("/{id}")
    public GetCourierResponse patchCourierById(@PathVariable Long id, @RequestBody CreateCourierRequest request) {
        log.info("Updating name: " + request.name());
        Courier courier = courierMapper.dtoToCourier(request);
        Courier updatedCourier = courierService.patchCourierById(id, courier);
        return courierMapper.courierToDto(updatedCourier);
    }

    @Operation(summary = "Add a new courier", description = "Creates a new courier and returns the created entity.")
    @ApiResponse(responseCode = "201", description = "Courier created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PostMapping
    public ResponseEntity<GetCourierResponse> createCourier(@RequestBody CreateCourierRequest request) {
        Courier courier = courierMapper.dtoToCourier(request);
        Courier savedCourier = courierService.addCourier(courier);
        GetCourierResponse response = courierMapper.courierToDto(savedCourier);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
