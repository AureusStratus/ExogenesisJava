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
            HaborymShootColor = new Effect(38f, e -> {
                color(e.color);

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
            instShootColor = new Effect(24f, e -> {
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
            colorSparkShootSmall = new Effect(12f, e -> {
                color(Color.white, e.color, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 3, 15f * e.finpow(), e.rotation, 30f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            }),

            shootHydrogenFlame = new Effect(10f, 80f, e -> {
                color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

                randLenVectors(e.id, 5, e.finpow() * 60f, e.rotation, 150f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 8.65f + e.fout() * 1.5f);
                });
                randLenVectors(e.id, 12, e.finpow() * 60f, e.rotation, 100f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 5.65f + e.fout(Interp.circleOut) * 1.5f);
                });
                color(Pal.lightFlame, Pal.darkFlame, e.fin());

                randLenVectors(e.id, 7, e.finpow() * 61f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout(Interp.fade) * 5 + 0.5f);
                });
            }).followParent(false),
            sparkLargeHydrogen = new Effect(10, e -> {
                color(Color.valueOf("fff69b"), Pal.darkFlame, e.fin());
                stroke(0.7f + e.fout());
                randLenVectors(e.id, 2, 5f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                           float ang = Mathf.angle(x, y);
                           lineAngle(e.x + x, e.y + y, ang, e.fout(Interp.circleOut) * 3 + 1f);
                });
                color(Color.valueOf("fff69b"), Pal.darkFlame, e.fin());
                stroke(e.fout() * 1.1f + 0.5f);

                randLenVectors(e.id, 1, 13f * e.fin(Interp.fade), e.rotation, 99f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
                });

            }),


            shootHeliumFlame = new Effect(8f, 80f, e -> {
                color(Color.valueOf("ecb5ff"), Color.valueOf("ffda71"), Color.valueOf("ff5a37"), e.fin());

                randLenVectors(e.id, 4, e.finpow() * 45f, e.rotation, 150f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 8.65f + e.fout() * 1.5f);
                });
                randLenVectors(e.id, 7, e.finpow() * 50f, e.rotation, 100f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 3.65f + e.fout(Interp.fastSlow) * 1.5f);
                });
                color(Color.valueOf("ecb5ff"), Color.valueOf("ffda71"), e.fin());

                randLenVectors(e.id, 5, e.finpow() * 61f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleOut) * 2 + 0.5f);
                });
            }).followParent(false),
            sparkLargeHelium = new Effect(8, e -> {
                color(Color.valueOf("fff69b"), Color.valueOf("ffda71"), e.fin());
                stroke(0.5f + e.fout());
                randLenVectors(e.id, 2, 8f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout(Interp.circleOut) * 3 + 1f);
                });
                color(Color.valueOf("fff69b"), Color.valueOf("ffda71"), e.fin());
                stroke(e.fout() * 1.1f + 0.5f);

                randLenVectors(e.id, 1, 13f * e.fin(Interp.fastSlow), e.rotation, 100f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
                });

            }),
                    shootOzoneFlame = new Effect(7f, 80f, e -> {
                        color(Color.white, Color.valueOf("5cb1ff"), Color.valueOf("b876ff"), e.fin());

                        randLenVectors(e.id, 4, e.finpow() * 45f, e.rotation, 150f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, 8.65f + e.fout() * 1.5f);
                        });
                        randLenVectors(e.id, 7, e.finpow() * 50f, e.rotation, 100f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, 3.65f + e.fout(Interp.fastSlow) * 1.5f);
                        });
                        color(Color.white, Color.valueOf("5cb1ff"), e.fin());

                        randLenVectors(e.id, 5, e.finpow() * 61f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleOut) * 2 + 0.5f);
                        });
                    }).followParent(false),
                    sparkLargeOzone = new Effect(7, e -> {
                        color(Color.valueOf("a1f7ff"), Color.valueOf("9374ff"), e.fin());
                        stroke(0.5f + e.fout());
                        randLenVectors(e.id, 2, 8f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                            float ang = Mathf.angle(x, y);
                            lineAngle(e.x + x, e.y + y, ang, e.fout(Interp.circleOut) * 3 + 1f);
                        });
                        color(Color.valueOf("a1f7ff"), Color.valueOf("9374ff"), e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 1, 13f * e.fin(Interp.fastSlow), e.rotation, 100f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
                        });

                    }),

                    shootCyanogenFlame = new Effect(6.5f, 80f, e -> {
                        color(Color.valueOf("5cffff"), Color.valueOf("ff5c87").a(0.4f), Color.valueOf("5353ff").a(0.2f), e.fin());

                        randLenVectors(e.id, 4, e.finpow() * 45f, e.rotation, 150f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, 8.65f + e.fout() * 1.5f);
                        });
                        randLenVectors(e.id, 7, e.finpow() * 50f, e.rotation, 100f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, 3.65f + e.fout(Interp.fastSlow) * 1.5f);
                        });
                        color(Color.valueOf("5cffff"), Color.valueOf("5353ff").a(0.3f), e.fin());

                        randLenVectors(e.id, 5, e.finpow() * 61f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleOut) * 2 + 0.5f);
                        });
                    }).followParent(false),
                    sparkLargeCyanogen = new Effect(6.5f, e -> {
                        color(Color.valueOf("5cffff"), Color.valueOf("5cffff"), e.fin());
                        stroke(0.5f + e.fout());
                        randLenVectors(e.id, 2, 8f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                            float ang = Mathf.angle(x, y);
                            lineAngle(e.x + x, e.y + y, ang, e.fout(Interp.circleOut) * 3 + 1f);
                        });
                        color(Color.valueOf("5cffff"), Color.valueOf("5cffff"), e.fin());
                        stroke(e.fout() * 1.1f + 0.5f);

                        randLenVectors(e.id, 1, 13f * e.fin(Interp.fastSlow), e.rotation, 100f, (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 11f + 0.5f);
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
