package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record CreateVehiculoRequest(
        String vin,
        BigDecimal precioBase,
        String moneda) {
}
