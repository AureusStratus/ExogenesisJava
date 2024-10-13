package exogenesis.type.bullet;

import exogenesis.content.TypeMultipliers;
import exogenesis.type.DamageType;
import arc.Events;
import arc.util.Tmp;
import mindustry.entities.bullet.BulletType;
import mindustry.game.EventType;
import mindustry.gen.Bullet;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Unit;

public interface TypedBulletType{
    DamageType damageType();

    default void typedHitEntity(BulletType type, Bullet b, Hitboxc entity, float health){
        boolean wasDead = entity instanceof Unit u && u.dead;

        if(entity instanceof Unit unit){
            float mul = TypeMultipliers.getMultiplier(unit.type, damageType());

            if(type.pierceArmor){
                unit.damagePierce(b.damage * mul);
            }else{
                unit.damage(b.damage * mul);
            }

            Tmp.v3.set(unit).sub(b).nor().scl(type.knockback * 80f);
            if(type.impact) Tmp.v3.setAngle(b.rotation() + (type.knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(type.status, type.statusDuration);

            Events.fire(new EventType.UnitDamageEvent().set(unit, b));
        } else if(entity instanceof Healthc h){
            if(type.pierceArmor){
                h.damagePierce(b.damage);
            }else{
                h.damage(b.damage);
            }
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead){
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
        }

        type.handlePierce(b, health, entity.x(), entity.y());
    }
}
