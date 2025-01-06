package exogenesis.content;

import exogenesis.content.effects.ExoChargeFx;
import exogenesis.entities.part.EffectSpawnPart;
import exogenesis.graphics.*;
import exogenesis.type.abilities.TurretShield;
import exogenesis.type.bullet.*;
import exogenesis.type.unit.ai.SniperAI;
import exogenesis.type.unit.AxinUnitType;
import exogenesis.type.unit.ExoUnitType;
import arc.graphics.*;
import arc.math.*;
import exogenesis.type.unit.ai.VanstarUnitType;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.RepairAI;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.content.*;
import mindustry.world.meta.BlockFlag;

import static arc.graphics.g2d.Draw.*;
import static mindustry.Vars.tilesize;

public class ExoUnitTypes {
    public static UnitType
    // Disaster unis
    catastrophe, war, entropy, apathy, silence, holocaust,
    //empyrean
    priest, bishop, apostle,
    soul, pneuma, psyche, myalo, acheron,
    lux, glimmer, shine, auric, radiance,
     prayer, apprise, revelation, enlightenment, excelsus,
    twinkle, starlight, stardustVoyager, orion, galileo, kuiper, oort, sirius, scout, guard, sentry, sentinel, overseer /* stele, pedestal, pylon, pillaster, monolith, meteor, asteroid, comet, planetoid, moon */;

