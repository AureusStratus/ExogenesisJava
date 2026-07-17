package exogenesis.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.alpha;
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
            hitFlamePlasmaColor = new Effect(14, e -> {
                color(Color.white, e.color, e.fin());
                stroke(0.5f + e.fout());

                randLenVectors(e.id, 2, 1f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
                });
            }),
            fireHitColor = new Effect(35f, e -> {
                color(Color.white, e.color, e.fin());

                randLenVectors(e.id, 3, 2f + e.fin() * 10f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 0.2f + e.fout() * 1.6f);
                });

                color();
            }),
            hydrogenFlameHit = new Effect(19, e -> {
                color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

                randLenVectors(e.id, 7, e.finpow() * 11f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout() * 4 + 2.5f);
                });
            }),
            heliumFlameHit = new Effect(14, e -> {
                color(Color.valueOf("fff69b"), Color.valueOf("ffda71"), e.fin());

                randLenVectors(e.id, 7, e.finpow() * 11f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout() * 2 + 0.5f);
                });
            }),
            ozoneFlameHit = new Effect(14, e -> {
                color(Color.valueOf("a1f7ff"), Color.valueOf("5cb1ff"), Color.valueOf("b876ff"), e.fin());

                randLenVectors(e.id, 7, e.finpow() * 21f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleOut) * 2 + 0.5f);
                });
            }),
            CyanogenFlameHit = new Effect(12, e -> {
                color(Color.valueOf("5cffff"), Color.valueOf("ff5c87").a(0.4f), Color.valueOf("5353ff").a(0.2f), e.fin());

                randLenVectors(e.id, 7, e.finpow() * 21f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleOut) * 2 + 0.5f);
                });
            }),
            hitScepterSecondaryColor = new Effect(8, e -> {
                rand.setSeed(e.id);

                for(int i : Mathf.signs){
                    color(Color.white, e.color, e.fout() * 1.2f);
                    Drawf.tri(e.x, e.y, e.fout() * 0.2f + 2f, 5f + 30f * e.fout(), e.rotation + 155f * i);
                }

                for(int s = 0; s < rand.random(1, 5); s++){
                    float stroke = rand.random(0.5f * e.fin(), e.fin());
                    float angle = rand.random(e.rotation - 20f, e.rotation + 20f);
                    Tmp.v1.trns(angle, rand.random(2f, 40f) * e.fin());
                    alpha(e.fout() * rand.random(0.4f, 2f));

                    color(e.color, Color.white, e.fin() * 0.8f);
                    Lines.stroke(stroke * 1.5f * e.fin() + 0.2f);
                    Lines.lineAngle(e.x + Tmp.v1.x, e.y + Tmp.v1.y, angle,rand.random(3f, 9f) + 1.5f * e.fin());
                }

            }).layer(Layer.bullet - 1f),

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
