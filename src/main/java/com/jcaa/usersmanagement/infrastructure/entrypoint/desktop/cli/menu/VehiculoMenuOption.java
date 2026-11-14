package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;


public enum VehiculoMenuOption {
    CREATE("1", "Registrar nuevo vehiculo"),
    FIND_BY_VIN("2", "Buscar vehiculo por VIN"),
    LIST_ALL("3", "Listar todos los vehiculos"),
    MARK_AS_SOLD("4", "Marcar vehiculo como VENDIDO"),
    DELETE("5", "Eliminar vehiculo"),
    BACK("6", "Volver al menu principal");

    private final String code;
    private final String description;

    VehiculoMenuOption(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static VehiculoMenuOption fromCode(final String code) {
        if (code == null) {
            return null;
        }
        for (final VehiculoMenuOption option : values()) {
            if (option.code.equalsIgnoreCase(code.trim())) {
                return option;
            }
        }
        return null;
    }
}
