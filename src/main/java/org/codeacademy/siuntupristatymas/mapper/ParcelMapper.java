package org.codeacademy.siuntupristatymas.mapper;

import org.codeacademy.siuntupristatymas.dto.CreateParcelRequest;
import org.codeacademy.siuntupristatymas.dto.GetCourierParcelsResponse;
import org.codeacademy.siuntupristatymas.dto.GetParcelResponse;
import org.codeacademy.siuntupristatymas.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

    GetParcelResponse parcelToDto(Parcel parcel);

    List<GetParcelResponse> parcelListToDto(List<Parcel> parcels);

    List<GetCourierParcelsResponse> courierParcelListToDto(List<Parcel> parcels);

    Parcel dtoToParcel(CreateParcelRequest request);

}
