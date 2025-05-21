package exogenesis.content;

import arc.Core;
import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.util.Scaling;
import arc.util.Strings;
import exogenesis.type.DamageType;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.type.UnitType;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

import static exogenesis.content.ExoDamageTypes.*;
import static exogenesis.content.ExoUnitTypeResistances.resistancesMap;
import static mindustry.Vars.content;

public class ExoPostProcess {
    public static void load(){
        compatibilityTest();

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
            if (block instanceof ItemTurret itemTurret) processAmmoStat(block, itemTurret.ammoTypes);
            if (block instanceof LiquidTurret liquidTurret) processAmmoStat(block, liquidTurret.ammoTypes);
            if (block instanceof PowerTurret powerTurret) processAmmoStat(block, ObjectMap.of(powerTurret, powerTurret.shootType));
            if (block instanceof ContinuousTurret continuousTurret) processAmmoStat(block, ObjectMap.of(continuousTurret, continuousTurret.shootType));
            if (block instanceof ContinuousLiquidTurret continuousLiquidTurret) processAmmoStat(block, continuousLiquidTurret.ammoTypes);
        }

        for (UnitType unitType: content.units()){
            unitType.checkStats();
            var map = unitType.stats.toMap();
            if (map.get(StatCat.function) != null && map.get(StatCat.function).get(Stat.weapons) != null){
                unitType.stats.remove(Stat.weapons);
                unitType.stats.add(Stat.weapons, ExoStatValues.weapons(unitType, unitType.weapons));
            }
        }
    }

    private static void processAmmoStat(Block block, ObjectMap<? extends UnlockableContent, BulletType> ammo){
        block.checkStats();
        var map = block.stats.toMap();
        if (map.get(StatCat.function) != null && map.get(StatCat.function).get(Stat.ammo) != null){
            block.stats.remove(Stat.ammo);
            if (block instanceof ContinuousLiquidTurret continuousLiquidTurret){
                block.stats.add(Stat.ammo, table -> {
                    table.row();
                    StatValues.number(continuousLiquidTurret.liquidConsumed * 60f, StatUnit.perSecond, true).display(table);
                });
            }
            block.stats.add(Stat.ammo, ExoStatValues.ammo(ammo, 0, false));
        }
    }
    
    private static void compatibilityTest(){
        ExoUnitTypeResistances.applyResistance("new-horizon-nucleoid", kinetic, 0.95f, explosive, 0.95f, pierce, 0.95f, energy, 0.95f, thermal, 0.95f, cryogenic, 0.95f, radiation, 0.95f, graviton , 0.95f);
        ExoUnitTypeResistances.applyResistance("allure-0b17-hellfire", kinetic, 0f, explosive, 0f, pierce, 0.0001f, energy, 2f, thermal, 2f, cryogenic, 2f, radiation, 2f, graviton , 0.8f);
    }
}
