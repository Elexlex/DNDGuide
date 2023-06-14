package DnD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnemiesWindowController implements Initializable {
    @FXML private TableView<Enemy> tableView;

    @FXML private TableColumn<Enemy, String> tableEnemyName;
    @FXML private TableColumn<Enemy, String> tableEnemyType;
    @FXML private TableColumn<Enemy, String> tableEnemySize;
    @FXML private TableColumn<Enemy, String> tableEnemyHP;
    @FXML private TableColumn<Enemy, String> tableEnemyChallenge;

    @FXML private TextField searchName;
    @FXML private ComboBox<String> typeBox;
    @FXML private ComboBox<String> sizeBox;
    @FXML private Pane pane;
    @FXML private Button arrowButton;

    public void initialize(URL url, ResourceBundle rb) {
        tableView.setPlaceholder(new Label("No creatures are here :("));

        ObservableList<Enemy> enemiesList = FXCollections.observableArrayList();

        ObservableList<String> typeList = FXCollections.observableArrayList("Aberration", "Construct", "Beast"
                , "Celestial", "Dragon", "Fey", "Elemental", "Fiend", "Giant", "Monstrosity", "Humanoid", "Ooze"
                , "Plant", "Undead");
        ObservableList<String> sizeList = FXCollections.observableArrayList("Tiny", "Small", "Medium", "Large"
                , "Huge", "Gargantuan");

        typeBox.setItems(typeList);
        sizeBox.setItems(sizeList);

        gettingInfo(enemiesList);
        sortingTable(enemiesList);

    }

    private void gettingInfo(ObservableList<Enemy> enemiesList){
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            String query = "SELECT enemy_id, enemy_name, enemy_type, enemy_hp, enemy_size, enemy_challenge FROM enemies";
            Statement statement = connectionDB.getConnectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("enemy_id");
                String name = resultSet.getString("enemy_name");
                String type = resultSet.getString("enemy_type");
                String hp = resultSet.getString("enemy_hp") + " HP";
                String size = resultSet.getString("enemy_size");
                String challenge = resultSet.getDouble("enemy_challenge") + " CR";
                enemiesList.add(new Enemy(id, name, type,  size,hp, challenge));
            }

            settingUpTable(enemiesList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void settingUpTable(ObservableList<Enemy> enemiesList){
        tableView.getColumns().clear();
        tableEnemyName.setCellValueFactory(new PropertyValueFactory<>("enemyName"));

        tableEnemyType.setCellValueFactory(new PropertyValueFactory<>("enemyType"));

        tableEnemySize.setCellValueFactory(new PropertyValueFactory<>("enemySize"));

        tableEnemyHP.setCellValueFactory(new PropertyValueFactory<>("enemyHP"));

        tableEnemyChallenge.setCellValueFactory(new PropertyValueFactory<>("enemyChallenge"));
        tableView.setItems(enemiesList);
        tableView.getColumns().addAll( tableEnemyName, tableEnemyType, tableEnemySize, tableEnemyHP,  tableEnemyChallenge);
    }

    private void sortingTable(ObservableList<Enemy> enemiesList){
        FilteredList<Enemy> filteredList = new FilteredList<>(enemiesList, b -> true);
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<Enemy> namePredicate = Enemy -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String search = newValue.toLowerCase();
                return Enemy.getEnemyName().toLowerCase().contains(search);
            };
            filteredList.setPredicate(namePredicate.and(getTypePredicate()).and(getSizePredicate()));
        });
        typeBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchName.setText("");
            Predicate<Enemy> typePredicate = Enemy -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                return Enemy.getEnemyType().equals(newValue);
            };
            filteredList.setPredicate(typePredicate.and(getSizePredicate()));
        });

        sizeBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchName.setText("");
            Predicate<Enemy> sizePredicate = Enemy -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                return Enemy.getEnemySize().equals(newValue);
            };
            filteredList.setPredicate(getTypePredicate().and(sizePredicate));
        });
        SortedList<Enemy> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void doubleMouseClicked (MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            try {
                String pathname = new File("java/DnD/CSS/EnemyPageWindowStylesheet.css").getAbsolutePath();
                File file = new File(pathname);
                Stage stage = new Stage();
                Scene scene;
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dnd-enemy-page.fxml"));
                root = loader.load();
                EnemyPageController controller = loader.getController();
                controller.setEnemyID(tableView.getSelectionModel().getSelectedItem().getEnemyID());
                scene = new Scene(root);
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Predicate<Enemy> getTypePredicate(){
        String type = typeBox.getValue();
        return Enemy -> {
            if (type == null){
                return true;
            }
            return Enemy.getEnemyType().equals(type);
        };
    }

    private Predicate<Enemy> getSizePredicate(){
        String size = sizeBox.getValue();
        return Enemy -> {
            if (size == null){
                return true;
            }
            return Enemy.getEnemySize().equals(size);
        };
    }

    public void MouseClicked(MouseEvent mouseEvent) throws IOException {
        SwitchToScenes sts = new SwitchToScenes();
        Button button = (Button) mouseEvent.getSource();
        String buttonId = button.getId();
        int id = Integer.parseInt(buttonId);
        sts.switchToNewScene(mouseEvent, id);

    }
}

