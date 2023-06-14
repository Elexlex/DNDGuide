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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CharacterSelectionController implements Initializable {
    @FXML private TableView<Character> characterTableView;
    @FXML private TextField searchCharacterName;
    @FXML private Button refreshButton;



    public void initialize(URL url, ResourceBundle rb) {
        characterTableView.setPlaceholder(new Label("No characters are here :("));
        ObservableList<Character> characterList = FXCollections.observableArrayList();

        gettingInfo(characterList);
        sortingTable(characterList);

        refreshButton.setOnMouseClicked(e -> {
            characterList.clear();
            characterTableView.getColumns().clear();

            gettingInfo(characterList);
        });
    }

    public void gettingInfo(ObservableList<Character> characterList){
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            String query = "SELECT character_id, character_name, character_class_level, character_race" +
                    ", character_player_name FROM character_page";
            Statement statement = connectionDB.getConnectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("character_id");
                String characterName = resultSet.getString("character_name");
                String classLevel = resultSet.getString("character_class_level");
                String race = resultSet.getString("character_race");
                String playerName = resultSet.getString("character_player_name");
                characterList.add(new Character(id, characterName, classLevel, race, playerName));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        settingUpTable(characterList);
    }

    private void settingUpTable(ObservableList<Character> characterList){

        TableColumn<Character, String> tableCharacterName = new TableColumn<>("Character Name");
        tableCharacterName.setCellValueFactory(new PropertyValueFactory<>("characterName"));

        TableColumn<Character, String> tableCharacterClass = new TableColumn<>("Class/Level");
        tableCharacterClass.setCellValueFactory(new PropertyValueFactory<>("characterClass"));

        TableColumn<Character, String> tableCharacterRace = new TableColumn<>("Race");
        tableCharacterRace.setCellValueFactory(new PropertyValueFactory<>("characterRace"));

        TableColumn<Character, String> tablePlayerName = new TableColumn<>("Player Name");
        tablePlayerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        characterTableView.setItems(characterList);
        characterTableView.getColumns().addAll(tableCharacterName, tableCharacterClass, tableCharacterRace, tablePlayerName);
    }

    private void sortingTable(ObservableList<Character> characterList){
        FilteredList<Character> filteredList = new FilteredList<>(FXCollections.observableArrayList(characterList));
        searchCharacterName.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<Character> namePredicate = Character -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String search = newValue.toLowerCase();
                return Character.getCharacterName().toLowerCase().contains(search);
            };
            filteredList.setPredicate(namePredicate);
        });

        SortedList<Character> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(characterTableView.comparatorProperty());
        characterTableView.setItems(sortedList);
    }

    public void onAddButtonClicked(){
        boolean newPageCheck = true;
        newPage(newPageCheck);
    }

    public void doubleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            boolean newPageCheck = false;
          newPage(newPageCheck);
        }
    }

    private void newPage(boolean newPageCheck){
        try {
            String pathname = new File("java/DnD/CSS/CharacterPageStylesheet.css").getAbsolutePath();
            File file = new File(pathname);
            Stage stage = new Stage();
            Scene scene;
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dnd-character-page.fxml"));
            root = loader.load();
            CharacterPageController controller = loader.getController();
            controller.setNewPageCheck(newPageCheck);
            if (!newPageCheck) {
                controller.setCharacterID(characterTableView.getSelectionModel().getSelectedItem().getCharacterID());
            }
            scene = new Scene(root);
            scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletingRow() {

        Character character = characterTableView.getSelectionModel().getSelectedItem();

        if (character != null) {

            characterTableView.getItems().remove(character);


            try {
                ConnectionDB connection = new ConnectionDB();
                Statement statement = connection.getConnectionDB().createStatement();
                String query = "DELETE FROM character_page WHERE character_id = " + character.getCharacterID();
                statement.executeUpdate(query);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public void MouseClicked(MouseEvent mouseEvent) throws IOException {
        SwitchToScenes sts = new SwitchToScenes();
        Button button = (Button) mouseEvent.getSource();
        String buttonId = button.getId();
        int id = Integer.parseInt(buttonId);
        sts.switchToNewScene(mouseEvent, id);

    }
}

