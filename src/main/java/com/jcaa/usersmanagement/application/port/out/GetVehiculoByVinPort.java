package com.jcaa.usersmanagement.application.port.out;

import java.util.Optional;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;

public interface GetVehiculoByVinPort {
    Optional<VehiculoModel> getByVin(ChassisVIN vin);
}
