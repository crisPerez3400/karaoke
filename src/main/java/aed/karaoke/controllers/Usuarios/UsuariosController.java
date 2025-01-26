package aed.karaoke.controllers.Usuarios;

import aed.karaoke.models.Usuario;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {
    UsuarioService usuarioService = new UsuarioService();


    private ObservableList<Usuario> usuarios;

    public UsuariosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usuarios/usuariosView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarios = FXCollections.observableArrayList();

        // UsuarioService usuarioService = new UsuarioService();

        List<Usuario> listaUsuarios = usuarioService.obtenerUsuarios();

        usuarios.addAll(listaUsuarios);

        nombreUsuarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        CorreoUsuarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        FechaRegistroColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaRegistro()));

        // Asignar la lista de usuarios a la tabla
        usuarioTable.setItems(usuarios);

        //boton desactivado x defecto
        modifyButton.setDisable(true);

        usuarioTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            modifyButton.setDisable(newValue == null); // Activar si hay un elemento seleccionado
        });

    }


    @FXML
    private TableColumn<Usuario, String> CorreoUsuarioColumn;

    @FXML
    private TableColumn<Usuario, Date> FechaRegistroColumn;

    @FXML
    private TableView<Usuario> usuarioTable;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyButton;

    @FXML
    private TableColumn<Usuario, String> nombreUsuarioColumn;

    @FXML
    private BorderPane root;

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    private SplitPane splitAlumno;

    @FXML
    void onCreateStudentAction(ActionEvent event) {
        UsuarioCreateDialog usuarioCreateDialog = new UsuarioCreateDialog();

        Optional<Usuario> result = usuarioCreateDialog.showAndWait();

        result.ifPresent(usuario -> {
            try {
                usuarioService.agregarUsuario(usuario);
                usuarios.add(usuario);
            } catch (Exception e) {
                e.printStackTrace(); // Manejar errores (podrías mostrar un alerta aquí)
            }
        });
    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {
        Usuario usuario = usuarioTable.getSelectionModel().getSelectedItem();
        if (usuario != null) {

            try {
                usuarioService.eliminarUsuario(usuario);
                usuarios.remove(usuario);
            } catch (Exception e) {
                e.printStackTrace(); // Manejar errores
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No se pudo eliminar el usuario");
                errorAlert.setContentText("Error al eliminar el usuario");
                errorAlert.showAndWait();
            }

        }

    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {
        Usuario usuarioSeleccionado = usuarioTable.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado != null) {
            UsuarioModifyDialog usuariosModifyDialog = new UsuarioModifyDialog();
            usuariosModifyDialog.setUsuario(usuarioSeleccionado);

            Optional<Usuario> result = usuariosModifyDialog.showAndWait();

            result.ifPresent(modifiedUsuario -> {
                try {
                    usuarioService.modificarUsuario(modifiedUsuario);
                    int index = usuarios.indexOf(usuarioSeleccionado);
                    usuarios.set(index, modifiedUsuario); // Actualiza el usuario en la lista
                    usuarioTable.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("No se pudo modificar el usuario");
                    errorAlert.setContentText("Error al modificar el usuario");
                    errorAlert.showAndWait();
                }
            });
        }
    }
}
