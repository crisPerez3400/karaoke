package aed.karaoke.controllers.HistorialController;

import aed.karaoke.controllers.Usuarios.UsuarioModifyDialog;
import aed.karaoke.models.Historial;
import aed.karaoke.models.Usuario;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HistorialController implements Initializable {
    HistorialService historialService = new HistorialService();
    private ObservableList<Historial> historial;

    public HistorialController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historial/historialView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableColumn<Historial, Date> FechaColumn;

    @FXML
    private TableView<Historial> historialTable;

    @FXML
    private TableColumn<Historial, String> artistaColumn;

    @FXML
    private TableColumn<Historial, String> cancionColumn;

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

    @FXML
    private SplitPane splitAlumno;

    @FXML
    void onCreateStudentAction(ActionEvent event) {
        HistorialCreateDialog historialCreateDialog = new HistorialCreateDialog();

        Optional<Historial> result = historialCreateDialog.showAndWait();

        result.ifPresent(historial -> {
            try {
                historialService.agregarHistorial(historial);
            } catch (Exception e) {
                e.printStackTrace(); // Manejar errores (podrías mostrar un alerta aquí)
            }
        });
    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        historial = FXCollections.observableArrayList();

      //  List<Historial> listaHistorial = historialService.obtenerHistorial();
       // historial.addAll(listaHistorial);

    }
}
