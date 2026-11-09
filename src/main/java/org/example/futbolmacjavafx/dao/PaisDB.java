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
}
