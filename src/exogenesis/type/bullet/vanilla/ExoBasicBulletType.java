package exogenesis.type.bullet.vanilla;

import arc.struct.ObjectFloatMap;
import arc.struct.OrderedMap;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import exogenesis.type.*;

public class ExoBasicBulletType extends BasicBulletType implements TypedBulletType{
    public OrderedMap<DamageType, Float> damageMultiplier = new OrderedMap<>();

    public ExoBasicBulletType(float speed, float damage){
        super(speed, damage);
    }

    public ExoBasicBulletType(){
        super(1, 1);
    }

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