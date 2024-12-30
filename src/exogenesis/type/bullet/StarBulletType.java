package exogenesis.type.bullet;

import exogenesis.entities.effect.SwirlEffect;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import exogenesis.content.*;
import exogenesis.graphics.*;

import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.*;
import mindustry.graphics.*;


public class StarBulletType extends BulletType {
    public float radius = 9f;
    public Color realColor = ExoPal.cronusRed;
    public Effect realHitEffect = ExoFx.hitEmpColorSpark;
    public float rotationSpeed;
    public Effect swirlEffect;
    public float swirlInterval;
    public int swirlEffects;
    public boolean counterClockwise;

    public StarBulletType(float speed, float damage){
        this.speed = speed;
        this.damage = damage;
        this.swirlEffect = new SwirlEffect();
        this.swirlInterval = 3.0F;
        this.swirlEffects = 4;
        this.rotationSpeed = 160;
        hitEffect = Fx.none;
        despawnEffect = Fx.none;
        pierce = true;
        pierceBuilding = true;
        collidesAir = true;
        lightRadius = 25f;
        lightColor = hitColor;
        trailColor = Color.white;
        hitSize = radius * 2f;
        trailWidth = radius * 0.9f;
        trailLength = 10;
        trailInterp = Interp.linear;
        despawnHit = true;
    }

    public float realRadius(float f){
        return radius * (0.5f + f * f * 0.5f);
    }

    public void setDamage(Bullet b){
        b.damage = damage * b.damageMultiplier() * (1f + 6.5f * b.fin());
    }

    @Override
    public void update(Bullet b){
        setDamage(b);
        super.update(b);
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        setDamage(b);
        super.hitEntity(b, entity, health);
    }

    @Override
    public void hit(Bullet b, float x, float y){
        realHitEffect.at(x, y, b.fin() * 100f, this);
        setDamage(b);
        super.hit(b, x, y);
    }

    @Override
    public void despawned(Bullet b){
        if(despawnHit){
            hit(b);
        }

        if(!fragOnHit){
            createFrags(b, b.x, b.y);
        }
        despawnSound.at(b);

        Effect.shake(despawnShake, despawnShake, b);
    }
    public void updateTrailEffects(Bullet b) {
        super.updateTrailEffects(b);
        if (this.swirlEffect != null && this.swirlInterval > 0.0F && b.time <= b.lifetime - this.swirlEffect.lifetime && b.timer(0, this.swirlInterval)) {
            for(int i = 0; i < this.swirlEffects; ++i) {
                this.swirlEffect.at(b.x, b.y, this.rotationSpeed * (this.counterClockwise ? -1.0F : 1.0F), this.realColor, b);
            }
        }

    }
    @Override
    public void draw(Bullet b){
        Draw.color(realColor);
        Fill.circle(b.x, b.y, realRadius(b.fin()));
        Draw.color(Color.white);
        Fill.circle(b.x, b.y, realRadius(b.fin())/2);
        Draw.rect("circle-shadow", b.x, b.y, radius * 1.8f, radius * 1.8f, 0);
        float z = Draw.z();
        Draw.z(Layer.bullet - 1);
        Draw.color(realColor,b.fin());
        Draw.blend(Blending.additive);
        Draw.rect("circle-shadow", b.x, b.y, radius * 4f, radius * 4f, 0);
        Draw.blend();
        Draw.z(z);
        drawTrail(b);
    }

    @Override
    public void drawTrail(Bullet b){
        if(trailLength > 0 && b.trail != null){
            float z = Draw.z();
            Draw.z(z - 0.0001f);
            b.trail.draw(realColor, trailWidth);
            Draw.z(z);
        }
    }

    @Override
    public void drawLight(Bullet b){
        if(lightOpacity <= 0f || lightRadius <= 0f) return;
        Drawf.light(b, lightRadius * (0.5f + 0.5f * b.fin()), realColor, lightOpacity);
    }
}
