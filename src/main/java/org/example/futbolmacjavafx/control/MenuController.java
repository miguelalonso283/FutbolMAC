package org.example.futbolmacjavafx.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {
    @FXML
    private AnchorPane contentArea;

    public void mostrarEquipos(javafx.event.ActionEvent actionEvent) {
        cargarVista("/org/example/futbolmacjavafx/equipos-view.fxml");
    }

    public void mostrarJugadores(javafx.event.ActionEvent actionEvent) {
        cargarVista(("/org/example/futbolmacjavafx/jugadores-view.fxml"));
    }

    private void cargarVista(String fxmlPath) {

        try {
            Parent node = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontró el archivo");
            alert.showAndWait();
        }
    }



}
