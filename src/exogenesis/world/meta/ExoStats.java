package exogenesis.world.meta;

import exogenesis.content.TypeMultipliers;
import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.DamageType;
import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Collapser;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectMap;
import arc.util.Log;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.content.StatusEffects;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Icon;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.ui.Styles;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValue;

import static mindustry.Vars.tilesize;

public class ExoStats{
    public static Stat typeDamage = new Stat("typeDamage"); //add "stat.typeDamage = Damage from Type" to bundle
    public static String spritePrefix = "exogenesis-damage-";

    public static void addTypeStatsUnit(){
        Vars.content.units().each(unit -> {
               unit.checkStats();
            if(TypeMultipliers.getMultipliers(unit) != null){
                unit.stats.add(typeDamage, damageTypes(TypeMultipliers.getMultipliers(unit)));
            }
            //just replaces all unit's weapon tables, I don't care at this point
            unit.stats.remove(Stat.weapons);
            unit.stats.add(Stat.weapons, table -> { //StatValues.weapons merged with weapon stats (with a single change)
                table.row();
                for(int i = 0; i < unit.weapons.size; i++){
                    Weapon weapon = unit.weapons.get(i);

                    if(weapon.flipSprite || !weapon.hasStats(unit)){
                        continue;
                    }

                    TextureRegion region = !weapon.name.isEmpty() ? Core.atlas.find(weapon.name + "-preview", weapon.region) : null;

                    table.table(Styles.grayPanel, w -> {
                        w.left().top().defaults().padRight(3).left();
                        if(region != null && region.found() && weapon.showStatSprite)
                            w.image(region).size(60).scaling(Scaling.bounded).left().top();
                        w.row();

                        if(weapon.inaccuracy > 0){
                            w.row();
                            w.add("[lightgray]" + Stat.inaccuracy.localized() + ": [white]" + (int) weapon.inaccuracy + " " + StatUnit.degrees.localized());
                        }
                        if(!weapon.alwaysContinuous && weapon.reload > 0){
                            w.row();
                            w.add("[lightgray]" + Stat.reload.localized() + ": " + (weapon.mirror ? "2x " : "") + "[white]" + Strings.autoFixed(60f / weapon.reload * weapon.shoot.shots, 2) + " " + StatUnit.perSecond.localized());
                        }

                        typedAmmo(ObjectMap.of(unit, weapon.bullet)).display(w);
                    }).growX().pad(5).margin(10);
                    table.row();
                }
            });
        });
    }

    public static StatValue damageTypes(float[] multipliers){
        return table -> {
            table.row(); //adds new row to the stats table

            table.table(Styles.grayPanel, t -> { //creates a new table on that row with gray background
                for(int i = 0; i < multipliers.length; i++){
                    addTypeDamageValue(t, DamageType.values()[i], multipliers[i]);
                }
            }).pad(5).left();
        };
    }

    public static void addTypeDamageValue(Table table, DamageType type, float value){
        if(Strings.autoFixed(value * 100f, 1).equals("100")) return;

        table.row();
        table.add(new Image(Core.atlas.find(spritePrefix + type)).setScaling(Scaling.fit)).size(32f).pad(5).left(); //add icon
        //table.add((value >= 0.99f ? "[stat]" : "[negstat]") + Strings.autoFixed(value * 100f, 1) + "% " + Core.bundle.get("damage." + type)).padRight(5).left();
        table.add("[lightgray]" + Core.bundle.get("damage." + type) + (value >= 0.99f ? ": [stat]" : ": [negstat]") + Strings.autoFixed(value * 100f, 1) + "%").padRight(5).left(); //add value as percentage, uses negative stat red if less than 100%
    }


    //--- exact copy of StatValues code with slight change starts here ---
    public static <T extends UnlockableContent> StatValue typedAmmo(ObjectMap<T, BulletType> map){
        return typedAmmo(map, 0, false);
    }

    public static <T extends UnlockableContent> StatValue typedAmmo(ObjectMap<T, BulletType> map, boolean showUnit){
        return typedAmmo(map, 0, showUnit);
    }

