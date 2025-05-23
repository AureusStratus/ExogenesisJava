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

public class ExoHitFx {
    public static final Effect
            lightHitLarge = new Effect(15f, e -> {
        color(e.color, Color.white, e.fin());
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 17, e.finpow() * 50f, (x, y) -> {
            float a = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, a, e.fout() * 8f);
        });

        stroke(0.5f + e.fout() * 1.2f);
        Lines.circle(e.x, e.y, e.finpow() * 30f);
    }),
    decayHitEffect = new Effect(13f, e -> {
        color(e.color, Color.white, e.fin());
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 17, e.finpow() * 20f, (x, y) -> {
            float a = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, a, e.fout() * 8f);
        });
    });

}
