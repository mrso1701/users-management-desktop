package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import java.util.EnumMap;
import java.util.Map;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindVehiculoByVinHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListVehiculosHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateVehiculoEstadoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.VehiculoMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;

public class VehiculoManagementCli {
    private static final String BANNER = """
            ==============================================
            |        Sistema de Gestion de Vehiculos     |
            ==============================================
            """;

    private static final String MENU_BORDER = "===============================================";

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;

    public VehiculoManagementCli(
            final VehiculoController vehiculoController,
            final ConsoleIO console) {
        this.vehiculoController = vehiculoController;
        this.console = console;
    }

    public void start() {
        console.println(BANNER);
        final VehiculoResponsePrinter printer = new VehiculoResponsePrinter(console);
        final Map<VehiculoMenuOption, OperationHandler> handlers = buildHandlers(printer);
        runLoop(handlers);
    }

    private Map<VehiculoMenuOption, OperationHandler> buildHandlers(VehiculoResponsePrinter printer) {
        Map<VehiculoMenuOption, OperationHandler> map = new EnumMap<>(VehiculoMenuOption.class);
        map.put(VehiculoMenuOption.CREATE, new CreateVehiculoHandler(vehiculoController, console, printer));
        map.put(VehiculoMenuOption.FIND_BY_VIN, new FindVehiculoByVinHandler(vehiculoController, console, printer));
        map.put(VehiculoMenuOption.LIST_ALL, new ListVehiculosHandler(vehiculoController, printer));
        map.put(VehiculoMenuOption.MARK_AS_SOLD, new UpdateVehiculoEstadoHandler(vehiculoController, console, printer));
        map.put(VehiculoMenuOption.DELETE, new DeleteVehiculoHandler(vehiculoController, console));
        return map;
    }

    private void runLoop(final Map<VehiculoMenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final String input = console.readRequired("Seleccione una opcion: ");
            final VehiculoMenuOption option = VehiculoMenuOption.fromCode(input);

            if (option == null) {
                console.println("Opcion no valida. Intente nuevamente.");
                continue;
            }

            if (option == VehiculoMenuOption.BACK) {
                running = false;
                continue;
            }

            final OperationHandler handler = handlers.get(option);
            if (handler != null) {
                try {
                    handler.handle();
                } catch (Exception e) {
                    console.println("Error al ejecutar la operacion: " + e.getMessage());
                }
            }
        }
    }

    private void printMenu() {
        console.println(MENU_BORDER);
        for (VehiculoMenuOption opt : VehiculoMenuOption.values()) {
            console.println(opt.getCode() + ". " + opt.getDescription());
        }
        console.println(MENU_BORDER);
    }
}
