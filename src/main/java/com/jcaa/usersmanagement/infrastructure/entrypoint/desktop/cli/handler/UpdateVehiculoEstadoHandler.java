package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;


public class UpdateVehiculoEstadoHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    public UpdateVehiculoEstadoHandler(VehiculoController vehiculoController,ConsoleIO console, VehiculoResponsePrinter printer) {
        this.console = console;
        this.printer = printer;
        this.vehiculoController = vehiculoController;
    }

    @Override
    public void handle() {
        try {
            final String vin = console.readRequired("Ingrese el VIN del vehiculo vendido: ");
            final var response = vehiculoController.marcarComoVendido(vin);
            console.println("Vehiculo actualizado a VENDIDO.");
            printer.print(response);
        } catch (Exception e) {
            console.println("Error: " + e.getMessage());
        }
    }
}
