package com.jcaa.usersmanagement.application.port.in;

import java.util.Optional;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface GetVehiculoByVinUseCase {
    Optional<VehiculoModel> getByVin(String vin);
}
