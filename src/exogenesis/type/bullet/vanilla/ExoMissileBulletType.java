package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import exogenesis.type.DamageType;
import mindustry.entities.bullet.MissileBulletType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;
public class ExoMissileBulletType extends MissileBulletType implements TypedBulletType{
    public ObjectFloatMap<DamageType> damageMultiplier = new ObjectFloatMap<>();

    @Override
    public ObjectFloatMap<DamageType> typedDamageMultipliers() {
        return damageMultiplier;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }
}
