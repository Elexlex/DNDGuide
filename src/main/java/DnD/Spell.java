package DnD;

public class Spell {
    private final int spellID;
    private final String spellName;
    private final String spellLevel;
    private final String spellSchool;
    private final String spellCastingTime;

    public Spell(int spellID, String spellName, String spellLevel, String spellSchool, String spellCastingTime){
        this.spellID = spellID;
        this.spellName = spellName;
        this.spellLevel = spellLevel;
        this.spellSchool = spellSchool;
        this.spellCastingTime = spellCastingTime;
    }

    public int getSpellID() {
        return spellID;
    }

    public String getSpellName() {
        return spellName;
    }

    public String getSpellLevel() {
        return spellLevel;
    }

    public String getSpellSchool() {
        return spellSchool;
    }

    public String getSpellCastingTime() {
        return spellCastingTime;
    }
}
