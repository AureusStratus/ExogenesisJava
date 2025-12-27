package exogenesis.content;
import exogenesis.entities.effect.SwirlEffect;
import exogenesis.graphics.ExoPal;
import exogenesis.util.feature.PositionLightning;
import exogenesis.util.func.DrawFunc;

import exogenesis.util.struct.Vec2Seq;
import exogenesis.util.util.GraphicUtils;
import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.type.UnitType;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static arc.math.Angles.randLenVectors;
import static arc.math.Interp.*;
import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;
import static mindustry.graphics.Drawf.light;


public class ExoFx{
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect
        empyreanExplosion = new Effect(30f, 160f, e -> {
        color(e.color);
        stroke(e.fout() * 5f);
        float circleRad = 6f + e.finpow() * 60f;
        Lines.circle(e.x, e.y, circleRad);
        stroke(e.fout());
        randLenVectors(e.id + 1, 8, 5f + 60f * e.finpow(), (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 3f + e.fout() * 3f));
        rand.setSeed(e.id);
        for(int i = 0; i < 16; i++){
            float angle = rand.random(360f);
            float lenRand = rand.random(0.5f, 1f);
            Tmp.v1.trns(angle, circleRad);

            for(int s : Mathf.signs){
                Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 30f, e.fout() * 20f * lenRand + 6f, angle + 90f + s * 90f);
            }
        }
        color(e.color);
        for(int i = 0; i < 7; i++){
            Drawf.tri(e.x, e.y, 6f, 70f * e.fout(), i*90);
        }
        color();
        for(int i = 0; i < 7; i++){
            Drawf.tri(e.x, e.y, 3f, 35f * e.fout(), i*90);
        }
    }),
            empyreanExplosionSplash = new Effect(30f, 160f, e -> {
                color(e.color);
                stroke(e.fout() * 4f);
                float circleRad = 2f + e.finpow() * 60f;
                Lines.circle(e.x, e.y, circleRad);
                stroke(e.fout());
                randLenVectors(e.id + 1, 8, 5f + 60f * e.finpow(), (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 3f + e.fout() * 3f));
                rand.setSeed(e.id);
                for(int i = 0; i < 16; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 30f, e.fout() * 20f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
            }),
            missileExplosion = new Effect(30, 500f, e -> {
                float intensity = 2f;
                float baseLifetime = 25f + intensity * 15f;
                e.lifetime = 50f + intensity * 64f;

                color(Color.darkGray);
                alpha(0.9f);
                for(int i = 0; i < 5; i++){
                    rand.setSeed(e.id * 2L + i);
                    float lenScl = rand.random(0.25f, 1f);
                    int fi = i;
                    e.scaled(e.lifetime * lenScl, s -> {
                        randLenVectors(s.id + fi - 1, s.fin(Interp.pow10Out), (int)(2.8f * intensity), 25f * intensity, (x, y, in, out) -> {
                            float fout = s.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 2.35f);
                            Fill.circle(s.x + x, s.y + y, rad);
                        });
                    });
                }

                e.scaled(baseLifetime, s -> {
                    color(Color.gray);
                    s.scaled(3 + intensity * 2f, i -> {
                        stroke((3.1f + intensity/5f) * i.fout());
                        Lines.circle(s.x, s.y, (3f + i.fin() * 14f) * intensity);
                        light(s.x, s.y, i.fin() * 28f * 2f * intensity, Color.white, 0.9f * i.fout());
                    });

                    color(Pal.lighterOrange, Pal.lightOrange, Pal.redSpark, s.fin());
                    stroke((2f * s.fout()));

                    Draw.z(Layer.effect + 0.001f);
                    randLenVectors(s.id + 1, s.finpow() + 0.001f, (int)(8 * intensity), 30f * intensity, (x, y, in, out) -> {
                        lineAngleCenter(s.x + x, s.y + y, angle(x, y), 1f + out * 4 * (4f + intensity));
                        light(s.x + x, s.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
                    });
                });
            }).layer(Layer.bullet - 0.021f),
            ExoTrailSmoke = new Effect(50, e -> {
                color(e.color);
                rand.setSeed(e.id);
                for(int i = 0; i < 4; i++){
                    float fin = e.fin() / rand.random(0.5f, 1f), fout = 1f - fin, angle = rand.random(360f), len = rand.random(0.5f, 1f);

                    if(fin <= 1f){
                        Tmp.v1.trns(angle, fin * 24f * len);

                        alpha((0.5f - Math.abs(fin - 0.5f)) * 2f);
                        Fill.square(e.x + Tmp.v1.x, e.y + Tmp.v1.y, 0.5f + fout * 4f);
                    }
                }
            }),
            ExoTrailSmoke1 = new Effect(65, e -> {
                color(e.color);
                rand.setSeed(e.id);
                for(int i = 0; i < 4; i++){
                    float fin = e.fin() / rand.random(0.5f, 1f), fout = 1f - fin, angle = rand.random(360f), len = rand.random(0.5f, 8f);

                    if(fin <= 1f){
                        Tmp.v1.trns(angle, fin * 24f * len);

                        alpha((0.5f - Math.abs(fin - 0.5f)) * 2f);
                        Fill.circle(e.x + Tmp.v1.x, e.y + Tmp.v1.y, 0.5f + fout * 4f);
                    }
                }
            }),
    randLifeSparkExo = new Effect(24f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.5f + 0.5f);

                rand.setSeed(e.id);
                for(int i = 0; i < 15; i++){
                    float ang = e.rotation + rand.range(9f), len = rand.random(90f * e.finpow());
                    e.scaled(e.lifetime * rand.random(0.5f, 1f), p -> {
                        v.trns(ang, len);
                        lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 10f + 0.5f);
                    });
                }
            }),
            randLifeSparkExo1 = new Effect(28f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.5f + 0.5f);

                rand.setSeed(e.id);
                for(int i = 0; i < 5; i++){
                    float ang = e.rotation + rand.range(9f), len = rand.random(90f * e.finpow());
                    e.scaled(e.lifetime * rand.random(0.5f, 1f), p -> {
                        v.trns(ang, len);
                        lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 10f + 0.5f);
                    });
                }
            }),
            randLifeSparkCone = new Effect(23f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.5f + 0.5f);

                rand.setSeed(e.id);
                for(int i = 0; i < 5; i++){
                    float ang = e.rotation + rand.range(360f), len = rand.random(90f * e.finpow());
                    e.scaled(e.lifetime * rand.random(0.2f, 1f), p -> {
                        v.trns(ang, len);
                        lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 10f + 0.5f);
                    });
                }
            }),
            coolBulletTrail = new Effect(13, e -> {
                color(Color.white, e.color, e.fin());
                stroke(0.6f + e.fout() * 1.7f);
                rand.setSeed(e.id);

                for(int i = 0; i < 2; i++){
                    float rot = e.rotation + rand.range(15f) + 180f;
                    v.trns(rot, rand.random(e.fin() * 27f));
                    lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(6f, 16f) + 1.5f);
                }
            }),
            spawnGround = new Effect(60f, e -> {
                Draw.color(e.color, Pal.gray, e.fin());
                randLenVectors(e.id, (int)(e.rotation * 1.35f), e.rotation * tilesize / 1.125f * e.fin(), (x, y) -> Fill.square(e.x + x, e.y + y, e.rotation * e.fout(), 45));
            }),
            circle = new Effect(25f, e -> {
                color(e.color, Color.white, e.fout() * 0.65f);
                stroke(Mathf.clamp(e.rotation / 18f, 2, 6) * e.fout());
                circle(e.x, e.y, e.rotation * e.finpow());
            }),
            decayEffect = new Effect(24f, e -> {
                color(ExoPal.cronusRed, Color.white, e.fin());
                stroke(1.25f);
                lineAngle(e.x, e.y, e.rotation, e.fout() * 4f);
            }),
            decayEffectYellow = new Effect(24f, e -> {
                color(ExoPal.empyrean, Color.white, e.fin());
                stroke(1.25f);
                lineAngle(e.x, e.y, e.rotation, e.fout() * 4f);
            }),
            healPyric = new Effect(11, e -> {
                color(ExoPal.empyreanPyre);
                stroke(e.fout() * 2f);
                Lines.circle(e.x, e.y, 2f + e.finpow() * 7f);
            }),

    decayEffectLong = new Effect(47f, e -> {
        color(Color.white, e.color, e.fin());
        stroke(1.25f);
        lineAngle(e.x, e.y, e.rotation, e.fout() * 7f);
    }),


    jumpTrail = new Effect(120f, 5000, e -> {
        if (!(e.data instanceof UnitType))return;
        UnitType type = e.data();
        color(type.engineColor == null ? e.color : type.engineColor);

        if(type.engineLayer > 0)Draw.z(type.engineLayer);
        else Draw.z((type.lowAltitude ? Layer.flyingUnitLow : Layer.flyingUnit) - 0.001f);

        for(int index = 0; index < type.engines.size; index++){
            UnitType.UnitEngine engine = type.engines.get(index);

            if(Angles.angleDist(engine.rotation, -90) > 75)return;
            float ang = Mathf.slerp(engine.rotation, -90, 0.75f);

            //noinspection SuspiciousNameCombination
            Tmp.v1.trns(e.rotation, engine.y, -engine.x);

            e.scaled(80, i -> {
                DrawFunc.tri(i.x + Tmp.v1.x, i.y + Tmp.v1.y, engine.radius * 1.5f * i.fout(Interp.slowFast), 3000 * engine.radius / (type.engineSize + 4), i.rotation + ang - 90);
                Fill.circle(i.x + Tmp.v1.x, i.y + Tmp.v1.y, engine.radius * 1.5f * i.fout(Interp.slowFast));
            });

            randLenVectors(e.id + index, 22, 400 * engine.radius / (type.engineSize + 4), e.rotation + ang - 90, 0f, (x, y) -> lineAngle(e.x + x + Tmp.v1.x, e.y + y + Tmp.v1.y, Mathf.angle(x, y), e.fout() * 60));
        }

        Draw.color();
        Draw.mixcol(e.color, 1);
        Draw.rect(type.fullIcon, e.x, e.y, type.fullIcon.width * e.fout(Interp.pow2Out) * Draw.scl * 1.2f, type.fullIcon.height * e.fout(Interp.pow2Out) * Draw.scl * 1.2f, e.rotation - 90f);
        Draw.reset();
    }),
            lightningFade = (new Effect(PositionLightning.lifetime, 1200.0f, e -> {
                if(!(e.data instanceof Vec2Seq)) return;
                Vec2Seq points = e.data();

                e.lifetime = points.size() < 2 ? 0 : 1000;
                int strokeOffset = (int)e.rotation;


                if(points.size() > strokeOffset + 1 && strokeOffset > 0 && points.size() > 2){
                    points.removeRange(0, points.size() - strokeOffset - 1);
                }

                if(!state.isPaused() && points.any()){
                    points.remove(0);
                }

                if(points.size() < 2)return;

                Vec2 data = points.peekTmp(); //x -> stroke, y -> fadeOffset;
                float stroke = data.x;
                float fadeOffset = data.y;

                Draw.color(e.color);
                for(int i = 1; i < points.size() - 1; i++){
//				Draw.alpha(Mathf.clamp((float)(i + fadeOffset - e.time) / points.size()));
                    Lines.stroke(Mathf.clamp((i + fadeOffset / 2f) / points.size() * (strokeOffset - (points.size() - i)) / strokeOffset) * stroke);
                    Vec2 from = points.setVec2(i - 1, Tmp.v1);
                    Vec2 to = points.setVec2(i, Tmp.v2);
                    Lines.line(from.x, from.y, to.x, to.y, false);
                    Fill.circle(from.x, from.y, Lines.getStroke() / 2);
                }

                Vec2 last = points.tmpVec2(points.size() - 2);
                Fill.circle(last.x, last.y, Lines.getStroke() / 2);
            })).layer(Layer.effect - 0.001f),
            railTrail = new Effect(40, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? Color.white : e.color);

                    float m = i == 0 ? 1f : 0.5f;

                    float rot = e.rotation + 180f;
                    float w = 15f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, Pal.heal, 0.6f * e.fout());
            }),
            heliosSelfdamage = new Effect(35f, e -> {
                color(Color.white, Pal.heal, e.fin());

                randLenVectors(e.id, 3, 2f + e.fin() * 7f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 0.1f + e.fout() * 1.4f);
                });
            }),

    randLifeSparkExoFollow = new Effect(24f, e -> {
        color(Color.white, e.color, e.fin());
        stroke(e.fout() * 1.5f + 0.5f);

        rand.setSeed(e.id);
        for(int i = 0; i < 15; i++){
            float ang = e.rotation + rand.range(9f), len = rand.random(90f * e.finpow());
            e.scaled(e.lifetime * rand.random(0.5f, 1f), p -> {
                v.trns(ang, len);
                lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 10f + 0.5f);
            });
        }
    }).followParent(true).rotWithParent(true),
            randLifeSparkExo1Follow = new Effect(28f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.5f + 0.5f);

                rand.setSeed(e.id);
                for(int i = 0; i < 5; i++){
                    float ang = e.rotation + rand.range(9f), len = rand.random(90f * e.finpow());
                    e.scaled(e.lifetime * rand.random(0.5f, 1f), p -> {
                        v.trns(ang, len);
                        lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 10f + 0.5f);
                    });
                }
            }).followParent(true).rotWithParent(true),
            empyreanStarHitSmaller = new Effect(35, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout() * 3, e.fout() * 25, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout() * 1.2f, e.fout() * 15, e.rotation + (90 * i));
                }
            }),
    laserHit = new Effect(35, e -> {
        color(e.color);
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, e.fout() * 3, e.fout() * 40, e.rotation + (90 * i));
        }
    }),
            empyreanStarHitSmall = new Effect(35, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(circleIn) * 3, e.fout(circleIn) * 40, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(circleIn) * 1.2f, e.fout(circleIn) * 30, e.rotation + (90 * i));
                }
            }),
            empyreanStarHitSmallWave = new Effect(35, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 3, e.fout(Interp.circle) * 40, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 1.2f, e.fout(Interp.circle) * 30, e.rotation + (90 * i));
                }
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 4f + e.finpow() * 25f;
                Lines.circle(e.x, e.y, circleRad);
            }),
            empyreanStarHitMedium = new Effect(35, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 5.5f, e.fout(Interp.circle) * 70, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 3.5f, e.fout(Interp.circle) * 50, e.rotation + (90 * i));
                }
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 4f + e.finpow() * 35f;
                Lines.circle(e.x, e.y, circleRad);
            }),
            empyreanStarHitLarge = new Effect(35, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 5.5f, e.fout(Interp.circle) * 90, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout(Interp.circle) * 3.5f, e.fout(Interp.circle) * 70, e.rotation + (90 * i));
                }
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 4f + e.finpow() * 45f;
                Lines.circle(e.x, e.y, circleRad);
            }),

    neutronMorterShockWave = new Effect(120F, 600f, e -> {
        float rad = 40f;
        rand.setSeed(e.id);

        Draw.color(Color.white, e.color, e.fin() + 0.6f);
        float circleRad = e.fin(Interp.circleOut) * rad * 4f;
        Lines.stroke(4 * e.fout());
        Lines.circle(e.x, e.y, circleRad);
        for(int i = 0; i < 24; i++){
            Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
            DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
        }
        Draw.blend(Blending.additive);
        Draw.z(Layer.effect + 0.1f);

        Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
        Draw.blend();
        Draw.z(Layer.effect);

        Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.4f);
    }).layer(Layer.effect + 0.001f),

    odinNukeStar = new Effect(95, 1600f, e -> {
                color(e.color);
                e.rotation = e.fin() * 200;
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout() * 9.5f, e.fout() * 230, e.rotation + (90 * i));
                }
                color();
                for (int i = 0; i < 4; i++) {
                    Drawf.tri(e.x, e.y, e.fout() * 6.5f, e.fout() * 110, e.rotation + (90 * i));
                }
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 4f + e.finpow() * 75f;
                Lines.circle(e.x, e.y, circleRad);
            }).layer(Layer.effect + 0.002f),
            odinNukeShockWave = new Effect(160F, 1600f, e -> {
                float rad = 60f;
                rand.setSeed(e.id);

                Draw.color(Color.white, e.color, e.fin() + 0.6f);
                float circleRad = e.fin(Interp.circleOut) * rad * 4f;
                Lines.stroke(7 * e.fout());
                Lines.circle(e.x, e.y, circleRad);
                for(int i = 0; i < 24; i++){
                    Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
                    DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
                }
                Draw.blend(Blending.additive);
                Draw.z(Layer.effect + 0.1f);

                Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
                Draw.blend();
                Draw.z(Layer.effect);

                Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.7f);
            }).layer(Layer.effect + 0.001f),
            odinNukeExplosion = new Effect(60, 500f, b -> {
                float intensity = 6.8f;
                float baseLifetime = 25f + intensity * 11f;
                b.lifetime = 80f + intensity * 65f;

                color(Color.valueOf("ffa665"));
                alpha(0.7f);
                for(int i = 0; i < 4; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.4f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 2.35f);

                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 2.5f, e.color, 0.5f);
                        });
                    });
                }

                b.scaled(baseLifetime, e -> {
                    Draw.color();
                    e.scaled(5 + intensity * 2f, i -> {
                        stroke((3.1f + intensity/5f) * i.fout());
                        Lines.circle(e.x, e.y, (3f + i.fin() * 14f) * intensity);
                        Drawf.light(e.x, e.y, i.fin() * 14f * 2f * intensity, Color.white, 0.9f * e.fout());
                    });

                    color(e.color, e.fin());
                    stroke((2f * e.fout()));

                    Draw.z(Layer.effect + 0.001f);
                    randLenVectors(e.id + 1, e.finpow() + 0.001f, (int)(8 * intensity), 28f * intensity, (x, y, in, out) -> {
                        lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + out * 4 * (4f + intensity));
                        Drawf.light(e.x + x, e.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
                    });
                });
            }),

    heliosNukeExplosion = new Effect(60, 500f, b -> {
        float intensity = 6.8f;
        float baseLifetime = 25f + intensity * 11f;
        b.lifetime = 80f + intensity * 65f;

        color(Pal.heal);
        alpha(0.7f);
        for(int i = 0; i < 4; i++){
            rand.setSeed(b.id*2 + i);
            float lenScl = rand.random(0.4f, 1f);
            int fi = i;
            b.scaled(b.lifetime * lenScl, e -> {
                randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * ((2f + intensity) * 2.35f);

                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, e.color, 0.5f);
                });
            });
        }

        b.scaled(baseLifetime, e -> {
            Draw.color();
            e.scaled(5 + intensity * 2f, i -> {
                stroke((3.1f + intensity/5f) * i.fout());
                Lines.circle(e.x, e.y, (3f + i.fin() * 14f) * intensity);
                Drawf.light(e.x, e.y, i.fin() * 14f * 2f * intensity, Color.white, 0.9f * e.fout());
            });

            color(e.color, e.fin());
            stroke((2f * e.fout()));

            Draw.z(Layer.effect + 0.001f);
            randLenVectors(e.id + 1, e.finpow() + 0.001f, (int)(8 * intensity), 28f * intensity, (x, y, in, out) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + out * 4 * (4f + intensity));
                Drawf.light(e.x + x, e.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
            });
        });
    }),
