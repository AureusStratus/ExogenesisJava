package exogenesis.entities.entitiesBH.abilities;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import exogenesis.entities.effect.*;
import exogenesis.entities.entitiesBH.effect.SwirlEffect;
import exogenesis.graphics.*;
import exogenesis.graphics.graphicsBH.BlackHoleRenderer;
import exogenesis.util.BlackHoleUtils;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.meta.*;

import static exogenesis.graphics.graphicsBH.BHDrawf.*;
import static mindustry.Vars.*;

public class BlackHoleAbility extends Ability{
    protected static Vec2 vec = new Vec2();
    
    public float x, y;
    public float damageInterval = 2f;
    /** If true, only activates when shooting. */
    public boolean whenShooting = false;
    public float warmupSpeed = 0.06f;
    public boolean drawBlackHole = true;
    public float horizonRadius = -1f, lensingRadius = -1f;
    public float damageRadius = 6f, suctionRadius = 160f;
    public boolean repel = false;
    /** Base amount of force applied to units */
    public float force = 10f;
    /** Scaled amount of force applied to units. As units get closer to the center, more of scaledForce is added to force. */
    public float scaledForce = 800f;
    /** Base amount of force applied to bullets. */
    public float bulletForce = 0.1f;
    /** Scaled amount of force applied to bullets. As bullets get closer to the center, more of scaledForce is added to force. */
    public float scaledBulletForce = 1f;
    public float damage = 30f, bulletDamage = 10f;
    /** Color of black hole and effects. If null, uses team color. */
    public @Nullable Color color = null;
    public float starWidth = -1, starHeight = -1, starAngle;
    public @Nullable Color starIn, starOut;

    public Effect swirlEffect = new SwirlEffect(Color.black);
    public float swirlInterval = 3f;
    public int swirlEffects = 4;
    public boolean counterClockwise = false;

    protected float effectTimer;
    protected float suctionTimer;
    protected float scl;

    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + Stat.range.localized() + ": [white]" + StatValues.fixValue(suctionRadius / tilesize) + " " + StatUnit.blocks.localized());
        t.row();
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + StatValues.fixValue(damage * 60f / damageInterval) + StatUnit.perSecond.localized());
    }

    @Override
    public void init(UnitType type){
        if(horizonRadius < 0f) horizonRadius = damageRadius;
        if(lensingRadius < 0f) lensingRadius = suctionRadius;
        if(!whenShooting) scl = 1f;
        if(starWidth > 0 && starHeight < 0) starHeight = starWidth / 2;
        BlackHoleUtils.immuneUnits.add(type);
    }

    @Override
    public void draw(Unit unit){
        if(!drawBlackHole || scl < 0.01f) return;

        vec.set(x, y).rotate(unit.rotation - 90f).add(unit);
        BlackHoleRenderer.addBlackHole(
            vec.x, vec.y,
            horizonRadius * scl, lensingRadius * scl,
            teamColor(unit, color)
        );
        if(starWidth > 0){
            BlackHoleRenderer.addStar(
                vec.x, vec.y,
                starWidth * scl, starHeight * scl, starAngle,
                teamColor(unit, starIn), teamColor(unit, starOut)
            );
        }
    }

    @Override
    public void update(Unit unit){
        boolean active = unit.isShooting || !whenShooting;
        if(active){
            scl = Mathf.lerpDelta(scl, 1f, warmupSpeed);
        }else{
            scl = Mathf.lerpDelta(scl, 0f, warmupSpeed);
        }

        if(scl < 0.01f) return;

        vec.set(x, y).rotate(unit.rotation - 90f);
        if((suctionTimer += Time.delta) >= damageInterval){
            BlackHoleUtils.blackHoleUpdate(
                unit.team, unit, vec.x, vec.y,
                damageRadius * scl, suctionRadius * scl,
                damage, bulletDamage,
                repel, force, scaledForce, bulletForce, scaledBulletForce
            );
            suctionTimer %= damageInterval;
        }

        if((effectTimer += Time.delta) >= swirlInterval){
            vec.add(unit);
            for(int i = 0; i < swirlEffects; i++){
                swirlEffect.at(
                    vec.x, vec.y,
                    suctionRadius * (counterClockwise ? -1f : 1f),
                    teamColor(unit, color), unit
                );
            }
            effectTimer %= swirlInterval;
        }
    }
}
