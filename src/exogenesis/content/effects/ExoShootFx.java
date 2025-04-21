package exogenesis.content.effects;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import exogenesis.graphics.ExoPal;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class ExoShootFx {
    public static final Effect
    HaborymShoot = new Effect(38f, e -> {
        color(ExoPal.cronusRedlight);

        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 16f * e.fout(), 85f, e.rotation + (65f + (e.fin(Interp.circleOut) * 60f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 12f * e.fout(), 65f, e.rotation + (50f + (e.fin(Interp.circleOut) * 40f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 9f * e.fout(), 40f, e.rotation + (45f + (e.fin(Interp.circleOut) * 20f)) * Mathf.signs[i]);
        }
    }),
    arbitorShoot = new Effect(26f, e -> {
        color(ExoPal.empyreanblue);
        Drawf.tri(e.x, e.y, 9f * e.fout(), 105f - (20f * e.fin()), e.rotation);
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 5f * e.fout(), 25f, e.rotation + (5f + (e.fin(Interp.circleOut) * 30f)) * Mathf.signs[i]);
        }

    }),
            weldSpark = new Effect(12f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 7, 55f * e.finpow(), e.rotation, 100f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            }),
    neutronShoot = new Effect(26f, e -> {
        color(e.color);
        Drawf.tri(e.x, e.y, 9f * e.fout(), 80f - (20f * e.fin()), e.rotation);
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 3f * e.fout(), 25f, e.rotation + (5f + (e.fin(Interp.circleOut) * 30f)) * Mathf.signs[i]);
        }

    });
}
