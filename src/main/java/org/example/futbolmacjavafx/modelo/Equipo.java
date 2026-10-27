package org.example.futbolmacjavafx.modelo;

public class Equipo {
    private int idEquipo;
    private String nombreEquipo;
    private String siglas;
    private String ciudad;
    private String estadio;
    private String liga;

    public Equipo() {
    }

    public Equipo(int idEquipo, String nombreEquipo, String siglas, String ciudad, String estadio, String liga) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.siglas = siglas;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.liga = liga;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", siglas='" + siglas + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estadio='" + estadio + '\'' +
                ", liga='" + liga + '\'' +
                '}';
    }
}
