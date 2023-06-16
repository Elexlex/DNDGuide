package module;

import DnD.Spell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpellTest {

    @Test
    void testGetters() {
        int spellID = 1;
        String spellName = "Fireball";
        String spellLevel = "3rd";
        String spellSchool = "Evocation";
        String spellCastingTime = "1 action";

        Spell spell = new Spell(spellID, spellName, spellLevel, spellSchool, spellCastingTime);

        Assertions.assertEquals(spellID, spell.getSpellID());
        Assertions.assertEquals(spellName, spell.getSpellName());
        Assertions.assertEquals(spellLevel, spell.getSpellLevel());
        Assertions.assertEquals(spellSchool, spell.getSpellSchool());
        Assertions.assertEquals(spellCastingTime, spell.getSpellCastingTime());
    }
}