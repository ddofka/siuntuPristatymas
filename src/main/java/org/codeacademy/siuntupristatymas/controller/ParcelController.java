package org.codeacademy.siuntupristatymas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RequestMapping("/api/parcels")
@RestController
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelMapper parcelMapper;

    @GetMapping
    public ResponseEntity<List<GetParcelResponse>> getAllParcels(@RequestParam(required = false) Long id) {
        List<GetParcelResponse> parcels = parcelMapper.parcelListToDto(parcelService.getAllParcels());
        if (parcels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(parcels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetParcelResponse> getParcelById(@PathVariable Long id) {
        Parcel parcelById = parcelService.getParcelById(id);
        GetParcelResponse response = parcelMapper.parcelToDto(parcelById);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteParcelById(@PathVariable Long id) {
        parcelService.deleteParcelById(id);
    }

    @PatchMapping("/{id}")
    public GetParcelResponse patchParcelById(@PathVariable Long id, @RequestBody UpdateParcelStatus request) {
        Parcel savedParcel = parcelService.updateParcelStatus(id, request.status());
        return parcelMapper.parcelToDto(savedParcel);
    }

    @PatchMapping("/{id}/courier")
    public GetParcelResponse assignCourierToParcelById(@PathVariable Long id, @RequestBody UpdateParcelCourier request) {
        Parcel savedParcel = parcelService.updateParcelCourierById(id, request.courierId());
        return parcelMapper.parcelToDto(savedParcel);
    }

    @Operation(summary = "Add a new parcel", description = "Creates a new parcel and returns the created entity.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<GetParcelResponse> createParcel(@RequestBody CreateParcelRequest request) {
        Parcel parcel = parcelMapper.dtoToParcel(request);
        Parcel savedParcel = parcelService.addParcel(parcel);
        GetParcelResponse response = parcelMapper.parcelToDto(savedParcel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
