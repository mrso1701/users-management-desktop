package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import java.util.List;
import java.util.Optional;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByVinUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.VehiculoDtoMapper;

public class VehiculoController {
    private final CreateVehiculoUseCase createVehiculoUseCase;
    private final GetVehiculoByVinUseCase getVehiculoByVinUseCase;
    private final GetAllVehiculosUseCase getAllVehiculosUseCase;
    private final UpdateVehiculoUseCase updateVehiculoUseCase;
    private final DeleteVehiculoUseCase deleteVehiculoUseCase;

    public VehiculoController(
            CreateVehiculoUseCase createVehiculoUseCase,
            GetVehiculoByVinUseCase getVehiculoByVinUseCase,
            GetAllVehiculosUseCase getAllVehiculosUseCase,
            UpdateVehiculoUseCase updateVehiculoUseCase,
            DeleteVehiculoUseCase deleteVehiculoUseCase) {
        this.createVehiculoUseCase = createVehiculoUseCase;
        this.getVehiculoByVinUseCase = getVehiculoByVinUseCase;
        this.getAllVehiculosUseCase = getAllVehiculosUseCase;
        this.updateVehiculoUseCase = updateVehiculoUseCase;
        this.deleteVehiculoUseCase = deleteVehiculoUseCase;
    }

    public VehiculoResponse crearVehiculo(CreateVehiculoRequest request) {
        VehiculoModel modelo = createVehiculoUseCase.create(request.vin(), request.precioBase(), request.moneda());
        return VehiculoDtoMapper.toResponse(modelo);
    }

    public Optional<VehiculoResponse> buscarPorVin(String vin) {
        return getVehiculoByVinUseCase.getByVin(vin).map(VehiculoDtoMapper::toResponse);
    }

    public List<VehiculoResponse> obtenerTodos() {
        return getAllVehiculosUseCase.getAll().stream()
                .map(VehiculoDtoMapper::toResponse)
                .toList();
    }

    public VehiculoResponse marcarComoVendido(String vin) {
        VehiculoModel modelo = updateVehiculoUseCase.marcarComoVendido(vin);
        return VehiculoDtoMapper.toResponse(modelo);
    }

    public boolean eliminarPorVin(String vin) {
        return deleteVehiculoUseCase.deleteByVin(vin);
    }
}
