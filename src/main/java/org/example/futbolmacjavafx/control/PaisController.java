package org.example.futbolmacjavafx.control;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.futbolmacjavafx.dao.PaisDB;
import org.example.futbolmacjavafx.modelo.Pais;

public class PaisController {

    @FXML private TableView<Pais> tablaPais;
    @FXML private TableColumn<Pais, Integer> colIdPais;
    @FXML private TableColumn<Pais, String> colNombrePais;
    @FXML private TextField txtNombrePais;

    public void initialize() {
        colIdPais.setCellValueFactory(new PropertyValueFactory<>("idPais"));
        colNombrePais.setCellValueFactory(new PropertyValueFactory<>("nombrePais"));
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        tablaPais.setItems(FXCollections.observableArrayList(PaisDB.consultarPaises()));
    }

    public void guardar(ActionEvent actionEvent) {
        String nombre = txtNombrePais.getText().trim();
        if (nombre.isEmpty()) {
            aviso("Escribe el nombre del país.");
            return;
        }
        if (PaisDB.insertarPais(new Pais(0, nombre)) == 0) {
            aviso("No se pudo añadir. Puede que ese país ya exista.");
            return;
        }
        cargarDatosTabla();
        limpiar();
    }

    public void modificar(ActionEvent actionEvent) {
        Pais seleccionado = tablaPais.getSelectionModel().getSelectedItem();
        String nombre = txtNombrePais.getText().trim();
        if (seleccionado == null) {
            aviso("Selecciona un país de la tabla.");
            return;
        }
        if (nombre.isEmpty()) {
            aviso("El nombre no puede estar vacío.");
            return;
        }
        if (PaisDB.modificarPais(new Pais(seleccionado.getIdPais(), nombre)) == 0) {
            aviso("No se pudo actualizar. Puede que ya exista un país con ese nombre.");
            return;
        }
        cargarDatosTabla();
        limpiar();
    }

    public void eliminar(ActionEvent actionEvent) {
        Pais seleccionado = tablaPais.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            aviso("Selecciona un país de la tabla.");
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar " + seleccionado.getNombrePais() + "?");
        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }
        if (PaisDB.eliminarPais(seleccionado.getIdPais()) == 0) {
            aviso("No se pudo eliminar. Puede que algún jugador tenga ese país como nacionalidad.");
            return;
        }
        cargarDatosTabla();
        limpiar();
    }

    public void limpiarFormulario(ActionEvent actionEvent) {
        limpiar();
    }

    public void seleccionarFila(MouseEvent mouseEvent) {
        Pais seleccionado = tablaPais.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtNombrePais.setText(seleccionado.getNombrePais());
        }
    }

    private void limpiar() {
        txtNombrePais.clear();
        tablaPais.getSelectionModel().clearSelection();
    }

    private void aviso(String mensaje) {
        new Alert(Alert.AlertType.WARNING, mensaje).showAndWait();
    }
}