/*
            lightningFade = (new Effect(PositionLightning.lifetime, 1200.0f, e -> {
                if(!(e.data instanceof PositionLightning.Vec2Seq)) return;
                PositionLightning.Vec2Seq points = e.data();

                e.lifetime = points.size() < 2 ? 0 : 1000;
                int strokeOffset = (int)e.rotation;


                if(points.size() > strokeOffset + 1 && strokeOffset > 0 && points.size() > 2){
                    points.removeRange(0, points.size() - strokeOffset - 1);
                }

                if(!state.isPaused() && points.any()){
                    points.remove(0);
                }

                if(points.size() < 2)return;

                Vec2 data = points.peekTmp(); //x -> stroke, y -> fadeOffset;
                float stroke = data.x;
                float fadeOffset = data.y;

                Draw.color(e.color);
                for(int i = 1; i < points.size() - 1; i++){
//				Draw.alpha(Mathf.clamp((float)(i + fadeOffset - e.time) / points.size()));
                    Lines.stroke(Mathf.clamp((i + fadeOffset / 2f) / points.size() * (strokeOffset - (points.size() - i)) / strokeOffset) * stroke);
                    Vec2 from = points.setVec2(i - 1, Tmp.v1);
                    Vec2 to = points.setVec2(i, Tmp.v2);
                    Lines.line(from.x, from.y, to.x, to.y, false);
                    Fill.circle(from.x, from.y, Lines.getStroke() / 2);
                }

                Vec2 last = points.tmpVec2(points.size() - 2);
                Fill.circle(last.x, last.y, Lines.getStroke() / 2);
            })).layer(Layer.effect - 0.001f),

 */
            instShootExo = new Effect(24f, e -> {
                e.scaled(10f, b -> {
                    color(Color.white, e.color, b.fin());
                    stroke(b.fout() * 3f + 0.2f);
                    Lines.circle(b.x, b.y, b.fin() * 50f);
                });

                color(e.color);

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 85f, e.rotation + 90f * i);
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 50f, e.rotation + 20f * i);
                }

                Drawf.light(e.x, e.y, 180f, e.color, 0.9f * e.fout());
            }),
        disperseTrailLarger = new Effect(13, e -> {
            color(Color.white, e.color, e.fin());
            stroke(0.6f + e.fout() * 3.7f);
            rand.setSeed(e.id);

            for(int i = 0; i < 2; i++){
                float rot = e.rotation + rand.range(15f) + 180f;
                v.trns(rot, rand.random(e.fin() * 27f));
                lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(2f, 9f) + 1.5f);
            }
        }),
    crossBlastArrow45 = new Effect(65, 140, e -> {
        color(e.color, Color.white, e.fout() * 0.55f);
        Drawf.light(e.x, e.y, e.fout() * 70, e.color, 0.7f);

        e.scaled(10f, i -> {
            stroke(1.35f * i.fout());
            circle(e.x, e.y, 49 * i.finpow());
        });

        rand.setSeed(e.id);
        float sizeDiv = 138;
        float randL = rand.random(sizeDiv);

        float f = Mathf.curve(e.fin(), 0, 0.05f);

        for(int i = 0; i < 4; i++){
            Tmp.v1.trns(45 + i * 90, 66);
            DrawFunc.arrow(e.x + Tmp.v1.x, e.y + Tmp.v1.y, 27.5f * (e.fout() * 3f + 1) / 4 * e.fout(Interp.pow3In), (sizeDiv + randL) * f * e.fout(Interp.pow3), -randL / 6f * f, i * 90 + 45);
        }
    }),
         testHit1 = new Effect(30, e -> {
             color(Color.white, e.color, e.fin());
             for (int i = 0; i < 2; i++) {
                 Drawf.tri(e.x, e.y, e.fout() * 5, e.fout() * 70, e.rotation - 190 + (25 * i) - e.fin());
             }
             Lines.stroke(e.fout() * 1.5f);
             Fill.circle(e.x, e.y, e.fout() * 3);
             Lines.circle(e.x, e.y, e.finpow() * 20);
        }),
                 shootGiant = new Effect(20, e -> {
                     color(Pal.lightOrange, Color.gray, e.fin());
                     float w = 1.2f + 12 * e.fout();
                     Drawf.tri(e.x, e.y, w, 29f * e.fout(), e.rotation);
                     Drawf.tri(e.x, e.y, w, 5f * e.fout(), e.rotation + 180f);
                 }),
                 casingLarge = new Effect(45f, e -> {
                     color(Pal.lightOrange, Pal.lightishGray, Pal.lightishGray, e.fin());
                     alpha(e.fout(0.5f));
                     float rot = Math.abs(e.rotation) + 90f;
                     int i = -Mathf.sign(e.rotation);
                     float len = (4f + e.finpow() * 9f) * i;
                     float lr = rot + Mathf.randomSeedRange(e.id + i + 6, 20f * e.fin()) * i;

                     rect(Core.atlas.find("casing"),
                             e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                             e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                             4.5f, 8f,
                             rot + e.fin() * 50f * i
                     );
                 }).layer(Layer.bullet),
            PrometheusSmoke = new Effect(120f, 300f, b -> {
                float intensity = 4f;

                color(b.color, 0.3f);
                for(int i = 0; i < 4; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.5f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 25f * intensity, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 2.35f);

                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 3.5f, b.color, 0.5f);
                        });
                    });
                }
            }),
            PrometheusExplosionSplash = new Effect(40f, 160f, e -> {
                color(e.color);
                stroke(e.fout() * 5f);
                float circleRad = 12f + e.finpow() * 60f;
                Lines.circle(e.x, e.y, circleRad);
                stroke(e.fout());
                randLenVectors(e.id + 1, 8, 6f + 60f * e.finpow(), (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 3f + e.fout() * 3f));
                rand.setSeed(e.id);
                for(int i = 0; i < 16; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 30f, e.fout() * 20f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
            }),
    PrometheusShoot = new Effect(80f, e -> {
        color(Color.valueOf("feb380"));

        for(int i : Mathf.signs){
            Drawf.tri(e.x, e.y, 13f * e.fout(), 145f, e.rotation + -30f * i);
            Drawf.tri(e.x, e.y, 19f * e.fout(), 175f, e.rotation + 90f * i);
            Drawf.tri(e.x, e.y, 16f * e.fout(), 80f, e.rotation + 20f * i);
        }

        Drawf.light(e.x, e.y, 180f, Color.valueOf("feb380"), 0.9f * e.fout());
    }).followParent(true).rotWithParent(true),
            ShockWaveTrail = new Effect(25f, 600f, e -> {
                //GraphicUtils.drawShockWave(e.x, e.y, 75f, 0f, -e.rotation - 90f, 200f, 4f, 12);
                color(Color.white);
                alpha(0.666f * e.fout());

                float size = e.data instanceof Float ? (float)e.data : 200f;
                float nsize = size - 2f;
                GraphicUtils.drawShockWave(e.x, e.y, -25f, 8f, -e.rotation - 90f, nsize * e.finpow() + 2, 3f * e.finpow() + 4f, 16, 1f);
            }).layer((Layer.bullet + Layer.effect) / 2),
            PrometheusBeamShockWave = new Effect(45f, 600f, e -> {
                Draw.z(Layer.effect);
                Draw.color(ExoPal.prometheusColor,e.fout());
                Lines.stroke(6f*e.fout());               Tmp.v1.trns(e.rotation, e.fin()*30f);
                Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 3f * e.fin()+0.1f,6,10, e.rotation);
                Tmp.v1.trns(e.rotation, e.fin()*20f);
                Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 5f * e.fin()+0.1f,8,12, e.rotation);
                Tmp.v2.trns(e.rotation, e.fin()*10f);
                Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 7f*e.fin()+0.1f,10, 18,  e.rotation);
            }),

            ullrChargeEffect = new Effect(40f, e -> {
                Angles.randLenVectors(e.id, 2, 10f, 90f, (x, y) -> {
                    float angle = Mathf.angle(x, y);
                    color(e.color, e.color, e.fin());
                    Lines.stroke(1.5f);
                    Lines.lineAngleCenter(e.x + (x * e.fout()), e.y + (y * e.fout()), angle, e.fslope() * 13f);
                });
            }).followParent(true).rotWithParent(true),

    ullrChargeBegin = new Effect(388f, e -> {
        Color[] colors = {Pal.heal, Pal.heal, Color.white};
        for(int ii = 0; ii < 3; ii++){
            float s = (3 - ii) / 3f;
            float width = Mathf.clamp(e.time / 60f) * (20f + Mathf.absin(Time.time + (ii * 1.4f), 1.1f, 7f)) * s;
            float length = e.fin() * (80f + Mathf.absin(Time.time + (ii * 1.4f), 1.1f, 11f)) * s;
            color(colors[ii]);
            for(int i : Mathf.signs){
                float rotation = e.rotation + (i * 90f);
                Drawf.tri(e.x, e.y, width, length * 0.5f, rotation);
            }
            Drawf.tri(e.x, e.y, width, length * 1.25f, e.rotation);
        }
    }),
            ullarTipHit = new Effect(27f, e ->
                    Angles.randLenVectors(e.id, 8, 90f * e.fin(), e.rotation, 80f, (x, y) -> {
                        float angle = Mathf.angle(x, y);
                        color(e.color, e.fin());
                        Lines.stroke(1.5f);
                        Lines.lineAngleCenter(e.x + x, e.y + y, angle, e.fslope() * 13f);
                    })),
            hitMeltColor = new Effect(12, e -> {
                color(e.color);
                stroke(e.fout() * 2f);

                randLenVectors(e.id, 6, e.finpow() * 18f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * 4 + 1f);
                });
            }),
            chargeTransfer = new Effect(20f, e -> {
                if(!(e.data instanceof Position)) return;
                Position to = e.data();
                Tmp.v1.set(e.x, e.y).interpolate(Tmp.v2.set(to), e.fin(), Interp.pow3)
                        .add(Tmp.v2.sub(e.x, e.y).nor().rotate90(1).scl(Mathf.randomSeedRange(e.id, 1f) * e.fslope() * 10f));
                float x = Tmp.v1.x, y = Tmp.v1.y, s = e.fslope() * 4f;
                Draw.color(e.color);
                Fill.square(x, y, s, 45f);
            }),
            toxicified = new Effect(40f, e -> {
                color(ExoPal.erekirPink);

                randLenVectors(e.id, 2, 1f + e.fin() * 2f, (x, y) -> {
                    Fill.square(e.x + x, e.y + y, e.fslope() * 1.1f, 45f);
                });
            }),
            auricCharge = new Effect(85, e -> {
                color(Color.valueOf(String.valueOf(ExoPal.empyrean)));
                Fill.circle(e.x, e.y, e.fin() * 20f);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 17f);
            }),
            enlightenmentCharge = new Effect(95, e -> {
                color(Color.valueOf(String.valueOf(ExoPal.empyreanIndigo)));
                Fill.circle(e.x, e.y, e.fin() * 12f);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 8f);
            }),

            pentaCharge = new Effect(115, e -> {
                color(Color.valueOf(String.valueOf(Pal.heal)));
                Fill.circle(e.x, e.y, e.fin() * 20f);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 17f);
            }),
            calamityCharge = new Effect(140, e -> {
                color(Color.valueOf(String.valueOf(ExoPal.genesisTitan)));
                Fill.circle(e.x, e.y, e.fin() * 25f);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 20f);
            }),
            colorBomb = new Effect(40f, 100f, e -> {
                color(e.color);
                stroke(e.fout() * 2f);
                float circleRad = 4f + e.finpow() * 45f;
                Lines.circle(e.x, e.y, circleRad);

                color(e.color);
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 4f, 50f * e.fout(), i*90);
                }

                color();
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 2f, 15f * e.fout(), i*90);
                }

                Drawf.light(e.x, e.y, circleRad * 1.6f, e.color, e.fout());
            }),
            empyreanCharge = new Effect(100f, 100f, e -> {
                color(ExoPal.empyrean);
                stroke(e.fin() * 5f);
                float circleRad = 6f + e.finpow() * -65f;
                Lines.circle(e.x, e.y, circleRad);

                color(ExoPal.empyrean);
                for(int i = 0; i < 5; i++){
                    Drawf.tri(e.x, e.y, 5f, 60f * e.fin(), i*90);
                }
                color();
                for(int i = 0; i < 5; i++){
                    Drawf.tri(e.x, e.y, 3f, 35f * e.fin(), i*90);
                }
                Drawf.light(e.x, e.y, circleRad * 1.6f, ExoPal.empyrean, e.fin());
            }),
            coloredHitLarge = new Effect(21f, e -> {
                color(Color.white, e.color, e.fin());
                e.scaled(8f, s -> {
                    stroke(0.5f + s.fout());
                    circle(e.x, e.y, s.fin() * 11f);
                });

                stroke(0.5f + e.fout());
                randLenVectors(e.id, 6, e.fin() * 35f, e.rotation + 180f, 95f, (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 10f + 1f));
            }),
            lightErnExplosion = new Effect(40, e -> {
                color(ExoPal.empyrean);
                e.scaled(20, i -> {
                    stroke(3f * i.fout());
                    circle(e.x, e.y, 3f + i.fin() * 100f);
                });

                stroke(e.fout());
                randLenVectors(e.id + 1, 8, 1f + 60f * e.finpow(), (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f));

                color(Color.white);

                randLenVectors(e.id, 5, 2f + 70 * e.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f));

                Drawf.light(e.x, e.y, e.fout(pow2Out) * 100f, ExoPal.empyrean, 0.7f);
            }),
            colorBombSmaller = new Effect(30f, 100f, e -> {
                color(e.color);
                stroke(e.fout());
                float circleRad = 3f + e.finpow() * 18f;
                Lines.circle(e.x, e.y, circleRad);

                color(e.color);
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 2f, 20f * e.fout(), i*90);
                }

                color();
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 0.9f, 6f * e.fout(), i*90);
                }

                Drawf.light(e.x, e.y, circleRad * 1.6f, e.color, e.fout());
            }),

            colorSparkShoot = new Effect(12f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            }),
                    flashShoot = new Effect(20f, 60f, e -> {
                        color(e.color);
                        for(int i = 0; i < 2; i++){
                            Drawf.tri(e.x, e.y, 1.2f, 20f * e.fout(), i*90);
                        }

                        color();
                        for(int i = 0; i < 2; i++){
                            Drawf.tri(e.x, e.y, 0.7f, 10f * e.fout(), i*90);
                        }

                    });
    public static final float lightningAlign = 0.5f;

    public static Effect
            trailFadeFast =  new Effect(600f, e -> {
        if(!(e.data instanceof Trail)) return;
        Trail trail = e.data();
        //lifetime is how many frames it takes to fade out the trail
        e.lifetime = trail.length * 1.4f;

        if(!state.isPaused()){
            trail.shorten();
            trail.shorten();
        }
        trail.drawCap(e.color, e.rotation * e.foutpow());
        trail.draw(e.color, e.rotation * e.foutpow());
    }),
            explodeyscathe = new Effect(35f, 160f, e -> {
                color(e.color);
                stroke(e.fout() * 5f);
                float circleRad = 12f + e.finpow() * 60f;
                Lines.circle(e.x, e.y, circleRad);

                rand.setSeed(e.id);
                for(int i = 0; i < 16; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 25f, e.fout() * 30f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
                color(e.color);
                stroke(e.fout() * 7f);
                circleRad = 9f + e.finpow() * 60f;
                Lines.circle(e.x, e.y, circleRad);

                rand.setSeed(e.id);
                for(int i = 0; i < 16; i++){
                    float angle = rand.random(360f);
                    float lenRand = rand.random(0.5f, 1f);
                    Tmp.v1.trns(angle, circleRad);

                    for(int s : Mathf.signs){
                        Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 15f, e.fout() * 40f * lenRand + 6f, angle + 90f + s * 90f);
                    }
                }
            }),
            casing5 = new Effect(45f, e -> {
                color(Pal.lightOrange, Pal.lightishGray, Pal.lightishGray, e.fin());
                alpha(e.fout(0.5f));
                float rot = Math.abs(e.rotation) + 90f;
                int i = -Mathf.sign(e.rotation);
                float len = (4f + e.finpow() * 9f) * i;
                float lr = rot + Mathf.randomSeedRange(e.id + i + 6, 20f * e.fin()) * i;

                rect(Core.atlas.find("casing"),
                        e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                        e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                        7f, 10f,
                        rot + e.fin() * 50f * i
                );
            }).layer(Layer.bullet),
            ColorRailTrail = new Effect(16f, e -> {
                color(e.color);
                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 16f * e.fout(), 21f, e.rotation + 90 + 90f * i);
                }
                Drawf.light(e.x, e.y, 60f * e.fout(), e.color, 0.5f);
            }),
            ColorRailHit = new Effect(22f, 200f, e -> {
                color(e.color);
                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 14f * e.fout(), 70f, e.rotation + 140f * i);
                    Drawf.tri(e.x, e.y, 8f * e.fout(), 90f, e.rotation + 170f * i);
                }
            }),
            ColorRailShoot = new Effect(24f, e -> {
                e.scaled(10f, b -> {
                    color(e.color, e.color, b.fin());
                    stroke(b.fout() * 3f + 0.2f);
                    Lines.circle(b.x, b.y, b.fin() * 50f);
                });
                color(e.color);
                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 20f * e.fout(), 145f, e.rotation + 90f * i);
                    Drawf.tri(e.x, e.y, 10f * e.fout(), 80f, e.rotation + 110f * i);
                    Drawf.tri(e.x, e.y, 5f * e.fout(), 60f, e.rotation + 160f * i);
                }
            }),
            polarisShoot = new Effect(48f, e -> {
                color(e.color);
                Drawf.tri(e.x, e.y, 10f * e.fout(), 175f - (20f * e.fin()), e.rotation);

                for(int i = 0; i < 2; i++){
                    Drawf.tri(e.x, e.y, 16f * e.fout(), 75f, e.rotation + (45f + (e.fin(Interp.pow3Out) * 30f)) * Mathf.signs[i]);
                }
                for(int i = 0; i < 2; i++){
                    Drawf.tri(e.x, e.y, 10f * e.fout(), 50f, e.rotation + (25f + (e.fin(Interp.pow3Out) * 30f)) * Mathf.signs[i]);
                }

                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.5f + 0.5f);

                rand.setSeed(e.id);
                for(int i = 0; i < 35; i++){
                    float ang = e.rotation + rand.range(30f), len = rand.random(90f * e.finpow());
                    e.scaled(e.lifetime * rand.random(0.5f, 1f), p -> {
                        v.trns(ang, len);
                        lineAngle(e.x + v.x, e.y + v.y, ang, p.fout() * 25f + 0.5f);
                    });
                }

                Fill.square(e.x, e.y, 5f * e.fout(Interp.pow3Out), e.rotation + 45f);
                color();
                Fill.square(e.x, e.y, 2f * e.fout(Interp.pow3Out), e.rotation + 45f);

                e.scaled(15f, s -> {
                    z(Layer.effect + 1f);
                    blend(Blending.additive);
                    Tmp.c1.set(e.color).a(s.fout(Interp.pow5In));

                    Fill.light(s.x, s.y, 4, 90f * s.fin(Interp.pow5Out), Color.clear, Tmp.c1);
                    blend();
                });
            }),

    chainLightningFade = new Effect(45f, 500f, e -> {
        if(!(e.data instanceof Position)) return;
        Position p = e.data();
        float tx = p.getX(), ty = p.getY(), dst = Mathf.dst(e.x, e.y, tx, ty);
        Tmp.v1.set(p).sub(e.x, e.y).nor();

        float normx = Tmp.v1.x, normy = Tmp.v1.y;
        float range = e.rotation;
        int links = Mathf.ceil(dst / range);
        float spacing = dst / links;

        Lines.stroke(2.5f * Mathf.curve(e.fout(), 0, 0.7f));
        Draw.color(Color.white, e.color, e.fin());

        Lines.beginLine();

        Fill.circle(e.x, e.y, Lines.getStroke() / 2);
        Lines.linePoint(e.x, e.y);

        rand.setSeed(e.id);

        float fin = Mathf.curve(e.fin(), 0, lightningAlign);
        float i;
        for(i = 0; i < links * fin; i++){
            float nx, ny;
            if(i == links - 1){
                nx = tx;
                ny = ty;
            }else{
                float len = (i + 1) * spacing;
                Tmp.v1.setToRandomDirection(rand).scl(range/2f);
                nx = e.x + normx * len + Tmp.v1.x;
                ny = e.y + normy * len + Tmp.v1.y;
            }

            Lines.linePoint(nx, ny);
        }
        Lines.endLine();
    }).followParent(false),

            hitSparkHuge = new Effect(70, e -> {
                color(e.color, Color.white, e.fout() * 0.3f);
                stroke(e.fout() * 1.6f);

                rand.setSeed(e.id);
                randLenVectors(e.id, 26, e.finpow() * 65f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * rand.random(6, 9) + 3f);
                });
            }),
            chainLightningFadeReversed = new Effect(45f, 500f, e -> {
        if(!(e.data instanceof Position))return;
        Position p = e.data();
        float tx = e.x, ty = e.y, dst = Mathf.dst(p.getX(), p.getY(), tx, ty);
        Tmp.v1.set(e.x, e.y).sub(p).nor();

        float normx = Tmp.v1.x, normy = Tmp.v1.y;
        float range = e.rotation;
        int links = Mathf.ceil(dst / range);
        float spacing = dst / links;

        Lines.stroke(2.5f * Mathf.curve(e.fout(), 0, 0.7f));
        Draw.color(Color.white, e.color, e.fin());

        Lines.beginLine();

        Fill.circle(p.getX(), p.getY(), Lines.getStroke() / 2);
        Lines.linePoint(p);

        rand.setSeed(e.id);

        float fin = Mathf.curve(e.fin(), 0, lightningAlign);
        float i;
        for(i = 0; i < links *fin; i++){
            float nx, ny;
            if(i == links - 1){
                nx = tx;
                ny = ty;
            }else{
                float len = (i + 1) * spacing;
                Tmp.v1.setToRandomDirection(rand).scl(range / 2f);
                nx = p.getX() + normx * len + Tmp.v1.x;
                ny = p.getY() + normy * len + Tmp.v1.y;
            }

            Lines.linePoint(nx, ny);
        }
        Lines.endLine();
    }).followParent(false),
            spawnWave = new Effect(60f, e -> {
                stroke(3 * e.fout(), e.color);
                circle(e.x, e.y, e.rotation * e.finpow());
            }),
            hugeSmokeGray = new Effect(40f, e -> {
                Draw.color(Color.gray, Color.darkGray, e.fin());
                Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x / 2.0F, e.y + y / 2.0F, e.fout() * 2f));
                e.scaled(25f, i -> Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * i.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, i.fout() * 4.0F)));
            }),

                    squareHitsmall = new Effect(13f, e -> {
                        color(e.color);
                        Angles.randLenVectors(e.id, 10, e.fin() * 20f, (x, y) -> Fill.square(e.x + x, e.y + y, 3f * e.fout()));

                        Tmp.c1.set(e.color).a(e.fout(Interp.pow3In));

                        z(Layer.effect + 1f);
                        blend(Blending.additive);
                        Fill.light(e.x, e.y, 4, 20f * e.fin(Interp.pow5Out), Color.clear, Tmp.c1);
                        blend();
                    }),
                    squareHitBig = new Effect(13f, e -> {
                        color(e.color);
                        Angles.randLenVectors(e.id, 10, e.fin() * 20f, (x, y) -> Fill.square(e.x + x, e.y + y, 5f * e.fout()));

                        Tmp.c1.set(e.color).a(e.fout(Interp.pow3In));

                        z(Layer.effect + 1f);
                        blend(Blending.additive);
                        Fill.light(e.x, e.y, 4, 25f * e.fin(Interp.pow5Out), Color.clear, Tmp.c1);
                        blend();
                    }),
                            squareHitBigger = new Effect(20f, e -> {
                                color(e.color);
                                z(Layer.effect + 1f);
                                blend(Blending.additive);
                                Fill.light(e.x, e.y, 4, 85f * e.fin(Interp.pow5Out), ExoPal.genesis, Tmp.c1);
                                blend();
                            }),
                            squareShoot = new Effect(60f, e -> {
                                int id = e.id;
                                for(int sign : Mathf.signs){
                                    float r = e.foutpow() * 2f;

                                    color(ExoPal.letoColorLight, ExoPal.letoColor, e.finpowdown());
                                    for(int rsign : Mathf.signs){
                                        randLenVectors(id++, 2, e.finpow() * 20f, e.rotation + sign * 90f, 30f, (x, y) ->
                                                Fill.rect(e.x + x, e.y + y, r, r, e.foutpow() * 135f * rsign)
                                        );
                                    }
                                }

                                float r = e.fout(Interp.pow5Out) * 2.4f;

                                color(ExoPal.letoColorLight, ExoPal.letoColor, e.fin(Interp.pow5In));
                                for(int rsign : Mathf.signs){
                                    randLenVectors(id++, 3, e.fin(Interp.pow5Out) * 32f, e.rotation, 45f, (x, y) ->
                                            Fill.rect(e.x + x, e.y + y, r, r, e.foutpow() * 180f * rsign)
                                    );
                                }
                            }),

            square45_6_45 = new Effect(25f, e-> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 8, 45f * e.finpow(), e.rotation, 50f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            }),

            starChargeWhite = new Effect(100f, 100f, e -> {
                    color(ExoPal.starWhite);
                    Fill.circle(e.x, e.y, e.fin() * 10);
                    color(Color.white);
                    Fill.circle(e.x, e.y, e.fin() * 6);
                }).followParent(true).rotWithParent(true),
            starChargeRed = new Effect(100f, 100f, e -> {
                color(ExoPal.cronusRed);
                Fill.circle(e.x, e.y, e.fin() * 10);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 6);
            }).followParent(true).rotWithParent(true),
            starChargeBlue = new Effect(100f, 100f, e -> {
                color(ExoPal.genesis);
                Fill.circle(e.x, e.y, e.fin() * 10);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 6);
            }).followParent(true).rotWithParent(true),
            starChargeGreen = new Effect(100f, 100f, e -> {
                color(ExoPal.radGreen);
                Fill.circle(e.x, e.y, e.fin() * 10);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 6);
            }).followParent(true).rotWithParent(true),
            starChargeDeepBlue = new Effect(100f, 100f, e -> {
                color(ExoPal.starBlue);
                Fill.circle(e.x, e.y, e.fin() * 10);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 6);
            }).followParent(true).rotWithParent(true),
            starChargeYellow = new Effect(100f, 100f, e -> {
                color(ExoPal.starYellow);
                Fill.circle(e.x, e.y, e.fin() * 10);
                color(Color.white);
                Fill.circle(e.x, e.y, e.fin() * 6);
            }).followParent(true).rotWithParent(true),
            singleSpark = new Effect(21f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.1f + 0.5f);

                randLenVectors(e.id, 2, 27f * e.fin(), e.rotation, 9f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 0.5f);
                });
            }),
                    singleSparkIn = new Effect(10f, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 2, 27f * e.fout(), e.rotation, 9f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 0.5f);
                        });
                    }),
                    singleSparkLongIn = new Effect(18f, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 2, 37f * e.fout(), e.rotation, 9f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
                        });
                    }),
                    singleSparkLong = new Effect(21f, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 2, 37f * e.fin(), e.rotation, 9f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
                        });
                    }),
            singleSparkYellow = new Effect(21f, e -> {
                        color(Color.white, ExoPal.empyrean, e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 2, 27f * e.fin(), e.rotation, 9f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 0.5f);
                        });
                    }),
            singularityDespawn = new Effect(80f, e -> {
                float rad = 24f;
                e.scaled(60f, s -> {
                    Lines.stroke(6f * s.fout(), ExoPal.genesis);
                    Lines.circle(e.x, e.y, 1.5f * rad * s.fin(pow3Out));
                });
                Lines.stroke(2f * e.fout(), Color.black);
                Lines.circle(e.x, e.y, rad * e.fin(pow3Out));
                color(ExoPal.genesis);
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 3f, 50f * e.fout(), i*90);
                }

                color();
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 1.3f, 30f * e.fout(), i*90);
                }
            }).layer(Layer.effect + 0.03f),
            supernovaShoot = new Effect(50f, 100f, e -> {
                e.scaled(7f, b -> {
                    color(ExoPal.genesis, ExoPal.genesisDark, b.fout());
                    Fill.circle(e.x, e.y, 20);
                });

                color(ExoPal.genesis);
                stroke(e.fout() * 3f);
                Lines.circle(e.x, e.y, 20);

                int points = 4;
                float offset = Mathf.randomSeed(e.id, 360f);
                for (int i = 0; i < points; i++) {
                    float angle = i * 360f / points + offset;
                    //for(int s : Mathf.zeroOne){
                    // Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle/* + s*180f*/);
                    // }
                    // }

                    Fill.circle(e.x, e.y, 12f * e.fout());
                    color();
                    Fill.circle(e.x, e.y, 6f * e.fout());
                    Drawf.light(e.x, e.y, 20 * 1.6f, ExoPal.genesis, e.fout());
                }
            }),
                    squareSpark = new Effect(16f, e -> {
                        color(Color.white, e.color, e.fin());
                        randLenVectors(e.id, 1, 100f * e.fin(), e.rotation, 0f, (x, y) -> {
                            rand.setSeed(e.id);
                            for (int i = 0; i < 6; i++) {
                                float rot = e.rotation + rand.range(22f);
                                v.trns(rot, rand.random(e.finpow() * 21f));
                                Fill.poly(e.x + v.x, e.y + v.y, 4, e.fout() * 4f + 0.2f, rand.random(360f));
                            }
                        });
                    }),
            supernovaSpark = new Effect(16f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.1f + 0.5f);

                randLenVectors(e.id, 1, 40f * e.fin(), e.rotation, 0f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 0.5f);
                });
            }),
    //test
            supernovaChargeStar = new Effect(30f, e -> {
                if(e.data instanceof Float data){
                    float r = data;

                    color(e.color);
                    alpha(e.fin() * 2f * r);
                    circle(e.x, e.y, 150f * pow2Out.apply(e.fout()) * Mathf.lerp(0.1f, 1f, r));
                }
            }),
            supernovaStarDecay = new Effect(56f, e -> randLenVectors(e.id, 1, 36f * e.finpow(), (x, y) -> {
                color(e.color);
                Fill.rect(e.x + x, e.y + y, 2.2f * e.fout(), 2.2f * e.fout(), 45f);
            })),
            atlasRocketClouds = new Effect(60, 500f, b -> {
                float intensity = 6.8f;
                float baseLifetime = 25f + intensity * 11f;
                b.lifetime = 50f + intensity * 65f;

                color(Pal.techBlue);
                alpha(0.7f);
                for(int i = 0; i < 4; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.4f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 2.35f);

                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 2.5f, Pal.techBlue, 0.5f);
                        });
                    });
                }

                b.scaled(baseLifetime, e -> {
                    Draw.color();
                    e.scaled(5 + intensity * 2f, i -> {
                        stroke((3.1f + intensity/5f) * i.fout());
                        Lines.circle(e.x, e.y, (3f + i.fin() * 14f) * intensity);
                        Drawf.light(e.x, e.y, i.fin() * 14f * 2f * intensity, Color.white, 0.9f * e.fout());
                    });

                    color(Pal.lighterOrange, Pal.reactorPurple, e.fin());
                    stroke((2f * e.fout()));

                    Draw.z(Layer.effect + 0.001f);
                    randLenVectors(e.id + 1, e.finpow() + 0.001f, (int)(8 * intensity), 28f * intensity, (x, y, in, out) -> {
                        lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + out * 4 * (4f + intensity));
                        Drawf.light(e.x + x, e.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
                    });
                });
            }),
           calamityExplostion = new Effect(170F, 1600f, e -> {
               float rad = 105f;
               rand.setSeed(e.id);

               Draw.color(Color.white, e.color, e.fin() + 0.6f);
               float circleRad = e.fin(Interp.circleOut) * rad * 4f;
               Lines.stroke(7 * e.fout());
               Lines.circle(e.x, e.y, circleRad);
               for(int i = 0; i < 24; i++){
                   Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
                   DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
               }
               Draw.blend(Blending.additive);
               Draw.z(Layer.effect + 0.1f);

               Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
               Draw.blend();
               Draw.z(Layer.effect);

               Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.7f);
           }).layer(Layer.effect + 0.001f),
            starExplodeTest = new Effect(100F, 1600f, e -> {
        float rad = 40f;
        rand.setSeed(e.id);

        Draw.color(Color.white, e.color, e.fin() + 0.6f);
        float circleRad = e.fin(Interp.circleOut) * rad * 4f;
        Lines.stroke(7 * e.fout());
        Lines.circle(e.x, e.y, circleRad);
        for(int i = 0; i < 24; i++){
            Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
            DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
        }
            Draw.blend(Blending.additive);
            Draw.z(Layer.effect + 0.1f);

            Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
            Draw.blend();
            Draw.z(Layer.effect);

        Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.7f);
    }).layer(Layer.effect + 0.001f),
            starExplodeBlue = new Effect(170F, 1600f, e -> {
                float rad = 85f;
                rand.setSeed(e.id);

                Draw.color(Color.white, e.color, e.fin() + 0.6f);
                float circleRad = e.fin(Interp.circleOut) * rad * 4f;
                Lines.stroke(7 * e.fout());
                Lines.circle(e.x, e.y, circleRad);
                for(int i = 0; i < 24; i++){
                    Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
                    DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
                }
                Draw.blend(Blending.additive);
                Draw.z(Layer.effect + 0.1f);

                Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
                Draw.blend();
                Draw.z(Layer.effect);

                Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.7f);
            }).layer(Layer.effect + 0.001f),
            starExplodeRed = new Effect(150F, 1600f, e -> {
                float rad = 45f;
                rand.setSeed(e.id);

                Draw.color(Color.white, e.color, e.fin() + 0.6f);
                float circleRad = e.fin(Interp.circleOut) * rad * 4f;
                Lines.stroke(4 * e.fout());
                Lines.circle(e.x, e.y, circleRad);
                for(int i = 0; i < 24; i++){
                    Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
                    DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
                }
                Draw.blend(Blending.additive);
                Draw.z(Layer.effect + 0.1f);

                Fill.light(e.x, e.y, circleVertices(circleRad), circleRad, Color.clear, Tmp.c1.set(Draw.getColor()).a(e.fout(Interp.pow10Out)));
                Draw.blend();
                Draw.z(Layer.effect);

                Drawf.light(e.x, e.y, rad * e.fout(Interp.circleOut) * 4f, e.color, 0.7f);
            }).layer(Layer.effect + 0.001f),
            blueStarExplosionCloud = new Effect(60, 500f, b -> {
                float intensity = 6.8f;
                float baseLifetime = 25f + intensity * 11f;
                b.lifetime = 50f + intensity * 65f;

                color(ExoPal.starBlue2);
                alpha(0.7f);
                for(int i = 0; i < 4; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.4f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 2.35f);
                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 2.5f, ExoPal.starBlue, 0.5f);
                        });
                    });
                }
        b.scaled(baseLifetime, e -> {
            Draw.color();
            e.scaled(5 + intensity * 2f, i -> {
                stroke((3.1f + intensity/5f) * i.fout());
                Lines.circle(e.x, e.y, (3f + i.fin() * 14f) * intensity);
                Drawf.light(e.x, e.y, i.fin() * 14f * 2f * intensity, Color.white, 0.9f * e.fout());
            });

            color(Pal.lighterOrange, Pal.reactorPurple, e.fin());
            stroke((2f * e.fout()));

            Draw.z(Layer.effect + 0.001f);
            randLenVectors(e.id + 1, e.finpow() + 0.001f, (int)(8 * intensity), 28f * intensity, (x, y, in, out) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + out * 4 * (4f + intensity));
                Drawf.light(e.x + x, e.y + y, (out * 4 * (3f + intensity)) * 3.5f, Draw.getColor(), 0.8f);
            });
        });
    }),
            pinkStarSwirl = new SwirlEffect(){{
                lifetime = 75.0F;
                length = 8;
                width = 3;
                minRot = 100.0F;
                maxRot = 380.0F;
                layer = 110.005F;
            }},
            redStarSwirl = new SwirlEffect(){{
                lifetime = 75.0F;
                length = 8;
                width = 3;
                minRot = 100.0F;
                maxRot = 380.0F;
                layer = 110.005F;
            }},
            yellowStarSwirl = new SwirlEffect(){{
                lifetime = 90.0F;
                length = 10;
                width = 3;
                minRot = 120.0F;
                maxRot = 480.0F;
                layer = 110.005F;
            }},
            whiteStarSwirl = new SwirlEffect(){{
                lifetime = 90.0F;
                length = 8;
                width = 3;
                minRot = 120.0F;
                maxRot = 480.0F;
                layer = 110.005F;
            }},
            strangeStarSwirl = new SwirlEffect(){{
                lifetime = 70.0F;
                length = 18;
                width = 1.7F;
                minRot = 140.0F;
                maxRot = 680.0F;
                layer = 110.005F;
            }},
            blueStarSwirl = new SwirlEffect(){{
                lifetime = 90.0F;
                length = 10;
                width = 3;
                minRot = 120.0F;
                maxRot = 480.0F;
                layer = 110.005F;
            }},
            darkBlueStarSwirl = new SwirlEffect(){{
                lifetime = 120.0F;
                length = 9;
                width = 5.5F;
                minRot = 140.0F;
                maxRot = 780.0F;
                layer = 110.005F;
            }},
            smolSwirl = new SwirlEffect(120.0F, 6, 2.5F, 140.0F, 780.0F).layer(110.005F),

            strangeStarSparks = new Effect(16f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.1f + 0.5f);

                randLenVectors(e.id, 3, 140f * e.fin(), e.rotation, 0f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5f + 0.5f);
                });
            }),
            starShockWave = new Effect(95f, e -> {
                float shock = 50f * (1f + e.fin(Interp.pow5Out) * 2f) + (e.fin() * 50f);
                color(e.color);
                Lines.stroke(6f * e.fout(Interp.pow5Out));
                Lines.circle(e.x, e.y, shock);
                float ang = rand.random(360f);
                Vec2 v = Tmp.v1.trns(ang, shock).add(e.x, e.y);
                Drawf.tri(v.x, v.y, 6f * e.fout(), (70f * e.fin()), ang + 180f);
            }),
            supernovaBlast = new Effect(30, e -> {
                color(e.color);
                stroke(e.fout() * 1.6f);

                randLenVectors(e.id, 18, e.finpow() * 27f, e.rotation, 60f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * 18 + 1f);
                });
            }),
            //test
             railgunSpark = new Effect(26f, e -> {
                 color(e.color);
                 float length = !(e.data instanceof Float) ? 70f : (Float)e.data;

                 randLenVectors(e.id, 7, length, e.rotation, 0f, (x, y) -> {
                     lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 9f);
                 });
             }),
             hitEmpColorSpark = new Effect(40, e -> {
                 color(e.color);
                 stroke(e.fout() * 1.6f);

                 randLenVectors(e.id, 18, e.finpow() * 27f, e.rotation, 360f, (x, y) -> {
                     float ang = Mathf.angle(x, y);
                     lineAngle(e.x + x, e.y + y, ang, e.fout() * 6 + 1f);
                 });
             }),
             blastExplosionColor = new Effect(22, e -> {
                 color(e.color);

                 e.scaled(6, i -> {
                     stroke(3f * i.fout());
                     Lines.circle(e.x, e.y, 3f + i.fin() * 15f);
                 });

                 color(Color.gray);

                 randLenVectors(e.id, 5, 2f + 23f * e.finpow(), (x, y) -> {
                     Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                 });

                 color(e.color);
                 stroke(e.fout());

                 randLenVectors(e.id + 1, 4, 1f + 23f * e.finpow(), (x, y) -> {
                     lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
                 });

                 Drawf.light(e.x, e.y, 45f, e.color, 0.8f * e.fout());
             }),
            blastcolor = new Effect(40f, 600,e -> {
                color(e.color);
                stroke(e.fout() * 3.7f);
                circle(e.x, e.y, e.fin(pow3Out) * 240 + 15);
                rand.setSeed(e.id);
                randLenVectors(e.id, 12, 8 + 60 * e.fin(Interp.pow5Out), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleIn) * (6f + rand.random(6f))));
                Drawf.light(e.x, e.y, e.fout() * 320f, e.color, 0.7f);
            }),

    colorBombSmall = new Effect(40f, 100f, e -> {
        color(e.color);
        stroke(e.fout());
        float circleRad = 4f + e.finpow() * 30f;
        Lines.circle(e.x, e.y, circleRad);

        color(e.color);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i*90);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 1.3f, 10f * e.fout(), i*90);
        }

        Drawf.light(e.x, e.y, circleRad * 1.6f, e.color, e.fout());
    });
}
