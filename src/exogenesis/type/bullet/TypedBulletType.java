package exogenesis.type.bullet;

import arc.Core;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectFloatMap;
import arc.struct.OrderedMap;
import arc.util.Log;
import arc.util.Strings;
import exogenesis.content.ExoUnitTypeResistances;
import exogenesis.type.DamageType;
import arc.Events;
import arc.util.Tmp;
import mindustry.content.StatusEffects;
import mindustry.core.World;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.Damage;
import mindustry.entities.Fires;
import mindustry.entities.bullet.BulletType;
import mindustry.game.EventType;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.world.meta.StatUnit;

import java.util.concurrent.atomic.AtomicReference;

import static exogenesis.content.ExoStatValues.buildSharedBulletTypeStat;
import static mindustry.Vars.indexer;
import static mindustry.Vars.tilesize;

public interface TypedBulletType{
    EventType.UnitDamageEvent bulletDamageEvent = new EventType.UnitDamageEvent();

    //only apply to units.
    //Damages with type. Damage Type - Damage Multiplier Map.
    //[Kinetic - 0.5f],[thermal - 2f] means [damage * 0.5f] for kinetic and [damage * 2f] for thermal.
    //notice the damage deals multiple time, in the previous case, 2.5x damage for total(resistance not considered)
    OrderedMap<DamageType, Float> typedDamageMultipliers();

    default void buildStat(BulletType type, UnlockableContent t, Table bt, boolean compact){
        bt.row();
        if (type.damage > 0){
            typedDamageMultipliers().each((damageType, multiplier) -> {
                bt.table(dt -> {
                    //todo replace the image with emoji
                    if (type.continuousDamage() > 0){
                        dt.add("[accent]" + Strings.fixed(Mathf.round(type.continuousDamage() * multiplier), 0) + " []");
                        dt.image(damageType.fullIcon).size(20, 20).padRight(4);
                        dt.add(damageType.localizedName + StatUnit.perSecond.localized());
                    }else {
                        dt.add("[accent]" + Strings.fixed(Mathf.round(type.damage * multiplier), 0) + " []");
                        dt.image(damageType.fullIcon).size(20, 20).padRight(4);
                        dt.add(damageType.localizedName);
                    }
                });
                bt.row();
            });
            bt.spacerY(() -> 6f);
            bt.row();
        }

        if(type.splashDamage > 0){
            typedDamageMultipliers().each((damageType, multiplier) -> {
                bt.table(dt -> {
                    dt.add(Core.bundle.format("bullet.splashdamage", Strings.fixed(Mathf.round(type.splashDamage * multiplier), 0), Strings.fixed(type.splashDamageRadius / tilesize, 1)) + " ");
                    dt.image(damageType.fullIcon).size(20, 20).padRight(4);
                    dt.add(damageType.localizedName);
                });
                bt.row();
            });
            bt.spacerY(() -> 6f);
            bt.row();
        }


        buildSharedBulletTypeStat(type, t, bt, compact);
    };

    default void addDamageMultiplier(Object...objects) {
        for (int i = 0; i < objects.length; i += 2) {
            if (objects[i] instanceof DamageType damageType && objects[i + 1] instanceof Float damageMultiplier) {
                typedDamageMultipliers().put(damageType, damageMultiplier);
            }
        }
    }

    default float getTotalDamageToUnit(float damage, Healthc entity){
        if (entity instanceof Unit unit && unit.type != null && typedDamageMultipliers() != null && !typedDamageMultipliers().isEmpty()){
            UnitType type = unit.type;
            AtomicReference<Float> total = new AtomicReference<>(0f);
            typedDamageMultipliers().each((damageType, multiplier) -> {
                float resistance = ExoUnitTypeResistances.getResistance(type, damageType);
                float result = multiplier * (1 - resistance);
                total.updateAndGet(v -> (v + result * damage));
            });
            return total.get();
        }else {
            return damage;
        }
    }

    default void typedHitEntity(BulletType type, Bullet b, Hitboxc entity, float health){
        boolean wasDead = entity instanceof Unit u && u.dead;

        if(entity instanceof Healthc h){
            float damage = getTotalDamageToUnit(b.damage, h);
            float shield = entity instanceof Shieldc s ? Math.max(s.shield(), 0f) : 0f;
            if(type.maxDamageFraction > 0){
                float cap = h.maxHealth() * type.maxDamageFraction + shield;
                damage = Math.min(damage, cap);
                //cap health to effective health for handlePierce to handle it properly
                health = Math.min(health, cap);
            }else{
                health += shield;
            }
            if(type.lifesteal > 0f && b.owner instanceof Healthc o){
                float result = Math.max(Math.min(h.health(), damage), 0);
                o.heal(result * type.lifesteal);
            }
            if(type.pierceArmor){
                h.damagePierce(damage);
            }else{
                h.damage(damage);
            }
        }

        if(entity instanceof Unit unit){
            Tmp.v3.set(unit).sub(b).nor().scl(type.knockback * 80f);
            if(type.impact) Tmp.v3.setAngle(b.rotation() + (type.knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(type.status, type.statusDuration);

            Events.fire(bulletDamageEvent.set(unit, b));
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead){
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
        }

        type.handlePierce(b, health, entity.x(), entity.y());
    }

    default void typedCreateSplash(BulletType type, Bullet b, float x, float y){
        if(type.splashDamageRadius > 0 && !b.absorbed){
            //do seperated damage
            Damage.tileDamage(b.team, World.toTile(x), World.toTile(y), type.splashDamageRadius, type.splashDamage * b.type.buildingDamageMultiplier, b);

            Damage.damageUnits(
                    b.team, x, y, type.splashDamageRadius, 0,
                    unit -> unit.within(b, type.splashDamageRadius + unit.hitSize / 2f),
                    unit -> {
                        unit.damage(getTotalDamageToUnit(type.splashDamage * b.damageMultiplier(), unit));
                        if(type.status != StatusEffects.none) unit.apply(type.status, type.statusDuration);
                    });

            if(type.heals()){
                indexer.eachBlock(b.team, x, y, type.splashDamageRadius, Building::damaged, other -> {
                    type.healEffect.at(other.x, other.y, 0f, type.healColor, other.block);
                    other.heal(type.healPercent / 100f * other.maxHealth() + type.healAmount);
                });
            }

            if(type.makeFire){
                indexer.eachBlock(null, x, y, type.splashDamageRadius, other -> other.team != b.team, other -> Fires.create(other.tile));
            }
        }
    }
}
