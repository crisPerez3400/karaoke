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

public class CancionesModifyDialog extends Dialog<Canciones> implements Initializable {
    private ObjectProperty<Canciones> cancion = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle("Modificar");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Modificar" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);

        cancion.addListener((observable, oldValue, newValue) -> {
           if(newValue != null) {
               tituloTextField.setText(newValue.getTitulo());
               artistaTextField.setText(newValue.getArtista());
               generoTextField.setText(newValue.getGenero());
               duracionTextField.setText(newValue.getDuracion());

           }
        });
    }

    public CancionesModifyDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/canciones/modifyCancionesView.fxml"));
            loader.setController(this);
            loader.load();

            setResultConverter(dialogButton -> {
                if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    // Crear un nuevo Usuario con los datos ingresados
                    Canciones cancion = getCancion();
                    cancion.setTitulo(tituloTextField.getText());
                    cancion.setGenero(generoTextField.getText());
                    cancion.setArtista(artistaTextField.getText());
                    cancion.setDuracion(duracionTextField.getText());
                    return cancion;
                }
                return null; // Si no es OK_DONE, no devolver nada
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Canciones getCancion() {return cancion.get();}

    public void setCancion(Canciones cancion) {this.cancion.set(cancion);}

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
