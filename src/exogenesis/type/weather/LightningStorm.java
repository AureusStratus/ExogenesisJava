package exogenesis.type.weather;

import arc.func.Prov;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import exogenesis.content.ExoFx;
import exogenesis.type.bullet.TrailedEnergyBulletType;
import mindustry.Vars;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Bullet;
import mindustry.gen.Call;
import mindustry.gen.WeatherState;
import mindustry.graphics.Pal;
import mindustry.type.Weather;

public class LightningStorm extends Weather {
    public float lightningChance = 1 / 60f;

    //the internal bullet used to being absorbed by lightning rod
    public static BulletType absorber = new BasicBulletType(){{
        absorbable = false;
        hittable = false;
        reflectable = false;
        collides = false;

        width = 0;
        height = 0;

        lifetime = 5f;

        splashDamage = 1200;
        splashDamageRadius = 30f;
    }};

    public static BulletType bulletType = new TrailedEnergyBulletType(8f, 0f){{
        absorbable = false;
        hittable = false;
        reflectable = false;
        collides = false;

        disableAccel();
        width = 0;
        height = 0;

        hitBlinkTrail = false;
        collidesTiles = false;

        tracers = 1;
        tracerRandX = 15f;
        tracerUpdateSpacing = 1f;
        tracerStroke = 3f;

        addBeginPoint = despawnHit = true;

        trailColor = hitColor = Pal.techBlue;

        fragOnHit = false;
        fragBullet = absorber;
        fragBullets = 1;
    }};


    public LightningStorm(String name) {
        super(name);
    }

    @Override
    public void update(WeatherState state) {
        if(!Vars.net.client() && Mathf.chanceDelta(lightningChance * state.intensity * 1.25f))for(int i = 0; i < 4; i++){
            float randX = Mathf.random(Vars.world.unitWidth()), randY = Mathf.random(Vars.world.unitHeight());
            float ang = state.windVector.angle();
            Call.createBullet(bulletType, Team.derelict, randX, randY, ang, bulletType.damage, 1f, 1f);
        }
    }
}
