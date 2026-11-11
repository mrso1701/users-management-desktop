package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListVehiculosHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final VehiculoResponsePrinter printer;

    @Override
    public void handle() {
        printer.printList(vehiculoController.obtenerTodos());
    }
}
