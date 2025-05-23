package exogenesis.type.bullet;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Time;
import arc.util.Tmp;
import exogenesis.content.ExoFx;
import exogenesis.content.ExoFxf;
import exogenesis.type.bullet.vanilla.ExoBasicBulletType;
import exogenesis.type.bullet.vanilla.ExoFlakBulletType;
import mindustry.content.StatusEffects;
import mindustry.core.World;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Fires;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Teamc;

import static mindustry.Vars.indexer;

//todo
public class FuseBombBulletType extends ExoFlakBulletType {
    //the max build up fuse for this bullet
    public int maxFuse = 8;
    //the interp for damage
    public Interp damageMultiplier = Interp.pow2In;
    //the interp for size
    public Interp sizeMultiplier = Interp.pow2Out;

    public Effect mergeEffect = ExoFx.supernovaBlast;

    public float speedDecrease = 0.2f / 60f;
    public float speedIncrease = 0.4f / 60f;

    public FuseBombBulletType() {
        despawnHit = true;

        speed = 3;

        homingRange = 120f;
        homingPower = 0.1f;

        splashDamage = 100;
        splashDamageRadius = 30f;
    }

    @Override
    public void init(Bullet b) {
        b.data = 1;
        shrinkX = shrinkY = 0;
    }

    @Override
    public void update(Bullet b) {
        super.update(b);
        if (!b.absorbed){
            if (b.data instanceof Integer fuse && fuse >= maxFuse){
                b.time = b.lifetime;
                return;
            }
            Groups.bullet.intersect(b.x, b.y, b.hitSize, b.hitSize, bullet -> {
                if (bullet.type instanceof FuseBombBulletType && bullet != b){
                    fuseBullet(b, bullet);
                }
            });
        }
        b.vel.setLength(Math.max(b.vel.len() - speedDecrease / Time.delta, 0f));
    }

    @Override
    public void typedCreateSplash(BulletType type, Bullet b, float x, float y) {
        if(type.splashDamageRadius > 0 && !b.absorbed && b.data instanceof Integer fuse){
            float scl = damageMultiplier.apply((float) fuse / maxFuse);
            //do seperated damage
            Damage.tileDamage(b.team, World.toTile(x), World.toTile(y), type.splashDamageRadius, type.splashDamage * b.type.buildingDamageMultiplier * scl, b);

            Damage.damageUnits(
                    b.team, x, y, type.splashDamageRadius, 0,
                    unit -> unit.within(b, type.splashDamageRadius + unit.hitSize / 2f),
                    unit -> {
                        unit.damage(getTotalDamageToUnit(type.splashDamage * b.damageMultiplier() * scl, unit));
                        if(type.status != StatusEffects.none) unit.apply(type.status, type.statusDuration);
                    });

            if(type.heals()){
                indexer.eachBlock(b.team, x, y, type.splashDamageRadius, Building::damaged, other -> {
                    type.healEffect.at(other.x, other.y, 0f, type.healColor, other.block);
                    other.heal(type.healPercent / 100f * other.maxHealth() + type.healAmount);
                });
            }

            if(type.makeFire){
                indexer.eachBlock(null, x, y, type.splashDamageRadius, other -> other.team != b.team, other -> Fires.create(other.tile));
            }
        }
    }

    @Override
    public void draw(Bullet b) {
        drawTrail(b);
        drawParts(b);

        if (b.data instanceof Integer fuse){
            float scl = sizeMultiplier.apply((float) fuse / maxFuse);
            float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;

            Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

            Draw.mixcol(mix, mix.a);

            if(backRegion.found()){
                Draw.color(backColor);
                Draw.rect(backRegion, b.x, b.y, width * scl, height * scl, b.rotation() + offset);
            }

            Draw.color(frontColor);
            Draw.rect(frontRegion, b.x, b.y, width * scl, height * scl, b.rotation() + offset);

            Draw.reset();
        }
    }

    //override the homing, now only home to another possible bullet to fuse.
    @Override
    public void updateHoming(Bullet b) {
        if(homingPower > 0.0001f && b.time >= homingDelay){
            Teamc target;
            Seq<Bullet> list = Groups.bullet.intersect(b.x, b.y, homingRange, homingRange);
            list.remove(b);
            list.remove(bullet -> !(bullet.type instanceof FuseBombBulletType));
            target = Geometry.findClosest(b.x, b.y, list);

            if(target != null && b.data instanceof Integer fuse){
                if (b.vel.isZero()) b.vel.trns(b.angleTo(target), 0.01f);
                b.vel.setLength(b.vel.len() + speedIncrease / Time.delta / fuse);
                b.vel.setAngle(Angles.moveToward(b.rotation(), b.angleTo(target), homingPower * Time.delta * 50f));
            }
        }
    }

    public void fuseBullet(Bullet b, Bullet b2) {
        if (b == b2) return;
        if (b.data instanceof Integer fuse && b2.data instanceof Integer fuse2) {
            b.data = fuse + fuse2;
            b.time(0f);
            b.vel.scl((float) fuse / (fuse + fuse2));
            b2.absorb();
            mergeEffect.at(b);
        }
    }

}
