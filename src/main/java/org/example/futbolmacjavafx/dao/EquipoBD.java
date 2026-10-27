package org.example.futbolmacjavafx.dao;


import org.example.futbolmacjavafx.modelo.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.futbolmacjavafx.config.Conexion.conectar;
import static org.example.futbolmacjavafx.config.Conexion.desconectar;


public class EquipoBD {
    public static List<Equipo> consultarEquipos() {
        List<Equipo>equipos = new ArrayList<>();
        ResultSet rs;
        try {
            Connection conexion = conectar();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM  equipos";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getInt(1));
                equipo.setNombreEquipo(rs.getString(2));
                equipo.setSiglas(rs.getString(3));
                equipo.setCiudad(rs.getString(4));
                equipo.setEstadio(rs.getString(5));
                equipo.setLiga(rs.getString(6));
                equipos.add(equipo);
            }
            desconectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipos;
    }

    public static int insertarEquipo(Equipo equipo) {
        int resultado = 0;
        String sql = "INSERT INTO equipos (nombre_equipo, siglas, ciudad, estadio, liga) VALUES (?,?,?,?,?)";
        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, equipo.getNombreEquipo());
            ps.setString(2, equipo.getSiglas());
            ps.setString(3, equipo.getCiudad());
            ps.setString(4, equipo.getEstadio());
            ps.setString(5, equipo.getLiga());
            resultado = ps.executeUpdate();
            desconectar();
        } catch (SQLException e) {
            System.out.println((e.getMessage()));
        }
        return resultado;
    }
}
