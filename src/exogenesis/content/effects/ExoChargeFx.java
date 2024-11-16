package exogenesis.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import exogenesis.graphics.ExoPal;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class ExoChargeFx {
    public static final Effect
    demiurgeCharge = new Effect(100f, e -> {
        color(ExoPal.empyreanIndigo);

        stroke(e.fin() * 2f);
        Lines.circle(e.x, e.y, 8f + e.fout() * 100f);

        Fill.circle(e.x, e.y, e.fin() * 10f);
        color(Color.white);
        Fill.circle(e.x, e.y, e.fin() * 6f);

        Drawf.tri(e.x, e.y, 10f * e.fout(), 35f + (20f * e.fin()), e.rotation);

        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 16f * e.fout(), 40f, e.rotation + (0f + (e.fin(Interp.circleOut) * 54f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 12f * e.fout(), 35f, e.rotation + (0f + (e.fin(Interp.circleOut) * 36.0f)) * Mathf.signs[i]);
        }
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 9f * e.fout(), 20f, e.rotation + (0f + (e.fin(Interp.circleOut) * 18f)) * Mathf.signs[i]);
        }
    });
}
