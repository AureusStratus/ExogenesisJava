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
import mindustry.type.weather.RainWeather;

public class LightningStorm extends RainWeather {
    public float lightningChance = 1 / 20f;

    //the internal bullet used to being absorbed by lightning rod
    public static BulletType absorber = new BasicBulletType(){{
        absorbable = false;
        hittable = false;
        reflectable = false;
        collides = false;

        width = 0;
        height = 0;

        lifetime = 5f;

        splashDamage = 400;
        splashDamageRadius = 30f;
    }};

    public static BulletType bulletType = new TrailedEnergyBulletType(15f, 0f){{
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
        tracerUpdateSpacing = 0.5f;
        tracerStroke = 5f;

        lifetime = 18f;
        speed = 15f;

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
        if(!Vars.net.client() && Mathf.chanceDelta(lightningChance * state.intensity * 1.25f)){
            float randX = Mathf.random(Vars.world.unitWidth()), randY = Mathf.random(Vars.world.unitHeight());
            float dst = bulletType.speed * bulletType.lifetime;
            Call.createBullet(bulletType, Team.derelict, randX + dst / Mathf.sqrt2, randY + dst / Mathf.sqrt2, 225f, bulletType.damage, 1f, 1f);
        }
    }
}
