package aed.karaoke.controllers;

import aed.karaoke.models.Usuarios.Usuario;
import aed.karaoke.models.Usuarios.UsuarioService;
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
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    private ObservableList<Usuario> usuarios;

    public UsuariosController() {
        try{
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

        UsuarioService usuarioService = new UsuarioService();

        List<Usuario> listaUsuarios = usuarioService.obtenerUsuarios();

        usuarios.addAll(listaUsuarios);



        nombreUsuarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        CorreoUsuarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        FechaRegistroColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaRegistro()));

        // Asignar la lista de usuarios a la tabla
        alumnoTable.setItems(usuarios);

    }


    @FXML
    private TableColumn<Usuario, String> CorreoUsuarioColumn;

    @FXML
    private TableColumn<Usuario, Date> FechaRegistroColumn;

    @FXML
    private TableView<Usuario> alumnoTable;


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

    }

    @FXML
    void onDeleteStudentAction(ActionEvent event) {

    }

    @FXML
    void onModifiedStudentAction(ActionEvent event) {

    }
}
