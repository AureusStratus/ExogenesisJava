package exogenesis.content;

import arc.Core;
import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatValues;

import static exogenesis.content.ExoUnitTypeResistances.resistancesMap;
import static mindustry.Vars.content;

public class ExoPostProcess {
    public static void load(){
        displayUnitResistanceStat();
        displayBulletDamageTypeStat();
    }

    public static void displayUnitResistanceStat(){
        content.units().each(unit-> {
            unit.checkStats();
            if (resistancesMap.get(unit) != null && !resistancesMap.get(unit).isEmpty()){
                unit.stats.add(ExoStats.resistance, table -> {
                    table.row();
                    resistancesMap.get(unit).each((damageType, multiplier) -> {
                        table.table(Styles.grayPanel, t -> {
                            t.image(damageType.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit).with(i -> StatValues.withTooltip(i, damageType));
                            t.table(info -> info.add(damageType.localizedName).left()).left();
                            t.table(res -> {
                                res.right();
                                String num = Strings.autoFixed(multiplier * 100, 0);
                                res.add(multiplier > 0 ? Core.bundle.format("exo.damage-resistance-unit", num) : Core.bundle.format("exo.damage-weakness-unit", num.replaceFirst("-", "")));
                            }).right().grow().pad(10f);
                        }).growX().pad(5);
                        table.row();
                    });
                });
            }
        });
    }

    public static void displayBulletDamageTypeStat(){
        for (Block block: content.blocks()){
            if (block instanceof ItemTurret itemTurret){
                block.checkStats();
                var map = block.stats.toMap();
                if (map.get(StatCat.function) != null && map.get(StatCat.function).get(Stat.ammo) != null){
                    block.stats.remove(Stat.ammo);
                    block.stats.add(Stat.ammo, ExoStatValues.ammo(itemTurret.ammoTypes, 0, false));
                }
            }
        }
    }
}
