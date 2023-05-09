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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SpellWindowController implements Initializable {

    @FXML private TableView<Spell> spellTableView;
    @FXML private TextField searchName;
    @FXML private ComboBox<String> levelBox;
    @FXML private ComboBox<String> schoolBox;

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Spell> spellList = FXCollections.observableArrayList();

        ObservableList<String> levelList = FXCollections.observableArrayList("0 Level", "1 Level", "2 Level", "3 Level",
                "4 Level", "5 Level", "6 Level", "7 Level", "8 Level", "9 Level");
        ObservableList<String> schoolList = FXCollections.observableArrayList("Abjuration", "Transmutation", "Conjuration", "Divination",
                "Enchantment", "Evocation", "Illusion", "Necromancy");

        levelBox.setItems(levelList);
        schoolBox.setItems(schoolList);

        gettingInfo(spellList);
        sortingTable(spellList);
    }

    private void gettingInfo(ObservableList<Spell> spellList){
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            String query = "SELECT spell_id, spell_name, spell_level, spell_school, spell_casting_time FROM spells";
            Statement statement = connectionDB.getConnectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("spell_id");
                String name = resultSet.getString("spell_name");
                String level = resultSet.getString("spell_level") + " Level";
                String school = resultSet.getString("spell_school");
                String castingTime = resultSet.getString("spell_casting_time");
                spellList.add(new Spell(id, name, level, school, castingTime));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        settingUpTable(spellList);
    }

    private void settingUpTable(ObservableList<Spell> spellList){
        spellTableView.getColumns().clear();

        TableColumn<Spell, String> tableSpellName = new TableColumn<>("Name");
        tableSpellName.setCellValueFactory(new PropertyValueFactory<>("spellName"));

        TableColumn<Spell, String> tableSpellLevel = new TableColumn<>("Level");
        tableSpellLevel.setCellValueFactory(new PropertyValueFactory<>("spellLevel"));

        TableColumn<Spell, String> tableSpellSchool = new TableColumn<>("School");
        tableSpellSchool.setCellValueFactory(new PropertyValueFactory<>("spellSchool"));

        TableColumn<Spell, String> tableSpellCastingTime = new TableColumn<>("Casting Time");
        tableSpellCastingTime.setCellValueFactory(new PropertyValueFactory<>("spellCastingTime"));

        spellTableView.setItems(spellList);
        spellTableView.getColumns().addAll(tableSpellName, tableSpellLevel, tableSpellSchool, tableSpellCastingTime);
    }

    private void sortingTable(ObservableList<Spell> spellList){
        FilteredList<Spell> filteredList = new FilteredList<>(FXCollections.observableArrayList(spellList));
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<Spell> namePredicate = Spell -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String search = newValue.toLowerCase();
                return Spell.getSpellName().toLowerCase().contains(search);
            };
            filteredList.setPredicate(namePredicate.and(getLevelPredicate().and(getSchoolPredicate())));
        });

        levelBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchName.setText("");
            Predicate<Spell> levelPredicate = Spell -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                return Spell.getSpellLevel().equals(newValue);
            };
            filteredList.setPredicate(levelPredicate.and(getSchoolPredicate()));
        });

        schoolBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchName.setText("");
            Predicate<Spell> schoolPredicate = Spell -> {
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                return Spell.getSpellSchool().equals(newValue);
            };
            filteredList.setPredicate(getLevelPredicate().and(schoolPredicate));
        });
        SortedList<Spell> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(spellTableView.comparatorProperty());
        spellTableView.setItems(sortedList);
    }

    public void doubleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            try {
                String pathname = new File("java/DnD/CSS/SpellsPageWindowStylesheet.css").getAbsolutePath();
                File file = new File(pathname);
                Stage stage = new Stage();
                Scene scene;
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dnd-spell-page.fxml"));
                root = loader.load();
                SpellPageController controller = loader.getController();
                controller.setSpellID(spellTableView.getSelectionModel().getSelectedItem().getSpellID());
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

    private Predicate<Spell> getLevelPredicate(){
        String level = levelBox.getValue();
        return Spell -> {
            if (level == null){
                return true;
            }
            return Spell.getSpellLevel().equals(level);
        };
    }

    private Predicate<Spell> getSchoolPredicate(){
        String school = schoolBox.getValue();
        return Spell -> {
            if (school == null){
                return true;
            }
            return Spell.getSpellSchool().equals(school);
        };
    }
}
