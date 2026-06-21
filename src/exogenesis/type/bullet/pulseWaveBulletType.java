package exogenesis.type.bullet;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import exogenesis.graphics.ExoPal;
import exogenesis.type.bullet.vanilla.ExoBulletType;
import exogenesis.util.ExoUtls;
import exogenesis.util.util.UtilsTwo;
import mindustry.Vars;
import mindustry.gen.*;
import exogenesis.graphics.*;
import exogenesis.util.util.Utils;

public class pulseWaveBulletType extends ExoBulletType {
    protected float health = 5000f, maxDamage = 5000f;
    protected float bulletDamage = 43f;
    protected float length = 70f, widthFrom = 180f, widthTo = 230f, offset = 1.75f;
    protected float startingScl = 1.4f, scaleReduction = 0.8f;
    protected float fadeOutTime = 20f, fadeInTime = 40f;
    public Color[] colors = {ExoPal.cronusRedlight, ExoPal.cronusRedlight, ExoPal.cronusRedlight, Color.white};

    public pulseWaveBulletType(float speed, float damage){
        hittable = false;
        absorbable = false;
        collides = false;
        pierce = true;
        shieldDamageMultiplier = 2;
        impact = true;
        keepVelocity = false;
        knockback = 5f;
    }

    @Override
    public float continuousDamage(){
        return damage / 5f * 60f;
    }

    @Override
    public float estimateDPS(){
        return damage * 100f / 5f * 3f;
    }

    @Override
    public void init(){
        super.init();
        despawnHit = false;
        drawSize = Math.max(Math.max(widthTo, widthFrom), length) * 2f * startingScl;
    }

    @Override
    public void init(Bullet b){
        super.init(b);
        DesolationBulletData data = new DesolationBulletData();
        data.health = health;
        b.data = data;
    }

    @Override
    public void update(Bullet b){
        if(b.timer(1, 5f) && b.data instanceof DesolationBulletData){
            DesolationBulletData d = (DesolationBulletData)b.data;
            d.collided.clear();
            float width = Mathf.lerp(widthFrom, widthTo, Mathf.clamp(b.time / fadeInTime));
            float in = d.health / health;
            float out = Mathf.clamp(b.time > b.lifetime - fadeOutTime ? 1f - (b.time - (lifetime - fadeOutTime)) / fadeOutTime : 1f);
            Vec2 v1 = Tmp.v1.trns(b.rotation(), length / 2f);

            if(d.health <= 0f) b.remove();
        }
    }

    @Override
    public void draw(Bullet b){
        if(b.data instanceof DesolationBulletData){
            DesolationBulletData d = (DesolationBulletData)b.data;
            float width = Mathf.lerp(widthFrom, widthTo, Mathf.clamp(b.time / fadeInTime));
            float in = d.health / health;
            Vec2 v1 = Tmp.v1.trns(b.rotation(), length / 2f);
            float scl = startingScl;
            float out = Mathf.clamp(b.time > b.lifetime - fadeOutTime ? 1f - (b.time - (lifetime - fadeOutTime)) / fadeOutTime : 1f);
            for(Color c : colors){
                Draw.color(c);
                float rx = Mathf.range(2f), ry = Mathf.range(2f);
                for(int s : Mathf.signs){
                    Vec2 v2 = Tmp.v2.trns(b.rotation() - 90f, width * in * s * out, -(length / 2f) * offset);
                    float x1 = b.x + (v1.x * scl), x2 = b.x - v1.x, x3 = b.x + (v2.x * scl),
                    y1 = b.y + (v1.y * scl), y2 = b.y - v1.y, y3 = b.y + (v2.y * scl);
                    Fill.tri(x1 + rx, y1 + ry, x2 + rx, y2 + ry, x3 + rx, y3 + ry);
                }
                scl *= scaleReduction;
            }
            Draw.reset();
        }
    }

    @Override
    public void drawLight(Bullet b){

    }

    static class DesolationBulletData{
        IntSet collided = new IntSet(103);
        float health = 0f;
    }
}
