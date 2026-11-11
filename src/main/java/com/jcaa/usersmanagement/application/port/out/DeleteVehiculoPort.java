package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;

public interface DeleteVehiculoPort {
    boolean deleteByVin(ChassisVIN vin);
}
