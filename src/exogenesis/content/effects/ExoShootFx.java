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
            Drawf.tri(e.x, e.y, 16f * e.fout(), 85f, e.rotation + (35f + (e.fin(Interp.circleOut) * 60f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 12f * e.fout(), 65f, e.rotation + (30f + (e.fin(Interp.circleOut) * 40f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 9f * e.fout(), 40f, e.rotation + (25f + (e.fin(Interp.circleOut) * 20f)) * Mathf.signs[i]);
        }
    });
}
