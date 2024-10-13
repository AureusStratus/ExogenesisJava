package exogenesis.type.bullet.vanilla;

import mindustry.entities.bullet.EmpBulletType;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;
public class ExoEmpBulletType extends EmpBulletType implements TypedBulletType{
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