package exogenesis.type.bullet.vanilla;

import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.FlakBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

public class ExoFlakBulletType extends FlakBulletType implements TypedBulletType{
    public DamageType damageType;

    @Override
    public DamageType damageType(){
        return damageType;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }
}
