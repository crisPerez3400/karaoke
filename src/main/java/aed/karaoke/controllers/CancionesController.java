package aed.karaoke.controllers;

import aed.karaoke.models.Canciones.Canciones;
import aed.karaoke.models.Canciones.CancionesService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CancionesController  implements Initializable {

    private ObservableList<Canciones> canciones;

    public CancionesController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/canciones/cancionesView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canciones = FXCollections.observableArrayList();

        CancionesService cancionesService = new CancionesService();

        List<Canciones> listaCanciones = cancionesService.obtenerCanciones();

        canciones.addAll(listaCanciones);

        tituloColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        artistaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtista()));
        generoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenero()));
        duracionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuracion()));

        cancionesTable.setItems(canciones);


    }

    @FXML
    private TableView<Canciones> cancionesTable;

    @FXML
    private TableColumn<Canciones, String> artistaColumn;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Canciones, String> duracionColumn;

    @FXML
    private TableColumn<Canciones, String> generoColumn;

    @FXML
    private Button modifyButton;

    @FXML
    private BorderPane root;

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    private SplitPane splitAlumno;

    @FXML
    private TableColumn<Canciones, String> tituloColumn;

    @FXML
    void onCreateStudentAction(ActionEvent event) {

    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {

    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {

    }
}
