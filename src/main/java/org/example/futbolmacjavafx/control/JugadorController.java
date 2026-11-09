package org.example.futbolmacjavafx.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import org.example.futbolmacjavafx.dao.EquipoBD;
import org.example.futbolmacjavafx.dao.JugadorBD;
import org.example.futbolmacjavafx.dao.NacionalidadDB;
import org.example.futbolmacjavafx.dao.PaisDB;
import org.example.futbolmacjavafx.modelo.Equipo;
import org.example.futbolmacjavafx.modelo.Jugador;
import org.example.futbolmacjavafx.modelo.Pais;

import java.util.Date;

public class JugadorController {

    @FXML private TableView<Jugador> tablaJugador;
    @FXML private TableColumn<Jugador, Integer> colIdJugador;
    @FXML private TableColumn<Jugador, String> colNombreJugador;
    @FXML private TableColumn<Jugador, String> colApellidos;
    @FXML private TableColumn<Jugador, Date> colFechaNacimiento;
    @FXML private TableColumn<Jugador, String> colNacionalidad;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, Integer> colDorsal;
    @FXML private TableColumn<Jugador, String> colEquipo;

    @FXML private TextField txtNombreJugador;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtFechaNacimiento;
    @FXML private TextField txtPosicion;
    @FXML private TextField txtDorsal;

    @FXML private ComboBox<Pais> cmbPais;
    @FXML private ComboBox<Equipo> cmbEquipo;
    @FXML private ListView<Pais> listaPaises;

    public void initialize(){
        colIdJugador.setCellValueFactory(new PropertyValueFactory<>("id_jugador"));
        colNombreJugador.setCellValueFactory(new PropertyValueFactory<>("nombreJugador"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colFechaNacimiento.setCellValueFactory(new  PropertyValueFactory<>("fechaNacimiento"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidades"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colDorsal.setCellValueFactory(new PropertyValueFactory<>("dorsal"));
        colEquipo.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getEquipo() == null ? "" : cell.getValue().getEquipo().getNombreEquipo()));
        cmbPais.setItems(FXCollections.observableArrayList(PaisDB.consultarPaises()));
        cmbEquipo.setItems(FXCollections.observableArrayList(EquipoBD.consultarEquipos()));
        cmbEquipo.setConverter(new StringConverter<Equipo>() {
            @Override
            public String toString(Equipo e) {
                return e == null ? "" : e.getNombreEquipo();
            }
            @Override
            public Equipo fromString(String s) {
                return null;
            }
        });
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        ObservableList<Jugador> jugador = FXCollections.observableArrayList(
                JugadorBD.consultarJugadores());
        tablaJugador.setItems(jugador);
    }

    private void cargarNacionalidades(int idJugador) {
        listaPaises.setItems(FXCollections.observableArrayList(
                NacionalidadDB.consultarPaisesDeJugador(idJugador)));
    }

    public void guardar(ActionEvent actionEvent) {
        String nombre = txtNombreJugador.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            aviso("Nombre y apellidos son obligatorios.");
            return;
        }

        Date fecha = null;
        String textoFecha = txtFechaNacimiento.getText().trim();
        if (!textoFecha.isEmpty()) {
            try {
                fecha = java.sql.Date.valueOf(textoFecha);
            } catch (IllegalArgumentException e) {
                aviso("La fecha debe tener el formato AAAA-MM-DD (por ejemplo 1997-04-21).");
                return;
            }
        }

        String dorsal = txtDorsal.getText().trim();
        if (!dorsal.isEmpty()) {
            try {
                Integer.parseInt(dorsal);
            } catch (NumberFormatException e) {
                aviso("El dorsal debe ser un número.");
                return;
            }
        }

        Jugador jugador = new Jugador(0, nombre, apellidos, fecha, null,
                txtPosicion.getText().trim(),
                dorsal.isEmpty() ? null : dorsal,
                cmbEquipo.getValue());

        if (JugadorBD.insertarJugador(jugador) == 0) {
            aviso("No se pudo añadir el jugador. Revisa la consola para ver el motivo.");
            return;
        }
        cargarDatosTabla();
        limpiar();
    }

    public void limpiarFormulario(ActionEvent actionEvent) {
        limpiar();
    }

    private void limpiar() {
        txtNombreJugador.clear();
        txtApellidos.clear();
        txtFechaNacimiento.clear();
        txtPosicion.clear();
        txtDorsal.clear();
        cmbEquipo.setValue(null);
        tablaJugador.getSelectionModel().clearSelection();
        listaPaises.getItems().clear();
    }

    public void modificar(ActionEvent actionEvent) {
    }

    public void eliminar(ActionEvent actionEvent) {
    }

    public void seleccionarFila(MouseEvent mouseEvent) {
        Jugador j = tablaJugador.getSelectionModel().getSelectedItem();
        if (j == null) {
            listaPaises.getItems().clear();
            return;
        }
        cargarNacionalidades(j.getId_jugador());
    }

    // Recarga la tabla (para refrescar la columna de nacionalidades) sin perder la selección
    private void recargarYReseleccionar(int idJugador) {
        cargarDatosTabla();
        for (Jugador j : tablaJugador.getItems()) {
            if (j.getId_jugador() == idJugador) {
                tablaJugador.getSelectionModel().select(j);
                break;
            }
        }
        cargarNacionalidades(idJugador);
    }

    public void anadirNacionalidad(ActionEvent actionEvent) {
        Jugador j = tablaJugador.getSelectionModel().getSelectedItem();
        Pais p = cmbPais.getValue();
        if (j == null || p == null) {
            aviso("Selecciona un jugador en la tabla y un país en la lista desplegable.");
            return;
        }
        if (NacionalidadDB.insertarNacionalidad(j.getId_jugador(), p.getIdPais()) == 0) {
            aviso("No se pudo añadir. Puede que el jugador ya tenga esa nacionalidad.");
            return;
        }
        recargarYReseleccionar(j.getId_jugador());
    }

    public void quitarNacionalidad(ActionEvent actionEvent) {
        Jugador j = tablaJugador.getSelectionModel().getSelectedItem();
        Pais p = listaPaises.getSelectionModel().getSelectedItem();
        if (j == null || p == null) {
            aviso("Selecciona un jugador y, en la lista de abajo, la nacionalidad que quieres quitar.");
            return;
        }
        NacionalidadDB.eliminarNacionalidad(j.getId_jugador(), p.getIdPais());
        recargarYReseleccionar(j.getId_jugador());
    }

    private void aviso(String mensaje) {
        new Alert(Alert.AlertType.WARNING, mensaje).showAndWait();
    }
}
