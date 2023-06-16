package module;

import DnD.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterTest {

    @Test
    void testGetters() {
        int characterID = 1;
        String characterName = "Gandalf";
        String characterClass = "Wizard";
        String characterRace = "Human";
        String playerName = "John Doe";

        Character character = new Character(characterID, characterName, characterClass, characterRace, playerName);

        Assertions.assertEquals(characterID, character.getCharacterID());
        Assertions.assertEquals(characterName, character.getCharacterName());
        Assertions.assertEquals(characterClass, character.getCharacterClass());
        Assertions.assertEquals(characterRace, character.getCharacterRace());
        Assertions.assertEquals(playerName, character.getPlayerName());
    }

    @Test
    void testCharacterIDConstructor() {
        int characterID = 1;

        Character character = new Character(characterID, "Gandalf", "Wizard", "Human", "John Doe");

        Assertions.assertEquals(characterID, character.getCharacterID());
    }

    @Test
    void testNullPlayerName() {
        int characterID = 1;
        String characterName = "Gandalf";
        String characterClass = "Wizard";
        String characterRace = "Human";
        String playerName = null;

        Character character = new Character(characterID, characterName, characterClass, characterRace, playerName);

        Assertions.assertNull(character.getPlayerName());
    }
}
