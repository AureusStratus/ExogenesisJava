package exogenesis.type.abilities;


import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import arc.util.Strings;
import arc.util.Time;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import exogenesis.content.ExoOtherStats;

import static mindustry.Vars.tilesize;

public class AccumulateAccelerate extends Ability {
    public float reloadMultiplier = 1f;
    public float maxMultiplier = 5f;
    public float increasePerTick = 0.2f / 60f;
    public float decreasePerTick = 2f / 60f;

    @Override
    public void update(Unit unit) {
        super.update(unit);
        float increment = (unit.isShooting? increasePerTick: -decreasePerTick) * Time.delta;
        reloadMultiplier = Mathf.clamp(reloadMultiplier + increment, 1, maxMultiplier);
        unit.reloadMultiplier *= reloadMultiplier;
    }

    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + ExoOtherStats.increaseWhenShooting.localized() + ": [white]+" + Strings.autoFixed(increasePerTick * 60 * 100, 0) + "%" + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + ExoOtherStats.decreaseNotShooting.localized() + ": [white]-" +  Strings.autoFixed(decreasePerTick * 60 * 100, 0) + "%" + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + ExoOtherStats.maxBoostPercent.localized() + ": [white]" +  Strings.autoFixed(maxMultiplier * 100, 0) + "%");
    }
}
