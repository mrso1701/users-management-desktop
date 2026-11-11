package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record VehiculoResponse(
        String vin,
        BigDecimal precio,
        String moneda,
        String estado) {
}
