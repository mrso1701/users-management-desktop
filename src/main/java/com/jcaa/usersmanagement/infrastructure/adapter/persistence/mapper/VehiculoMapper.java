package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;
import com.jcaa.usersmanagement.domain.valueobject.Money;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;

public class VehiculoMapper {
    public static VehiculoModel toDomain(VehiculoEntity entity) {
        if (entity == null)
            return null;
        ChassisVIN vin = new ChassisVIN(entity.getNumeroBastidor());
        Money precio = new Money(entity.getPrecioBase() != null ? entity.getPrecioBase() : BigDecimal.ZERO, "COP");
        return new VehiculoModel(vin, precio, entity.getEstado());
    }

    public static VehiculoEntity toEntity(VehiculoModel domain) {
        if (domain == null)
            return null;
        return new VehiculoEntity(
                domain.getVin().getValue(),
                "MOD-001",
                "Patio Principal",
                domain.getEstado(),
                domain.getPrecioBase().getAmount());
    }
}
