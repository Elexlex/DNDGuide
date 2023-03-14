package DnDGuide;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class MainWindowController {
    public void MouseClicked(MouseEvent mouseEvent) throws IOException, SQLException {
        Main main = new Main();
        ImageView imageView = (ImageView) mouseEvent.getSource();
        String imageViewId = imageView.getId();
        switch (imageViewId){
            case "1":
                main.switchToEnemyScene(mouseEvent);
                break;
            case "4":
                main.switchToMiscScene(mouseEvent);
                break;
        }

    }
    }