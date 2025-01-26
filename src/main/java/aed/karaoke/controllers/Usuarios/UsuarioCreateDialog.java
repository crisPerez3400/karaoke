package aed.karaoke.controllers.Usuarios;

import aed.karaoke.models.Usuario;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioCreateDialog extends Dialog<Usuario> implements Initializable {

    private ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>(new Usuario());



    @FXML
    private TextField correoTextField;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private TextField nombreTextField;



    @FXML
    private GridPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle("Crear");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Crear" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);
        setResultConverter(this::onResult);


    }

    public UsuarioCreateDialog() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usuarios/createUsuariosView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario onResult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombreTextField.getText());
            nuevoUsuario.setCorreo(correoTextField.getText());
            nuevoUsuario.setFechaRegistro(java.sql.Date.valueOf(fechaDatePicker.getValue()));

            usuario.set(nuevoUsuario);
            return usuario.get();
        }
        return null;
    }


}
