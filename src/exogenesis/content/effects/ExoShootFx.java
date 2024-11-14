package exogenesis.content.effects;

import arc.math.Interp;
import arc.math.Mathf;
import exogenesis.graphics.ExoPal;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.color;

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

    });
}
