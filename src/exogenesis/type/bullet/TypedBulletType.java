package exogenesis.type.bullet;

import arc.struct.ObjectFloatMap;
import exogenesis.content.ExoUnitTypeResistances;
import exogenesis.type.DamageType;
import arc.Events;
import arc.util.Tmp;
import mindustry.entities.bullet.BulletType;
import mindustry.game.EventType;
import mindustry.gen.*;
import mindustry.type.UnitType;

import java.util.concurrent.atomic.AtomicReference;

public interface TypedBulletType{
    EventType.UnitDamageEvent bulletDamageEvent = new EventType.UnitDamageEvent();

    //only apply to units.
    //Damages with type. Damage Type - Damage Multiplier Map.
    //[Kinetic - 0.5f],[thermal - 2f] means [damage * 0.5f] for kinetic and [damage * 2f] for thermal.
    ObjectFloatMap<DamageType> typedDamageMultipliers();

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
            typedDamageMultipliers().each(entry -> {
                float resistance = ExoUnitTypeResistances.getResistance(type, entry.key);
                float multiplier = entry.value * (1 - resistance);
                total.updateAndGet(v -> (v + multiplier * damage));
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
}
