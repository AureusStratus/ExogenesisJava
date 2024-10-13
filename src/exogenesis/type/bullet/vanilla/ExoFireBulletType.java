package exogenesis.type.bullet.vanilla;

import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import exogenesis.type.*;

public class ExoFireBulletType extends FireBulletType implements TypedBulletType{
    public DamageType damageType;

    public ExoFireBulletType(float speed, float damage){
        super(speed, damage);
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