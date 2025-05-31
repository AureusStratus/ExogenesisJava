package exogenesis.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Tmp;
import exogenesis.graphics.ExoPal;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;

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
            }),
            smallerLightSmallExo = new Effect(40f, 100f, e -> {
                float circleRad = 10f + e.finpow() * 20f;

                color(e.color, e.foutpow());
                Fill.circle(e.x, e.y, circleRad);
            }).layer(Layer.bullet + 2f),

            smallerExplosionFragExo = new Effect(20f, 50f, e -> {
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 10f + e.finpow() * 20f;
                Lines.circle(e.x, e.y, circleRad);

                rand.setSeed(e.id);
                for(int i = 0; i < 8; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 15f, e.fout() * 15f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
            }),

            titanLightSmallExo = new Effect(40f, 100f, e -> {
                float circleRad = 15f + e.finpow() * 20f;

                color(e.color, e.foutpow());
                Fill.circle(e.x, e.y, circleRad);
            }).layer(Layer.bullet + 2f),

            titanExplosionFragExo = new Effect(20f, 50f, e -> {
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 15f + e.finpow() * 20f;
                Lines.circle(e.x, e.y, circleRad);

                rand.setSeed(e.id);
                for(int i = 0; i < 8; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 15f, e.fout() * 20f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
            });
}
