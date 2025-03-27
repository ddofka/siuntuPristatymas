package org.codeacademy.siuntupristatymas.controller;

import lombok.RequiredArgsConstructor;
import org.codeacademy.siuntupristatymas.dto.CreateCourierRequest;
import org.codeacademy.siuntupristatymas.dto.CreateParcelRequest;
import org.codeacademy.siuntupristatymas.dto.GetCourierResponse;
import org.codeacademy.siuntupristatymas.dto.GetParcelResponse;
import org.codeacademy.siuntupristatymas.entity.Courier;
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
    public GetParcelResponse patchParcelById(@PathVariable Long id, @RequestBody CreateParcelRequest request) {
        Parcel parcel = parcelMapper.dtoToParcel(request);
        Parcel updatedParcel = parcelService.patchParcelById(id, parcel);
        return parcelMapper.parcelToDto(updatedParcel);
    }

    @PostMapping
    public GetParcelResponse createParcel(@RequestBody CreateParcelRequest request) {
        Parcel parcel = parcelMapper.dtoToParcel(request);
        Parcel savedParcel = parcelService.addParcel(parcel);
        return parcelMapper.parcelToDto(savedParcel);
    }
}
