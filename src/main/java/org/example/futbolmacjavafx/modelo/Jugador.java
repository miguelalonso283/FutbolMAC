package org.example.futbolmacjavafx.modelo;

import java.util.Date;

public class Jugador {
    private int  id_jugador;
    private String nombreJugador;
    private String apellidos;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String posicion;
    private String dorsal;
    private Equipo equipo_id;
    private EquipoPais equipo_pais_id;

    public Jugador() {
    }

    public Jugador(int id_jugador, String nombreJugador, String apellidos, Date fechaNacimiento, String nacionalidad, String posicion, String dorsal, Equipo equipo_id, EquipoPais equipo_pais_id) {
        this.id_jugador = id_jugador;
        this.nombreJugador = nombreJugador;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.equipo_id = equipo_id;
        this.equipo_pais_id = equipo_pais_id;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public Equipo getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Equipo equipo_id) {
        this.equipo_id = equipo_id;
    }

    public EquipoPais getEquipo_pais_id() {
        return equipo_pais_id;
    }

    public void setEquipo_pais_id(EquipoPais equipo_pais_id) {
        this.equipo_pais_id = equipo_pais_id;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id_jugador=" + id_jugador +
                ", nombreJugador='" + nombreJugador + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", posicion='" + posicion + '\'' +
                ", dorsal='" + dorsal + '\'' +
                ", equipo_id=" + equipo_id +
                ", equipo_pais_id=" + equipo_pais_id +
                '}';
    }
}
