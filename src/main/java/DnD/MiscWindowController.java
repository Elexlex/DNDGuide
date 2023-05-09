package DnD;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MiscWindowController {
    public void onButtonClicked (MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Button button = (Button) mouseEvent.getSource();
        String buttonId = button.getId();
        switch (buttonId){
            case "1":
                fxmlLoader.setLocation(getClass().getResource("dnd-calculator.fxml"));
                break;
            case "2":
                break;
        }
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
