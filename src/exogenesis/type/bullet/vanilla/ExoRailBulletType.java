package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

public class ExoRailBulletType extends RailBulletType implements TypedBulletType{
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
