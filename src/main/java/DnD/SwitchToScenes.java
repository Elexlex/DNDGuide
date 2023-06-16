package DnD;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SwitchToScenes {

    public void switchToNewScene(MouseEvent event, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        switch (id) {
            case 0 -> loader = new FXMLLoader(getClass().getResource("DnD-guide.fxml"));

            case 1 -> loader = new FXMLLoader(getClass().getResource("DnD-enemies.fxml"));

            case 2 -> loader = new FXMLLoader(getClass().getResource("DnD-spells.fxml"));

            case 3 -> loader = new FXMLLoader(getClass().getResource("DnD-character-selection.fxml"));

            case 4 -> loader = new FXMLLoader(getClass().getResource("DnD-calculator.fxml"));
        }

        newScene(event, loader, id);
    }
    private void newScene(MouseEvent event, FXMLLoader loader, int id) throws IOException {
        String pathname;
        File file;
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        switch(id){
            case 0, 3 -> {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                pathname = new File("java/DnD/CSS/DarkStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            }
            case 1 -> {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                pathname = new File("java/DnD/CSS/DarkStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
                pathname = new File("java/DnD/CSS/EnemyWindowStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            }
            case 2 -> {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                pathname = new File("java/DnD/CSS/DarkStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
                pathname = new File("java/DnD/CSS/SpellsWindowStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            }

            case 4 -> {
                pathname = new File("java/DnD/CSS/CalculatorStylesheet.CSS").getAbsolutePath();
                file = new File(pathname);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            }
        }

        stage.setScene(scene);
        stage.show();
    }
}
