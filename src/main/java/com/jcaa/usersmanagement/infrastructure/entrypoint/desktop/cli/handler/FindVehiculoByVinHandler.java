package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;


public class FindVehiculoByVinHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    public FindVehiculoByVinHandler(VehiculoController vehiculoController, ConsoleIO console, VehiculoResponsePrinter printer) {
        this.console = console;
        this.printer = printer;
        this.vehiculoController = vehiculoController;
    }


    @Override
    public void handle() {
        final String vin = console.readRequired("Ingrese el VIN a buscar: ");
        vehiculoController.buscarPorVin(vin).ifPresentOrElse(
                printer::print,
                () -> console.println("No se encontro ningun vehiculo con VIN: " + vin)
        );
    }
}
