package DnD;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        String pathname = new File("java/DnD/CSS/MainWindowStylesheet.css").getAbsolutePath();
        File file = new File(pathname);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dnd-guide.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToNewScene(MouseEvent event, int id) throws IOException {
        String pathname = new File("").getAbsolutePath();
        FXMLLoader loader = new FXMLLoader();
        switch (id) {
            case 1 -> {
                pathname = new File("java/DnD/CSS/EnemyWindowStylesheet.css").getAbsolutePath();
                loader = new FXMLLoader(getClass().getResource("dnd-enemies.fxml"));
            }
            case 2 -> loader = new FXMLLoader(getClass().getResource("dnd-spells.fxml"));
            case 4 -> loader = new FXMLLoader(getClass().getResource("dnd-misc.fxml"));
        }
        newScene(event, loader, pathname);

    }
    private void newScene(MouseEvent event, FXMLLoader loader, String pathname) throws IOException {
        File file = new File(pathname);
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (file.exists()) {
            scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}