package exogenesis.type.bullet.vanilla;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.util.Time;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.PointLaserBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;
import mindustry.graphics.Drawf;

public class ExoPointLaserBulletType extends PointLaserBulletType implements TypedBulletType{
    public DamageType damageType;

    public float laserSize = 1f;
    @Override
    public void draw(Bullet b){
        super.draw(b);

        Draw.color(color);
        Drawf.laser(laser, laserEnd, b.x, b.y, b.aimX, b.aimY, b.fslope() * laserSize * (1f - oscMag + Mathf.absin(Time.time, oscScl, oscMag)));

        Draw.reset();
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