    public static <T extends UnlockableContent> StatValue typedAmmo(ObjectMap<T, BulletType> map, int indent, boolean showUnit){
        return table -> {

            table.row();

            var orderedKeys = map.keys().toSeq();
            orderedKeys.sort();

            for(T t : orderedKeys){
                boolean compact = t instanceof UnitType && !showUnit || indent > 0;

                BulletType type = map.get(t);

                if(type.spawnUnit != null && type.spawnUnit.weapons.size > 0){
                    typedAmmo(ObjectMap.of(t, type.spawnUnit.weapons.first().bullet), indent, false).display(table);
                    continue;
                }

                table.table(Styles.grayPanel, bt -> {
                    bt.left().top().defaults().padRight(3).left();
                    //no point in displaying unit icon twice
                    if(!compact && !(t instanceof Turret)){
                        bt.table(title -> {
                            title.image(t.uiIcon).size(3 * 8).padRight(4).right().scaling(Scaling.fit).top();
                            title.add(t.localizedName).padRight(10).left().top();
                        });
                        bt.row();
                    }

                    if(type.damage > 0 && (type.collides || type.splashDamage <= 0)){
                        table.table(c -> {
                            if(type instanceof TypedBulletType){
                                c.add(new Image(Core.atlas.find(spritePrefix + (((TypedBulletType)type).damageType()))).setScaling(Scaling.fit)).size(32f).left().padRight(5f);
                            }

                            if(type.continuousDamage() > 0){
                                c.add(Core.bundle.format("bullet.damage", type.continuousDamage()) + StatUnit.perSecond.localized());
                            }else{
                                c.add(Core.bundle.format("bullet.damage", type.damage));
                            }
                        });

                        table.row();
                    }

                    if(type.buildingDamageMultiplier != 1){
                        int val = (int)(type.buildingDamageMultiplier * 100 - 100);
                        sep(bt, Core.bundle.format("bullet.buildingdamage", ammoStat(val)));
                    }

                    if(type.rangeChange != 0 && !compact){
                        sep(bt, Core.bundle.format("bullet.range", ammoStat(type.rangeChange / tilesize)));
                    }

                    if(type.splashDamage > 0){
                        sep(bt, Core.bundle.format("bullet.splashdamage", (int)type.splashDamage, Strings.fixed(type.splashDamageRadius / tilesize, 1)));
                    }

                    if(!compact && !Mathf.equal(type.ammoMultiplier, 1f) && type.displayAmmoMultiplier && (!(t instanceof Turret turret) || turret.displayAmmoMultiplier)){
                        sep(bt, Core.bundle.format("bullet.multiplier", (int)type.ammoMultiplier));
                    }

                    if(!compact && !Mathf.equal(type.reloadMultiplier, 1f)){
                        int val = (int)(type.reloadMultiplier * 100 - 100);
                        sep(bt, Core.bundle.format("bullet.reload", ammoStat(val)));
                    }

                    if(type.knockback > 0){
                        sep(bt, Core.bundle.format("bullet.knockback", Strings.autoFixed(type.knockback, 2)));
                    }

                    if(type.healPercent > 0f){
                        sep(bt, Core.bundle.format("bullet.healpercent", Strings.autoFixed(type.healPercent, 2)));
                    }

                    if(type.healAmount > 0f){
                        sep(bt, Core.bundle.format("bullet.healamount", Strings.autoFixed(type.healAmount, 2)));
                    }

                    if(type.pierce || type.pierceCap != -1){
                        sep(bt, type.pierceCap == -1 ? "@bullet.infinitepierce" : Core.bundle.format("bullet.pierce", type.pierceCap));
                    }

                    if(type.incendAmount > 0){
                        sep(bt, "@bullet.incendiary");
                    }

                    if(type.homingPower > 0.01f){
                        sep(bt, "@bullet.homing");
                    }

                    if(type.lightning > 0){
                        sep(bt, Core.bundle.format("bullet.lightning", type.lightning, type.lightningDamage < 0 ? type.damage : type.lightningDamage));
                    }

                    if(type.pierceArmor){
                        sep(bt, "@bullet.armorpierce");
                    }

                    if(type.suppressionRange > 0){
                        sep(bt, Core.bundle.format("bullet.suppression", Strings.autoFixed(type.suppressionDuration / 60f, 2), Strings.fixed(type.suppressionRange / tilesize, 1)));
                    }

                    if(type.status != StatusEffects.none){
                        sep(bt, (type.status.minfo.mod == null ? type.status.emoji() : "") + "[stat]" + type.status.localizedName + (type.status.reactive ? "" : "[lightgray] ~ [stat]" + ((int)(type.statusDuration / 60f)) + "[lightgray] " + Core.bundle.get("unit.seconds")));
                    }

                    if(type.intervalBullet != null){
                        bt.row();

                        Table ic = new Table();
                        typedAmmo(ObjectMap.of(t, type.intervalBullet), indent + 1, false).display(ic);
                        Collapser coll = new Collapser(ic, true);
                        coll.setDuration(0.1f);

                        bt.table(it -> {
                            it.left().defaults().left();

                            it.add(Core.bundle.format("bullet.interval", Strings.autoFixed(type.intervalBullets / type.bulletInterval * 60, 2)));
                            it.button(Icon.downOpen, Styles.emptyi, () -> coll.toggle(false)).update(i -> i.getStyle().imageUp = (!coll.isCollapsed() ? Icon.upOpen : Icon.downOpen)).size(8).padLeft(16f).expandX();
                        });
                        bt.row();
                        bt.add(coll);
                        bt.add(coll);
                    }

                    if(type.fragBullet != null){
                        bt.row();

                        Table fc = new Table();
                        typedAmmo(ObjectMap.of(t, type.fragBullet), indent + 1, false).display(fc);
                        Collapser coll = new Collapser(fc, true);
                        coll.setDuration(0.1f);

                        bt.table(ft -> {
                            ft.left().defaults().left();

                            ft.add(Core.bundle.format("bullet.frags", type.fragBullets));
                            ft.button(Icon.downOpen, Styles.emptyi, () -> coll.toggle(false)).update(i -> i.getStyle().imageUp = (!coll.isCollapsed() ? Icon.upOpen : Icon.downOpen)).size(8).padLeft(16f).expandX();
                        });
                        bt.row();
                        bt.add(coll);
                    }
                }).padLeft(indent * 5).padTop(5).padBottom(compact ? 0 : 5).growX().margin(compact ? 0 : 10);
                table.row();
            }
        };
    }

    //for AmmoListValue
    private static void sep(Table table, String text){
        table.row();
        table.add(text);
    }

    //for AmmoListValue
    private static String ammoStat(float val){
        return (val > 0 ? "[stat]+" : "[negstat]") + Strings.autoFixed(val, 1);
    }

    private static TextureRegion icon(UnlockableContent t){
        return t.uiIcon;
    }
}
