package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import java.math.BigDecimal;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateVehiculoRequest;

public class CreateVehiculoHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

   public CreateVehiculoHandler(
        final VehiculoController vehiculoController,
        final ConsoleIO console,
        final VehiculoResponsePrinter printer) {
    this.vehiculoController = vehiculoController;
    this.console = console;
    this.printer = printer;
}

    @Override
    public void handle() {
        try {
            final String vin = console.readRequired("Ingrese el VIN (17 caracteres): ");
            final String precioStr = console.readRequired("Ingrese el precio base: ");
            final BigDecimal precio = new BigDecimal(precioStr);

            final var response = vehiculoController.crearVehiculo(new CreateVehiculoRequest(vin, precio, "COP"));
            console.println("Vehiculo registrado exitosamente.");
            printer.print(response);
        } catch (Exception e) {
            console.println("Error: " + e.getMessage());
        }
    }
}
