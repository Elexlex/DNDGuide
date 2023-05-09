package DnD;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;


public class EnemyPageController implements Initializable {
    @FXML private Label enemyName;
    @FXML private Label enemyHP;
    @FXML private Label enemyAC;
    @FXML private Label enemyDescription;
    @FXML private Label enemyChallenge;
    @FXML private Label enemyStrength;
    @FXML private Label enemyDexterity;
    @FXML private Label enemyConstitution;
    @FXML private Label enemyIntelligence;
    @FXML private Label enemyWisdom;
    @FXML private Label enemyCharisma;
    @FXML private Label enemyStrengthModifier;
    @FXML private Label enemyDexterityModifier;
    @FXML private Label enemyConstitutionModifier;
    @FXML private Label enemyIntelligenceModifier;
    @FXML private Label enemyWisdomModifier;
    @FXML private Label enemyCharismaModifier;
    @FXML private ImageView enemyPicture;
    @FXML private Pane statsPane;
    @FXML private AnchorPane anchorPaneMain;
    @FXML private AnchorPane anchorPaneSecondary;
    private int enemyID;


    public void initialize (URL url, ResourceBundle rb){
        Platform.runLater(() -> {
            try {
                ConnectionDB connectionDB = new ConnectionDB();
                String query = "SELECT enemy_name, enemy_size, enemy_type, enemy_hp, enemy_ac, enemy_speeds, enemy_save_throws," +
                        " enemy_skills, enemy_senses, enemy_languages, enemy_challenge FROM enemies WHERE enemy_id = " + enemyID;
                Statement statement = connectionDB.getConnectionDB().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                enemyName.setText(resultSet.getString("enemy_name"));
                enemyHP.setText(resultSet.getString("enemy_hp"));
                enemyDescription.setText(resultSet.getString("enemy_size") + " " + resultSet.getString("enemy_type")
                        + "\nSpeed: " + resultSet.getString("enemy_speeds") + "\nSaving Throws: "
                        + resultSet.getString("enemy_save_throws") + "\nSkills: " + resultSet.getString("enemy_skills")
                        + "\nSenses: " + resultSet.getString("enemy_senses") + "\nLanguages: " + resultSet.getString("enemy_languages"));
                enemyAC.setText(resultSet.getString("enemy_ac"));
                enemyChallenge.setText(resultSet.getString("enemy_challenge"));
                enemyName.getStyleClass().add("enemyName");
                query = "SELECT enemy_str, enemy_dex, enemy_con, enemy_int, enemy_wis, enemy_cha, enemy_str_mod, " +
                        "enemy_dex_mod, enemy_con_mod, enemy_int_mod, enemy_wis_mod, enemy_cha_mod FROM enemies_stats WHERE enemy_id = " + enemyID;
                resultSet = statement.executeQuery(query);
                resultSet.next();
                enemyStrength.setText(resultSet.getString("enemy_str"));
                enemyDexterity.setText(resultSet.getString("enemy_dex"));
                enemyConstitution.setText(resultSet.getString("enemy_con"));
                enemyIntelligence.setText(resultSet.getString("enemy_int"));
                enemyWisdom.setText(resultSet.getString("enemy_wis"));
                enemyCharisma.setText(resultSet.getString("enemy_cha"));
                enemyStrengthModifier.setText(resultSet.getString("enemy_str_mod"));
                enemyDexterityModifier.setText(resultSet.getString("enemy_dex_mod"));
                enemyConstitutionModifier.setText(resultSet.getString("enemy_con_mod"));
                enemyIntelligenceModifier.setText(resultSet.getString("enemy_int_mod"));
                enemyWisdomModifier.setText(resultSet.getString("enemy_wis_mod"));
                enemyCharismaModifier.setText(resultSet.getString("enemy_cha_mod"));
                query = "SELECT enemy_image_url FROM enemies_images WHERE enemy_id = " + enemyID;
                resultSet = statement.executeQuery(query);
                resultSet.next();
                String imageURL = resultSet.getString("enemy_image_url");
                if (imageURL.equals("None")){
                    statsPane.setLayoutY(-2);
                    enemyPicture.setVisible(false);
                }
                else{
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageURL)));
                    enemyPicture.setImage(image);
                    centerImage(enemyPicture);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reduceCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reduceCoeff;
            h = img.getHeight() * reduceCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void setEnemyID(int enemyID) {
        this.enemyID = enemyID;
    }
}
