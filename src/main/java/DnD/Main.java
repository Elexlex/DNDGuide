package DnD;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String pathname = new File("java/DnD/CSS/DarkStylesheet.css").getAbsolutePath();
        File file = new File(pathname);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dnd-guide.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}