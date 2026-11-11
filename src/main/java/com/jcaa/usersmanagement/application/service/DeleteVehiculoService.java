package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;

public class DeleteVehiculoService implements DeleteVehiculoUseCase {
    private final DeleteVehiculoPort deleteVehiculoPort;

    public DeleteVehiculoService(DeleteVehiculoPort deleteVehiculoPort) {
        this.deleteVehiculoPort = deleteVehiculoPort;
    }

    @Override
    public boolean deleteByVin(String vin) {
        return deleteVehiculoPort.deleteByVin(new ChassisVIN(vin));
    }
}
