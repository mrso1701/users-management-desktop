package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.VehiculoNoDisponibleException;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;
import com.jcaa.usersmanagement.domain.valueobject.Money;

public class VehiculoModel {
    private final ChassisVIN vin;
    private Money precioBase;
    private String estado;

    public VehiculoModel(ChassisVIN vin, Money precioBase) {
        if (vin == null || precioBase == null) {
            throw new IllegalArgumentException("El VIN y el precio base son obligatorios.");
        }
        this.vin = vin;
        this.precioBase = precioBase;
        this.estado = "DISPONIBLE";
    }

    public VehiculoModel(ChassisVIN vin, Money precioBase, String estado) {
        if (vin == null || precioBase == null) {
            throw new IllegalArgumentException("El VIN y el precio base son obligatorios.");
        }
        this.vin = vin;
        this.precioBase = precioBase;
        this.estado = (estado != null) ? estado : "DISPONIBLE";
    }

    public void marcarComoVendido() {
        if ("VENDIDO".equals(this.estado)) {
            throw new VehiculoNoDisponibleException("El vehículo con VIN " + vin.getValue() + " ya ha sido vendido.");
        }
        this.estado = "VENDIDO";
    }

    public void actualizarPrecio(Money nuevoPrecio) {
        if (nuevoPrecio == null) {
            throw new IllegalArgumentException("El nuevo precio no puede ser nulo.");
        }
        this.precioBase = nuevoPrecio;
    }

    public ChassisVIN getVin() {
        return vin;
    }

    public Money getPrecioBase() {
        return precioBase;
    }

    public String getEstado() {
        return estado;
    }
}
