package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindVehiculoByVinHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    @Override
    public void handle() {
        final String vin = console.readRequired("Ingrese el VIN a buscar: ");
        vehiculoController.buscarPorVin(vin).ifPresentOrElse(
                printer::print,
                () -> console.println("No se encontro ningun vehiculo con VIN: " + vin)
        );
    }
}
