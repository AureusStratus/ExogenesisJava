package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import exogenesis.type.*;

public class ExoBasicBulletType extends BasicBulletType implements TypedBulletType{
    public ObjectFloatMap<DamageType> damageMultiplier = new ObjectFloatMap<>();

    public ExoBasicBulletType(float speed, float damage){
        super(speed, damage);
    }

    @Override
    public ObjectFloatMap<DamageType> typedDamageMultipliers() {
        return damageMultiplier;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }
}