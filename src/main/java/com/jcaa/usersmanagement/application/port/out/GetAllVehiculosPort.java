package com.jcaa.usersmanagement.application.port.out;

import java.util.List;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface GetAllVehiculosPort {
    List<VehiculoModel> getAll();
}
