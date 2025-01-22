package aed.karaoke;

import aed.karaoke.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    private RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(rootController.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke");
        primaryStage.show();
    }
}
