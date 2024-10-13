package exogenesis.type.bullet.vanilla;

import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

public class ExoExplosionBulletType extends ExplosionBulletType implements TypedBulletType{
    public DamageType damageType;

    public ExoExplosionBulletType(float splashDamage, float splashDamageRadius){
        super(splashDamage, splashDamageRadius);
    }

    @Override
    public DamageType damageType(){
        return damageType;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }
}
