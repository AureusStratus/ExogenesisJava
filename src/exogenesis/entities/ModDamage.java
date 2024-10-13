package exogenesis.entities;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.struct.IntSeq;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Nullable;
import arc.util.Time;
import exogenesis.content.ExoFxf;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Unit;

public class ModDamage {
    public static Seq<Unit> list = new Seq<>();

    public static void chain(Position origin, @Nullable Position targetPos, Team team, Unit current, IntSeq collided, Sound hitSound, Effect hitEffect, float power, float initialPower, float width, float distanceDamageFalloff, float pierceDamageFactor, int branches, float segmentLength, float arc, Color color){

        current.damage(power);
        hitSound.at(current.x, current.y, Mathf.random(0.8f, 1.1f));
        //Scales down width based on percent of power left
        float w = width * power/(initialPower);

        ExoFxf.chainLightning.at(current.x, current.y, 0, color, new ExoFxf.VisualLightningHolder() {
            @Override
            public Vec2 start() {
                return new Vec2(origin.getX(), origin.getY());
            }

            @Override
            public Vec2 end() {
                return new Vec2(current.x, current.y);
            }

            @Override
            public float width() {
                return w;
            }

            @Override
            public float segLength(){
                return segmentLength;
            }

            @Override
            public float arc() {
                return arc;
            }
        });
        hitEffect.at(current.x, current.y, 0, color);
        if(!current.dead) collided.add(current.id);

        float effectiveRange = power/distanceDamageFalloff;

        final float newPower = power * (pierceDamageFactor == 0 ? 1 : pierceDamageFactor);

        boolean derelict = team.id == Team.derelict.id;
        int teamID = team.id;

        Position tPos = targetPos == null ? current : targetPos;

        Time.run(15, () -> {
            Seq<Unit> units = Groups.unit.intersect(current.x - effectiveRange, current.y - effectiveRange, effectiveRange * 2, effectiveRange * 2);
            units.sort(u -> u.dst(tPos));
            if(units.contains(current)) units.remove(current);
            list.clear();
            for (int i = 0; i < Math.min(branches, units.size); i++) {
                Unit unit = units.get(i);
                if(collided.contains(unit.id) || !derelict && unit.team.id == teamID) continue;
                float dst = unit.dst(current);
                if(dst > effectiveRange) break;
                list.add(unit);
            }
            if(list.size == 0) return;
            float numberMultiplier = 1.0f/list.size;

            list.each(u -> {
                float newDamage = power - distanceDamageFalloff * current.dst(u);
                Log.info(Core.bundle.format("Old power was: {0}, while new one is {1}", power, newPower));
                if(newPower < 0) return;
                chain(current, u, collided, hitSound, hitEffect, newDamage * numberMultiplier, initialPower, width, distanceDamageFalloff, pierceDamageFactor, branches, segmentLength, arc, color);
            });
        });
    }

    public static void chain(Position origin, Unit current, IntSeq collided, Sound hitSound, Effect hitEffect, float power, float initialPower, float width, float distanceDamageFalloff, float pierceDamageFactor, int branches, float segmentLength, float arc, Color color) {
        chain(origin, null, Team.derelict, current, collided, hitSound, hitEffect, power, initialPower, width, distanceDamageFalloff, pierceDamageFactor, branches, segmentLength, arc, color);
    }
}