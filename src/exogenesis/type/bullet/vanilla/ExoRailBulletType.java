package exogenesis.type.bullet.vanilla;

import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

public class ExoRailBulletType extends RailBulletType implements TypedBulletType{
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
