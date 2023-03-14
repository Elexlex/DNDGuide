package DnDGuide;


import javafx.fxml.FXML;

public class Enemy {
    private final int enemyID;
    private final String enemyName;

    public Enemy(int enemyID, String enemyName){
        this.enemyID = enemyID;
        this.enemyName = enemyName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyID() {
        return enemyID;
    }
}