    public static void load() {
        float coreFleeRange = 500f;
        priest = new VanstarUnitType("priest"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;
            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            flying = true;
            mineSpeed = 6.1f;
            mineTier = 1;
            buildSpeed = 1.2f;
            drag = 0.08f;
            speed = 5.6f;
            rotateSpeed = 7f;
            accel = 0.09f;
            itemCapacity = 60;
            health = 300f;
            armor = 1f;
            omniMovement = true;
            hitSize = 9f;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            drawBody = false;
            drawCell = false;
            engineLayer = Layer.effect-1;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            engineSize = 1f;
            engineOffset = 0;

            parts.addAll(
                    new ShapePart() {{
                        mirror = true;
                        progress = PartProgress.warmup;
                        circle = true;
                        hollow = true;
                        layer = Layer.buildBeam;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 0.35f;
                        radiusTo = radius = 7.5f;
                    }},
                    new HaloPart() {{
                        y = 0f;
                        radius = 2.5f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = -2.5f;
                        haloRadius = haloRadiusTo = 3.6f;
                        stroke = 0f;
                        strokeTo = 0.43f;
                        shapes = 4;
                        triLengthTo = triLength = 4f;
                    }},

                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 1f;
                        radiusTo = radius = 2.2f;
                    }},
                    new FlarePart(){{
                        radius = 10f;
                        radiusTo = 10f;
                        spinSpeed = 3;
                        layer = Layer.effect;
                        color1 = ExoPal.empyrean;
                        followRotation = true;
                        stroke = 2f;
                        rotation = 45f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = Color.white;
                        radiusTo = radius = 1.2f;
                    }}
            );
            abilities.add(new RepairFieldAbility(10f, 30f * 3, 60f){{
                parentizeEffects = true;
                healEffect = Fx.shootHealYellow;
                activeEffect = new WaveEffect(){{
                    colorFrom = ExoPal.empyreanLight;
                    colorTo = ExoPal.empyrean;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 60f;
                    lifetime = 55f;
                    strokeTo = 0;
                    strokeFrom = 3f;
                }};
            }});
            /*
            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 0f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                repairSpeed = 3.1f;
                fractionRepairSpeed = 0.06f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = ExoPal.empyrean;
                healColor = ExoPal.empyrean;

                bullet = new BulletType(){{
                    layer = Layer.effect;
                    maxRange = 60f;
                }};
            }});
             */
            weapons.add(new Weapon("zappy"){{
                x = 0;
                reload = 40f;
                shootSound = Sounds.spark;
                recoil = 0;
                mirror = false;
                rotate = true;
                rotateSpeed = 5.5f;
                shoot = new ShootSummon(0, 0, 12, 15){{
                    shots = 3;
                    shotDelay = 2f;
                }};
                bullet = new BasicBulletType() {{
                    sprite = "exogenesis-arrow-bullet";
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBigColor;
                    pierce = true;
                    pierceCap = 2;
                    homingPower = 0.01f;
                    homingDelay = 1;
                    homingRange = 70;
                    speed = 9;
                    damage = 6;
                    width = 4f;
                    height = 8f;
                    lifetime = 16f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = ExoPal.empyrean;
                    frontColor = Color.white;
                    trailWidth = 0.8f;
                    trailLength = 4;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        bishop = new VanstarUnitType("bishop"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            omniMovement = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            flying = true;
            mineSpeed = 8f;
            mineTier = 2;
            buildSpeed = 1.9f;
            drag = 0.08f;
            speed = 7f;
            rotateSpeed = 8f;
            accel = 0.09f;
            itemCapacity = 90;
            health = 500f;
            armor = 2f;
            hitSize = 11f;
            fogRadius = 0f;

            drawBody = false;
            drawCell = false;
            engineLayer = Layer.effect-1;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            engineSize = 2.7f;
            engineOffset = 0;

            parts.addAll(
                    new ShapePart() {{
                        mirror = true;
                        progress = PartProgress.warmup;
                        circle = true;
                        hollow = true;
                        layer = Layer.buildBeam;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 0.5f;
                        radiusTo = radius = 8f;
                    }},
                    new HaloPart() {{
                        y = 0f;
                        radius = 2.5f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = -2.5f;
                        haloRadius = haloRadiusTo = 5f;
                        stroke = 0f;
                        strokeTo = 1.2f;
                        shapes = 6;
                        triLengthTo = triLength = 6f;
                    }},

                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 1f;
                        radiusTo = radius = 3.5f;
                    }},
                    new FlarePart(){{
                        radius = 17f;
                        radiusTo = 17f;
                        spinSpeed = 3;
                        layer = Layer.effect;
                        color1 = ExoPal.empyrean;
                        followRotation = true;
                        stroke = 2f;
                        rotation = 45f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = Color.white;
                        radiusTo = radius = 1.5f;
                    }}
            );
            abilities.add(new RepairFieldAbility(25f, 35f * 3, 75f){{
                parentizeEffects = true;
                healEffect = Fx.shootHealYellow;
                activeEffect = new WaveEffect(){{
                    colorFrom = ExoPal.empyreanLight;
                    colorTo = ExoPal.empyrean;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 75f;
                    lifetime = 55f;
                    strokeTo = 0;
                    strokeFrom = 3f;
                }};
            }});
            /*
            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 0f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                repairSpeed = 3.3f;
                fractionRepairSpeed = 0.06f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = ExoPal.empyrean;
                healColor = ExoPal.empyrean;

                bullet = new BulletType(){{
                    layer = Layer.effect;
                    maxRange = 60f;
                }};
            }});
             */
            weapons.add(new Weapon("zappy"){{
                x = 0;
                reload = 20f;
                shootSound = Sounds.spark;
                recoil = 0;
                mirror = false;
                rotate = true;
                rotateSpeed = 5.5f;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyrean;
                    range = 80;
                    targetRange = 20;
                    damage = 17;
                    distanceDamageFalloff = 4;
                    chainLightning = 2;
                    segmentLength = 6;
                }};
            }});
        }};
        apostle = new VanstarUnitType("apostle"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            omniMovement = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            flying = true;
            mineSpeed = 9f;
            mineTier = 3;
            buildSpeed = 2.1f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 110;
            health = 700f;
            armor = 3f;
            hitSize = 12f;
            fogRadius = 0f;
            targetable = false;
            hittable = false;

            drawBody = false;
            drawCell = false;
            engineLayer = Layer.effect-1;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            engineSize = 3f;
            engineOffset = 0;

            parts.addAll(
                    new ShapePart() {{
                        mirror = true;
                        progress = PartProgress.warmup;
                        circle = true;
                        hollow = true;
                        layer = Layer.buildBeam;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 0.5f;
                        radiusTo = radius = 9f;
                    }},
                    new HaloPart() {{
                        y = 0f;
                        radius = 2.5f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = -2.5f;
                        haloRadius = haloRadiusTo = 7f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 6;
                        triLengthTo = triLength = 6f;
                    }},
                    new HaloPart() {{
                        y = 0f;
                        radius = 2.5f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = 2.5f;
                        haloRadius = haloRadiusTo = 7f;
                        stroke = 0f;
                        strokeTo = 1.2f;
                        shapes = 2;
                        triLengthTo = triLength = 11f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = ExoPal.empyrean;
                        stroke = strokeTo = 1f;
                        radiusTo = radius = 5f;
                    }},
                    new FlarePart(){{
                        radius = 19f;
                        radiusTo = 19f;
                        spinSpeed = 3;
                        layer = Layer.effect;
                        color1 = ExoPal.empyrean;
                        followRotation = true;
                        stroke = 2f;
                        rotation = 45f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = Color.white;
                        radiusTo = radius = 2.5f;
                    }}
            );
            abilities.add(new RepairFieldAbility(25f, 35f * 3, 90f){{
                parentizeEffects = true;
                healEffect = Fx.shootHealYellow;
                activeEffect = new WaveEffect(){{
                    colorFrom = ExoPal.empyreanLight;
                    colorTo = ExoPal.empyrean;
                    interp = Interp.circle;
                    sizeFrom = 0;
                    sizeTo = 90f;
                    lifetime = 55f;
                    strokeTo = 0;
                    strokeFrom = 2f;
                }};
            }});
            /*
            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 0f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = false;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = ExoPal.empyrean;
                healColor = ExoPal.empyrean;

                bullet = new BulletType(){{
                    layer = Layer.effect;
                    maxRange = 65f;
                }};
            }});
             */
            weapons.add(new Weapon("zappy"){{
                x = 0;
                reload = 20f;
                shootSound = Sounds.spark;
                recoil = 0;
                mirror = false;
                rotate = true;
                rotateSpeed = 5.5f;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyrean;
                    range = 80;
                    targetRange = 20;
                    damage = 23;
                    distanceDamageFalloff = 4;
                    chainLightning = 2;
                    segmentLength = 6;
                }};
            }});
        }};

        soul = new VanstarUnitType("soul") {{
            constructor = LegsUnit::create;
            outlineColor = ExoPal.empyreanOutline;
            speed = 0.7f;
            hitSize = 13f;
            health = 360f;
            faceTarget = true;
            armor = 2;
            shadowElevation = 0.1f;
            targetAir = false;
            allowLegStep = true;
            hovering = true;
            rotateSpeed = 1.6f;
            legMoveSpace = 1.5f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.96f;
            legForwardScl = 1.1f;
            legPhysicsLayer = false;
            legGroupSize = 4;
            legCount = 8;
            legExtension = -2;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.2f;
            legBaseOffset = 5;
            legLength = 9;
            parts.add(
                    new RegionPart("-blade"){{
                        mirror = true;
                        heatColor = ExoPal.empyreanPinkDark;
                        progress = PartProgress.warmup;
                        heatProgress = PartProgress.warmup;
                        moveRot = -26f;
                    }}
            );
            weapons.add(new Weapon("soul") {{
                mirror = false;
                x = 0;
                shootSound = Sounds.torch;
                showStatSprite = false;
                /*
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                 */
                continuous = true;
                alwaysContinuous = true;
                shake = 1f;
                shootY = 0;
                recoil = 0;
                parts.add(
                        new ShapePart() {{
                            mirror = true;
                            progress = PartProgress.warmup;
                            hollow = true;
                            circle = true;
                            layer = Layer.effect;
                            y = 3f;
                            color = ExoPal.empyreanPink;
                            stroke = 0f;
                            strokeTo = 1f;
                            radiusTo = radius = 3f;
                        }},
                        new HaloPart() {{
                            y = 3f;
                            radius = 2f;
                            tri = true;
                            color = ExoPal.empyreanPink;
                            layer = Layer.effect;
                            haloRotateSpeed = -1f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 5f;
                            triLength = 0f;
                        }},
                        new HaloPart() {{
                            y = 3f;
                            radius = 2f;
                            tri = true;
                            color = ExoPal.empyreanPink;
                            layer = Layer.effect;
                            haloRotateSpeed = 1f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 5f;
                            triLength = 0f;
                        }}
                );
                /*
                bullet = new TentacleBulletType(2) {{
                    length = 120f;
                    width = 5f;
                    segments = 18;
                    lifetime = 35;
                    angleVelocity = 2f;
                    damageType = radiation;
                    fromColor = ExoPal.empyreanPinkLight;
                    toColor = ExoPal.empyreanPinkDark;
                    shootEffect = Fx.colorSpark;
                    smokeEffect = Fx.sparkShoot;
                }};
                 */
                /*
                bullet = new EmpBulletType() {{
                    width = 8f;
                    height = 11f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanPink;
                    lifetime = 33f;
                    speed = 6f;
                    damage = 10f;
                    recoil = 0.6f;
                    splashDamage = 15;
                    splashDamageRadius = 30;
                    shrinkY = shrinkX = 0;
                    radius = 40f;
                    timeIncrease = 0.5f;
                    powerDamageScl = 0.3f;
                    powerSclDecrease = 0.1f;
                    unitDamageScl = 0.3f;
                    shootEffect = Fx.thoriumShoot;
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
                 */

                bullet = new ContinuousFlameBulletType() {{
                    hitColor = ExoPal.empyreanPink;
                    drawFlare = false;
                    damage = 3.5f;
                    pierceCap = 1;
                    length = 45f;
                    hitEffect = ExoFx.hitMeltColor;
                    oscScl = 1;
                    width = 5.4f;
                    colors = new Color[]{ExoPal.empyreanPinkDark.cpy().a(0.4f), ExoPal.empyreanPink, ExoPal.empyreanLight, Color.white};
                    despawnEffect = Fx.smokeCloud;
                    smokeEffect = Fx.none;
                    shootEffect = Fx.none;
                }};
            }});
        }};
        pneuma = new VanstarUnitType("pneuma") {{
            constructor = LegsUnit::create;
            outlineColor = ExoPal.empyreanOutline;
            speed = 0.8f;
            hitSize = 13f;
            health = 530f;
            rotateSpeed = 2.5f;
            faceTarget = true;
            armor = 4;
            shadowElevation = 0.1f;
            targetAir = false;
            allowLegStep = true;
            hovering = true;
            legPhysicsLayer = false;
            legGroupSize = 4;
            legCount = 6;
            legExtension = -2;
            legMoveSpace = 0.8f;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.2f;
            legBaseOffset = 3;
            legLength = 18;
            parts.addAll(
                    new EffectSpawnPart() {{
                        useProgress = mirror = true;
                        y = 0f;
                        effect = ExoFx.supernovaSpark;
                        effectColor = ExoPal.empyreanPink;
                        randomEffectRot = 30;
                        effectChance = 0.5f;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 114;
                        radiusTo = 1;
                        radius = 0.8f;
                        color = Color.white;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 110;
                        radiusTo = 2.5f;
                        radius = 1.45f;
                        color = ExoPal.empyreanPink;
                    }}
            );
            weapons.add(new Weapon("starshoot") {{
                reload = 8;
                mirror = false;
                x = 0;
                inaccuracy = 40;
                shootSound = Sounds.blaster;
                showStatSprite = false;
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                shootCone = 30;
                shootY = 0;
                recoil = 0;
                bullet = new BasicBulletType(6, 10){{
                    backColor = hitColor = trailColor = ExoPal.empyreanPink;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.empyreanPink;
                                spinSpeed = 3;
                                radius = 8;
                                radiusTo = 8;
                                stroke = 3.3f;
                            }}
                    );
                    trailWidth = 2f;
                    trailLength = 8;
                    homingPower = 0.0789f;
                    homingRange = 90;
                    homingDelay = 6;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    lifetime = 32;
                    hitEffect = despawnEffect = Fx.colorSpark;
                }};
            }});
        }};
        psyche = new VanstarUnitType("psyche"){{
            speed = 0.5f;
            hitSize = 32f;
            rotateSpeed = 2.2f;
            health = 1200;
            armor = 7f;
            constructor = LegsUnit::create;
            outlineColor = ExoPal.empyreanOutline;
            outlineRadius = 4;
            legCount = 6;
            legLength = 21f;
            legForwardScl = 0.65f;
            legMoveSpace = 0.8f;
            legExtension = -15;
            legBaseOffset =  10;
            lockLegBase = legContinuousMove = faceTarget = true;
            legGroupSize = 3;
            legStraightness = 0.35f;
            baseLegStraightness = 0.3f;
            legMaxLength = 1.3f;
            rippleScale = 2;
            hovering = true;
            legSplashDamage = 22;
            legSplashRange = 30;
            drawShields = false;

            abilities.add(new ForceFieldAbility(60f, 0.5f, 1000f, 60f, 360, 45));

            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("exogenesis-psyche-weapon"){{
                shootSound = Sounds.shootBig;
                x = 19.25f;
                y = 0;
                shootY = 13.5f;
                shootX = -2.5f;
                shake = 3;
                reload = 70f;
                heatColor = Color.red;
                top = false;
                rotate = false;
                recoil = 2;
                inaccuracy = 2f;
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 4f;
                }};
                bullet = new BasicBulletType(5.6f, 65) {{
                    width = 11f;
                    height = 11f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanPink;
                    lifetime = 35f;
                    weaveMag = 0.5f;
                    weaveScale = 10;
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.empyreanStarHitSmallWave);
                    splashDamage = 15;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    hitSound = Sounds.dullExplosion;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    trailChance = 0.1f;
                    trailEffect = new MultiEffect(ExoFx.ExoTrailSmoke);
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.shootSmokeTitan);
                    homingPower = 0.0178f;
                    homingRange = 50;
                    homingDelay = 2;
                    trailLength = 10;
                    trailWidth = 3.5f;
                }};
            }});
        }};
        myalo = new VanstarUnitType("myalo"){{
            constructor = LegsUnit::create;
            outlineColor = ExoPal.empyreanOutline;
            outlineRadius = 4;
            drag = 0.2f;
            speed = 0.43f;
            hitSize = 53f;
            health = 5500;
            rotateSpeed = 1.5f;
            armor = 7f;
            legCount = 4;
            legMoveSpace = 1.1f;

            lockLegBase = true;
            legContinuousMove = true;
            legSpeed = 0.001f;
            legLength = 18.5f;
            legForwardScl = 0.45f;
            rippleScale = 2f;
            stepShake = 4.5f;
            legBaseOffset = 12f;

            legSplashDamage = 62;
            legSplashRange = 30;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            parts.addAll(
                    new RegionPart("-bodyside"){{
                        mirror = true;
                        progress = PartProgress.warmup;
                        children.add(
                                new RegionPart("-bodyside-bit"){{
                                    progress = PartProgress.warmup;
                                    layerOffset = -0.001f;
                                    mirror = true;
                                    under = true;
                                    moves.add(new PartMove(PartProgress.recoil, 0f, -3f, -5f));
                                    y = 8.5f;
                                    x = 10.75f;
                                    moveRot = -15.3f;
                                    moveY = 5;
                                    moveX = 3.3f;
                                }},
                                new RegionPart("-bodyside2"){{
                                    progress = PartProgress.warmup.delay(0.6f);
                                    moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f), new PartMove(PartProgress.warmup.delay(0.5f), 0f, 0f, -25f));
                                    layerOffset = -0.001f;
                                    mirror = true;
                                    under = true;
                                    y = 2.75f;
                                    x = 18.5f;
                                }});
                        moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                        moveRot = -5;
                        moveX = 3.5f;
                    }}
            );
            weapons.add(new Weapon("exogenesis-myalo-weapon"){{
                shootSound = Sounds.malignShoot;
                mirror = false;
                top = false;
                x = 0;
                y = 20f;
                shootY = 0;
                reload = 400f;
                shake = 3f;
                heatColor = Color.red;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = shoot.firstShotDelay = 100 + 5f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                /*
                bullet = new BlackHoleBulletType(1f, 1400f / 35f){{
                    lifetime = 330f;
                    growTime = 15;
                    damageRadius = 20;
                    swirlEffects = 5;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.empyreanPink;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
                 */
                bullet = new DestructionBulletType(1f, 260){{
                    size /= 2.2f;
                    trailWidth = 9.5f;
                    trailLength = 57;
                    chargeEffect = ExoChargeFx.starChargePink;
                    spreadEffect = slopeEffect = Fx.none;
                    backColor = trailColor = hitColor = lightColor = lightningColor = ExoPal.empyreanPink;
                    frontColor = Color.white;
                    scaleLife = false;
                    randomLightningChance = 0.5f;
                    randomGenerateRange = linkRange = 200f;
                    randomLightningNum = 5;
                    maxHit = 6;
                    range = 200f;
                    drawSize = 20f;
                    hitSound = Sounds.explosionbig;
                    splashDamageRadius = 100f;
                    splashDamage = 300;
                    lightningDamage = 8f;
                    intervalBullets = 1;
                    bulletInterval = 2;
                    trailEffect = new Effect(30f, e -> {
                        color(ExoPal.empyreanPink);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 5.5f, 44f * e.fslope(), e.rotation + 90f * s);
                        }
                    });
                    homingRange = 80;
                    homingPower = 0.02f;
                    trailRotation = true;
                    trailInterval = 7f;
                    intervalBullet = new LightningBulletType(){{
                        damage = 25;
                        ammoMultiplier = 1f;
                        lightningColor = ExoPal.empyreanPink;
                        lightningLength = 5;
                        lightningLengthRand = 10;
                    }};
                    pierce = false;
                    collides = false;
                    lifetime = 450;
                    despawnEffect = hitEffect = ExoFx.empyreanExplosion;
                    shootEffect = ExoFx.square45_6_45;
                    hitSpacing = 3;
                }};
            }});
            weapons.add(new Weapon("exogenesis-empyreanPink-laser"){{
                x = 18f;
                y = -7f;
                shootY = 8.25f;
                shootSound = Sounds.laser;
                rotate = true;
                alternate = true;
                layerOffset = 0.002f;
                rotateSpeed = 2.5f;
                reload = 69f;
                recoil = 2f;
                bullet = new FancyLaserBulletType(){{
                    damage = 125f;
                    sideAngle = 27f;
                    sideWidth = 1.5f;
                    sideLength = 20f;
                    width = 35f;
                    length = 200f;
                    colors = new Color[]{ExoPal.empyreanPink.cpy().a(0.4f), ExoPal.empyreanPinkLight, Color.white};
                    hitEffect = ExoFx.coloredHitLarge;
                    hitColor = ExoPal.empyreanPink;
                    shootEffect = ExoFx.hitEmpColorSpark;
                }};
            }});
        }};
        acheron = new VanstarUnitType("acheron"){{
            constructor = LegsUnit::create;
            outlineColor = ExoPal.empyreanOutline;
            outlineRadius = 5;
            drag = 0.1f;
            speed = 0.35f;
            hitSize = 56;
            health = 37000;
            armor = 10f;
            drawShields = false;
            rotateSpeed = 1.2f;
            lockLegBase = true;
            legContinuousMove = true;
            legCount = 6;
            legLength = 50f;
            legSpeed = 0.8f;
            legMoveSpace = 0.85f;
            rippleScale = 3.2f;
            stepShake = 1.5f;
            legPairOffset = 3;
            legExtension = -15f;
            legBaseOffset = 22f;
            legMaxLength = 1.6f;

            legSplashDamage = 172;
            legSplashRange = 32;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            abilities.add(new TurretShield(){{
                region = "exogenesis-acheron-shield";
                radius = 90f;
                drawArc = true;
                regen = 11f;
                cooldown = 60f * 8f;
                max = 6000f;
                y = -25f;
                width = 9f;
                whenShooting = true;
            }});
            parts.addAll(
                    new EffectSpawnPart() {{
                        useProgress = mirror = false;
                        y = 18/4f;
                        effect = ExoFx.supernovaSpark;
                        randomEffectRot = 360;
                        effectChance = 0.5f;
                    }},
                    new HoverPart(){{
                        color = ExoPal.empyreanPink;
                        circles = 3;
                        sides = 360;
                        stroke = 3;
                        phase = 200;
                        radius = 17f;
                        mirror = false;
                        layer = Layer.effect;
                        y = 18/4f;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 18/4f;
                        layer = 114;
                        radiusTo = radius = 4f;
                        color = Color.white;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 18/4f;
                        layer = 110;
                        radiusTo = radius = 6.5f;
                        color = ExoPal.empyreanPink;
                    }}
            );
            weapons.add(new Weapon("exogenesis-acheron-weapon"){{
                shootSound = Sounds.shootBig;
                mirror = true;
                rotationLimit = 30;
                shootCone = 70f;
                rotateSpeed = 0.5f;
                top = true;
                rotate = true;

                x = 38.5f;
                y = 0;
                shootY = 25.0f;
                shootX = -0.25f;
                recoil = 7f;
                reload = 45f;
                shake = 3f;
                velocityRnd = 0.1f;
                inaccuracy = 5;
                layerOffset = -0.001f;

                shoot = new ShootPattern(){{
                    shots = 6;
                    shotDelay = 1.7f;
                }};
                heatColor = Color.red;
                bullet = new BasicBulletType(9, 60){{
                    width = 6f;
                    height = 17f;
                    sprite = "circle-bullet";
                    shrinkY = shrinkX = 0f;

                    lifetime = 35;
                    pierceArmor = true;
                    pierce = true;
                    pierceCap = 1;
                    shootEffect = new MultiEffect(ExoFx.shootGiant, Fx.colorSparkBig);
                    hitEffect = despawnEffect = ExoFx.blastExplosionColor;
                    backColor = trailColor = hitColor = ExoPal.empyreanPinkDark;
                    lightningColor = lightColor = ExoPal.empyreanPinkDark;
                    /*
                    trailEffect = new MultiEffect(
                            new ParticleEffect(){{
                                lightOpacity = 0.5f;
                                line = true;
                                particles = 3;
                                cone = -30;
                                length = -85;
                                lifetime = 20;
                                lenFrom = 6;
                                lenTo = 0;
                                lightColor = colorFrom = ExoPal.empyreanPink;
                                colorTo = ExoPal.empyreanPinkDark;
                            }}, new Effect(20,e->{
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.empyreanPink,e.fout());
                        Tmp.v2.trns(e.rotation, e.fin()*10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                        Lines.stroke(6f*e.fout());
                    }));
                     */
                    homingPower = 0.178f;
                    homingRange = 100;
                    homingDelay = 2;
                    trailLength = 13;
                    trailWidth = 2f;
                    lightningDamage = 20;
                    lightning = 3;
                    lightningLength = 2;
                    lightningLengthRand = 1;
                    lightningCone = 35f;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-empyreanPink-ioner") {{
                reload = 245f;
                mirror = true;
                x = 25;
                y = -10;
                alternate = true;
                shootSound = Sounds.blaster;
                shootY = 8.5f;
                recoil = 2;
                rotateSpeed = 2.5f;

                rotate = true;
                shake = 1.5f;
                bullet = new BasicBulletType(3.5f, 65){{
                    width = 22;
                    height = 22;
                    sprite = "exogenesis-plasma";
                    scaleLife = false;
                    hitSound = Sounds.dullExplosion;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanPink;
                    trailRotation = true;
                    collidesTiles = false;
                    lifetime = 125f;
                    splashDamage = 30;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.blastExplosionColor, Fx.colorSpark);
                    intervalBullet = new ChainLightningBulletType() {{
                        lightningColor = ExoPal.empyreanPink;
                        range = 125;
                        targetRange = 125;
                        damage = 20;
                        distanceDamageFalloff = 4;
                        chainLightning = 2;
                        segmentLength = 6;
                    }};
                    intervalBullets = 1;
                    bulletInterval = 5.3f;
                    lightning = 7;
                    lightningLength = 9;
                    lightningColor = ExoPal.empyreanPink;
                    lightningDamage = 15;
                    shootEffect = Fx.shootBigColor;
                    trailSinScl = 6;
                    trailSinMag = 0.3f;
                    trailParam = 5;
                    trailLength = 10;
                    trailWidth = 3.5f;
                }};
            }});
        }};

        lux = new VanstarUnitType("lux") {{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 3;
            speed = 4.8f;
            hitSize = 14f;
            health = 260f;
            flying = true;
            drag = 0.08f;
            accel = 0.09f;
            drawCell = false;
            faceTarget = true;
            lowAltitude = true;
            armor = 2;
            engineLayer = Layer.effect;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            rotateSpeed = 4.7f;
            engineSize = 2.7f;
            engineOffset = 0;
            weapons.add(new Weapon("lux") {{
                reload = 40f;
                mirror = false;
                x = 0;
                shoot = new  ShootPattern(){{
                    shotDelay = 2.5f;
                    shots = 3;
                }};
                inaccuracy = 20;
                velocityRnd = 0.1f;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new ShapePart() {{
                            mirror = false;
                            progress = PartProgress.warmup;
                            circle = true;
                            layer = Layer.effect;
                            y = 0f;
                            color = ExoPal.empyrean;
                            stroke = strokeTo = 1f;
                            radiusTo = radius = 3f;
                        }},
                        new ShapePart() {{
                            mirror = false;
                            progress = PartProgress.warmup;
                            circle = true;
                            layer = Layer.effect;
                            y = 0f;
                            color = Color.white;
                            radiusTo = radius = 1.5f;
                        }},
                        new HaloPart() {{
                            y = 0f;
                            radius = 1.5f;
                            tri = true;
                            color = ExoPal.empyrean;
                            layer = Layer.effect;
                            haloRotateSpeed = -2.5f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = triLength = 4f;
                        }},
                        new HaloPart() {{
                            y = 0f;
                            radius = 1.5f;
                            tri = true;
                            color = ExoPal.empyrean;
                            layer = Layer.effect;
                            haloRotateSpeed = 2.5f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = triLength = 4f;
                        }}
                );
                bullet = new BasicBulletType(8,5){{
                    width = 0f;
                    height = 0f;
                    homingRange = 100;
                    homingPower = 0.075f;
                    homingDelay = 1;
                    weaveScale = 4;
                    weaveMag = 2;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.empyrean;
                                spinSpeed = 2;
                                radius = 7;
                                radiusTo = 7;
                                stroke = 3.5f;
                            }}
                    );
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyrean;
                    lifetime = 16f;
                    shrinkY = shrinkX = 0;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    shootEffect = ExoFx.colorSparkShoot;
                    trailLength = 5;
                    trailWidth = 2f;
                }};
            }});
        }};
        glimmer = new VanstarUnitType("glimmer"){{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 3;
            speed = 3.4f;
            hitSize = 21f;
            health = 470f;
            flying = true;
            drag = 0.08f;
            accel = 0.09f;
            faceTarget = true;
            lowAltitude = true;
            armor = 3;
            engineLayer = Layer.effect;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            rotateSpeed = 6.4f;
            engineSize = 3;
            engineOffset = 4;
            parts.add(
                    new ShapePart() {{
                        mirror = false;
                        progress = PartProgress.warmup;
                        circle = true;
                        layer = Layer.effect;
                        y = -4f;
                        color = ExoPal.empyrean;
                        radiusTo = radius = 3f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        progress = PartProgress.warmup;
                        circle = true;
                        layer = Layer.effect;
                        y = -4f;
                        color = Color.white;
                        radiusTo = radius = 1.5f;
                    }},
                    new HaloPart() {{
                        y = -4f;
                        radius = 1.8f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = -2.5f;
                        haloRadius = haloRadiusTo = 3f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 2;
                        triLengthTo = triLength = 4f;
                    }},
                    new HaloPart() {{
                        y = -4f;
                        radius = 1.8f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = 2.5f;
                        haloRadius = haloRadiusTo = 3f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 2;
                        triLengthTo = triLength = 4f;
                    }}
            );
            weapons.add(new Weapon("glimmer") {{
                reload = 6.5f;
                mirror = true;
                x = 4;
                y = 3;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                recoil = 0;
                shake = 1f;
                bullet = new BasicBulletType(9f, 7){{
                    width = 7f;
                    height = 21f;
                    sprite = "missile";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyrean;
                    lifetime = 10f;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(Fx.shootSmallColor, ExoFx.colorSparkShoot);
                    trailLength = 10;
                    trailWidth = 2f;
                    fragOnHit = false;
                    fragRandomSpread = 0f;
                    fragSpread = 10f;
                    fragBullets = 3;
                    fragVelocityMin = 1f;

                    fragBullet = new BasicBulletType(8f, 3){{
                        sprite = "missile";
                        width = 7f;
                        height = 13f;
                        lifetime = 7f;
                        hitSize = 4f;
                        backColor = hitColor = trailColor = ExoPal.empyrean;
                        frontColor = Color.white;
                        trailWidth = 1f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }};
                }};
            }});
        }};
        shine = new VanstarUnitType("shine"){{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 3;
            speed = 4f;
            hitSize = 28f;
            health = 800f;
            flying = true;
            drag = 0.06f;
            accel = 0.09f;
            faceTarget = true;
            lowAltitude = true;
            armor = 5;
            trailLength = 8;
            trailColor = ExoPal.empyrean;
            rotateSpeed = 3.8f;
            engineSize = 0;
            engineOffset = 0;
            setEnginesMirror(
            new UnitEngine(4.5f, -9, 2f, 315f)
            );
            weapons.add(new Weapon("shine") {{
                reload = 63f;
                mirror = false;
                x = 0;
                shoot = new  ShootPattern(){{
                    shotDelay = 5f;
                    shots = 3;
                }};
                shootSound = Sounds.malignShoot;
                showStatSprite = false;
                recoil = 0;
                shake = 1f;
                parts.add(
                        new ShapePart() {{
                            mirror = false;
                            progress = PartProgress.warmup;
                            circle = true;
                            layer = Layer.effect;
                            y = 0f;
                            color = ExoPal.empyrean;
                            radiusTo = radius = 4.5f;
                        }},
                        new ShapePart() {{
                            mirror = false;
                            progress = PartProgress.warmup;
                            circle = true;
                            layer = Layer.effect;
                            y = 0f;
                            color = Color.white;
                            radiusTo = radius = 3f;
                        }},
                        new HaloPart() {{
                            y = 0f;
                            radius = 2f;
                            tri = true;
                            color = ExoPal.empyrean;
                            layer = Layer.effect;
                            haloRotateSpeed = -2.5f;
                            haloRadius = haloRadiusTo = 4.5f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = triLength = 4f;
                        }},
                        new HaloPart() {{
                            y = 0f;
                            radius = 2f;
                            tri = true;
                            color = ExoPal.empyrean;
                            layer = Layer.effect;
                            haloRotateSpeed = 2.5f;
                            haloRadius = haloRadiusTo = 4.5f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 3;
                            triLengthTo = triLength = 5.5f;
                        }}
                );
                bullet = new BasicBulletType(7f, 16){{
                    width = height = 10f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyrean;
                    hitEffect = despawnEffect = ExoFx.colorBombSmall;
                    lifetime = 30f;
                    weaveMag = 3;
                    weaveScale = 2;
                    shrinkY = shrinkX = 0;
                    trailEffect = new Effect(13f, e -> {
                        color(ExoPal.empyrean);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 2.5f, 14f * e.fslope(), e.rotation + 90f*s);
                        }
                    });
                    trailRotation = true;
                    trailInterval = 2f;
                    lightning = 4;
                    lightningLength = 6;
                    lightningColor = ExoPal.empyrean;
                    lightningDamage = 11;
                    shootEffect = ExoFx.colorSparkShoot;
                    trailLength = 10;
                    trailWidth = 3f;
                }};
            }});
        }};
        auric = new VanstarUnitType("auric"){{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 3;
            speed = 2.4f;
            hitSize = 47f;
            health = 2650f;
            flying = true;
            drag = 0.07f;
            accel = 0.04f;
            faceTarget = true;
            lowAltitude = false;
            armor = 8;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyrean;
            rotateSpeed = 2.6f;
            engineSize = 0;
            engineOffset = 0;
            parts.addAll(
            new RegionPart("-mandible"){{
              moves.add(new PartMove(PartProgress.charge.curve(Interp.circleIn), 0, 0, -50));
              moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2In), 0, 0, -50));
              mirror = true;
              under = true;
              x = 20.75f;
              y = 1.25f;
              layerOffset = -0.0001f;
              heatProgress = PartProgress.charge.curve(Interp.circleIn);
              }}
              );
            setEnginesMirror(
                    new UnitEngine(19.5f, -18, 5f, 315f),
                    new UnitEngine(9.5f, -25, 3f, 315f)
            );
            weapons.add(new Weapon("auric-blast") {{
                reload = 220f;
                mirror = false;
                x = 0;
                y = 6;
                shoot.firstShotDelay = 80;
                shootStatusDuration = 90;
                shootStatus = StatusEffects.unmoving;
                shootSound = Sounds.malignShoot;
                showStatSprite = false;
                recoil = 0;
                shake = 1f;
                bullet = new BasicBulletType(1.8f, 185){{
                    width = height = 55;
                    recoil = 0.5f;
                    sprite = "exogenesis-plasma";
                    scaleLife = false;
                    chargeEffect = ExoFx.auricCharge;
                    hitSound = Sounds.explosionbig;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyrean;
                    lifetime = 165f;
                    splashDamage = 100;
                    splashDamageRadius = 70;
                    shrinkY = shrinkX = 0;
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.empyreanExplosion);
                    bulletInterval = 5f;
                    intervalBullet = new ChainLightningBulletType() {{
                        lightningColor = ExoPal.empyrean;
                        range = 215;
                        collidesTiles = true;
                        targetRange = 160;
                        damage = 45;
                        branches = 1;
                        distanceDamageFalloff = 4;
                        chainLightning = 4;
                        segmentLength = 6;
                    }};
                    lightning = 7;
                    lightningLength = 9;
                    lightningColor = ExoPal.empyrean;
                    lightningDamage = 11;
                    shootEffect = Fx.lightningShoot;
                    trailSinScl = 2;
                    trailSinMag = 0.8f;
                    trailParam = 5;
                    trailLength = 10;
                    trailWidth = 10f;
                }};
            }});
        }};
        radiance = new VanstarUnitType("radiance"){{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 3;
            speed = 1.97f;
            hitSize = 56f;
            health = 26000f;
            flying = true;
            drag = 0.07f;
            accel = 0.04f;
            faceTarget = true;
            lowAltitude = true;
            armor = 8;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyrean;
            rotateSpeed = 2f;
            engineSize = 0;
            engineOffset = 0;
            parts.add(
                    new ShapePart() {{
                        mirror = false;
                        progress = PartProgress.warmup;
                        circle = true;
                        layer = Layer.effect;
                        y = -8f;
                        color = ExoPal.empyrean;
                        radiusTo = radius = 11f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        progress = PartProgress.warmup;
                        circle = true;
                        layer = Layer.effect;
                        y = -8f;
                        color = Color.white;
                        radiusTo = radius = 7f;
                    }},
                    new HaloPart() {{
                        y = -8f;
                        radius = 3.5f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = -1f;
                        haloRadius = haloRadiusTo = 11f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 6;
                        triLengthTo = triLength = 6f;
                    }},
                    new HaloPart() {{
                        y = -8f;
                        radius = 4f;
                        tri = true;
                        color = ExoPal.empyrean;
                        layer = Layer.effect;
                        haloRotateSpeed = 2f;
                        haloRadius = haloRadiusTo = 11f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 2;
                        triLengthTo = triLength = 13.5f;
                    }});
            parts.add(new HaloPart(){{
                y = -8f;
                radius = 3f;
                tri = true;
                color = ExoPal.empyrean;
                layer = Layer.effect;
                haloRotateSpeed = -2f;
                haloRadius = haloRadiusTo = 11f;
                stroke = 0f;
                strokeTo = 2f;
                shapes = 2;
                triLengthTo = triLength = 10.5f;
            }});
            setEnginesMirror(
                    new UnitEngine(19.5f, -27, 5f, 60f),
                    new UnitEngine(9.5f, -27, 4f, 60f)
            );
            weapons.add(new Weapon("radiance-cannon") {{
                reload = 200f;
                mirror = false;
                y = 8;
                x = 0;
                shootSound = Sounds.mediumCannon;
                parentizeEffects = true;
                recoil = 0;
                shake = 3f;
                bullet = new BasicBulletType(10f, 530f){{
                    recoil = 1.2f;
                    lifetime = 40f;
                    trailLength = 10;
                    trailWidth = 5;
                    hitSize = 34f;
                    keepVelocity = false;
                    trailColor = hitColor = backColor = lightColor = lightningColor = ExoPal.empyrean;
                    width = 30f;
                    height = 60f;
                    knockback = 60;
                    impact = true;
                    despawnShake = hitShake = 18f;
                    lightning = 3;
                    lightningLength = 6;
                    lightningLengthRand = 18;
                    lightningDamage = 20;
                    fragOnHit = false;
                    fragRandomSpread = 0f;
                    fragSpread = 12f;
                    fragBullets = 5;
                    fragVelocityMin = 0.75f;

                    fragBullet = new BasicBulletType(8f, 60){{
                        width = 17f;
                        height = 23f;
                        lifetime = 27f;
                        hitSize = 14f;
                        backColor = hitColor = trailColor = ExoPal.empyrean;
                        frontColor = Color.white;
                        trailWidth = 2.8f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }};
                    smokeEffect = Fx.smokePuff;
                    shootEffect = new MultiEffect(ExoFx.instShootExo, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    despawnEffect = new MultiEffect(ExoFx.empyreanExplosionSplash, ExoFx.empyreanStarHitLarge, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    hitEffect = new MultiEffect(ExoFx.empyreanExplosionSplash, ExoFx.empyreanStarHitLarge, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    despawnHit = true;
                }};
            }});
            weapons.add(new Weapon("exogenesis-radiance-mount"){{
                x = 25;
                y = - 10;
                shootY = 11f;
                reload = 35f;
                recoil = 2f;
                shootSound = Sounds.bolt;
                shadow = 15f;
                mirror = true;
                rotate = true;
                rotateSpeed = 1.5f;
                bullet = new LaserBulletType(){{
                    damage = 95f;
                    sideAngle = 90f;
                    sideWidth = 1.5f;
                    sideLength = 50f;
                    lifetime = 50;
                    width = 25f;
                    length = 160f;
                    lightningSpacing = 30f;
                    lightningLength = 1;
                    lightningAngleRand = 1;
                    lightningAngle = 70;
                    lightningDelay = 0.12f;
                    lightningType = new LaserBulletType(){{
                        damage = 45f;
                        sideWidth = 0f;
                        lifetime = 50;
                        width = 15f;
                        length = 30f;
                        hitColor = ExoPal.empyrean;
                        colors = new Color[]{ExoPal.empyrean.cpy().a(0.3f), ExoPal.empyrean, Color.white};
                    }};
                    hitColor = ExoPal.empyrean;
                    shootEffect = ExoFx.colorBombSmall;
                    colors = new Color[]{ExoPal.empyreanAlpha, ExoPal.empyrean, Color.white};
                }};
            }});
        }};

        prayer = new VanstarUnitType("prayer"){{
            constructor = UnitEntity::create;
            aiController = RepairAI::new;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 2;
            speed = 2.8f;
            hitSize = 10f;
            health = 161f;
            flying = true;
            drag = 0.08f;
            accel = 0.09f;
            faceTarget = true;
            lowAltitude = true;
            armor = 1;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyreanIndigo;
            rotateSpeed = 3.7f;
            engineSize = 1.7f;
            engineOffset = 8;
            parts.add(
                    new ShapePart() {{
                        mirror = true;
                        progress = PartProgress.warmup;
                        circle = true;
                        hollow = true;
                        layer = Layer.effect;
                        y = 0f;
                        color = ExoPal.empyreanIndigo;
                        stroke = strokeTo = 1.4f;
                        radiusTo = radius = 11f;
                    }},
                    new HaloPart() {{
                        y = 0f;
                        radius = 2.5f;
                        tri = true;
                        color = ExoPal.empyreanIndigo;
                        layer = Layer.effect;
                        haloRotateSpeed = -2.5f;
                        haloRadius = haloRadiusTo = 11f;
                        stroke = 0f;
                        strokeTo = 2f;
                        shapes = 2;
                        triLengthTo = triLength = 4f;
                    }}
            );
            weapons.add(new RepairBeamWeapon("prayer") {{
                mirror = rotate = false;
                shootY = 8;
                x = 0;
                laserColor = healColor = ExoPal.empyreanIndigo;
                targetBuildings = true;
                shootCone = 60;
                beamWidth = 0.8f;
                repairSpeed = 1.7f;
                bullet = new BulletType(){{
                    maxRange = 120f;
                }};
            }});
        }};
        apprise = new VanstarUnitType("apprise") {{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 2.1f;
            speed = 3f;
            hitSize = 16f;
            health = 285f;
            flying = true;
            drag = 0.08f;
            accel = 0.09f;
            faceTarget = false;
            circleTarget = true;
            lowAltitude = false;
            armor = 2;
            engineLayer = Layer.effect;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyreanIndigo;
            rotateSpeed = 3.4f;
            engineSize = 2.5f;
            engineOffset = 13;
            weapons.add(new Weapon("apprise") {{
                reload = 55f;
                mirror = true;
                shootCone = 150f;
                ejectEffect = Fx.none;
                inaccuracy = 15f;
                rotate = false;
                baseRotation = 35;
                shoot = new  ShootPattern(){{
                    shotDelay = 2f;
                    shots = 6;
                }};
                x = 5;
                y = -3;
                shootSound = Sounds.missile;
                recoil = 0;
                bullet = new MissileBulletType(){{
                    width = 8f;
                    height = 8f;
                    keepVelocity = false;
                    speed = 6;
                    damage = 8;
                    shrinkY = 0f;
                    homingRange = 13f;
                    splashDamageRadius = 25f;
                    splashDamage = 7f;
                    lifetime = 53f;
                    trailColor = backColor = frontColor = ExoPal.empyreanIndigo;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;
                    weaveScale = 6f;
                    weaveMag = 1f;
                }};
            }});
        }};
        revelation = new VanstarUnitType("revelation") {{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 2;
            speed = 2.8f;
            hitSize = 15f;
            health = 530f;
            flying = true;
            drag = 0.06f;
            accel = 0.09f;
            faceTarget = false;
            lowAltitude = true;
            armor = 5;
            rotateSpeed = 4.7f;
            engineSize = 2.5f;
            engineOffset = 15;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyreanIndigo;
            weapons.add(new Weapon("revelation-zap") {{
                reload = 50f;
                mirror = false;
                y = 18;
                x = 0;
                rotate = true;
                rotateSpeed = 5.5f;
                shootSound = Sounds.spark;
                recoil = 0;
                shake = 1f;
                parts.add(
                new FlarePart () {{
                mirror = false;
                progress = PartProgress.reload;
                layer = Layer.effect;
                y = 0f;
                stroke = 3;
                color1 = ExoPal.empyreanIndigo;
                radius = 8.5f;
                }}
                );
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyreanIndigo;
                    range = 80;
                    targetRange = 20;
                    damage = 19;
                    distanceDamageFalloff = 4;
                    chainLightning = 2;
                    segmentLength = 6;
                }};
            }});
            weapons.add(new Weapon("zappy"){{
                x = -12;
                reload = 20f;
                shootSound = Sounds.spark;
                recoil = 0;
                mirror = false;
                rotate = true;
                rotateSpeed = 5.5f;
                parts.add(
                        new FlarePart () {{
                            mirror = false;
                            progress = PartProgress.reload;
                            layer = Layer.effect;
                            y = 0f;
                            stroke = 3;
                            color1 = ExoPal.empyreanIndigo;
                            radius = 7f;
                        }}
                );
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyreanIndigo;
                    range = 80;
                    targetRange = 20;
                    damage = 19;
                    distanceDamageFalloff = 4;
                    chainLightning = 2;
                    segmentLength = 6;
                }};
            }});
            weapons.add(new Weapon("zappy"){{
                x = 12.1f;
                reload = 20f;
                shootSound = Sounds.spark;
                recoil = 0;
                mirror = false;
                rotate = true;
                rotateSpeed = 5.5f;
                parts.add(
                        new FlarePart () {{
                            mirror = false;
                            progress = PartProgress.reload;
                            layer = Layer.effect;
                            y = 0f;
                            stroke = 3;
                            color1 = ExoPal.empyreanIndigo;
                            radius = 7f;
                        }}
                );
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyreanIndigo;
                    range = 80;
                    targetRange = 20;
                    damage = 19;
                    distanceDamageFalloff = 4;
                    chainLightning = 2;
                    segmentLength = 6;
                }};
            }});
        }};
        enlightenment = new VanstarUnitType("enlightenment") {{
            constructor = UnitEntity::create;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 2;
            speed = 2f;
            hitSize = 40f;
            health = 1650f;
            flying = true;
            drag = 0.07f;
            accel = 0.04f;
            faceTarget = true;
            lowAltitude = true;
            armor = 8;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyreanIndigo;
            rotateSpeed = 1.97f;
            engineSize = 4;
            engineOffset = 15;
            parts.add(
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 35.75f;
                        color = ExoPal.empyreanIndigo;
                        radiusTo = radius = 4f;
                    }},
                    new ShapePart() {{
                        mirror = false;
                        circle = true;
                        layer = Layer.effect;
                        y = 35.75f;
                        color = Color.white;
                        radiusTo = radius = 2.5f;
                    }},
                    new HoverPart(){{
                        color = ExoPal.empyreanIndigo;
                        circles = 2;
                        stroke = 3;
                        sides = 360;
                        phase = 100;
                        radius = 8f;
                        mirror = false;
                        layer = 109;
                        y = 35.75f;
                    }}
            );
            weapons.add(new Weapon("exogenesis-surge-torrent"){{
                reload = 83f;
                mirror = true;
                x = 15;
                y = 0f;
                shootSound = Sounds.blaster;
                recoil = 2;
                shoot = new ShootSpread(){{
                    spread = 7;
                    shots = 3;
                }};
                bullet = new BasicBulletType(9f, 32){{
                    width = 8;
                    height = 14f;
                    homingPower = 0.005f;
                    homingRange = 60;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanIndigo;
                    lifetime = 30f;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(Fx.shootSmallColor, ExoFx.colorSparkShoot);
                    trailLength = 10;
                    trailWidth = 1f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-surge-torrent"){{
                reload = 81f;
                mirror = true;
                x = 24;
                y = -4f;
                shootSound = Sounds.blaster;
                recoil = 2;
                shoot = new ShootSpread(){{
                    spread = 7;
                    shots = 3;
                }};
                bullet = new BasicBulletType(9f, 32){{
                    width = 8;
                    height = 14f;
                    homingPower = 0.005f;
                    homingRange = 60;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanIndigo;
                    lifetime = 30f;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(Fx.shootSmallColor, ExoFx.colorSparkShoot);
                    trailLength = 10;
                    trailWidth = 1f;
                }};
            }});
            weapons.add(new Weapon("energy-zap") {{
                reload = 30f;
                mirror = false;
                y = 35.75f;
                x = 0;
                rotate = true;
                rotateSpeed = 5.5f;
                shootSound = Sounds.spark;
                recoil = 0;
                inaccuracy = 15;
                shake = 1f;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.empyreanIndigo;
                    range = 250;
                    targetRange = 20;
                    damage = 40;
                    distanceDamageFalloff = 4;
                    chainLightning = 3;
                    segmentLength = 6;
                }};
            }});
        }};
        excelsus = new VanstarUnitType("excelsus") {{
            constructor = UnitEntity::create;
            aiController = SniperAI::new;
            outlineColor = ExoPal.empyreanOutline;
            shadowElevation = 2;
            speed = 1f;
            hitSize = 40f;
            health = 12600f;
            flying = true;
            drag = 0.07f;
            accel = 0.04f;
            faceTarget = true;
            lowAltitude = true;
            circleTarget = false;
            rotateMoveFirst = false;
            outlineRadius = 4;
            strafePenalty = 0.8f;
            armor = 12;
            trailLength = 8;
            trailColor = engineColor = ExoPal.empyreanIndigo;
            rotateSpeed = 0.8f;
            engineSize = 10;
            engineOffset = 45.25f;

            targetFlags = new BlockFlag[]{BlockFlag.turret, null, BlockFlag.battery, BlockFlag.generator, BlockFlag.core};

            setEnginesMirror(
                    new UnitEngine(24.25f, -41.25f, 5f, -45f),
                    new UnitEngine(23, -14.75f, 5f, 70f)
            );
            weapons.add(new Weapon("exogenesis-excelsus-cannon"){{
                x = 0;
                shootY = 24.5f;
                reload = 400;
                recoil = 12f;
                shootSound = Sounds.largeCannon;
                alternate = false;
                rotate = false;
                cooldownTime = 150f;
                shootCone = 3f;
                minWarmup = 0.95f;
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 18f;
                }};
                top = true;
                soundPitchMax = 1.1f;
                soundPitchMin = 0.9f;

                layerOffset = -0.001f;
                shake = 12;
                mirror = false;

                bullet = new TrailedEnergyBulletType(25f, 400f){{
                    recoil = 0.095f;
                    lifetime = 40f;
                    trailLength = 60;
                    trailWidth = 2F;
                    tracers = 1;
                    keepVelocity = false;
                    sprite = "circle-bullet";
                    tracerSpacing = 10f;
                    tracerUpdateSpacing *= 1.25f;

                    trailColor = hitColor = backColor = lightColor = lightningColor = ExoPal.empyreanIndigo;
                    width = 10f;
                    height = 40f;

                    hitSound = Sounds.plasmaboom;
                    despawnShake = hitShake = 18f;
					pierceArmor = true;

                    lightning = 3;
                    lightningLength = 6;
                    lightningLengthRand = 18;
                    lightningDamage = 20;

                    smokeEffect = Fx.smokePuff;
                    shootEffect = new MultiEffect(ExoFx.instShootExo, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    despawnEffect = new MultiEffect(ExoFx.empyreanExplosionSplash, ExoFx.empyreanStarHitLarge, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    hitEffect = new MultiEffect(ExoFx.empyreanExplosionSplash, ExoFx.empyreanStarHitLarge, ExoFx.randLifeSparkExo, Fx.massiveExplosion);
                    despawnHit = true;
                }};
            }});
        }};

        twinkle = new AxinUnitType("twinkle"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            isEnemy = false;
            envDisabled = 0;

            targetPriority = -2;
            lowAltitude = true;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 6f;
            mineTier = 3;
            buildSpeed = 1.2f;
            drag = 0.08f;
            speed = 5f;
            rotateSpeed = 3f;
            accel = 0.09f;
            itemCapacity = 40;
            health = 300f;
            armor = 2f;
            hitSize = 10f;
            engineSize = 3;
            engineOffset = 5;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = false;

            fogRadius = 0f;
            targetable = false;
            hittable = true;
            parts.addAll(
                    new EffectSpawnPart() {{
                        useProgress = true;
                        y = 0f;
                        effect = ExoFx.supernovaStarDecay;
                        effectColor = ExoPal.genesis;
                        randomEffectRot = 360;
                        effectChance = 0.3f;
                    }},
                    new HoverPart(){{
                        color = ExoPal.genesis;
                        circles = 3;
                        sides = 360;
                        stroke = 3;
                        phase = 100;
                        radius = 12f;
                        layer = Layer.flyingUnitLow -1;
                        mirror = false;
                        y = 0;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 114;
                        radiusTo = 1.7f;
                        radius = 0.5f;
                        color = Color.white;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 110;
                        radiusTo = 3f;
                        radius = 1f;
                        color = ExoPal.genesis;
                    }}
            );
            weapons.add(new Weapon("twinkle-star-middle") {{
                reload = 60;
                mirror = false;
                x = 0;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                shootCone = 30;
                shootY = 0;
                recoil = 0;
                bullet = new BasicBulletType(9, 24.5f){{
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    parts.addAll(
                            new FlarePart() {{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = 2.5f;
                                radius = 17;
                                radiusTo = 17;
                                stroke = 4.1f;
                            }},
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = -2.5f;
                                radius = 10;
                                radiusTo = 10;
                                stroke = 2.1f;
                            }});
                    trailWidth = 1.7f;
                    trailLength = 8;
                    drag = 0.004f;
                    homingPower = 0.0989f;
                    homingRange = 90;
                    homingDelay = 2;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    splashDamage = 15;
                    splashDamageRadius = 25;
                    lifetime = 40;
                    hitEffect = despawnEffect = ExoFx.colorBombSmall;
                }};
            }});
            weapons.add(new Weapon("twinkle-star-secondary") {{
                reload = 30;
                mirror = false;
                x = 0;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                shoot = new ShootSpread(2, 12f);
                shootCone = 30;
                shootY = 0;
                recoil = 0;
                bullet = new BasicBulletType(7.5f, 6.5f){{
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = 2f;
                                radius = 12;
                                radiusTo = 12;
                                stroke = 2.6f;
                            }}
                    );
                    trailWidth = 1.3f;
                    trailLength = 6;
                    drag = 0.002f;
                    homingPower = 0.0989f;
                    homingRange = 90;
                    homingDelay = 1;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    lifetime = 40;
                    hitEffect = despawnEffect = ExoFx.colorBombSmall;
                }};
            }});
        }};
        starlight = new AxinUnitType("starlight"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            isEnemy = false;
            envDisabled = 0;
            targetPriority = -2;
            lowAltitude = true;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 8f;
            mineTier = 3;
            buildSpeed = 1.4f;
            drag = 0.08f;
            speed = 6f;
            rotateSpeed = 2f;
            accel = 0.09f;
            itemCapacity = 90;
            health = 600f;
            armor = 3f;
            hitSize = 15f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;
            fogRadius = 0f;
            targetable = false;

            engineOffset = 7.2f;
            engineSize = 3.1f;

            parts.addAll(
                   /*
                    new BlackHolePart(){{
                        color = colorTo = ExoPal.genesis;
                        size = 0;
                        sizeTo = 3;
                        edge = 0;
                        edgeTo = 4;
                        mirror = false;
                        y = 0;
                    }},
                    */
                    new HoverPart(){{
                        color = ExoPal.genesis;
                        circles = 3;
                        sides = 360;
                        stroke = 3;
                        phase = 100;
                        radius = 7f;
                        mirror = true;
                        layer = Layer.flyingUnitLow -1;
                        x = 7.5f;
                        y = 5.5f;
                    }},
                    new HoverPart(){{
                        color = ExoPal.genesis;
                        circles = 3;
                        sides = 360;
                        stroke = 3;
                        phase = 100;
                        radius = 7f;
                        mirror = true;
                        layer = Layer.flyingUnitLow -1;
                        x = 7.5f;
                        y = -7.5f;
                    }}
            );
            /*
            weapons.add(new Weapon("starblast") {{
                reload = 60;
                mirror = false;
                x = 0;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                shootCone = 30;

                shootY = 0;
                recoil = 0;
                bullet = new BlackHoleBulletType(3f, 1f){{
                    lifetime = 100f;
                    growTime = 2;
                    force = 20;
                    horizonRadius = 10;
                    lensingRadius = 19;
                    suctionRadius = 95;
                    damageRadius = 30;
                    homingRange = 70;
                    homingPower = 0.007f;
                    swirlEffect = ExoFx.smolSwirl;
                    swirlEffects = 2;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.genesis;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
            }});
             */
            drawBuildBeam = false;
            weapons.add(new BuildWeapon("build-weapon"){{
                rotate = true;
                rotateSpeed = 7f;
                x = 7f;
                y = 0f;
                layerOffset = -0.001f;
                shootY = 0f;
            }});
        }};
        stardustVoyager = new AxinUnitType("stardust-voyager"){{
            constructor = UnitEntity::create;
            coreUnitDock = true;
            isEnemy = false;

            envDisabled = 0;

            targetPriority = -2;
            lowAltitude = true;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 3;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 6.5f;
            rotateSpeed = 2f;
            accel = 0.08f;
            itemCapacity = 110;
            health = 700f;
            armor = 3f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f/4f;
                y = 19f/4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};
        /*
        orion = new AxinUnitType("orion") {{
            constructor = LegsUnit::create;
            speed = 0.7f;
            hitSize = 13f;
            health = 460f;
            faceTarget = true;
            armor = 2;
            shadowElevation = 0.1f;
            targetAir = false;
            allowLegStep = true;
            hovering = true;
            rotateSpeed = 1.6f;
            legMoveSpace = 1.5f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.96f;
            legForwardScl = 1.1f;
            legPhysicsLayer = false;
            legGroupSize = 4;
            legCount = 8;
            legExtension = -2;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.2f;
            legBaseOffset = 5;
            legLength = 9;
            parts.add(
            new RegionPart("-blade"){{
                mirror = true;
                moves.add(new PartMove(PartProgress.recoil, 1.2f, 0f, 0f));
                heatColor = Color.valueOf("66B1FF");
                progress = PartProgress.warmup;
                heatProgress = PartProgress.warmup;
                moveRot = -26f;
            }}
            );
            weapons.add(new Weapon("orion") {{
                reload = 100f;
                mirror = false;
                x = 0;
                shootSound = Sounds.bolt;
                showStatSprite = false;
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                shootY = 3;
                recoil = 0;
                shake = 1f;
                        parts.add(
                            new ShapePart() {{
                            mirror = true;
                            progress = PartProgress.warmup;
                            hollow = true;
                            circle = true;
                            layer = Layer.effect;
                            y = 3f;
                            color = Color.valueOf("66B1FF");
                            stroke = 0f;
                            strokeTo = 1f;
                            radiusTo = radius = 3f;
                        }},
                        new HaloPart() {{
                            y = 3f;
                            radius = 2f;
                            tri = true;
                            color = Color.valueOf("66B1FF");
                            layer = Layer.effect;
                            haloRotateSpeed = -1f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 3f;
                            triLength = 0f;
                        }},
                        new HaloPart() {{
                            y = 3f;
                            radius = 2f;
                            tri = true;
                            color = Color.valueOf("66B1FF");
                            layer = Layer.effect;
                            haloRotateSpeed = 1f;
                            haloRadius = haloRadiusTo = 3f;
                            stroke = 0f;
                            strokeTo = 2f;
                            shapes = 2;
                            triLengthTo = 3f;
                            triLength = 0f;
                        }}
                        );
                bullet = new EmpBulletType() {{
                    width = 8f;
                    height = 11f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    lifetime = 40f;
                    speed = 6f;
                    damage = 25f;
                    recoil = 0.6f;
                    splashDamage = 15;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    radius = 40f;
                    timeIncrease = 0.5f;
                    powerDamageScl = 0.3f;
                    powerSclDecrease = 0.1f;
                    unitDamageScl = 0.3f;
                    status = StatusEffects.freezing;
                    statusDuration = 100;
                    shootEffect = Fx.lightningShoot;
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
        galileo = new AxinUnitType("galileo") {{
            constructor = LegsUnit::create;
            speed = 0.8f;
            hitSize = 13f;
            health = 920f;
            rotateSpeed = 2.5f;
            faceTarget = true;
            armor = 4;
            shadowElevation = 0.1f;
            targetAir = false;
            allowLegStep = true;
            hovering = true;
            legPhysicsLayer = false;
            legGroupSize = 4;
            legCount = 6;
            legExtension = -2;
            legMoveSpace = 0.8f;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.2f;
            legBaseOffset = 3;
            legLength = 18;
            parts.addAll(
                    new EffectSpawnPart() {{
                        useProgress = mirror = true;
                        y = 0f;
                        effect = ExoFx.supernovaSpark;
                        effectColor = ExoPal.genesis;
                        randomEffectRot = 30;
                        effectChance = 0.5f;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 114;
                        radiusTo = 1;
                        radius = 0.8f;
                        color = Color.white;
                    }},
                    new ShapePart() {{
                        circle = true;
                        y = 0f;
                        layer = 110;
                        radiusTo = 2.5f;
                        radius = 1.45f;
                        color = ExoPal.genesis;
                    }}
            );
            weapons.add(new Weapon("starshoot") {{
                reload = 5;
                mirror = false;
                x = 0;
                inaccuracy = 40;
                shootSound = Sounds.blaster;
                showStatSprite = false;
                smoothReloadSpeed = 0.15f;
                shootWarmupSpeed = 0.05f;
                minWarmup = 0.9f;
                shootCone = 30;
                shootY = 0;
                recoil = 0;
                bullet = new ExoBasicBulletType(6, 10){{
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = 3;
                                radius = 6;
                                radiusTo = 6;
                                stroke = 2f;
                            }}
                    );
                    trailWidth = 1.5f;
                    trailLength = 8;
                    homingPower = 0.0789f;
                    homingRange = 90;
                    homingDelay = 8;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    damageType = energy;
                    lifetime = 40;
                    hitEffect = despawnEffect = Fx.colorSpark;
                }};
            }});
        }};
        kuiper = new AxinUnitType("kuiper"){{
            speed = 0.5f;
            hitSize = 32f;
            rotateSpeed = 2.2f;
            health = 3100;
            armor = 4f;
            constructor = LegsUnit::create;
            legCount = 6;
            legLength = 21f;
            legForwardScl = 0.65f;
            legMoveSpace = 0.8f;
            legExtension = -15;
            legBaseOffset =  10;
            lockLegBase = legContinuousMove = faceTarget = true;
            legGroupSize = 3;
            legStraightness = 0.35f;
            baseLegStraightness = 0.3f;
            legMaxLength = 1.3f;
            rippleScale = 2;
            hovering = true;
            legSplashDamage = 22;
            legSplashRange = 30;
            drawShields = false;

            abilities.add(new ForceFieldAbility(60f, 0.3f, 400f, 60f, 360, 45));

            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("exogenesis-kuiper-weapon"){{
                shootSound = Sounds.shootBig;
                x = 19.25f;
                y = 0;
                shootY = 13.5f;
                shootX = -2.5f;
                shake = 3;
                reload = 70f;
                heatColor = Color.red;
                top = false;
                rotate = false;
                recoil = 2;
                inaccuracy = 2f;
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 4f;
                }};
                bullet = new EmpBulletType() {{
                    width = 11f;
                    height = 11f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    lifetime = 30f;
                    weaveMag = 0.5f;
                    weaveScale = 10;
                    speed = 6f;
                    damage = 95f;
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.colorBombSmall);
                    splashDamage = 15;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    radius = 40f;
                    timeIncrease = 0.5f;
                    powerDamageScl = 0.3f;
                    powerSclDecrease = 0.1f;
                    unitDamageScl = 0.8f;
                    hitSound = Sounds.dullExplosion;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.shootSmokeTitan);
                    homingPower = 0.0378f;
                    homingRange = 50;
                    homingDelay = 2;
                    trailLength = 10;
                    trailWidth = 3.5f;
                }};
            }});
        }};
        oort = new AxinUnitType("oort"){{
            constructor = LegsUnit::create;
            drag = 0.2f;
            speed = 0.43f;
            hitSize = 53f;
            health = 14500;
            rotateSpeed = 1.5f;
            armor = 7f;
            legCount = 4;
            legMoveSpace = 1.1f;

            lockLegBase = true;
            legContinuousMove = true;
            legSpeed = 0.001f;
            legLength = 18.5f;
            legForwardScl = 0.45f;
            rippleScale = 2f;
            stepShake = 4.5f;
            legBaseOffset = 12f;

            legSplashDamage = 62;
            legSplashRange = 30;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            parts.addAll(

                    new BlackHolePart(){{
                        color = colorTo = ExoPal.genesis;
                        progress = growProgress = PartProgress.charge;
                        size = 0;
                        sizeTo = 15;
                        edge = 0;
                        edgeTo = 19;
                        mirror = false;
                        y = 20;
                    }},
                    new RegionPart("-bodyside"){{
                        mirror = true;
                        progress = PartProgress.warmup;
                        children.add(
                        new RegionPart("-bodyside-bit"){{
                            progress = PartProgress.warmup;
                            layerOffset = -0.001f;
                            mirror = true;
                            under = true;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -3f, -5f));
                            y = 8.5f;
                            x = 10.75f;
                            moveRot = -15.3f;
                            moveY = 5;
                            moveX = 3.3f;
                        }},
                        new RegionPart("-bodyside2"){{
                            progress = PartProgress.warmup.delay(0.6f);
                            moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f), new PartMove(PartProgress.warmup.delay(0.5f), 0f, 0f, -25f));
                            layerOffset = -0.001f;
                            mirror = true;
                            under = true;
                            y = 2.75f;
                            x = 18.5f;
                        }});
                        moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                        moveRot = -5;
                        moveX = 3.5f;
                    }}
            );
            weapons.add(new Weapon("exogenesis-oort-weapon"){{
                shootSound = Sounds.malignShoot;
                mirror = false;
                top = false;
                x = 0;
                y = 20f;
                shootY = 0;
                reload = 400f;
                shake = 3f;
                heatColor = Color.red;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = shoot.firstShotDelay = 100 + 5f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                bullet = new BlackHoleBulletType(1f, 1400f / 35f){{
                    lifetime = 330f;
                    growTime = 15;
                    damageRadius = 20;
                    swirlEffects = 5;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.genesis;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
            }});
            weapons.add(new Weapon("exogenesis-align-laser"){{
                x = 18f;
                y = -7f;
                shootY = 8.25f;
                shootSound = Sounds.laser;
                rotate = true;
                alternate = true;
                layerOffset = 0.002f;
                rotateSpeed = 2.5f;
                reload = 69f;
                recoil = 2f;
                bullet = new FancyLaserBulletType(){{
                    damage = 125f;
                    sideAngle = 27f;
                    sideWidth = 1.5f;
                    sideLength = 20f;
                    width = 35f;
                    length = 200f;
                    colors = new Color[]{ExoPal.genesisDark.cpy().a(0.4f), ExoPal.genesis, Color.white};
                    hitEffect = ExoFx.coloredHitLarge;
                    hitColor = ExoPal.genesis;
                    shootEffect = ExoFx.hitEmpColorSpark;
                }};
            }});
        }};
        sirius = new AxinUnitType("sirius"){{
            constructor = LegsUnit::create;
            drag = 0.1f;
            speed = 0.35f;
            hitSize = 56;
            health = 47000;
            armor = 9f;
            drawShields = false;
            rotateSpeed = 1.2f;
            lockLegBase = true;
            legContinuousMove = true;
            legCount = 6;
            legLength = 50f;
            legSpeed = 0.8f;
            legMoveSpace = 0.85f;
            rippleScale = 3.2f;
            stepShake = 1.5f;
            legPairOffset = 3;
            legExtension = -15f;
            legBaseOffset = 22f;
            legMaxLength = 1.6f;

            legSplashDamage = 172;
            legSplashRange = 32;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            abilities.add(new TurretShield(){{
                region = "exogensis-sirius-shield";
                radius = 90f;
                drawArc = true;
                regen = 11f;
                cooldown = 60f * 8f;
                max = 6000f;
                y = -25f;
                width = 9f;
                whenShooting = true;
            }});
            parts.addAll(
            new EffectSpawnPart() {{
                useProgress = mirror = false;
                y = 18/4f;
                effect = ExoFx.supernovaSpark;
                randomEffectRot = 360;
                effectChance = 0.5f;
            }},
            new HoverPart(){{
                color = ExoPal.genesis;
                circles = 3;
                sides = 360;
                stroke = 3;
                phase = 200;
                radius = 28f;
                mirror = false;
                layer = Layer.effect;
                y = 18/4f;
            }},
            new ShapePart() {{
                circle = true;
                y = 18/4f;
                layer = 114;
                radiusTo = radius = 4f;
                color = Color.white;
            }},
            new ShapePart() {{
                circle = true;
                y = 18/4f;
                layer = 110;
                radiusTo = radius = 6.5f;
                color = ExoPal.genesis;
            }}
            );
            weapons.add(new Weapon("exogenesis-sirius-weapon"){{
                shootSound = Sounds.shootBig;
                mirror = true;
                rotationLimit = 30;
                shootCone = 70f;
                rotateSpeed = 0.5f;
                top = true;
                rotate = true;

                x = 38.5f;
                y = 0;
                shootY = 120f / 4f;
                shootX = -0.25f;
                recoil = 4f;
                reload = 45f;
                shake = 3f;
                layerOffset = -0.001f;

                shoot = new ShootSpread(3, 8f);
                heatColor = Color.red;
                bullet = new ExoBasicBulletType(9, 300){{
                    width = 9f;
                    height = 17f;
                    sprite = "circle-bullet";
                    shrinkY = shrinkX = 0f;
                    damageType = DamageType.explosive;

                    lifetime = 30;
                    pierceArmor = true;
                    pierce = true;
                    pierceCap = 3;
                    shootEffect = new MultiEffect(ExoFx.shootGiant, Fx.colorSparkBig);
                    hitEffect = despawnEffect = ExoFx.blastExplosionColor;
                    backColor = trailColor = hitColor = ExoPal.genesis;
                    lightningColor = lightColor = ExoPal.genesis;
                    trailChance = 1;
                    trailInterval = 1;
                    trailRotation = true;
                    trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                        lightOpacity = 0.5f;
                        line = true;
                        particles = 3;
                        cone = -30;
                        length = -85;
                        lifetime = 20;
                        lenFrom = 6;
                        lenTo = 0;
                        lightColor = colorFrom = ExoPal.genesis;
                        colorTo = ExoPal.genesisDark;
                    }}, new Effect(20,e->{
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.genesisDark,e.fout());
                        Tmp.v2.trns(e.rotation, e.fin()*10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                        Lines.stroke(6f*e.fout());
                    }));
                    trailLength = 13;
                    trailWidth = 3.5f;
                    lightningDamage = 40;
                    lightning = 3;
                    lightningLength = 2;
                    lightningLengthRand = 1;
                    lightningCone = 35f;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-align-ioner") {{
                reload = 245f;
                mirror = true;
                x = 25;
                y = -10;
                alternate = true;
                shootSound = Sounds.blaster;
                shootY = 8.5f;
                recoil = 2;
                rotateSpeed = 2.5f;

                rotate = true;
                shake = 1.5f;
                bullet = new ExoBasicBulletType(3.5f, 125){{
                    width = height = 22;
                    sprite = "exogenesis-plasma";
                    scaleLife = false;
                    damageType = DamageType.radiation;
                    hitSound = Sounds.dullExplosion;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    trailRotation = true;
                    collidesTiles = false;
                    lifetime = 125f;
                    splashDamage = 100;
                    splashDamageRadius = 50;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.blastExplosionColor, Fx.colorSpark);
                    intervalBullet = new ChainLightningBulletType() {{
                        lightningColor = ExoPal.genesis;
                        damageType = DamageType.radiation;
                        range = 125;
                        targetRange = 125;
                        damage = 20;
                        distanceDamageFalloff = 4;
                        chainLightning = 2;
                        segmentLength = 6;
                    }};
                    intervalBullets = 1;
                    bulletInterval = 5.3f;
                    lightning = 7;
                    lightningLength = 9;
                    lightningColor = ExoPal.radGreen;
                    lightningDamage = 41;
                    shootEffect = Fx.shootBigColor;
                    trailSinScl = 6;
                    trailSinMag = 0.3f;
                    trailParam = 5;
                    trailLength = 10;
                    trailWidth = 3.5f;
                }};
            }});
        }};
         */
        scout = new AxinUnitType("scout") {{
            constructor = LegsUnit::create;
            speed = 1.3f;
            hitSize = 13f;
            health = 360f;
            omniMovement = true;
            faceTarget = true;
            armor = 4;
            shadowElevation = 0.1f;
            targetAir = false;
            allowLegStep = false;
            hovering = true;
            rotateSpeed = 1.8f;
            legPhysicsLayer = false;
            legGroupSize = 1;
            legCount = 4;
            legExtension = -2;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.2f;
            legBaseOffset = 7;
            legLength = 10;
            weapons.add(new Weapon("exogenesis-scout-core") {{
                mirror = false;
                x = 0;
                top = false;
                reload = 40;
                shootSound = Sounds.none;
                shootY = 6.25f;
                recoil = 0;
                shake = 0f;
                rotate = true;
                rotateSpeed = 3.5f;

                bullet = new LaserBoltBulletType(8.2f, 8){{
                    lifetime = 35f;
                    shootEffect = ExoFx.hitMeltColor;
                    hitEffect = ExoFx.hitMeltColor;
                    backColor = ExoPal.genesis;
                    frontColor = Color.white;
                }};
            }});
        }};
        guard = new AxinUnitType("guard") {{
            constructor = LegsUnit::create;
            speed = 1.27f;
            hitSize = 17f;
            health = 890f;
            rotateSpeed = 1.8f;
            faceTarget = true;
            omniMovement = true;
            armor = 7;
            shadowElevation = 0.1f;
            allowLegStep = false;
            hovering = true;
            legPhysicsLayer = false;
            legGroupSize = 2;
            legCount = 5;
            legExtension = -2;
            legMoveSpace = 0.8f;
            legContinuousMove = true;
            lockLegBase = true;
            rippleScale = 0.7f;
            legBaseOffset = 9;
            legLength = 21;
            weapons.add(new Weapon("exogenesis-guard-core") {{
                reload = 5;
                mirror = top = false;
                x = 0;
                shoot = new ShootBarrel(){{
                    barrels = new float[]{
                            -7.0f, 7.0f, 45f,
                            0f, 9.75f, 0f,
                            7.0f, 7.0f, -45f,
                    };
                }};
                rotate = true;
                rotateSpeed = 3.5f;
                shootSound = Sounds.blaster;
                shootCone = 30;
                shootY = 0;
                recoil = 0;
                bullet = new BasicBulletType(5.5f, 10){{
                    width = height = 8;
                    sprite = "exogenesis-plasma";
                    homingPower = 0.007f;
                    homingRange = 50;
                    homingDelay = 1;
                    scaleLife = false;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    trailRotation = true;
                    lifetime = 35f;
                    shrinkY = shrinkX = 0;
                    shootEffect = new MultiEffect(ExoFx.hitEmpColorSpark);
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.blastExplosionColor, Fx.colorSpark);

                    lightning = 3;
                    lightningLength = 7;
                    lightningColor = ExoPal.genesis;
                    lightningDamage = 6;
                    shootEffect = Fx.shootBigColor;
                    trailLength = 10;
                    trailWidth = 2f;
                }};
            }});
        }};
        sentry = new AxinUnitType("sentry"){{
            speed = 1.25f;
            hitSize = 17f;
            rotateSpeed = 1.8f;
            health = 2900;
            armor = 10f;
            constructor = LegsUnit::create;
            legCount = 5;
            legLength = 21f;
            legForwardScl = 0.65f;
            legMoveSpace = 0.8f;
            legExtension = -6;
            legBaseOffset = 8.5f;
            lockLegBase = legContinuousMove = faceTarget = omniMovement = true;
            legGroupSize = 3;
            legMaxLength = 1.3f;
            rippleScale = 2;
            hovering = true;
            legSplashDamage = 22;
            legSplashRange = 30;

            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("exogenesis-sentry-core"){{
                shootSound = Sounds.none;
                x = 0f;
                y = 0;
                shootY = 12.5f;
                shake = 0;
                reload = 2f;
                top = false;
                rotate = true;
                rotateSpeed = 3.5f;
                recoil = 0;
                bullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.genesis;
                    shootEffect = Fx.hitEmpSpark;
                    range = 125;
                    targetRange = 10;
                    damage = 8;
                    distanceDamageFalloff = 2;
                    chainLightning = 1;
                    segmentLength = 6;
                }};
            }});
        }};
        sentinel = new AxinUnitType("sentinel"){{
            constructor = LegsUnit::create;
            drag = 0.2f;
            speed = 1.24f;
            hitSize = 25f;
            health = 12000;
            rotateSpeed = 1.8f;
            armor = 14f;
            legCount = 5;
            legMoveSpace = 1.1f;

            lockLegBase = true;
            legContinuousMove = true;
            legSpeed = 0.001f;
            legLength = 85f;
            legMaxLength = 1.5f;
            legForwardScl = 0.45f;
            rippleScale = 2f;
            stepShake = 4.5f;
            legBaseOffset = 13f;
            legExtension = -6;

            legSplashDamage = 62;
            legSplashRange = 30;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            weapons.add(new Weapon("exogenesis-sentinel-core"){{
                shootSound = Sounds.none;
                mirror = false;
                top = false;
                x = 0;
                y = 0f;

                rotate = true;
                rotateSpeed = 3.5f;
                shootY = 0;
                reload = 400f;
                shake = recoil = 0f;
                heatColor = Color.red;

                bullet = new BasicBulletType(6, 10){{
                    instantDisappear = true;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    lifetime = 40;
                    hitEffect = despawnEffect = shootEffect = smokeEffect = Fx.none;
                }};
            }});
            weapons.add(new Weapon("exogenesis-cores"){{
                shootSound = Sounds.cannon;
                mirror = true;
                top = false;
                x = 0;
                y = 0f;
                shoot = new ShootPattern(){{
                    shots = 1;
                    shotDelay = 4f;
                }};
                rotate = false;
                shootCone = 260;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                layerOffset = -0.001f;
                baseRotation = 45;
                shootY = 0;
                reload = 200f;
                shake = recoil = 0f;
                parts.addAll(
                        new RegionPart("-missile"){{
                            progress = PartProgress.reload.curve(Interp.pow2In);
                            y = 0;
                            rotation = -90;
                            colorTo = new Color(1f, 1f, 1f, 0f);
                            color = Color.white;
                            mixColorTo = ExoPal.genesis;
                            mixColor = new Color(1f, 1f, 1f, 0f);
                            mirror = true;
                            under = true;
                            moves.add(new PartMove(PartProgress.warmup.delay(0.8f), 0f, 0f, 45f));
                            moves.add(new PartMove(PartProgress.warmup.inv(), 16f, 0f, 0f));
                        }}
                );
                bullet = new BulletType(){{
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootSmokeTitan;
                    hitColor = ExoPal.genesis;
                    shake = 1f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = false;
                    spawnUnit = new MissileUnitType("sirava-missile"){{
                        targetAir = false;
                        speed = 4.6f;
                        maxRange = 5f;
                        outlineColor = ExoPal.genesisOutline;
                        health = 200;
                        homingDelay = 10f;
                        lowAltitude = true;
                        engineOffset = 10;
                        engineSize = 3f;
                        engineColor = trailColor = ExoPal.genesis;
                        engineLayer = Layer.effect;
                        deathExplosionEffect = Fx.none;
                        loopSoundVolume = 0.1f;
                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            shootOnDeath = true;
                            bullet = new ExplosionBulletType(140f, 25f){{
                                collidesAir = false;
                                shootEffect = new ExplosionEffect(){{
                                    lifetime = 50f;
                                    waveStroke = 5f;
                                    waveLife = 18f;
                                    waveColor = Color.white;
                                    sparkColor = smokeColor = ExoPal.genesis;
                                    waveRad = 30f;
                                    smokeSize = 4f;
                                    smokes = 7;
                                    smokeSizeBase = 0f;
                                    sparks = 10;
                                    sparkRad = 30f;
                                    sparkLen = 6f;
                                    sparkStroke = 2f;
                                }};
                            }};
                        }});
                    }};
                }};
            }});
            weapons.add(new Weapon("exogenesis-cores"){{
                shootSound = Sounds.cannon;
                mirror = true;
                top = false;
                x = 0;
                y = 0f;
                shoot = new ShootPattern(){{
                    shots = 1;
                    shotDelay = 4f;
                }};
                rotate = false;
                shootCone = 260;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                layerOffset = -0.001f;
                baseRotation = 90;
                shootY = 0;
                reload = 200f;
                shake = recoil = 0f;
                parts.addAll(
                        new RegionPart("-missile"){{
                            progress = PartProgress.reload.curve(Interp.pow2In);
                            y = 0;
                            rotation = -90;
                            colorTo = new Color(1f, 1f, 1f, 0f);
                            color = Color.white;
                            mixColorTo = ExoPal.genesis;
                            mirror = true;
                            mixColor = new Color(1f, 1f, 1f, 0f);
                            under = true;
                            moves.add(new PartMove(PartProgress.warmup.delay(0.8f), 0f, 0f, 135f));
                            moves.add(new PartMove(PartProgress.warmup.inv(), 16f, 0f, 0f));
                        }}
                );
                bullet = new BulletType(){{
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootSmokeTitan;
                    hitColor = ExoPal.genesis;
                    shake = 1f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = false;
                    spawnUnit = new MissileUnitType("siravax-missile"){{
                        targetAir = false;
                        speed = 4.6f;
                        maxRange = 5f;
                        outlineColor = ExoPal.genesisOutline;
                        health = 200;
                        homingDelay = 10f;
                        lowAltitude = true;
                        engineOffset = 10;
                        engineSize = 3f;
                        engineColor = trailColor = ExoPal.genesis;
                        engineLayer = Layer.effect;
                        deathExplosionEffect = Fx.none;
                        loopSoundVolume = 0.1f;
                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            shootOnDeath = true;
                            bullet = new ExplosionBulletType(140f, 25f){{
                                collidesAir = false;
                                shootEffect = new ExplosionEffect(){{
                                    lifetime = 50f;
                                    waveStroke = 5f;
                                    waveLife = 18f;
                                    waveColor = Color.white;
                                    sparkColor = smokeColor = ExoPal.genesis;
                                    waveRad = 30f;
                                    smokeSize = 4f;
                                    smokes = 7;
                                    smokeSizeBase = 0f;
                                    sparks = 10;
                                    sparkRad = 30f;
                                    sparkLen = 6f;
                                    sparkStroke = 2f;
                                }};
                            }};
                        }});
                    }};
                }};
            }});
        }};
        overseer = new AxinUnitType("overseer"){{
            constructor = LegsUnit::create;
            drag = 0.1f;
            speed = 1.23f;
            hitSize = 27;
            health = 45000;
            drawShields = false;

            rotateSpeed = 1.8f;
            armor = 20f;
            legCount = 7;
            legMoveSpace = 1.1f;

            lockLegBase = true;
            legContinuousMove = true;
            legSpeed = 0.001f;
            legLength = 62f;
            legForwardScl = 0.45f;
            rippleScale = 2f;
            stepShake = 4.5f;
            legBaseOffset = 12f;

            legSplashDamage = 172;
            legSplashRange = 32;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.8f;
            groundLayer = Layer.legUnit;
            weapons.add(new Weapon("exogenesis-overseer-core"){{
                shootSound = Sounds.none;
                mirror = false;
                shootCone = 70f;
                rotateSpeed = 3.5f;
                top = false;
                rotate = true;
                x = 0f;
                y = 0;
                recoil = 0f;
                reload = 45f;
                shake = 0f;
                bullet = new BasicBulletType(6, 10){{
                    instantDisappear = true;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    lifetime = 40;
                    hitEffect = despawnEffect = shootEffect = smokeEffect = Fx.none;
                }};
            }});
        }};
        /*
        catastrophe = new AxinUnitType("calamity") {{
            constructor = LegsUnit::create;
            fogRadius = 50;
            speed = 0.6f;
            hitSize = 96f;
            health = 10000000;
            outlineColor = Color.valueOf("0e1014");
            outlineRadius = 7;
            faceTarget = false;
            forceMultiTarget = true;
            armor = 230;
            allowLegStep = hovering = true;
            shadowElevation = 6f;
            groundLayer = Layer.darkness + 1f;
            rotateSpeed = 0.8f;
            legSpeed = 0.5f;
            legMoveSpace = 0.5f;
            legLength = 204;
            legCount = 10;
            legExtension = -25;
            legContinuousMove = lockLegBase = true;
            rippleScale = 10f;
            legBaseOffset = 53;
            legSplashDamage = 886;
            legSplashRange = 88;
            clipSize = 9999999f;
            parts.addAll(

                    new BlackHolePart(){{
                        color = colorTo = ExoPal.genesisTitan;
                        size = 23;
                        sizeTo = 23;
                        edge = 27;
                        edgeTo = 27;
                        mirror = false;
                        y = -8;
                    }},
                    new BlackHolePart(){{
                        color = colorTo = ExoPal.genesisTitan;
                        size = 5;
                        sizeTo = 5;
                        edge = 7;
                        edgeTo = 7;
                        moveY = 10;
                        mirror = false;
                        y = 98;
                    }},
                    new RegionPart("-mandible"){{
                        mirror = true;
                        layerOffset = -0.0001f;
                        progress = PartProgress.warmup.curve(Interp.slowFast);
                        moveRot = -16;
                        moveX = 3;
                        y = 17;
                        x = 77;
                    }},
                    new RegionPart("-small-mandible"){{
                        mirror = true;
                        layerOffset = -0.0002f;
                        progress = PartProgress.warmup.curve(Interp.slowFast);
                        moveRot = -12;
                        y = 38;
                        x = 59;
                    }}
            );
            immunities.addAll(Vars.content.statusEffects());
            abilities.add(new BlackHoleAbility(){{
                suctionRadius = 800f;
                swirlEffects = 0;
                damageRadius = 300;
                whenShooting = true;
                force = 3;
                damage = 1F;
                y = 108f;
                bulletForce = 0.4f;
                lensingRadius = 25;
                horizonRadius = 20;
                color = ExoPal.genesisTitan;
            }});
            weapons.add(new Weapon("comet bullets") {{
                reload = 180f;
                mirror = false;
                rotate = true;
                rotateSpeed = 6f;
                recoil = shake = 0;
                xRand = 30;
                x = 0;
                y = 0;
                shootSound = Sounds.malignShoot;
                shoot = new ShootMulti(new ShootSummon(0f, 0f, 300, 148f),
                new ShootPattern(){{
                    shotDelay = 8;
                    shots = 6;
                }});
                inaccuracy = 7;
                shootCone = 90;

                bullet = new FlakBulletType(8f, 700f){{
                    sprite = "missile-large";
                    lifetime = 105f;
                    width = 16f;
                    height = 28f;

                    hitSize = 7f;
                    shootEffect =  new MultiEffect(
                            new WaveEffect(){{
                                colorFrom = ExoPal.genesisLight;
                                colorTo = ExoPal.genesisTitan;
                                sizeFrom = 0;
                                sizeTo = 35f;
                                interp = Interp.circle;
                                lifetime = 35f;
                                strokeTo = 0;
                                strokeFrom = 3f;
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                length = 0;
                                lifetime = 50f;
                                layer = 114;
                                interp = Interp.swingIn;
                                colorFrom = colorTo = Color.black;
                                sizeFrom = 16f;
                                sizeTo = 0f;
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                length = 0;
                                lifetime = 50f;
                                interp = Interp.swingIn;
                                colorFrom = colorTo = ExoPal.genesisTitan;
                                sizeFrom = 18f;
                                sizeTo = 0f;
                            }}
                    );
                    smokeEffect = Fx.none;
                    ammoMultiplier = 1;
                    hitColor = backColor = trailColor = lightningColor = ExoPal.genesisTitan;
                    frontColor = Color.white;
                    trailWidth = 3.5f;
                    trailLength = 12;
                    hitEffect = despawnEffect = Fx.hitBulletColor;

                    trailRotation = true;
                    trailInterval = 3f;

                    homingPower = 0.17f;
                    homingDelay = 2f;
                    homingRange = 160f;

                    explodeRange = 160f;
                    explodeDelay = 0f;

                    flakInterval = 20f;
                    despawnShake = 3f;
                    trailChance = 0.7f;
                    trailEffect = new MultiEffect(
                            new ParticleEffect(){{
                                lightOpacity = 0.5f;
                                line = true;
                                particles = 35;
                                baseLength = -6.5f;
                                length = 75;
                                strokeFrom = 3;
                                strokeTo = 0;
                                cone = 20;
                                interp = Interp.pow5Out;
                                lifetime = 20;
                                lenFrom = 12;
                                lenTo = 8;
                                lightColor = colorFrom = ExoPal.genesisLight;
                                colorTo = ExoPal.genesisTitan;
                            }},
                            new ParticleEffect() {{
                                particles = 2;
                                length = baseLength = 6.5f;
                                lifetime = 40f;
                                sizeInterp = Interp.fastSlow;
                                colorFrom = colorTo = trailColor;
                                sizeFrom = 8f;
                                sizeTo = 0f;
                            }},
                            new ParticleEffect() {{
                                particles = 2;
                                length = baseLength = 6.5f;
                                lifetime = 30f;
                                sizeInterp = Interp.circleOut;
                                colorFrom = trailColor;
                                colorTo = trailColor.cpy().a(1f);
                                sizeFrom = 8f;
                                sizeTo = 4f;
                            }}
                    );
                    hitEffect = Fx.hitSquaresColor;
                    collidesGround = true;
                }};
            }});
            weapons.add(new Weapon("exogenesis-calamity-gunner") {{
                reload = 30f;
                mirror = rotate = alternate = true;
                rotateSpeed = recoil = shake = 2f;
                x = 45;
                y = 25;
                shootSound = Sounds.blaster;
                shoot = new ShootHelix(){{
                    scl = 3;
                    mag = 2;
                    shots = 2;
                }};
                shootY = 8;
                cooldownTime = 15;
                shootCone = 45;
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.heat;
                            color1 = ExoPal.genesisTitan;
                            y = 8;
                            sides = 2;
                            radius = 0;
                            radiusTo = 50;
                            stroke = 2.5f;
                        }}
                );
                bullet = new EmpBulletType(){{
                    width = 6f;
                    height = 9f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesisTitan;
                    lifetime = 60f;
                    speed = 7f;
                    damage = 300f;
                    splashDamage = 15;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    radius = 70f;
                    timeIncrease = 10f;
                    powerDamageScl = 0.3f;
                    powerSclDecrease = 0.5f;
                    unitDamageScl = 0.3f;
                    despawnHit = true;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    hitEffect = ExoFx.colorBombSmall;
                    shootEffect = Fx.lightningShoot;
                    trailLength = 12;
                    trailWidth = 3f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-calamity-gunner") {{
                reload = 30f;
                mirror = rotate = alternate = true;
                rotateSpeed = recoil = shake = 2f;
                x = 76;
                y = -8;
                shootSound = Sounds.blaster;
                shoot = new ShootHelix(){{
                    scl = 3;
                    mag = 2;
                    shots = 2;
                }};
                shootY = 8;
                cooldownTime = 15;
                shootCone = 45;
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.heat;
                            color1 = ExoPal.genesisTitan;
                            y = 8;
                            sides = 2;
                            radius = 0;
                            radiusTo = 50;
                            stroke = 2.5f;
                        }}
                );
                bullet = new EmpBulletType(){{
                    width = 6f;
                    height = 9f;
                    sprite = "circle-bullet";
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.genesisTitan;
                    lifetime = 60f;
                    speed = 7f;
                    damage = 300f;
                    splashDamage = 15;
                    splashDamageRadius = 40;
                    shrinkY = shrinkX = 0;
                    radius = 70f;
                    timeIncrease = 10f;
                    powerDamageScl = 0.3f;
                    powerSclDecrease = 0.5f;
                    unitDamageScl = 0.3f;
                    despawnHit = true;
                    status = StatusEffects.blasted;
                    statusDuration = 100;
                    hitEffect = ExoFx.colorBombSmall;
                    shootEffect = Fx.lightningShoot;
                    trailLength = 12;
                    trailWidth = 3f;
                }};
            }});
            weapons.add(new Weapon("exogenesis-clamaity-turret") {{
                reload = 100f;
                mirror = rotate = true;
                alternate = true;
                rotateSpeed = 1.5f;
                recoil = 0;
                shake = 2f;
                continuous = true;
                x = 56;
                y = 12;
                shootSound = Sounds.laser;
                shootY = 0;
                shootCone = 15;
                parts.addAll(
                        new BlackHolePart(){{
                            color = colorTo = ExoPal.genesisTitan;
                            growProgress = PartProgress.reload;
                            size = 5;
                            sizeTo = 0;
                            edge = 7;
                            edgeTo = 0;
                            mirror = false;
                            y = 0;
                        }}
                );
                bullet = new BlackHoleBulletType(3f, 3f){{
                    lifetime = 100f;
                    growTime = 15;
                    force = 20;
                    horizonRadius = 12;
                    lensingRadius = 19;
                    suctionRadius = 135;
                    damageRadius = 70;
                    swirlEffect = ExoFx.smolSwirl;
                    swirlEffects = 2;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.genesisTitan;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
            }});
            weapons.add(new Weapon("exogenesis-clamaity-turret") {{
                reload = 100f;
                mirror = rotate = true;
                alternate = true;
                rotateSpeed = 1.5f;
                recoil = 0;
                shake = 2f;
                x = 54;
                y = -38;
                shootSound = Sounds.laser;
                shootY = 0;
                shootCone = 15;
                parts.addAll(
                        new BlackHolePart(){{
                            color = colorTo = ExoPal.genesisTitan;
                            growProgress = PartProgress.reload;
                            size = 5;
                            sizeTo = 0;
                            edge = 7;
                            edgeTo = 0;
                            mirror = false;
                            y = 0;
                        }}
                );
                bullet = new BlackHoleBulletType(3f, 3f){{
                    lifetime = 100f;
                    growTime = 15;
                    force = 20;
                    horizonRadius = 12;
                    lensingRadius = 19;
                    suctionRadius = 135;
                    damageRadius = 70;
                    swirlEffect = ExoFx.smolSwirl;
                    swirlEffects = 2;
                    swirlInterval = 3;
                    color = hitColor = ExoPal.genesisTitan;
                    lightRadius = 8f;
                    lightOpacity = 0.7f;
                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                }};
            }});
        }};
         */
    }
}