package org.codeacademy.siuntupristatymas.mapper;

import org.codeacademy.siuntupristatymas.dto.CreateCourierRequest;
import org.codeacademy.siuntupristatymas.dto.GetCourierResponse;
import org.codeacademy.siuntupristatymas.entity.Courier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    GetCourierResponse courierToDto(Courier courier);

    List<GetCourierResponse> courierListToDto(List<Courier> couriers);

    Courier dtoToCourier(CreateCourierRequest request);

}
