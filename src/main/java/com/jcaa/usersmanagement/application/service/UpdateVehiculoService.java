package com.jcaa.usersmanagement.application.service;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetVehiculoByVinPort;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.domain.exception.VehiculoNoEncontradoException;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;
import com.jcaa.usersmanagement.domain.valueobject.Money;

public class UpdateVehiculoService implements UpdateVehiculoUseCase {
    private final GetVehiculoByVinPort getVehiculoByVinPort;
    private final UpdateVehiculoPort updateVehiculoPort;

    public UpdateVehiculoService(GetVehiculoByVinPort getVehiculoByVinPort, UpdateVehiculoPort updateVehiculoPort) {
        this.getVehiculoByVinPort = getVehiculoByVinPort;
        this.updateVehiculoPort = updateVehiculoPort;
    }

    @Override
    public VehiculoModel updatePrecio(String vin, BigDecimal nuevoPrecio, String moneda) {
        ChassisVIN chassisVin = new ChassisVIN(vin);
        VehiculoModel vehiculo = getVehiculoByVinPort.getByVin(chassisVin)
                .orElseThrow(() -> new VehiculoNoEncontradoException("Vehículo no encontrado con VIN: " + vin));

        vehiculo.actualizarPrecio(new Money(nuevoPrecio, moneda));
        return updateVehiculoPort.update(vehiculo);
    }

    @Override
    public VehiculoModel marcarComoVendido(String vin) {
        ChassisVIN chassisVin = new ChassisVIN(vin);
        VehiculoModel vehiculo = getVehiculoByVinPort.getByVin(chassisVin)
                .orElseThrow(() -> new VehiculoNoEncontradoException("Vehículo no encontrado con VIN: " + vin));

        vehiculo.marcarComoVendido();
        return updateVehiculoPort.update(vehiculo);
    }
}
