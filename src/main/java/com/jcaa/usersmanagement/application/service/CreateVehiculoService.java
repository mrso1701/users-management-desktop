package com.jcaa.usersmanagement.application.service;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetVehiculoByVinPort;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;
import com.jcaa.usersmanagement.domain.valueobject.Money;

public class CreateVehiculoService implements CreateVehiculoUseCase {
    private final SaveVehiculoPort saveVehiculoPort;
    private final GetVehiculoByVinPort getVehiculoByVinPort;

    public CreateVehiculoService(SaveVehiculoPort saveVehiculoPort, GetVehiculoByVinPort getVehiculoByVinPort) {
        this.saveVehiculoPort = saveVehiculoPort;
        this.getVehiculoByVinPort = getVehiculoByVinPort;
    }

    @Override
    public VehiculoModel create(String vin, BigDecimal precioBase, String moneda) {
        ChassisVIN chassisVin = new ChassisVIN(vin);
        if (getVehiculoByVinPort.getByVin(chassisVin).isPresent()) {
            throw new IllegalArgumentException("Ya existe un vehículo registrado con el VIN: " + vin);
        }
        Money precio = new Money(precioBase, moneda);
        VehiculoModel nuevoVehiculo = new VehiculoModel(chassisVin, precio);
        return saveVehiculoPort.save(nuevoVehiculo);
    }
}
