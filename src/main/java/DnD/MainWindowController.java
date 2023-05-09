package DnD;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainWindowController {
    public void MouseClicked(MouseEvent mouseEvent) throws IOException {
        Main main = new Main();
        Button button = (Button) mouseEvent.getSource();
        String buttonId = button.getId();
        int id = Integer.parseInt(buttonId);
        main.switchToNewScene(mouseEvent, id);

    }
    }