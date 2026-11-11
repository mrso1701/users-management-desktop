package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllVehiculosPort;
import com.jcaa.usersmanagement.application.port.out.GetVehiculoByVinPort;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.ChassisVIN;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.VehiculoMapper;

public class PostgresVehiculoRepositoryAdapter implements
        SaveVehiculoPort,
        GetVehiculoByVinPort,
        GetAllVehiculosPort,
        UpdateVehiculoPort,
        DeleteVehiculoPort {

    private final DatabaseConfig config;

    public PostgresVehiculoRepositoryAdapter(DatabaseConfig config) {
        this.config = config;
    }

    private Connection getConnection() {
        return DatabaseConnectionFactory.createConnection(config);
    }

    @Override
    public VehiculoModel save(VehiculoModel vehiculo) {
        VehiculoEntity entity = VehiculoMapper.toEntity(vehiculo);
        String sql = "INSERT INTO vehiculos (numero_bastidor, id_modelo, ubicacion_actual, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getNumeroBastidor());
            stmt.setString(2, entity.getIdModelo());
            stmt.setString(3, entity.getUbicacionActual());
            stmt.setString(4, entity.getEstado());
            stmt.executeUpdate();

            return vehiculo;
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar vehículo en PostgreSQL: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<VehiculoModel> getByVin(ChassisVIN vin) {
        String sql = "SELECT v.numero_bastidor, v.id_modelo, v.ubicacion_actual, v.estado, m.precio_base " +
                "FROM vehiculos v " +
                "LEFT JOIN modelos m ON v.id_modelo = m.id_modelo " +
                "WHERE v.numero_bastidor = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin.getValue());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    VehiculoEntity entity = new VehiculoEntity(
                            rs.getString("numero_bastidor"),
                            rs.getString("id_modelo"),
                            rs.getString("ubicacion_actual"),
                            rs.getString("estado"),
                            rs.getBigDecimal("precio_base"));
                    return Optional.ofNullable(VehiculoMapper.toDomain(entity));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar vehículo por VIN: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<VehiculoModel> getAll() {
        List<VehiculoModel> lista = new ArrayList<>();
        String sql = "SELECT v.numero_bastidor, v.id_modelo, v.ubicacion_actual, v.estado, m.precio_base " +
                "FROM vehiculos v " +
                "LEFT JOIN modelos m ON v.id_modelo = m.id_modelo";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VehiculoEntity entity = new VehiculoEntity(
                        rs.getString("numero_bastidor"),
                        rs.getString("id_modelo"),
                        rs.getString("ubicacion_actual"),
                        rs.getString("estado"),
                        rs.getBigDecimal("precio_base"));
                lista.add(VehiculoMapper.toDomain(entity));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la lista de vehículos: " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public VehiculoModel update(VehiculoModel vehiculo) {
        String sql = "UPDATE vehiculos SET estado = ? WHERE numero_bastidor = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getEstado());
            stmt.setString(2, vehiculo.getVin().getValue());
            stmt.executeUpdate();

            return vehiculo;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estado del vehículo: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteByVin(ChassisVIN vin) {
        String sql = "DELETE FROM vehiculos WHERE numero_bastidor = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin.getValue());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar vehículo por VIN: " + e.getMessage(), e);
        }
    }
}
