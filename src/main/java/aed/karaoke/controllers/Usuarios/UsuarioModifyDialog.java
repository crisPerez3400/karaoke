package aed.karaoke.controllers.Usuarios;

import aed.karaoke.models.Historial;
import aed.karaoke.models.Usuario;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioModifyDialog extends Dialog<Usuario> implements Initializable {

    private ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle("Modificar");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Modificar" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);


        usuario.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nombreTextField.setText(newValue.getNombre());
                correoTextField.setText(newValue.getCorreo());

            }
        });

    }

    public UsuarioModifyDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usuarios/modifyUsuariosView.fxml"));
            loader.setController(this);
            loader.load();

            // Configurar el ResultConverter
            setResultConverter(dialogButton -> {
                if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    // Crear un nuevo Usuario con los datos ingresados
                    Usuario usuarioModificado = getUsuario();
                    usuarioModificado.setNombre(nombreTextField.getText());
                    usuarioModificado.setCorreo(correoTextField.getText());
                    usuarioModificado.setFechaRegistro(java.sql.Date.valueOf(fechaDatePicker.getValue()));
                    return usuarioModificado;
                }
                return null; // Si no es OK_DONE, no devolver nada
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario.set(usuario);
    }

    public Usuario getUsuario() {
        return usuario.get();
    }

    public ObjectProperty<Usuario> usuarioProperty() {
        return usuario;
    }

    @FXML
    private BorderPane root;

    @FXML
    private TextField correoTextField;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private TextField nombreTextField;


}
