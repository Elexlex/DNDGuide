package DnD;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CharacterPageController implements Initializable {

    @FXML private TextField strength;
    @FXML private TextField dexterity;
    @FXML private TextField constitution;
    @FXML private TextField intelligence;
    @FXML private TextField wisdom;
    @FXML private TextField charisma;

    @FXML private Label strengthModifier;
    @FXML private Label dexterityModifier;
    @FXML private Label constitutionModifier;
    @FXML private Label intelligenceModifier;
    @FXML private Label wisdomModifier;
    @FXML private Label charismaModifier;

    @FXML private TextField passiveWisdom;
    @FXML private TextField proficiencyBonus;
    @FXML private CheckBox inspiration;

    @FXML private TextField strengthSaving;
    @FXML private TextField dexteritySaving;
    @FXML private TextField constitutionSaving;
    @FXML private TextField intelligenceSaving;
    @FXML private TextField wisdomSaving;
    @FXML private TextField charismaSaving;

    @FXML private RadioButton strengthRadio;
    @FXML private RadioButton dexterityRadio;
    @FXML private RadioButton constitutionRadio;
    @FXML private RadioButton intelligenceRadio;
    @FXML private RadioButton wisdomRadio;
    @FXML private RadioButton charismaRadio;

    @FXML private TextField acrobaticsSkill;
    @FXML private TextField animalHandlingSkill;
    @FXML private TextField arcanaSkill;
    @FXML private TextField athleticsSkill;
    @FXML private TextField deceptionSkill;
    @FXML private TextField historySkill;
    @FXML private TextField insightSkill;
    @FXML private TextField intimidationSkill;
    @FXML private TextField investigationSkill;
    @FXML private TextField medicineSkill;
    @FXML private TextField natureSkill;
    @FXML private TextField perceptionSkill;
    @FXML private TextField performanceSkill;
    @FXML private TextField persuasionSkill;
    @FXML private TextField religionSkill;
    @FXML private TextField sleightOfHandSkill;
    @FXML private TextField stealthSkill;
    @FXML private TextField survivalSkill;

    @FXML private RadioButton acrobaticsRadio;
    @FXML private RadioButton animalHandlingRadio;
    @FXML private RadioButton arcanaRadio;
    @FXML private RadioButton athleticsRadio;
    @FXML private RadioButton deceptionRadio;
    @FXML private RadioButton historyRadio;
    @FXML private RadioButton insightRadio;
    @FXML private RadioButton intimidationRadio;
    @FXML private RadioButton investigationRadio;
    @FXML private RadioButton medicineRadio;
    @FXML private RadioButton natureRadio;
    @FXML private RadioButton perceptionRadio;
    @FXML private RadioButton performanceRadio;
    @FXML private RadioButton persuasionRadio;
    @FXML private RadioButton religionRadio;
    @FXML private RadioButton sleightOfHandRadio;
    @FXML private RadioButton stealthRadio;
    @FXML private RadioButton survivalRadio;

    @FXML private TextField characterName;
    @FXML private TextField characterClass;
    @FXML private TextField characterRace;
    @FXML private TextField characterBackground;
    @FXML private TextField characterAlignment;
    @FXML private TextField playerName;
    @FXML private TextField characterEXP;

    @FXML private TextField armorClass;
    @FXML private TextField initiative;
    @FXML private TextField speed;
    @FXML private TextField maximumHP;
    @FXML private TextField currentHP;
    @FXML private TextField temporaryHP;
    @FXML private TextField hitDice;
    @FXML private TextField hitDiceNumber;

    @FXML private RadioButton saveSuccess1;
    @FXML private RadioButton saveSuccess2;
    @FXML private RadioButton saveSuccess3;
    @FXML private RadioButton saveFailure1;
    @FXML private RadioButton saveFailure2;
    @FXML private RadioButton saveFailure3;

    @FXML private TextField copperMoney;
    @FXML private TextField silverMoney;
    @FXML private TextField electrumMoney;
    @FXML private TextField goldMoney;
    @FXML private TextField platinumMoney;

    @FXML private TextField weaponName1;
    @FXML private TextField weaponName2;
    @FXML private TextField weaponName3;
    @FXML private TextField attackBonus1;
    @FXML private TextField attackBonus2;
    @FXML private TextField attackBonus3;
    @FXML private TextField damageBonus1;
    @FXML private TextField damageBonus2;
    @FXML private TextField damageBonus3;

    @FXML private TextArea proficiencies;
    @FXML private TextArea attacks;
    @FXML private TextArea equipment;
    @FXML private TextArea traits;
    @FXML private TextArea ideals;
    @FXML private TextArea bonds;
    @FXML private TextArea flaws;
    @FXML private TextArea features;

    @FXML private Button editButton;
    @FXML private Button saveButton;

    private boolean newPageCheck;
    private int characterID;

    public void initialize(URL url, ResourceBundle resourceBundle){
        Platform.runLater(() -> {

            ObservableList<TextField> textFieldList = FXCollections.observableArrayList();

            textFieldList.addAll(
                    strength, dexterity, constitution, intelligence, wisdom, charisma,
                    passiveWisdom, proficiencyBonus, strengthSaving, dexteritySaving,
                    constitutionSaving, intelligenceSaving, wisdomSaving, charismaSaving,
                    acrobaticsSkill, animalHandlingSkill, arcanaSkill, athleticsSkill,
                    deceptionSkill, historySkill, insightSkill, intimidationSkill,
                    investigationSkill, medicineSkill, natureSkill, perceptionSkill,
                    performanceSkill, persuasionSkill, religionSkill, sleightOfHandSkill,
                    stealthSkill, survivalSkill, characterName, characterClass, characterRace,
                    characterBackground, characterAlignment, playerName, characterEXP,
                    armorClass, initiative, speed, maximumHP, currentHP, temporaryHP,
                    hitDice, hitDiceNumber, copperMoney, silverMoney, electrumMoney,
                    goldMoney, platinumMoney, weaponName1, weaponName2, weaponName3,
                    attackBonus1, attackBonus2, attackBonus3, damageBonus1, damageBonus2,
                    damageBonus3
            );

            ObservableList<RadioButton> radioButtonsList = FXCollections.observableArrayList();
            radioButtonsList.addAll(
                    saveSuccess1, saveSuccess2, saveSuccess3,
                    saveFailure1, saveFailure2, saveFailure3,
                    acrobaticsRadio, animalHandlingRadio, arcanaRadio,
                    athleticsRadio, deceptionRadio, historyRadio,
                    insightRadio, intimidationRadio, investigationRadio,
                    medicineRadio, natureRadio, perceptionRadio,
                    performanceRadio, persuasionRadio, religionRadio,
                    sleightOfHandRadio, stealthRadio, survivalRadio,
                    strengthRadio, dexterityRadio, constitutionRadio,
                    intelligenceRadio, wisdomRadio, charismaRadio
            );

            editButton.setOnMouseClicked(e -> {
                setVisibility(textFieldList, radioButtonsList, true);
            });

            saveButton.setOnMouseClicked(e -> {
                setVisibility(textFieldList, radioButtonsList, false);
            });

            System.out.println(newPageCheck);
            if (!newPageCheck) {
                try {
                    ConnectionDB connectionDB = new ConnectionDB();
                    String query = "SELECT * FROM character_page WHERE character_id = " + characterID;
                    Statement statement = connectionDB.getConnectionDB().createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    resultSet.next();


                    characterName.setText(resultSet.getString(2));
                    characterClass.setText(resultSet.getString(3));
                    characterRace.setText(resultSet.getString(4));
                    characterBackground.setText(resultSet.getString(5));
                    characterAlignment.setText(resultSet.getString(6));
                    playerName.setText(resultSet.getString(7));
                    characterEXP.setText(resultSet.getString(8));
                    armorClass.setText(resultSet.getString(9));
                    initiative.setText(resultSet.getString(10));
                    speed.setText(resultSet.getString(11));
                    maximumHP.setText(resultSet.getString(12));
                    currentHP.setText(resultSet.getString(13));
                    temporaryHP.setText(resultSet.getString(14));
                    hitDiceNumber.setText(resultSet.getString(15));
                    hitDice.setText(resultSet.getString(16));
                    saveSuccess1.setSelected(resultSet.getBoolean(17));
                    saveSuccess2.setSelected(resultSet.getBoolean(18));
                    saveSuccess3.setSelected(resultSet.getBoolean(19));
                    saveFailure1.setSelected(resultSet.getBoolean(20));
                    saveFailure2.setSelected(resultSet.getBoolean(21));
                    saveFailure3.setSelected(resultSet.getBoolean(22));
                    strength.setText(resultSet.getString(23));
                    dexterity.setText(resultSet.getString(24));
                    constitution.setText(resultSet.getString(25));
                    intelligence.setText(resultSet.getString(26));
                    wisdom.setText(resultSet.getString(27));
                    charisma.setText(resultSet.getString(28));

                    modifier(strengthModifier, strength.getText());
                    modifier(dexterityModifier, dexterity.getText());
                    modifier(constitutionModifier, constitution.getText());
                    modifier(intelligenceModifier, intelligence.getText());
                    modifier(wisdomModifier, wisdom.getText());
                    modifier(charismaModifier, charisma.getText());


                    strengthSaving.setText(resultSet.getString(29));
                    dexteritySaving.setText(resultSet.getString(30));
                    constitutionSaving.setText(resultSet.getString(31));
                    intelligenceSaving.setText(resultSet.getString(32));
                    wisdomSaving.setText(resultSet.getString(33));
                    charismaSaving.setText(resultSet.getString(34));
                    acrobaticsSkill.setText(resultSet.getString(35));
                    animalHandlingSkill.setText(resultSet.getString(36));
                    arcanaSkill.setText(resultSet.getString(37));
                    athleticsSkill.setText(resultSet.getString(38));
                    deceptionSkill.setText(resultSet.getString(39));
                    historySkill.setText(resultSet.getString(40));
                    insightSkill.setText(resultSet.getString(41));
                    intimidationSkill.setText(resultSet.getString(42));
                    investigationSkill.setText(resultSet.getString(43));
                    medicineSkill.setText(resultSet.getString(44));
                    natureSkill.setText(resultSet.getString(45));
                    perceptionSkill.setText(resultSet.getString(46));
                    performanceSkill.setText(resultSet.getString(47));
                    persuasionSkill.setText(resultSet.getString(48));
                    religionSkill.setText(resultSet.getString(49));
                    sleightOfHandSkill.setText(resultSet.getString(50));
                    stealthSkill.setText(resultSet.getString(51));
                    survivalSkill.setText(resultSet.getString(52));
                    strengthRadio.setSelected(resultSet.getBoolean(53));
                    dexterityRadio.setSelected(resultSet.getBoolean(54));
                    constitutionRadio.setSelected(resultSet.getBoolean(55));
                    intelligenceRadio.setSelected(resultSet.getBoolean(56));
                    wisdomRadio.setSelected(resultSet.getBoolean(57));
                    charismaRadio.setSelected(resultSet.getBoolean(58));
                    acrobaticsRadio.setSelected(resultSet.getBoolean(59));
                    animalHandlingRadio.setSelected(resultSet.getBoolean(60));
                    arcanaRadio.setSelected(resultSet.getBoolean(61));
                    athleticsRadio.setSelected(resultSet.getBoolean(62));
                    deceptionRadio.setSelected(resultSet.getBoolean(63));
                    historyRadio.setSelected(resultSet.getBoolean(64));
                    insightRadio.setSelected(resultSet.getBoolean(65));
                    intimidationRadio.setSelected(resultSet.getBoolean(66));
                    investigationRadio.setSelected(resultSet.getBoolean(67));
                    medicineRadio.setSelected(resultSet.getBoolean(68));
                    natureRadio.setSelected(resultSet.getBoolean(69));
                    perceptionRadio.setSelected(resultSet.getBoolean(70));
                    performanceRadio.setSelected(resultSet.getBoolean(71));
                    persuasionRadio.setSelected(resultSet.getBoolean(72));
                    religionRadio.setSelected(resultSet.getBoolean(73));
                    sleightOfHandRadio.setSelected(resultSet.getBoolean(74));
                    stealthRadio.setSelected(resultSet.getBoolean(75));
                    survivalRadio.setSelected(resultSet.getBoolean(76));
                    weaponName1.setText(resultSet.getString(77));
                    weaponName2.setText(resultSet.getString(78));
                    weaponName3.setText(resultSet.getString(79));
                    attackBonus1.setText(resultSet.getString(80));
                    attackBonus2.setText(resultSet.getString(81));
                    attackBonus3.setText(resultSet.getString(82));
                    damageBonus1.setText(resultSet.getString(83));
                    damageBonus2.setText(resultSet.getString(84));
                    damageBonus3.setText(resultSet.getString(85));
                    traits.setText(resultSet.getString(86));
                    ideals.setText(resultSet.getString(87));
                    bonds.setText(resultSet.getString(88));
                    flaws.setText(resultSet.getString(89));
                    proficiencies.setText(resultSet.getString(90));
                    equipment.setText(resultSet.getString(91));
                    attacks.setText(resultSet.getString(92));
                    features.setText(resultSet.getString(93));
                    copperMoney.setText(resultSet.getString(94));
                    silverMoney.setText(resultSet.getString(95));
                    electrumMoney.setText(resultSet.getString(96));
                    goldMoney.setText(resultSet.getString(97));
                    platinumMoney.setText(resultSet.getString(98));
                    passiveWisdom.setText(resultSet.getString(99));
                    proficiencyBonus.setText(resultSet.getString(100));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void modifier(Label modifier, String stat){
        if (!stat.equals("")){
            int statNumber = Integer.parseInt(stat);
            String modifierNumber = "" + (statNumber-10)/2;
            if (Integer.parseInt(modifierNumber) > 0)
                modifierNumber = "+" + modifierNumber;
            modifier.setText(modifierNumber);
        }
    }

    public void statReleased(KeyEvent keyEvent){
        TextField textField = (TextField) keyEvent.getSource();
        String id = textField.getId();
        String stat = textField.getText();
        if (!stat.equals("")){
            int statNumber = Integer.parseInt(textField.getText());
            String modifier = "" + (statNumber-10)/2;
            if (Integer.parseInt(modifier) > 0)
                modifier = "+" + modifier;
            switch (id) {
                case "STR" -> strengthModifier.setText(modifier);
                case "DEX" -> dexterityModifier.setText(modifier);
                case "CON" -> constitutionModifier.setText(modifier);
                case "INT" -> intelligenceModifier.setText(modifier);
                case "WIS" -> wisdomModifier.setText(modifier);
                case "CHA" -> charismaModifier.setText(modifier);
            }
        }
        else {
            switch (id) {
                case "STR" -> strengthModifier.setText("");
                case "DEX" -> dexterityModifier.setText("");
                case "CON" -> constitutionModifier.setText("");
                case "INT" -> intelligenceModifier.setText("");
                case "WIS" -> wisdomModifier.setText("");
                case "CHA" -> charismaModifier.setText("");
            }
        }
    }

    public void onlyNumbers(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        String text = textField.getText();
        String filtered = text.replaceAll("[^\\d]", "");
        textField.setText(filtered);
        textField.positionCaret(textField.getLength());
        textLimit(keyEvent);
    }

    private void textLimit(KeyEvent keyEvent) {
        int maxSymbols = 0;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        TextField textField = (TextField) keyEvent.getSource();
        String text = textField.getText();
        if (stackTrace.length > 2) {
            String callingMethod = stackTrace[2].getMethodName();

            if (callingMethod.equals("onlyNumbers") || callingMethod.equals("onlyNumbersSymbols")) {
                maxSymbols = 3;
            }

            if (callingMethod.equals("onlyWeapons")) {
                maxSymbols = 15;
            }

            if (callingMethod.equals("onlyDamage")) {
                maxSymbols = 13;
            }

            if (callingMethod.equals("onlyCharacter")) {
                maxSymbols = 20;
            }

            if (callingMethod.equals("onlyAttackBonus")) {
                maxSymbols = 5;
            }

            if (callingMethod.equals("onlyName")) {
                maxSymbols = 30;
            }
        }
        if (text.length() > maxSymbols) {
            String truncatedText = text.substring(0, maxSymbols);
            textField.setText(truncatedText);
            textField.positionCaret(textField.getLength());
        }
    }

    public void onlyNumbersSymbols(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        String text = textField.getText();
        String filtered = text.replaceAll("[^\\d+-]", "");
        textField.setText(filtered);
        textField.positionCaret(textField.getLength());
        textLimit(keyEvent);
    }
    public void onlyWeapons(KeyEvent keyEvent){
        textLimit(keyEvent);
    }

    public void onlyDamage(KeyEvent keyEvent){
        textLimit(keyEvent);
    }

    public void onlyCharacter(KeyEvent keyEvent){
        textLimit(keyEvent);
    }

    public void onlyName(KeyEvent keyEvent){
        textLimit(keyEvent);
    }

    public void onlyAttackBonus(KeyEvent keyEvent){
        textLimit(keyEvent);
    }
    
    public void onlyDescription(KeyEvent keyEvent){
        final int maxSymbols = 1000;
        TextArea textArea = (TextArea) keyEvent.getSource();
        String text = textArea.getText();
        if (text.length() > maxSymbols) {
            String truncatedText = text.substring(0, maxSymbols);
            textArea.setText(truncatedText);
            textArea.positionCaret(textArea.getLength());
        }
    }

    public void onSaveButtonClicked(){

        System.out.println("Hello");
        System.out.println(newPageCheck);
        ConnectionDB connection = new ConnectionDB();
        int id = 0;
        try {
            Statement statement = connection.getConnectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(character_id) as character_id FROM character_page");
            resultSet.next();
            id = resultSet.getInt("character_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id == 0){
            id = 1;
        }
        else if (newPageCheck){
            id++;
        }
        try  {
            PreparedStatement statement;
            if (!newPageCheck){
                statement = connection.getConnectionDB().prepareStatement(
                        "UPDATE character_page SET character_id = ?, character_name = ?, character_class_level = ?, character_race = ?, " +
                                "character_background = ?, character_alignment = ?, character_player_name = ?, " +
                                "character_exp = ?, character_armor = ?, character_initiative = ?, character_speed = ?, " +
                                "character_max_hp = ?, character_hp = ?, character_temporary_hp = ?, character_dice_count = ?, " +
                                "character_dice = ?, character_death_save1_success = ?, character_death_save2_success = ?, " +
                                "character_death_save3_success = ?, character_death_save1_failure = ?, " +
                                "character_death_save2_failure = ?, character_death_save3_failure = ?, character_str_stat = ?, " +
                                "character_dex_stat = ?, character_con_stat = ?, character_int_stat = ?, character_wis_stat = ?, " +
                                "character_cha_stat = ?, character_str_saving = ?, character_dex_saving = ?, character_con_saving = ?, " +
                                "character_int_saving = ?, character_wis_saving = ?, character_cha_saving = ?, character_acrobatics_skill = ?, " +
                                "character_animal_handling_skill = ?, character_arcana_skill = ?, character_athletics_skill = ?, " +
                                "character_deception_skill = ?, character_history_skill = ?, character_insight_skill = ?, " +
                                "character_intimidation_skill = ?, character_investigation_skill = ?, character_medicine_skill = ?, " +
                                "character_nature_skill = ?, character_perception_skill = ?, character_performance_skill = ?, " +
                                "character_persuassion_skill = ?, character_religion_skill = ?, character_sleight_of_hand_skill = ?, " +
                                "character_stealth_skill = ?, character_survival_skill = ?, character_str_mastery = ?, " +
                                "character_dex_mastery = ?, character_con_mastery = ?, character_int_mastery = ?, character_wis_mastery = ?, " +
                                "character_cha_mastery = ?, character_acrobatics_mastery = ?, character_animal_handling_mastery = ?, " +
                                "character_arcana_mastery = ?, character_athletics_mastery = ?, character_deception_mastery = ?, " +
                                "character_history_mastery = ?, character_insight_mastery = ?, character_intimidation_mastery = ?, " +
                                "character_investigation_mastery = ?, character_medicine_mastery = ?, character_nature_mastery = ?, " +
                                "character_perception_mastery = ?, character_performance_mastery = ?, character_persuassion_mastery = ?, " +
                                "character_religion_mastery = ?, character_sleight_of_hand_mastery = ?, character_stealth_mastery = ?, " +
                                "character_survival_mastery = ?, character_weapon_name1 = ?, character_weapon_name2 = ?, " +
                                "character_weapon_name3 = ?, character_weapon_atk_bonus1 = ?, character_weapon_atk_bonus2 = ?, " +
                                "character_weapon_atk_bonus3 = ?, character_weapon_damage1 = ?, character_weapon_damage2 = ?, " +
                                "character_weapon_damage3 = ?, character_personality_traits = ?, character_ideals = ?, " +
                                "character_bonds = ?, character_flaws = ?, character_proficiencies = ?, character_equipment = ?, " +
                                "character_attacks = ?, character_features = ?, character_cp = ?, character_sp = ?, " +
                                "character_ep = ?, character_gp = ?, character_pp = ?, character_inspiration = ?, " +
                                "character_passive_wisdom = ?, character_proficiency_bonus = ? WHERE character_id = " + id);
                System.out.println("Updated");
            }
            else {
                statement = connection.getConnectionDB().prepareStatement(
                    "INSERT INTO character_page (character_id, character_name, character_class_level, character_race," +
                            " character_background, character_alignment, character_player_name, character_exp," +
                            " character_armor, character_initiative, character_speed, character_max_hp, character_hp," +
                            " character_temporary_hp, character_dice_count, character_dice, character_death_save1_success," +
                            " character_death_save2_success, character_death_save3_success, character_death_save1_failure," +
                            " character_death_save2_failure, character_death_save3_failure, character_str_stat," +
                            " character_dex_stat, character_con_stat, character_int_stat, character_wis_stat," +
                            " character_cha_stat, character_str_saving, character_dex_saving, character_con_saving," +
                            " character_int_saving, character_wis_saving, character_cha_saving, character_acrobatics_skill," +
                            " character_animal_handling_skill, character_arcana_skill, character_athletics_skill," +
                            " character_deception_skill, character_history_skill, character_insight_skill," +
                            " character_intimidation_skill, character_investigation_skill, character_medicine_skill," +
                            " character_nature_skill, character_perception_skill, character_performance_skill," +
                            " character_persuassion_skill, character_religion_skill, character_sleight_of_hand_skill," +
                            " character_stealth_skill, character_survival_skill, character_str_mastery," +
                            " character_dex_mastery, character_con_mastery, character_int_mastery, character_wis_mastery," +
                            " character_cha_mastery, character_acrobatics_mastery, character_animal_handling_mastery," +
                            " character_arcana_mastery, character_athletics_mastery, character_deception_mastery," +
                            " character_history_mastery, character_insight_mastery, character_intimidation_mastery," +
                            " character_investigation_mastery, character_medicine_mastery, character_nature_mastery," +
                            " character_perception_mastery, character_performance_mastery, character_persuassion_mastery," +
                            " character_religion_mastery, character_sleight_of_hand_mastery, character_stealth_mastery," +
                            " character_survival_mastery, character_weapon_name1, character_weapon_name2," +
                            " character_weapon_name3, character_weapon_atk_bonus1, character_weapon_atk_bonus2," +
                            " character_weapon_atk_bonus3, character_weapon_damage1, character_weapon_damage2," +
                            " character_weapon_damage3, character_personality_traits, character_ideals, character_bonds," +
                            " character_flaws, character_proficiencies, character_equipment, character_attacks," +
                            " character_features, character_cp, character_sp, character_ep, character_gp, character_pp," +
                            " character_inspiration, character_passive_wisdom, character_proficiency_bonus) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                newPageCheck = false;
                System.out.println("Inserted");
            }

            updateStatement(statement, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStatement(PreparedStatement statement, int id) throws SQLException {
            statement.setString(1, String.valueOf(id));
            statement.setString(2, characterName.getText());
            statement.setString(3, characterClass.getText());
            statement.setString(4, characterRace.getText());
            statement.setString(5, characterBackground.getText());
            statement.setString(6, characterAlignment.getText());
            statement.setString(7, playerName.getText());
            statement.setString(8, characterEXP.getText());
            statement.setString(9, armorClass.getText());
            statement.setString(10, initiative.getText());
            statement.setString(11, speed.getText());
            statement.setString(12, maximumHP.getText());
            statement.setString(13, currentHP.getText());
            statement.setString(14, temporaryHP.getText());
            statement.setString(15, hitDiceNumber.getText());
            statement.setString(16, hitDice.getText());
            statement.setBoolean(17, saveSuccess1.isSelected());
            statement.setBoolean(18, saveSuccess2.isSelected());
            statement.setBoolean(19, saveSuccess3.isSelected());
            statement.setBoolean(20, saveFailure1.isSelected());
            statement.setBoolean(21, saveFailure2.isSelected());
            statement.setBoolean(22, saveFailure3.isSelected());
            statement.setString(23, strength.getText());
            statement.setString(24, dexterity.getText());
            statement.setString(25, constitution.getText());
            statement.setString(26, intelligence.getText());
            statement.setString(27, wisdom.getText());
            statement.setString(28, charisma.getText());
            statement.setString(29, strengthSaving.getText());
            statement.setString(30, dexteritySaving.getText());
            statement.setString(31, constitutionSaving.getText());
            statement.setString(32, intelligenceSaving.getText());
            statement.setString(33, wisdomSaving.getText());
            statement.setString(34, charismaSaving.getText());
            statement.setString(35, acrobaticsSkill.getText());
            statement.setString(36, animalHandlingSkill.getText());
            statement.setString(37, arcanaSkill.getText());
            statement.setString(38, athleticsSkill.getText());
            statement.setString(39, deceptionSkill.getText());
            statement.setString(40, historySkill.getText());
            statement.setString(41, insightSkill.getText());
            statement.setString(42, intimidationSkill.getText());
            statement.setString(43, investigationSkill.getText());
            statement.setString(44, medicineSkill.getText());
            statement.setString(45, natureSkill.getText());
            statement.setString(46, perceptionSkill.getText());
            statement.setString(47, performanceSkill.getText());
            statement.setString(48, persuasionSkill.getText());
            statement.setString(49, religionSkill.getText());
            statement.setString(50, sleightOfHandSkill.getText());
            statement.setString(51, stealthSkill.getText());
            statement.setString(52, survivalSkill.getText());
            statement.setBoolean(53, (strengthRadio.isSelected()));
            statement.setBoolean(54, dexterityRadio.isSelected());
            statement.setBoolean(55, constitutionRadio.isSelected());
            statement.setBoolean(56, intelligenceRadio.isSelected());
            statement.setBoolean(57, wisdomRadio.isSelected());
            statement.setBoolean(58, charismaRadio.isSelected());
            statement.setBoolean(59, acrobaticsRadio.isSelected());
            statement.setBoolean(60, animalHandlingRadio.isSelected());
            statement.setBoolean(61, arcanaRadio.isSelected());
            statement.setBoolean(62, athleticsRadio.isSelected());
            statement.setBoolean(63, deceptionRadio.isSelected());
            statement.setBoolean(64, historyRadio.isSelected());
            statement.setBoolean(65, insightRadio.isSelected());
            statement.setBoolean(66, intimidationRadio.isSelected());
            statement.setBoolean(67, investigationRadio.isSelected());
            statement.setBoolean(68, medicineRadio.isSelected());
            statement.setBoolean(69, natureRadio.isSelected());
            statement.setBoolean(70, perceptionRadio.isSelected());
            statement.setBoolean(71, performanceRadio.isSelected());
            statement.setBoolean(72, persuasionRadio.isSelected());
            statement.setBoolean(73, religionRadio.isSelected());
            statement.setBoolean(74, sleightOfHandRadio.isSelected());
            statement.setBoolean(75, stealthRadio.isSelected());
            statement.setBoolean(76, survivalRadio.isSelected());
            statement.setString(77, weaponName1.getText());
            statement.setString(78, weaponName2.getText());
            statement.setString(79, weaponName3.getText());
            statement.setString(80, attackBonus1.getText());
            statement.setString(81, attackBonus2.getText());
            statement.setString(82, attackBonus3.getText());
            statement.setString(83, damageBonus1.getText());
            statement.setString(84, damageBonus2.getText());
            statement.setString(85, damageBonus3.getText());
            statement.setString(86, traits.getText());
            statement.setString(87, ideals.getText());
            statement.setString(88, bonds.getText());
            statement.setString(89, flaws.getText());
            statement.setString(90, proficiencies.getText());
            statement.setString(91, equipment.getText());
            statement.setString(92, attacks.getText());
            statement.setString(93, features.getText());
            statement.setString(94, copperMoney.getText());
            statement.setString(95, silverMoney.getText());
            statement.setString(96, electrumMoney.getText());
            statement.setString(97, goldMoney.getText());
            statement.setString(98, platinumMoney.getText());
            statement.setBoolean(99, inspiration.isSelected());
            statement.setString(100, passiveWisdom.getText());
            statement.setString(101, proficiencyBonus.getText());
            statement.executeUpdate();
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public void setNewPageCheck(boolean newPageCheck){
        this.newPageCheck = newPageCheck;
    }

    private void setVisibility(ObservableList<TextField> textFieldList, ObservableList<RadioButton> radioButtonsList, boolean visibility){
        for (TextField textField : textFieldList) {
            textField.setEditable(visibility);
        }

        for (RadioButton radioButton : radioButtonsList) {
            radioButton.setDisable(!visibility);
        }

        inspiration.setDisable(!visibility);
        saveButton.setVisible(visibility);
        editButton.setVisible(!visibility);
    }
}
