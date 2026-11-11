package com.jcaa.usersmanagement.application.port.in;

import java.util.List;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface GetAllVehiculosUseCase {
    List<VehiculoModel> getAll();
}
