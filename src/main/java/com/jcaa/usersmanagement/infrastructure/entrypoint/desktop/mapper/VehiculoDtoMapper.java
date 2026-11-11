package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;

public class VehiculoDtoMapper {
    public static VehiculoResponse toResponse(VehiculoModel model) {
        if (model == null)
            return null;
        return new VehiculoResponse(
                model.getVin().getValue(),
                model.getPrecioBase().getAmount(),
                model.getPrecioBase().getCurrency(),
                model.getEstado());
    }
}
