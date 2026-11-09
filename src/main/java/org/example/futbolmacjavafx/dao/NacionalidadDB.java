package org.example.futbolmacjavafx.dao;

import org.example.futbolmacjavafx.modelo.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.futbolmacjavafx.config.Conexion.conectar;

public class NacionalidadDB {
    // Países de un jugador (el JOIN de la relación muchos a muchos)
    public static List<Pais> consultarPaisesDeJugador(int idJugador) {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT p.id_pais, p.nombre_pais FROM pais p " +
                "INNER JOIN nacionalidad n ON n.pais_id = p.id_pais " +
                "WHERE n.jugador_id = ? ORDER BY p.nombre_pais";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idJugador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    paises.add(new Pais(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paises;
    }

    public static int insertarNacionalidad(int idJugador, int idPais) {
        String sql = "INSERT INTO nacionalidad (jugador_id, pais_id) VALUES (?, ?)";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idJugador);
            ps.setInt(2, idPais);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // p. ej. la pareja ya existe
            return 0;
        }
    }

    public static int eliminarNacionalidad(int idJugador, int idPais) {
        String sql = "DELETE FROM nacionalidad WHERE jugador_id = ? AND pais_id = ?";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idJugador);
            ps.setInt(2, idPais);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
