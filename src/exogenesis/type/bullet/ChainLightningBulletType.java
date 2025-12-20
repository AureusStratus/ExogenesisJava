package exogenesis.type.bullet;

import exogenesis.content.ExoFxf;
import exogenesis.type.bullet.vanilla.ExoBulletType;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.struct.IntSeq;
import arc.struct.Seq;
import arc.util.Tmp;
import exogenesis.entities.ModDamage;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.core.World;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.world.Tile;

import static exogenesis.entities.ModDamage.list;

public class ChainLightningBulletType extends BulletType {
    private static int charges;

    public float width, segmentLength, arc, jumpDamageFactor, distanceDamageFalloff, targetRange;

    public int chainLightning, branches;

    public ChainLightningBulletType() {
        super();
        speed = 0;
        instantDisappear = true;
        lifetime = 1;
        despawnEffect = Fx.none;
        hitEffect = Fx.hitLancer;
        keepVelocity = false;
        hittable = false;
        collides = false;
        collidesTeam = false;
        jumpDamageFactor = 0.85f;
        distanceDamageFalloff = 0.65f;
        targetRange = -1;
        width = 8;
        arc = 0.35f;
        segmentLength = 4;
        lightningColor = Pal.techBlue;
        lightningLength = 0;
        chainLightning = 1;
        branches = 2;
        hitSound = Sounds.shootArc;
        despawnSound= Sounds.none;
    }

    @Override
    public void init() {
        super.init();
        if(targetRange == -1) targetRange = range;
    }

    @Override
    protected float calculateRange() {
        return range;
    }
    @Override
    public float estimateDPS() {
        return super.estimateDPS() * Math.max((float)this.lightningLength / 10.0F, 1.0F);
    }

    @Override
    public void draw(Bullet b) {
    }

    @Override
    public void init(Bullet b) {
        super.init(b);
        Position aimPos = b.aimTile == null ? b : b.aimTile;

        Seq<Unit> units = Groups.unit.intersect(b.x - range, b.y - range, range * 2, range * 2);
        list.clear();
        charges = chainLightning;


        units.sort(u -> u.dst2(aimPos));

        for (int i = 0; i < Math.min(chainLightning, units.size); i++) {
            Unit unit = units.get(i);
            if(!unit.targetable(b.team) || !(collidesTeam || unit.team != b.team)) break;
            float dst = unit.dst(b);
            if(dst > range) break;
            float dst2 = unit.dst(aimPos);
            if(dst2 > targetRange) break;
            list.add(unit);
            charges--;
        }

        list.each(u -> {
            ModDamage.chain(new Vec2(b.x, b.y), u, new IntSeq(), hitSound, hitEffect, b.damage, b.damage, width, distanceDamageFalloff, jumpDamageFactor, branches, segmentLength, arc, lightningColor);
        });

        if(charges <= 0) return;

        Seq<Building> buildings = Seq.with();

        Geometry.circle(b.tileX(), b.tileY(), World.toTile(range), (x, y) -> {
            Tile t = Vars.world.tile(x, y);

            if(t == null || t.build == null || t.build.dst(aimPos) > targetRange || t.build.team.id == b.team.id && !collidesTeam || buildings.contains(t.build)) return;
            buildings.add(t.build);
        });

        if(buildings.size == 0){
            sparks(b, aimPos);
            return;
        }

        int builds = Math.min(charges, buildings.size);

        for (int i = 0; i < builds; i++) {
            Building build = buildings.get(i);

            build.damage(damage);
            Tmp.v1.set(build).add(Tmp.v2.set(Mathf.random(build.block.size), Mathf.random(build.block.size)).scl(0.5f));
            ExoFxf.chainLightning.at(Tmp.v1.x, Tmp.v1.y, 0, lightningColor, new LightningHolder(b, Tmp.v1));
            hitEffect.at(build.x, build.y);
            charges--;
        }

        sparks(b, aimPos);
    }

    public void sparks(Bullet b, Position aimPos){
        for (int i = 0; i < charges; i++) {
            Tmp.v1.setToRandomDirection().scl(targetRange/2).add(aimPos).sub(b).clamp(0, range).add(b);
            ExoFxf.chainLightning.at(Tmp.v1.x, Tmp.v1.y, 0, lightningColor, new LightningHolder(b, Tmp.v1));
            hitEffect.at(Tmp.v1.x, Tmp.v1.y);
        }
    }

    public class LightningHolder implements ExoFxf.VisualLightningHolder {

        public Vec2 start, end;

        public LightningHolder(Position start, Position end){
            this.start = new Vec2(start.getX(), start.getY());
            this.end = new Vec2(end.getX(), end.getY());
        }

        @Override
        public Vec2 start() {
            return start;
        }

        @Override
        public Vec2 end() {
            return end;
        }

        @Override
        public float width() {
            return width;
        }

        @Override
        public float segLength() {
            return segmentLength;
        }

        @Override
        public float arc() {
            return arc;
        }
    }
}