package exogenesis.type.bullet;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import exogenesis.graphics.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;

public class RayBulletType extends BulletType {
    public Color color, lightColor;
    public float frac = 1f / 6f, radius = 8f, length = 160f;
    public float lightLayer = Layer.bullet - 0.03f;
    public Blending blending = Blending.additive;

    public RayBulletType() {
        speed = 0f;
        despawnEffect = Fx.none;
        smokeEffect = Fx.none;
        impact = true;
        keepVelocity = false;
        collides = false;
        pierce = true;
        hittable = false;
        absorbable = false;
    }

    @Override
    public void update(Bullet b) {
        super.update(b);

        Units.nearbyEnemies(b.team, b.x, b.y, b.fin() * length + radius, u -> {
            if (!u.hittable()) return;

            if (u.dst(b) >= b.fin() * length - radius && Angles.near(b.rotation(), b.angleTo(u), 360f * frac)) {
                u.collision(b, u.x, u.y);
                b.collision(u, u.x, u.y);
            }
        });

        Vars.indexer.eachBlock(null, b.x, b.y, b.fin() * length + radius, build -> build.team != b.team, build -> {
            if (build.dst(b) >= b.fin() * length - radius && Angles.near(b.rotation(), b.angleTo(build), 360f * frac)) {
                build.collision(b);
                hit(b, build.x, build.y);
            }
        });
    }

    @Override
    public void draw(Bullet b) {
        super.draw(b);
        Lines.stroke(radius * 2f * b.foutpow());
        Draw.color(color);
        Lines.arc(b.x, b.y, b.fin() * length, frac, b.rotation() - (360f * frac / 2f));
        Draw.z(lightLayer);
        Draw.blend(blending);
        Drawd.lightArc(b.x, b.y, 20, b.fin() * length, b.rotation()  - (360f * frac / 2f), frac * 360f, Color.clear, Tmp.c1.set(lightColor).a(b.fout()));
        Draw.blend();
    }
}
