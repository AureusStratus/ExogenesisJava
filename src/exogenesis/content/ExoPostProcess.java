package exogenesis.content;

import arc.Core;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import static exogenesis.content.ExoUnitTypeResistances.resistancesMap;
import static mindustry.Vars.content;

public class ExoPostProcess {
    public static void load(){
        displayUnitResistanceStat();
    }

    public static void displayUnitResistanceStat(){
        content.units().each(unit-> {
            unit.checkStats();
            if (resistancesMap.get(unit) != null && !resistancesMap.get(unit).isEmpty()){
                unit.stats.add(ExoStats.resistance, table -> {
                    table.row();
                    resistancesMap.get(unit).each(entry -> {
                        table.table(Styles.grayPanel, t -> {
                            t.image(entry.key.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit).with(i -> StatValues.withTooltip(i, entry.key));
                            t.table(info -> info.add(entry.key.localizedName).left()).left();
                            t.table(res -> {
                                res.right();
                                String num = Strings.autoFixed(resistancesMap.get(unit).get(entry.key, 0f) * 100, 0);
                                res.add(resistancesMap.get(unit).get(entry.key, 0f) < 0 ? Core.bundle.format("exo.damage-resistance", num.replaceFirst("-", "")) : Core.bundle.format("exo.damage-weakness", num));
                            }).right().grow().pad(10f);
                        }).growX().pad(5);
                        table.row();
                    });
                });
            }
        });
    }
}
