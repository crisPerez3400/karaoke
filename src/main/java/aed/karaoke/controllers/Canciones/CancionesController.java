package aed.karaoke.controllers.Canciones;

import aed.karaoke.models.Canciones;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CancionesController  implements Initializable {
    CancionesService cancionesService = new CancionesService();

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

        CancionesCreateDialog cancionesCreateDialog = new CancionesCreateDialog();
        Optional<Canciones> result = cancionesCreateDialog.showAndWait();

        result.ifPresent(cancion -> {
            try {
                cancionesService.agregarCancion(cancion);
                canciones.add(cancion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {
        Canciones cancion = cancionesTable.getSelectionModel().getSelectedItem();
        if (cancion!= null) {
            try {
                cancionesService.eliminarCancion(cancion);
                canciones.remove(cancion);
            } catch (Exception e) {
                e.printStackTrace(); // Manejar errores
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No se pudo eliminar la canción");
                errorAlert.setContentText("Error al eliminar la canción");
                errorAlert.showAndWait();
            }
        }

    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {
        Canciones cancion = cancionesTable.getSelectionModel().getSelectedItem();
        if (cancion!= null) {
            CancionesModifyDialog cancionesModifyDialog = new CancionesModifyDialog();
            cancionesModifyDialog.setCancion(cancion);

            Optional<Canciones> result = cancionesModifyDialog.showAndWait();

            result.ifPresent(updatedCancion -> {
                try {
                    cancionesService.modificarCancion(updatedCancion);
                    int index = canciones.indexOf(cancion);
                    canciones.set(index, cancion);
                    cancionesTable.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("No se pudo modificar la canción");
                    errorAlert.setContentText("Error al modificar la canción");
                    errorAlert.showAndWait();
                }
            });

        }

    }
}
