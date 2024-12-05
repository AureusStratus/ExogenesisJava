package exogenesis.content;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.struct.ObjectSet;
import arc.util.Time;
import arc.util.Tmp;
import blackhole.entities.abilities.BlackHoleAbility;
import blackhole.entities.bullet.BlackHoleBulletType;
import exogenesis.entities.part.EffectSpawnPart;
import exogenesis.graphics.ExoPal;
import exogenesis.type.DamageType;
import exogenesis.type.abilities.TurretShield;
import exogenesis.type.bullet.*;
import exogenesis.type.bullet.vanilla.*;
import exogenesis.type.unit.ExoUnitType;
import exogenesis.type.weapons.MissileOwnerWeapon;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.DefenderAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static exogenesis.type.DamageType.*;
import static exogenesis.type.DamageType.explosive;
import static mindustry.Vars.tilePayload;

public class ExoVanillaUnitTypes {
    public static UnitType
    ursa, ullr,
    empire, heimdall,
    avicularia, vidar,
    twilight, odin,
    notodoris, thor,
    pantagruel, helios,
    //erekir
    //erekir supportMech
    calm, serene, tranquil, sanctuary, ataraxia, leto,
    //support air
    ivy, yew, lantana, kalmia, hemlock, rhea,
    // Anti-unit Hover
    squall, gust, storm, thunderstorm, hurricane,  hyperion,
    // Swarm spiders
    cronus,

    prometheus, atlas, nemesis;

