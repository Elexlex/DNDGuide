package DnD;

public class Character {
    private final int characterID;
    private final String characterName;
    private final String characterClass;
    private final String characterRace;
    private final String playerName;

    public Character(int characterID, String characterName, String characterClass, String characterRace, String playerName){
        this.characterID = characterID;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.characterRace = characterRace;
        this.playerName = playerName;
    }

    public int getCharacterID() {
        return characterID;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public String getPlayerName() {
        return playerName;
    }
}
