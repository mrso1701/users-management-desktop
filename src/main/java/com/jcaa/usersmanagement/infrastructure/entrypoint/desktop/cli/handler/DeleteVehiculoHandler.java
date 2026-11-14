package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;


public class DeleteVehiculoHandler implements OperationHandler {
    private final VehiculoController vehiculoController;
    private final ConsoleIO console;

    public DeleteVehiculoHandler(VehiculoController vehiculoController, ConsoleIO console) {
        this.console = console;
        this.vehiculoController = vehiculoController;
    }


    @Override
    public void handle() {
        final String vin = console.readRequired("Ingrese el VIN del vehiculo a eliminar: ");
        if (vehiculoController.eliminarPorVin(vin)) {
            console.println("Vehiculo eliminado correctamente.");
        } else {
            console.println("No se pudo eliminar el vehiculo.");
        }
    }
}
