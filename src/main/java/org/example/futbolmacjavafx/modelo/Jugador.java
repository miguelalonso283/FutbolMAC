package org.example.futbolmacjavafx.modelo;

import java.util.Date;

public class Jugador {
    private int id_jugador;
    private String nombreJugador;
    private String apellidos;
    private Date fechaNacimiento;
    private String nacionalidades;
    private String posicion;
    private String dorsal;
    private Equipo equipo;

    public Jugador() {}

    public Jugador(int id_jugador, String nombreJugador, String apellidos, Date fechaNacimiento,
                   String nacionalidades, String posicion, String dorsal, Equipo equipo) {
        this.id_jugador = id_jugador;
        this.nombreJugador = nombreJugador;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidades = nacionalidades;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.equipo = equipo;
    }

    public int getId_jugador() { return id_jugador; }
    public void setId_jugador(int id_jugador) { this.id_jugador = id_jugador; }
    public String getNombreJugador() { return nombreJugador; }
    public void setNombreJugador(String nombreJugador) { this.nombreJugador = nombreJugador; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getNacionalidades() { return nacionalidades; }
    public void setNacionalidades(String nacionalidades) { this.nacionalidades = nacionalidades; }
    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
    public String getDorsal() { return dorsal; }
    public void setDorsal(String dorsal) { this.dorsal = dorsal; }
    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    @Override
    public String toString() {
        return "Jugador{id_jugador=" + id_jugador + ", nombreJugador='" + nombreJugador + '\'' +
                ", apellidos='" + apellidos + '\'' + ", fechaNacimiento=" + fechaNacimiento +
                ", nacionalidades='" + nacionalidades + '\'' + ", posicion='" + posicion + '\'' +
                ", dorsal='" + dorsal + '\'' + ", equipo=" + equipo + '}';
    }
}
