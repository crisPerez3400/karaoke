package dad.colores.controllers;

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

public class EstadisticasController implements Initializable {

    public EstadisticasController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/estadisticas/estadisticasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TableColumn<?, ?> CorreoUsuarioColumn;

    @FXML
    private TableColumn<?, ?> FechaColumn;

    @FXML
    private TableView<?> alumnoTable;

    @FXML
    private Button createButton;

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
    private TableColumn<?, ?> vecesCantadaColumn;

    @FXML
    void onCreateStudentAction(ActionEvent event) {

    }
}
