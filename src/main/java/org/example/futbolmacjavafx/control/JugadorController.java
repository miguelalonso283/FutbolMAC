package org.example.futbolmacjavafx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.futbolmacjavafx.dao.JugadorBD;
import org.example.futbolmacjavafx.modelo.Jugador;

import java.util.Date;

public class JugadorController {

    @FXML
    private TableView<Jugador> tablaJugador;
    @FXML
    private TableColumn<Jugador, Integer> colIdJugador;
    @FXML
    private TableColumn<Jugador, String> colNombreJugador;
    @FXML
    private TableColumn<Jugador, String> colApellidos;
    @FXML
    private TableColumn<Jugador, Date> colFechaNacimiento;
    @FXML
    private TableColumn<Jugador, String> colNacionalidad;
    @FXML
    private TableColumn<Jugador, String> colPosicion;
    @FXML
    private TableColumn<Jugador, Integer> colDorsal;
    @FXML
    private TableColumn<Jugador, Integer> colEquipo_id;
    @FXML
    private TableColumn<Jugador, Integer> colEquipo_pais_id;
    @FXML
    private TextField txtNombreJugador;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtFechaNacimiento;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtPosicion;
    @FXML
    private TextField txtDorsal;
    @FXML
    private TextField txtEquipo_id;
    @FXML
    private TextField txtEquipo_pais_id;
    private TableColumn<Jugador,String> colDescripcionJugador;
    JugadorBD jugadorBD;

    public void initialize(){
        colIdJugador.setCellValueFactory(new PropertyValueFactory<>("id_jugador"));
        colNombreJugador.setCellValueFactory(new PropertyValueFactory<>("nombreJugador"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colFechaNacimiento.setCellValueFactory(new  PropertyValueFactory<>("fechaNacimiento"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colDorsal.setCellValueFactory(new PropertyValueFactory<>("dorsal"));
        colEquipo_id.setCellValueFactory(new PropertyValueFactory<>("equipo_id"));
        colEquipo_pais_id.setCellValueFactory(new PropertyValueFactory<>("equipo_pais_id"));
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        ObservableList<Jugador> jugador = FXCollections.observableArrayList(
                JugadorBD.consultarJugadores());
        tablaJugador.setItems(jugador);
    }

    public void guardar(ActionEvent actionEvent) {
    }

    public void modificar(ActionEvent actionEvent) {
    }

    public void eliminar(ActionEvent actionEvent) {
    }

    public void limpiarFormulario(ActionEvent actionEvent) {
    }

    public void seleccionarFila(MouseEvent mouseEvent) {
    }
}
