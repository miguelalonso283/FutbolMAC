package org.example.futbolmacjavafx.dao;

import org.example.futbolmacjavafx.modelo.Equipo;
import org.example.futbolmacjavafx.modelo.Jugador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.futbolmacjavafx.config.Conexion.conectar;
import static org.example.futbolmacjavafx.config.Conexion.desconectar;

public class JugadorBD {
    public static List<Jugador> consultarJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        ResultSet rs = null;
        try {
            Connection conexion = conectar();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM jugadores";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId_jugador(rs.getInt(1));
                jugador.setNombreJugador(rs.getString(2));
                jugador.setApellidos(rs.getString(3));
                jugador.setFechaNacimiento(rs.getDate(4));
                jugador.setNacionalidad(rs.getString(5));
                jugador.setPosicion(rs.getString(6));
                jugador.setDorsal(rs.getString(7));
                jugador.setEquipo(new Equipo(rs.getInt(9),
                        rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13),
                        rs.getString(14)));
                jugadores.add(jugador);
            }
            desconectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }
}
