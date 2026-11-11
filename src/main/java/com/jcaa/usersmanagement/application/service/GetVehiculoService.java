package com.jcaa.usersmanagement.application.service;

import java.util.List;
import java.util.Optional;

import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByVinUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllVehiculosPort;
import com.jcaa.usersmanagement.application.port.out.GetVehiculoByVinPort;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;

public class GetVehiculoService implements GetVehiculoByVinUseCase, GetAllVehiculosUseCase {
    private final GetVehiculoByVinPort getVehiculoByVinPort;
    private final GetAllVehiculosPort getAllVehiculosPort;

    public GetVehiculoService(GetVehiculoByVinPort getVehiculoByVinPort, GetAllVehiculosPort getAllVehiculosPort) {
        this.getVehiculoByVinPort = getVehiculoByVinPort;
        this.getAllVehiculosPort = getAllVehiculosPort;
    }

    @Override
    public Optional<VehiculoModel> getByVin(String vin) {
        return getVehiculoByVinPort.getByVin(new ChassisVIN(vin));
    }

    @Override
    public List<VehiculoModel> getAll() {
        return getAllVehiculosPort.getAll();
    }
}
