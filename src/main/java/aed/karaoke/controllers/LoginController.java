package aed.karaoke.controllers;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import aed.karaoke.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private BooleanProperty loggedIn = new SimpleBooleanProperty(false);

    @FXML
    private Button logInButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField userTextfield;

    @FXML
    private BorderPane root;

    @FXML
    private TextField nombreTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private TextField edadTextfield;

    @FXML
    private PasswordField passwordField;

    private EntityManagerFactory emf;

    public LoginController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogInView.fxml"));
            loader.setController(this);
            loader.load();
            emf = Persistence.createEntityManagerFactory("persistencia");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesario
    }



    @FXML
    private void handleLogIn(ActionEvent event) {
        String user = userTextfield.getText().trim();
        String password = passwordField.getText().trim();

        // Autenticación
        if (authenticateUser(user, password)) {
            loggedIn.set(true);
            showInfoAlert("Inicio de sesión exitoso", "Bienvenido, " + user + ".");
        } else {
            showLoginError();
        }
    }

    private boolean authenticateUser(String usuario, String contraseña) {
        // Verificar si el usuario es el administrador "root"
        if (usuario.equals("root") && contraseña.equals("1234")) {
            loggedIn.set(true);
            return true;  // El usuario es el administrador "root"
        }
        return false;
    }



    private void showLoginError() {
        showErrorAlert("Error de autenticación", "Intentelo de nuevo.");
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public BooleanProperty loggedInProperty() {
        return loggedIn;
    }
}
