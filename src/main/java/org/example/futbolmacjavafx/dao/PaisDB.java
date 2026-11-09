package org.example.futbolmacjavafx.dao;

import org.example.futbolmacjavafx.modelo.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.futbolmacjavafx.config.Conexion.conectar;

public class PaisDB {
    public static List<Pais> consultarPaises() {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT id_pais, nombre_pais FROM pais ORDER BY nombre_pais";
        try (Connection conexion = conectar();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                paises.add(new Pais(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paises;
    }

    public static int insertarPais(Pais pais) {
        String sql = "INSERT INTO pais (nombre_pais) VALUES (?)";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public static int modificarPais(Pais pais) {
        String sql = "UPDATE pais SET nombre_pais = ? WHERE id_pais = ?";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            ps.setInt(2, pais.getIdPais());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static int eliminarPais(int idPais) {
        String sql = "DELETE FROM pais WHERE id_pais = ?";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPais);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
