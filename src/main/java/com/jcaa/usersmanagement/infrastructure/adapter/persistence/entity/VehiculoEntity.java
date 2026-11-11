package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;

public class VehiculoEntity {
    private String numeroBastidor;
    private String idModelo;
    private String ubicacionActual;
    private String estado;
    private BigDecimal precioBase;

    public VehiculoEntity() {
    }

    public VehiculoEntity(String numeroBastidor, String idModelo, String ubicacionActual, String estado,
            BigDecimal precioBase) {
        this.numeroBastidor = numeroBastidor;
        this.idModelo = idModelo;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
        this.precioBase = precioBase;
    }

    public String getNumeroBastidor() {
        return numeroBastidor;
    }

    public void setNumeroBastidor(String numeroBastidor) {
        this.numeroBastidor = numeroBastidor;
    }

    public String getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(String idModelo) {
        this.idModelo = idModelo;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }
}
