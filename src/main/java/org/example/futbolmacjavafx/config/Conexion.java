package org.example.futbolmacjavafx.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/futbolmac";
    private static String user = "root";
    private static String password = "";
    private static String driver ="com.mysql.cj.jdbc.Driver";

    private static Connection jdbcConexion=null;

    private Conexion() {
        try {
            if ((jdbcConexion==null)||jdbcConexion.isClosed()) {
                Class.forName(driver);
                jdbcConexion = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    /* Patrón singleton ---- Patrón de diseño creacional que nos permite asegurarnos que
     la clase tenga una única instancia y un único punto de acceso global */
    public static Connection conectar() {
        new Conexion();
        return jdbcConexion;
    }

    public static void desconectar() {
        try {
            if (!jdbcConexion.isClosed() && jdbcConexion!=null) {//Cambio: Antes-->(||) Ahora-->(&&)
                jdbcConexion.close(); //Cambio: Antes-->(jdbcConexion==null) Ahora-->(jdbcConexion.close())
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
