package DnD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML private Pane pane;
    @FXML private Label labelQuote;

    public void initialize(URL url, ResourceBundle rb){
        Random random = new Random();

        ObservableList<String> quotesList = FXCollections.observableArrayList("Hey, you. You're finally awake."
                , "Hello there.", "*Rolls D20* I rolled one...", "*Rolls D20* I rolled twenty!", "I hope you are doing well."
                , "Glory to Ukraine!", "Glory to Arstotzka!", "I hope you are lucky today.");

        int randomIndex = random.nextInt(quotesList.size());
        labelQuote.setText(quotesList.get(randomIndex));

    }
    public void MouseClicked(MouseEvent mouseEvent) throws IOException {
        SwitchToScenes sts = new SwitchToScenes();
        Button button = (Button) mouseEvent.getSource();
        String buttonId = button.getId();
        int id = Integer.parseInt(buttonId);
        sts.switchToNewScene(mouseEvent, id);

    }
    }