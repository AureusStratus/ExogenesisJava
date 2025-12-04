package exogenesis.type.bullet;

import arc.math.geom.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

public class ArcBoltBulletType extends ArcBasicBulletType {
    public ArcBoltBulletType(float speed, float damage, String sprite){
        super(speed, damage, sprite);
    }

    public ArcBoltBulletType(float speed, float damage){
        this(speed, damage, "bullet");

        spinShade = drawZone = drawTarget = drawProgress = false;
    }

    public ArcBoltBulletType(float speed){
        this(speed, 0f);
    }

    @Override
    public ArcBulletData createData(){
        return Pools.obtain(ArcBulletData.class, ArcBoltData::new);
    }

    @Override
    public ArcBulletData createData(float z, float zVel, float gravity){
        return new ArcBoltData(z, zVel, gravity);
    }

    public static class ArcBoltData extends ArcBulletData{
        public float xAccel, yAccel;

        public ArcBoltData(float z, float zVel, float gravity){
            super(z, zVel, gravity);
        }

        public ArcBoltData(float z, float zVel){
            this(z, zVel, 1f);
        }

        public ArcBoltData(){
            this(0, 0);
        }

        @Override
        public void backMove(Bullet b){
            b.vel.sub(xAccel * Time.delta, yAccel * Time.delta);
            super.backMove(b);
        }

        @Override
        public void updateAccel(Bullet b){
            float life = b.lifetime() - b.time();
            //Calculate accels
            float dx = b.aimX - b.x;
            xAccel = (2 * (dx - b.vel.x * life)) / (life * life);
            float dy = b.aimY - b.y;
            yAccel = (2 * (dy - b.vel.y * life)) / (life * life);
        }

        @Override
        public void updateHoming(Bullet b, Position target){
            BulletType type = b.type;

            Tmp.v1.set(b.aimX, b.aimY).approachDelta(Tmp.v2.set(target), type.homingPower);
            b.aimX = Tmp.v1.x;
            b.aimY = Tmp.v1.y;
            updateAccel(b);
        }

        @Override
        public void update(Bullet b){
            b.vel.add(xAccel * Time.delta, yAccel * Time.delta);
            super.update(b);
        }

        @Override
        public ArcBulletData setAccel(float a){
            Tmp.v1.set(xAccel, yAccel).setLength(a);
            xAccel = Tmp.v1.x;
            yAccel = Tmp.v1.y;
            return this;
        }

        @Override
        public float xAccel(Bullet b){
            return xAccel;
        }

        @Override
        public float yAccel(Bullet b){
            return yAccel;
        }
    }
}
