package module;

import DnD.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnemyTest {

    @Test
    void testGetters() {
        int enemyID = 1;
        String enemyName = "Goblin";
        String enemyType = "Humanoid";
        String enemySize = "Small";
        String enemyHP = "7 (2d6)";
        String enemyChallenge = "1/4";

        Enemy enemy = new Enemy(enemyID, enemyName, enemyType, enemySize, enemyHP, enemyChallenge);

        Assertions.assertEquals(enemyID, enemy.getEnemyID());
        Assertions.assertEquals(enemyName, enemy.getEnemyName());
        Assertions.assertEquals(enemyType, enemy.getEnemyType());
        Assertions.assertEquals(enemySize, enemy.getEnemySize());
        Assertions.assertEquals(enemyHP, enemy.getEnemyHP());
        Assertions.assertEquals(enemyChallenge, enemy.getEnemyChallenge());
    }
}
