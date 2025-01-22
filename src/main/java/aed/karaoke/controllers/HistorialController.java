package aed.karaoke.controllers;

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
import java.util.ResourceBundle;

public class HistorialController implements Initializable {


    public HistorialController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historial/historialView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableColumn<?, ?> FechaColumn;

    @FXML
    private TableView<?> alumnoTable;

    @FXML
    private TableColumn<?, ?> cancionColumn;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyButton;

    @FXML
    private TableColumn<?, ?> nombreUsuarioColumn;

    @FXML
    private BorderPane root;

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    private SplitPane splitAlumno;

    @FXML
    void onCreateStudentAction(ActionEvent event) {

    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {

    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
