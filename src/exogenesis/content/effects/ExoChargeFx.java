package exogenesis.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
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

public class ExoChargeFx {
    public static final Effect
            superCriticalCharge = new Effect(38f, e -> {
        color(ExoPal.cronusRed);

        randLenVectors(e.id, 14, 1f + 20f * e.fout(), e.rotation, 190f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 1f);
        });
    }),

    superCriticalChargeBegin = new Effect(60f, e -> {
        float margin = 1f - Mathf.curve(e.fin(), 0.9f);
        float fin = Math.min(margin, e.fin());
        color(ExoPal.cronusRed);
        Fill.circle(e.x, e.y, fin * 6f);

        color(Color.white);
        Fill.circle(e.x, e.y, fin * 5f);
    }),
    muonCharge = new Effect(420, e -> {
        color(Color.valueOf(String.valueOf(Pal.heal)));
        Fill.circle(e.x, e.y, e.fin() * 11f);
        color(Color.white);
        Fill.circle(e.x, e.y, e.fin() * 9f);
    }),
    starChargePink = new Effect(100f, 160f, e -> {
        color(ExoPal.empyreanPinkLight);
        Fill.circle(e.x, e.y, e.fin() * 10);
        color(Color.white);
        Fill.circle(e.x, e.y, e.fin() * 6);
    }).followParent(true).rotWithParent(true),

    OrbitalCharge = new Effect(160f, 100f, e -> {
        color(Pal.heal);
        stroke(e.fin() * 3f);
        Lines.circle(e.x, e.y, 18f + e.fout() * 100f);

        Fill.circle(e.x, e.y, e.fin() * 20);

        randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
            Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Pal.heal, 0.7f);
        });

        color();

        Fill.circle(e.x, e.y, e.fin() * 10);
        Drawf.light(e.x, e.y, e.fin() * 20f, Pal.heal, 0.7f);
    }).followParent(true).rotWithParent(true),

    demiurgeCharge = new Effect(106f, e -> {
        color(ExoPal.empyreanIndigo);

        stroke(e.fin() * 2f);
        Lines.circle(e.x, e.y, 8f + e.fout() * 100f);

        Fill.circle(e.x, e.y, e.fin() * 10f);
        color(Color.white);
        Fill.circle(e.x, e.y, e.fin() * 6f);

        Drawf.tri(e.x, e.y, 12f * e.fout(), 55f + (20f * e.fin()), e.rotation);

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
