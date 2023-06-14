package DnD;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SpellPageController implements Initializable {

    @FXML private Label spellName;
    @FXML private Label spellLevelAndSchool;
    @FXML private Label spellCasting;
    @FXML private Text spellDescription;
    @FXML private Line line;
    @FXML private AnchorPane anchorPaneMain;
    @FXML private AnchorPane anchorPaneSecondary;
    private int spellID;

    public void initialize (URL url, ResourceBundle rb){
        Platform.runLater(() -> {
            try {
                ConnectionDB connectionDB = new ConnectionDB();
                String query = "SELECT spell_name, spell_level, spell_school, spell_casting_time, spell_duration, spell_range, spell_ritual," +
                        " spell_concentration, spell_verbal, spell_somatic, spell_material, spell_material_components, spell_description FROM spells WHERE spell_id = " + spellID;
                Statement statement = connectionDB.getConnectionDB().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                spellName.setText(resultSet.getString("spell_name"));
                spellLevelAndSchool.setText(resultSet.getString("spell_level") + " Level, " + resultSet.getString("spell_school"));
                spellCasting.setText("Casting time: " + resultSet.getString("spell_casting_time"));
                if (resultSet.getString("spell_ritual").equals("Y")){
                    spellCasting.setText(spellCasting.getText() + " (ritual)");
                }
                spellCasting.setText(spellCasting.getText() + "\nRange: " + resultSet.getString("spell_range"));
                spellCasting.setText(spellCasting.getText() + "\nDuration: " + resultSet.getString("spell_duration"));
                if (resultSet.getString("spell_concentration").equals("Y")){
                    spellCasting.setText(spellCasting.getText() + " (concentration)");
                }
                spellCasting.setText(spellCasting.getText() + "\nComponents: ");
                if (resultSet.getString("spell_verbal").equals("Y")){
                    spellCasting.setText(spellCasting.getText() + "V ");
                }
                if (resultSet.getString("spell_somatic").equals("Y")){
                    spellCasting.setText(spellCasting.getText() + "S ");
                }
                if (resultSet.getString("spell_material").equals("Y")){
                    spellCasting.setText(spellCasting.getText() + "M, " + resultSet.getString("spell_material_components"));
                }
                spellDescription.setText(resultSet.getString("spell_description"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setSpellID(int spellID){
        this.spellID = spellID;
    }
}