    public static void load() {
        prometheus = new ErekirUnitType("prometheus") {{
            constructor = TankUnit::create;
            speed = 0.34f;
            hitSize = 110f;
            health = 80000f;
            outlineRadius = 6;
            fogRadius = 50;
            crushDamage = 1.5f;
            faceTarget = false;
            singleTarget = true;
            armor = 75;
            shadowElevation = 0.1f;
            omniMovement = false;
            treadPullOffset = 1;
            rotateSpeed = 0.45f;
            treadRects = new Rect[]{new Rect(65, 70, 131, 180), new Rect(46, -248, 85, 63), new Rect(166, -183.5f, 32, 39)};
            weapons.add(new Weapon("exogenesis-prometheus-weapon") {{
                shootSound = Sounds.largeCannon;
                shadow = 50f;
                heatColor = Color.valueOf("f9350f");
                top = rotate = true;
                mirror = false;
                shake = 14f;
                shootY = 0f;
                x = 0;
                y = -11;
                reload = 100f;
                recoils = 4;
                recoil = 3f;
                layerOffset = 1;
                rotateSpeed = 0.6f;
                cooldownTime = 350f;
                shoot = new ShootBarrel() {{
                    barrels = new float[]{
                            19.75f, 61.75f, 0f,
                            6.75f, 61.75f, 0f,
                            -6.75f, 61.75f, 0f,
                            -19.75f, 61.75f, 0f,
                    };
                }};
                parts.addAll(
                        new RegionPart("-tungsten-sink-1") {{
                            mirror = false;
                            recoilIndex = 3;
                            progress = PartProgress.recoil;
                            moveY = -6f;
                        }},
                        new RegionPart("-tungsten-sink-2") {{
                            mirror = false;
                            recoilIndex = 2;
                            progress = PartProgress.recoil;
                            moveY = -6f;
                        }},
                        new RegionPart("-tungsten-sink-3") {{
                            mirror = false;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -6f;
                        }},
                        new RegionPart("-tungsten-sink-4") {{
                            mirror = false;
                            recoilIndex = 0;
                            progress = PartProgress.recoil;
                            moveY = -6f;
                        }},
                        new RegionPart("-barrel-1") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 3;
                            progress = PartProgress.recoil;
                            moveY = -8f;
                        }},
                        new RegionPart("-barrel-2") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 2;
                            progress = PartProgress.recoil;
                            moveY = -8f;
                        }},
                        new RegionPart("-barrel-3") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -8f;
                        }},
                        new RegionPart("-barrel-4") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 0;
                            progress = PartProgress.recoil;
                            moveY = -8f;
                        }}
                );
                bullet = new BasicBulletType(13f, 820) {{
                    sprite = "missile-large";
                    width = 19f;
                    height = 39f;
                    splashDamage = 157;
                    splashDamageRadius = 50;
                    pierceArmor = true;
                    hitSound = Sounds.titanExplosion;
                    hitShake = 8;
                    lifetime = 38f;
                    hitColor = trailColor = backColor = ExoPal.prometheusColor;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    fragOnHit = false;
                    fragRandomSpread = 55f;
                    fragBullets = 5;
                    fragVelocityMin = 0.75f;

                    fragBullet = new BasicBulletType(8f, 100){{
                        sprite = "missile";
                        width = 9f;
                        height = 16f;
                        lifetime = 33f;
                        hitSize = 11f;
                        backColor = hitColor = trailColor = ExoPal.prometheusColor;
                        frontColor = Color.white;
                        trailWidth = 2.5f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                    }};
                    smokeEffect = new Effect(20, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.prometheusColor, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.8f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.6f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(6f * e.fout());
                    });
                    shootEffect = new MultiEffect(ExoFx.shootGiant, Fx.blastExplosion, Fx.shootSmokeTitan);
                    hitEffect = new MultiEffect(ExoFx.PrometheusSmoke, ExoFx.PrometheusExplosionSplash, Fx.flakExplosionBig);
                    trailEffect = new Effect(20, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.prometheusColor, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.8f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Lines.stroke(6f * e.fout());
                    });
                    trailRotation = true;
                    trailInterval = 8;
                    trailLength = 10;
                    trailWidth = 5.5f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-prometheus-machine-gun") {{
                reload = 60f;
                mirror = rotate = true;
                cooldownTime = 80;
                rotateSpeed = 1.5f;
                x = 30;
                y = 24;
                shoot = new ShootPattern() {{
                    shots = 4;
                }};
                shootY = inaccuracy = 8;
                velocityRnd = 0.2f;
                shootSound = Sounds.artillery;
                ejectEffect = Fx.casing2;
                heatColor = Color.valueOf("f9350f");
                recoil = 4;
                shake = 1.8f;
                parts.addAll(
                        new RegionPart("-glow") {{
                            color = Color.red;
                            colorTo = Color.red;
                            blending = Blending.additive;
                            outline = mirror = false;
                        }}
                );
                bullet = new ArtilleryBulletType() {{
                    damage = 115f;
                    hitEffect = Fx.massiveExplosion;
                    speed = 4.5f;
                    lifetime = 90;
                    shootEffect = new MultiEffect(Fx.shootBig2, Fx.shootBigSmoke);
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    width = height = 19;
                    splashDamage = 40f;
                    splashDamageRadius = 60;
                    trailLength = 10;
                    trailWidth = 3.5f;
                    backColor = trailColor = lightColor = Color.valueOf("feb380");
                }};
            }});
            weapons.add(new Weapon("exogenesis-prometheus-machine-gun") {{
                reload = 60f;
                mirror = rotate = true;
                cooldownTime = 80;
                rotateSpeed = 1.5f;
                x = 38;
                y = -15;
                shoot = new ShootPattern() {{
                    shots = 4;
                }};
                shootY = inaccuracy = 8;
                velocityRnd = 0.2f;
                shootSound = Sounds.artillery;
                ejectEffect = Fx.casing2;
                heatColor = Color.valueOf("f9350f");
                recoil = 4;
                shake = 1.8f;
                parts.addAll(
                        new RegionPart("-glow") {{
                            color = Color.red;
                            colorTo = Color.red;
                            blending = Blending.additive;
                            outline = mirror = false;
                        }}
                );
                bullet = new ArtilleryBulletType() {{
                    damage = 115f;
                    hitEffect = Fx.massiveExplosion;
                    speed = 4.5f;
                    lifetime = 90;
                    shootEffect = new MultiEffect(Fx.shootBig2, Fx.shootBigSmoke);
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    width = height = 19;
                    splashDamage = 40f;
                    splashDamageRadius = 60;
                    trailLength = 10;
                    trailWidth = 3.5f;
                    backColor = trailColor = lightColor = Color.valueOf("feb380");
                }};
            }});
        }};
        atlas = new ErekirUnitType("atlas") {{
            constructor = LegsUnit::create;
            fogRadius = 50;
            speed = 0.40f;
            hitSize = 56f;
            health = 78000f;
            outlineRadius = 6;

            faceTarget = singleTarget = true;
            armor = 30;
            shadowElevation = 0.3f;
            allowLegStep = hovering = true;
            rotateSpeed = 0.5f;

            legStraightLength = 0.9f;
            legMaxLength = 1.2f;
            legStraightness = 0.6f;
            baseLegStraightness = 0.65f;
            legSpeed = 0.3f;
            legMoveSpace = 0.5f;
            legPhysicsLayer = false;
            legLength = 60;
            legCount = 6;
            legExtension = -4;
            legContinuousMove = lockLegBase = true;
            stepShake = 3;
            rippleScale = 6.8f;
            legPairOffset = 4;
            legBaseOffset = 46;
            legSplashDamage = 156;
            legSplashRange = 50;
            groundLayer = 77;

            for(int j = 0; j < 5; j++){
                int i = j;
                parts.add(new RegionPart("-spine"){{
                    layerOffset = 0.01f;
                    heatLayerOffset = 0.005f;
                    x = 17f;
                    y  = 27 + i * 6.5f;;
                    moveX = 18f + i * 3.5f;
                    moveY = 12f + -6f * i;
                    moveRot = 30f - i * 5f;
                    mirror = true;
                    progress = PartProgress.warmup.delay(i * 0.2f);
                    heatProgress = p -> Mathf.absin(Time.time + i * 14f, 7f, 1f);

                    heatColor = Pal.techBlue;
                }});
            }
            /*
            weapons.add(new Weapon("atlas-boom") {{
                reload = 800f;
                mirror = false;
                x = 0;
                y = 4.5f;
                shootY = 0;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 150;
                shoot.firstShotDelay = 150;
                shootSound = ExoSounds.bigLaserShoot;
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                recoilTime = 500;
                recoil = 0;
                shake = 1f;
                parts.addAll(
                        new HoverPart() {{
                            color = Pal.techBlue;
                            circles = 5;
                            stroke = 3f;
                            phase = 170;
                            radius = 28;
                            layer = Layer.effect;
                            y = 2.5f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            circle = hollow = true;
                            stroke = 0f;
                            strokeTo = 1.9f;
                            radius = radiusTo = 18;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            circle = hollow = true;
                            sides = 4;
                            rotateSpeed = 0.7f;
                            stroke = 0f;
                            strokeTo = 1.3f;
                            radius = radiusTo = 17;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            circle = hollow = true;
                            sides = 4;
                            rotateSpeed = -0.7f;
                            stroke = 0f;
                            strokeTo = 1.3f;
                            radius = radiusTo = 17;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            circle = hollow = true;
                            stroke = 0f;
                            strokeTo = 0.7f;
                            radius = radiusTo = 9;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        new HaloPart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            tri = true;
                            haloRotateSpeed = -0.7f;
                            haloRadius = haloRadiusTo = 17.5f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 8;
                            triLength = 0;
                            radius = 4.6f;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        new HaloPart() {{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Pal.techBlue;
                            tri = true;
                            haloRotateSpeed = 0.7f;
                            haloRadius = haloRadiusTo = 17.5f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 8;
                            triLength = 0;
                            radius = 4.6f;
                            layer = Layer.effect;
                            y = -42;
                        }},
                        // weapon parts
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Color.white;
                            circle = true;
                            radius = 10f;
                            radiusTo = 1.5f;
                            layer = 114;
                            y = 2.5f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Color.valueOf("aec6ff");
                            circle = true;
                            radius = 20;
                            radiusTo = 3;
                            layer = Layer.effect;
                            y = 2.5f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Color.valueOf("8ca9e855");
                            circle = true;
                            radius = 26;
                            radiusTo = 4;
                            layer = Layer.effect;
                            y = 2.5f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Color.valueOf("597cff45");
                            circle = true;
                            radius = 29f;
                            radiusTo = 4.2f;
                            layer = Layer.effect;
                            y = 2.5f;
                        }}
                );
                bullet = new EmpBulletType() {{
                    parts.addAll(
                            new ShapePart() {{
                                color = Color.white;
                                circle = true;
                                radius = radiusTo = 15;
                                layer = 114;
                            }},
                            new ShapePart() {{
                                color = Color.valueOf("aec6ff");
                                circle = true;
                                radius = radiusTo = 25;
                                layer = Layer.effect;
                            }},
                            new ShapePart() {{
                                color = Color.valueOf("8ca9e855");
                                circle = true;
                                radius = radiusTo = 31;
                                layer = Layer.effect;
                            }},
                            new ShapePart() {{
                                color = Color.valueOf("597cff45");
                                circle = true;
                                radius = radiusTo = 34;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                haloRotateSpeed = -2.4f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 2;
                                triLengthTo = triLength = 9;
                                radius = 35f;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                shapeRotation = 180;
                                haloRotateSpeed = -2.4f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 2;
                                triLengthTo = triLength = 6;
                                radius = 12f;
                                layer = Layer.effect;
                            }},
                            //1
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                haloRotateSpeed = -2.7f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 9;
                                radius = 35f;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                shapeRotation = 180;
                                haloRotateSpeed = -2.7f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 6;
                                radius = 12f;
                                layer = Layer.effect;
                            }},
                            //2
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                haloRotateSpeed = -1.7f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 9;
                                radius = 35f;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                shapeRotation = 180;
                                haloRotateSpeed = -1.7f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 6;
                                radius = 12f;
                                layer = Layer.effect;
                            }},
                            //3
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                haloRotateSpeed = 2f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 9;
                                radius = 35f;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                shapeRotation = 180;
                                haloRotateSpeed = 2f;
                                haloRadius = haloRadiusTo = 36f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 6;
                                radius = 12f;
                                layer = Layer.effect;
                            }},
                            //4
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                haloRotateSpeed = 1.2f;
                                haloRadius = haloRadiusTo = 32f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 9;
                                radius = 28f;
                                layer = Layer.effect;
                            }},
                            new HaloPart() {{
                                color = Pal.techBlue;
                                tri = true;
                                shapeRotation = 180;
                                haloRotateSpeed = 1.2f;
                                haloRadius = haloRadiusTo = 32f;
                                stroke = strokeTo = 2f;
                                shapes = 1;
                                triLengthTo = triLength = 6;
                                radius = 12f;
                                layer = Layer.effect;
                            }}
                            //5
                    );
                    width = height = 0;
                    backColor = hitColor = trailColor = Pal.techBlue;
                    lifetime = 160f;
                    scaleLife = scaledSplashDamage = true;
                    hitSound = Sounds.largeExplosion;
                    chargeEffect = new MultiEffect(
                            new WaveEffect() {{
                                colorFrom = Pal.techBlue;
                                colorTo = Color.valueOf("aec6ff");
                                sizeFrom = 180;
                                sizeTo = 0f;
                                lifetime = 150f;
                                strokeTo = 7;
                                strokeFrom = 0f;
                            }},
                            new WaveEffect() {{
                                colorFrom = Pal.techBlue;
                                colorTo = Color.valueOf("aec6ff");
                                interp = Interp.pow5Out;
                                sizeFrom = 180;
                                sizeTo = 0f;
                                lifetime = 150f;
                                strokeTo = 7;
                                strokeFrom = 0f;
                            }},
                            new WaveEffect() {{
                                colorFrom = Pal.techBlue;
                                colorTo = Color.valueOf("aec6ff");
                                sizeFrom = 150;
                                sizeTo = 0f;
                                lifetime = 130f;
                                strokeTo = 6;
                                strokeFrom = 0f;
                            }},
                            new WaveEffect() {{
                                colorFrom = Pal.techBlue;
                                colorTo = Color.valueOf("aec6ff");
                                sizeFrom = 120;
                                sizeTo = 0f;
                                lifetime = 120f;
                                strokeTo = 4;
                                strokeFrom = 0f;
                            }});
                    hitEffect = new MultiEffect(
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 30;
                                length = 150;
                                baseLength = 30;
                                lifetime = 240;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 27;
                                sizeTo = 1;
                                lightColor = colorFrom = colorTo = Pal.techBlue;
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 30;
                                length = 180;
                                baseLength = 30;
                                lifetime = 60;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 15;
                                sizeTo = 1;
                                lightColor = colorFrom = colorTo = Pal.techBlue;
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 30;
                                length = 120;
                                baseLength = 30;
                                lifetime = 180;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 20;
                                sizeTo = 1;
                                lightColor = colorFrom = colorTo = Pal.techBlue;
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 20;
                                length = 180;
                                baseLength = 30;
                                lifetime = 220;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 35;
                                sizeTo = 1;
                                lightColor = colorFrom = colorTo = Pal.techBlue;
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 35;
                                layer = 99;
                                length = 160;
                                baseLength = 30;
                                lifetime = 160;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 21;
                                sizeTo = 10;
                                lightColor = colorFrom = colorTo = Color.valueOf("7d7d7d");
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 35;
                                layer = 99;
                                length = 160;
                                baseLength = 30;
                                lifetime = 180;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 25;
                                sizeTo = 18;
                                lightColor = colorFrom = colorTo = Color.valueOf("7d7d7d");
                            }},
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                line = true;
                                particles = 35;
                                length = 275;
                                offset = 40;
                                strokeFrom = 5;
                                strokeTo = 0;
                                lifetime = 160;
                                lenFrom = 20;
                                lenTo = 10;
                                lightColor = colorFrom = Pal.techBlue;
                                colorTo = Color.valueOf("aec6ff");
                            }},
                            new WaveEffect() {{
                                colorTo = Pal.techBlue;
                                colorFrom = Color.valueOf("aec6ff");
                                interp = Interp.circleOut;
                                sizeFrom = 20;
                                sizeTo = 150f;
                                lifetime = 55;
                                strokeTo = 0;
                                strokeFrom = 10f;
                            }},
                            new WaveEffect() {{
                                colorTo = Pal.techBlue;
                                colorFrom = Color.valueOf("aec6ff");
                                interp = Interp.circleOut;
                                sizeFrom = 10;
                                sizeTo = 100f;
                                lifetime = 55;
                                strokeTo = 0;
                                strokeFrom = 7f;
                            }});
                    speed = 4f;
                    damage = 260f;
                    recoil = 0.6f;
                    splashDamage = 1275;
                    splashDamageRadius = 100;
                    buildingDamageMultiplier = 1.45f;
                    collidesTiles = true;
                    collidesAir = collidesGround = false;
                    radius = 150f;
                    lightRadius = 60;
                    timeIncrease = 0f;
                    powerDamageScl = 1;
                    powerSclDecrease = 0.3f;
                    unitDamageScl = 0.2f;
                    status = StatusEffects.freezing;
                    statusDuration = 100;
                    shootEffect = Fx.lightningShoot;
                    homingPower = 0.0678f;
                    homingRange = 40;
                    trailSinScl = 2.4f;
                    trailSinMag = 1.2f;
                    trailLength = 26;
                    trailWidth = 9f;
                    trailChance = 0.3f;
                    trailParam = 3.5f;
                    trailEffect = new ParticleEffect() {{
                        particles = 5;
                        length = baseLength = 13.5f;
                        lifetime = 30f;
                        cone = 360;
                        colorFrom = trailColor;
                        colorTo = Color.valueOf("8ca9e8AA");
                        sizeFrom = 10f;
                        sizeTo = 0f;
                    }};
                    intervalBullets = 1;
                    intervalRandomSpread = 60;
                    bulletInterval = 2;
                    intervalBullet = new EmpBulletType() {{
                        width = height = 15f;
                        hitPowerEffect = hitEffect = Fx.none;
                        lifetime = 35f;
                        radius = 140;
                        hitColor = backColor = trailColor = Pal.techBlue;
                        frontColor = Color.white;
                        unitDamageScl = 0.1f;
                        trailWidth = 2.1f;
                        trailLength = 5;
                        despawnHit = true;
                        collides = false;
                        instantDisappear = true;
                        lightningColor = Pal.techBlue;
                        lightning = 3;
                        lightningDamage = 32;
                        lightningLength = 4;
                        lightningCone = 360;
                        lightningLengthRand = 7;
                        buildingDamageMultiplier = 0.3f;
                        fragBullets = 1;
                        fragLifeMin = 0.3f;
                        fragBullet = new BasicBulletType(6f, 130) {{
                            parts.addAll(
                                    new ShapePart() {{
                                        color = Color.white;
                                        circle = true;
                                        radius = radiusTo = 1.5f;
                                        layer = 114;
                                    }},
                                    new ShapePart() {{
                                        color = Color.valueOf("aec6ff");
                                        circle = true;
                                        radius = radiusTo = 3.5f;
                                        layer = Layer.effect;
                                    }},
                                    new ShapePart() {{
                                        color = Color.valueOf("8ca9e855");
                                        circle = true;
                                        radius = radiusTo = 5.5f;
                                        layer = Layer.effect;
                                    }},
                                    new ShapePart() {{
                                        color = Color.valueOf("597cff45");
                                        circle = true;
                                        radius = radiusTo = 6;
                                        layer = Layer.effect;
                                    }}
                            );
                            width = height = 0f;
                            lifetime = 60f;
                            backColor = hitColor = trailColor = Pal.techBlue;
                            frontColor = Color.white;
                            hitSound = Sounds.explosion;
                            drag = 0.019f;
                            pierceBuilding = true;
                            pierceCap = 2;
                            pierce = despawnHit = true;
                            weaveMag = 2;
                            weaveScale = 8;
                            homingPower = 0.0002f;
                            homingRange = 100;
                            trailSinScl = 2.4f;
                            trailSinMag = 0.8f;
                            trailParam = 1.5f;
                            trailWidth = 2.5f;
                            trailLength = 9;
                            hitEffect = ExoFx.colorBombSmaller;
                        }};
                    }};
                    fragLifeMin = 0.2f;
                    fragBullets = 14;
                    fragBullet = new ArtilleryBulletType(5.4f, 32) {{
                        buildingDamageMultiplier = 1.3f;
                        parts.addAll(
                                new FlarePart() {{
                                    progress = PartProgress.life;
                                    color1 = Pal.techBlue;
                                    sides = 2;
                                    stroke = 3.5f;
                                    radius = radiusTo = 15;
                                    layer = Layer.effect;
                                }}
                        );
                        hitEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheSlash);
                        despawnHit = true;
                        knockback = 0.8f;
                        lifetime = 30f;
                        width = height = 18f;
                        splashDamageRadius = 50f;
                        splashDamage = 100f;
                        backColor = trailColor = hitColor = Pal.techBlue;
                        frontColor = Color.white;
                        despawnShake = 7f;
                        lightRadius = 30f;
                        lightColor = Pal.techBlue;
                        lightOpacity = 0.5f;
                        trailLength = 20;
                        trailWidth = 3.5f;
                        trailEffect = Fx.none;
                    }};
                }};
            }});
             */
            weapons.add(new Weapon("exogenesis-atlas-energy-mount") {{
                reload = 230f;
                mirror = rotate = true;
                shootCone = 45;
                cooldownTime = 80;
                layerOffset = 2;
                rotateSpeed = 2;
                x = 44;
                y = -5;
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                alwaysShootWhenMoving = true;
                shootY = 7;
                shootSound = Sounds.missileLarge;
                recoil = 3.5f;
                shake = 1.8f;

                parts.addAll(
                        new RegionPart("-front") {{
                            mirror = under = true;
                            layerOffset = -1;
                            outlineLayerOffset = 1;
                            x = 8;
                            y = 7;
                            moveX = 4;
                            progress = PartProgress.warmup;
                            moves.add(new PartMove(PartProgress.reload.shorten(0.5f), 0f, -5f, -33f));
                        }},
                        new RegionPart("-bottom") {{
                            mirror = under = true;
                            layerOffset = -2;
                            y = 3;
                            moveY = -3;
                            progress = PartProgress.warmup;
                            moves.add(new PartMove(PartProgress.reload.shorten(0.5f), 0f, -4f, 0f));
                            moveRot = -13f;
                        }}
                );
                bullet = new BulletType(){{
                    shootEffect = new MultiEffect(Fx.shootBigColor,
                            new Effect(9, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(0.7f + e.fout());
                        Lines.square(e.x, e.y, e.fin() * 5f, e.rotation + 45f);

                        Drawf.light(e.x, e.y, 23f, e.color, e.fout() * 0.7f);
                    }), new WaveEffect(){{
                        colorFrom = colorTo = Pal.techBlue;
                        sizeTo = 15f;
                        lifetime = 12f;
                        strokeFrom = 3f;
                    }});

                    smokeEffect = Fx.shootBigSmoke2;
                    shake = 2f;
                    speed = 0f;
                    keepVelocity = false;
                    inaccuracy = 2f;

                    spawnUnit = new MissileUnitType("big-thorium-missile"){{
                        speed = 5.6f;
                        maxRange = 15f;
                        hitSize = 20;
                        lifetime = 250f;
                        parts.add(new FlarePart(){{
                            progress = PartProgress.life.slope().curve(Interp.pow2In);
                            radius = 0f;
                            radiusTo = 65f;
                            stroke = 4f;
                            rotation = 45f;
                            y = -19;
                            followRotation = true;
                        }});
                        engineColor = trailColor = Pal.techBlue;
                        outlineColor = Color.valueOf("36363c");
                        engineLayer = Layer.effect;
                        engineSize = 5.3f;
                        engineOffset = 19f;
                        rotateSpeed = 0.9f;
                        missileAccelTime = 45;
                        trailLength = 22;
                        homingDelay = 5;
                        lowAltitude = true;
                        deathSound = Sounds.largeExplosion;
                        loopSound = Sounds.missileTrail;
                        loopSoundVolume = 0.6f;
                        fogRadius = 0f;
                        health = 800;
                        abilities.add(new MoveEffectAbility(){{
                            effect = Fx.none;
                            rotation = 180f;
                            rotateEffect = true;
                            y = -17f;
                            color = Color.grays(0.6f).lerp(Pal.techBlue, 0.5f).a(0.4f);
                            interval = 3f;
                        }});
                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            reload = 1;
                            mirror = false;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 4f;
                            bullet = new ExplosionBulletType(500f, 70f){{
                                shootEffect = new MultiEffect(Fx.massiveExplosion, ExoFx.atlasRocketClouds, ExoFx.starExplodeTest);
                                killShooter = true;
                                status = StatusEffects.blasted;
                                hitColor = Pal.techBlue;
                                statusDuration = 100;
                                hitSoundVolume = 5;
                                collidesGround = collidesAir = collidesTiles = true;
                                buildingDamageMultiplier = 0.8f;
                            }};
                        }});
                    }};
                }};
                /*
                bullet = new BasicBulletType() {{
                    shootEffect = Fx.shootBig;
                    hitColor = Pal.techBlue;
                    smokeEffect = Fx.none;
                    spawnUnit = new MissileUnitType("atlas-missile") {{
                        speed = 6;
                        maxRange = 15f;
                        lifetime = 105f;
                        targetable = false;
                        engineColor = trailColor = Pal.techBlue;
                        engineLayer = Layer.effect;
                        engineSize = 3f;
                        engineOffset = 6f;
                        rotateSpeed = 3.9f;
                        trailLength = 6;
                        homingDelay = 25;
                        outlines = drawCell = drawBody = false;
                        lowAltitude = true;
                        deathSound = Sounds.explosion;
                        fogRadius = 0f;
                        health = 100;
                        parts.addAll(
                                new FlarePart() {{
                                    progress = PartProgress.life.slope().curve(Interp.pow3Out);
                                    color1 = Pal.techBlue;
                                    stroke = 5.5f;
                                    radius = 0;
                                    radiusTo = 45;
                                    layer = Layer.effect;
                                }}
                        );
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 2f;
                            bullet = new ExplosionBulletType(140f, 58f) {{
                                hitColor = ExoPal.empyrean;
                                shootEffect = new MultiEffect(ExoFx.colorBombSmaller, new ExplosionEffect() {{
                                    waveColor = sparkColor = Pal.techBlue;
                                    smokes = 12;
                                    smokeSize = 4.7f;
                                    smokeSizeBase = 1.6f;
                                    waveLife = 10;
                                    waveStroke = 3.1f;
                                    waveRad = 65f;
                                    waveRadBase = 2;
                                    sparkLen = 5;
                                    sparks = 12;
                                }});
                                collidesGround = collidesAir = collidesTiles = true;
                                buildingDamageMultiplier = 0.6f;
                            }};
                        }});
                    }};
                }};
                 */
            }});

            weapons.add(new Weapon("exogenesis-atlas-flak-turret") {{
                x = 17.75f;
                y = 34.25f;
                rotateSpeed = 1.5f;
                reload = 130f;
                alternate = false;
                shootSound = Sounds.laser;
                rotate = true;
                targetGround = false;
                recoil = 3f;
                shootY = 2.75f;
                parts.addAll(
                        new RegionPart("-arm") {{
                            mirror = under = true;
                            layerOffset = -1;
                            outlineLayerOffset = 1;
                            moveX = 3;
                            progress = PartProgress.recoil;
                        }}
                );
                bullet = new FlakBulletType(4f, 50) {{
                    width = height = 28f;
                    sprite = "large-bomb";
                    collidesGround = false;
                    shrinkX = shrinkY = 0;
                    spin = 4;
                    explodeDelay = 1;
                    explodeRange = 40;
                    homingDelay = 3f;
                    homingPower = 0.2f;
                    homingRange = 160f;
                    drag = 0.005f;
                    fragRandomSpread = 360f;
                    fragBullets = 7;
                    fragVelocityMin = 1f;

                    fragBullet = new BasicBulletType(8, 53){{
                        sprite = "missile";
                        width = 7f;
                        pierce = true;
                        pierceCap = 2;
                        homingRange = 45;
                        homingPower = 0.3f;
                        homingDelay = 0.6f;
                        height = 13f;
                        lifetime = 13f;
                        backColor = hitColor = trailColor = Pal.techBlue;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }};
                    lifetime = 95f;
                    trailWidth = 6f;
                    trailLength = 5;
                    splashDamageRadius = 50;
                    splashDamage = 25;
                    hitEffect = despawnEffect = new MultiEffect(Fx.circleColorSpark, ExoFx.colorBombSmaller);
                    shootEffect = Fx.shootBig;
                    trailColor = hitColor = backColor = healColor = Pal.techBlue;
                }};
            }});
            weapons.add(new Weapon("exogenesis-atlas-flak-turret") {{
                x = 17.75f;
                y = -17.0f;
                rotateSpeed = 1.5f;
                reload = 130f;
                alternate = false;
                shootSound = Sounds.laser;
                rotate = true;
                targetGround = false;
                recoil = 3f;
                shootY = 2.75f;
                parts.addAll(
                        new RegionPart("-arm") {{
                            mirror = under = true;
                            layerOffset = -1;
                            outlineLayerOffset = 1;
                            moveX = 3;
                            progress = PartProgress.recoil;
                        }}
                );
                bullet = new FlakBulletType(4f, 50) {{
                    width = height = 28f;
                    sprite = "large-bomb";
                    collidesGround = false;
                    shrinkX = shrinkY = 0;
                    spin = 4;
                    explodeDelay = 1;
                    explodeRange = 40;
                    homingDelay = 3f;
                    homingPower = 0.2f;
                    homingRange = 160f;
                    drag = 0.005f;
                    fragRandomSpread = 360f;
                    fragBullets = 7;
                    fragVelocityMin = 1f;

                    fragBullet = new BasicBulletType(8, 53){{
                        sprite = "missile";
                        width = 7f;
                        pierce = true;
                        pierceCap = 2;
                        homingRange = 45;
                        homingPower = 0.3f;
                        homingDelay = 0.6f;
                        height = 13f;
                        lifetime = 13f;
                        backColor = hitColor = trailColor = Pal.techBlue;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }};
                    lifetime = 95f;
                    trailWidth = 6f;
                    trailLength = 5;
                    splashDamageRadius = 50;
                    splashDamage = 25;
                    hitEffect = despawnEffect = new MultiEffect(Fx.circleColorSpark, ExoFx.colorBombSmaller);
                    shootEffect = Fx.shootBig;
                    trailColor = hitColor = backColor = healColor = Pal.techBlue;
                }};
            }});

            weapons.add(new Weapon("exogenesis-atlas-assualt-weapon") {{
                x = 30.5f;
                y = 50.25f;
                rotateSpeed = 3f;
                reload = 35f;
                shootSound = Sounds.bolt;
                targetAir = false;
                alternate = false;
                rotate = true;
                recoil = 3f;
                shootY = 2.75f;
                bullet = new BasicBulletType(4.5f, 58f, "circle-bullet"){{
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.hitBulletColor;

                    lifetime = 73;
                    collidesAir = false;

                    shrinkX = shrinkY = 0f;
                    height = width = 5;

                    homingDelay = 1f;
                    homingPower = 0.2f;
                    homingRange = 120f;

                    backColor = trailColor = hitColor = Pal.techBlue;
                    trailWidth = 2.5f;
                    trailLength = 4;

                }
                    //I just didn't want to make a separate bulletType for one turret. (Maybe someday I will).
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        drawTrail(b);
                        int sides = 4;
                        float radius = 0f, radiusTo = 15f, stroke = 3f, innerScl = 0.5f, innerRadScl = 0.33f;
                        Color color1 = Pal.techBlue, color2 = Color.valueOf("d1efff");
                        float progress = b.fslope();
                        float rotation = 45f;
                        float layer = Layer.effect;

                        float z = Draw.z();
                        Draw.z(layer);

                        float rx = b.x, ry = b.y, rad = Mathf.lerp(radius, radiusTo, progress);

                        Draw.color(color1);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke, rad, j * 360f / sides + rotation);
                        }

                        Draw.color(color2);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke * innerScl, rad * innerRadScl, j * 360f / sides + rotation);
                        }

                        Draw.color();
                        Draw.z(z);
                    }};
            }});
            weapons.add(new Weapon("exogenesis-atlas-assualt-weapon") {{
                x = 37.75f;
                y = 36.5f;
                rotateSpeed = 3f;
                reload = 35f;
                shootSound = Sounds.bolt;
                alternate = false;
                targetAir = false;
                rotate = true;
                recoil = 3f;
                shootY = 2.75f;
                bullet = new BasicBulletType(4.5f, 58f, "circle-bullet"){{
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.hitBulletColor;

                    lifetime = 73;
                    collidesAir = false;

                    shrinkX = shrinkY = 0f;
                    height = width = 5;

                    homingDelay = 1f;
                    homingPower = 0.2f;
                    homingRange = 120f;

                    backColor = trailColor = hitColor = Pal.techBlue;
                    trailWidth = 2.5f;
                    trailLength = 4;

                }
                    //I just didn't want to make a separate bulletType for one turret. (Maybe someday I will).
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        drawTrail(b);
                        int sides = 4;
                        float radius = 0f, radiusTo = 15f, stroke = 3f, innerScl = 0.5f, innerRadScl = 0.33f;
                        Color color1 = Pal.techBlue, color2 = Color.valueOf("d1efff");
                        float progress = b.fslope();
                        float rotation = 45f;
                        float layer = Layer.effect;

                        float z = Draw.z();
                        Draw.z(layer);

                        float rx = b.x, ry = b.y, rad = Mathf.lerp(radius, radiusTo, progress);

                        Draw.color(color1);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke, rad, j * 360f / sides + rotation);
                        }

                        Draw.color(color2);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke * innerScl, rad * innerRadScl, j * 360f / sides + rotation);
                        }

                        Draw.color();
                        Draw.z(z);
                    }};
            }});
            weapons.add(new Weapon("exogenesis-atlas-assualt-weapon") {{
                x = 37.75f;
                y = 21.25f;
                rotateSpeed = 3f;
                reload = 35f;
                shootSound = Sounds.bolt;
                alternate = false;
                targetAir = false;
                rotate = true;
                recoil = 3f;
                shootY = 2.75f;
                bullet = new BasicBulletType(4.5f, 58f, "circle-bullet"){{
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.hitBulletColor;

                    lifetime = 73;
                    collidesAir = false;

                    shrinkX = shrinkY = 0f;
                    height = width = 5;

                    homingDelay = 1f;
                    homingPower = 0.2f;
                    homingRange = 120f;

                    backColor = trailColor = hitColor = Pal.techBlue;
                    trailWidth = 2.5f;
                    trailLength = 4;

                }
                    //I just didn't want to make a separate bulletType for one turret. (Maybe someday I will).
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        drawTrail(b);
                        int sides = 4;
                        float radius = 0f, radiusTo = 15f, stroke = 3f, innerScl = 0.5f, innerRadScl = 0.33f;
                        Color color1 = Pal.techBlue, color2 = Color.valueOf("d1efff");
                        float progress = b.fslope();
                        float rotation = 45f;
                        float layer = Layer.effect;

                        float z = Draw.z();
                        Draw.z(layer);

                        float rx = b.x, ry = b.y, rad = Mathf.lerp(radius, radiusTo, progress);

                        Draw.color(color1);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke, rad, j * 360f / sides + rotation);
                        }

                        Draw.color(color2);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke * innerScl, rad * innerRadScl, j * 360f / sides + rotation);
                        }

                        Draw.color();
                        Draw.z(z);
                    }};
            }});

            weapons.add(new Weapon("exogenesis-atlas-assualt-weapon") {{
                x = 31.25f;
                y = -27.5f;
                rotateSpeed = 3f;
                reload = 35f;
                shootSound = Sounds.bolt;
                alternate = false;
                targetAir = false;
                rotate = true;
                recoil = 3f;
                shootY = 2.75f;
                bullet = new BasicBulletType(4.5f, 58f, "circle-bullet"){{
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.hitBulletColor;

                    lifetime = 73;
                    collidesAir = false;

                    shrinkX = shrinkY = 0f;
                    height = width = 5;

                    homingDelay = 1f;
                    homingPower = 0.2f;
                    homingRange = 120f;

                    backColor = trailColor = hitColor = Pal.techBlue;
                    trailWidth = 2.5f;
                    trailLength = 4;

                }
                    //I just didn't want to make a separate bulletType for one turret. (Maybe someday I will).
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        drawTrail(b);
                        int sides = 4;
                        float radius = 0f, radiusTo = 15f, stroke = 3f, innerScl = 0.5f, innerRadScl = 0.33f;
                        Color color1 = Pal.techBlue, color2 = Color.valueOf("d1efff");
                        float progress = b.fslope();
                        float rotation = 45f;
                        float layer = Layer.effect;

                        float z = Draw.z();
                        Draw.z(layer);

                        float rx = b.x, ry = b.y, rad = Mathf.lerp(radius, radiusTo, progress);

                        Draw.color(color1);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke, rad, j * 360f / sides + rotation);
                        }

                        Draw.color(color2);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke * innerScl, rad * innerRadScl, j * 360f / sides + rotation);
                        }

                        Draw.color();
                        Draw.z(z);
                    }};
            }});
            weapons.add(new Weapon("exogenesis-atlas-assualt-weapon") {{
                x = 23.5f;
                y = -42.25f;
                rotateSpeed = 3f;
                reload = 35f;
                shootSound = Sounds.bolt;
                alternate = false;
                targetAir = false;
                rotate = true;
                recoil = 3f;
                shootY = 2.75f;
                bullet = new BasicBulletType(4.5f, 58f, "circle-bullet"){{
                    hitEffect = Fx.hitBulletColor;
                    despawnEffect = Fx.hitBulletColor;

                    lifetime = 73;
                    collidesAir = false;

                    shrinkX = shrinkY = 0f;
                    height = width = 5;

                    homingDelay = 1f;
                    homingPower = 0.2f;
                    homingRange = 120f;

                    backColor = trailColor = hitColor = Pal.techBlue;
                    trailWidth = 2.5f;
                    trailLength = 4;

                }
                    //I just didn't want to make a separate bulletType for one turret. (Maybe someday I will).
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        drawTrail(b);
                        int sides = 4;
                        float radius = 0f, radiusTo = 15f, stroke = 3f, innerScl = 0.5f, innerRadScl = 0.33f;
                        Color color1 = Pal.techBlue, color2 = Color.valueOf("d1efff");
                        float progress = b.fslope();
                        float rotation = 45f;
                        float layer = Layer.effect;

                        float z = Draw.z();
                        Draw.z(layer);

                        float rx = b.x, ry = b.y, rad = Mathf.lerp(radius, radiusTo, progress);

                        Draw.color(color1);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke, rad, j * 360f / sides + rotation);
                        }

                        Draw.color(color2);
                        for(int j = 0; j < sides; j++){
                            Drawf.tri(rx, ry, stroke * innerScl, rad * innerRadScl, j * 360f / sides + rotation);
                        }

                        Draw.color();
                        Draw.z(z);
                    }};
            }});
            /*
            weapons.add(new Weapon("exogenesis-atlas-blaster") {{
                reload = 80f;
                mirror = rotate = true;
                rotateSpeed = 1.5f;
                x = 35;
                y = 5f;
                shootSound = Sounds.bolt;
                shoot = new ShootPattern() {{
                    shots = 3;
                    shotDelay = 3.5f;
                }};
                shootCone = 15;
                layerOffset = 2.1f;
                shootY = 7;
                inaccuracy = 1.5f;
                recoil = 3;
                shake = 1f;
                bullet = new BasicBulletType(9f, 155) {{
                    width = 11f;
                    height = 17f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = Pal.techBlue;
                    lifetime = 50f;
                    hitEffect = new ParticleEffect() {{
                        particles = 7;
                        line = true;
                        length = 65f;
                        lifetime = 35f;
                        offset = 50;
                        cone = 30;
                        colorFrom = Pal.techBlue;
                        colorTo = Pal.techBlue;
                        lenFrom = lenTo = 8f;
                    }};
                    shrinkY = shrinkX = 0;
                    shootEffect = Fx.lightningShoot;
                    buildingDamageMultiplier = 1.15f;
                    despawnHit = true;
                    trailLength = 10;
                    trailWidth = 3.5f;
                    trailChance = 0.3f;
                    trailEffect = new ParticleEffect() {{
                        particles = 13;
                        length = baseLength = 2.5f;
                        lifetime = 20f;
                        cone = 360;
                        colorFrom = Pal.techBlue;
                        colorTo = Pal.techBlue;
                        sizeFrom = 3f;
                        sizeTo = 0f;
                    }};
                }};
            }});
             */
        }};
        /*
        nemesis = new ErekirUnitType("nemesis") {{
            constructor = UnitEntity::create;
            shadowElevation = 3f;
            fogRadius = 50;
            health = 46500;
            outlineRadius = 6;
            crashDamageMultiplier = 10;
            rotateSpeed = 0.7f;
            armor = 20f;
            speed = 0.88f;
            accel = 0.04f;
            drag = 0.04f;
            range = 200;
            flying = true;
            hitSize = lightRadius = 80f;
            engineSize = 0f;
            faceTarget = singleTarget = lowAltitude = true;
            abilities.add(new EnergyFieldAbility(25f, 45f, 280f){{
                statusDuration = 60f * 6f;
                sectors = 6;
                status = StatusEffects.sapped;
                color = Color.valueOf("a393fe");
                rotateSpeed = 2f;
                y = 25.5f;
                maxTargets = 35;
            }});
            abilities.add(new RegenAbility(){{
                amount = 10f;
            }});
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = 5.3f;
                reload = 40;
                range = 150;
                layer = 109;
                x = 29;
                y = -16f;
            }});
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = 5.3f;
                reload = 40;
                range = 150;
                layer = 109;
                x = -29;
                y = -16f;
            }});
            parts.addAll(
            new RegionPart("-glow1"){{
                mirror = false;
                under = true;
                layer = Layer.effect;
                color = colorTo = Color.valueOf("9681fb");
                blending = Blending.additive;
                outline = false;
                progress = PartProgress.warmup;
            }},
            new HoverPart(){{
                color = Color.valueOf("a393fe");
                circles = 3;
                phase = 50;
                radius = 15;
                sides = 360;
                mirror = false;
                layer = Layer.effect;
                y = 25.5f;
            }},
            new ShapePart(){{
                progress = PartProgress.recoil;
                color = Color.valueOf("a393fe");
                hollow = circle = mirror = true;
                stroke = strokeTo = 1.2f;
                radius = radiusTo = 8;
                layer = Layer.effect;
                x = 29;
                y = -16;
            }},
            new ShapePart(){{
                progress = PartProgress.recoil;
                color = Color.valueOf("a393fe");
                hollow = circle = mirror = true;
                stroke = strokeTo = 0.5f;
                radius = radiusTo = 5;
                layer = Layer.effect;
                x = 29;
                y = -16;
            }},
            new HaloPart(){{
                color = Color.valueOf("a393fe");
                tri = true;
                mirror = true;
                haloRotateSpeed = 1.7f;
                haloRadius = haloRadiusTo = 5f;
                stroke = strokeTo = 2f;
                shapes = 6;
                triLengthTo = triLength = 8;
                radius = 2f;
                layer = Layer.effect;
                x = 29;
                y = -16;
            }},
            new HaloPart(){{
                color = Color.valueOf("a393fe");
                tri = true;
                mirror = true;
                haloRotateSpeed = 0.7f;
                haloRadius = haloRadiusTo = 5f;
                stroke = strokeTo = 2f;
                shapes = 4;
                triLengthTo = triLength = 3;
                radius = 2.6f;
                layer = Layer.effect;
                x = 29;
                y = -16;
            }},
            new HaloPart(){{
                color = Color.valueOf("a393fe");
                tri = true;
                mirror = true;
                haloRotateSpeed = 0.6f;
                haloRadius = haloRadiusTo = 8f;
                stroke = strokeTo = 2f;
                shapes = 4;
                triLengthTo = triLength = 3;
                radius = 3f;
                layer = Layer.effect;
                x = 29;
                y = -16;
            }}
            );
            weapons.add(new Weapon("engine"){{
                mirror = alwaysContinuous = parentizeEffects = continuous = alwaysShooting = true;
                alternate = display = rotate = false;
                baseRotation = 180;
                x = 33;
                y = -39;
                shootY = 10;
                shootSound = Sounds.none;
                bullet = new ContinuousFlameBulletType(){{
                    damage = width = 4f;
                    layer = Layer.effect;
                    drawFlare = collides = false;
                    length = 27;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    intervalBullet = new BulletType(){{
                        despawnHit = true;
                        despawnEffect = Fx.none;
                        instantDisappear = true;
                        hitEffect = new ParticleEffect(){{
                            particles = 1;
                            line = true;
                            layer = 108;
                            length = 35f;
                            lifetime = 31f;
                            baseLength = 8;
                            cone = 20;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Color.valueOf("9681fb");
                            strokeFrom = 2;
                            lenFrom = 10;
                            lenTo = 0f;
                        }};
                    }};
                    colors = new Color[]{Color.valueOf("9681fb50"), Color.valueOf("9681fb"), Color.valueOf("bf92f9"), Color.white};
                }};
            }});
            weapons.add(new Weapon("engine2"){{
                mirror = alwaysContinuous = parentizeEffects = continuous = alwaysShooting = true;
                alternate = display = rotate = false;
                baseRotation = 224;
                x = 53;
                y = -35;
                shootY = 14;
                shootSound = Sounds.none;
                bullet = new ContinuousFlameBulletType(){{
                    damage = 4;
                    width = 5.3f;
                    layer = Layer.effect;
                    drawFlare = collides = false;
                    length = 20;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    intervalBullet = new BulletType(){{
                        despawnHit = true;
                        despawnEffect = Fx.none;
                        instantDisappear = true;
                        hitEffect = new ParticleEffect(){{
                            particles = 1;
                            line = true;
                            layer = 108;
                            length = 45f;
                            lifetime = 31f;
                            baseLength = 8;
                            cone = 25;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Color.valueOf("9681fb");
                            strokeFrom = 2;
                            lenFrom = 6;
                            lenTo = 0f;
                        }};
                    }};
                    colors = new Color[]{Color.valueOf("9681fb50"), Color.valueOf("9681fb"), Color.valueOf("bf92f9"), Color.white};
                }};
            }});
            weapons.add(new Weapon("exogenesis-big-missiles") {{
                reload = 230f;
                mirror = true;
                alternate = false;
                shootCone = 300;
                baseRotation = - 21;
                layerOffset = -1;
                x = 60;
                y = 8f;
                minWarmup = 0.85f;
                smoothReloadSpeed = 0.08f;
                shootWarmupSpeed = 0.02f;
                shootY = inaccuracy = 8;
                shootSound = Sounds.missileLaunch;
                recoil = 3.5f;
                shake = 1.8f;
                parts.addAll(
                new RegionPart("-missile"){{
                    mirror = false;
                    under = true;
                    layerOffset = -1;
                    outlineLayerOffset = 1;
                    progress = PartProgress.reload;
                    moveRot = -5f;
                }}
                );
                bullet = new BulletType(){{
                    shootEffect = smokeEffect = Fx.none;
                    spawnUnit = new MissileUnitType("big-thorium-missile"){{
                        speed = 5.6f;
                        maxRange = 15f;
                        hitSize = 20;
                        lifetime = 160f;
                        engineColor = trailColor = Color.valueOf("9681fb");
                        outlineColor = Color.valueOf("36363c");
                        engineLayer = Layer.effect;
                        engineSize = 5.3f;
                        engineOffset = 19f;
                        rotateSpeed = 0.9f;
                        missileAccelTime = 45;
                        trailLength = 22;
                        homingDelay = 5;
                        lowAltitude = true;
                        deathSound = Sounds.largeExplosion;
                        loopSound = Sounds.missileTrail;
                        loopSoundVolume = 0.6f;
                        fogRadius = 0f;
                        health = 800;
                        abilities.add(new MoveEffectAbility(){{
                            effect = Fx.none;
                            rotation = 180f;
                            rotateEffect = true;
                            y = -17f;
                            color = Color.grays(0.6f).lerp(Color.valueOf("9681fb"), 0.5f).a(0.4f);
                            interval = 3f;
                        }});
                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 4f;
                            bullet = new ExplosionBulletType(500f, 70f){{
                                shootEffect = Fx.reactorExplosion;
                                killShooter = true;
                                suppressionRange = 70;
                                suppressionDuration = 150;
                                status = StatusEffects.blasted;
                                statusDuration = 100;
                                hitSoundVolume = 5;
                                collidesGround = collidesAir = collidesTiles = true;
                                buildingDamageMultiplier = 0.8f;
                            }};
                        }});
                    }};
                }};
            }});
            weapons.add(new Weapon("exogenesis-nemesis-weapon"){{
                reload = 30f;
                rotate = mirror = false;
                x = 0;
                y = 0;
                layerOffset = -0.0001f;
                shoot = new ShootMulti(new ShootBarrel(){{
                barrels = new float[]{
                        42f, 65f, 0f,
                        -42f, 65f, 0f,
                        42f, 64f, 0f,
                        -42f, 64f, 0f,
                };
                }}, new ShootHelix(){{
                    shots = 2;
                    scl = 2.7f;
                    mag = 2f;
                }});
                shootSound = Sounds.mediumCannon;
                recoils = 4;
                recoil = shootY = shootX = 0;
                shake = 2f;
                parts.add(
                        new RegionPart("-barrel-1"){{
                            mirror = false;
                            under = true;
                            outlineLayerOffset = 0.0001f;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3.5f;
                        }},
                        new RegionPart("-barrel-2"){{
                            mirror = false;
                            under = true;
                            outlineLayerOffset = 0.0001f;
                            recoilIndex = 0;
                            progress = PartProgress.recoil;
                            moveY = -3.5f;
                        }},
                        new RegionPart("-barrel-3"){{
                            mirror = false;
                            under = true;
                            outlineLayerOffset = 0.0001f;
                            recoilIndex = 3;
                            progress = PartProgress.recoil;
                            moveY = -3.5f;
                        }},
                        new RegionPart("-barrel-4"){{
                            mirror = false;
                            under = true;
                            outlineLayerOffset = 0.0001f;
                            recoilIndex = 2;
                            progress = PartProgress.recoil;
                            moveY = -3.5f;
                        }}
                );
                bullet = new BasicBulletType(13.5f, 120){{
                    width = 15f;
                    height = 25f;
                    lifetime = 40f;
                    shootEffect = new MultiEffect(Fx.explosion, Fx.colorSparkBig);
                    shrinkY = shrinkX = 0;
                    hitEffect = new MultiEffect(Fx.sapExplosion, Fx.circleColorSpark, ExoFx.colorBombSmall);
                    despawnHit = true;
                    intervalRandomSpread = 50;
                    intervalBullets = 3;
                    intervalBullet = new LightningBulletType(){{
                        damage = 78;
                        lightningColor = Color.valueOf("9681fb");
                        lightningLength = 8;
                        lightningLengthRand = 11;
                        buildingDamageMultiplier = 0.25f;
                    }};
                    trailLength = 10;
                    trailWidth = 4.5f;
                    sprite = "missile-large";
                    hitColor = Color.valueOf("a393fe");
                    lightningColor = backColor = trailColor = Color.valueOf("a393fe");
                }};
            }});
        }};

         */
        nemesis = new ErekirUnitType("nemesis") {{
            constructor = UnitEntity::create;
            shadowElevation = 3f;
            fogRadius = 50;
            health = 46500;
            outlineRadius = 6;
            crashDamageMultiplier = 10;
            targetPriority = 4f;
            fallSpeed = 13000;
            faceTarget = false;
            rotateSpeed = 0.7f;
            armor = 20f;
            speed = 1.1f;
            accel = 0.04f;
            drag = 0.04f;
            flying = true;
            hitSize = 100;
            lightRadius = 80f;

            healColor = ExoPal.erekirPurple;
            engineSize = 0f;
            singleTarget = lowAltitude = true;
            /*
            abilities.add(new EnergyFieldAbility(25f, 45f, 280f){{
                statusDuration = 60f * 6f;
                sectors = 0;
                effectRadius = 0;
                status = StatusEffects.sapped;
                color = ExoPal.erekirPurple;
                rotateSpeed = 2f;
                y = 39.25f;
                maxTargets = 35;
            }});
             */
            abilities.add(new RegenAbility() {{
                amount = 4f;
            }});

            abilities.add(new BlackHoleAbility() {{
                suctionRadius = 350f;
                swirlEffects = 0;
                damageRadius = 180;
                force = 3;
                damage = 1F;
                y = 39.25f;
                bulletForce = 0.4f;
                lensingRadius = 20;
                horizonRadius = 15;
                color = ExoPal.erekirPurple;
            }});

            abilities.add(new SuppressionFieldAbility() {{
                orbRadius = 0f;
                particleSize = 0;
                reload = 40;
                range = 350;
                layer = 109;
                y = 39.25f;
            }});
            weapons.add(new Weapon("exogenesis-cronus-rocket-launcher") {{
                reload = 130f;
                mirror = true;
                rotate = false;
                x = 34;
                y = 46.25f;
                shootSound = Sounds.blaster;
                baseRotation = -65;
                shootY = 2;
                shoot = new ShootMulti(new ShootAlternate() {{
                    spread = 7;
                    shots = 3;
                    barrels = 3;
                }}, new ShootPattern() {{
                    shots = 5;
                    shotDelay = 3.5f;
                }});
                velocityRnd = 0.2f;
                shootCone = 165;
                inaccuracy = 6;
                layerOffset = -0.001f;
                recoil = 0;
                shake = 0.5f;
                bullet = new BulletType(){{
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                    smokeEffect = Fx.shootSmokeTitan;
                    hitColor = Pal.suppress;
                    shake = 1f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = true;

                    spawnUnit = new MissileUnitType("nemesis-missile"){{
                        targetAir = true;
                        speed = 7.6f;
                        rotateSpeed = 4;
                        maxRange = 5f;
                        outlineColor = Pal.darkOutline;
                        health = 70;
                        homingDelay = 3f;
                        lowAltitude = true;

                        engineSize = 3f;
                        engineColor = trailColor = Pal.sapBulletBack;
                        engineLayer = Layer.effect;
                        deathExplosionEffect = Fx.none;
                        loopSoundVolume = 0.1f;

                        parts.add(new ShapePart(){{
                            layer = Layer.effect;
                            circle = true;
                            y = -0.25f;
                            radius = 1.5f;
                            color = Pal.suppress;
                            colorTo = Color.white;
                            progress = PartProgress.life.curve(Interp.pow5In);
                        }});

                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            shootOnDeath = true;
                            bullet = new ExplosionBulletType(100f, 45f){{
                                collidesAir = false;
                                suppressionRange = 140f;
                                shootEffect = new ExplosionEffect(){{
                                    lifetime = 50f;
                                    waveStroke = 5f;
                                    waveLife = 8f;
                                    waveColor = Color.white;
                                    sparkColor = smokeColor = Pal.suppress;
                                    waveRad = 40f;
                                    smokeSize = 4f;
                                    smokes = 7;
                                    smokeSizeBase = 0f;
                                    sparks = 10;
                                    sparkRad = 40f;
                                    sparkLen = 6f;
                                    sparkStroke = 2f;
                                }};
                            }};
                        }});
                    }};
                }};
            }});
            weapons.add(new Weapon("exogenesis-nemesis-singularity") {{
                reload = 680f;
                rotate = true;
                rotateSpeed = 15;
                mirror = false;
                x = 0;
                y = 39.25f;
                shootSound = Sounds.none;
                recoil = shootY = shootX = 0;
                shake = 2f;
                bullet = new BlackHoleBulletType(1f, 7f) {{
                    lifetime = 550f;
                    growTime = 0;
                    force = 10;
                    horizonRadius = 15;
                    lensingRadius = 24;
                    suctionRadius = 100;
                    damageRadius = 50;
                    swirlEffect = ExoFx.smolSwirl;
                    swirlEffects = 2;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.erekirPurple;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
            }});
            /*
            weapons.add(new EnergyChargeWeapon("nemesis-weapon"){{
                reload = 150f;
                rotate = mirror = false;
                x = 0;
                y = 39.25f;
                shootSound = Sounds.mediumCannon;
                recoils = 4;
                recoil = shootY = shootX = 0;
                shake = 2f;

                float rad = 70f;
                drawCharge = (unit, mount, charge) -> {
                    float rotation = unit.rotation - 90f,
                            wx = unit.x + Angles.trnsx(rotation, x, y),
                            wy = unit.y + Angles.trnsy(rotation, x, y),
                            scl = Math.max(1f - (mount.reload / reload), 0f) / 2f;

                    Draw.color(ExoPal.erekirPurple);
                    ExoDrawf.shiningCircle(unit.id, Time.time, wx, wy, 20f * scl, 5, 70f, 15f, 4f * scl, 90f);
                    Draw.color(Color.white);
                    ExoDrawf.shiningCircle(unit.id, Time.time, wx, wy, 10f * scl, 5, 70f, 15f, 3f * scl, 90f);

                    Lines.stroke(2f);
                    Draw.color(ExoPal.erekirPurple);
                    ExoDrawf.dashCircleAngle(wx, wy, rad, Mathf.sin(Time.time + Mathf.randomSeed(unit.id, 0f, 6f), 90f, 30f));
                    Draw.reset();
                };
                chargeCondition = (unit, mount) -> {
                    ChargeMount m = (ChargeMount)mount;
                    if(mount.reload > 0f){
                        mount.reload = Math.max(mount.reload - Time.delta * unit.reloadMultiplier, 0);
                    }
                    if((m.timer += Time.delta) >= 5f){
                        float rotation = unit.rotation - 90f,
                                wx = unit.x + Angles.trnsx(rotation, x, y),
                                wy = unit.y + Angles.trnsy(rotation, x, y);

                        Units.nearbyEnemies(unit.team, wx, wy, rad, u -> {
                            u.damage(90f);
                            if(u.dead){
                                m.charge += Mathf.sqrt(u.maxHealth) * (u.isFlying() ? Mathf.clamp(u.type.fallSpeed * 5f) : 1f);
                                for(int i = 0; i < 4; i++){
                                    Time.run(i * 5f, () ->
                                            ExoFx.chargeTransfer.at(u.x, u.y, 0f, ExoPal.erekirPurple, unit));
                                }
                            }
                        });

                        m.timer = 0f;
                    }
                    if(m.charge > 0f){
                        float v = Math.min(m.charge, Time.delta * 2f);
                        m.charge -= v;
                        mount.reload -= v;
                    }
                    if(mount.reload < -250f){
                        mount.reload = -250f;
                        m.charge = 0f;
                    }
                };
                parts.add(
                        new RegionPart("-barrel-1"){{
                            mirror = false;
                            under = true;
                            outlineLayerOffset = 0.0001f;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3.5f;
                        }}
                );
                bullet = new AcceleratingLaserBulletType(60f){{
                    maxLength = 400f;
                    maxRange = 400f;
                    oscOffset = 0.3f;
                    shootEffect = ExoFx.ullarTipHit;
                    status = ExoStatusEffects.energyZapped;
                    statusDuration = 400;
                    lifetime = 200;
                    width = 33f;
                    damageType = energy;
                    collisionWidth = 10f;
                    colors = new Color[]{ExoPal.erekirPurple.cpy().a(0.2f), ExoPal.erekirPurple, Color.white};
                    pierceCap = 3;
                    hitEffect = ExoFx.ullarTipHit;
                    hitColor = ExoPal.erekirPurple;
                }};
            }});
             */

        }};
        cronus = new ErekirUnitType("cronus") {{
            constructor = LegsUnit::create;
            fogRadius = 50;
            speed = 0.8f;
            hitSize = 76f;
            health = 78000f;
            outlineRadius = 6;
            faceTarget = singleTarget = true;
            armor = 38;
            shadowElevation = 0.3f;
            allowLegStep = hovering = true;
            rotateSpeed = 0.8f;
            legSpeed = 0.6f;
            legMoveSpace = 0.7f;
            baseLegStraightness = 0.8f;
            legPhysicsLayer = false;
            legLength = 120;

            legCount = 8;
            legExtension = -6;
            legContinuousMove = lockLegBase = true;
            legStraightness = 0.3f;
            rippleScale = 2.8f;
            legPairOffset = 2;
            legBaseOffset = 36;
            legSplashDamage = 156;
            legSplashRange = 10;
            groundLayer = 77;
            range = 300;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 110f, 90f, 200f) {{
                parentizeEffects = true;
                effectY = 20f;
                activeEffect = new WaveEffect() {{
                    colorFrom = ExoPal.cronusRedlight;
                    colorTo = ExoPal.cronusRed;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 160f;
                    lifetime = 95f;
                    strokeTo = 0;
                    strokeFrom = 8f;
                }};
                applyEffect = Fx.none;
            }});
            immunities.add(StatusEffects.overclock);
            parts.addAll(
                    new ShapePart() {{
                        circle = true;
                        layer = 114;
                        y = 22;
                        radiusTo = radius = 8;
                        color = Color.white;
                    }},
                    new ShapePart() {{
                        circle = true;
                        layer = Layer.effect;
                        y = 22;
                        radiusTo = radius = 10;
                        color = ExoPal.cronusRedlight;
                    }},
                    new ShapePart() {{
                        circle = true;
                        layer = Layer.effect;
                        y = 22;
                        radiusTo = radius = 14;
                        color = ExoPal.cronusRed;
                    }},
                    new HoverPart() {{
                        color = ExoPal.cronusRed;
                        circles = 4;
                        stroke = 3;
                        phase = 100;
                        radius = 25f;
                        mirror = false;
                        layer = Layer.effect;
                        y = 22f;
                    }},
                    //main
                    new HaloPart() {{
                        tri = true;
                        y = 22;
                        radius = 6f;
                        layer = Layer.effect;
                        haloRadius = haloRadiusTo = 12;
                        haloRotateSpeed = -2.5f;
                        shapeRotation = 38;
                        strokeTo = 2;
                        shapes = 4;
                        triLength = triLengthTo = 17;
                        color = ExoPal.cronusRed;
                    }},
                    new HaloPart() {{
                        tri = true;
                        y = 22;
                        radius = 6f;
                        layer = Layer.effect;
                        haloRadius = haloRadiusTo = 12;
                        haloRotateSpeed = -2.5f;
                        shapeRotation = 218;
                        strokeTo = 2;
                        shapes = 4;
                        triLength = triLengthTo = 4;
                        color = ExoPal.cronusRed;
                    }},
                    new HaloPart() {{
                        tri = true;
                        y = 22;
                        radius = 6f;
                        layer = Layer.effect;
                        haloRadius = haloRadiusTo = 12;
                        haloRotateSpeed = -2.5f;
                        haloRotation = 45;
                        shapeRotation = 38;
                        strokeTo = stroke = 2;
                        shapes = 4;
                        triLength = triLengthTo = 19;
                        color = ExoPal.cronusRed;
                    }},
                    new HaloPart() {{
                        tri = true;
                        y = 22;
                        radius = 6f;
                        layer = Layer.effect;
                        haloRadius = haloRadiusTo = 12;
                        haloRotateSpeed = -2.5f;
                        haloRotation = 45;
                        shapeRotation = 218;
                        strokeTo = stroke = 2;
                        shapes = 4;
                        triLength = triLengthTo = 6;
                        color = ExoPal.cronusRed;
                    }}
            );
            weapons.add(new Weapon("disk") {{
                reload = 80f;
                mirror = rotate = true;
                alternate = true;
                shootCone = 45;
                cooldownTime = 80;
                layerOffset = 2;
                rotateSpeed = 9;
                x = 0;
                y = 22f;
                shoot = new ShootSpread() {{
                    shots = 6;
                    spread = 7;
                }};
                shootSound = Sounds.laser;
                recoil = shake = shootY = 0f;
                bullet = new BasicBulletType(9, 20) {{
                    parts.addAll(
                            new ShapePart() {{
                                circle = true;
                                layer = 114;
                                radiusTo = radius = 3.5f;
                                color = Color.white;
                            }},
                            new ShapePart() {{
                                circle = true;
                                layer = Layer.effect;
                                radiusTo = radius = 6;
                                color = ExoPal.cronusRed;
                            }},
                            new HaloPart() {{
                                tri = true;
                                radius = 4f;
                                layer = Layer.effect;
                                haloRadius = haloRadiusTo = 5;
                                haloRotateSpeed = -4.4f;
                                shapeRotation = 38;
                                strokeTo = stroke = 2;
                                shapes = 8;
                                triLength = triLengthTo = 4;
                                color = ExoPal.cronusRed;
                            }},
                            new HaloPart() {{
                                tri = true;
                                radius = 4f;
                                layer = Layer.effect;
                                haloRadius = haloRadiusTo = 5;
                                haloRotateSpeed = -4.4f;
                                shapeRotation = 218;
                                strokeTo = stroke = 2;
                                shapes = 8;
                                triLength = triLengthTo = 14;
                                color = ExoPal.cronusRed;
                            }}
                    );
                    trailColor = hitColor = lightningColor = ExoPal.cronusRed;
                    pierce = pierceBuilding = true;
                    pierceCap = 8;
                    width = height = 0;
                    lifetime = 90;
                    homingRange = 40;
                    homingDelay = 10;
                    homingPower = 0.8f;
                    despawnHit = true;
                    trailWidth = 3;
                    trailLength = 10;
                    hitEffect = Fx.circleColorSpark;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                }};
            }});
            weapons.add(new Weapon("exogenesis-cronus") {{
                reload = 70f;
                alwaysShooting = true;
                mirror = rotate = false;
                linearWarmup = true;
                alternate = true;
                shootCone = 45;
                cooldownTime = 80;
                layerOffset = 2;
                rotateSpeed = 0;
                recoils = 3;
                x = 0;
                y = 0f;
                shootSound = Sounds.none;
                recoil = shake = shootY = 0f;
                shoot = new ShootBarrel() {{
                    barrels = new float[]{
                            0f, 0f, 0f,
                            0f, 0f, 0f,
                            0f, 0f, 0f,
                    };
                }};
                parts.addAll(
                        new RegionPart("-barrel-3") {{
                            mirror = true;
                            x = 46;
                            y = 25;
                            recoilIndex = 2;
                            layerOffset = -0.002f;
                            progress = PartProgress.smoothReload.curve(Interp.slowFast);
                            moveRot = 30f;
                        }},
                        new RegionPart("-barrel-2") {{
                            mirror = true;
                            x = 50;
                            y = 0;
                            recoilIndex = 1;
                            layerOffset = -0.002f;
                            rotation = -20;
                            progress = PartProgress.smoothReload.curve(Interp.slowFast);
                            moveRot = 30f;
                        }},
                        new RegionPart("-barrel-1") {{
                            mirror = true;
                            x = 43;
                            y = -30;
                            recoilIndex = 0;
                            layerOffset = -0.002f;
                            rotation = -30;
                            progress = PartProgress.smoothReload.curve(Interp.slowFast);
                            moveRot = 50f;
                        }}
                );
                bullet = new BasicBulletType(9, 20) {{
                    instantDisappear = true;
                    width = height = 0;
                    lifetime = 1;
                    hitEffect = Fx.none;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                }};
            }});
            weapons.add(new Weapon("exogenesis-cronus-rocket-launcher") {{
                reload = 50f;
                mirror = true;
                rotate = false;
                rotateSpeed = 1.5f;
                x = 40;
                y = 31f;
                shootSound = Sounds.missile;
                baseRotation = -45;
                shoot = new ShootMulti(new ShootAlternate() {{
                    spread = 5;
                    shots = 3;
                    barrels = 3;
                }}, new ShootPattern() {{
                    shots = 5;
                    shotDelay = 1.5f;
                }});
                velocityRnd = 0.2f;
                shootCone = 165;
                layerOffset = -0.001f;
                recoil = 0;
                shake = 0.5f;
                bullet = new BasicBulletType(9f, 65) {{
                    width = 6f;
                    height = 29f;
                    sprite = "missile-large";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.cronusRed;
                    lifetime = 40f;
                    hitEffect = Fx.blastExplosion;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                    buildingDamageMultiplier = 0.8f;
                    despawnHit = true;
                    weaveMag = weaveScale = 2;
                    keepVelocity = false;
                    homingRange = 100;
                    homingDelay = 3;
                    homingPower = 0.08f;
                    trailLength = 10;
                    trailWidth = 2f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-cronus-repulser") {{
                reload = 40f;
                mirror = rotate = alternate = true;
                rotateSpeed = 2f;
                x = 36;
                y = 13;
                shootSound = Sounds.laser;
                shootStatus = StatusEffects.slow;
                shootStatusDuration = 40;
                shoot = new ShootSpread() {{
                    spread = 4;
                    shots = 3;
                }};
                shootCone = 45;
                recoil = 2;
                shake = 1.5f;
                bullet = new LaserBulletType() {{
                    damage = 140f;
                    sideWidth = 1f;
                    sideAngle = 35;
                    sideLength = 17;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                    width = 40f;
                    length = 200f;
                    hitColor = ExoPal.cronusRed;
                    colors = new Color[]{ExoPal.cronusRed.cpy().a(0.4f), ExoPal.cronusRed, Color.white};
                }};
            }});
            weapons.add(new Weapon("exogenesis-cronus-ion-blaster") {{
                reload = 80f;
                mirror = rotate = alternate = top = true;
                rotateSpeed = 2.2f;
                x = 50;
                y = -15.5f;
                shootSound = Sounds.shootSmite;
                shootStatus = StatusEffects.slow;
                shootStatusDuration = 40;
                cooldownTime = 120;
                heatColor = Color.red;
                parts.addAll(
                        new RegionPart("-blade") {{
                            mirror = true;
                            moveX = 3;
                            heatColor = Color.red;
                            moves.add(new PartMove(PartProgress.recoil, 4f, 0f, 0f));
                            progress = PartProgress.warmup;
                        }}
                );
                shoot = new ShootSpread() {{
                    spread = 5;
                    shots = 7;
                }};
                shootCone = 45;
                layerOffset = 0.001f;
                recoil = 3.5f;
                shake = 0.5f;
                bullet = new BasicBulletType(8.5f, 207) {{
                    lifetime = 60f;
                    backColor = lightColor = lightningColor = trailColor = hitColor = ExoPal.cronusRed;
                    impact = true;
                    knockback = 3f;
                    sprite = "circle-bullet";
                    trailSinScl = 2;
                    trailSinMag = 1.2f;
                    trailWidth = 5.5f;
                    trailLength = 10;
                    hitSize = 12f;
                    drag = 0.017f;
                    width = 105f;
                    height = 7;
                    shrinkX = 0.45f;
                    shrinkY = -2.48f;
                    pierceDamageFactor = 0.3f;
                    shrinkInterp = Interp.reverse;
                    pierce = pierceBuilding = laserAbsorb = true;
                    pierceCap = 6;
                    splashDamage = 50;
                    splashDamageRadius = 20;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.shootBigSmoke);
                    intervalBullet = new LightningBulletType() {{
                        damage = 3;
                        lightningColor = ExoPal.cronusRed;
                        lightningLength = 4;
                        lightningLengthRand = 6;
                        buildingDamageMultiplier = 0.25f;
                    }};
                    bulletInterval = 1f;
                    hitEffect = Fx.circleColorSpark;
                    despawnEffect = new Effect(35f, 70f, e -> {
                        Draw.color(e.color, Color.white, e.fout() * 0.7f);
                        for (int i : Mathf.signs) {

                            Drawf.tri(e.x, e.y, height * 1.5f * e.fout(), width * 0.885f * e.fout(), e.rotation + i * 90);
                            Drawf.tri(e.x, e.y, height * 0.8f * e.fout(), width * 0.252f * e.fout(), e.rotation + 90 + i * 90);
                        }
                    });
                }};
            }});
        }};

        //region Erekir ground support
        calm = new ErekirUnitType("calm") {{
            constructor = MechUnit::create;
            speed = 0.8f;
            hitSize = 8f;
            health = 720f;
            buildSpeed = 0.8f;
            healColor = ExoPal.letoColor;
            armor = 5f;
            researchCostMultiplier = 0f;

            abilities.add(new RepairFieldAbility(10f, 60f * 4, 60f));
            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                shootY = 2f;
                reload = 24f;
                x = 4.5f;
                mirror = true;
                alternate = false;
                ejectEffect = Fx.none;
                shootCone = 60f;
                velocityRnd = 0.35f;
                inaccuracy = 0.5f;
                shoot.shots = 3;
                recoil = 2f;
                shootSound = Sounds.shootBig;

                bullet = new ExoBasicBulletType(3.5f, 13) {{
                    lifetime = 40f;
                    height = 5;
                    width = 3;
                    trailLength = 7;
                    trailWidth = 1;
                    weaveScale = 5f;
                    weaveMag = 2f;
                    homingPower = 0.007f;
                    homingRange = 50f;
                    collidesTeam = true;
                    healAmount = 15;
                    sprite = "circle-bullet";
                    trailColor = hitColor = healColor = backColor = ExoPal.letoColor;
                    hitEffect = despawnEffect = new MultiEffect(Fx.colorSpark, ExoFx.squareHitsmall);
                    smokeEffect = Fx.colorSpark;
                    shootEffect = Fx.shootSmallColor;
                }};
            }});
        }};
        serene = new ErekirUnitType("serene") {{
            constructor = MechUnit::create;
            speed = 0.75f;
            hitSize = 11f;
            health = 2000f;
            armor = 9f;
            fogRadius = 25;
            healColor = ExoPal.letoColor;
            researchCostMultiplier = 0f;
            abilities.add(new RepairFieldAbility(25f, 60f * 4, 60f));
            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                alternate = mirror = true;
                x = 5f;
                y = 0.5f;
                shootY = 2.5f;
                reload = 13f;
                ejectEffect = Fx.none;
                recoil = 2.5f;
                shootSound = Sounds.blaster;
                velocityRnd = 0.3f;

                shoot = new ShootSpread(3, 6f);
                bullet = new BasicBulletType(5f, 12) {{
                    homingPower = 0.007f;
                    homingDelay = 0.8f;
                    buildingDamageMultiplier = 1.1f;
                    pierce = true;
                    pierceCap = 4;
                    width = 8f;
                    height = 16f;
                    lifetime = 30f;
                    collidesTeam = true;
                    healAmount = 15;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.colorSpark;
                    hitColor = backColor = healColor = trailColor = ExoPal.letoColor;
                    trailWidth = 1.5f;
                    trailLength = 5;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        tranquil = new ErekirUnitType("tranquil") {{
            constructor = MechUnit::create;
            health = 4140f;
            armor = 13f;
            fogRadius = 25;
            mechLandShake = 2f;
            riseSpeed = 0.05f;
            mechFrontSway = 0.55f;
            researchCostMultiplier = 0f;
            healColor = ExoPal.letoColor;
            speed = 0.64f;
            hitSize = 13f;
            drawShields = false;

            abilities.add(new ForceFieldAbility(140f, 3.7f, 3000f, 60f, 4, 45));

            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                alternate = mirror = true;
                shake = 2f;
                shootY = 6f;
                x = 7.5f;
                y = 0;
                reload = 35f;
                recoil = 3f;
                shootSound = Sounds.spark;
                shootCone = 30;
                inaccuracy = 5f;
                shoot.shots = 3;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = hitColor = ExoPal.letoColor;
                    damageType = DamageType.energy;
                    shootEffect = new MultiEffect(ExoFx.squareShoot, ExoFx.squareHitsmall);
                    smokeEffect = Fx.colorSpark;
                    range = 85;
                    targetRange = 10;
                    damage = 40;
                    distanceDamageFalloff = 2;
                    chainLightning = 1;
                    segmentLength = 6;
                }};
            }});
        }};
        sanctuary = new ErekirUnitType("sanctuary") {{
            constructor = MechUnit::create;
            hitSize = 24f;
            rotateSpeed = 1.8f;
            mechFrontSway = 1f;
            mechStepParticles = true;
            stepShake = 0.15f;
            drownTimeMultiplier = 4f;
            researchCostMultiplier = 0f;
            outlineRadius = 5;
            speed = 0.59f;
            clipSize = 250f;
            health = 9200f;
            healColor = ExoPal.letoColor;
            armor = 15f;
            mechLandShake = 4f;
            immunities = ObjectSet.with(StatusEffects.overclock);
            abilities.add(new EnergyFieldAbility(40f, 65f, 100f) {{
                statusDuration = 60f * 6f;
                healColor = color = ExoPal.letoColor;
                maxTargets = 25;
            }});
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 100f, 100, 100f) {{
                parentizeEffects = true;
                activeEffect = Fx.none;
                applyEffect = Fx.none;
            }});

            weapons.add(new Weapon(name + "-weapon") {{
                mirror = alternate = true;
                top = true;
                layerOffset = -0.001f;
                shake = 2f;
                shootY = 14.0f;
                x = 20.5f;
                y = 0f;
                reload = 65f;
                recoil = 3f;
                shootSound = Sounds.laser;
                shoot = new ShootSpread(3, 9f);
                bullet = new BasicBulletType(9f, 150) {{
                    width = height = 18f;
                    sprite = "circle-bullet";
                    shrinkX = shrinkY = 0;
                    drag = 0.06f;
                    lifetime = 55f;
                    trailWidth = 6f;
                    trailLength = 5;
                    splashDamageRadius = 50;
                    splashDamage = 25;
                    collidesTeam = true;
                    healAmount = 55;
                    pierce = true;
                    pierceCap = 2;
                    hitEffect = despawnEffect = new MultiEffect(Fx.circleColorSpark, ExoFx.squareHitsmall);
                    shootEffect = Fx.shootBig;
                    lightning = 3;
                    lightningLength = 6;
                    lightningColor = trailColor = hitColor = backColor = healColor = ExoPal.letoColor;
                    lightningDamage = 20;
                }};
            }});
        }};
        ataraxia = new ErekirUnitType("ataraxia") {{
            constructor = MechUnit::create;
            speed = 0.52f;
            hitSize = 26f;
            rotateSpeed = 1.65f;
            health = 24000;
            armor = 17f;
            mechStepParticles = true;
            stepShake = 0.75f;
            researchCostMultiplier = 0f;
            healColor = ExoPal.letoColor;
            outlineRadius = 5;
            drownTimeMultiplier = 6f;
            mechFrontSway = 1.9f;
            mechSideSway = 0.6f;
            weapons.add(
                    new Weapon(name + "-weapon") {{
                        top = false;
                        alternate = mirror = true;
                        y = 1.75f;
                        x = 27f;
                        shootY = 13f;
                        reload = 50f;
                        recoil = 5f;
                        shake = 2f;
                        shootSound = Sounds.malignShoot;
                        bullet = new FancyLaserBulletType() {{
                            damage = 425f;
                            sideAngle = 40f;
                            sideWidth = 0.8f;
                            sideLength = 130f;
                            collidesTeam = true;
                            pierceCap = 3;
                            healAmount = 230;
                            boltNum = 3;
                            liWidth = 2.8f;
                            width = 44f;
                            length = 240f;
                            colors = new Color[]{ExoPal.letoColor.cpy().a(0.3f), ExoPal.letoColor, Color.white};
                            hitEffect = ExoFx.coloredHitLarge;
                            hitColor = healColor = ExoPal.letoColor;
                            shootEffect = new MultiEffect(ExoFx.colorBomb, ExoFx.squareHitBig);
                        }};
                    }},
                    new Weapon("exogenesis-ES-heal-gun") {{
                        shootSound = Sounds.lasershoot;
                        reload = 34f;
                        x = 16.25f;
                        y = 7.25f;
                        rotate = true;
                        bullet = new ExoBasicBulletType(5f, 32) {{
                            homingPower = 0.007f;
                            homingDelay = 0.8f;
                            buildingDamageMultiplier = 1.1f;
                            damageType = kinetic;
                            pierce = true;
                            pierceCap = 1;
                            width = 10f;
                            height = 19f;
                            lifetime = 40f;
                            collidesTeam = true;
                            healAmount = 15;
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.colorSpark;
                            hitColor = backColor = healColor = trailColor = ExoPal.letoColor;
                            trailWidth = 1.7f;
                            trailLength = 5;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }},
                    new Weapon("exogenesis-ES-heal-gun") {{
                        shootSound = Sounds.lasershoot;
                        reload = 34f;
                        x = 16.25f;
                        y = -8.75f;
                        rotate = true;
                        bullet = new ExoBasicBulletType(5f, 32) {{
                            homingPower = 0.007f;
                            homingDelay = 0.8f;
                            buildingDamageMultiplier = 1.1f;
                            damageType = kinetic;
                            pierce = true;
                            pierceCap = 1;
                            width = 10f;
                            height = 19f;
                            lifetime = 40f;
                            collidesTeam = true;
                            healAmount = 15;
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.colorSpark;
                            hitColor = backColor = healColor = trailColor = ExoPal.letoColor;
                            trailWidth = 1.7f;
                            trailLength = 5;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }}
            );
        }};
        leto = new ErekirUnitType("leto") {{
            constructor = MechUnit::create;
            speed = 0.5f;
            hitSize = 57f;
            fogRadius = 50;
            rotateSpeed = 0.8f;
            health = 66500f;
            outlineRadius = 6;
            armor = 45f;
            mechStepParticles = true;
            stepShake = 4f;
            canDrown = false;
            mechFrontSway = 2f;
            mechSideSway = 0.7f;
            mechStride = (4f + (hitSize - 8f) / 2.1f) / 1.25f;
            abilities.add(new ShieldRegenFieldAbility(40f, 120f, 60f * 2, 260f) {{
                parentizeEffects = true;
                activeEffect = new WaveEffect() {{
                    colorFrom = ExoPal.letoColorLight;
                    colorTo = ExoPal.letoColor;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 260f;
                    lifetime = 95f;
                    strokeTo = 0;
                    strokeFrom = 6f;
                }};
                applyEffect = Fx.none;
            }});
            abilities.add(new RepairFieldAbility(450f, 60f * 3, 260f) {{
                activeEffect = Fx.none;
            }});
            abilities.add(new StatusFieldAbility(ExoStatusEffects.LetoBuff, 110f, 90f, 200f) {{
                parentizeEffects = true;
                activeEffect = new WaveEffect() {{
                    colorFrom = ExoPal.letoColorLight;
                    colorTo = ExoPal.letoColor;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 260f;
                    lifetime = 95f;
                    strokeTo = 0;
                    strokeFrom = 6f;
                }};
                applyEffect = Fx.none;
            }});
            immunities.add(ExoStatusEffects.LetoBuff);
            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                layerOffset = -0.001f;
                x = 57.75f;
                y = 0f;
                rotate = true;
                rotateSpeed = 1;
                rotationLimit = 40;
                parentizeEffects = true;
                shootY = 52.5f;
                shootX = -3f;
                cooldownTime = 2;
                alternate = false;
                recoil = 3f;
                shake = 2f;
                reload = 170;
                continuous = true;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.laserbeam;
                parts.addAll(
                        new EffectSpawnPart() {{
                            useProgress = true;
                            parentizeEffects = true;
                            y = 52.5f;
                            x = -3f;
                            effect = ExoFx.randLifeSparkCone;
                            progress = PartProgress.heat;
                            effectColor = ExoPal.letoColor;
                            randomEffectRot = 60;
                            effectChance = 0.6f;
                        }},
                        new RegionPart("-bit1") {{
                            mirror = false;
                            under = true;
                            heatColor = Color.red;
                            moves.add(new PartMove(PartProgress.recoil.curve(Interp.bounceIn), 3.5f, 0f, 0f));
                        }},
                        new RegionPart("-bit2") {{
                            mirror = false;
                            under = true;
                            heatColor = Color.red;
                            moves.add(new PartMove(PartProgress.recoil.curve(Interp.bounceIn), -3.5f, 0f, 0f));
                        }}
                );
                bullet = new AcceleratingLaserBulletType(260f) {{
                    maxLength = 530f;
                    maxRange = 530f;
                    oscOffset = 0.3f;
                    shootEffect = ExoFx.ullarTipHit;
                    status = ExoStatusEffects.energyZapped;
                    statusDuration = 400;
                    lifetime = 200;
                    width = 33f;
                    damageType = energy;
                    collisionWidth = 10f;
                    colors = new Color[]{ExoPal.letoColor.cpy().a(0.2f), ExoPal.letoColor, Color.white};
                    pierceCap = 3;
                    hitEffect = ExoFx.ullarTipHit;
                    hitColor = ExoPal.letoColor;
                }};
            }});
            weapons.add(new Weapon("exogenesis-leto-aa-railgun") {{
                reload = 10f;
                mirror = true;
                controllable = false;
                autoTarget = true;
                targetInterval = 20f;
                targetSwitchInterval = 35f;
                alternate = false;
                rotate = true;
                targetGround = false;
                rotateSpeed = 2.5f;
                baseRotation = -45;
                x = 34.25f;
                y = 18;
                layerOffset = 1;
                shootY = 15.5f;
                shoot = new ShootAlternate() {{
                    shots = 2;
                    spread = 8.6f;
                }};
                shootSound = Sounds.bolt;
                recoil = 1;
                shake = 1f;
                parts.add(
                        new RegionPart("-tip") {{
                            mirror = false;
                            under = true;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    buildingDamageMultiplier = 0.5f;
                    damageType = DamageType.pierce;
                    hitColor = ExoPal.letoColor;
                    collidesGround = collidesTiles = false;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark, ExoFx.squareShoot);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.letoColor, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
            weapons.add(new Weapon("exogenesis-leto-aa-railgun") {{
                reload = 10f;
                mirror = true;
                alternate = false;
                controllable = false;
                autoTarget = true;
                targetInterval = 20f;
                targetSwitchInterval = 35f;
                rotate = true;
                targetGround = false;
                rotateSpeed = 2.5f;
                baseRotation = -135;
                x = 34.25f;
                y = -18;
                layerOffset = 1;
                shootY = 15.5f;
                shoot = new ShootAlternate() {{
                    shots = 2;
                    spread = 8.6f;
                }};
                shootSound = Sounds.bolt;
                recoil = 1;
                shake = 1f;
                parts.add(
                        new RegionPart("-tip") {{
                            mirror = false;
                            under = true;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    damageType = DamageType.pierce;
                    collidesGround = collidesTiles = false;
                    buildingDamageMultiplier = 0.5f;
                    hitColor = ExoPal.letoColor;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark, ExoFx.squareShoot);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.letoColor, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
        }};

        // erekir hover tanks
        squall = new ErekirUnitType("squall") {{
            constructor = ElevationMoveUnit::create;
            hitSize = 12f;
            speed = 1.6f;
            rotateSpeed = 3.5f;
            health = 850;
            armor = 6f;
            itemCapacity = 0;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = true;
            faceTarget = false;
            hovering = true;
            singleTarget = true;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.1f;
            groundLayer = Layer.groundUnit;
            parts.addAll(
                    new HoverPart() {{
                        x = 3f;
                        y = 6.25f;
                        mirror = true;
                        sides = 360;
                        radius = 3f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                        ;
                    }},
                    new HoverPart() {{
                        x = 3f;
                        y = -6.25f;
                        mirror = true;
                        sides = 360;
                        radius = 3f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                        ;
                    }}
            );
            weapons.add(new Weapon("exogenesis-squall-weapon") {{
                layerOffset = 0.0001f;
                reload = 10f;
                shootY = 1.5f;
                recoil = 1f;
                inaccuracy = 3;
                rotate = true;
                rotateSpeed = 2.2f;
                mirror = false;
                x = 0f;
                y = 0f;
                cooldownTime = 30f;
                bullet = new EmpBulletType() {{
                    sprite = "exogenesis-arrow-bullet";
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBigColor;
                    hitPowerEffect = chainEffect = Fx.none;
                    unitDamageScl = 1.5f;
                    radius = 0;
                    pierce = true;
                    pierceCap = 2;
                    speed = 9;
                    damage = 18;
                    width = 8f;
                    height = 18f;
                    lifetime = 16f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = ExoPal.erekirYellow;
                    frontColor = Color.white;
                    trailWidth = 1f;
                    trailLength = 4;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        gust = new ErekirUnitType("gust") {{
            constructor = ElevationMoveUnit::create;
            hitSize = 18f;
            speed = 1.4f;
            rotateSpeed = 2.6f;
            health = 2100;
            armor = 8f;
            itemCapacity = 0;
            rotateMoveFirst = true;
            hovering = true;
            singleTarget = true;
            faceTarget = false;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.1f;
            groundLayer = Layer.groundUnit;
            researchCostMultiplier = 0f;
            parts.addAll(
                    new HoverPart() {{
                        x = 5.0f;
                        y = 8.75f;
                        mirror = true;
                        sides = 360;
                        radius = 4f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }},
                    new HoverPart() {{
                        x = 5.0f;
                        y = -8.75f;
                        mirror = true;
                        sides = 360;
                        radius = 6f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }}
            );
            weapons.add(new Weapon("exogenesis-gust-weapon") {{
                shootSound = Sounds.bolt;
                layerOffset = 0.0001f;
                reload = 38f;
                shootY = 1f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 1.4f;
                mirror = false;
                shootCone = 30f;
                inaccuracy = 8;
                x = 0f;
                y = 0f;
                cooldownTime = 30f;
                shoot.shotDelay = 2;

                shoot.shots = 3;

                bullet = new EmpBulletType() {{
                    width = height = 8f;
                    sprite = "circle-bullet";
                    shrinkX = shrinkY = 0;
                    hitPowerEffect = chainEffect = Fx.none;
                    unitDamageScl = 1.5f;
                    radius = 0;
                    speed = 9;
                    damage = 27;
                    drag = 0.06f;
                    lifetime = 55f;
                    trailWidth = 4f;
                    trailLength = 5;
                    splashDamageRadius = 40;
                    splashDamage = 25;
                    pierce = true;
                    pierceCap = 2;
                    hitEffect = Fx.blastExplosion;
                    shootEffect = Fx.colorSparkBig;
                    lightning = 2;
                    lightningLength = 6;
                    lightningColor = trailColor = hitColor = backColor = healColor = ExoPal.erekirYellow;
                    lightningDamage = 20;
                    fragBullets = 6;

                    fragBullet = new LightningBulletType() {{
                        damage = 5f;
                        lightningLength = 1;
                        lightningLengthRand = 4;
                        lightningColor = ExoPal.erekirYellow;
                    }};
                }};
            }});
        }};
        storm = new ErekirUnitType("storm") {{
            constructor = ElevationMoveUnit::create;
            hitSize = 26f;
            speed = 1.15f;
            rotateSpeed = 1.5f;
            health = 5000;
            armor = 11f;
            itemCapacity = 0;
            rotateMoveFirst = true;
            hovering = true;
            singleTarget = true;
            faceTarget = false;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.1f;
            groundLayer = Layer.groundUnit;
            researchCostMultiplier = 0f;
            parts.addAll(
                    new HoverPart() {{
                        x = 8.0f;
                        y = 11.75f;
                        mirror = true;
                        sides = 360;
                        radius = 4f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }},
                    new HoverPart() {{
                        x = 8.0f;
                        y = -11.75f;
                        mirror = true;
                        sides = 360;
                        radius = 6f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }}
            );
            weapons.add(new Weapon("exogenesis-storm-weapon") {{
                shootSound = Sounds.dullExplosion;
                layerOffset = 0.0001f;
                reload = 80f;
                shootY = 16f;
                recoil = 3f;
                rotate = true;
                rotateSpeed = 2.3f;
                mirror = false;
                shootCone = 2f;
                x = 0f;
                y = -1f;
                cooldownTime = 30f;
                bullet = new BasicBulletType(7f, 120) {{
                    sprite = "exogenesis-arrow-bullet";
                    width = 7f;
                    height = 18f;
                    shrinkX = 0;
                    shrinkY = 0;
                    lifetime = 28f;
                    hitSize = 6f;
                    intervalBullets = 2;
                    intervalRandomSpread = 30;
                    bulletInterval = 10f;
                    intervalBullet = new FancyLaserBulletType() {{
                        damage = 55f;
                        sideAngle = 45f;
                        sideWidth = 1f;
                        sideLength = 30f;
                        damageType = energy;
                        width = 20f;
                        length = 40f;
                        hitColor = lightningColor = ExoPal.erekirYellow;
                        shootEffect = Fx.colorSpark;
                        colors = new Color[]{ExoPal.erekirYellow.cpy().a(0.4f), ExoPal.erekirYellow, Color.white};
                    }};
                    pierceCap = 2;
                    pierce = true;
                    hitColor = backColor = trailColor = ExoPal.erekirYellow;
                    frontColor = Color.white;
                    trailWidth = 2.8f;
                    trailLength = 8;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    shootEffect = Fx.shootTitan;
                    smokeEffect = Fx.shootSmokeTitan;

                    trailEffect = Fx.hitSquaresColor;
                    trailRotation = true;
                    trailInterval = 3f;
                }};
            }});
        }};
        thunderstorm = new ErekirUnitType("thunderstorm") {{
            constructor = ElevationMoveUnit::create;
            hitSize = 28f;
            speed = 0.85f;
            rotateSpeed = 1f;
            health = 11000;
            armor = 20f;
            itemCapacity = 0;
            rotateMoveFirst = true;
            hovering = true;
            singleTarget = true;
            faceTarget = false;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.2f;
            groundLayer = Layer.groundUnit;
            parts.addAll(
                    new HoverPart() {{
                        x = 10.25f;
                        y = 11.75f;
                        mirror = true;
                        sides = 360;
                        radius = 7f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }},
                    new HoverPart() {{
                        x = 8.0f;
                        y = -11.75f;
                        mirror = true;
                        sides = 360;
                        radius = 9f;
                        phase = 30f;
                        layerOffset = -0.001f;
                        color = ExoPal.erekirYellow;
                    }}
            );
            weapons.add(new Weapon("exogenesis-thunderstorm-weapon") {{
                shootSound = Sounds.spark;
                layerOffset = 1f;
                reload = 50f;
                shootY = 0f;
                shake = 5f;
                recoil = 2f;
                rotate = true;
                rotateSpeed = 1f;
                mirror = false;
                x = 0f;
                y = 0;
                shadow = 28f;
                heatColor = Color.valueOf("f9350f");

                recoils = 3;
                parts.add(
                        new RegionPart("-barrel-1") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 2;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }},
                        new RegionPart("-barrel-2") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 1;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }},
                        new RegionPart("-barrel-3") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 0;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }}
                );
                shoot = new ShootBarrel() {{
                    barrels = new float[]{
                            6.0f, 17.5f, 0f,
                            0f, 19.0f, 0f,
                            -6.0f, 17.5f, 0f,
                    };
                }};

                bullet = new PosLightningType(90f) {{
                    lightningColor = hitColor = ExoPal.erekirYellow;
                    damageType = DamageType.energy;
                    boltNum = 4;
                    lightningDamage = 11;
                    lightning = 7;
                    lightningLength = 3;
                    lightningLengthRand = 7;
                    maxRange = rangeOverride = 200f;
                    shootEffect = ExoFx.flashShoot;
                    hitEffect = Fx.circleColorSpark;
                    smokeEffect = Fx.shootBigSmoke2;
                }};
            }});
        }};
        hurricane = new ErekirUnitType("hurricane") {{
            constructor = ElevationMoveUnit::create;
            hitSize = 46f;
            speed = 0.7f;
            health = 22000;
            armor = 26f;
            rotateSpeed = 0.8f;
            rotateMoveFirst = true;
            hovering = true;
            faceTarget = false;
            singleTarget = true;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.2f;
            groundLayer = Layer.groundUnit;
            weapons.add(new Weapon("exogenesis-hurricane-weapon") {{
                shootSound = Sounds.largeCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 32.5f;
                shake = 5f;
                recoil = 5f;
                rotate = true;
                rotateSpeed = 0.6f;
                mirror = false;
                x = 0f;
                y = -2f;
                shadow = 50f;
                heatColor = Color.valueOf("f9350f");
                shootWarmupSpeed = 0.06f;
                cooldownTime = 110f;
                heatColor = Color.valueOf("f9350f");
                minWarmup = 0.9f;

                parts.addAll(
                        new RegionPart("-glow") {{
                            color = Color.red;
                            blending = Blending.additive;
                            outline = mirror = false;
                        }},
                        new RegionPart("-sides") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            moveX = 0.75f;
                            moveY = 0.75f;
                            moveRot = 82f;
                            x = 37 / 4f;
                            y = 8 / 4f;
                        }},
                        new RegionPart("-sinks") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            heatColor = new Color(1f, 0.1f, 0.1f);
                            moveX = 17f / 4f;
                            moveY = -15f / 4f;
                            x = 32 / 4f;
                            y = -34 / 4f;
                        }},
                        new RegionPart("-sinks-heat") {{
                            blending = Blending.additive;
                            progress = PartProgress.warmup;
                            mirror = true;
                            outline = false;
                            colorTo = new Color(1f, 0f, 0f, 0.5f);
                            color = colorTo.cpy().a(0f);
                            moveX = 17f / 4f;
                            moveY = -15f / 4f;
                            x = 32 / 4f;
                            y = -34 / 4f;
                        }}
                );

                for (int i = 1; i <= 3; i++) {
                    int fi = i;
                    parts.add(new RegionPart("-blade") {{
                        progress = PartProgress.warmup.delay((3 - fi) * 0.3f).blend(PartProgress.reload, 0.3f);
                        heatProgress = PartProgress.heat.add(0.3f).min(PartProgress.warmup);
                        heatColor = new Color(1f, 0.1f, 0.1f);
                        mirror = true;
                        under = true;
                        moveRot = -40f * fi;
                        moveX = 3f;
                        layerOffset = -0.002f;

                        x = 11 / 4f;
                    }});
                }

                bullet = new BasicBulletType(8f, 360f) {{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 35f;
                    hitSize = 6f;

                    smokeEffect = Fx.shootSmokeTitan;
                    pierceCap = 3;
                    pierce = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.massiveExplosion;

                    shootEffect = new ExplosionEffect() {{
                        lifetime = 40f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 15f;
                        smokeSize = 5f;
                        smokes = 8;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 8;
                        sparkRad = 40f;
                        sparkLen = 4f;
                        sparkStroke = 3f;
                    }};

                    int count = 6;
                    for (int j = 0; j < count; j++) {
                        int s = j;
                        for (int i : Mathf.signs) {
                            float fin = 0.05f + (j + 1) / (float) count;
                            float spd = speed;
                            float life = lifetime / Mathf.lerp(fin, 1f, 0.5f);
                            spawnBullets.add(new BasicBulletType(spd * fin, 60) {{
                                drag = 0.002f;
                                width = 12f;
                                height = 11f;
                                lifetime = life + 5f;
                                weaveRandom = false;
                                hitSize = 5f;
                                pierceCap = 2;
                                pierce = true;
                                pierceBuilding = true;
                                hitColor = backColor = trailColor = Color.valueOf("feb380");
                                frontColor = Color.white;
                                trailWidth = 2.5f;
                                trailLength = 7;
                                weaveScale = (3f + s / 2f) / 1.2f;
                                weaveMag = i * (4f - fin * 2f);

                                splashDamage = 65f;
                                splashDamageRadius = 30f;
                                despawnEffect = new ExplosionEffect() {{
                                    lifetime = 50f;
                                    waveStroke = 4f;
                                    waveColor = sparkColor = trailColor;
                                    waveRad = 30f;
                                    smokeSize = 7f;
                                    smokes = 6;
                                    smokeSizeBase = 0f;
                                    smokeColor = trailColor;
                                    sparks = 5;
                                    sparkRad = 30f;
                                    sparkLen = 3f;
                                    sparkStroke = 1.5f;
                                }};
                            }});
                        }
                    }
                }};
            }});
        }};
        hyperion = new ErekirUnitType("hyperion") {{
            constructor = ElevationMoveUnit::create;
            speed = 0.78f;
            hitSize = 90f;
            health = 32000f;
            faceTarget = false;
            fogRadius = 50;
            armor = 45;
            outlineRadius = 6;
            rotateMoveFirst = true;
            hovering = true;
            singleTarget = true;
            useEngineElevation = false;
            flying = false;
            shadowElevation = 0.1f;
            groundLayer = Layer.groundUnit;
            rotateSpeed = 0.8f;
            //small
            parts.add(new HoverPart() {{
                x = 48f;
                y = 25;
                mirror = true;
                sides = 360;
                radius = 9f;
                phase = 30f;
                layerOffset = -0.001f;
                color = ExoPal.erekirYellow;
                ;
            }});
            parts.add(new HoverPart() {{
                x = 48f;
                y = -25;
                mirror = true;
                sides = 360;
                radius = 9f;
                phase = 30f;
                layerOffset = -0.001f;
                color = ExoPal.erekirYellow;
                ;
            }});
            //large
            parts.add(new HoverPart() {{
                x = 26f;
                y = 49;
                mirror = true;
                sides = 360;
                radius = 19f;
                circles = 4;
                phase = 90f;
                layerOffset = -0.001f;
                color = ExoPal.erekirYellow;
                ;
            }});
            parts.add(new HoverPart() {{
                x = 26f;
                y = -49;
                mirror = true;
                sides = 360;
                radius = 19f;
                circles = 4;
                phase = 90f;
                layerOffset = -0.001f;
                color = ExoPal.erekirYellow;
                ;
            }});
            weapons.add(new Weapon("exogenesis-hyperion-weapon") {{
                shootSound = Sounds.mediumCannon;
                top = rotate = true;
                mirror = false;
                x = 0;
                y = -10;
                reload = 16;
                recoil = 0f;
                recoils = 3;
                layerOffset = 2;
                cooldownTime = 250f;
                rotateSpeed = 1;
                parts.add(
                        new RegionPart("-barrel-1") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 2;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }},
                        new RegionPart("-barrel-2") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 1;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }},
                        new RegionPart("-barrel-3") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 0;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }}
                );
                shoot = new ShootBarrel() {{
                    barrels = new float[]{
                            15.5f, 43.0f, 0f,
                            0f, 47.75f, 0f,
                            -15.5f, 43.0f, 0f,
                    };
                }};
                bullet = new EmpBulletType() {{
                    sprite = "exogenesis-arrow-bullet";
                    width = 15f;
                    height = 36f;
                    speed = 13;
                    damage = 550;
                    lifetime = 32f;
                    shootEffect = Fx.shootBig;
                    smokeEffect = new ParticleEffect() {{
                        particles = 8;
                        line = true;
                        length = 60f;
                        lifetime = 40f;
                        baseLength = 8;
                        cone = offset = 30;
                        interp = Interp.circleOut;
                        colorFrom = colorTo = ExoPal.erekirYellow;
                        strokeFrom = 4;
                        lenFrom = lenTo = 6f;
                    }};
                    shootEffect = new MultiEffect(Fx.blastExplosion, Fx.flakExplosionBig, Fx.massiveExplosion);
                    hitEffect = new MultiEffect(Fx.blastExplosion, Fx.colorSparkBig, Fx.shootBigColor);
                    despawnHit = true;
                    shrinkY = shrinkX = 0;
                    pierceCap = 1;
                    pierceArmor = true;
                    pierceBuilding = false;
                    hitPowerEffect = chainEffect = Fx.none;
                    unitDamageScl = 1.5f;
                    buildingDamageMultiplier = 0.3f;
                    radius = 0;
                    bulletInterval = 1;
                    intervalBullets = 2;
                    intervalRandomSpread = 30;
                    intervalBullet = new LightningBulletType() {{
                        damage = 30;
                        ammoMultiplier = 1f;
                        collidesTiles = false;
                        lightningColor = ExoPal.erekirYellow;
                        ;
                        lightningLength = 3;
                        lightningLengthRand = 5;
                        buildingDamageMultiplier = 0.2f;
                    }};
                    trailChance = 1;
                    trailRotation = true;
                    trailInterval = 5f;
                    trailLength = 12;
                    trailWidth = 4f;
                    trailEffect = Fx.shootHealYellow;
                    lightning = 2;
                    lightningLength = 3;
                    lightningLengthRand = 6;
                    lightningColor = backColor = trailColor = hitColor = ExoPal.erekirYellow;
                    ;
                    lightningDamage = 50;
                }};
            }});
            weapons.add(new Weapon("exogenesis-hyperion-flak-turret") {{
                x = 41.0f;
                y = 3.25f;
                rotateSpeed = 2.5f;
                reload = 6f;
                shootSound = Sounds.shootBig;
                ejectEffect = Fx.casing2;
                rotationLimit = 60;
                cooldownTime = 80;
                shootCone = 40;
                layerOffset = 1;
                shadow = 7f;
                rotate = true;
                mirror = true;
                alternate = false;
                recoils = 2;
                recoil = 0f;
                parts.add(
                        new RegionPart("-barrel-l") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -5f;
                        }},
                        new RegionPart("-barrel-r") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 0;
                            progress = PartProgress.recoil;
                            moveY = -5f;
                        }}
                );
                shoot = new ShootBarrel() {{
                    barrels = new float[]{
                            3.5f, 15f, 0f,
                            -3.5f, 15f, 0f,
                    };
                }};
                bullet = new BasicBulletType(6.5f, 60) {{
                    width = height = 13f;
                    splashDamage = 57;
                    splashDamageRadius = 40;
                    lifetime = 35f;
                    hitColor = trailColor = backColor = ExoPal.erekirYellow;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBig2;
                    hitEffect = Fx.flakExplosion;
                    buildingDamageMultiplier = 0.5f;
                    trailLength = 10;
                    trailWidth = 2.5f;
                    fragBullets = 6;
                    fragLifeMin = 0.3f;
                    fragBullet = new BasicBulletType(8.5f, 6) {{
                        width = 5;
                        height = 9f;
                        splashDamage = 30;
                        splashDamageRadius = 25;
                        lifetime = 5f;
                        hitColor = trailColor = backColor = ExoPal.erekirYellow;
                        status = StatusEffects.blasted;
                        statusDuration = 100;
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = Fx.shootBig2;
                        hitEffect = Fx.flakExplosion;
                        buildingDamageMultiplier = 0.5f;
                    }};
                }};
            }});
        }};
        //region Erekir air support
        ivy = new ErekirUnitType("ivy") {{
            defaultCommand = UnitCommand.repairCommand;
            constructor = UnitEntity::create;
            flying = true;
            lowAltitude = false;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;
            health = 600;
            engineSize = 1.8f;
            engineOffset = 5.7f;
            engineColor = healColor = ExoPal.erekirPink;
            range = 50f;
            isEnemy = false;

            ammoType = new PowerAmmoType(500);
            abilities.add(new RegenAbility() {{
                amount = 10f;
            }});
            abilities.add(new RepairFieldAbility(5f, 60f * 8, 50f));

            weapons.add(new Weapon("cone-weapon") {{
                top = false;
                continuous = true;
                y = 0f;
                x = 0f;
                reload = 60f;
                ejectEffect = Fx.none;
                recoil = 0f;
                shootSound = Sounds.minebeam;
                bullet = new HealingConeBulletType(3f) {{
                    shootEffect = ExoFx.hitMeltColor;
                    hitSound = Sounds.none;
                    lifetime = 120 * 2.5f;
                    status = ExoStatusEffects.toxin1;
                    statusDuration = 200;
                    allyStatus = StatusEffects.overclock;
                    allyStatusDuration = 80;
                    cone = 25f;
                    length = 100f;
                    scanAccuracy = 10;
                    healPercent = 5.5f;
                    collidesTeam = true;
                    hitColor = color = ExoPal.erekirPink;
                }};
            }});
        }};
        yew = new ErekirUnitType("yew") {{
            constructor = UnitEntity::create;
            fogRadius = 25;
            flying = true;
            drag = 0.05f;
            speed = 2.6f;
            rotateSpeed = 15f;
            accel = 0.1f;
            range = 130f;
            health = 1130;
            buildSpeed = 0.5f;
            engineOffset = 6.5f;
            engineSize = 3;
            hitSize = 9f;
            engineColor = healColor = ExoPal.erekirPink;
            lowAltitude = true;

            ammoType = new PowerAmmoType(900);

            abilities.add(new RepairFieldAbility(5f, 60f * 8, 50f));
            abilities.add(new EnergyFieldAbility(5f, 65f, 20f) {{
                effectRadius = 3;
                y = 3.5f;
                hitBuildings = false;
                healColor = color = ExoPal.erekirPink;
                maxTargets = 10;
            }});

            weapons.add(
                    new Weapon("yew-weapon") {{
                        top = false;
                        y = 3.5f;
                        x = 0f;
                        reload = 23f;
                        ejectEffect = Fx.none;
                        recoil = 0f;
                        showStatSprite = false;
                        shootSound = Sounds.blaster;
                        velocityRnd = 0.5f;
                        inaccuracy = 15f;
                        alternate = mirror = false;
                        bullet = new MissileBulletType(4f, 12) {{
                            homingPower = 0.008f;
                            weaveMag = 4;
                            weaveScale = 4;
                            lifetime = 50f;
                            height = 8;
                            keepVelocity = false;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            frontColor = Color.white;
                            hitSound = Sounds.none;
                            trailLength = 6;
                            trailWidth = 2.5f;
                            healAmount = 50f;
                            collidesTeam = true;
                            trailColor = hitColor = healColor = backColor = ExoPal.erekirPink;
                        }};
                    }},
                    new Weapon("yew-weapon") {{
                        reload = 48f;
                        mirror = false;
                        x = 0;
                        y = 3.5f;
                        shootSound = Sounds.bolt;
                        showStatSprite = false;
                        shootY = 0;
                        recoil = 0;
                        bullet = new BasicBulletType(5, 28) {{
                            width = 8f;
                            height = 11f;
                            sprite = "circle-bullet";
                            frontColor = Color.white;
                            backColor = hitColor = healColor = trailColor = ExoPal.erekirPink;
                            lifetime = 33f;
                            shrinkY = shrinkX = 0;
                            collidesTeam = true;
                            healAmount = 50;
                            homingPower = 0.0678f;
                            homingRange = 40;
                            trailLength = 10;
                            trailWidth = 2f;
                            trailChance = 0.9f;
                            trailEffect = new ParticleEffect() {{
                                particles = 1;
                                length = baseLength = 2.5f;
                                lifetime = 20f;
                                colorFrom = colorTo = trailColor;
                                sizeFrom = 4f;
                                sizeTo = 0f;
                            }};
                        }};
                    }});
        }};
        lantana = new ErekirUnitType("lantana") {{
            defaultCommand = UnitCommand.repairCommand;
            constructor = UnitEntity::create;
            fogRadius = 25;
            mineTier = 3;
            mineSpeed = 4f;
            health = 460;
            armor = 3f;
            speed = 2.5f;
            accel = 0.06f;
            drag = 0.017f;
            lowAltitude = true;
            flying = true;
            engineOffset = 10.5f;
            hitSize = 16.05f;
            engineSize = 3f;
            engineColor = healColor = ExoPal.erekirPink;
            buildSpeed = 2.6f;
            isEnemy = false;

            ammoType = new PowerAmmoType(1100);

            weapons.add(
                    new Weapon("lantana-weapon") {{
                        reload = 48f;
                        mirror = false;
                        x = y = 0f;
                        shootSound = Sounds.bolt;
                        showStatSprite = false;
                        shootY = 0;
                        recoil = 0;
                        bullet = new BasicBulletType(3, 68) {{
                            width = 11f;
                            height = 18f;
                            sprite = "circle-bullet";
                            frontColor = Color.white;
                            backColor = hitColor = healColor = trailColor = ExoPal.erekirPink;
                            lifetime = 93f;
                            shrinkY = shrinkX = 0;
                            collidesTeam = true;
                            keepVelocity = false;
                            healAmount = 120;
                            homingPower = 0.0678f;
                            homingRange = 40;
                            int count = 3;
                            for (int j = 0; j < count; j++) {
                                int s = j;
                                for (int i : Mathf.signs) {
                                    float fin = 0.05f + (j + 1) / (float) count;
                                    float spd = speed;
                                    float life = lifetime / Mathf.lerp(fin, 0.8f, 0.5f);
                                    spawnBullets.add(new MissileBulletType(spd * fin, 60) {{
                                        drag = 0.002f;
                                        height = 11f;
                                        lifetime = life + 5f;
                                        weaveRandom = false;
                                        weaveScale = (4f + s / 2f) / 1.2f;
                                        weaveMag = i * (4f - fin * 2f);
                                        keepVelocity = false;
                                        hitEffect = despawnEffect = Fx.hitBulletColor;
                                        hitSize = 5f;
                                        healAmount = 50f;
                                        collidesTeam = true;
                                        trailColor = hitColor = healColor = backColor = ExoPal.erekirPink;
                                        frontColor = Color.white;
                                        trailWidth = 2.5f;
                                        trailLength = 7;
                                    }});
                                }
                            }
                            trailLength = 10;
                            trailWidth = 2f;
                            trailChance = 0.9f;
                            trailEffect = new ParticleEffect() {{
                                particles = 1;
                                length = baseLength = 2.5f;
                                lifetime = 20f;
                                colorFrom = colorTo = trailColor;
                                sizeFrom = 4f;
                                sizeTo = 0f;
                            }};
                            fragOnHit = false;
                            fragRandomSpread = 0f;
                            fragSpread = 10f;
                            fragBullets = 5;
                            fragBullet = new ShrapnelBulletType() {{
                                length = 20;
                                damage = 26f;
                                serrations = 0;
                                backColor = hitColor = toColor = ExoPal.erekirPink;
                                width = 8f;
                            }};
                        }};
                    }}
            );
        }};
        kalmia = new ErekirUnitType("kalmia") {{
            constructor = UnitEntity::create;
            armor = 8f;
            health = 6000;
            speed = 1.4f;
            fogRadius = 25;
            rotateSpeed = 2f;
            accel = 0.05f;
            drag = 0.017f;
            lowAltitude = false;
            flying = true;
            circleTarget = true;
            engineOffset = 13f;
            engineSize = 7f;
            faceTarget = false;
            hitSize = 36f;
            engineColor = healColor = ExoPal.erekirPink;
            payloadCapacity = (3 * 3) * tilePayload;
            outlineRadius = 4;
            buildSpeed = 2.5f;
            buildBeamOffset = 23;
            range = 140f;
            targetAir = false;

            ammoType = new PowerAmmoType(3000);
            abilities.add(new EnergyFieldAbility(40f, 65f, 140f) {{
                effectRadius = 0;
                hitBuildings = false;
                healColor = color = ExoPal.erekirPink;
                maxTargets = 10;
            }});
            abilities.add(new SuppressionFieldAbility() {{
                orbRadius = 5.3f;
                particleColor = color = ExoPal.erekirPink;
                reload = 40;
                range = 150;
                layer = 109;
                x = y = 0f;
            }});

            weapons.add(
                    new Weapon() {{
                        x = y = 0f;
                        mirror = false;
                        reload = 125f;
                        minShootVelocity = 0.01f;

                        soundPitchMin = 1f;
                        shootSound = Sounds.plasmadrop;
                        shoot = new ShootPattern() {{
                            shots = 5;
                            shotDelay = 3.5f;
                        }};
                        velocityRnd = 0.2f;
                        bullet = new BasicBulletType() {{
                            sprite = "large-bomb";
                            width = height = 120 / 4f;
                            maxRange = 30f;
                            ignoreRotation = true;
                            backColor = ExoPal.letoColor;
                            frontColor = Color.white;
                            mixColorTo = Color.white;
                            hitSound = Sounds.plasmaboom;
                            shootCone = 180f;
                            ejectEffect = Fx.none;
                            hitShake = 4f;
                            collidesAir = false;
                            lifetime = 70f;
                            despawnEffect = ExoFx.colorBomb;
                            hitEffect = Fx.massiveExplosion;
                            keepVelocity = false;
                            spin = 2f;
                            shrinkX = shrinkY = 0.7f;
                            speed = 0f;
                            collides = false;
                            healAmount = 265f;
                            splashDamage = 100f;
                            splashDamageRadius = 80f;
                        }};
                    }});
        }};
        hemlock = new ErekirUnitType("hemlock") {{
            aiController = DefenderAI::new;
            constructor = UnitEntity::create;
            outlineRadius = 5;
            armor = 16f;
            health = 24000;
            speed = 0.8f;
            rotateSpeed = 1f;
            accel = 0.04f;
            drag = 0.018f;
            flying = true;
            engineOffset = 46f;
            engineSize = 7.8f;
            faceTarget = false;
            hitSize = 66f;
            engineColor = healColor = ExoPal.erekirPink;
            payloadCapacity = (5.5f * 5.5f) * tilePayload;
            buildSpeed = 4f;
            drawShields = false;
            lowAltitude = true;
            buildBeamOffset = 43;
            ammoCapacity = 1;

            abilities.add(new ForceFieldAbility(140f, 4f, 7000f, 60f * 8, 8, 0f), new RepairFieldAbility(130f, 60f * 2, 140f));
        }};
        rhea = new ErekirUnitType("rhea") {{
            constructor = UnitEntity::create;
            defaultCommand = UnitCommand.repairCommand;
            buildSpeed = 2.6f;
            rotateSpeed = 0.9f;
            buildBeamOffset = 30;
            shadowElevation = 2f;
            health = 76500f;
            lightRadius = 80;
            fogRadius = 50;
            outlineRadius = 6;
            armor = 16f;
            speed = 1.4f;
            accel = 0.04f;
            drag = 0.04f;
            flying = true;
            fallSpeed = 0.006f;
            crashDamageMultiplier = 20;
            hitSize = 100f;
            engineColor = Color.valueOf("fc81de");
            engineOffset = 58;
            engineSize = 9.5f;
            faceTarget = false;
            singleTarget = true;
            lowAltitude = true;
            abilities.add(new RegenAbility() {{
                //fully regen in 70 seconds
                percentAmount = 1f / (70f * 60f) * 100f;
            }});
            abilities.add(new StatusFieldAbility(ExoStatusEffects.RheaBuff, 110f, 100f, 260f) {{
                parentizeEffects = true;
                effectY = 14.25f;
                activeEffect = new WaveEffect() {{
                    colorFrom = Color.valueOf("ffcbdd");
                    colorTo = ExoPal.erekirPink;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 160f;
                    lifetime = 95f;
                    strokeTo = 0;
                    strokeFrom = 8f;
                }};

                applyEffect = Fx.none;
            }});
            immunities.add(ExoStatusEffects.RheaBuff);
            parts.addAll(
                    new RegionPart("-glow") {{
                        mirror = false;
                        under = true;
                        layer = -1;
                        color = colorTo = Color.valueOf("8400ff");
                        blending = Blending.additive;
                        outline = false;
                        progress = PartProgress.warmup;
                    }},
                    new HoverPart() {{
                        color = ExoPal.erekirPink;
                        circles = 3;
                        stroke = 6;
                        sides = 360;
                        phase = 100;
                        radius = 48f;
                        mirror = false;
                        layer = Layer.effect;
                        y = 14.25f;
                    }}
            );
            setEnginesMirror(
                    new UnitEngine(28, -56, 5, 270),
                    new UnitEngine(-28, -56, 5, 270)
            );
            weapons.add(new Weapon("exogenesis-rhea-energy-ball") {{
                x = 0f;
                y = 14.25f;
                shootCone = 25;
                mirror = false;
                reload = 700;
                shootSound = ExoSounds.bigLaserShoot;
                shootY = 0f;
                rotateSpeed = 5;
                rotate = true;
                minWarmup = 0.8f;
                smoothReloadSpeed = 0.15f;
                recoil = 0f;
                parts.addAll(
                        new ShapePart() {{
                            circle = true;
                            layer = 114;
                            radiusTo = 0;
                            radius = 9;
                            color = Color.white;
                            progress = PartProgress.reload;
                        }},
                        new ShapePart() {{
                            circle = true;
                            layer = 110;
                            radiusTo = 0;
                            radius = 13;
                            color = Color.valueOf("ffcbdd");
                            progress = PartProgress.reload;
                        }},
                        new ShapePart() {{
                            circle = true;
                            layer = 110;
                            radiusTo = 0;
                            radius = 16;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.reload;
                        }},
                        new RegionPart("-pew") {{
                            mirror = false;
                            under = true;
                            growY = 12;
                            growX = 50;
                            y = 12;
                            x = 16;
                            layer = 110;
                            color = ExoPal.erekirPink;
                            colorTo = Color.valueOf("d370d300");
                            blending = Blending.additive;
                            outline = false;
                            progress = PartProgress.warmup;
                        }},
                        //main
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 7.6f;
                            layer = Layer.effect;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 10;
                            triLengthTo = 43;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        new HaloPart() {{
                            tri = true;
                            shapeRotation = 180;
                            y = 12;
                            radius = 7.6f;
                            layer = Layer.effect;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = triLengthTo = 4;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        //sides
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 5.6f;
                            layer = Layer.effect;
                            haloRotation = 33;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 6;
                            triLengthTo = 23;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 5.6f;
                            layer = Layer.effect;
                            haloRotation = 33;
                            shapeRotation = 180;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = triLengthTo = 4;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        //sides
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 5.6f;
                            layer = Layer.effect;
                            haloRotation = -33;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 6;
                            triLengthTo = 23;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 5.6f;
                            layer = Layer.effect;
                            haloRotation = 33;
                            shapeRotation = 180;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 4;
                            triLengthTo = 4;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        //sides2
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 4.3f;
                            layer = Layer.effect;
                            haloRotation = -60;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 0;
                            triLengthTo = 10;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 4.3f;
                            layer = Layer.effect;
                            haloRotation = -60;
                            shapeRotation = 180;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 0;
                            triLengthTo = 10;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        //sides2
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 4.3f;
                            layer = Layer.effect;
                            haloRotation = 60;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 0;
                            triLengthTo = 10;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }},
                        new HaloPart() {{
                            tri = true;
                            y = 12;
                            radius = 4.3f;
                            layer = Layer.effect;
                            haloRotation = 60;
                            shapeRotation = 180;
                            haloRadius = haloRadiusTo = 12;
                            strokeTo = 2;
                            shapes = 1;
                            triLength = 0;
                            triLengthTo = 10;
                            color = ExoPal.erekirPink;
                            progress = PartProgress.warmup;
                        }}
                );
                bullet = new BasicBulletType(4f, 70) {{
                    parts.addAll(
                            new ShapePart() {{
                                circle = true;
                                layer = 114;
                                radiusTo = 0;
                                radius = 9;
                                color = Color.white;
                                progress = PartProgress.reload;
                            }},
                            new ShapePart() {{
                                circle = true;
                                layer = Layer.effect;
                                radiusTo = 0;
                                radius = 13;
                                color = Color.valueOf("ffcbdd");
                                progress = PartProgress.reload;
                            }},
                            new ShapePart() {{
                                circle = true;
                                layer = Layer.effect;
                                radiusTo = 0;
                                radius = 16;
                                color = ExoPal.erekirPink;
                                progress = PartProgress.reload;
                            }},
                            new HoverPart() {{
                                color = ExoPal.erekirPink;
                                circles = 3;
                                phase = 100;
                                radius = 48f;
                                mirror = false;
                                layer = Layer.effect;
                            }}
                    );
                    lightRadius = 40;
                    hitColor = trailColor = ExoPal.erekirPink;
                    trailLength = 26;
                    trailWidth = 9;
                    scaleLife = true;
                    collidesGround = collidesTiles = collidesAir = false;
                    width = height = 0;
                    despawnEffect = new MultiEffect(ExoFx.explodeyscathe, ExoFx.colorBomb,
                            new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                line = true;
                                particles = 35;
                                length = 275;
                                offset = 40;
                                strokeFrom = 5;
                                strokeTo = 0;
                                lifetime = 160;
                                lenFrom = 20;
                                lenTo = 10;
                                lightColor = colorFrom = Color.valueOf("ffcbdd");
                                colorTo = ExoPal.erekirPink;
                            }});
                    hitSound = Sounds.largeExplosion;
                    splashDamage = 1276;
                    splashDamageRadius = 100;
                    scaledSplashDamage = true;
                    trailSinScl = 2;
                    trailSinMag = 1.2f;
                    trailChance = 0.3f;
                    trailParam = 3.5f;
                    lifetime = 160f;
                    shootEffect = ExoFx.explodeyscathe;
                    bulletInterval = 3;
                    intervalRandomSpread = 60;
                    intervalBullet = new BasicBulletType(7, 40) {{
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    layer = 114;
                                    radiusTo = radius = 1;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    layer = Layer.effect;
                                    radiusTo = radius = 2;
                                    color = Color.valueOf("ffcbdd");
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    layer = Layer.effect;
                                    radiusTo = radius = 3;
                                    color = ExoPal.erekirPink;
                                }}
                        );
                        hitSound = Sounds.explosion;
                        hitColor = trailColor = ExoPal.erekirPink;
                        pierceBuilding = pierce = true;
                        pierceCap = 2;
                        homingPower = 0.002f;
                        homingRange = 100;
                        lifetime = 60;
                        width = height = 1;
                        drag = 0.029f;
                        weaveMag = 40;
                        weaveScale = -5;
                        trailSinScl = 2;
                        trailSinMag = 0.8f;
                        trailLength = 9;
                        trailWidth = 2;
                        despawnHit = true;
                        hitEffect = ExoFx.colorBombSmall;
                    }};
                    fragRandomSpread = 0f;
                    fragBullets = 1;
                    fragBullet = new BasicBulletType(0f, 5) {{
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    layer = 114;
                                    radiusTo = 0;
                                    radius = 10;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    layer = Layer.effect;
                                    radiusTo = 0;
                                    radius = 17;
                                    color = Color.valueOf("ffcbdd");
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    layer = Layer.effect;
                                    radiusTo = 0;
                                    radius = 20;
                                    color = ExoPal.erekirPink;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.erekirPink;
                                    circles = 2;
                                    phase = 100;
                                    radius = 68f;
                                    mirror = false;
                                    layer = Layer.effect;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.erekirPink;
                                    circles = 2;
                                    phase = 100;
                                    radius = 88f;
                                    mirror = false;
                                    layer = Layer.effect;
                                }}
                        );
                        hitSound = Sounds.largeExplosion;
                        width = height = 0f;
                        lifetime = 240f;
                        hitSize = 4f;
                        splashDamage = 576;
                        splashDamageRadius = 60;
                        scaledSplashDamage = true;
                        backColor = hitColor = trailColor = ExoPal.erekirPink;
                        collidesGround = collidesTiles = collidesAir = false;
                        trailWidth = 2f;
                        trailLength = 6;
                        hitEffect = despawnEffect = new MultiEffect(
                                new ParticleEffect() {{
                                    lightOpacity = 0.5f;
                                    line = true;
                                    particles = 35;
                                    length = 275;
                                    offset = 40;
                                    strokeFrom = 5;
                                    strokeTo = 0;
                                    lifetime = 60;
                                    lenFrom = 20;
                                    lenTo = 10;
                                    lightColor = colorFrom = Color.valueOf("ffcbdd");
                                    colorTo = ExoPal.erekirPink;
                                }},
                                new WaveEffect() {{
                                    colorFrom = Color.valueOf("ffcbdd");
                                    colorTo = ExoPal.erekirPink;
                                    sizeFrom = 70;
                                    sizeTo = 0f;
                                    lifetime = 55f;
                                    strokeTo = 19;
                                    strokeFrom = 0f;
                                }},
                                new WaveEffect() {{
                                    colorFrom = Color.valueOf("ffcbdd");
                                    colorTo = ExoPal.erekirPink;
                                    sizeFrom = 50;
                                    sizeTo = 0f;
                                    lifetime = 55f;
                                    strokeTo = 7;
                                    strokeFrom = 0f;
                                }}
                        );
                        bulletInterval = 1;
                        intervalRandomSpread = 360;
                        intervalBullet = new BasicBulletType(5, 16) {{
                            parts.addAll(
                                    new ShapePart() {{
                                        circle = true;
                                        layer = 114;
                                        radiusTo = radius = 2;
                                        color = Color.white;
                                    }},
                                    new ShapePart() {{
                                        circle = true;
                                        layer = Layer.effect;
                                        radiusTo = radius = 3;
                                        color = Color.valueOf("ffcbdd");
                                    }},
                                    new ShapePart() {{
                                        circle = true;
                                        layer = Layer.effect;
                                        radiusTo = radius = 4;
                                        color = ExoPal.erekirPink;
                                    }}
                            );
                            hitSound = Sounds.bolt;
                            hitColor = trailColor = ExoPal.erekirPink;
                            weaveMag = 30;
                            weaveScale = 14;
                            weaveRandom = false;
                            pierceBuilding = pierce = true;
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        line = true;
                                        particles = 10;
                                        length = 45;
                                        offset = 40;
                                        strokeFrom = 2;
                                        strokeTo = 0;
                                        lifetime = 30;
                                        lenFrom = 7;
                                        lenTo = 3;
                                        lightColor = colorFrom = Color.valueOf("ffcbdd");
                                        colorTo = ExoPal.erekirPink;
                                    }},
                                    new WaveEffect() {{
                                        colorFrom = Color.valueOf("ffcbdd");
                                        colorTo = ExoPal.erekirPink;
                                        interp = Interp.circleOut;
                                        sizeFrom = 0;
                                        sizeTo = 20f;
                                        lifetime = 35f;
                                        strokeTo = 0;
                                        strokeFrom = 2f;
                                    }}
                            );
                            pierceCap = 5;
                            lifetime = 65;
                            width = height = 1;
                            drag = -0.017f;
                            trailLength = 9;
                            trailWidth = 2;
                            despawnHit = true;
                            spawnBullets.add(new BasicBulletType(7, 9) {{
                                parts.addAll(
                                        new ShapePart() {{
                                            circle = true;
                                            layer = 114;
                                            radiusTo = radius = 1;
                                            color = Color.white;
                                        }},
                                        new ShapePart() {{
                                            circle = true;
                                            layer = Layer.effect;
                                            radiusTo = radius = 2;
                                            color = Color.valueOf("ffcbdd");
                                        }},
                                        new ShapePart() {{
                                            circle = true;
                                            layer = Layer.effect;
                                            radiusTo = radius = 3;
                                            color = ExoPal.erekirPink;
                                        }}
                                );
                                fragRandomSpread = 0f;
                                fragSpread = 120;
                                fragBullets = 3;
                                fragBullet = new ShrapnelBulletType() {{
                                    damage = 15f;
                                    width = 10f;
                                    serrations = 0;
                                    length = 3f;
                                    lifetime = 25;
                                    toColor = fromColor = ExoPal.erekirPink;
                                }};
                                hitSound = Sounds.bolt;
                                drag = -0.0015f;
                                width = height = 0f;
                                lifetime = 42f;
                                pierceCap = 5;
                                pierce = true;
                                pierceBuilding = true;
                                hitColor = trailColor = ExoPal.erekirPink;
                                trailWidth = 2f;
                                trailLength = 9;
                                weaveScale = 5;
                                weaveMag = 30;
                                despawnHit = true;
                                hitEffect = ExoFx.colorBombSmall;
                            }});
                            fragRandomSpread = 0f;
                            fragBullets = 1;
                            fragBullet = new LaserBulletType() {{
                                damage = 20f;
                                sideWidth = 0f;
                                width = 25f;
                                length = 80f;
                                hitColor = ExoPal.erekirPink;
                                colors = new Color[]{ExoPal.erekirPink.cpy().a(0.4f), ExoPal.erekirPink, Color.white};
                            }};
                        }};
                    }};
                }};
            }});
            weapons.add(new RepairBeamWeapon("exogenesis-rhea-mount") {{
                mirror = rotate = true;
                alternate = controllable = false;
                rotateSpeed = 5;
                y = 0;
                x = 61.75f;
                laserColor = healColor = ExoPal.erekirPink;
                targetBuildings = true;
                targetUnits = false;
                beamWidth = 1f;
                repairSpeed = 0.9f;
                fractionRepairSpeed = 0.03f;
                shootY = 10;
                shootCone = 15;
                pulseRadius = 10;
                pulseStroke = 3;
                bullet = new BulletType() {{
                    shootEffect = new WaveEffect() {{
                        colorFrom = Color.valueOf("ffcbdd");
                        colorTo = ExoPal.erekirPink;
                        sizeFrom = 0;
                        sizeTo = 7f;
                        lifetime = 25f;
                        strokeTo = 4;
                        strokeFrom = 0f;
                    }};
                    maxRange = 260f;
                }};
            }});
            weapons.add(new RepairBeamWeapon("exogenesis-rhea-mount") {{
                mirror = rotate = true;
                alternate = controllable = false;
                rotateSpeed = 5;
                y = 14;
                x = 41.75f;
                laserColor = healColor = ExoPal.erekirPink;
                targetBuildings = true;
                targetUnits = false;
                beamWidth = 1f;
                repairSpeed = 0.9f;
                fractionRepairSpeed = 0.03f;
                shootY = 10;
                shootCone = 15;
                pulseRadius = 10;
                pulseStroke = 3;
                bullet = new BulletType() {{
                    shootEffect = new WaveEffect() {{
                        colorFrom = Color.valueOf("ffcbdd");
                        colorTo = ExoPal.erekirPink;
                        sizeFrom = 0;
                        sizeTo = 7f;
                        lifetime = 25f;
                        strokeTo = 4;
                        strokeFrom = 0f;
                    }};
                    maxRange = 260f;
                }};
            }});
            weapons.add(new RepairBeamWeapon("exogenesis-rhea-mount") {{
                mirror = rotate = true;
                alternate = controllable = false;
                rotateSpeed = 5;
                y = 35;
                x = 27f;
                laserColor = healColor = ExoPal.erekirPink;
                targetBuildings = true;
                targetUnits = false;
                beamWidth = 1f;
                repairSpeed = 0.9f;
                fractionRepairSpeed = 0.03f;
                shootY = 10;
                shootCone = 15;
                pulseRadius = 10;
                pulseStroke = 3;
                bullet = new BulletType() {{
                    shootEffect = new WaveEffect() {{
                        colorFrom = Color.valueOf("ffcbdd");
                        colorTo = ExoPal.erekirPink;
                        sizeFrom = 0;
                        sizeTo = 7f;
                        lifetime = 25f;
                        strokeTo = 4;
                        strokeFrom = 0f;
                    }};
                    maxRange = 260f;
                }};
            }});
        }};
        //end
        ursa = new ExoUnitType("ursa", 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 0.2f) {{
            constructor = LegsUnit::create;
            speed = 0.27f;
            hitSize = 37f;
            health = 37000f;
            outlineRadius = 5;
            faceTarget = true;
            armor = 10;
            shadowElevation = 0.23f;
            targetAir = false;
            allowLegStep = true;
            hovering = true;

            legPhysicsLayer = false;
            legGroupSize = 4;
            legCount = 8;
            legContinuousMove = true;
            rippleScale = 1.2f;
            stepShake = 1.5f;
            rotateSpeed = 1.3f;
            legLength = 29f;
            legBaseOffset = 21f;
            legMoveSpace = 0.7f;

            groundLayer = Layer.legUnit;
            abilities.add(new TurretShield() {{
                radius = hitSize + 32f;
                angle = 180;
                regen = 3f;
                cooldown = 60f * 10f;
                max = 10000f;
                width = 34f;
                drawWidth = 17.0f;
                whenShooting = true;
            }});
            weapons.add(new Weapon("exogenesis-ursa-weapon") {{
                shootSound = ExoSounds.jupiterShoot;
                soundPitchMin = 1f;
                top = false;
                mirror = true;
                alternate = true;
                layerOffset = -0.001f;
                shootY = 24.75f;
                shootX = 1;
                x = 33;
                y = 0;
                reload = 80f;
                recoil = 5f;
                inaccuracy = 3f;
                shoot.shots = 3;
                shoot.shotDelay = 5f;
                bullet = new ExoBasicBulletType(7f, 140) {{
                    width = 10f;
                    height = 28f;
                    shrinkY = shrinkX = 0;
                    sprite = "circle-bullet";
                    trailLength = 8;
                    trailWidth = 2.5f;
                    lifetime = 47f;
                    weaveMag = 0.5f;
                    weaveScale = 10;
                    shootEffect = Fx.shootBig;
                    smokeEffect = ExoFx.randLifeSparkExo;
                    hitEffect = ExoFx.empyreanStarHitSmall;
                    despawnEffect = ExoFx.blastExplosionColor;

                    suppressionRange = 40f;
                    suppressionDuration = 60f * 8f;
                    suppressionEffectChance = 50;
                    damageType = energy;
                    collidesTeam = true;
                    healPercent = 20;
                    bulletInterval = 5f;
                    intervalBullet = new ChainLightningBulletType() {{
                        lightningColor = hitColor = Pal.heal;
                        damageType = energy;
                        range = 50;
                        targetRange = 85;
                        damage = 30;
                        width = 5;
                        distanceDamageFalloff = 4;
                        chainLightning = 2;
                        segmentLength = 6;
                    }};
                    lightning = 3;
                    lightningLength = 6;
                    lightningColor = hitColor = trailColor = backColor = Pal.heal;
                    lightningDamage = 40;
                }};
            }});
            weapons.add(new Weapon("exogenesis-big-heal-gun") {{
                x = 12.0f;
                y = 17.0f;
                rotateSpeed = 3f;
                reload = 50f;
                shootSound = Sounds.bolt;
                rotate = true;
                recoil = 3f;
                shootY = 4.0f;
                bullet = new FancyLaserBulletType() {{
                    damage = 135f;
                    sideAngle = 45f;
                    sideWidth = 1f;
                    sideLength = 50f;
                    healPercent = 10f;
                    collidesTeam = true;
                    damageType = energy;
                    width = 20f;
                    length = 120f;
                    hitColor = lightningColor = Pal.heal;
                    shootEffect = Fx.colorSpark;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                }};
            }});
            weapons.add(new Weapon("exogenesis-big-heal-gun") {{
                x = 23.5f;
                y = 9.25f;
                rotateSpeed = 3f;
                reload = 50f;
                shootSound = Sounds.bolt;
                rotate = true;
                recoil = 3f;
                shootY = 4.0f;
                bullet = new FancyLaserBulletType() {{
                    damage = 135f;
                    sideAngle = 45f;
                    sideWidth = 1f;
                    sideLength = 50f;
                    healPercent = 10f;
                    collidesTeam = true;
                    damageType = energy;
                    width = 20f;
                    length = 120f;
                    hitColor = lightningColor = Pal.heal;
                    shootEffect = Fx.colorSpark;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                }};
            }});
        }};
        ullr = new ExoUnitType("ullr", 1.5f, 1f, 1f, 0.05f, 0.8f, 1f, 1.8f) {{
            constructor = LegsUnit::create;
            speed = 0.20f;
            hitSize = 56f;
            health = 65000f;
            outlineRadius = 6;
            faceTarget = singleTarget = true;
            armor = 15;
            shadowElevation = 0.3f;
            allowLegStep = hovering = true;
            rotateSpeed = 1.6f;
            legSpeed = 0.6f;
            legLength = 33;
            legCount = 4;
            legMoveSpace = 0.8f;
            lockLegBase = true;
            legContinuousMove = false;
            legMaxLength = 36;
            rippleScale = 6.8f;
            legGroupSize = 1;
            legPairOffset = 1;
            legBaseOffset = 20;
            legSplashDamage = 156;
            legSplashRange = 60;
            groundLayer = 76;
            abilities.add(new TurretShield() {{
                cooldown = 60f * 6f;
                radius = 80;
                angle = 180;
                regen = 1;
                rotateSpeed = 2f;
                y = 32.75f;
                max = 15000;
            }});

            weapons.add(new Weapon("ullr") {{
                reload = 1000f;
                mirror = false;
                x = 0;
                y = 2f;
                shootY = 0;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 760;
                shoot.firstShotDelay = 380;
                shootSound = ExoSounds.funnylaserloop;
                recoilTime = 285;
                cooldownTime = 105;
                continuous = true;
                recoil = 0;
                shake = 1f;
                parts.addAll(
                        // weapon parts
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.supernovaSpark;
                            progress = PartProgress.reload;
                            effectColor = Pal.heal;
                            randomEffectRot = 360;
                            effectChance = 0.5f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.ullrChargeEffect;
                            progress = PartProgress.charge;
                            effectColor = Pal.heal;
                            randomEffectRot = 360;
                            effectChance = 0.2f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.ullrChargeEffect;
                            progress = PartProgress.charge.delay(0.95f);
                            effectColor = Pal.heal;
                            randomEffectRot = 360;
                            effectChance = 0.45f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.ullrChargeEffect;
                            progress = PartProgress.charge.delay(0.9f);
                            effectColor = Pal.heal;
                            randomEffectRot = 360;
                            effectChance = 0.75f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.ullarTipHit;
                            progress = PartProgress.recoil;
                            effectColor = Pal.heal;
                            randomEffectRot = 0;
                            effectChance = 0.5f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            y = 0f;
                            effect = ExoFx.hitSparkHuge;
                            progress = PartProgress.recoil;
                            effectColor = Pal.heal;
                            randomEffectRot = 0;
                            effectChance = 0.1f;
                        }},
                        new FlarePart() {{
                            progress = PartProgress.recoil;
                            color1 = Pal.heal;
                            color2 = Color.white;
                            spinSpeed = 0.6f;
                            radius = 0f;
                            stroke = 5;
                            radiusTo = 280f;
                            layer = 109;
                            y = 0;
                        }},
                        new FlarePart() {{
                            progress = PartProgress.heat;
                            color1 = Pal.heal;
                            color2 = Color.white;
                            sides = 2;
                            rotation = 90;
                            followRotation = true;
                            radius = 0f;
                            stroke = 12;
                            radiusTo = 380f;
                            layer = 109;
                            y = 0;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Color.white;
                            circle = true;
                            radius = 6f;
                            radiusTo = 0f;
                            layer = 114;
                            y = 0f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            color = Pal.heal;
                            circle = true;
                            radius = 10;
                            radiusTo = 0;
                            layer = Layer.effect;
                            y = 0f;
                        }}
                );
                bullet = new AcceleratingLaserBulletType(260f) {{
                    lifetime = 280f;
                    maxLength = 830f;
                    maxRange = 830f;
                    oscOffset = 0.3f;
                    shootEffect = ExoFx.blastcolor;
                    chargeEffect = new MultiEffect(ExoFx.ullrChargeBegin, ExoFx.ullrChargeEffect);
                    width = 30f;
                    damageType = energy;
                    collisionWidth = 10f;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                    pierceCap = 3;
                    hitEffect = ExoFx.ullarTipHit;
                    hitColor = Pal.heal;
                }};
            }});
            weapons.add(new Weapon("exogenesis-energy-mount") {{
                reload = 82f;
                mirror = true;
                rotate = true;
                rotateSpeed = 1.5f;
                x = 27;
                y = 0;
                shootY = 9;
                shoot = new ShootPattern() {{
                    shotDelay = 7f;
                    shots = 4;
                }};
                shootSound = Sounds.bolt;
                shake = 1f;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = Pal.heal;
                    damageType = energy;
                    range = 150;
                    targetRange = 20;
                    damage = 90;
                    width = 6;
                    distanceDamageFalloff = 4;
                    chainLightning = 5;
                    segmentLength = 6;
                }};
            }});
        }};

        empire = new ExoUnitType("empire", 0.2f, 0.8f, 1.5f, 1f, 1f, 1f, 1f) {{
            constructor = MechUnit::create;
            speed = 0.35f;
            hitSize = 49f;
            rotateSpeed = 1.5f;
            health = 78000f;
            outlineRadius = 5;
            armor = 35f;
            mechStepParticles = singleTarget = true;
            stepShake = 1f;
            canDrown = false;
            mechFrontSway = 2f;
            mechSideSway = 0.7f;
            mechStride = (4f + (hitSize - 8f) / 2.1f) / 1.25f;
            immunities.add(StatusEffects.blasted);
            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                layerOffset = -0.001f;
                x = 35.25f;
                y = 0f;
                rotate = true;
                rotationLimit = 50;
                rotateSpeed = 1.4f;

                shootY = 30.25f;
                cooldownTime = 100;
                reload = 4f;
                recoil = 1f;
                shake = 2f;
                shoot = new ShootMulti(new ShootAlternate() {{
                    spread = 1.1f;
                    barrels = 5;
                }}, new ShootPattern() {{
                    shots = 3;
                    shotDelay = 1;
                }});
                inaccuracy = 9;
                velocityRnd = 0.2f;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.shootBig;
                bullet = new ExoBasicBulletType(25f, 80f) {{
                    lifetime = 17f;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    shootEffect = Fx.shootBig;
                    damageType = DamageType.kinetic;
                    trailWidth = 3f;
                    trailLength = 8;
                    width = 10f;
                    height = 17f;
                    shrinkY = 0f;
                    shrinkX = 0f;
                    pierceArmor = true;
                    pierceCap = 1;
                }};
            }});
        }};
        heimdall = new ExoUnitType("heimdall", 0.05f, 0.5f, 2f, 1f, 1.2f, 1f, 1) {{
            constructor = MechUnit::create;
            speed = 0.25f;
            hitSize = 49f;
            rotateSpeed = 1.25f;
            health = 180000f;
            outlineRadius = 6;
            armor = 80f;
            mechStepParticles = true;
            singleTarget = true;
            stepShake = 3f;
            canDrown = targetAir = false;
            mechStride = (4f + (hitSize - 8f) / 2.1f) / 1.3f;
            immunities.addAll(StatusEffects.blasted, StatusEffects.melting);
            abilities.add(new MoveLightningAbility(10, -1, 1f, 12, 0.33f, 4, Pal.meltdownHit, "exogenesis-bash-heat") {{
                display = false;
                bullet = new ExoBasicBulletType(22, 0) {{
                    lifetime = 2;
                    damageType = kinetic;
                    hitEffect = despawnEffect = shootEffect = smokeEffect = Fx.none;
                    width = height = 0f;
                    despawnShake = 4;
                    knockback = 10;
                    impact = true;
                    hitShake = 7;
                    scaledSplashDamage = true;
                    splashDamage = 250;
                    splashDamageRadius = 70;
                }};
                color = Pal.turretHeat;
                shootSound = Sounds.none;
            }});
            weapons.add(new Weapon(name + "-weapon") {{
                x = 36.5f;
                y = 2.75f;
                shootY = 26;
                top = true;
                layerOffset = -0.001f;
                alternate = true;
                rotate = true;
                recoil = 6;
                rotationLimit = 50;
                rotateSpeed = 1f;
                reload = 30f;
                shootCone = 60f;
                velocityRnd = 0.35f;
                inaccuracy = 23;
                ejectEffect = ExoFx.casing5;
                shootSound = Sounds.shotgun;
                shoot.shots = 18;
                bullet = new ExoBasicBulletType(10f, 250f) {{
                    lifetime = 18f;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    shootEffect = Fx.shootBig;
                    damageType = explosive;
                    status = StatusEffects.blasted;
                    statusDuration = 130;
                    trailWidth = 4f;
                    trailLength = 8;
                    knockback = 6;
                    impact = true;
                    width = 16f;
                    height = 26f;
                    shrinkY = 0f;
                    shrinkX = 0f;
                    pierceArmor = true;
                    pierceBuilding = false;
                    pierceCap = 1;
                }};
            }});
            weapons.add(new Weapon("enginemain") {{
                parentizeEffects = continuous = ignoreRotation = true;
                alternate = display = rotate = false;
                baseRotation = 180;
                alwaysShootWhenMoving = false;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 140;
                shootCone = 360;
                parts.addAll(
                        new RegionPart("-engine") {{
                            mirror = false;
                            layer = 109;
                            color = Color.valueOf("000000");
                            colorTo = Pal.meltdownHit;
                            blending = Blending.additive;
                            outline = false;
                            progress = PartProgress.recoil;
                        }}
                );
                x = 0;
                y = -10;
                shootY = 0;
                reload = 400;
                shootSound = Sounds.none;
                bullet = new ContinuousFlameBulletType() {{
                    maxRange = 150;
                    lifetime = 130;
                    damage = 4;
                    width = 8.3f;
                    layer = 110;
                    drawFlare = collides = collidesTiles = collidesGround = collidesAir = false;
                    recoil = 0.5f;
                    length = 60;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    intervalBullet = new BulletType() {{
                        despawnHit = true;
                        despawnEffect = Fx.none;
                        instantDisappear = true;
                        hitEffect = new ParticleEffect() {{
                            particles = 1;
                            line = true;
                            layer = 108;
                            length = 105f;
                            lifetime = 25f;
                            baseLength = 8;
                            cone = 65;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Color.valueOf("ff9c5a");
                            strokeFrom = 2.5f;
                            lenFrom = 9;
                            lenTo = 0f;
                        }};
                    }};
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
            weapons.add(new Weapon("engine-1") {{
                parentizeEffects = continuous = alwaysContinuous = ignoreRotation = true;
                alternate = display = rotate = false;
                minShootVelocity = 0.22f;
                alwaysShootWhenMoving = true;
                mirror = true;
                baseRotation = 147.7f;
                x = -12.5f;
                y = -8;
                shootCone = 360;
                shootY = 0;
                shootSound = Sounds.none;
                bullet = new ContinuousFlameBulletType() {{
                    recoil = 0.1f;
                    maxRange = 150;
                    lifetime = 90;
                    damage = 4;
                    width = 6.3f;
                    layer = Layer.effect;
                    drawFlare = collides = collidesTiles = collidesGround = collidesAir = false;
                    length = 30;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    intervalBullet = new BulletType() {{
                        despawnHit = true;
                        despawnEffect = Fx.none;
                        instantDisappear = true;
                        hitEffect = new ParticleEffect() {{
                            particles = 1;
                            line = true;
                            layer = 108;
                            length = 45f;
                            lifetime = 25f;
                            baseLength = 8;
                            cone = 25;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Color.valueOf("ff9c5a");
                            strokeFrom = 2;
                            lenFrom = 6;
                            lenTo = 0f;
                        }};
                    }};
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
            weapons.add(new Weapon("engine-2") {{
                parentizeEffects = continuous = alwaysContinuous = ignoreRotation = true;
                alternate = display = rotate = false;
                minShootVelocity = 0.22f;
                alwaysShootWhenMoving = true;
                mirror = true;
                baseRotation = 162.7f;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 200;
                shootCone = 360;
                x = -19.5f;
                y = -6.25f;
                shootY = 0;
                shootSound = Sounds.none;
                bullet = new ContinuousFlameBulletType() {{
                    maxRange = 150;
                    lifetime = 90;
                    recoil = 0.1f;
                    damage = 4;
                    width = 3.3f;
                    layer = Layer.effect;
                    drawFlare = collides = collidesTiles = collidesGround = collidesAir = false;
                    length = 13;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    intervalBullet = new BulletType() {{
                        despawnHit = true;
                        despawnEffect = Fx.none;
                        instantDisappear = true;
                        hitEffect = new ParticleEffect() {{
                            particles = 1;
                            line = true;
                            layer = 108;
                            length = 45f;
                            lifetime = 25f;
                            baseLength = 8;
                            cone = 25;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Color.valueOf("ff9c5a");
                            strokeFrom = 2;
                            lenFrom = 6;
                            lenTo = 0f;
                        }};
                    }};
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
        }};

        avicularia = new ExoUnitType("avicularia", 1f, 1f, 1f, 1f, 1f, 1.5f, 0.4f) {{
            groundLayer = Layer.legUnit;
            constructor = LegsUnit::create;
            drag = 0.1f;
            speed = 0.42f;
            hitSize = 35.5f;
            health = 52000;
            outlineRadius = 5;
            rotateSpeed = 1.3f;
            legContinuousMove = true;
            legCount = 8;
            legMoveSpace = 0.76f;
            legPairOffset = 0.7f;
            legGroupSize = 2;
            legLength = 112f;
            legExtension = -8.25f;
            legBaseOffset = 8f;
            stepShake = 2.4f;
            legLengthScl = 1f;
            rippleScale = 2f;
            legSpeed = 0.2f;

            legSplashDamage = 80f;
            legSplashRange = 40f;
            hovering = true;
            armor = 13f;
            allowLegStep = true;
            shadowElevation = 0.95f;
            weapons.add(new Weapon("exogenesis-sap-energy-gun") {{
                reload = 52f;
                mirror = true;
                rotate = true;
                rotateSpeed = 2;
                x = 19;
                y = 3;
                shoot = new ShootSpread() {{
                    spread = 15f;
                    shots = 3;
                }};
                shootSound = Sounds.blaster;
                recoil = 3;
                shake = 1f;
                bullet = new ExoLaserBulletType() {{
                    damage = 115f;
                    sideWidth = 0f;
                    sideLength = 0f;
                    lightningSpacing = 17f;
                    lightningDelay = 0.12f;
                    lightningDamage = 10f;
                    lightningLength = 4;
                    lightningLengthRand = 2;
                    lightningAngleRand = 15f;
                    damageType = energy;
                    width = 42f;
                    length = 140f;
                    hitColor = lightningColor = Pal.sapBullet;
                    shootEffect = ExoFx.colorBombSmall;
                    colors = new Color[]{Pal.sapBulletBack.cpy().a(0.4f), Pal.sapBullet, Color.white};
                }};
            }});
            weapons.add(new Weapon("exogenesis-avicularia-weapon") {{
                reload = 170f;
                mirror = false;
                x = 0;
                y = -17;
                shootSound = Sounds.torch;
                shootY = 21;
                recoil = 2;
                rotateSpeed = 2;
                rotate = continuous = true;
                shake = 1f;
                bullet = new ExoContinuousFlameBulletType() {{
                    hitColor = Pal.sapBullet;
                    drawFlare = false;
                    damage = 35f;
                    lifetime = 430;
                    damageType = DamageType.energy;
                    length = 165f;
                    hitEffect = ExoFx.hitMeltColor;
                    drawSize = 420f;
                    width = 8.6f;
                    shake = 1f;
                    largeHit = true;
                    colors = new Color[]{Pal.sapBulletBack.cpy().a(0.4f), Pal.sapBullet, Color.white};
                    despawnEffect = Fx.smokeCloud;
                    intervalBullet = new ExoLightningBulletType() {{
                        damage = 30;
                        damageType = DamageType.energy;
                        collidesAir = false;
                        ammoMultiplier = 1f;
                        lightningColor = Pal.sapBullet;
                        lightningLength = 20;
                        lightningLengthRand = 28;
                    }};
                    intervalRandomSpread = 20;
                    intervalBullets = 1;
                    bulletInterval = 6f;
                    smokeEffect = Fx.none;
                    shootEffect = Fx.none;
                }};
            }});
        }};
        vidar = new ExoUnitType("vidar", 1f, 1f, 1f, 1f, 1.5f, 2f, 0.05f) {{
            constructor = LegsUnit::create;
            speed = 0.4f;
            drag = 0.12f;
            hitSize = 49f;
            hovering = true;
            allowLegStep = true;
            health = 78750;
            outlineRadius = 6;
            armor = 18f;
            rotateSpeed = 1.3f;
            legCount = 10;
            legGroupSize = 2;
            legMoveSpace = 0.7f;
            legPairOffset = 0.2f;
            legLength = 176f;
            legExtension = -24f;
            legBaseOffset = 9f;
            shadowElevation = 1f;
            groundLayer = Layer.legUnit + 0.02f;
            rippleScale = 3.4f;
            legSplashDamage = 130f;
            legSplashRange = 60f;
            targetAir = false;

            weapons.add(new Weapon(name + "-purple-mount") {{
                            x = 15f;
                            y = 8.75f;
                            shootY = 9.25f;
                            reload = 57f;
                            recoil = 3f;
                            rotate = true;
                            shootCone = 20f;
                            shootSound = Sounds.shootBig;
                            shoot = new ShootSpread(4, 14f);
                            parts.addAll(
                                    new RegionPart("-glow") {{
                                        color = Color.purple;
                                        colorTo = Color.purple;
                                        blending = Blending.additive;
                                        outline = mirror = false;
                                    }});
                            bullet = new TentacleBulletType(15) {{
                                length = 250f;
                                width = 15f;
                                segments = 14;
                                lifetime = 35;
                                damageType = radiation;
                                fromColor = Color.white;
                                toColor = Pal.sapBullet;
                                shootEffect = Fx.blastExplosion;
                                smokeEffect = Fx.sparkShoot;
                            }};
                        }},
                    new Weapon(name + "-plasma-gunner") {{
                        x = 36.25f;
                        y = 1.25f;
                        shootY = 6.25f - 1f;
                        reload = 7f;
                        recoil = 1f;
                        rotate = true;
                        shootCone = 20f;
                        inaccuracy = 25f;
                        shoot.shots = 2;
                        shootSound = Sounds.missile;

                        bullet = new ExoMissileBulletType() {{
                            width = 10f;
                            height = 12f;
                            shrinkY = 0f;
                            speed = 3.7f;
                            damage = 15;
                            damageType = DamageType.explosive;
                            drag = -0.01f;
                            splashDamageRadius = 30f;
                            splashDamage = 55f;
                            ammoMultiplier = 5f;
                            hitEffect = Fx.blastExplosion;
                            despawnEffect = Fx.blastExplosion;
                            backColor = trailColor = Pal.sapBulletBack;
                            frontColor = lightningColor = lightColor = Pal.sapBullet;
                            trailLength = 13;
                            homingRange = 80f;
                            weaveScale = 8f;
                            weaveMag = 2f;
                            lightning = 2;
                            lightningLength = 2;
                            lightningLengthRand = 1;
                            lightningCone = 15f;

                            status = StatusEffects.blasted;
                            statusDuration = 60f;
                        }};
                    }},
                    new Weapon(name + "-laser") {{
                        x = 26.25f;
                        y = -2.25f;
                        shootY = 20.5f - 4f;
                        shootSound = Sounds.cannon;
                        parts.addAll(
                                new RegionPart("-cell") {{
                                    mirror = false;
                                    under = true;
                                    recoilIndex = 2;
                                    cooldownTime = 150;
                                    heatProgress = PartProgress.recoil;
                                    progress = PartProgress.recoil;
                                    moveY = -2.4f;
                                    moveX = 2.5f;
                                }}
                        );
                        rotate = true;
                        alternate = true;
                        drawCell = false;
                        rotateSpeed = 0.9f;
                        cooldownTime = 120f;
                        reload = 90f;
                        shake = 6f;
                        recoil = 8f;
                        bullet = new ExoArtilleryBulletType() {{
                            width = height = 30f;
                            damageType = DamageType.explosive;
                            splashDamage = 200;
                            damage = 100;
                            splashDamageRadius = 70;
                            sprite = "shell";
                            frontColor = Color.white;
                            backColor = hitColor = trailColor = Pal.sapBulletBack;
                            speed = 3;
                            collidesTiles = collides = true;
                            scaleLife = true;
                            lifetime = 120f;
                            hitEffect = despawnEffect = Fx.sapExplosion;
                            shootEffect = Fx.sapExplosion;
                            trailChance = 0.6f;
                            trailSize = 5;
                            trailLength = 10;
                            trailWidth = 5f;
                            hitShake = 10f;
                            lightRadius = 40f;
                            lightColor = Pal.sap;
                            lightOpacity = 0.6f;

                            status = StatusEffects.sapped;
                            statusDuration = 60f * 10;
                            fragLifeMin = 0.3f;
                            fragBullets = 14;
                            fragBullet = new ExoArtilleryBulletType() {{
                                hitEffect = Fx.sapExplosion;
                                knockback = 0.8f;
                                lifetime = 120f;
                                damageType = DamageType.explosive;
                                damage = 30;
                                speed = 2.3f;
                                width = height = 25f;
                                collidesTiles = false;
                                splashDamageRadius = 70f;
                                splashDamage = 70f;
                                backColor = Pal.sapBulletBack;
                                frontColor = lightningColor = Pal.sapBullet;
                                lightning = 2;
                                lightningLength = 5;
                                smokeEffect = Fx.shootBigSmoke2;
                                hitShake = 5f;
                                lightRadius = 30f;
                                lightColor = Pal.sap;
                                lightOpacity = 0.5f;

                                status = StatusEffects.sapped;
                                statusDuration = 60f * 10;
                            }};
                        }};
                    }});
        }};

        twilight = new ExoUnitType("twilight", 0.6f, 0.8f, 1.5f, 1f, 1.5f, 1f, 1f) {{
            constructor = UnitEntity::create;
            shadowElevation = 1.3f;
            health = 54000f;
            outlineRadius = 5;
            outlineColor = Color.valueOf("50505f");
            armor = 17f;
            speed = 0.45f;
            accel = 0.04f;
            drag = 0.04f;
            flying = true;
            hitSize = 80f;
            rotateSpeed = 1;
            engineOffset = 38.25f;
            engineSize = 5.75f;
            faceTarget = true;
            lowAltitude = true;
            weapons.add(new Weapon("exogenesis-twilight-mount") {{
                            reload = 10f;
                            mirror = true;
                            alternate = false;
                            rotate = true;
                            rotationLimit = 90;
                            layerOffset = 1;
                            rotateSpeed = 2.5f;
                            x = 24;
                            y = 1;
                            shootY = 13;
                            inaccuracy = 2;
                            shoot = new ShootMulti(new ShootAlternate() {{
                                spread = 4f;
                            }}, new ShootPattern() {{
                                shots = 2;
                                shotDelay = 2f;
                            }});
                            shootSound = Sounds.shootBig;
                            recoil = 4;
                            shake = 1f;
                            bullet = new ExoBasicBulletType(9f, 90) {{
                                width = 11f;
                                height = 20f;
                                damageType = DamageType.kinetic;
                                lifetime = 42f;
                                shootEffect = Fx.shootBig;
                                hitEffect = Fx.blastExplosion;
                                despawnHit = true;
                                trailLength = 6;
                                trailWidth = 2f;
                                lightning = 2;
                                lightningLength = 6;
                                lightningColor = Pal.surge;
                                lightningDamage = 20;
                            }};
                        }},
                    new Weapon("exogenesis-small-assualt-weapon") {{
                        x = 9.75f;
                        y = 24.5f;
                        rotateSpeed = 3f;
                        reload = 80f;
                        shootSound = Sounds.laser;
                        rotate = true;
                        recoil = 3f;
                        shootY = 7.25f;
                        bullet = new ExoLaserBulletType() {{
                            damage = 115f;
                            sideAngle = -90f;
                            sideWidth = 1.5f;
                            sideLength = 30f;
                            damageType = thermal;
                            width = 16f;
                            length = 140f;
                            hitColor = lightningColor = Color.valueOf("ff9c5a");
                            shootEffect = ExoFx.colorBombSmall;
                            colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                        }};
                    }},
                    new Weapon("exogenesis-small-assualt-weapon") {{
                        x = 16.75f;
                        y = 8.25f;
                        rotateSpeed = 3f;
                        reload = 80f;
                        shootSound = Sounds.laser;
                        rotate = true;
                        recoil = 3f;
                        shootY = 7.25f;
                        bullet = new ExoLaserBulletType() {{
                            damage = 115f;
                            sideAngle = -90f;
                            sideWidth = 1.5f;
                            sideLength = 30f;
                            damageType = thermal;
                            width = 16f;
                            length = 140f;
                            hitColor = lightningColor = Color.valueOf("ff9c5a");
                            shootEffect = ExoFx.colorBombSmall;
                            colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                        }};
                    }},
                    new Weapon("exogenesis-small-assualt-weapon") {{
                        x = 12.0f;
                        y = -16.25f;
                        rotateSpeed = 3f;
                        reload = 80f;
                        shootSound = Sounds.laser;
                        rotate = true;
                        recoil = 3f;
                        shootY = 7.25f;
                        bullet = new ExoLaserBulletType() {{
                            damage = 115f;
                            sideAngle = -90f;
                            sideWidth = 1.5f;
                            sideLength = 30f;
                            damageType = thermal;
                            width = 16f;
                            length = 140f;
                            hitColor = lightningColor = Color.valueOf("ff9c5a");
                            shootEffect = ExoFx.colorBombSmall;
                            colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                        }};
                    }});
        }};
        odin = new ExoUnitType("odin", 0.3f, 0.05f, 1.5f, 1f, 1.6f, 1f, 1f) {{
            constructor = UnitEntity::create;
            shadowElevation = 3.8f;
            health = 94000f;
            outlineRadius = 6;
            outlineColor = Color.valueOf("50505f");
            armor = 26f;
            speed = 3.35f;
            accel = 0.011f;
            drag = 0.01f;
            targetAir = false;
            flying = true;
            rotateSpeed = 1.3f;
            hitSize = 100f;

            engineSize = 0f;
            targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.generator, null};
            circleTarget = true;
            faceTarget = false;
            lowAltitude = false;

            immunities.addAll(StatusEffects.blasted, ExoStatusEffects.superBlasted, StatusEffects.melting);
            weapons.add(new Weapon("odin-Nuke") {{
                x = y = 0f;
                mirror = false;
                reload = 125f;
                minShootVelocity = 0.75f;
                soundPitchMin = 1f;
                shootSound = Sounds.plasmadrop;
                bullet = new ExoBasicBulletType(0, 1) {{
                    sprite = "large-bomb";
                    width = height = 65f;
                    maxRange = 30f;
                    ignoreRotation = true;
                    damageType = explosive;

                    backColor = hitColor = Color.valueOf("ffa665");
                    frontColor = Color.white;
                    mixColorTo = Color.white;
                    status = ExoStatusEffects.superBlasted;
                    statusDuration = 300f;
                    hitSound = Sounds.largeExplosion;
                    shootCone = 180f;
                    ejectEffect = Fx.none;
                    hitShake = 24f;
                    collidesAir = false;
                    lifetime = 130f;

                    hitEffect = new MultiEffect(ExoFx.odinNukeStar, ExoFx.odinNukeExplosion, ExoFx.odinNukeShockWave, Fx.massiveExplosion);
                    keepVelocity = false;
                    spin = 2f;
                    shrinkX = shrinkY = 0.7f;
                    speed = 0f;
                    collides = false;
                    scaledSplashDamage = true;
                    splashDamage = 3420f;
                    splashDamageRadius = 120f;
                    fragLifeMin = 0.1f;
                    fragBullets = 18;
                    fragBullet = new ExoArtilleryBulletType() {{
                        damageType = explosive;
                        drag = 0.02f;
                        speed = 3.4f;
                        damage = 32;
                        hitEffect = Fx.massiveExplosion;
                        despawnEffect = Fx.scatheSlash;
                        knockback = 0.8f;
                        lifetime = 53f;
                        width = height = 18f;
                        collidesTiles = false;
                        splashDamageRadius = 40f;
                        splashDamage = 80f;
                        backColor = trailColor = hitColor = Color.valueOf("ffa665");
                        frontColor = Color.white;
                        smokeEffect = Fx.shootBigSmoke2;
                        despawnShake = 7f;
                        lightRadius = 30f;
                        lightColor = ExoPal.cronusRed;
                        lightOpacity = 0.5f;
                        trailLength = 20;
                        trailWidth = 3.5f;
                        trailEffect = Fx.none;
                    }};
                }};
            }});
            weapons.add(new Weapon("odin-carpet-bombs") {{
                minShootVelocity = 0.75f;
                y = -13f;
                shootY = 0f;
                reload = 132f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                inaccuracy = 15f;
                xRand = 50;
                shoot = new ShootPattern() {{
                    shots = 60;
                    shotDelay = 2;
                }};
                ignoreRotation = true;
                shootSound = Sounds.mineDeploy;
                bullet = new ExoBombBulletType(77f, 45f) {{
                    width = 15f;
                    height = 18f;
                    damageType = explosive;
                    sprite = "shell";
                    hitEffect = Fx.massiveExplosion;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    incendChance = 0.5f;
                    incendAmount = 10;
                    incendSpread = 6;
                    trailLength = 5;
                    trailWidth = 2f;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }};
            }});
            //engines
            weapons.add(new Weapon("engine-main") {{
                alwaysContinuous = parentizeEffects = continuous = alwaysShooting = true;
                display = rotate = mirror = false;
                baseRotation = 180;
                x = 0;
                y = -36;
                shootY = 0;
                shootSound = Sounds.none;
                parts.addAll(
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.recoil;
                            effect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                layer = 108;
                                length = 45f;
                                lifetime = 31f;
                                baseLength = 8;
                                cone = 50;
                                interp = Interp.circleOut;
                                colorFrom = colorTo = Pal.meltdownHit;
                                strokeFrom = 2;
                                lenFrom = 10;
                                lenTo = 0f;
                            }};
                            randomEffectRot = 5f;
                            effectChance = 0.8f;
                        }});
                bullet = new ContinuousFlameBulletType() {{
                    damage = width = 9f;
                    layer = Layer.effect;
                    drawFlare = collides = false;
                    length = 16;
                    divisions = 20;
                    intervalBullets = 2;
                    intervalRandomSpread = 1;
                    bulletInterval = 2.7f;
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
            weapons.add(new Weapon("engines-secondary") {{
                alwaysContinuous = parentizeEffects = continuous = alwaysShooting = true;
                display = rotate = false;
                mirror = true;
                alternate = false;
                baseRotation = 180;
                x = 25;
                y = -29;
                shootY = 0;
                shootSound = Sounds.none;
                parts.addAll(
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.recoil;
                            effect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                layer = 108;
                                length = 45f;
                                lifetime = 31f;
                                baseLength = 8;
                                cone = 10;
                                interp = Interp.circleOut;
                                colorFrom = colorTo = Pal.meltdownHit;
                                strokeFrom = 2;
                                lenFrom = 5;
                                lenTo = 0f;
                            }};
                            randomEffectRot = 5f;
                            effectChance = 0.8f;
                        }});
                bullet = new ContinuousFlameBulletType() {{
                    damage = width = 4f;
                    layer = Layer.effect;
                    drawFlare = collides = false;
                    length = 10;
                    divisions = 20;
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
            weapons.add(new Weapon("engines-tertiary") {{
                alwaysContinuous = parentizeEffects = continuous = alwaysShooting = true;
                display = rotate = false;
                mirror = true;
                alternate = false;
                baseRotation = -135;
                x = 38;
                y = -18;
                shootY = 0;
                shootSound = Sounds.none;
                parts.addAll(
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.recoil;
                            effect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                layer = 108;
                                length = 45f;
                                lifetime = 31f;
                                baseLength = 8;
                                cone = 10;
                                interp = Interp.circleOut;
                                colorFrom = colorTo = Pal.meltdownHit;
                                strokeFrom = 2;
                                lenFrom = 5;
                                lenTo = 0f;
                            }};
                            effectRot = -90f;
                            effectChance = 0.8f;
                        }});
                bullet = new ContinuousFlameBulletType() {{
                    damage = width = 4f;
                    layer = Layer.effect;
                    drawFlare = collides = false;
                    length = 8;
                    divisions = 20;
                    colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
        }};

        pantagruel = new ExoUnitType("pantagruel", 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f){{
            constructor = UnitEntity::create;
            outlineRadius = 5;
            outlineColor = Color.valueOf("50505f");
            shadowElevation = 3;
            armor = 19f;
            health = 56000;
            speed = 0.93f;
            rotateSpeed = 0.9f;
            accel = 0.04f;
            drag = 0.018f;
            flying = true;
            engineOffset = 41.25f;
            engineSize = 7.8f;
            engineColor = Pal.heal;
            faceTarget = false;
            hitSize = 66f;
            buildSpeed = 4.5f;
            drawShields = false;
            lowAltitude = true;
            buildBeamOffset = 4;
            ammoCapacity = 1;
            parts.addAll(
                    new RegionPart("-madible-energy-tanks"){{
                        moves.add(new PartMove(PartProgress.recoil.curve(Interp.bounceIn), -5, 5, 0));
                        moves.add(new PartMove(PartProgress.recoil.curve(Interp.bounceIn), 0, -6, 0));
                        mirror = true;
                        progress = PartProgress.charge.curve(Interp.circleIn);
                        moveX = -6;
                        x = -18;
                        y = -24;
                        layerOffset = -0.0001f;
                        heatProgress = PartProgress.recoil.curve(Interp.bounceIn).shorten(0.8f);
                        heatProgress = PartProgress.charge.curve(Interp.circleIn);
                    }},
                    new RegionPart("-madible"){{
                        moves.add(new PartMove(PartProgress.recoil.curve(Interp.bounceIn), -6, 0, 0));
                        progress = PartProgress.charge.curve(Interp.circleIn);
                        moveX = -6;
                        x = -18;
                        y = -24;
                        mirror = true;
                        under = true;
                        layerOffset = -0.0001f;
                    }},
                    new RegionPart("-lines"){{
                        progress = PartProgress.charge.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.charge);
                        color = Color.valueOf("000000");
                        colorTo = Pal.heal;
                        mirror = true;
                        layerOffset = Layer.effect;
                    }},
                    new RegionPart("-arrows"){{
                        progress = PartProgress.charge.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.charge);
                        color = Color.valueOf("000000");
                        colorTo = Pal.heal;
                        mirror = false;
                        layerOffset = Layer.effect;
                    }},

                    new EffectSpawnPart() {{
                        useProgress =  false;
                        y = 38f;
                        effectColor = Pal.heal;
                        effect = ExoFx.randLifeSparkExo1;
                        randomEffectRot = 360f;
                        effectChance = 0.085f;
                    }},
                    new EffectSpawnPart() {{
                        useProgress =  false;
                        y = 38f;
                        progress = PartProgress.charge.curve(Interp.circleIn);
                        effectColor = Pal.heal;
                        effect = ExoFx.randLifeSparkExo;
                        randomEffectRot = 360f;
                        effectChance = 0.03f;
                    }}
            );
            abilities.add(new EnergyFieldAbility(40f, 65f, 0f){{
                statusDuration = 60f * 6f;
                y = 38;
                effectRadius = 3f;
                sectorRad = 0f;
                maxTargets = 0;
                healPercent = 1.5f;
                sameTypeHealMult = 0.15f;
            }});
            weapons.add(new Weapon("launch") {{
                reload = 220f;
                mirror = false;
                x = 0;
                y = 38;
                shoot.firstShotDelay = 100;
                shootStatusDuration = 110;
                chargeSound = Sounds.lasercharge;
                shootStatus = StatusEffects.unmoving;
                shootSound = Sounds.malignShoot;
                showStatSprite = false;
                recoil = 0;
                shake = 1f;
                bullet = new ExoBasicBulletType(30.5f, 185){{
                    width = height = 50;
                    recoil = 0.5f;
                    sprite = "exogenesis-plasma";
                    scaleLife = false;
                    chargeEffect = ExoFx.pentaCharge;
                    damageType = kinetic;
                    hitSound = Sounds.explosionbig;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = Pal.heal;
                    trailEffect = new Effect(13f, e -> {
                        color(Pal.heal);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 2.5f, 56f * e.fslope(), e.rotation + 90f*s);
                            Drawf.tri(e.x, e.y, 1.8f, 24f * e.fslope(), e.rotation + 50f*s);
                            Drawf.tri(e.x, e.y, 1.8f, 24f * e.fslope(), e.rotation + -50f*s);
                        }
                            Draw.z(Layer.effect);
                            Draw.color(Pal.heal, e.fout());
                            Tmp.v1.trns(e.rotation, e.fin() * 20f);
                            Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 3.8f * e.fin() + 0.1f, 10, 18, e.rotation);
                            Lines.stroke(6f * e.fout());
                    });
                    hitSize = 20;
                    pierce = true;
                    pierceBuilding = true;
                    trailRotation = true;
                    trailInterval = 3f;
                    lifetime = 165f;
                    splashDamage = 100;
                    splashDamageRadius = 20;
                    shrinkY = shrinkX = 0;
                    lightning = 7;
                    lightningLength = 9;
                    lightningColor = Pal.heal;
                    lightningDamage = 11;
                    shootEffect = Fx.lightningShoot;
                }};
            }});
            weapons.add(new PointDefenseWeapon("point-defense"){{
                mirror = false;
                x = 0f;
                y = 38f;
                reload = 6f;
                targetInterval = 6f;
                targetSwitchInterval = 8f;

                bullet = new BulletType(){{
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.shootHeal;
                    maxRange = 250f;
                    damage = 50f;
                }};
            }});
        }};

        notodoris = new UnitType("notodoris") {{
            constructor = UnitWaterMove::create;
            trailLength = 70;
            waveTrailX = 23f;
            waveTrailY = -39f;
            trailScl = 3.5f;
            health = 54000f;
            outlineRadius = 5;
            omniMovement = true;
            armor = 17f;
            speed = 0.56f;
            accel = 0.2f;
            drag = 0.4f;
            hitSize = 80f;
            rotateSpeed = 1f;
            faceTarget = false;
            weapons.add(new Weapon("exogenesis-notodoris-mount") {{
                reload = 6f;
                mirror = true;
                alternate = false;
                rotate = true;
                rotateSpeed = 2.5f;
                x = 27;
                y = -7;
                layerOffset = 1;
                inaccuracy = 1;
                recoils = 2;
                shootY = 16;
                shoot = new ShootAlternate() {{
                    spread = 8f;
                }};
                shootSound = Sounds.bolt;
                recoil = 1;
                shake = 1f;
                parts.add(
                        new RegionPart("-barrel-1") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }},
                        new RegionPart("-barrel-2") {{
                            mirror = false;
                            under = true;
                            recoilIndex = 0;
                            progress = PartProgress.recoil;
                            moveY = -4f;
                        }}
                );
                bullet = new ExoEmpBulletType() {{
                    width = 17f;
                    height = 28f;
                    speed = 9;
                    damageType = DamageType.energy;
                    damage = 170;
                    lifetime = 32f;
                    shootEffect = Fx.shootBig;
                    shrinkY = shrinkX = 0;
                    trailEffect = new Effect(13f, e -> {
                        color(Pal.heal);
                        for (int s : Mathf.signs) {
                            Drawf.tri(e.x, e.y, 2.5f, 14f * e.fslope(), e.rotation + 90f * s);
                        }
                    });
                    healAmount = 100;
                    radius = 30;
                    unitDamageScl = 0.3f;
                    trailRotation = true;
                    trailInterval = 2f;
                    trailLength = 6;
                    trailWidth = 2.6f;
                    lightning = 2;
                    lightningLength = 6;
                    lightningColor = backColor = trailColor = hitColor = Pal.heal;
                    lightningDamage = 20;
                }};
            }});
            weapons.add(new Weapon("torpedo") {{
                reload = 10f;
                mirror = true;
                alternate = false;
                x = 30;
                y = -12;
                baseRotation = -90;
                shootCone = 180;
                shoot = new ShootAlternate() {{
                    barrels = 7;
                    shots = 3;
                    spread = 2;
                }};
                shootSound = Sounds.mineDeploy;
                showStatSprite = false;
                shake = 1f;
                bullet = new ExoBasicBulletType(2f, 75) {{
                    width = height = 16f;
                    sprite = "mine-bullet";
                    maxRange = 50f;
                    ignoreRotation = true;
                    damageType = DamageType.explosive;
                    hitSound = Sounds.plasmaboom;
                    layer = Layer.scorch;
                    inaccuracy = 2f;
                    splashDamageRadius = splashDamage = 25;
                    spin = 2;
                    weaveMag = 2f;
                    weaveScale = 4f;
                    drag = -0.017f;
                    homingPower = 0.05f;
                    collideFloor = true;
                    ejectEffect = Fx.none;
                    hitSize = 22f;
                    collidesAir = false;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = Pal.heal;
                    lifetime = 86f;
                    shrinkY = shrinkX = 0;
                    hitEffect = despawnEffect = new MultiEffect(Fx.blastExplosion, Fx.greenCloud);
                    shootEffect = ExoFx.colorSparkShoot;
                    trailLength = 10;
                    trailWidth = 2f;
                }};
            }});
        }};
        thor = new ExoUnitType("thor", 1.2f, 0.85f, 1f, 0.3f, 1.1f, 1f, 1) {{
            constructor = UnitWaterMove::create;
            trailLength = 90;
            waveTrailX = 39f;
            waveTrailY = -56f;
            trailScl = 9.5f;
            health = 84000f;
            outlineRadius = 6;
            omniMovement = true;
            outlineColor = Color.valueOf("50505f");
            armor = 25f;
            speed = 0.53f;
            accel = 0.2f;
            drag = 0.4f;
            hitSize = 80f;
            rotateSpeed = 0.5f;
            faceTarget = false;
            weapons.add(new Weapon("exogenesis-thor-hammer-mount") {{
                reload = 36f;
                mirror = alternate = false;
                rotate = true;
                rotateSpeed = 1.5f;
                x = -23.25f;
                y = -12;
                layerOffset = 1;
                shoot = new ShootBarrel() {{
                    shots = 2;
                    barrels = new float[]{
                            5.5f, 15.5f, 0f,
                            -5.5f, 15.5f, 0f,
                    };
                }};
                shootSound = Sounds.bolt;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new RegionPart("-barrels") {{
                            mirror = false;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    damageType = DamageType.pierce;
                    hitColor = Pal.heal;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(Pal.heal, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
            weapons.add(new Weapon("exogenesis-thor-hammer-mount") {{
                reload = 36.1f;
                mirror = alternate = false;
                rotate = true;
                rotateSpeed = 1.5f;
                x = -23.25f;
                y = 24.75f;
                layerOffset = 1;
                shoot = new ShootBarrel() {{
                    shots = 2;
                    barrels = new float[]{
                            5.5f, 15.5f, 0f,
                            -5.5f, 15.5f, 0f,
                    };
                }};
                shootSound = Sounds.bolt;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new RegionPart("-barrels") {{
                            mirror = false;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    damageType = DamageType.pierce;
                    hitColor = Pal.heal;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(Pal.heal, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
            weapons.add(new Weapon("exogenesis-thor-hammer-mount") {{
                reload = 36.2f;
                mirror = alternate = false;
                rotate = true;
                rotateSpeed = 1.5f;
                x = -23.25f;
                y = 51.5f;
                layerOffset = 1;
                shoot = new ShootBarrel() {{
                    shots = 2;
                    barrels = new float[]{
                            5.5f, 15.5f, 0f,
                            -5.5f, 15.5f, 0f,
                    };
                }};
                shootSound = Sounds.bolt;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new RegionPart("-barrels") {{
                            mirror = false;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    damageType = DamageType.pierce;
                    hitColor = Pal.heal;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(Pal.heal, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
            weapons.add(new Weapon("exogenesis-thor-hammer-mount") {{
                reload = 36.3f;
                mirror = alternate = false;
                rotate = true;
                rotateSpeed = 1.5f;
                x = -23.25f;
                y = 71.5f;
                layerOffset = 1;
                shoot = new ShootBarrel() {{
                    shots = 2;
                    barrels = new float[]{
                            5.5f, 15.5f, 0f,
                            -5.5f, 15.5f, 0f,
                    };
                }};
                shootSound = Sounds.bolt;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new RegionPart("-barrels") {{
                            mirror = false;
                            recoilIndex = 1;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }}
                );
                bullet = new ExoRailBulletType() {{
                    length = 300f;
                    damage = 138f;
                    damageType = DamageType.pierce;
                    hitColor = Pal.heal;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSpark);
                    smokeEffect = new Effect(30, e -> {
                        Draw.z(Layer.effect);
                        Draw.color(Pal.heal, e.fout());
                        Tmp.v1.trns(e.rotation, e.fin() * 20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y, 0.5f * e.fin() + 0.1f, 8, 16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin() * 10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y, 0.3f * e.fin() + 0.1f, 8f * 0.75f, 12, e.rotation);
                        Lines.stroke(2f * e.fout());
                    });
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if (!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for (int i = 0; i < 7; i++) {
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 3f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }});
            weapons.add(new Weapon("exogenesis-thor-weapon") {{
                reload = 360f;
                mirror = false;
                x = -21.5f;
                y = -46;
                rotateSpeed = 0.5f;
                shootSound = ExoSounds.funnylaserloop;
                shootY = 5;
                recoil = 4;
                rotate = continuous = true;
                cooldownTime = 200;
                shake = 4f;
                parts.addAll(
                        new FlarePart() {{
                            progress = PartProgress.life;
                            color1 = Pal.heal;
                            color2 = Color.white;
                            spinSpeed = 0.6f;
                            radius = 0f;
                            stroke = 4;
                            radiusTo = 100f;
                            layer = 109;
                            y = 5;
                        }}
                );
                bullet = new AcceleratingLaserBulletType(90f) {{
                    lifetime = 280f;
                    maxLength = 430f;
                    maxRange = 430f;
                    oscOffset = 0.3f;
                    shootEffect = ExoFx.colorBomb;
                    width = 16f;
                    damageType = DamageType.energy;
                    collisionWidth = 7f;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                    pierceCap = 3;
                    hitEffect = ExoFx.ullarTipHit;
                    hitColor = Pal.heal;
                }};
            }});
        }};
    }
}
