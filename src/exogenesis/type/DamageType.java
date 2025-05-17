package exogenesis.type;

import arc.Core;
import exogenesis.content.ExoStatValues;
import exogenesis.content.ExoStats;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;

import static mindustry.ctype.ContentType.effect_UNUSED;
import static mindustry.ctype.ContentType.mech_UNUSED;

public class DamageType extends UnlockableContent {
    public DamageType(String name) {
        super(name);

        localizedName = Core.bundle.get("damage." + name + ".name");
        description = Core.bundle.get("damage." + name + ".description");
        details = Core.bundle.getOrNull("damage." + name + ".details");

        alwaysUnlocked = true;
    }

    //take the space of effect_UNUSED, hope there are no other mods that using this
    @Override
    public ContentType getContentType() {
        return effect_UNUSED;
    }

    @Override
    public void load() {
        super.load();
        fullIcon = uiIcon = Core.atlas.find(name);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(ExoStats.resistance, ExoStatValues.resistance(this));
    }
}
