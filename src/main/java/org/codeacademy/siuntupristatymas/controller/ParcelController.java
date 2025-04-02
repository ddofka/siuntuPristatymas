package org.codeacademy.siuntupristatymas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeacademy.siuntupristatymas.dto.CreateParcelRequest;
import org.codeacademy.siuntupristatymas.dto.GetParcelResponse;
import org.codeacademy.siuntupristatymas.dto.UpdateParcelCourier;
import org.codeacademy.siuntupristatymas.dto.UpdateParcelStatus;
import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.codeacademy.siuntupristatymas.mapper.ParcelMapper;
import org.codeacademy.siuntupristatymas.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/parcels")
@RestController
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelMapper parcelMapper;

    @Operation(summary = "Get all parcels", description = "Retrieves a list of all parcels.")
    @ApiResponse(responseCode = "200", description = "List of parcels retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No parcels found")
    @GetMapping
    public ResponseEntity<List<GetParcelResponse>> getAllParcels(@RequestParam(required = false) Long id) {
        List<GetParcelResponse> parcels = parcelMapper.parcelListToDto(parcelService.getAllParcels());
        if (parcels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(parcels);
    }

    @Operation(summary = "Get courier by id", description = "Retrieves a courier by id.")
    @ApiResponse(responseCode = "200", description = "Courier retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No courier found")
    @GetMapping("/{id}")
    public ResponseEntity<GetParcelResponse> getParcelById(@PathVariable Long id) {
        Parcel parcelById = parcelService.getParcelById(id);
        GetParcelResponse response = parcelMapper.parcelToDto(parcelById);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete the parcel", description = "Deletes the parcel by ID.")
    @ApiResponse(responseCode = "204", description = "Parcel deleted successfully")
    @ApiResponse(responseCode = "404", description = "Parcel not found")
    @DeleteMapping("/{id}")
    public void deleteParcelById(@PathVariable Long id) {
        parcelService.deleteParcelById(id);
    }

    @Operation(summary = "Update parcel status", description = "Update parcel status.")
    @ApiResponse(responseCode = "200", description = "Parcel updated successfully")
    @ApiResponse(responseCode = "404", description = "Parcel not found")
    @PatchMapping("/{id}")
    public GetParcelResponse patchParcelById(@PathVariable Long id, @RequestBody UpdateParcelStatus request) {
        Parcel savedParcel = parcelService.updateParcelStatus(id, request.status());
        return parcelMapper.parcelToDto(savedParcel);
    }

    @Operation(summary = "Assign parcel courier", description = "Assign parcel a courier id.")
    @ApiResponse(responseCode = "200", description = "Assigned successfully")
    @ApiResponse(responseCode = "404", description = "Parcel not found")
    @PatchMapping("/{id}/courier")
    public GetParcelResponse assignCourierToParcelById(@PathVariable Long id, @RequestBody UpdateParcelCourier request) {
        Parcel savedParcel = parcelService.updateParcelCourierById(id, request.courierId());
        return parcelMapper.parcelToDto(savedParcel);
    }

    @Operation(summary = "Add a new parcel", description = "Creates a new parcel and returns the created entity.")
    @ApiResponse(responseCode = "201", description = "Parcel created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PostMapping
    public ResponseEntity<GetParcelResponse> createParcel(@RequestBody CreateParcelRequest request) {
        Parcel parcel = parcelMapper.dtoToParcel(request);
        Parcel savedParcel = parcelService.addParcel(parcel);
        GetParcelResponse response = parcelMapper.parcelToDto(savedParcel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
