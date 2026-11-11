package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public class ChassisVIN {
    private final String value;

    public ChassisVIN(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El VIN no puede estar vacío.");
        }
        this.value = value.trim().toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ChassisVIN that = (ChassisVIN) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
