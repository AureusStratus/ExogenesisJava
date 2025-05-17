package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import exogenesis.type.DamageType;
import mindustry.entities.bullet.LightningBulletType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;
public class ExoLightningBulletType extends LightningBulletType implements TypedBulletType{
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
