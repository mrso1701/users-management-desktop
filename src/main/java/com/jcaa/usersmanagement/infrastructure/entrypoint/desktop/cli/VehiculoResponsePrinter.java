package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import java.util.List;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehiculoResponsePrinter {
    private final ConsoleIO console;

    public void print(final VehiculoResponse vehiculo) {
        console.println("\n--- DETALLE DEL VEHÍCULO ---");
        console.println("VIN: " + vehiculo.vin());
        console.println("Precio: $" + vehiculo.precio() + " " + vehiculo.moneda());
        console.println("Estado: " + vehiculo.estado());
    }

    public void printList(final List<VehiculoResponse> vehiculos) {
        if (vehiculos.isEmpty()) {
            console.println("No hay vehículos registrados en el sistema.");
            return;
        }
        console.println("\n--- LISTADO DE VEHÍCULOS ---");
        vehiculos.forEach(this::print);
    }
}
