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
import org.example.futbolmacjavafx.dao.EquipoBD;
import org.example.futbolmacjavafx.modelo.Equipo;

import static org.example.futbolmacjavafx.dao.EquipoBD.insertarEquipo;


public class EquipoController {

    @FXML
    private TableView<Equipo> tablaEquipo;
    @FXML
    private TableColumn<Equipo,Integer> colIdEquipo;
    @FXML
    private TableColumn<Equipo,String> colNombreEquipo;
    @FXML
    private TableColumn<Equipo,String> colSiglas;
    @FXML
    private TableColumn<Equipo,String> colCiudad;
    @FXML
    private TableColumn<Equipo,String> colEstadio;
    @FXML
    private TableColumn<Equipo,String> colLiga;
    @FXML
    private TextField txtNombreEquipo;
    @FXML
    private TextField txtSiglas;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtEstadio;
    @FXML
    private TextField txtLiga;
    private TableColumn<Equipo,String> colDescripcionEquipo;
    EquipoBD equipoBD;

    public void initialize() {
        colIdEquipo.setCellValueFactory(new PropertyValueFactory<>("idEquipo"));
        colNombreEquipo.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
        colSiglas.setCellValueFactory(new PropertyValueFactory<>("siglas"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colEstadio.setCellValueFactory(new PropertyValueFactory<>("estadio"));
        colLiga.setCellValueFactory(new PropertyValueFactory<>("liga"));
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        ObservableList<Equipo> equipo = FXCollections.observableArrayList(
                EquipoBD.consultarEquipos());
        tablaEquipo.setItems(equipo);
    }

    public void guardar(ActionEvent actionEvent) {
        insertarEquipo(new Equipo(0, txtNombreEquipo.getText(),
                txtSiglas.getText(), txtCiudad.getText(), txtEstadio.getText(),
                txtLiga.getText()));
        cargarDatosTabla();
        txtNombreEquipo.clear();
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

