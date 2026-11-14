package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;

import lombok.RequiredArgsConstructor;


public class ListVehiculosHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final VehiculoResponsePrinter printer;

    public ListVehiculosHandler(VehiculoController vehiculoController, VehiculoResponsePrinter printer) {
        this.vehiculoController = vehiculoController;
        this.printer = printer;
    }


    @Override
    public void handle() {
        printer.printList(vehiculoController.obtenerTodos());
    }
}
