package exogenesis.type.bullet.vanilla;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.struct.OrderedMap;
import arc.util.Time;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.entities.bullet.PointLaserBulletType;
import mindustry.gen.Bullet;
import mindustry.graphics.Drawf;

public class ExoPointLaserBulletType extends PointLaserBulletType implements TypedBulletType {
    public float laserSize = 1f;
    public OrderedMap<DamageType, Float> damageMultiplier = new OrderedMap<>();

    @Override
    public OrderedMap<DamageType, Float> typedDamageMultipliers() {
        return damageMultiplier;
    }

    @Override
    public void draw(Bullet b){
        super.draw(b);

        Draw.color(color);
        Drawf.laser(laser, laserEnd, b.x, b.y, b.aimX, b.aimY, b.fslope() * laserSize * (1f - oscMag + Mathf.absin(Time.time, oscScl, oscMag)));

        Draw.reset();
    }
}

