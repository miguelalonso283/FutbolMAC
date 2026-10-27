package org.example.futbolmacjavafx.dao;


import org.example.futbolmacjavafx.modelo.Equipo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try {
            Connection conexion = conectar();
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO equipos (id_Equipo, name_Equipo,"+
                    "siglas, ciudad, estadio, liga)" +
                    "VALUES (0,'"+ equipo.getNombreEquipo()+"','"+
                    equipo.getSiglas()+"','"+
                    equipo.getCiudad()+"','"+
                    equipo.getEstadio()+"','"+
                    equipo.getLiga()+"')";
            resultado = st.executeUpdate(sql);
            desconectar();
        } catch (SQLException e) {
            System.out.println((e.getMessage()));
        }
        return resultado;
    }
}
