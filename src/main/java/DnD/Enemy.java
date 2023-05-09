package DnD;


public class Enemy {
    private final int enemyID;
    private final String enemyName;
    private final String enemyType;
    private final String enemyHP;
    private final String enemySize;
    private final String enemyChallenge;

    public Enemy(int enemyID, String enemyName, String enemyType, String enemySize, String enemyHP, String enemyChallenge){
        this.enemyID = enemyID;
        this.enemyName = enemyName;
        this.enemySize = enemySize;
        this.enemyHP = enemyHP;
        this.enemyType = enemyType;
        this.enemyChallenge = enemyChallenge;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyID() {
        return enemyID;
    }

    public String getEnemySize() {
        return enemySize;
    }

    public String getEnemyHP() {
        return enemyHP;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public String getEnemyChallenge() {
        return enemyChallenge;
    }
}

