package exogenesis.world.power;

import arc.Events;
import arc.func.Cons;
import arc.math.Mathf;
import arc.math.geom.Intersector;
import arc.util.Log;
import arc.util.Strings;
import arc.util.Time;
import exogenesis.type.weather.LightningStorm;
import mindustry.content.Fx;
import mindustry.game.EventType;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.consumers.ConsumePower;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;

public class LightningRod extends ForceProjector {
    public float maxPowerGeneration = 500f;
    protected static final Cons<Bullet> shieldConsumer = bullet -> {
        if(bullet.type == LightningStorm.absorber && !bullet.absorbed && Intersector.isInRegularPolygon(((ForceProjector)(paramEntity.block)).sides, paramEntity.x, paramEntity.y, paramEntity.realRadius(), ((ForceProjector)(paramEntity.block)).shieldRotation, bullet.x, bullet.y)){
            bullet.absorb();
            paramEffect.at(bullet);
            paramEntity.hit = 1f;
        }
    };

    public LightningRod(String name) {
        super(name);

        removeConsumers(consume -> consume instanceof ConsumePower);

        hasPower = true;
        consumesPower = false;
        conductivePower = true;
        outputsPower = true;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.shieldHealth);
        stats.remove(Stat.cooldownTime);
        stats.remove(Stat.booster);

        stats.add(Stat.basePowerGeneration, maxPowerGeneration);
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("outputPower", (LightningRodBuild e) -> new Bar(
                () -> Strings.autoFixed(e.getPowerProduction(), 0),
                () -> Pal.power,
                () -> e.hit
        ));
    }

    public class LightningRodBuild extends ForceBuild {
        @Override
        public float getPowerProduction() {
            return Math.max(0, hit * maxPowerGeneration);
        }

        @Override
        public void updateTile(){
            boolean phaseValid = itemConsumer != null && itemConsumer.efficiency(this) > 0;

            phaseHeat = Mathf.lerpDelta(phaseHeat, Mathf.num(phaseValid), 0.1f);

            if(phaseValid && !broken && timer(timerUse, phaseUseTime) && efficiency > 0){
                consume();
            }

            radscl = Mathf.lerpDelta(radscl, broken ? 0f : warmup, 0.05f);

            if(Mathf.chanceDelta(buildup / shieldHealth * 0.1f)){
                Fx.reactorsmoke.at(x + Mathf.range(tilesize / 2f), y + Mathf.range(tilesize / 2f));
            }

            warmup = Mathf.lerpDelta(warmup, efficiency, 0.1f);

            if(buildup > 0){
                float scale = !broken ? cooldownNormal : cooldownBrokenBase;

                //TODO I hate this system
                if(coolantConsumer != null){
                    if(coolantConsumer.efficiency(this) > 0){
                        coolantConsumer.update(this);
                        scale *= (cooldownLiquid * (1f + (liquids.current().heatCapacity - 0.4f) * 0.9f));
                    }
                }

                buildup -= delta() * scale;
            }

            if(broken && buildup <= 0){
                broken = false;
            }

            if(buildup >= shieldHealth + phaseShieldBoost * phaseHeat && !broken){
                broken = true;
                buildup = shieldHealth;
                shieldBreakEffect.at(x, y, realRadius(), team.color);
                if(team != state.rules.defaultTeam){
                    Events.fire(EventType.Trigger.forceProjectorBreak);
                }
            }

            if(hit > 0f){
                hit -= 1f / 30f * Time.delta;
            }

            deflectBullets();
        }

        public float realRadius(){
            return radius * radscl;
        }

        public void deflectBullets(){
            float realRadius = realRadius();

            if(realRadius > 0 && !broken){
                paramEntity = this;
                paramEffect = absorbEffect;
                Groups.bullet.intersect(x - realRadius, y - realRadius, realRadius * 2f, realRadius * 2f, shieldConsumer);
            }
        }

    }
}
