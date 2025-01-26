package aed.karaoke.controllers.Canciones;

import aed.karaoke.models.Canciones;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CancionesCreateDialog extends Dialog<Canciones> implements Initializable {

    private ObjectProperty<Canciones> cancion = new SimpleObjectProperty<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle("Crear");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Crear" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);
        setResultConverter(this::onResult);
    }

    public CancionesCreateDialog() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/canciones/createCancionesView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Canciones onResult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Canciones nuevaCancion = new Canciones();
            nuevaCancion.setTitulo(tituloTextField.getText());
            nuevaCancion.setArtista(artistaTextField.getText());
            nuevaCancion.setGenero(generoTextField.getText());
            nuevaCancion.setDuracion(duracionTextField.getText());

            // Formatear duración antes de asignarla
            String duracionFormateada = formatDuration(duracionTextField.getText());
            nuevaCancion.setDuracion(duracionFormateada);

            cancion.set(nuevaCancion);
            return cancion.get();
        }
        return null;
    }

    private String formatDuration(String inputDuracion) {
        try {
            // Intentar convertir la entrada en un número entero
            int minutos = Integer.parseInt(inputDuracion);
            return String.format("00:%02d:00", minutos);
        } catch (NumberFormatException e) {
            // Si no es un número, se devuelve el valor original (o puedes manejar errores aquí)
            return inputDuracion;
        }
    }

    @FXML
    private TextField artistaTextField;

    @FXML
    private TextField duracionTextField;

    @FXML
    private TextField generoTextField;

    @FXML
    private GridPane root;

    @FXML
    private TextField tituloTextField;
}
