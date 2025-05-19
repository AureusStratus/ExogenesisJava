package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import arc.struct.OrderedMap;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

public class ExoContinuousLaserBulletType extends ContinuousLaserBulletType implements TypedBulletType{
    public OrderedMap<DamageType, Float> damageMultiplier = new OrderedMap<>();

    @Override
    public OrderedMap<DamageType, Float> typedDamageMultipliers() {
        return damageMultiplier;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }

    @Override
    public void createSplashDamage(Bullet b, float x, float y) {
        typedCreateSplash(this, b, x, y);
    }
}
