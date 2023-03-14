package DnDGuide;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;

public class EnemiesWindowController implements Initializable {
    @FXML private TableView<Enemy> tableView;
    @FXML private TableColumn<Enemy, Integer> tableEnemyID;
    @FXML private TableColumn<Enemy, String> tableEnemyName;

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Enemy> enemiesList = FXCollections.observableArrayList();
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            String query = "SELECT enemy_id, enemy_name FROM enemies";
            Statement statement = connectionDB.getConnectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("enemy_id");
                String name = resultSet.getString("enemy_name");
                System.out.println(name + " " + id);
                enemiesList.add(new Enemy(id, name));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        tableView.getColumns().clear();
        tableEnemyID.setCellValueFactory(new PropertyValueFactory<>("enemyID"));
        tableEnemyName.setCellValueFactory(new PropertyValueFactory<>("enemyName"));
        tableView.setItems(enemiesList);
        tableView.getColumns().addAll(tableEnemyID, tableEnemyName);
    }
}

