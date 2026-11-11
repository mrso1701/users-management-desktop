package com.jcaa.usersmanagement.application.port.in;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface UpdateVehiculoUseCase {
    VehiculoModel updatePrecio(String vin, BigDecimal nuevoPrecio, String moneda);

    VehiculoModel marcarComoVendido(String vin);
}
