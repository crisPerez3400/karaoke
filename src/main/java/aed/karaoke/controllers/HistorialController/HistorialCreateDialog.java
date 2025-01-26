package aed.karaoke.controllers.HistorialController;

import aed.karaoke.controllers.Canciones.CancionesService;
import aed.karaoke.controllers.Usuarios.UsuarioService;
import aed.karaoke.models.Canciones;
import aed.karaoke.models.Historial;
import aed.karaoke.models.Usuario;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HistorialCreateDialog extends Dialog<Historial> implements Initializable {
    private ObjectProperty<Canciones> cancion = new SimpleObjectProperty<>(new Canciones());

    private ObjectProperty<Historial> historial = new SimpleObjectProperty<>(new Historial());
    CancionesService cancionesService = new CancionesService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UsuarioService usuarioService = new UsuarioService();
        usuarioCombo.getItems().addAll(usuarioService.obtenerUsuarios());

        setTitle("Crear");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Crear" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);
        setResultConverter(this::onResult);

        // Configurar cómo mostrar los nombres en el ComboBox
        usuarioCombo.setConverter(new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario usuario) {
                return usuario != null ? usuario.getNombre() : "";
            }

            @Override
            public Usuario fromString(String nombre) {
                return usuarioCombo.getItems().stream()
                        .filter(u -> u.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);
            }


        });



        cargarCanciones();
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Crear", ButtonBar.ButtonData.OK_DONE), ButtonType.CANCEL);
        setResultConverter(this::onResult);

    }

    public HistorialCreateDialog() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historial/createHistorialView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Historial onResult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            Historial historial = new Historial();

            // Obtener y asignar los valores seleccionados
            historial.setFechaCantada(Date.valueOf(fechaDatePicker.getValue()));
            historial.setUsuario(usuarioCombo.getValue()); // Usuario completo


            Canciones cancionSeleccionada = cancionCombo.getValue();
            if (cancionSeleccionada != null) {
                historial.setCancion(String.valueOf(cancionSeleccionada)); // Asignamos el objeto Canciones
            }

            return historial;
        }
        return null;
    }

    private void cargarCanciones() {
        List<Canciones> canciones = cancionesService.obtenerCanciones();
        cancionCombo.getItems().setAll(canciones);

        // Configurar cómo se muestran las canciones en el ComboBox
        cancionCombo.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Canciones cancion, boolean empty) {
                super.updateItem(cancion, empty);
                setText(cancion == null || empty ? null : cancion.getTitulo() + " - " + cancion.getArtista());
            }
        });

        // Configurar cómo mostrar la canción seleccionada en el ComboBox
        cancionCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Canciones cancion) {
                return cancion != null ? cancion.getTitulo() + " - " + cancion.getArtista() : "";
            }
            @Override
            public Canciones fromString(String string) {
                return cancionCombo.getItems().stream()
                        .filter(c -> (c.getTitulo() + " - " + c.getArtista()).equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    private ComboBox<String> artistaCombo;

    @FXML
    private ComboBox<Canciones> cancionCombo;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private GridPane root;

    @FXML
    private ComboBox<Usuario> usuarioCombo;

    public GridPane getRoot() {
        return root;
    }




}
