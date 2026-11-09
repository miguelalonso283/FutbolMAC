package org.example.futbolmacjavafx.dao;

import org.example.futbolmacjavafx.modelo.Equipo;
import org.example.futbolmacjavafx.modelo.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.futbolmacjavafx.config.Conexion.conectar;
import static org.example.futbolmacjavafx.config.Conexion.desconectar;

public class JugadorBD {
    public static List<Jugador> consultarJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT j.id_jugador, j.nombreJugador, j.apellidos, j.fech_nacimiento, " +
                "j.posicion, j.dorsal, " +
                "e.id_equipo, e.nombre_equipo, e.siglas, e.ciudad, e.estadio, e.liga, " +
                "GROUP_CONCAT(p.nombre_pais ORDER BY p.nombre_pais SEPARATOR ', ') AS nacionalidades " +
                "FROM jugadores j " +
                "LEFT JOIN equipos e ON j.equipo = e.id_equipo " +
                "LEFT JOIN nacionalidad n ON n.jugador_id = j.id_jugador " +
                "LEFT JOIN pais p ON p.id_pais = n.pais_id " +
                "GROUP BY j.id_jugador, e.id_equipo";
        try (Connection conexion = conectar();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId_jugador(rs.getInt("id_jugador"));
                jugador.setNombreJugador(rs.getString("nombreJugador"));
                jugador.setApellidos(rs.getString("apellidos"));
                jugador.setFechaNacimiento(rs.getDate("fech_nacimiento"));
                jugador.setPosicion(rs.getString("posicion"));
                jugador.setDorsal(rs.getString("dorsal"));
                jugador.setNacionalidades(rs.getString("nacionalidades")); // null si no tiene

                int idEquipo = rs.getInt("id_equipo");
                if (!rs.wasNull()) {   // jugador sin equipo -> equipo = null
                    jugador.setEquipo(new Equipo(idEquipo, rs.getString("nombre_equipo"),
                            rs.getString("siglas"), rs.getString("ciudad"),
                            rs.getString("estadio"), rs.getString("liga")));
                }
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

    public static int insertarJugador(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombreJugador, apellidos, fech_nacimiento, posicion, dorsal, equipo) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, jugador.getNombreJugador());
            ps.setString(2, jugador.getApellidos());
            ps.setDate(3, jugador.getFechaNacimiento() == null ? null
                    : new java.sql.Date(jugador.getFechaNacimiento().getTime()));
            ps.setString(4, jugador.getPosicion());
            ps.setString(5, jugador.getDorsal());
            if (jugador.getEquipo() == null) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, jugador.getEquipo().getIdEquipo());
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
