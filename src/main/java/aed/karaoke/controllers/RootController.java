package aed.karaoke.controllers;

import aed.karaoke.controllers.Canciones.CancionesController;
import aed.karaoke.controllers.HistorialController.HistorialController;
import aed.karaoke.controllers.Usuarios.UsuariosController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    LoginController loginController = new LoginController();
    UsuariosController usuariosController = new UsuariosController();
    CancionesController cancionesController = new CancionesController();
    HistorialController historialController = new HistorialController();
    EstadisticasController estadisticasController = new EstadisticasController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        usuariosTab.setContent(usuariosController.getRoot());
        cancionesTab.setContent(cancionesController.getRoot());
        historialTab.setContent(historialController.getRoot());
        estadisticasTab.setContent(estadisticasController.getRoot());
    }

    public RootController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private TabPane root;

    public TabPane getRoot() {
        return root;
    }

    public void setRoot(TabPane root) {
        this.root = root;
    }


    @FXML
    private Tab cancionesTab;

    @FXML
    private Tab historialTab;


    @FXML
    private Tab usuariosTab;


    @FXML
    private Tab estadisticasTab;


}
