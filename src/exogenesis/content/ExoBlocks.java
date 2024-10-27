package exogenesis.content;

import blackhole.entities.bullet.BlackHoleBulletType;
import exogenesis.entities.part.EffectSpawnPart;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.*;
import exogenesis.type.bullet.vanilla.ExoBasicBulletType;
import exogenesis.type.bullet.vanilla.ExoFireBulletType;
import exogenesis.type.bullet.vanilla.*;
import exogenesis.world.blocks.PowerHarvester;
import exogenesis.world.turrets.SpeedupTurret;
import exogenesis.graphics.ExoPal;
import arc.util.Tmp;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.Seq;
import mindustry.entities.effect.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.distribution.*;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;

import static exogenesis.type.DamageType.*;
import static mindustry.type.ItemStack.*;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;

public class ExoBlocks{
    public static Block
    //blocks
    astral, starFleet, cosmos, armada, astrology, stellar, coldPlasmaThrower, sagittarius, nebula, halley, magnetar, neutronMortar, biltzar,
    supernova, thuban, polaris, lunar, /*theia, constellation, tesseract, hypernovaBurst,*/
    genesisFactory,
    //erekir
    trueMechFabricator, supportFabricator, hoverFabricator, trueMechRefabricator, supportRefabricator, hoverRefabricator,
    zetaRefabricator, trueMechAssembler, supportAssembler, hoverAssembler, airTitanAssembler, groundTitanAssembler, leggedTitanAssembler;
    public static void load(){
        //genesis align
        //turrets Genesis Align
        //tier 1
        starFleet = new PowerTurret("starfleet"){{
            requirements(Category.turret, with(ExoItems.nickel, 35, ExoItems.curtuses, 100));
            squareSprite = false;
            range = 82f;
            reload = 35f;
            shootEffect = Fx.colorSpark;
            smokeEffect = Fx.none;
            outlineColor = Pal.darkOutline;
            shootY = 8;
            size = 1;
            xRand = 7;
            scaledHealth = 240;
            shootSound = Sounds.laser;
            shoot = new ShootPattern(){{
                shots = 4;
                shotDelay = 3;
            }};
            rotateSpeed = 2.5f;
            coolant = consumeCoolant(0.2f);
            consumePower(1f);
            drawer = new DrawTurret("genesux-");
            shootType = new ExoBasicBulletType(5, 5){{
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
                weaveMag = 2.2f;
                weaveScale = 7;
                trailWidth = 1.5f;
                trailLength = 8;
                homingPower = 0.0789f;
                homingRange = 150;
                width = height = 0f;
                shrinkX = shootY = 0;
                damageType = energy;
                lifetime = 20;
                hitEffect = despawnEffect = Fx.colorSpark;
            }};
        }};
        astral = new ItemTurret("astral"){{
            requirements(Category.turret, with(ExoItems.astrolite, 50, ExoItems.nickel, 80));
            squareSprite = false;
            range = 60f;
            recoil = 0.7f;
            reload = 35;
            shootEffect = Fx.colorSparkBig;
            smokeEffect = Fx.none;
            outlineColor = Pal.darkOutline;
            size = 1;
            scaledHealth = 240;
            maxAmmo = 15;
            shootSound = Sounds.bang;
            rotateSpeed = 2.5f;
            coolant = consumeCoolant(0.2f);
            drawer = new DrawTurret("genesux-");
            ammo(
                    ExoItems.curtuses, new ExoBasicBulletType(6f, 10) {{
                        backColor = hitColor = trailColor = ExoPal.letoColor;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 6;
                        width = height = 9;
                        shrinkX = shrinkY = 0;
                        damageType = cryogenic;
                        lifetime = 11;
                        hitEffect = despawnEffect = Fx.flakExplosion;
                    }},
                    ExoItems.astrolite, new ExoBasicBulletType(4f, 14) {{
                        backColor = hitColor = trailColor = ExoPal.genesis;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 6;
                        width = height = 9;
                        shrinkX = shrinkY = 0;
                        damageType = energy;
                        lifetime = 11;
                        hitEffect = despawnEffect = ExoFx.colorBombSmaller;
                    }},
                    ExoItems.nickel, new ExoBasicBulletType(8f, 6) {{
                        backColor = hitColor = trailColor = Color.valueOf("8bc99e");
                        rangeChange = 20;
                        frontColor = Color.white;
                        reloadMultiplier = 4;
                        trailWidth = 2f;
                        trailLength = 6;
                        width = height = 9;
                        shrinkX = shrinkY = 0;
                        damageType = kinetic;
                        lifetime = 11;
                        hitEffect = despawnEffect = ExoFx.blastExplosionColor;
                    }}
            );
        }};
        cosmos = new ItemTurret("cosmos"){{
            requirements(Category.turret, with(ExoItems.nickel, 120, ExoItems.curtuses, 80, Items.silicon, 50));
            range = 160f;
            recoil = 2f;
            reload = 135;
            smokeEffect = Fx.none;
            outlineColor = Pal.darkOutline;
            size = 2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;
            shootCone = 30f;
            rotateSpeed = 1.5f;
            coolant = consumeCoolant(0.2f);
            drawer = new DrawTurret("genesux-"){{
                new RegionPart("-back"){{
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -2;
                    mirror = false;
                }};
            }};
            ammo(
                    ExoItems.curtuses, new ExoArtilleryBulletType(){{
                        speed = 5f;
                        ammoPerShot = 5;
                        damageType = explosive;
                        trailWidth = 3f;
                        trailLength = 6;
                        shrinkX = shrinkY = 0;
                        splashDamage = 42;
                        splashDamageRadius = 55;
                        lifetime = 38;
                        width = 15;
                        height = 20;
                        backColor = hitColor = lightColor = trailColor = ExoPal.letoColor;
                        hitEffect = despawnEffect = new MultiEffect(ExoFx.coloredHitLarge);
                        smokeEffect = Fx.colorSpark;
                    }},
                    ExoItems.astrolite, new ExoArtilleryBulletType(){{
                        speed = 5f;
                        ammoPerShot = 5;
                        damageType = explosive;
                        trailWidth = 3f;
                        trailLength = 6;
                        shrinkX = shrinkY = 0;
                        splashDamage = 25;
                        splashDamageRadius = 55;
                        lifetime = 38;
                        width = 15;
                        height = 20;
                        backColor = hitColor = lightColor = trailColor = ExoPal.genesis;
                        hitEffect = despawnEffect = new MultiEffect(ExoFx.coloredHitLarge);
                        smokeEffect = Fx.colorSpark;
                        fragRandomSpread = 360f;
                        fragBullets = 5;
                        fragVelocityMin = 1f;

                        fragBullet = new ExoBasicBulletType(5, 9){{
                            width = 7f;
                            damageType = cryogenic;
                            height = 7f;
                            lifetime = 8f;
                            backColor = hitColor = trailColor = ExoPal.genesis;
                            frontColor = Color.white;
                            trailWidth = 1.3f;
                            trailLength = 6;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }}
            );
        }};
        armada = new PowerTurret("armada"){{
            requirements(Category.turret, with(ExoItems.nickel, 100, ExoItems.curtuses, 100, Items.graphite, 150));
            squareSprite = false;
            range = 140f;
            reload = 35f;
            shootEffect = ExoFx.colorSparkShoot;
            smokeEffect = Fx.none;
            outlineColor = Pal.darkOutline;
            shootY = 8;
            size = 2;
            xRand = 7;
            scaledHealth = 240;
            shootSound = Sounds.bolt;
            shoot = new ShootMulti(new ShootSummon(0f, 0f, 12, 20f),
                    new ShootPattern(){{
                        shots = 2;
                        shotDelay = 3;
                    }}
            );
            rotateSpeed = 1.5f;
            coolant = consumeCoolant(0.2f);
            consumePower(1f);
            drawer = new DrawTurret("genesux-");
            shootType = new ExoBasicBulletType(5, 10){{
                backColor = hitColor = trailColor = ExoPal.genesisDark;
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.life;
                            color1 = ExoPal.genesisDark;
                            spinSpeed = 3;
                            radius = 11;
                            radiusTo = 11;
                            stroke = 2.2f;
                        }}
                );
                weaveMag = 2.6f;
                weaveScale = 7;
                trailWidth = 1.5f;
                trailLength = 8;
                homingPower = 0.0789f;
                homingRange = 150;
                width = height = 0f;
                shrinkX = shootY = 0;
                damageType = energy;
                lifetime = 35;
                hitEffect = despawnEffect = ExoFx.coloredHitLarge;
            }};
        }};
        sagittarius = new PowerTurret("sagittarius"){{
            requirements(Category.turret, with(ExoItems.nickel, 400, ExoItems.selfHealingAlloy, 250, ExoItems.curtuses, 800, ExoItems.stellarIron, 600, Items.silicon, 500));
            squareSprite = false;
            targetAir = true;
            targetGround = false;
            range = 280f;
            recoil = 2f;
            reload = 10;
            outlineColor = Pal.darkOutline;
            size = 4;
            shootY = 4;
            minWarmup = 0.85f;
            shootWarmupSpeed = 0.05f;
            scaledHealth = 240;
            shootSound = Sounds.bolt;
            shoot = new ShootAlternate(){{
                shots = 3;
                barrels = 3;
                spread = 9;
            }};
            rotateSpeed = 2.5f;
            coolant = consumeCoolant(0.2f);
            consumePower(12f);
            drawer = new DrawTurret("genesux-"){{
                parts.addAll(
                        new RegionPart("-top"){{
                            mirror = false;
                            outlineLayerOffset = -0.004f;
                            layerOffset = 0.004f;
                        }},
                        new RegionPart("-side"){{
                            progress = PartProgress.warmup;
                            under = true;
                            moveX = 4f;
                            moveY = -2;
                            moveRot = -12;
                            outlineLayerOffset = -0.001f;
                            layerOffset = 0.001f;
                            mirror = true;
                            children.add(new RegionPart("-bottom"){{
                                mirror = true;
                                under = true;
                            }});
                        }},
                        new RegionPart("-body"){{
                            progress = PartProgress.warmup;
                            moveX = 1.5f;
                            moveRot = -8;
                            mirror = true;
                        }}
                );
            }};
            shootType = new ExoBasicBulletType(6, 32){{
                sprite = "exogenesis-energy-projectile";
                homingRange = 100;
                homingPower = 0.075f;
                shrinkX = shrinkY = 0;
                lifetime = 50;
                width = 8;
                height = 15;
                damageType = DamageType.energy;
                pierce = true;
                hitColor = trailColor = ExoPal.genesisDark;
                trailWidth = 1.5f;
                trailLength = 8;
                shootEffect = ExoFx.square45_6_45;
                hitEffect = despawnEffect = ExoFx.hitMeltColor;
                smokeEffect = Fx.colorSpark;
            }};
        }};
        stellar = new ContinuousLiquidTurret("stellar"){{
            requirements(Category.turret, with(ExoItems.selfHealingAlloy, 200,  Items.silicon, 300, ExoItems.curtuses, 500, ExoItems.nickel, 400));
            drawer = new DrawTurret("genesux-");
            squareSprite = false;
            scaledHealth = 240;
            shootY = 0f;
            size = 3;
            outlineColor = Pal.darkOutline;

            liquidConsumed = 10f / 60f;
            targetInterval = 5f;
            targetUnderBlocks = false;

            float r = range = 130f;

            loopSound = Sounds.torch;
            shootSound = Sounds.none;
            loopSoundVolume = 1f;
            ammo(
                    ExoLiquids.coldPlasma, new ContinuousFlameBulletType(){{
                        damage = 60f;
                        length = r;
                        knockback = 1f;
                        pierceCap = 3;
                        buildingDamageMultiplier = 0.3f;
                        flareColor = ExoPal.genesis;
                        colors = new Color[]{Color.valueOf("6666ff").a(0.55f), ExoPal.genesisDark.a(0.7f), ExoPal.genesis.a(0.8f), ExoPal.genesisLight, Color.white};
                    }}
            );
        }};
        astrology = new PowerTurret("astrology"){{
            requirements(Category.turret, with(ExoItems.astrolite, 430, ExoItems.selfHealingAlloy, 300, Items.silicon, 350, ExoItems.stellarIron, 400));
            range = 200f;
            recoil = 0f;
            reload = 80f;
            squareSprite = false;
            shootEffect = Fx.colorSparkBig;
            outlineColor = Pal.darkOutline;
            size = 3;
            shootY = 8;
            inaccuracy = 100;
            shootCone = 100;
            rotateSpeed = 1.5f;
            scaledHealth = 240;
            shootSound = Sounds.laser;
            shoot = new ShootPattern(){{
                shots = 4;
                shotDelay = 3;
            }};
            coolant = consumeCoolant(0.2f);
            consumePower(6f);
            shootType = new ExoBasicBulletType(5, 20){{
                backColor = hitColor = trailColor = ExoPal.genesis;
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.life;
                            color1 = ExoPal.genesis;
                            spinSpeed = 3;
                            radius = 16;
                            radiusTo = 16;
                            stroke = 2.8f;
                        }}
                );
                drag = 0.07f;
                weaveMag = 5f;
                weaveScale = 6;
                trailWidth = 1.5f;
                trailLength = 8;
                width = height = 0f;
                damageType = energy;
                lifetime = 65;
                hitEffect = despawnEffect = ExoFx.colorBombSmall;
                fragBullets = 1;
                fragBullet = new ExoBasicBulletType(6, 55){{
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = 6;
                                radius = 19;
                                radiusTo = 19;
                                stroke = 2.8f;
                            }}
                    );
                    weaveMag = 2.6f;
                    weaveScale = 7;
                    trailWidth = 2f;
                    trailLength = 8;
                    splashDamage = 15;
                    splashDamageRadius = 30;
                    homingPower = 0.0589f;
                    homingRange = 170;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    damageType = energy;
                    lifetime = 70;
                    hitEffect = despawnEffect = ExoFx.colorBombSmall;
                }};
            }};
        }};
        coldPlasmaThrower = new LiquidTurret("cold-plasmathrower"){{
            requirements(Category.turret, with(ExoItems.selfHealingAlloy, 600, ExoItems.nickel, 1000, ExoItems.stellarIron, 700, ExoItems.axionEnergyCell, 700, ExoItems.urbium, 300, ExoItems.axinvaxaAlloy, 400));
            range = 130f;
            recoil = 0;
            reload = 6f;
            squareSprite = false;
            targetAir = false;
            inaccuracy = 5f;
            heatColor = ExoPal.genesis;
            outlineColor = Pal.darkOutline;
            shootEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 2;
                        length = 140;
                        lifetime = 30;
                        interp = Interp.circleOut;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 5;
                        sizeTo = 1;
                        lightColor = ExoPal.genesis;
                        colorFrom = ExoPal.genesisLight;
                        colorTo = ExoPal.genesis;
                        cone = 10;
                    }},
                    new ParticleEffect(){{
                        particles = 2;
                        length = 100;
                        lifetime = 35;
                        interp = Interp.circleOut;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 6;
                        sizeTo = 1;
                        lightColor = ExoPal.genesis;
                        colorFrom = ExoPal.genesisLight;
                        colorTo = ExoPal.genesis;
                        cone = 15;
                    }},
                    new ParticleEffect(){{
                        particles = 2;
                        length = 110;
                        lifetime = 40;
                        interp = Interp.fastSlow;
                        sizeFrom = 6;
                        sizeTo = 1.5f;
                        lightColor = ExoPal.genesis;
                        colorFrom = ExoPal.genesis;
                        colorTo = ExoPal.genesisDark;
                        cone = 15;
                    }},
                    new ParticleEffect(){{
                        particles = 2;
                        length = 120;
                        lifetime = 50;
                        interp = Interp.fastSlow;
                        sizeFrom = 6;
                        sizeTo = 1.5f;
                        lightColor = ExoPal.genesis;
                        colorFrom = ExoPal.genesis;
                        colorTo = ExoPal.genesisDark;
                        cone = 10;
                    }});
            smokeEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 3;
                        length = 160;
                        lifetime = 50;
                        interp = Interp.fastSlow;
                        sizeFrom = 5;
                        sizeTo = 1;
                        lightColor = ExoPal.genesisDark;
                        colorFrom = ExoPal.genesisDark;
                        colorTo = ExoPal.genesisDarkAlpha;
                        cone = 15;
                    }},
                    new ParticleEffect(){{
                        particles = 3;
                        length = 180;
                        lifetime = 60;
                        interp = Interp.fastSlow;
                        sizeFrom = 7;
                        sizeTo = 1;
                        lightColor = ExoPal.genesisDark;
                        colorFrom = ExoPal.genesisDark;
                        colorTo = ExoPal.genesisDarkAlpha;
                        cone = 10;
                    }});
            size = 4;
            scaledHealth = 240;
            shootY = 3;
            shootSound = Sounds.flame;
            coolant = consumeCoolant(0.2f);
            shoot = new ShootPattern(){{
                shots = 4;
                shotDelay = 1;
            }};
            drawer = new DrawTurret("genesux-");
            ammo(
                    ExoLiquids.coldPlasma, new ExoBasicBulletType(5, 35){{
                        lightColor = hitColor = ExoPal.genesisDark;
                        damageType = cryogenic;
                        statusDuration = 60;
                        status = StatusEffects.freezing;
                        height = width = 0;
                        hitSize = 9;
                        hittable = reflectable = false;
                        pierce = pierceBuilding = true;
                        pierceCap = 4;
                        lifetime = 27;
                        despawnEffect = Fx.none;
                        hitEffect = ExoFx.hitMeltColor;
                    }}
            );
        }};
        nebula = new ItemTurret("nebula"){{
            requirements(Category.turret, with(ExoItems.nickel, 800, ExoItems.selfHealingAlloy, 550, ExoItems.stellarIron, 450, ExoItems.curtuses, 350, Items.graphite, 550));
            range = 105f;
            recoil = 3f;
            reload = 70f;
            shake = 2f;
            squareSprite = false;
            outlineColor = Pal.darkOutline;
            shootEffect = Fx.shootBigColor;
            smokeEffect = Fx.shootSmokeSquareSparse;
            size = 4;
            scaledHealth = 240;
            velocityRnd = 0.25f;
            shootCone = 90;
            shootSound = Sounds.shotgun;
            ammoPerShot = 4;
            maxAmmo = 40;
            consumeAmmoOnce = true;
            shoot = new ShootSpread(18, 7f);
            coolant = consumeCoolant(0.4f);

            drawer = new DrawTurret("genesux-"){{
                parts.addAll(
                        new RegionPart("-side"){{
                            moveY = -2;
                            moveX = 1.5f;
                            moveRot =- 16;
                            progress = PartProgress.recoil;
                            mirror = true;
                        }}
                );
            }};
            ammo(
                    ExoItems.axinvaxaAlloy, new ExoBasicBulletType(9f, 120){{
                        knockback = 4f;
                        width = 25f;
                        hitSize = 7f;
                        height = 20f;
                        damageType = kinetic;
                        ammoMultiplier = 1;
                        hitColor = backColor = trailColor = ExoPal.genesisDark;
                        frontColor = Color.white;
                        lifetime = 10;
                        trailWidth = 6f;
                        trailLength = 3;
                        hitEffect = despawnEffect = new MultiEffect( Fx.colorSpark,
                                new ExplosionEffect(){{
                                    smokes = 4;
                                    smokeSize = 4.7f;
                                    lifetime = 35;
                                    smokeSizeBase = 1.6f;
                                    smokeRad = 36;
                                    waveLife = 10;
                                    waveStroke = 4.1f;
                                    waveRad = 25;
                                    waveRadBase = 2.0f;
                                    sparkLen = 7;
                                    sparks = 12;
                                    lightColor = ExoPal.genesis;
                                    waveColor = sparkColor = ExoPal.genesis;
                                }});
                    }},
                    ExoItems.nickel, new ExoBasicBulletType(9f, 40){{
                        knockback = 6f;
                        width = 25f;
                        hitSize = 7f;
                        height = 20f;
                        damageType = kinetic;
                        ammoMultiplier = 1;
                        hitColor = backColor = trailColor = Color.valueOf("8bc99e");
                        frontColor = Color.white;
                        lifetime = 10;
                        trailWidth = 6f;
                        trailLength = 3;
                        hitEffect = despawnEffect = shootEffect = new MultiEffect( Fx.colorSpark,
                                new ExplosionEffect(){{
                                    smokes = 4;
                                    smokeSize = 4.7f;
                                    lifetime = 35;
                                    smokeSizeBase = 1.6f;
                                    smokeRad = 36;
                                    waveLife = 10;
                                    waveStroke = 4.1f;
                                    waveRad = 25;
                                    waveRadBase = 2.0f;
                                    sparkLen = 7;
                                    sparks = 12;
                                    lightColor = Color.valueOf("8bc99e");
                                    waveColor = sparkColor = Color.valueOf("8bc99e");

                                }});
                    }}
            );
        }};
        thuban = new SpeedupTurret("thuban"){{
            requirements(Category.turret, with(ExoItems.nickel, 800, ExoItems.selfHealingAlloy, 550, ExoItems.stellarIron, 450, ExoItems.curtuses, 350, Items.graphite, 550));
            range = 175f;
            recoil = 0f;
            reload = 70f;
            shake = 2f;
            squareSprite = false;
            outlineColor = Pal.darkOutline;
            shootEffect = Fx.shootBigColor;
            smokeEffect = Fx.shootSmokeSquareSparse;
            size = 5;
            scaledHealth = 240;
            warmupMaintainTime = 120f;
            maxSpeedupScl = 9f;
            speedupPerShoot = 0.095f;
            overheatTime = 800f;
            shootCone = 90;
            shootSound = Sounds.spark;
            ammoPerShot = 4;
            maxAmmo = 40;
            consumeAmmoOnce = true;
            shoot = new ShootAlternate(){{
                shots = 1;
                barrels = 2;
                spread = 16;
            }};
            coolant = consumeCoolant(0.4f);
            shoot = new ShootAlternate(3.5f);

            recoils = 2;
            drawer = new DrawTurret("genesux-"){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    int finalI = i;
                    parts.add(new RegionPart("-side-" + (finalI == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        mirror = true;
                        under = true;
                        moveRot = 19;
                        moveX = 5.5f;
                        moveY = 3.5f;
                        children.add(new RegionPart("-side2-" + (finalI == 0 ? "l" : "r")){{
                            recoilIndex = f;
                            progress = PartProgress.warmup.delay(0.6f);
                            moves.add(new PartMove(PartProgress.warmup.delay(0.5f), 0f, -3f, 0f));
                            mirror = true;
                            moveX = 2;
                            moveY = 9f;
                        }});
                    }});
                    new RegionPart("-bottom") {{
                        mirror = false;
                    }};
                }
            }};

            shootType = new ChainLightningBulletType() {{
                lightningColor = ExoPal.genesis;
                damageType = DamageType.energy;
                width = 12;
                shootEffect = new Effect(20,e->{
                    Draw.z(Layer.effect);
                    Draw.color(ExoPal.genesis,e.fout());
                    Tmp.v1.trns(e.rotation, e.fin()*20f);
                    Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 0.8f*e.fin()+0.1f, 8,16, e.rotation);
                    Tmp.v2.trns(e.rotation, e.fin()*10f);
                    Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                    Lines.stroke(6f*e.fout());
                });
                smokeEffect = new Effect(10, e -> {
                    color(e.color);
                    float w = 1.2f + 7 * e.fout();

                    Drawf.tri(e.x, e.y, w, 45f * e.fout(), e.rotation);
                    color(e.color);

                    for(int i : Mathf.signs){
                        Drawf.tri(e.x, e.y, w * 0.9f, 18f * e.fout(), e.rotation + i * 90f);
                    }

                    Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
                });
                range = 145;
                targetRange = 45;
                damage = 120;
                distanceDamageFalloff = 2;
                chainLightning = 2;
                segmentLength = 5;
            }};
        }};
        halley = new ContinuousTurret("halley"){{
            requirements(Category.turret, with(ExoItems.oltuxium, 15, ExoItems.cobolt, 20, ExoItems.quartz, 20));
            range = 100f;
            recoil = 0f;
            squareSprite = false;
            shootEffect = ExoFx.colorBombSmaller;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            outlineColor = ExoPal.empyreanOutline;
            shootY = 4;
            size = 4;
            scaledHealth = 280;
            shootSound = Sounds.none;
            loopSoundVolume = 1f;
            loopSound = Sounds.laserbeam;

            shootWarmupSpeed = 0.08f;
            shootCone = 360f;

            rotateSpeed = 2.5f;
            coolant = consumeCoolant(0.2f);

            consumePower(6f);
            drawer = new DrawTurret("elecian-"){{
                parts.addAll(
                        new RegionPart("-front"){{
                            progress = PartProgress.warmup;
                            moveRot = -12;
                            moveX = 4;
                            mirror = true;
                        }}
                );
            }};
            shootType = new ExoPointLaserBulletType(){{
                hitColor = trailColor = ExoPal.empyreanIndigo;
                color = Color.white;
                damageType = DamageType.energy;
                sprite = "exogenesis-focal-point-laser";
                beamEffect = Fx.none;
                trailLength = 8;
                damage = 10;
                hitEffect = ExoFx.hitMeltColor;
                smokeEffect = Fx.colorSparkBig;
            }};
        }};
        lunar = new PowerTurret("lunar"){{
            requirements(Category.turret, with(ExoItems.rustyCopper, 420, Items.silicon, 300, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.lightningStone, 250, ExoItems.vanstariumAlloy, 200, ExoItems.empyreanPlating, 300, ExoItems.litusiumAlloy, 150));
            range = 260f;
            recoil = 3f;
            reload = 100f;
            shootEffect = Fx.shootSmokeSmite;
            heatColor = Color.red;
            outlineColor = ExoPal.empyreanOutline;
            size = 4;
            minWarmup = 0.99f;
            shoot.firstShotDelay = 80;
            shootY = 9;
            scaledHealth = 280;
            cooldownTime = 320;
            shootSound = Sounds.laser;
            shootCone = 65f;
            coolant = consumeCoolant(0.2f);
            consumePower(6f);
            drawer = new DrawTurret("genesux-"){{
                parts.add(
                        new RegionPart("-blade"){{
                            progress = PartProgress.charge.curve(Interp.pow2In);
                            moveX = 3.5f;
                            moveY = -4;
                            moveRot = 15;
                            mirror = true;
                        }},
                        new RegionPart("-cell"){{
                            progress = PartProgress.charge.curve(Interp.pow2In);
                            moveY = -2f;
                            moveRot = 7;
                            mirror = true;
                        }}
                );
            }};
            shootType = new ExoBasicBulletType(8f, 207){{
                lifetime = 35f;
                backColor = lightColor = lightningColor = trailColor = hitColor = ExoPal.genesis;
                sprite = "exogenesis-plasma";
                damageType = energy;
                hitSize = 18f;
                width = 45f;
                height = 45;
                smokeEffect = ExoFx.square45_6_45;
                hitEffect = ExoFx.square45_6_45;
                despawnEffect = new Effect(35f, 70f, e -> {
                    Draw.color(e.color, Color.white, e.fout() * 0.7f);
                    for(int i : Mathf.signs){

                        Drawf.tri(e.x, e.y, height * 1.5f * e.fout(), width * 0.885f * e.fout(), e.rotation + i * 90);
                        Drawf.tri(e.x, e.y, height * 0.8f * e.fout(), width * 0.252f * e.fout(), e.rotation + 90 + i * 90);
                    }
                });
                fragBullets = 8;
                fragSpread = 45;
                fragRandomSpread = 0;
                fragBullet = new ExoBasicBulletType(6, 55){{
                    backColor = hitColor = trailColor = ExoPal.genesis;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.genesis;
                                spinSpeed = 6;
                                radius = 15;
                                radiusTo = 15;
                                stroke = 2f;
                            }}
                    );
                    trailWidth = 2f;
                    trailLength = 8;
                    homingPower = 0.0889f;
                    homingRange = 170;
                    width = height = 0f;
                    shrinkX = shootY = 0;
                    damageType = energy;
                    lifetime = 60;
                    hitEffect = despawnEffect = ExoFx.colorBombSmall;
                }};
            }};
        }};
        magnetar = new LaserTurret("magnetar"){{
            requirements(Category.turret, with(ExoItems.cobolt, 350, Items.silicon, 280, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.viliotStone, 250, ExoItems.iron, 170, ExoItems.empyreanPlating, 200, ExoItems.litusiumAlloy, 150, ExoItems.vastanium, 170, ExoItems.vanstariumAlloy, 180));
            range = 340f;
            recoil = 2f;
            shake = 4f;
            shootEffect = Fx.colorSparkBig;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            outlineColor = ExoPal.genesisOutline;
            cooldownTime = 30;
            reload = 400;
            size = 5;
            minWarmup = 0.85f;
            shootWarmupSpeed = 0.07f;
            scaledHealth = 280;
            shootY = 10;
            shoot.firstShotDelay = 480;
            rotateSpeed = 0.8f;
            firingMoveFract = 0.35f;
            loopSound = ExoSounds.funnylaserloop;
            shootSound = ExoSounds.bigLaserShoot;
            chargeSound = Sounds.torch;
            shootDuration = 800f;
            loopSoundVolume = 1.1f;
            coolant = consumeCoolant(0.2f);
            consumePower(26f);
            drawer = new DrawTurret("genesux-"){{
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.recoil;
                            color1 = ExoPal.genesis;
                            y = 9;
                            spinSpeed = 1.5f;
                            sides = 4;
                            radius = 0;
                            radiusTo = 150;
                            stroke = 2.5f;
                        }},
                        new FlarePart(){{
                            progress = PartProgress.recoil;
                            color1 = ExoPal.genesis;
                            y = 9;
                            spinSpeed = -1.9f;
                            sides = 4;
                            radius = 0;
                            radiusTo = 100;
                            stroke = 2.1f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            y = 9f;
                            progress = PartProgress.heat;
                            effectColor = ExoPal.genesisDark;
                            effect = ExoFx.singleSpark;
                            height = 33;
                            width = 18;
                            randomEffectRot = 0f;
                            effectChance = 0.5f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.heat;
                            height = 33;
                            width = 18;
                            randomEffectRot = 0f;
                            y = 9f;
                            effect = new MultiEffect(
                            new ParticleEffect() {{
                                particles = 3;
                                cone = 1;
                                layer = 109;
                                length = 80;
                                interp = Interp.circleOut;
                                lifetime = 30;
                                sizeFrom = 4;
                                sizeTo = 0;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesisDark;
                            }},
                            new ParticleEffect() {{
                                particles = 3;
                                cone = 1;
                                layer = 109;
                                length = 120;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                lifetime = 20;
                                sizeFrom = 2;
                                sizeTo = 0;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesisDark;
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                cone = 1;
                                layer = 109;
                                length = 60;
                                lifetime = 20;
                                lenFrom = 15;
                                lenTo = 8;
                                strokeFrom = 0.5f;
                                strokeTo = 1.5f;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesis;
                            }}
                            );
                            effectColor = ExoPal.genesis;
                            effectChance = 0.5f;
                        }},
                        //backstar effects
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.recoil;
                            y = -2.5f;
                            effect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 3;
                                        cone = 360;
                                        layer = 109;
                                        length = 80;
                                        interp = Interp.circleOut;
                                        lifetime = 30;
                                        sizeFrom = 4;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.genesisLight;
                                        colorTo = ExoPal.genesisDark;
                                    }}
                            );
                            effectColor = ExoPal.genesis;
                            randomEffectRot = 0;
                            effectChance = 0.5f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            y = -2.5f;
                            progress = PartProgress.recoil;
                            effectColor = ExoPal.genesisDark;
                            effect = ExoFx.randLifeSparkExo;
                            randomEffectRot = 360f;
                            effectChance = 0.1f;
                        }},
                        /*
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.heat;
                            y = -5f;
                            effectColor = ExoPal.genesis;
                            effect = ExoFx.supernovaStarDecay;
                            randomEffectRot = 360;
                            effectChance = 0.5f;
                        }},
                        */
                        //charge effects
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.charge;
                            rotation = 180;
                            y = 9f;
                            effectColor = ExoPal.genesis;
                            effect = new ParticleEffect() {{
                                particles = 2;
                                line = true;
                                rotWithParent = true;
                                cone = 25;
                                length = 60;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                baseLength = -60;
                                lifetime = 10;
                                lenFrom = 7;
                                lenTo = 4;
                                strokeFrom = 0.5f;
                                strokeTo = 1.5f;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesisLight;
                            }};
                            randomEffectRot = 0;
                            effectChance = 0.25f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.charge;
                            rotation = 180;
                            y = 9f;
                            effectColor = ExoPal.genesis;
                            effect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                rotWithParent = true;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                cone = 65;
                                length = 60;
                                baseLength = -60;
                                lifetime = 24;
                                lenFrom = 5;
                                lenTo = 4;
                                strokeFrom = 0.5f;
                                strokeTo = 1.8f;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesisLight;
                            }};
                            randomEffectRot = 0;
                            effectChance = 0.6f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.charge;
                            rotation = 180;
                            y = 9f;
                            effectColor = ExoPal.genesis;
                            effect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                rotWithParent = true;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                cone = 10;
                                length = 80;
                                baseLength = -80;
                                lifetime = 28;
                                lenFrom = 15;
                                lenTo = 7;
                                strokeFrom = 0.5f;
                                strokeTo = 1f;
                                lightColor = colorFrom = ExoPal.genesis;
                                colorTo = ExoPal.genesisLight;
                            }};
                            randomEffectRot = 0;
                            effectChance = 0.6f;
                        }},
                        /*
                        new EffectSpawnPart() {{
                            useProgress = mirror = true;
                            progress = PartProgress.heat;
                            x = 4f;
                            effect = new WaveEffect() {{
                                lifetime = 40;
                                interp = Interp.sineOut;
                                strokeFrom = 0;
                                strokeTo = 4;
                                sizeFrom = 45;
                                sizeTo = 0f;
                                lightColor = colorFrom = ExoPal.starBlue;
                                colorTo = ExoPal.genesis;
                            }};
                            effectChance = 0.1f;
                        }},
                        */
                        //Star
                        new ShapePart() {{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            circle = true;
                            moveY = -5;
                            layer = 114;
                            radiusTo = 1.5f;
                            radius = 0f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            circle = true;
                            moveY = -5;
                            layer = 110;
                            radiusTo = 2.5f;
                            radius = 0f;
                            color = ExoPal.genesis;
                        }},
                        //recoil
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            circle = true;
                            moveY = -2.5f;
                            layer = 114;
                            radiusTo = 1.5f;
                            radius = 0f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            circle = true;
                            moveY = -2.5f;
                            layer = 110;
                            radiusTo = 2.5f;
                            radius = 0f;
                            color = ExoPal.genesis;
                        }},
                        //StarFront
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            progress = PartProgress.charge;
                            effectColor = ExoPal.genesisDark;
                            y = shootY;
                            height = 3;
                            width = 9;
                            effect = ExoFx.randLifeSparkExo;
                            randomEffectRot = 10f;
                            effectChance = 0.1f;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.charge;
                            circle = true;
                            y = shootY;
                            layer = 114;
                            radiusTo = 1.5f;
                            radius = 0f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.charge;
                            circle = true;
                            y = shootY;
                            layer = 110;
                            radiusTo = 3;
                            radius = 0f;
                            color = ExoPal.genesis;
                        }},
                        new RegionPart("-barrel"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            outlineLayerOffset = -2;
                            layerOffset = 1;
                            moves.add(new PartMove(PartProgress.charge.delay(0.60f).blend(PartProgress.recoil, 0.5f), 0f, -4f, 0f));
                            moveX = 3f;
                            mirror = true;
                        }},
                        new RegionPart("-body-top"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            outlineLayerOffset = -2;
                            layerOffset = 1;
                            moveY = -5f;
                            mirror = false;
                        }},
                        new RegionPart("-body"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveY = -4f;
                            under = true;
                            mirror = false;
                        }},

                        new RegionPart("-bottom-seg"){{
                            outlineLayerOffset = -2;
                            layerOffset = 1;
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveY = -5;
                            moveRot = 9;
                            mirror = true;
                        }},
                        new RegionPart("-bodyseg2"){{
                            outlineLayerOffset = -2;
                            layerOffset = 1;
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveRot = -16;
                            moveY = -3;
                            moveX = 3;
                            under = true;
                            mirror = true;
                        }},
                        new RegionPart("-bodyseg1"){{
                            outlineLayerOffset = -2;
                            layerOffset = 1;
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveRot = -12;
                            moveY = 2.5f;
                            moveX = 2.5f;
                            under = true;
                            mirror = true;
                        }},
                        new RegionPart("-bottom-barrel"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            layerOffset = -1;
                            moveY = -4f;
                            mirror = false;
                        }}
                );
            }};
            shootType = new ContinuousLaserBulletType(){{
                hitColor = ExoPal.genesis;
                layer = Layer.effect;
                damage = 180f;
                pointyScaling = 0.5f;
                length = 340f;
                hitEffect = new MultiEffect(
                        new ParticleEffect(){{
                            line = true;
                            rotWithParent = true;
                            colorFrom = ExoPal.genesisLight;
                            colorTo = ExoPal.genesis;
                            cone = 35;
                            particles = 3;
                            length = 100;
                            lifetime = 21f;
                            lenFrom = 10;
                            lenTo = 7;
                            strokeFrom = 2f;
                            strokeTo = 0.8f;
                        }},
                        new ParticleEffect(){{
                            line = true;
                            rotWithParent = true;
                            colorFrom = ExoPal.genesisLight;
                            colorTo = ExoPal.genesis;
                            cone = 45;
                            particles = 2;
                            length = 85;
                            lifetime = 21f;
                            lenFrom = 10;
                            lenTo = 10;
                            strokeFrom = 2f;
                            strokeTo = 0.8f;
                        }});
                intervalBullet = new LightningBulletType(){{
                    damage = 16;
                    ammoMultiplier = 1f;
                    lightningColor = ExoPal.genesis;
                    lightningLength = 9;
                    lightningLengthRand = 14;
                }};
                intervalRandomSpread = 18;
                intervalBullets = 2;
                bulletInterval = 3f;

                drawSize = 260f;
                pointyScaling = 0.5f;
                shootEffect = new Effect(20,e->{
                    Draw.z(Layer.effect);
                    Draw.color(ExoPal.empyreanIndigo,e.fout());
                    Tmp.v1.trns(e.rotation, e.fin()*20f);
                    Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 0.8f*e.fin()+0.1f, 8,16, e.rotation);
                    Tmp.v2.trns(e.rotation, e.fin()*10f);
                    Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                    Lines.stroke(6f*e.fout());
                });
                oscScl = 0.5f;
                oscMag = 1.8f;
                width = 3.6f;
                shake = 2f;
                colors = new Color[]{ExoPal.starBlue.cpy().a(.6f), ExoPal.genesisDark.cpy().a(.8f), ExoPal.genesis, Color.white};
                despawnEffect = Fx.none;
            }};
        }};
        neutronMortar = new ItemTurret("neutron-mortar"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 420, Items.silicon, 300, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.lightningStone, 250, ExoItems.vanstariumAlloy, 200, ExoItems.empyreanPlating, 300, ExoItems.litusiumAlloy, 150));
                range = 290f;
                recoil = 3f;
                reload = 450f;
                shake = 4f;
                rotateSpeed = 0.4f;
                shootEffect = Fx.shootSmokeSmite;
                heatColor = Color.red;
                outlineColor = ExoPal.genesisOutline;
                size = 7;
                shootY = 18;
                scaledHealth = 280;
                cooldownTime = 320;
                shootSound = Sounds.mediumCannon;
                shootCone = 35f;
                minWarmup = 0.85f;
                shootWarmupSpeed = 0.02f;
                drawer = new DrawTurret("genesux-"){{
                    parts.addAll(
                            new RegionPart("-body"){{
                                progress = PartProgress.warmup;
                                outlineLayerOffset = -2;
                                layerOffset = 1;
                                mirror = false;
                            }},
                            new RegionPart("-side"){{
                                progress = PartProgress.warmup;
                                moveX = -2f;
                                moves.add(new PartMove(PartProgress.recoil, -2.5f, 0f, 0f));
                                mirror = true;
                            }},
                            new RegionPart("-barrel"){{
                                progress = PartProgress.warmup;
                                under = true;
                                moves.add(new PartMove(PartProgress.recoil, 0f, -10f, 0f));
                                moveY = 10f;
                                mirror = false;
                            }}
                    );
                }};
                coolant = consumeCoolant(0.2f);
                consumePower(6f);
                ammo(
                        ExoItems.axionCell, new ArtilleryBulletType(3.5f, 650, "shell") {{
                            hitEffect = despawnEffect = new MultiEffect(ExoFx.neutronMorterShockWave, ExoFx.empyreanStarHitLarge, ExoFx.odinNukeExplosion);
                            knockback = 8f;
                            lifetime = 140f;
                            height = 39f;
                            width = 37f;
                            splashDamageRadius = 65f;
                            splashDamage = 1150f;
                            scaledSplashDamage = true;
                            backColor = hitColor = trailColor = ExoPal.genesis;
                            frontColor = Color.white;
                            ammoMultiplier = 1f;
                            hitSound = Sounds.largeExplosion;

                            status = StatusEffects.blasted;
                            trailLength = 32;
                            trailWidth = 5.35f;
                            trailSinScl = 2.5f;
                            trailSinMag = 0.5f;
                            trailEffect = Fx.none;
                            despawnShake = 7f;

                            shootEffect = Fx.shootTitan;
                            smokeEffect = Fx.shootSmokeTitan;

                            trailInterp = v -> Math.max(Mathf.slope(v), 0.8f);
                            shrinkX = 0.2f;
                            shrinkY = 0.1f;
                            buildingDamageMultiplier = 0.3f;
                        }}
                );
            }};
        biltzar = new LaserTurret("blitzar"){{
            requirements(Category.turret, with(ExoItems.cobolt, 350, Items.silicon, 280, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.viliotStone, 250, ExoItems.iron, 170, ExoItems.empyreanPlating, 200, ExoItems.litusiumAlloy, 150, ExoItems.vastanium, 170, ExoItems.vanstariumAlloy, 180));
            range = 550f;
            recoil = 2f;
            shake = 4f;
            shootEffect = Fx.colorSparkBig;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            outlineColor = ExoPal.genesisOutline;
            cooldownTime = 30;
            reload = 400;
            size = 7;
            minWarmup = 0.85f;
            shootWarmupSpeed = 0.07f;
            scaledHealth = 280;
            shootY = 10;
            shoot.firstShotDelay = 280;
            shootDuration = 800;
            firingMoveFract = 0.35f;
            rotateSpeed = 0.5f;
            loopSound = ExoSounds.funnylaserloop;
            shootSound = ExoSounds.bigLaserShoot;
            chargeSound = Sounds.torch;
            loopSoundVolume = 1.1f;
            coolant = consumeCoolant(0.2f);
            consumePower(26f);
            drawer = new DrawTurret("genesux-"){{
                parts.addAll(
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            y = 9f;
                            progress = PartProgress.heat;
                            effectColor = ExoPal.genesisDark;
                            effect = ExoFx.singleSpark;
                            height = 33;
                            width = 18;
                            randomEffectRot = 0f;
                            effectChance = 0.5f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.heat;
                            height = 33;
                            width = 18;
                            randomEffectRot = 0f;
                            y = 9f;
                            effect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 1;
                                        cone = 1;
                                        layer = 109;
                                        length = 120;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        lifetime = 20;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.genesis;
                                        colorTo = ExoPal.genesisDark;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        line = true;
                                        cone = 1;
                                        layer = 109;
                                        length = 140;
                                        lifetime = 20;
                                        interp = Interp.circleOut;
                                        lenFrom = 15;
                                        lenTo = 8;
                                        strokeFrom = 2.5f;
                                        strokeTo = 0.5f;
                                        lightColor = colorFrom = ExoPal.genesisLight;
                                        colorTo = ExoPal.genesisTitan;
                                    }}
                            );
                            effectColor = ExoPal.genesis;
                            effectChance = 0.5f;
                        }},
                        //backstar effects
                        new EffectSpawnPart() {{
                            useProgress = true;
                            progress = PartProgress.recoil;
                            y = -2f;
                            effect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 1;
                                        cone = 360;
                                        layer = 109;
                                        length = 80;
                                        interp = Interp.circleOut;
                                        lifetime = 30;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.genesisLight;
                                        colorTo = ExoPal.genesisTitan;
                                    }}
                            );
                            effectColor = ExoPal.genesis;
                            randomEffectRot = 0;
                            effectChance = 0.1f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            y = -2f;
                            mirror = true;
                            progress = PartProgress.recoil;
                            effectColor = ExoPal.genesisTitan;
                            effect = ExoFx.randLifeSparkExo;
                            rotation = 90;
                            randomEffectRot = 0f;
                            effectChance = 0.3f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress =  true;
                            y = -2f;
                            mirror = true;
                            progress = PartProgress.recoil;
                            effectColor = ExoPal.genesisTitan;
                            effect = ExoFx.singleSpark;
                            randomEffectRot = 360f;
                            effectChance = 0.5f;
                        }},
                        //Star
                        new ShapePart() {{
                            progress = PartProgress.charge;
                            circle = true;
                            y = -2;
                            layer = 114;
                            radiusTo = 1.5f;
                            radius = 0f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.charge;
                            circle = true;
                            y = -2;
                            layer = 110;
                            radiusTo = 2.5f;
                            radius = 0f;
                            color = ExoPal.genesisTitan;
                        }},
                        //recoil
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            circle = true;
                            y = -2;
                            layer = 114;
                            radiusTo = 0;
                            radius = 2f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            progress = PartProgress.recoil;
                            circle = true;
                            y = -2;
                            layer = 110;
                            radiusTo = 0f;
                            radius = 3f;
                            color = ExoPal.genesisTitan;
                        }},

                        new RegionPart("-body"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            layerOffset = 1;
                            outlineLayerOffset = -2;
                            mirror = false;
                        }},

                        new RegionPart("-front"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveY = 4.5f;
                            mirror = false;
                        }},
                        new RegionPart("-wing"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveRot = 26;
                            x = -9;
                            y = -11;
                            mirror = true;
                        }},
                        new RegionPart("-back"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            moveY = -4.5f;
                            mirror = false;
                        }},
                        new RegionPart("-bit"){{
                            progress = PartProgress.charge.blend(PartProgress.recoil, 0.5f);
                            mirror = true;
                            moveRot = 26;
                            x = -9;
                            y = -11;
                        }}
                );
            }};
            shootType = new ContinuousLaserBulletType(){{
                hitColor = ExoPal.genesisTitan;
                layer = Layer.effect;
                damage = 180f;
                pointyScaling = 0.5f;
                length = 550f;
                hitEffect = new MultiEffect(
                        new ParticleEffect(){{
                            line = true;
                            rotWithParent = true;
                            colorFrom = ExoPal.genesisLight;
                            colorTo = ExoPal.genesisTitan;
                            cone = 35;
                            particles = 3;
                            length = 100;
                            lifetime = 21f;
                            lenFrom = 10;
                            lenTo = 7;
                            strokeFrom = 2f;
                            strokeTo = 0.8f;
                        }},
                        new ParticleEffect(){{
                            line = true;
                            rotWithParent = true;
                            colorFrom = ExoPal.genesisLight;
                            colorTo = ExoPal.genesisTitan;
                            cone = 45;
                            particles = 2;
                            length = 85;
                            lifetime = 21f;
                            lenFrom = 10;
                            lenTo = 10;
                            strokeFrom = 2f;
                            strokeTo = 0.8f;
                        }});
                intervalBullet = new LightningBulletType(){{
                    damage = 16;
                    ammoMultiplier = 1f;
                    lightningColor = ExoPal.genesisTitan;
                    lightningLength = 9;
                    lightningLengthRand = 14;
                }};
                intervalRandomSpread = 18;
                intervalBullets = 2;
                bulletInterval = 3f;

                drawSize = 260f;
                pointyScaling = 0.5f;
                shootEffect = new Effect(20,e->{
                    Draw.z(Layer.effect);
                    Draw.color(ExoPal.empyreanIndigo,e.fout());
                    Tmp.v1.trns(e.rotation, e.fin()*20f);
                    Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 0.8f*e.fin()+0.1f, 8,16, e.rotation);
                    Tmp.v2.trns(e.rotation, e.fin()*10f);
                    Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                    Lines.stroke(6f*e.fout());
                });
                oscScl = 0.5f;
                oscMag = 1.8f;
                width = 3.6f;
                shake = 2f;
                colors = new Color[]{ExoPal.genesisTitan.cpy().a(.6f), ExoPal.genesis.cpy().a(.8f), ExoPal.genesisLight, Color.white};
                despawnEffect = Fx.none;
        }};
        }};
        supernova = new LiquidTurret("supernova"){{
            requirements(Category.turret, with(ExoItems.cobolt, 400, ExoItems.rustyCopper, 300, ExoItems.osmium, 350, ExoItems.thermoCore, 300, ExoItems.iron, 400, ExoItems.neodymium, 200, ExoItems.vanstariumAlloy, 180, ExoItems.empyreanPlating, 150, ExoItems.litusiumAlloy, 250));
            range = 500f;
            squareSprite = false;
            loopSound = Sounds.none;
            extinguish = false;
            recoil = 0f;
            reload = 650f;
            shake = 4f;
            heatColor = Color.red;
            outlineColor = ExoPal.genesisOutline;
            size = 7;
            scaledHealth = 300;
            cooldownTime = 320;
            shoot.firstShotDelay = 100;
            liquidCapacity = 40f;
            moveWhileCharging = false;
            shootEffect = ExoFx.supernovaBlast;
            chargeSound = Sounds.lasercharge2;
            shootSound = ExoSounds.coolplasmaboom;

            warmupMaintainTime = 30f;
            minWarmup = 0.96f;
            shootWarmupSpeed = 0.03f;
            shootY = -4.75f;
            rotateSpeed = 1.5f;
            unitSort = UnitSorts.strongest;
            coolant = consume(new ConsumeLiquid(Liquids.cryofluid, 15f / 60f));
            consumePower(90f);
            drawer = new DrawTurret("genesux-"){{
                parts.addAll(
                new RegionPart("-glow") {{
                    progress = PartProgress.charge.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.warmup);
                    color = Color.valueOf("000000");
                    colorTo = Color.red;
                    blending = Blending.additive;
                    outline = mirror = false;
                }},
                /*
                new EffectSpawnPart() {{
                    useProgress = true;
                    progress = PartProgress.charge;
                    y = -4.75f;
                   effectColor = ExoPal.genesis;
                   randomEffectRot = 360;
                   effectChance = 0.5f;
                }},
                new EffectSpawnPart() {{
                    useProgress = true;
                    y = -4.75f;
                    effectColor = ExoPal.genesis;
                    effect = ExoFx.singleSpark;
                    randomEffectRot = 360;
                    effectChance = 0.5f;
                }},
                new EffectSpawnPart() {{
                    useProgress = mirror = true;
                   progress = PartProgress.charge;
                    x = 4f;
                    effect = ExoFx.supernovaSpark;
                    effectChance = 0.5f;
                }},
                new ShapePart() {{
                    circle = true;
                    y = -4.75f;
                    layer = 114;
                    radiusTo = 1;
                    radius = 0.3f;
                    color = Color.white;
                }},
                new ShapePart() {{
                    circle = true;
                    y = -4.75f;
                    layer = 110;
                    radiusTo = 3;
                    radius = 0.45f;
                    color = ExoPal.genesis;
                }},
                */
                new RegionPart("-bodySide"){{
                    progress = PartProgress.warmup;
                    moveX = 6.5f;
                    mirror = under = true;
                }},
                new RegionPart("-backWing"){{
                    progress = PartProgress.warmup;
                    moveX = 12.5f;
                    moveY = -24.5f;
                    moveRot = 80;
                    under = mirror = true;
                }},
                new RegionPart("-backWing"){{
                    progress = PartProgress.warmup;
                    moveX = 6.5f;
                    moveY = -20.5f;
                    moveRot = 25;
                    under = mirror = true;
                }},
                new RegionPart("-backWing"){{
                    progress = PartProgress.warmup;
                    moveY = -17.5f;
                    under = mirror = true;
                }},
                new RegionPart("-wing"){{
                    progress = PartProgress.warmup;
                    y = -8.5f;
                    x = 14.25f;
                    moveRot = -10;
                    moveX = 4.5f;
                    under = mirror = true;
                }},
                new RegionPart("-bodySidePlate"){{
                    progress = PartProgress.warmup;
                    moveX = 4.5f;
                    mirror = true;
                }}
                );
            }};
            ammo(
            Liquids.hydrogen, new StarBulletType(1f, 225){{
                radius = 20;
                hitSound = Sounds.dullExplosion;
                realColor = hitColor = trailColor = ExoPal.cronusRed;
                rotationSpeed = 80;
                homingRange = 150;
                homingPower = 0.01f;

                trailRotation = true;
                lifetime = 400f;
                swirlEffects = 2;
                swirlEffect = ExoFx.redStarSwirl;
                splashDamage = 100;
                splashDamageRadius = 50;
                despawnHit = true;
                chargeEffect = new MultiEffect(ExoFx.starChargeRed);
                shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                hitEffect = despawnEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("RedStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 125f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 20f;
                        loopSound = Sounds.spellLoop;
                        deathSound = Sounds.dullExplosion;
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 110;
                                    radiusTo = 1;
                                    radius = 13f;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 109;
                                    radiusTo = 3;
                                    radius = 20f;
                                    color = hitColor;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.cronusRed;
                                    circles = 3;
                                    sides = 360;
                                    stroke = 3;
                                    phase = 50;
                                    radius = 38f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 80;
                                        lifetime = 30;
                                        interp = Interp.sineOut;
                                        sizeFrom = 6;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.cronusRed;
                                        colorTo = Color.valueOf("af041d");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.6f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 60;
                                        lifetime = 40;
                                        interp = Interp.sineOut;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = hitColor;
                                        colorTo = Color.valueOf("af041d");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.9f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = ExoFx.supernovaStarDecay;
                                    effectColor = hitColor;
                                    randomEffectRot = 0;
                                    effectChance = 1f;
                                }}
                        );
                        health = 1;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            targetGround = targetAir = false;
                            reload = 1f;
                            y = shootY = 0;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExoExplosionBulletType(450f, 60f) {{
                                hitColor = ExoPal.cronusRed;
                                splashDamagePierce = true;
                                damageType = thermal;
                                shootEffect = new MultiEffect(Fx.titanSmoke, ExoFx.PrometheusSmoke, ExoFx.empyreanExplosion, ExoFx.starExplodeRed, Fx.colorSpark);
                                collidesGround = true;
                                collidesTiles = false;
                            }};
                        }});
                    }};
                        }};
                intervalBullet = new ExoFireBulletType(0.3f,75) {{
                    damageType = thermal;
                    lifetime = 30;
                    radius = 6;
                    drag = -0.0001f;
                    colorFrom = ExoPal.cronusRedlight;
                    colorMid = ExoPal.cronusRed;
                    colorTo = ExoPal.cronusRedDark;
                }};
                intervalBullets = 3;
                bulletInterval = 2;
                trailSinScl = 6;
                trailSinMag = 0.3f;
                trailParam = 5;
                trailLength = 10;
                trailWidth = 3.5f;
            }},
            ExoLiquids.helium, new StarBulletType(1.3f, 425){{
                radius = 25;
                hitSound = Sounds.dullExplosion;
                realColor = hitColor = trailColor = ExoPal.starYellow;
                trailRotation = true;
                lifetime = 350f;
                homingRange = 150;
                homingPower = 0.01f;
                splashDamage = 150;
                splashDamageRadius = 50;
                despawnHit = true;
                rotationSpeed = 90;
                swirlEffect = ExoFx.yellowStarSwirl;
                chargeEffect = new MultiEffect(ExoFx.starChargeYellow);
                shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                despawnEffect = hitEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("YellowStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 160f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 0f;
                        loopSound = Sounds.spellLoop;
                        deathSound = Sounds.dullExplosion;
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 110;
                                    radiusTo = 1;
                                    radius = 13f;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 109;
                                    radiusTo = 3;
                                    radius = 25f;
                                    color = ExoPal.starYellow;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.starYellow;
                                    circles = 3;
                                    sides = 360;
                                    stroke = 3;
                                    phase = 50;
                                    radius = 42f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 100;
                                        baseLength = 20;
                                        lifetime = 30;
                                        interp = Interp.sineOut;
                                        sizeFrom = 6;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.starYellow;
                                        colorTo = Color.valueOf("ff8428");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.6f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 80;
                                        baseLength = 20;
                                        lifetime = 40;
                                        interp = Interp.sineOut;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.starYellow;
                                        colorTo = Color.valueOf("ff8428");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.9f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = ExoFx.supernovaStarDecay;
                                    effectColor = hitColor;
                                    randomEffectRot = 0;
                                    effectChance = 1f;
                                }}
                                );
                                health = 1;
                                weapons.add(new Weapon() {{
                                    shootCone = 360f;
                                    mirror = false;
                                    targetGround = targetAir = false;
                                    reload = 1f;
                                    y = shootY = 0;
                                    deathExplosionEffect = shootEffect;
                                    shootOnDeath = true;
                                    shake = 10f;
                                    bullet = new ExoExplosionBulletType(565f, 70f) {{
                                        hitColor = ExoPal.starYellow;
                                        splashDamagePierce = true;
                                        damageType = thermal;
                                        shootEffect = new MultiEffect(Fx.titanSmoke, ExoFx.PrometheusSmoke, ExoFx.empyreanExplosion, ExoFx.starExplodeTest, Fx.colorSpark);
                                        collidesGround = true;
                                        collidesTiles = false;
                                    }};
                                }});
                            }};
                        }};
                intervalBullet = new ChainLightningBulletType() {{
                    lightningColor = ExoPal.starYellow;
                    damageType = DamageType.thermal;
                    range = 180;
                    targetRange = 150;
                    hitSound = Sounds.none;
                    damage = 80;
                    width = 11;
                    distanceDamageFalloff = 4;
                    chainLightning = 1;
                    segmentLength = 1;
                }};
                intervalBullets = 2;
                bulletInterval = 3;
                trailSinScl = 6;
                trailSinMag = 0.3f;
                trailParam = 5;
                trailLength = 10;
                trailWidth = 3.5f;
            }},
            Liquids.ozone, new StarBulletType(1.3f, 535){{
                radius = 28;
                hitSound = Sounds.dullExplosion;
                realColor = hitColor = trailColor = ExoPal.starWhite;
                trailRotation = true;
                lifetime = 350f;
                homingRange = 150;
                homingPower = 0.01f;
                splashDamage = 150;
                splashDamageRadius = 50;
                despawnHit = true;
                rotationSpeed = 100;
                swirlEffect = ExoFx.whiteStarSwirl;
                chargeEffect = new MultiEffect(ExoFx.starChargeWhite);
                shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                despawnEffect = hitEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                intervalBullets = 1;
                bulletInterval = 4;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("whiteStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 180f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 0f;
                        loopSound = Sounds.spellLoop;
                        deathSound = Sounds.dullExplosion;
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 110;
                                    radiusTo = 3;
                                    radius = 13f;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 109;
                                    radiusTo = 5;
                                    radius = 28f;
                                    color = ExoPal.starWhite;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.starWhite;
                                    circles = 3;
                                    sides = 360;
                                    stroke = 4;
                                    phase = 70;
                                    radius = 49f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 110;
                                        baseLength = 20;
                                        lifetime = 30;
                                        interp = Interp.sineOut;
                                        sizeFrom = 6;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.starWhite;
                                        colorTo = Color.valueOf("a1b9e9");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.6f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 90;
                                        baseLength = 20;
                                        lifetime = 40;
                                        interp = Interp.sineOut;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.starWhite;
                                        colorTo = Color.valueOf("a1b9e9");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.9f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = ExoFx.supernovaStarDecay;
                                    effectColor = hitColor;
                                    randomEffectRot = 0;
                                    effectChance = 1f;
                                }}
                        );
                        health = 1;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            targetGround = targetAir = false;
                            reload = 1f;
                            y = shootY = 0;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExoExplosionBulletType(645f, 70f) {{
                                hitColor = ExoPal.starWhite;
                                splashDamagePierce = true;
                                damageType = thermal;
                                shootEffect = new MultiEffect(Fx.titanSmoke, ExoFx.PrometheusSmoke, ExoFx.empyreanExplosion, ExoFx.starExplodeTest, Fx.colorSpark);
                                collidesGround = true;
                                collidesTiles = false;
                            }};
                        }});
                    }};
                }};
                intervalBullet = new ExoBasicBulletType(2f, 100){{
                    width = height = 7f;
                    sprite = "exogenesis-plasma";
                    shrinkY = shrinkX = 0f;
                    damageType = thermal;
                    pierce = true;
                    pierceCap = 3;
                    drag = -0.03f;
                    hitEffect = despawnEffect = ExoFx.blastExplosionColor;
                    backColor = trailColor = hitColor = ExoPal.starWhite;
                    frontColor = lightningColor = lightColor = hitColor;
                    trailLength = 13;
                    homingRange = 200f;
                    homingPower = 0.07f;
                    status = StatusEffects.melting;
                    statusDuration = 60f;
                }};
            }},
            ExoLiquids.impurePlasma, new StarBulletType(1.3f, 585){{
                radius = 18;
                hitSound = Sounds.dullExplosion;
                realColor = hitColor = trailColor = ExoPal.radGreen;
                trailRotation = true;
                rotationSpeed = 60;
                parts.addAll(
                        new FlarePart(){{
                            progress = PartProgress.life;
                            color1 = ExoPal.radGreen;
                            spinSpeed = 13;
                            sides = 2;
                            radius = 16;
                            radiusTo = 16;
                            stroke = 4f;
                        }}
                );
                lifetime = 350f;
                homingRange = 150;
                homingPower = 0.02f;
                splashDamage = 150;
                splashDamageRadius = 50;
                swirlEffects = 3;
                swirlEffect = ExoFx.strangeStarSwirl;
                despawnHit = true;
                rotationSpeed = 100;
                chargeEffect = new MultiEffect(ExoFx.starChargeGreen);
                shootEffect = new MultiEffect(ExoFx.blastExplosionColor, ExoFx.hitEmpColorSpark);
                despawnEffect = hitEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                intervalBullets = 1;
                bulletInterval = 10;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("strangeStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 120f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 0f;
                        loopSound = Sounds.spellLoop;
                        deathSound = Sounds.dullExplosion;
                        parts.addAll(
                        new ShapePart() {{
                            circle = true;
                            progress = PartProgress.life;
                            y = 0f;
                            layer = 110;
                            radiusTo = 3;
                            radius = 13f;
                            color = Color.white;
                        }},
                        new ShapePart() {{
                            circle = true;
                            progress = PartProgress.life;
                            y = 0f;
                            layer = 109;
                            radiusTo = 5;
                            radius = 18f;
                            color = hitColor;
                        }},
                        new HoverPart() {{
                            color = ExoPal.radGreen;
                            circles = 3;
                            sides = 360;
                            stroke = 2;
                            phase = 30;
                            radius = 49f;
                            mirror = false;
                            layer = Layer.effect;
                            y = 0;
                        }},
                        new EffectSpawnPart() {{
                            useProgress =  false;
                            mirror = true;
                            effectColor = ExoPal.radGreen;
                            effect = ExoFx.randLifeSparkExo;
                            rotation = 90;
                            randomEffectRot = 0f;
                            effectChance = 0.3f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = false;
                            y = 0f;
                            effect = new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 3;
                                length = 110;
                                baseLength = 20;
                                lifetime = 30;
                                interp = Interp.sineOut;
                                sizeFrom = 6;
                                sizeTo = 0;
                                lightColor = colorFrom = ExoPal.radGreen;
                                colorTo = Color.valueOf("15bd6d");
                            }};
                            effectColor = hitColor;
                            randomEffectRot = 360;
                            effectChance = 0.6f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = false;
                            y = 0f;
                            effect = new ParticleEffect() {{
                                lightOpacity = 0.5f;
                                particles = 1;
                                length = 90;
                                baseLength = 20;
                                lifetime = 40;
                                interp = Interp.sineOut;
                                sizeFrom = 3;
                                sizeTo = 0;
                                lightColor = colorFrom = ExoPal.radGreen;
                                colorTo = Color.valueOf("15bd6d");
                            }};
                            effectColor = hitColor;
                            randomEffectRot = 360;
                            effectChance = 1f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = false;
                            y = 0f;
                            effect = ExoFx.strangeStarSparks;
                            effectColor = ExoPal.radGreen;
                            randomEffectRot = 0;
                            effectChance = 1f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = false;
                            y = 0f;
                            effect = ExoFx.strangeStarSparks;
                            effectColor = ExoPal.radGreen;
                            randomEffectRot = 0;
                            rotation = 180;
                            effectChance = 1f;
                        }},
                        new EffectSpawnPart() {{
                            useProgress = false;
                            y = 0f;
                            effect = ExoFx.supernovaStarDecay;
                            effectColor = ExoPal.radGreen;
                            randomEffectRot = 0;
                            effectChance = 1f;
                        }});
                        health = 1;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            targetGround = targetAir = false;
                            reload = 1f;
                            y = shootY = 0;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExoExplosionBulletType(645f, 70f) {{
                                hitColor = ExoPal.radGreen;
                                splashDamagePierce = true;
                                damageType = radiation;
                                fragBullets = 2;
                                fragLifeMin = 1;
                                fragRandomSpread = 0;
                                fragSpread = 180;
                                fragBullet = new ExoRailBulletType(){{
                                    length = 1000;
                                    damage = 3250f;
                                    pierceArmor = true;
                                    pierce = true;
                                    pierceCap = 10;
                                    shootEffect = ExoFx.ullarTipHit;
                                    damageType = radiation;
                                    hitColor = ExoPal.radGreen;
                                    hitEffect = ExoFx.coloredHitLarge;
                                    pierceDamageFactor = 0.1f;
                                    smokeEffect = Fx.colorSpark;
                                    endEffect = new Effect(32f, e -> {
                                        clipSize = 140;
                                        color(e.color);
                                        Drawf.tri(e.x, e.y, e.fout() * 12f, 25f, e.rotation);
                                        color(Color.white);
                                        Drawf.tri(e.x, e.y, e.fout() * 6f, 19f, e.rotation);
                                    });
                                    lineEffect = new Effect(20f, e -> {
                                        clipSize = 140;
                                        if(!(e.data instanceof Vec2 v)) return;

                                        color(e.color);
                                        stroke(e.fout() * 1.1f + 0.6f);

                                        Fx.rand.setSeed(e.id);
                                        for(int i = 0; i < 7; i++){
                                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                                        }
                                        e.scaled(32f, b -> {
                                            stroke(b.fout() * 12f);
                                            color(e.color);
                                            Lines.line(e.x, e.y, v.x, v.y);
                                        });
                                        e.scaled(32f, b -> {
                                            stroke(b.fout() * 8f);
                                            color(Color.white);
                                            Lines.line(e.x, e.y, v.x, v.y);
                                        });
                                    });
                                }};
                                shootEffect = new MultiEffect(Fx.titanSmoke, ExoFx.PrometheusSmoke, ExoFx.empyreanExplosion, ExoFx.starExplodeTest, Fx.colorSpark);
                                collidesGround = true;
                                collidesTiles = false;
                            }};
                        }});
                    }};
                }};
                /*
                intervalBullet = new ExoRailBulletType(){{
                    length = 400;
                    damage = 250f;
                    pierceArmor = true;
                    pierce = true;
                    pierceCap = 10;
                    damageType = radiation;
                    hitColor = ExoPal.radGreen;
                    hitEffect = ExoFx.coloredHitLarge;
                    pierceDamageFactor = 0.1f;
                    smokeEffect = Fx.colorSpark;
                    endEffect = new Effect(22f, e -> {
                        clipSize = 140;
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 7f, 25f, e.rotation);
                        color(Color.white);
                        Drawf.tri(e.x, e.y, e.fout() * 4f, 19f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        clipSize = 140;
                        if(!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 1.1f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for(int i = 0; i < 7; i++){
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(22f, b -> {
                            stroke(b.fout() * 7f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                        e.scaled(22f, b -> {
                            stroke(b.fout() * 4f);
                            color(Color.white);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};

                 */
            }},
            ExoLiquids.coldPlasma, new StarBulletType(1.3f, 660){{
                radius = 28;
                trailWidth = 9.5f;
                trailLength = 57;
                trailSinScl = 3;
                trailSinMag = 0.4f;
                trailParam = 3.5f;
                chargeEffect = new MultiEffect(ExoFx.starChargeBlue);
                swirlEffect = ExoFx.blueStarSwirl;
                realColor = trailColor = hitColor = lightColor = lightningColor = ExoPal.genesis;
                scaleLife = false;
                hitSound = Sounds.plasmaboom;
                homingRange = 150;
                homingPower = 0.01f;
                splashDamageRadius = 80f;
                splashDamage = 170;
                despawnHit = true;
                intervalBullets = 1;
                bulletInterval = 4;
                intervalBullet = new FancyLaserBulletType(){{
                    damage = 175f;
                    sideAngle = 40f;
                    sideWidth = 1.5f;
                    sideLength = 30f;
                    width = 35f;
                    length = 80f;
                    colors = new Color[]{ExoPal.genesisDark.cpy().a(0.4f), ExoPal.genesis, Color.white};
                    hitEffect = ExoFx.coloredHitLarge;
                    hitColor = ExoPal.genesis;
                    shootEffect = ExoFx.colorBombSmall;
                }};
                pierce = false;
                collides = true;
                lifetime = 350;
                despawnEffect = hitEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("blueStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 185f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 0f;
                        loopSound = Sounds.spellLoop;
                        deathSound = Sounds.dullExplosion;
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 110;
                                    radiusTo = 3;
                                    radius = 13f;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 109;
                                    radiusTo = 5;
                                    radius = 28f;
                                    color = hitColor;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.genesis;
                                    circles = 3;
                                    sides = 360;
                                    stroke = 4;
                                    phase = 70;
                                    radius = 49f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 110;
                                        baseLength = 20;
                                        lifetime = 30;
                                        interp = Interp.sineOut;
                                        sizeFrom = 6;
                                        sizeTo = 0;
                                        lightColor = colorFrom = ExoPal.genesis;
                                        colorTo = Color.valueOf("3d62ff");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.6f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 90;
                                        baseLength = 20;
                                        lifetime = 40;
                                        interp = Interp.sineOut;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = hitColor;
                                        colorTo = Color.valueOf("3d62ff");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.9f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = ExoFx.supernovaStarDecay;
                                    effectColor = hitColor;
                                    randomEffectRot = 0;
                                    effectChance = 1f;
                                }}
                        );
                        health = 1;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            targetGround = targetAir = false;
                            reload = 1f;
                            y = shootY = 0;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExoExplosionBulletType(645f, 70f) {{
                                hitColor = ExoPal.genesis;
                                splashDamagePierce = true;
                                damageType = thermal;
                                shootEffect = new MultiEffect(Fx.titanSmoke, ExoFx.PrometheusSmoke, ExoFx.empyreanExplosion, ExoFx.starExplodeTest, Fx.colorSpark);
                                collidesGround = true;
                                collidesTiles = false;
                            }};
                        }});
                    }};
                }};
            }},
            ExoLiquids.axinian, new StarBulletType(0.9f, 760){{
                radius = 35;
                rotationSpeed = 200;
                trailWidth = 10.5f;
                trailLength = 57;
                trailSinScl = 4;
                trailSinMag = 0.6f;
                trailParam = 3.5f;
                swirlEffects = 2;
                swirlEffect = ExoFx.darkBlueStarSwirl;
                chargeEffect = new MultiEffect(ExoFx.starChargeDeepBlue);
                realColor = trailColor = hitColor = lightColor = lightningColor = ExoPal.starBlue;

                scaleLife = false;
                hitSound = Sounds.explosionbig;
                homingRange = 180;
                homingPower = 0.01f;
                splashDamageRadius = 100f;
                splashDamage = 250;
                despawnHit = true;
                intervalBullets = 1;
                bulletInterval = 3;
                intervalBullet = new ContinuousFlameBulletType(){{
                    damage = 80f;
                    length = 80;
                    knockback = 1f;
                    width = 10;
                    pierceArmor = true;
                    pierceCap = 4;
                    buildingDamageMultiplier = 0.3f;
                    flareColor = ExoPal.starBlue;
                    colors = new Color[]{ExoPal.starBlue2.a(0.7f), ExoPal.starBlue.a(0.8f), ExoPal.genesisLight, Color.white};
                }};
                collides = true;
                lifetime = 420;
                despawnEffect = hitEffect = ExoFx.hitEmpColorSpark;
                fragOnHit = false;
                fragLifeMin = 1f;
                fragBullets = 1;
                fragBullet = new BasicBulletType(){{
                    width = height = 0.001f;
                    instantDisappear = true;
                    fragLifeMin = 1f;
                    fragBullets = 1;
                    despawnUnit = new MissileUnitType("BigBlueStarDeath") {{
                        speed = 0f;
                        maxRange = 1f;
                        lifetime = 235f;
                        lowAltitude = true;
                        drawCell = false;
                        isEnemy = false;
                        targetable = false;
                        hittable = false;
                        engineSize = 0f;
                        rotateSpeed = 0f;
                        missileAccelTime = 0f;
                        loopSound = Sounds.spellLoop;
                        deathSound = ExoSounds.coolplasmaboom;
                        parts.addAll(
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 110;
                                    radiusTo = 3;
                                    radius = 13f;
                                    color = Color.white;
                                }},
                                new ShapePart() {{
                                    circle = true;
                                    progress = PartProgress.life;
                                    y = 0f;
                                    layer = 109;
                                    radiusTo = 5;
                                    radius = 35f;
                                    color = hitColor;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.starBlue;
                                    circles = 3;
                                    sides = 360;
                                    stroke = 6;
                                    phase = 160;
                                    radius = 89f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new HoverPart() {{
                                    color = ExoPal.starBlue;
                                    circles = 1;
                                    sides = 360;
                                    stroke = 8;
                                    phase = 230;
                                    radius = 149f;
                                    mirror = false;
                                    layer = Layer.effect;
                                    y = 0;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 130;
                                        baseLength = 30;
                                        lifetime = 40;
                                        interp = Interp.sineOut;
                                        sizeFrom = 6;
                                        sizeTo = 0;
                                        lightColor = colorFrom = Pal.techBlue;
                                        colorTo = ExoPal.starBlue;
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.6f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = new ParticleEffect() {{
                                        lightOpacity = 0.5f;
                                        particles = 5;
                                        length = 100;
                                        baseLength = 30;
                                        lifetime = 47;
                                        interp = Interp.sineOut;
                                        sizeFrom = 3;
                                        sizeTo = 0;
                                        lightColor = colorFrom = hitColor;
                                        colorTo = Color.valueOf("5024db");
                                    }};
                                    effectColor = hitColor;
                                    randomEffectRot = 360;
                                    effectChance = 0.9f;
                                }},
                                new EffectSpawnPart() {{
                                    useProgress = false;
                                    y = 0f;
                                    effect = ExoFx.supernovaStarDecay;
                                    effectColor = hitColor;
                                    randomEffectRot = 0;
                                    effectChance = 1f;
                                }}
                        );
                        health = 1;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            targetGround = targetAir = false;
                            reload = 1f;
                            y = shootY = 0;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExoExplosionBulletType(855f, 100f) {{
                                hitColor = ExoPal.starBlue;
                                splashDamagePierce = true;
                                damageType = thermal;
                                fragOnHit = false;
                                fragLifeMin = 1f;
                                fragBullets = 1;
                                fragBullet = new BlackHoleBulletType(0f, 1400f / 30f){{
                                    lifetime = 330f;
                                    growTime = 15;
                                    damageRadius = 30;
                                    swirlEffects = 5;
                                    swirlInterval = 3;
                                    color = hitColor = ExoPal.starBlue;
                                    lightRadius = 8f;
                                    lightOpacity = 0.7f;
                                    despawnEffect = hitEffect = ExoFx.singularityDespawn;
                                }};
                                shootEffect = new MultiEffect(ExoFx.PrometheusSmoke, ExoFx.blueStarExplosionCloud, ExoFx.starExplodeBlue, ExoFx.empyreanExplosion, ExoFx.starExplodeBlue, Fx.colorSpark);
                                collidesGround = true;
                                collidesTiles = false;
                            }};
                        }});
                    }};
                }};
            }}
        );
        }};
        polaris = new ItemTurret("polaris"){{
            requirements(Category.turret, with(ExoItems.cobolt, 400, ExoItems.rustyCopper, 300, ExoItems.osmium, 350, ExoItems.thermoCore, 300, ExoItems.iron, 400, ExoItems.neodymium, 200, ExoItems.vanstariumAlloy, 180, ExoItems.empyreanPlating, 150, ExoItems.litusiumAlloy, 250));
            range = 1000f;
            recoil = 3f;
            reload = 500f;
            shake = 4f;
            heatColor = Color.red;
            outlineColor = ExoPal.genesisOutline;
            size = 7;
            scaledHealth = 280;
            cooldownTime = 2;
            maxAmmo = 200;
            shootSound = Sounds.railgun;
            shootEffect = new Effect(22, e -> {
                color(e.color);
                float w = 1.2f + 7 * e.fout();

                Drawf.tri(e.x, e.y, w, 45f * e.fout(), e.rotation);
                color(e.color);

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, w * 3.3f, 18f * e.fout(), e.rotation + i * 90f);
                }

                Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
            });
            smokeEffect = new Effect(30,e->{
                Draw.z(Layer.effect);
                Draw.color(e.color,e.fout());
                Tmp.v1.trns(e.rotation, e.fin()*20f);
                Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 1.1f*e.fin()+0.1f, 16,27, e.rotation);
                Lines.stroke(4f*e.fout());
            });
            warmupMaintainTime = 30f;
            minWarmup = 0.96f;
            shootWarmupSpeed = 0.03f;
            shootY = -12.25f;
            rotateSpeed = 1;
            shootCone = 20f;
            unitSort = UnitSorts.strongest;
            coolant = consumeCoolant(0.2f);
            consumePower(6f);
            drawer = new DrawTurret("genesux-") {{
                parts.addAll(
                new RegionPart("-glow") {{
                    progress = PartProgress.warmup.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.warmup);
                    color = Color.valueOf("000000");
                    colorTo = Color.red;
                    blending = Blending.additive;
                    outline = mirror = false;
                }},
                new EffectSpawnPart() {{
                    useProgress = mirror = true;
                    progress = PartProgress.heat;
                    y = -12.25f;
                    x = 3.5f;
                    effectColor = ExoPal.genesis;
                    effect = ExoFx.railgunSpark;
                    randomEffectRot = 0;
                    effectChance = 1f;
                }},
                new RegionPart("-rail") {{
                    progress = PartProgress.warmup.curve(Interp.pow2In);
                    heatProgress = PartProgress.warmup.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.warmup);
                    moveY = 8;
                    mirror = false;
                }},
                new RegionPart("-wing") {{
                    progress = PartProgress.warmup.curve(Interp.pow2In);
                    moves.add(new PartMove(PartProgress.recoil, 0f, -8f, 0f), new PartMove(PartProgress.warmup.delay(0.6f), 0f, 5.5f, 0f));
                    heatColor = Color.red;
                    heatProgress = PartProgress.warmup.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.warmup);
                    moveX = 2.6f;
                    children.add(
                    new RegionPart("-wingbit"){{
                        progress = PartProgress.warmup.delay(0.5f);
                        layerOffset = -0.001f;
                        mirror = true;
                        under = true;
                        moveY = 4.3f;
                    }},
                    new RegionPart("-wingbit2"){{
                        progress = PartProgress.warmup.delay(0.6f);
                        moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f), new PartMove(PartProgress.warmup.delay(0.5f), 0f, -3f, 0f));
                        layerOffset = -0.001f;
                        mirror = true;
                        under = true;
                        moveX = 5f;
                    }});
                    mirror = under = true;
                }}
                );
            }};
            ammo(
            Items.tungsten, new ExoRailBulletType(){{
                length = 1000;
                damage = 1000f;
                pierceArmor = true;
                pierce = true;
                pierceCap = 10;
                ammoPerShot = 50;
                damageType = DamageType.pierce;
                hitColor = ExoPal.genesis;
                hitEffect = ExoFx.coloredHitLarge;
                pierceDamageFactor = 0.1f;
                smokeEffect = Fx.colorSpark;
                endEffect = new Effect(22f, e -> {
                    clipSize = 140;
                    color(e.color);
                    Drawf.tri(e.x, e.y, e.fout() * 10f, 25f, e.rotation);
                    color(Color.white);
                    Drawf.tri(e.x, e.y, e.fout() * 4.8f, 19f, e.rotation);
                });
                lineEffect = new Effect(20f, e -> {
                    clipSize = 140;
                    if(!(e.data instanceof Vec2 v)) return;

                    color(e.color);
                    stroke(e.fout() * 1.1f + 0.6f);

                    Fx.rand.setSeed(e.id);
                    for(int i = 0; i < 7; i++){
                        Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                        Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                    }

                    e.scaled(22f, b -> {
                        stroke(b.fout() * 10f);
                        color(e.color);
                        Lines.line(e.x, e.y, v.x, v.y);
                    });
                    e.scaled(22f, b -> {
                        stroke(b.fout() * 6.5f);
                        color(Color.white);
                        Lines.line(e.x, e.y, v.x, v.y);
                    });
                });
            }}
        );
        }};

        genesisFactory = new UnitFactory("genesis-factory"){{
            requirements(Category.units, with(ExoItems.astrolite, 50, Items.silicon, 100, ExoItems.curtuses, 50));
            plans = Seq.with(
                    new UnitPlan(ExoUnitTypes.orion, 60f * 15, with(Items.silicon, 15, ExoItems.curtuses, 50))
            );
            size = 3;
            consumePower(1.2f);
        }};

        //erekir blocks
        trueMechFabricator = new UnitFactory("true-mech-fabricator"){{
            requirements(Category.units, with(Items.silicon, 200, Items.graphite, 300, Items.tungsten, 60));
            size = 3;
            configurable = false;
            plans.add(new UnitPlan(ExoUnitTypes.calm, 60f * 40f, with(Items.beryllium, 70, Items.silicon, 50)));
            regionSuffix = "-dark";
            fogRadius = 3;
            researchCostMultiplier = 0.65f;
            consumePower(2f);
        }};
        hoverFabricator = new UnitFactory("hover-fabricator"){{
            requirements(Category.units, with(Items.silicon, 200, Items.graphite, 300, Items.tungsten, 60));
            size = 3;
            configurable = false;
            plans.add(new UnitPlan(ExoUnitTypes.squall, 20f * 40f, with(Items.beryllium, 50, Items.silicon, 20)));
            regionSuffix = "-dark";
            fogRadius = 3;
            researchCostMultiplier = 0.65f;
            consumePower(2f);
        }};
        supportFabricator = new UnitFactory("support-fabricator"){{
            requirements(Category.units, with(Items.silicon, 200, Items.graphite, 300, Items.tungsten, 60));
            size = 3;
            configurable = false;
            plans.add(new UnitPlan(ExoUnitTypes.ivy, 60f * 40f, with(Items.beryllium, 40, Items.silicon, 50)));
            regionSuffix = "-dark";
            fogRadius = 3;
            researchCostMultiplier = 0.65f;
            consumePower(2f);
        }};
        trueMechRefabricator = new Reconstructor("true-mech-refabricator"){{
            requirements(Category.units, with(Items.beryllium, 200, Items.tungsten, 80, Items.silicon, 100));
            regionSuffix = "-dark";

            size = 3;
            consumePower(3f);
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            consumeItems(with(Items.silicon, 40, Items.tungsten, 30));

            constructTime = 60f * 30f;
            researchCostMultiplier = 0.75f;

            upgrades.addAll(
                    new UnitType[]{ExoUnitTypes.calm, ExoUnitTypes.serene}
            );
        }};
        hoverRefabricator = new Reconstructor("hover-refabricator"){{
            requirements(Category.units, with(Items.beryllium, 250, Items.tungsten, 120, Items.silicon, 150));
            regionSuffix = "-dark";

            size = 3;
            consumePower(2.5f);
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            consumeItems(with(Items.silicon, 50, Items.tungsten, 40));

            constructTime = 60f * 45f;
            researchCostMultiplier = 0.75f;

            upgrades.addAll(
                    new UnitType[]{ExoUnitTypes.squall, ExoUnitTypes.gust}
            );
        }};
        supportRefabricator = new Reconstructor("support-refabricator"){{
            requirements(Category.units, with(Items.beryllium, 200, Items.tungsten, 100, Items.silicon, 150, Items.oxide, 40));
            regionSuffix = "-dark";

            size = 3;
            consumePower(2.5f);
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            consumeItems(with(Items.silicon, 60, Items.tungsten, 40));

            constructTime = 60f * 50f;

            upgrades.addAll(
                    new UnitType[]{ExoUnitTypes.ivy, ExoUnitTypes.yew}
            );

            researchCost = with(Items.beryllium, 500, Items.tungsten, 200, Items.silicon, 300, Items.oxide, 80);
        }};
        zetaRefabricator = new Reconstructor("zeta-refabricator"){{
            requirements(Category.units, with(Items.thorium, 250, Items.oxide, 200, Items.tungsten, 200, Items.silicon, 400));
            regionSuffix = "-dark";

            researchCostMultipliers.put(Items.thorium, 0.2f);

            size = 5;
            consumePower(5f);
            consumeLiquid(Liquids.nitrogen, 10f / 60f);
            consumeItems(with(Items.thorium, 80, Items.silicon, 100));

            constructTime = 60f * 60f;

            upgrades.addAll(
                    new UnitType[]{ExoUnitTypes.serene, ExoUnitTypes.tranquil},
                    new UnitType[]{ExoUnitTypes.yew, ExoUnitTypes.lantana},
                    new UnitType[]{UnitTypes.avert, UnitTypes.obviate}
            );
        }};
        trueMechAssembler = new UnitAssembler("true-mech-assembler"){{
            requirements(Category.units, with(Items.thorium, 500, Items.oxide, 150, Items.carbide, 80, Items.silicon, 500));
            regionSuffix = "-dark";
            size = 5;
            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.sanctuary, 60f * 50f, PayloadStack.list(ExoUnitTypes.calm, 4, Blocks.tungstenWallLarge, 10)),
                    new AssemblerUnitPlan(ExoUnitTypes.ataraxia, 60f * 60f * 3f, PayloadStack.list(ExoUnitTypes.serene, 6, Blocks.carbideWallLarge, 20))
            );
            areaSize = 13;
            researchCostMultiplier = 0.4f;

            consumePower(3f);
            consumeLiquid(Liquids.cyanogen, 9f / 60f);
        }};
        supportAssembler = new UnitAssembler("support-assembler"){{
            requirements(Category.units, with(Items.carbide, 100, Items.oxide, 200, Items.tungsten, 500, Items.silicon, 800, Items.thorium, 400));
            regionSuffix = "-dark";
            size = 5;
            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.kalmia, 60f * 60f, PayloadStack.list(ExoUnitTypes.ivy, 4, Blocks.berylliumWallLarge, 12)),
                    new AssemblerUnitPlan(ExoUnitTypes.hemlock, 60f * 60f * 3f, PayloadStack.list(ExoUnitTypes.yew, 6, Blocks.carbideWallLarge, 20))
            );
            areaSize = 13;

            consumePower(3f);
            consumeLiquid(Liquids.cyanogen, 12f / 60f);
        }};
        hoverAssembler = new UnitAssembler("hover-assembler"){{
            requirements(Category.units, with(Items.carbide, 200, Items.thorium, 600, Items.oxide, 200, Items.tungsten, 500, Items.silicon, 900));
            regionSuffix = "-dark";
            size = 5;

            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.thunderstorm, 60f * 70f, PayloadStack.list(ExoUnitTypes.squall, 5, Blocks.tungstenWallLarge, 12)),
                    new AssemblerUnitPlan(ExoUnitTypes.hurricane, 60f * 60f * 3f, PayloadStack.list(ExoUnitTypes.gust, 6, Blocks.carbideWallLarge, 20))
            );
            areaSize = 13;

            consumePower(3.5f);
            consumeLiquid(Liquids.cyanogen, 12f / 60f);
        }};
        airTitanAssembler = new UnitAssembler("air-titan-assembler"){{
            requirements(Category.units, with(Items.thorium, 500, Items.oxide, 150, Items.carbide, 80, Items.silicon, 500));
            regionSuffix = "-dark";
            size = 6;
            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.nemesis, 60f * 60f * 5, PayloadStack.list(UnitTypes.quell, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20)),
                    new AssemblerUnitPlan(ExoUnitTypes.rhea, 60f * 60f * 5f, PayloadStack.list(ExoUnitTypes.kalmia, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20))
            );
            areaSize = 26;
            researchCostMultiplier = 0.4f;

            consumePower(3f);
            consumeLiquid(Liquids.cyanogen, 9f / 60f);
        }};
        groundTitanAssembler = new UnitAssembler("ground-titan-assembler"){{
            requirements(Category.units, with(Items.carbide, 100, Items.oxide, 200, Items.tungsten, 500, Items.silicon, 800, Items.thorium, 400));
            regionSuffix = "-dark";
            size = 6;
            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.hyperion, 60f * 60f * 5, PayloadStack.list(UnitTypes.quell, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20)),
                    new AssemblerUnitPlan(ExoUnitTypes.leto, 60f * 60f * 5, PayloadStack.list(ExoUnitTypes.sanctuary, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20)),
                    new AssemblerUnitPlan(ExoUnitTypes.prometheus, 60f * 60f * 5f, PayloadStack.list(UnitTypes.vanquish, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20))
            );
            areaSize = 26
            ;

            consumePower(3f);
            consumeLiquid(Liquids.cyanogen, 12f / 60f);
        }};
        leggedTitanAssembler = new UnitAssembler("legged-titan-assembler"){{
            requirements(Category.units, with(Items.carbide, 200, Items.thorium, 600, Items.oxide, 200, Items.tungsten, 500, Items.silicon, 900));
            regionSuffix = "-dark";
            size = 6;
            plans.add(
                    new AssemblerUnitPlan(ExoUnitTypes.cronus, 60f * 60f * 5, PayloadStack.list(UnitTypes.quell, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20)),
                    new AssemblerUnitPlan(ExoUnitTypes.atlas, 60f * 60f * 5f, PayloadStack.list(UnitTypes.tecta, 8, Blocks.carbideWallLarge, 20, Blocks.reinforcedSurgeWallLarge, 20))
            );
            areaSize = 26;

            consumePower(3.5f);
            consumeLiquid(Liquids.cyanogen, 12f / 60f);
        }};
    }
}