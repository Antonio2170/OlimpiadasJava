package ni.edu.uam.grupoolimpiadas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Ajustamos el tamaño inicial de la ventana para que se vea bien la tabla
        Scene scene = new Scene(fxmlLoader.load(), 520, 450);
        stage.setTitle("Gestión de Olimpiadas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
