package com.jcaa.usersmanagement.application.port.in;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface CreateVehiculoUseCase {
    VehiculoModel create(String vin, BigDecimal precioBase, String moneda);
}
