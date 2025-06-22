package exogenesis.content;

import exogenesis.content.effects.ExoChargeFx;
import exogenesis.content.effects.ExoHitFx;
import exogenesis.content.effects.ExoShootFx;
import exogenesis.entities.part.EffectSpawnPart;
import exogenesis.type.bullet.*;
import exogenesis.type.bullet.vanilla.*;
import exogenesis.world.blocks.ExoIronDome;
import exogenesis.world.draw.DrawLoopPart;
import exogenesis.world.meta.ExoEnv;
import exogenesis.world.power.LightningRod;
import exogenesis.world.turrets.PowerShootTypeTurret;
import exogenesis.world.turrets.SpeedupTurret;
import exogenesis.graphics.ExoPal;
import arc.util.Tmp;
import mindustry.entities.abilities.MoveEffectAbility;
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
import mindustry.world.blocks.defense.RegenProjector;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.distribution.*;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.Env;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static exogenesis.content.ExoDamageTypes.*;
import static mindustry.type.ItemStack.with;

public class ExoVanstarBlocks{
        public static Block
        // blocks
        ductEmpyrean, ductEmpyreanBridge, empyreanJunction, empyreanSorter, empyreanRouter,

        drainPipe, drainPipeRouter, drainPipeBridge,

        pulsePump, pulseImpactPump, liquidTankEmpyrean, liquidCup,
        // power blocks
        harvesterSmall, harvesterMedium, energyExtractorMedium, energyExtractor, luxNode, luxTower, oltuxiumBattery, oltuxiumBatteryLarge,
        // crafters
        platingFactory, gasiousConverter, sandSift, atmosphericExtractor, rockGrinder, ironFurnace, metaglassForger, alloyForge, kryptonChamber, HeliumFactory, listusiumForge, vanstaniumOven, osmiumBlastForge, gigavoltForge,
        // Drills
        pulsarDrill, pulsarWallDrill, smallWallGrinder, wallGrinder, pulseImpactDrill, quaryDrill,
        // Defence
        medicusProjector, arielRestrictor, stormGuard,
        //cores
        coreBelief, coreHope, coreReliance,
        //walls
        listusiumWall, largeListusiumWall, coboltWall, largeCoboltWall, oltuxiumWall, largeOltuxiumWall, ironWall, largeIronWall, vanstaniumWall, largeVanstaniumWall,
        violiteWall, largeVioliteWall, vanstariumWall, largeVanstariumWall, hugeVanstariumWall,
        //turrets
        gale, light, focalPoint, bliss, cleanser, prism, sanctify, glory, tanons, wrath,  essence, purger,
        excalibur,  aspect, eminence, godsent, blessing, aeon, grandeur, aether, profane, sacrosanct, agios, sin, haborym, arbiter, demiurge, phoss,
        empyreanFactory;
        public static void load(){
            //Empyrean blocks
            ductEmpyrean = new Duct("empyrean-duct"){{
                requirements(Category.distribution, with(ExoItems.cobolt, 1));
                alwaysUnlocked = true;
                bridgeReplacement = ExoVanstarBlocks.ductEmpyreanBridge;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 90;
                speed = 5f;
                researchCost = with(ExoItems.cobolt, 5);
            }};
            ductEmpyreanBridge = new DuctBridge("empyrean-duct-bridge"){{
                requirements(Category.distribution, with(ExoItems.cobolt, 15));
                health = 90;
                speed = 5f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                buildCostMultiplier = 2f;
                researchCostMultiplier = 0.3f;
            }};
            empyreanSorter = new Sorter("empyrean-sorter"){{
                requirements(Category.distribution, with(ExoItems.cobolt, 2, ExoItems.rustyCopper, 2));
                buildCostMultiplier = 3f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
            }};
            empyreanJunction = new Junction("empyrean-junction"){{
                requirements(Category.distribution, with(ExoItems.cobolt, 2));
                speed = 26;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                capacity = 6;
                health = 30;
                buildCostMultiplier = 6f;
            }};
            empyreanRouter = new Router("empyrean-router"){{
                requirements(Category.distribution, with(ExoItems.cobolt, 3));
                buildCostMultiplier = 4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
            }};

            drainPipe = new Conduit("drain-pipe"){{
                requirements(Category.liquid, with(ExoItems.cobolt, 2, ExoItems.exoMetaglass, 1));
                liquidCapacity = 16f;
                liquidPressure = 1.025f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 90;
            }};
            drainPipeRouter = new LiquidRouter("drain-pipe-router"){{
                requirements(Category.liquid, with(ExoItems.exoMetaglass, 1, ExoItems.cobolt, 2));
                liquidCapacity = 30f;
                liquidPadding = 3f/4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                researchCostMultiplier = 3;
                underBullets = true;
                solid = false;
            }};
            drainPipeBridge = new LiquidBridge("drain-pipe-bridge"){{
                requirements(Category.liquid, with(ExoItems.exoMetaglass, 1, ExoItems.cobolt, 3));
                fadeIn = moveArrows = false;
                arrowSpacing = 6f;
                range = 4;
                hasPower = false;
                liquidCapacity = 100f;
                explosivenessScale = flammabilityScale = 20f/100f;
            }};
            pulsePump = new Pump("pulse-pump"){{
                requirements(Category.liquid, with(ExoItems.cobolt, 30, ExoItems.exoMetaglass, 50, ExoItems.exoGraphite, 20));
                pumpAmount = 0.3f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                consumePower(0.3f);
                liquidCapacity = 30f;
                hasPower = true;
                size = 2;
            }};
            pulseImpactPump = new Pump("pulse-impact-pump"){{
                requirements(Category.liquid, with(ExoItems.cobolt, 80, ExoItems.exoMetaglass, 90, ExoItems.exoSilicon, 30, ExoItems.iron, 40, ExoItems.osmium, 35));
                pumpAmount = 0.26f;
                consumePower(1f);
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                liquidCapacity = 40f;
                hasPower = true;
                size = 3;
            }};
            liquidCup = new LiquidRouter("liquid-cup"){{
                requirements(Category.liquid, with(ExoItems.cobolt, 20, ExoItems.exoMetaglass, 15));
                liquidCapacity = 700f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                size = 2;
                solid = true;
            }};
            liquidTankEmpyrean = new LiquidRouter("liquid-tank-empyrean"){{
                requirements(Category.liquid, with(ExoItems.cobolt, 60, ExoItems.litusiumAlloy, 30, ExoItems.exoMetaglass, 40));
                size = 3;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                solid = true;
                liquidCapacity = 1800f;
                health = 500;
            }};

            //power
            luxNode = new PowerNode("lux-node"){{
                requirements(Category.power, with(ExoItems.rustyCopper, 1, ExoItems.cobolt, 3));
                size = 2;
                health = 120;
                maxNodes = 10;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                baseExplosiveness = 8;
                destroyBulletSameTeam = false;
                destroyBullet = new LightningBulletType(){{
                    lightningColor = hitColor = ExoPal.empyrean;
                    collidesTeam = true;
                    damage = 14f;
                    lightningLength = 7;
                    lightningLengthRand = 7;

                    lightningType = new BulletType(0.0001f, 0f){{
                        lifetime = Fx.lightning.lifetime;
                        hitEffect = Fx.hitLancer;
                        despawnEffect = Fx.none;
                        status = StatusEffects.shocked;
                        statusDuration = 10f;
                        hittable = false;
                        collidesTeam = true;
                    }};
                }};
                laserScale = 0.55f;
                laserColor2 = ExoPal.empyrean;
                laserRange = 9;
            }};
            luxTower = new PowerNode("lux-tower"){{
                requirements(Category.power, with(ExoItems.neodymium, 15, ExoItems.rustyCopper, 30, ExoItems.exoSilicon, 25));
                size = 4;
                health = 1920;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                baseExplosiveness = 15;
                laserScale = 0.55f;
                laserColor2 = ExoPal.empyrean;
                maxNodes = 4;
                laserRange = 28f;
            }};
            oltuxiumBattery = new Battery("oltuxium-battery"){{
                requirements(Category.power, with(ExoItems.oltuxium, 10, ExoItems.cobolt, 1));
                consumePowerBuffered(1000f);
                scaledHealth = 280;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                emptyLightColor = Color.valueOf("5eb1c1");
                fullLightColor = Color.valueOf("8deee2");
                baseExplosiveness = 1f;
            }};
            oltuxiumBatteryLarge = new Battery("oltuxium-battery-large"){{
                requirements(Category.power, with(ExoItems.neodymium, 20, ExoItems.oltuxium, 90, ExoItems.cobolt, 50, ExoItems.exoSilicon, 30));
                size = 3;
                scaledHealth = 280;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                consumePowerBuffered(35000f);
                emptyLightColor = Color.valueOf("5eb1c1");
                fullLightColor = Color.valueOf("8deee2");
                baseExplosiveness = 5f;
            }};
            energyExtractor = new ThermalGenerator("energy-extractor"){{
                requirements(Category.power, with(ExoItems.cobolt, 30, ExoItems.oltuxium, 60));
                attribute = ExoAttribute.power;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                scaledHealth = 280;
                minEfficiency = 0f;
                powerProduction = 16f;

                generateEffect = new RadialEffect() {{
                    rotationOffset = 360;
                    rotationSpacing = 120;
                    effect = ExoFx.singleSparkYellow;
                    amount = 3;
                }};
                effectChance = 0.05f;
                size = 2;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.03f;

                drawer = new DrawMulti(
                        new DrawCrucibleFlame(){{
                            particleRad = 3;
                            particleLife = 20.0F;
                            particles = 20;
                        }},
                        new DrawDefault()
                );

                liquidCapacity = 20f;
                fogRadius = 3;
                researchCost = with(ExoItems.cobolt, 15);
            }};
            energyExtractorMedium = new ThermalGenerator("energy-extractor-medium"){{
                requirements(Category.power, with(ExoItems.cobolt, 80, ExoItems.oltuxium, 160, ExoItems.iron, 50));
                attribute = ExoAttribute.power;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                scaledHealth = 280;
                minEfficiency = 0f;
                powerProduction = 16f;

                generateEffect = new RadialEffect() {{
                    rotationOffset = 360;
                    rotationSpacing = 120;
                    effect = ExoFx.singleSparkYellow;
                    amount = 3;
                }};
                effectChance = 0.05f;
                size = 3;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.03f;

                drawer = new DrawMulti(
                        new DrawCrucibleFlame(){{
                            particleRad = 3;
                            particleLife = 20.0F;
                            particles = 20;
                        }},
                        new DrawBlurSpin("-rotator1", 6f){{
                            blurThresh = 0.05f;
                        }},
                        new DrawBlurSpin("-rotator2", -6f){{
                            blurThresh = 0.05f;
                        }},
                new DrawDefault()
                );

                liquidCapacity = 20f;
                fogRadius = 3;
                researchCost = with(ExoItems.cobolt, 15);
            }};
            harvesterSmall = new ThermalGenerator("harvester-small"){{
                requirements(Category.crafting, with(ExoItems.cobolt, 30, ExoItems.exoMetaglass, 30, ExoItems.oltuxium, 60));
                attribute = ExoAttribute.power;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                scaledHealth = 280;
                minEfficiency = 0f;
                powerProduction = 4f;
                effectChance = 0;
                size = 2;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.03f;

                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawLiquidRegion(),
                        new DrawDefault(),
                        new DrawPulseShape(false){{
                            layer = Layer.effect;
                            color = Color.valueOf("2e8fff");
                        }},
                        new DrawGlowRegion("-topglow"){{
                            color = Color.valueOf("2e8fff");
                            glowIntensity = 0.1f;
                            alpha = 0.8f;
                        }}
                );
                hasLiquids = true;
                outputLiquid = new LiquidStack(ExoLiquids.ichorium, 0.2f);
                liquidCapacity = 30f;
                fogRadius = 3;
                researchCost = with(ExoItems.cobolt, 15);
            }};
            harvesterMedium = new ThermalGenerator("harvester-medium"){{
                requirements(Category.crafting, with(ExoItems.cobolt, 80, ExoItems.exoMetaglass, 80, ExoItems.oltuxium, 160, ExoItems.iron, 50));
                attribute = ExoAttribute.power;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                scaledHealth = 280;
                minEfficiency = 0f;
                powerProduction = 4f;
                effectChance = 0f;
                size = 3;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.03f;

                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawLiquidRegion(),
                        new DrawDefault(),
                        new DrawPulseShape(false){{
                            layer = Layer.effect;
                            color = Color.valueOf("2e8fff");
                        }},
                        new DrawGlowRegion("-topglow"){{
                            color = Color.valueOf("2e8fff");
                            glowIntensity = 0.1f;
                            alpha = 0.8f;
                        }}
                );
                hasLiquids = true;
                outputLiquid = new LiquidStack(ExoLiquids.ichorium, 0.6f);
                liquidCapacity = 30f;
                fogRadius = 3;
                researchCost = with(ExoItems.cobolt, 15);
            }};
            //drills
            pulsarDrill = new Drill("plusar-drill"){{
                requirements(Category.production, with(ExoItems.rustyCopper, 18, ExoItems.cobolt, 10));
                tier = 2;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                alwaysUnlocked = true;
                drillTime = 350;
                hardnessDrillMultiplier = 10f;
                size = 2;

                consumeLiquid(Liquids.water, 0.06f).boost();
            }};
            pulseImpactDrill = new Drill("pulse-impact-drill"){{
                requirements(Category.production, with(ExoItems.rustyCopper, 158, ExoItems.cobolt, 150, ExoItems.exoSilicon, 60));
                tier = 3;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                drillTime = 300;
                size = 3;

                consumeLiquid(Liquids.water, 0.06f).boost();
            }};
            quaryDrill = new Drill("quary-drill"){{
                requirements(Category.production, with(ExoItems.rustyCopper, 158, ExoItems.cobolt, 150, ExoItems.iron, 150, ExoItems.exoSilicon, 160));
                tier = 4;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                drillTime = 100;
                rotateSpeed = 7;
                size = 3;
                consumePower(10f);
                consumeLiquid(Liquids.water, 0.06f).boost();
            }};
            pulsarWallDrill = new BeamDrill("plusar-wall-drill"){{
                requirements(Category.production, with(ExoItems.rustyCopper, 30, ExoItems.cobolt, 15));
                consumePower(0.15f);
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                drillTime = 300f;
                tier = 2;
                size = 2;
                range = 7;

                consumeLiquid(Liquids.water, 0.25f / 60f).boost();
            }};
            smallWallGrinder = new WallCrafter("wall-grinder-small"){{
                requirements(Category.production, with(ExoItems.cobolt, 65, ExoItems.rustyCopper, 80));
                consumePower(11 / 60f);
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                drillTime = 260f;

                size = 2;
                attribute = ExoAttribute.rocky;
                output = ExoItems.vanstarBasalt;
                rotateSpeed = 4.5f;
                ambientSound = Sounds.drill;
                ambientSoundVolume = 0.1f;
            }};
            wallGrinder = new WallCrafter("wall-grinder"){{
                requirements(Category.production, with(ExoItems.cobolt, 125, ExoItems.exoGraphite, 125, ExoItems.rustyCopper, 180));
                consumePower(11 / 60f);
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;

                drillTime = 400f;
                size = 3;
                attribute = ExoAttribute.ferrus;
                output = ExoItems.ferricPowder;
                rotateSpeed = 2.5f;
                ambientSound = Sounds.drill;
                ambientSoundVolume = 0.04f;
            }};

            //crafters
            platingFactory = new GenericCrafter("plating-factory"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 60, ExoItems.cobolt, 30));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                craftEffect = Fx.hitMeltdown;
                outputItem = new ItemStack(ExoItems.empyreanPlating, 1);
                craftTime = 75f;
                size = 2;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawPistons(){{
                            sinMag = 2f;
                            sinScl = 2f;
                            sides = 8;
                            sideOffset = 15;
                }},
                        new DrawRegion("-bottom"),
                        new DrawLoopPart("-clamp", 2, 0, false, 2){{
                            x = 1;
                        }},
                        new DrawLoopPart("-clamp1", -2, 0, false, 2){{
                            x = -1;
                        }},
                new DrawDefault()
                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;

                consumeItems(with(ExoItems.oltuxium, 1, ExoItems.cobolt, 1));
                consumePower(0.30f);
            }};
            sandSift = new GenericCrafter("sand-sift"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 30, ExoItems.exoGraphite, 40));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                craftEffect = Fx.none;
                outputItem = new ItemStack(ExoItems.quartz, 3);
                craftTime = 100f;
                size = 2;
                liquidCapacity = 50;
                hasPower = hasLiquids = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawLiquidRegion(),
                        new DrawRegion(){{
                            suffix = "-rotator";
                            spinSprite = true;
                            rotateSpeed = 1f;
                        }},
                        new DrawDefault(),
                        new DrawRegion(){{
                            suffix = "-top";
                }}
                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;

                consumeLiquid(Liquids.water, 10f / 60f);
                consumeItems(with(Items.sand, 1));
                consumePower(0.30f);
            }};
            rockGrinder = new Separator("rock-grinder"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 65, ExoItems.empyreanPlating, 30, ExoItems.oltuxium, 20, ExoItems.cobolt, 40));
                researchCostMultiplier = 0.3f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                craftTime = 30f;
                itemCapacity = 30;
                hasLiquids = true;
                results = with(
                        ExoItems.peridotite, 1,
                        ExoItems.ameythystGeode, 1,
                        Items.sand, 2,
                        ExoItems.quartz, 2
                );
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = 0;
                            y = 2.5f;
                            rotateSpeed = 6;
                        }},
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = 0;
                            y = -2.5f;
                            rotateSpeed = -6;
                        }},
                        //grinder left
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = 5;
                            y = 2.5f;
                            rotateSpeed = 6;
                        }},
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = 5;
                            y = -2.5f;
                            rotateSpeed = -6;
                        }},
                        //grinder right
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = -5;
                            y = 2.5f;
                            rotateSpeed = 6;
                        }},
                        new DrawRegion(){{
                            suffix = "-grinder";
                            spinSprite = true;
                            x = -5;
                            y = -2.5f;
                            rotateSpeed = -6;
                        }},
                        new DrawDefault()
                );
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.07f;

                consumeItems(with(ExoItems.vanstarBasalt, 2));
                consumePower(1.60f);
            }};

            atmosphericExtractor = new GenericCrafter("energy-extractor"){{
                requirements(Category.power, with(ExoItems.cobolt, 30, ExoItems.oltuxium, 60));
                scaledHealth = 280;
                hasPower = hasLiquids = true;
                size = 3;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.03f;
                consumePower(3.60f);
                outputLiquid = new LiquidStack(ExoLiquids.scalvaur, 0.7f);
                drawer = new DrawMulti(
                        new DrawDefault(),
                        new DrawLiquidRegion(),
                        new DrawCrucibleFlame() {{
                            particleRad = 13;
                            particleLife = 20.0F;
                            particleSize = 5.0F;
                            flameColor = Color.valueOf("dcc7a8");
                            midColor = Color.valueOf("f1e4d1");
                            flameRad = 0;
                            particles = 20;
                }},
                        new DrawBlurSpin("-rotator", -9f){{
                            blurThresh = 0.05f;
                        }}
                );

                liquidCapacity = 40f;
                fogRadius = 3;
                researchCost = with(ExoItems.cobolt, 15);
            }};

            ironFurnace = new GenericCrafter("iron-furnace"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 65, ExoItems.empyreanPlating, 30, ExoItems.oltuxium, 20, ExoItems.cobolt, 40));
                researchCost = with (ExoItems.rustyCopper, 325, ExoItems.empyreanPlating, 150, ExoItems.oltuxium, 100, ExoItems.cobolt, 200);
                craftEffect = Fx.smokePuff;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItem = new ItemStack(ExoItems.iron, 4);
                outputLiquid = new LiquidStack(Liquids.slag, 0.08f);
                craftTime = 120f;
                liquidCapacity = 40;
                hasLiquids = true;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawCrucibleFlame(){{
                            particleLife = 90;
                            particleRad = 12;
                            particles = 100;
                            particleSize = 6;
                        }},
                        new DrawRegion(){{
                            suffix = "-rotater";
                            spinSprite = true;
                            rotateSpeed = 1;
                        }},
                        new DrawRegion(){{
                            suffix = "-rotater";
                            spinSprite = true;
                            rotateSpeed = -1f;
                        }},
                        new DrawDefault(),
                        new DrawLiquidRegion()
                );
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.07f;

                consumeItems(with(ExoItems.ferricPowder, 3));
                consumePower(2.60f);
            }};
            alloyForge = new GenericCrafter("alloy-forge"){{
                requirements(Category.crafting, with(ExoItems.iron, 100, ExoItems.exoGraphite, 50, ExoItems.magnetite, 30, ExoItems.cobolt, 30));
                researchCost = with (ExoItems.iron, 500, ExoItems.exoGraphite, 250, ExoItems.magnetite, 150, ExoItems.cobolt, 150);
                craftEffect = Fx.fire;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItem = new ItemStack(ExoItems.exoSilicon, 2);
                craftTime = 55f;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawPistons(){{
                            sinMag = 1f;
                            sinScl = 3f;
                            sides = 4;
                            sideOffset = 6;
                        }},
                        new DrawDefault(),
                        new DrawFlame(Color.valueOf("ffc099"))
                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.1f;

                consumeItems(with(ExoItems.exoGraphite, 2, ExoItems.quartz, 1));
                consumePower(2.60f);
            }};
            metaglassForger = new GenericCrafter("metaglass-forger"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 120, ExoItems.magnetite, 85, ExoItems.exoGraphite, 80, ExoItems.cobolt, 150));
                researchCost = with (ExoItems.rustyCopper, 600, ExoItems.magnetite, 425, ExoItems.exoGraphite, 400, ExoItems.cobolt, 750);
                craftEffect = Fx.smeltsmoke;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItem = new ItemStack(ExoItems.exoMetaglass, 2);
                craftTime = 40f;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawDefault(),
                        new DrawFlame(Color.valueOf("ffc099")),
                        new DrawGlowRegion("-glow"){{
                            color = Color.valueOf("70170b");
                            glowIntensity = 1f;
                            alpha = 0.9f;
                        }}
                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;

                consumeItems(with( Items.sand, 3));
                consumePower(2.60f);
            }};
            listusiumForge = new GenericCrafter("litusium-forge"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 240, ExoItems.exoMetaglass, 165, ExoItems.exoSilicon, 100, ExoItems.iron, 120, ExoItems.empyreanPlating, 120));
                researchCost = with (ExoItems.rustyCopper, 1200, ExoItems.exoMetaglass, 825, ExoItems.exoSilicon, 500, ExoItems.iron, 600, ExoItems.empyreanPlating, 600);
                craftEffect = Fx.smeltsmoke;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItem = new ItemStack(ExoItems.litusiumAlloy, 1);
                craftTime = 70f;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawLoopPart("-presses", 0, -3, false, 1),
                        new DrawLoopPart("-presses1", 0, 3, false, 1),
                        new DrawGlowRegion("-heatGlow"){{
                            color = Color.valueOf("70170b");
                            glowIntensity = 0.2f;
                            alpha = 0.7f;
                        }},
                        new DrawDefault(),
                        new DrawFlame(Color.valueOf("ffa665")){{
                            flameX = -5;
                            flameY = 0;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }},
                        new DrawFlame(Color.valueOf("ffa665")){{
                            flameX = -5;
                            flameY = -4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }},
                        new DrawFlame(Color.valueOf("ffa665")){{
                            flameX = -5;
                            flameY = 4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }}

                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;
                consumeLiquid(ExoLiquids.scalvaur, 0.13333336f);
                consumeItems(with(ExoItems.empyreanPlating, 1, ExoItems.iron, 2));
                consumePower(3.60f);
            }};
            vanstaniumOven = new GenericCrafter("vastanium-oven"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 300, ExoItems.cobolt, 160, ExoItems.exoSilicon, 160, ExoItems.osmium, 180, ExoItems.empyreanPlating, 150));
                researchCost = with (ExoItems.rustyCopper, 2100, ExoItems.cobolt, 1120, ExoItems.exoSilicon, 1120, ExoItems.osmium, 1260, ExoItems.empyreanPlating, 1050);
                craftEffect = Fx.smeltsmoke;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;

                outputItem = new ItemStack(ExoItems.vastanium, 2);
                craftTime = 160f;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(
                        new DrawDefault(),
                        new DrawFlame(Color.valueOf("8dde8f")){{
                            flameX = -4;
                            flameY = 4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }},
                        new DrawFlame(Color.valueOf("8dde8f")){{
                            flameX = 4;
                            flameY = -4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }},
                        new DrawFlame(Color.valueOf("8dde8f")){{
                            flameX = -4;
                            flameY = -4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;

                        }},
                        new DrawFlame(Color.valueOf("8dde8f")){{
                            flameX = 4;
                            flameY = 4;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }}


                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;
                consumeLiquids(LiquidStack.with(ExoLiquids.scalvaur, 0.09333336f, ExoLiquids.krypton, 0.09333336f));
                consumeItems(with(ExoItems.empyreanPlating, 2));
                consumePower(2.60f);
            }};
            osmiumBlastForge = new GenericCrafter("osmium-blast-forge"){{
                requirements(Category.crafting, with(ExoItems.rustyCopper, 540, ExoItems.cobolt, 560, ExoItems.iron, 560, ExoItems.empyreanPlating, 160, ExoItems.neodymium, 440, ExoItems.litusiumAlloy, 250));
                researchCost = with (ExoItems.rustyCopper, 3780, ExoItems.cobolt, 3920, ExoItems.iron, 3920, ExoItems.empyreanPlating, 1120, ExoItems.neodymium, 3080, ExoItems.litusiumAlloy, 1750);
                craftEffect = Fx.fire;
                updateEffect = Fx.fireSmoke;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItems = ItemStack.with(ExoItems.iron, 3, ExoItems.osmium, 2);
                craftTime = 180f;
                itemCapacity = 30;
                liquidCapacity = 40;
                hasLiquids = true;
                size = 6;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(),
                        new DrawGlowRegion("-glow1"){{
                            color = Color.valueOf("70170b");
                            glowIntensity = 0.4f;
                            alpha = 0.7f;
                        }},
                        new DrawLiquidRegion(),
                        new DrawCrucibleFlame(){{
                            particleRad = 11;
                            particleLife = 40.0F;
                            particles = 60;
                        }},
                        new DrawGlowRegion("-glow2"){{
                            color = Color.valueOf("70170b");
                            glowIntensity = 0.1f;
                            alpha = 0.8f;
                        }}
                );
                ambientSound = Sounds.torch;
                ambientSoundVolume = 0.07f;

                consumeLiquid(Liquids.slag, 10f / 60f);
                consumeItems(with(ExoItems.thermite, 5, ExoItems.cobolt, 5, ExoItems.exoSilicon, 3));
                consumePower(4.60f);
            }};
            gigavoltForge = new GenericCrafter("alpha-forge"){{
                requirements(Category.crafting, with(ExoItems.oltuxium, 100, ExoItems.cobolt, 100, ExoItems.exoSilicon, 160, ExoItems.osmium, 180, ExoItems.iron, 140));
                craftEffect = ExoFx.empyreanStarHitSmall;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outputItem = new ItemStack(ExoItems.vanstariumAlloy, 1);
                craftTime = 160f;
                size = 3;
                hasPower = hasItems = true;
                drawer = new DrawMulti(new DrawDefault(),
                        new DrawFlame(Color.valueOf("ffd75d")),
                        new DrawFlame(Color.valueOf("ffd75d")){{
                            flameX = 7;
                            flameY = 0;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                        }},
                        new DrawFlame(Color.valueOf("ffd75d")){{
                            flameX = -7;
                            flameY = 0;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                }},
                        new DrawFlame(Color.valueOf("ffd75d")){{
                            flameX = 0;
                            flameY = 7;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;
                }},
                        new DrawFlame(Color.valueOf("ffd75d")){{
                            flameX = 0;
                            flameY = -7;
                            flameRadiusScl = 2f;
                            flameRadiusMag = 0.8f;
                            flameRadiusInMag = 0.3f;
                            flameRadiusIn = 0.9f;
                            flameRadius = 1.5f;

                }}
                );
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;

                consumeItems(with(ExoItems.magnetite, 3, ExoItems.oltuxium, 2, ExoItems.cobolt, 2, ExoItems.exoSilicon, 1));
                consumePower(2.60f);
            }};
            //walls
            coboltWall = new Wall("cobolt-wall"){{
                requirements(Category.defense, with(ExoItems.cobolt, 6));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 180;
                researchCostMultiplier = 0.1f;
            }};
            largeCoboltWall = new Wall("large-cobolt-wall"){{
                requirements(Category.defense, ItemStack.mult(coboltWall.requirements, 4));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                researchCostMultiplier = 0.25f;
                health = 180 * 4;
                size = 2;
            }};
            oltuxiumWall = new Wall("oltuxium-wall"){{
                requirements(Category.defense, with(ExoItems.oltuxium, 6));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 155;
                lightningChance = 0.2f;
                lightningColor = ExoPal.empyrean;
                researchCostMultiplier = 0.1f;
            }};
            largeOltuxiumWall = new Wall("large-oltuxium-wall"){{
                requirements(Category.defense, ItemStack.mult(oltuxiumWall.requirements, 4));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 155 * 4;
                lightningChance = 0.2f;
                lightningColor = ExoPal.empyrean;
                researchCostMultiplier = 0.25f;
                size = 2;
            }};
            ironWall = new Wall("iron-wall"){{
                requirements(Category.defense, with(ExoItems.iron, 6));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 230;
                researchCostMultiplier = 0.1f;
            }};
            largeIronWall = new Wall("large-iron-wall"){{
                requirements(Category.defense, ItemStack.mult(ironWall.requirements, 4));
                researchCostMultiplier = 0.25f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 230 * 4;
                size = 2;
            }};
            listusiumWall = new Wall("listusium-wall"){{
                requirements(Category.defense, with(ExoItems.litusiumAlloy, 6));
                health = 270;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                researchCostMultiplier = 0.1f;
            }};
            largeListusiumWall = new Wall("large-listusium-wall"){{
                requirements(Category.defense, ItemStack.mult(listusiumWall.requirements, 4));
                researchCostMultiplier = 0.25f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                health = 270 * 4;
                size = 2;
            }};

            //turrets Empyrean
            gale = new PowerTurret("gale"){{
                requirements(Category.turret, with(ExoItems.cobolt, 20, ExoItems.oltuxium, 20));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                range = 180f;
                recoil = 2f;
                reload = 50;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 2;
                hasPower = true;
                scaledHealth = 280;
                shootY = 8;
                targetAir = true;
                targetGround = false;
                shootSound = Sounds.bolt;
                inaccuracy = 6;
                shootCone = 30f;
                shoot = new ShootPattern(){{
                    shotDelay = 4.7f;
                    shots = 3;
                }};
                rotateSpeed = 6.5f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.25f));
                consumePower(12f);
                drawer = new DrawTurret("elecian-");
                shootType = new ExoFlakBulletType(){{
                    backColor = hitColor = trailColor = ExoPal.empyrean;
                    frontColor = Color.white;
                    addDamageMultiplier(
                            explosive, 1f
                    );
                    trailWidth = 2f;
                    trailLength = 6;
                    width = height = 25f;
                    shrinkX = shootY = 0;
                    lifetime = 50;
                    speed = 6;
                    spin = 4;
                    explodeDelay = 1;
                    explodeRange = 40;
                    splashDamageRadius = 15;
                    splashDamage = 6;
                    sprite = "large-bomb";
                    damage = 4;
                    hitEffect = despawnEffect = Fx.flakExplosion;
                    fragRandomSpread = 360f;
                    fragBullets = 5;
                    fragVelocityMin = 1f;

                    fragBullet = new ExoBasicBulletType(8, 3){{
                        sprite = "missile";
                        addDamageMultiplier(
                                ExoDamageTypes.pierce, 0.5f,
                                kinetic, 0.5f

                        );
                        width = 4f;
                        pierce = true;
                        pierceCap = 1;
                        homingRange = 45;
                        homingPower = 0.3f;
                        homingDelay = 0.6f;
                        height = 13f;
                        lifetime = 13f;
                        backColor = hitColor = trailColor = ExoPal.empyrean;
                        frontColor = Color.white;
                        trailWidth = 1.3f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }};
                }};
            }};
            light = new SpeedupTurret("light"){{
                requirements(Category.turret, with(ExoItems.oltuxium, 20, ExoItems.rustyCopper, 25, ExoItems.cobolt, 20));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                range = 160f;
                recoil = 2f;
                reload = 20;
                shootEffect = new Effect(10, e -> {
                    color(e.color);
                    float w = 1.2f + 7 * e.fout();

                    Drawf.tri(e.x, e.y, w, 45f * e.fout(), e.rotation);
                    color(e.color);

                    for(int i : Mathf.signs){
                        Drawf.tri(e.x, e.y, w * 0.9f, 18f * e.fout(), e.rotation + i * 90f);
                    }

                    Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
                });
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 2;
                scaledHealth = 280;
                shootSound = Sounds.bolt;
                warmupMaintainTime = 120f;
                maxSpeedupScl = 6f;
                speedupPerShoot = 0.095f;
                overheatTime = 400f;
                shootCone = 30f;
                unitSort = UnitSorts.weakest;
                shoot = new ShootAlternate(){{
                    barrels = 2;
                    spread = 6;
                }};
                rotateSpeed = 6.5f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.25f));
                consumePower(8f);
                drawer = new DrawTurret("elecian-");
                shootType = new ExoRailBulletType(){{
                    length = 160f;
                    addDamageMultiplier(
                            energy, 1f
                    );
                    damage = 4f;
                    hitColor = ExoPal.empyreanblue;
                    hitEffect = endEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                    smokeEffect = Fx.colorSpark;
                    endEffect = new Effect(14f, e -> {
                        color(e.color);
                        Drawf.tri(e.x, e.y, e.fout() * 1.5f, 5f, e.rotation);
                    });
                    lineEffect = new Effect(20f, e -> {
                        if(!(e.data instanceof Vec2 v)) return;

                        color(e.color);
                        stroke(e.fout() * 0.9f + 0.6f);

                        Fx.rand.setSeed(e.id);
                        for(int i = 0; i < 7; i++){
                            Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                            Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                        }

                        e.scaled(14f, b -> {
                            stroke(b.fout() * 1.5f);
                            color(e.color);
                            Lines.line(e.x, e.y, v.x, v.y);
                        });
                    });
                }};
            }};

            focalPoint = new ContinuousTurret("focal-point"){{
                requirements(Category.turret, with(ExoItems.oltuxium, 15, ExoItems.cobolt, 20, ExoItems.quartz, 20));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                range = 100f;
                recoil = 0f;
                shootEffect = ExoFx.colorBombSmaller;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                shootY = 4;
                size = 2;
                scaledHealth = 280;
                shootSound = Sounds.none;
                loopSoundVolume = 1f;
                loopSound = Sounds.laserbeam;

                shootWarmupSpeed = 0.08f;
                shootCone = 360f;

                rotateSpeed = 6.5f;
                unitSort = UnitSorts.strongest;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.25f));

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
                    color = ExoPal.empyreanIndigo;
                    addDamageMultiplier(
                            ExoDamageTypes.pierce, 1f
                    );
                    laserSize = 1;
                    sprite = "exogenesis-focal-point-laser";
                    beamEffect = Fx.none;
                    trailLength = 8;
                    damage = 1f;
                    hitEffect = ExoFx.hitMeltColor;
                    smokeEffect = Fx.colorSparkBig;
                }};
            }};
            bliss = new PowerShootTypeTurret("bliss"){{
                requirements(Category.turret, with(ExoItems.oltuxium, 30, ExoItems.magnetite, 30));
                researchCostMultiplier = 0.1f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                range = 130f;
                recoil = 2f;
                reload = 40;
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 2;
                scaledHealth = 280;
                shootSound = Sounds.laser;
                shootCone = 30f;
                velocityRnd = 0.1f;
                shoot = new ShootSpread(){{
                    shots = 4;
                    spread = 9;
                }};
                rotateSpeed = 6.5f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.25f));
                consumePower(4f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.reload;
                                color1 = ExoPal.empyreanblue;
                                y = 6;
                                radius = 10;
                                radiusTo = 0;
                                stroke = 2.5f;
                            }}
                    );
                }};
                ammo(
                        new ExoBasicBulletType(7, 8){{
                    homingRange = 100;
                    homingPower = 0.075f;
                    homingDelay = 6;
                    addDamageMultiplier(
                            energy, 1f
                    );
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.empyreanblue;
                                radius = 12;
                                radiusTo = 12;
                                stroke = 2.5f;
                            }}
                    );
                    lifetime = 20;
                    speed = 7;
                    damage = 25;
                    hitColor = trailColor = ExoPal.empyreanblue;
                    trailWidth = 1.3f;
                    trailLength = 4;
                    weaveScale = 6;
                    weaveMag = 2;
                    shootEffect = Fx.colorSparkBig;
                    hitEffect = despawnEffect = ExoFx.empyreanStarHitSmall;
                    smokeEffect = Fx.colorSpark;
                        }}, "base-rounds",
                        shootType = new ExoBasicBulletType(18.5f, 5){{
                            addDamageMultiplier(
                                    ExoDamageTypes.pierce, 0.7f,
                                    ExoDamageTypes.energy, 0.3f
                            );
                            width = 9;
                            height = 14;
                            reloadMultiplier = 2;
                            pierce = true;
                            pierceCap = 5;
                            shrinkY = shrinkX = 0f;
                            backSprite = "large-bomb-back";
                            sprite = "mine-bullet";
                            trailWidth = 2f;
                            trailLength = 4;
                            velocityRnd = 0.11f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeDisperse;
                            backColor = trailColor = hitColor = Color.sky;
                            lifetime = 9f;

                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }}, "piercer-rounds"

                );

            }};
            cleanser = new PowerTurret("cleanser"){{
                requirements(Category.turret, with(ExoItems.oltuxium, 35, ExoItems.cobolt, 20, ExoItems.peridotite, 20));
                researchCostMultiplier = 0.1f;
                range = 130f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outlineColor = ExoPal.empyreanOutline;
                size = 2;
                shootY = 0;
                reload = 90;
                rotateSpeed = 5.5f;
                scaledHealth = 280;
                shoot.firstShotDelay = 60;
                shootSound = Sounds.bolt;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.25f));
                consumePower(3f);
                drawer = new DrawTurret("elecian-") {{
                    parts.addAll(
                            new ShapePart() {{
                                progress = PartProgress.charge;
                                color = ExoPal.empyreanPeridot;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 4.5f;
                            }},
                            new FlarePart() {{
                                progress = PartProgress.charge;
                                color1 = ExoPal.empyreanPeridot;
                                sides = 3;
                                spinSpeed = 3;
                                radius = 0;
                                radiusTo = 0;
                                stroke = 2.5f;
                            }},
                            new FlarePart() {{
                                progress = PartProgress.charge;
                                color1 = ExoPal.empyreanPeridot;
                                sides = 3;
                                spinSpeed = 0.7f;
                                radius = 0;
                                radiusTo = 0;
                                stroke = 2.5f;
                            }},
                            new ShapePart() {{
                                progress = PartProgress.charge;
                                color = Color.white;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 2.5f;
                            }}
                    );
                }};
                shootType = new ExoBasicBulletType(2.5f, 3){{
                    width = height = 10;
                    addDamageMultiplier(
                            energy, 1f
                    );
                    sprite = "exogenesis-plasma";
                    pierce = true;
                    chargeEffect = new WaveEffect() {{
                        colorFrom = ExoPal.empyreanPeridot;
                        colorTo = ExoPal.empyreanPeridotLight;
                        sizeFrom = 30;
                        sizeTo = 0f;
                        lifetime = 60f;
                        strokeTo = 2;
                        strokeFrom = 0f;
                    }};
                    status = StatusEffects.slow;
                    statusDuration = 60;
                    homingRange = 45;
                    homingPower = 0.35f;
                    homingDelay = 0.6f;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = ExoPal.empyreanPeridot;
                    trailEffect = new ParticleEffect() {{
                        lightOpacity = 0.5f;
                        region = "exogenesis-plasma";
                        particles = 1;
                        length = baseLength = 0;
                        lifetime = 20;
                        sizeFrom = 11;
                        sizeTo = 11;
                        lightColor = colorFrom = ExoPal.empyreanPeridot;
                        colorTo = ExoPal.empyreanPeridot.cpy().a(.0f);
                    }};
                    trailRotation = true;
                    trailChance = 1;
                    lifetime = 85f;
                    shrinkY = shrinkX = 0;
                    hitEffect = despawnEffect = new MultiEffect(ExoFx.hitMeltColor);
                    shootEffect = new WaveEffect() {{
                        colorFrom = ExoPal.empyreanPeridot;
                        colorTo = ExoPal.empyreanPeridotDark;
                        sizeFrom = 0;
                        sizeTo = 20f;
                        interp = Interp.fastSlow;
                        lifetime = 40f;
                        strokeTo = 0;
                        strokeFrom = 3f;
                    }};
                }};
            }};

            prism = new ContinuousTurret("prism"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 130, ExoItems.exoGraphite, 100, ExoItems.empyreanPlating, 60, ExoItems.oltuxium, 130, ExoItems.iron, 40));
                range = 270f;
                recoil = 0f;
                reload = 10f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                shootY = 0;
                rotateSpeed = 5.5f;
                warmupMaintainTime = 25f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.04f;
                scaledHealth = 280;
                shootSound = Sounds.none;
                loopSoundVolume = 1f;
                loopSound = Sounds.laserbeam;
                unitSort = UnitSorts.strongest;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.4f));
                consumePower(12f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.warmup;
                                color1 = ExoPal.empyreanIndigo;
                                sides = 3;
                                spinSpeed = 1;
                                radius = 0;
                                radiusTo = 50;
                                stroke = 2.5f;
                            }},
                            new FlarePart(){{
                                progress = PartProgress.warmup;
                                color1 = ExoPal.empyreanIndigo;
                                sides = 2;
                                spinSpeed = 0.7f;
                                radius = 0;
                                radiusTo = 70;
                                stroke = 2.5f;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                hollow = true;
                                color = ExoPal.empyreanIndigo;
                                layer = Layer.effect;
                                circle = true;
                                stroke = 0;
                                strokeTo = 1;
                                radius = 6;
                                radiusTo = 6;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                hollow = true;
                                color = ExoPal.empyreanIndigo;
                                layer = Layer.effect;
                                circle = true;
                                stroke = 0;
                                strokeTo = 0.9f;
                                radius = 7.5f;
                                radiusTo = 7.5f;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = ExoPal.empyreanIndigoLight;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 4;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = Color.white;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 2f;
                            }}
                    );
                }};
                shootType = new ExoPointLaserBulletType(){{
                    hitColor = trailColor = ExoPal.empyreanIndigo;
                    color = Color.white;
                    addDamageMultiplier(
                            ExoDamageTypes.pierce, 1f
                    );
                    sprite = "exogenesis-prism-laser";
                    beamEffect = ExoFx.hitMeltColor;
                    oscMag = 0.1f;
                    trailWidth = 3;
                    trailLength = 8;
                    damage = 35;
                    hitEffect = ExoFx.hitMeltColor;
                    smokeEffect = Fx.colorSparkBig;
                }};
            }};
            glory = new ItemTurret("glory"){{
                requirements(Category.turret, with(ExoItems.cobolt, 180, ExoItems.empyreanPlating, 70, ExoItems.iron, 55, ExoItems.magnetite, 155));
                range = 450f;
                recoil = 2.1f;
                reload = 300f;
                shake = 2f;
                itemCapacity = 30;
                ammoPerShot = 10;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                scaledHealth = 280;
                shootSound = Sounds.railgun;
                rotateSpeed = 3;

                consumePower(13f);
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.4f));
                drawer = new DrawTurret("elecian-") {{
                    parts.addAll(
                            new RegionPart("-side") {{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                heatColor = Color.red;
                                moveX = 3f;
                                mirror = true;
                            }},
                            new RegionPart("-missile"){{
                                progress = PartProgress.reload.curve(Interp.pow2In);
                                y = 0;
                                colorTo = new Color(1f, 1f, 1f, 0f);
                                color = Color.white;
                                mixColorTo = Pal.accent;
                                mixColor = new Color(1f, 1f, 1f, 0f);
                                under = true;

                                moves.add(new PartMove(PartProgress.warmup.inv(), 0f, 2f, 0f));
                            }}
                    );
                }};
                ammo(
                ExoItems.iron, new BasicBulletType(0f, 0) {{
                    shootEffect = Fx.shootBig;
                    smokeEffect = Fx.shootSmokeMissile;
                    reloadMultiplier = 0.5f;
                    ammoMultiplier = 5f;
                    spawnUnit = new MissileUnitType("glory-ironhead-missile") {{
                        speed = 5.6f;
                        maxRange = 16f;
                        lifetime = 130f;
                        outlineColor = ExoPal.empyreanOutline;
                        engineColor = trailColor = Pal.lightishOrange;
                        engineLayer = Layer.effect;
                        engineSize = 3.4f;
                        engineOffset = 12f;
                        rotateSpeed = 0.45f;
                        trailLength = 8;
                        missileAccelTime = 50f;
                        lowAltitude = false;

                        deathSound = Sounds.explosion;
                        targetAir = true;
                        health = 210;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            deathExplosionEffect = Fx.massiveExplosion;
                            shootOnDeath = true;

                            shake = 10f;
                            bullet = new ExoExplosionBulletType() {{
                                hitColor = Pal.lightishOrange;
                                shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.colorSparkBig, ExoHitFx.titanExplosionFragExo, Fx.scatheLight, new WaveEffect() {{
                                    lifetime = 10f;
                                    strokeFrom = 4f;
                                    sizeTo = 130f;
                                }});
                                addDamageMultiplier(
                                        explosive, 0.2f,
                                        kinetic, 0.8f
                                );
                                knockback = 60;
                                collidesAir = true;
                                splashDamage = 150;
                                splashDamageRadius = 20;
                                buildingDamageMultiplier = 0.3f;

                                ammoMultiplier = 1f;
                            }};
                        }});
                    }};
                }},
                ExoItems.oltuxium, new BasicBulletType(0f, 0) {{
                    shootEffect = Fx.shootBig;
                    smokeEffect = Fx.shootSmokeMissile;
                    ammoMultiplier = 5f;
                    spawnUnit = new MissileUnitType("glory-thunder-missile") {{
                        speed = 7.6f;
                        maxRange = 16f;
                        lifetime = 100f;
                        outlineColor = ExoPal.empyreanOutline;
                        engineColor = trailColor = ExoPal.empyrean;
                        engineLayer = Layer.effect;
                        engineSize = 3.1f;
                        engineOffset = 12f;
                        rotateSpeed = 1.65f;
                        trailLength = 8;
                        missileAccelTime = 30f;
                        lowAltitude = false;

                        deathSound = Sounds.explosion;
                        targetAir = true;
                        health = 210;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            shootSound = Sounds.none;
                            reload = 6f;
                            shake = 10f;
                            bullet = new ExoLightningBulletType(){{
                                damage = 10;
                                hitColor = lightningColor = ExoPal.empyrean;
                                hitEffect = ExoFx.hitMeltColor;
                                addDamageMultiplier(
                                        energy, 1f
                                );
                                lightningLengthRand = 30;
                                lightningLength = 8;
                                buildingDamageMultiplier = 0.25f;
                            }};
                        }});
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            deathExplosionEffect = Fx.massiveExplosion;
                            shootOnDeath = true;

                            shake = 10f;
                            bullet = new ExoExplosionBulletType() {{
                                hitColor = ExoPal.empyrean;
                                shootEffect = new MultiEffect(Fx.massiveExplosion, ExoFx.empyreanStarHitMedium, ExoHitFx.titanLightSmallExo, Fx.scatheLight, new WaveEffect() {{
                                    lifetime = 10f;
                                    strokeFrom = 4f;
                                    sizeTo = 130f;
                                }});
                                addDamageMultiplier(
                                        explosive, 0.5f,
                                        energy, 0.5f
                                );
                                collidesAir = true;
                                splashDamage = 40;
                                splashDamageRadius = 60;
                                buildingDamageMultiplier = 0.3f;
                                fragOnHit = false;
                                fragRandomSpread = 0f;
                                fragSpread = 72.0f;
                                fragBullets = 5;
                                fragBullet = new ExoLaserBulletType(){{
                                    damage = 40f;
                                    addDamageMultiplier(
                                            ExoDamageTypes.energy, 1f
                                    );
                                    sideWidth = 0f;
                                    lifetime = 50;
                                    width = 15f;
                                    length = 60f;
                                    hitColor = ExoPal.empyrean;
                                    colors = new Color[]{ExoPal.empyreanDark.cpy().a(0.3f), ExoPal.empyrean, Color.white};
                                }};
                                ammoMultiplier = 1f;

                            }};
                        }});
                        abilities.add(new MoveEffectAbility(){{
                            effect = ExoFx.disperseTrailLarger;
                            rotateEffect = true;
                            rotation = 180f;
                            y = -9f;
                            color = ExoPal.empyrean;
                            interval = 1f;
                        }});
                    }};
                }},
                ExoItems.peridotite, new BasicBulletType(0f, 0) {{
                    shootEffect = Fx.shootBig;
                    smokeEffect = Fx.shootSmokeMissile;
                    ammoMultiplier = 5f;
                    reloadMultiplier = 2f;
                    rangeChange = 100;
                    spawnUnit = new MissileUnitType("glory-slowdown-missile") {{
                        speed = 7.6f;
                        maxRange = 16f;
                        lifetime = 110f;
                        outlineColor = ExoPal.empyreanOutline;
                        engineColor = trailColor = ExoPal.empyreanPeridot;
                        engineLayer = Layer.effect;
                        engineSize = 3.1f;
                        engineOffset = 12f;
                        rotateSpeed = 2.75f;
                        trailLength = 8;
                        missileAccelTime = 20f;
                        lowAltitude = false;

                        deathSound = Sounds.explosion;
                        targetAir = true;
                        health = 210;
                        weapons.add(new Weapon() {{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            deathExplosionEffect = Fx.massiveExplosion;
                            shootOnDeath = true;

                            shake = 10f;
                            bullet = new ExoExplosionBulletType() {{
                                hitColor = ExoPal.empyreanPeridot;
                                shootEffect = new MultiEffect(Fx.flakExplosionBig, ExoFx.empyreanStarHitMedium, Fx.randLifeSpark, Fx.scatheLight, new WaveEffect() {{
                                    lifetime = 10f;
                                    strokeFrom = 4f;
                                    sizeTo = 80f;
                                }});
                                status = StatusEffects.slow;
                                statusDuration = 100;
                                addDamageMultiplier(
                                        explosive, 0.5f,
                                        energy, 0.5f
                                );
                                collidesAir = true;
                                splashDamage = 23;
                                splashDamageRadius = 70;
                                buildingDamageMultiplier = 0.3f;

                                ammoMultiplier = 1f;
                                fragLifeMin = 1f;
                                fragVelocityMin = 1;
                                fragBullets = 10;
                                fragRandomSpread = 0;
                                fragSpread = 36;
                                fragBullet = new ExoBasicBulletType(6, 10){{
                                    lifetime = 50f;
                                    width = 6.5f;
                                    status = StatusEffects.slow;
                                    statusDuration = 100;
                                    weaveRandom = false;
                                    weaveMag = 40;
                                    weaveScale = -5;
                                    height = 15;
                                    addDamageMultiplier(
                                            energy, 1f
                                    );
                                    pierce = true;
                                    sprite = "missile-large";
                                    backColor = hitColor = trailColor = ExoPal.empyreanPeridot;
                                    frontColor = Color.white;
                                    trailWidth = 2f;
                                    trailLength = 6;
                                    hitEffect = despawnEffect = Fx.hitBulletColor;
                                }};
                            }};
                        }});
                    }};
                }}
                );
            }};
            sanctify = new ItemTurret("sanctify"){{
                requirements(Category.turret, with(ExoItems.cobolt, 300, ExoItems.iron, 130, ExoItems.peridotite, 400, ExoItems.exoGraphite, 150, ExoItems.empyreanPlating, 150));
                range = 250f;
                recoil = 0f;
                reload = 90;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                scaledHealth = 280;
                shootSound = Sounds.laser;
                shootY = 9;
                warmupMaintainTime = 120f;
                shootCone = 30f;
                shoot = new ShootSine(){{
                    mag = 40.0f;
                    shotDelay = 2;
                    scl = 2.5f;
                    shots = 11;
                }};
                rotateSpeed = 2.5f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.4f));
                drawer = new DrawTurret("elecian-") {{
                    parts.addAll(
                            new RegionPart("-barrel") {{
                                under = true;
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                heatColor = Color.red;
                                moveY = -3f;
                            }}
                    );
                }};
                ammo(ExoItems.peridotite, new ExoBasicBulletType(6, 7){{
                    lifetime = 50f;
                    width = 6.5f;
                    height = 15;
                    homingRange = 45;
                    homingPower = 0.3f;
                    homingDelay = 0.6f;
                    addDamageMultiplier(
                            kinetic, 1f
                    );
                    sprite = "missile-large";
                    shootEffect = Fx.shootBigColor;
                    backColor = hitColor = trailColor = ExoPal.empyreanPeridot;
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 6;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }});
            }};

            //tier 2
            tanons = new PowerShootTypeTurret("tanons"){{
                requirements(Category.turret, with(ExoItems.exoSilicon, 110, ExoItems.cobolt, 130, ExoItems.magnetite, 40, ExoItems.empyreanPlating, 50, ExoItems.rustyCopper, 100));
                researchCostMultiplier = 0.6f;
                range = 250f;
                recoil = 0;
                reload = 80f;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                scaledHealth = 280;
                rotateSpeed = 5;
                consumeLiquid(ExoLiquids.scalvaur, 0.13333336f);
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.4f));
                shoot = new ShootPattern(){{
                    shotDelay = 3.7f;
                    shots = 3;
                }};
                consumePower(11f);
                drawer = new DrawTurret("elecian-");
                ammo(
                        new PosLightningType(122f){{
                            lightningColor = hitColor = ExoPal.empyrean;
                            addDamageMultiplier(
                                    energy, 1f
                            );
                            shootSound = Sounds.spark;
                            boltNum = 2;
                            lightningDamage = 8;
                            lightning = 5;
                            lightningLength = 3;
                            lightningLengthRand = 7;
                            maxRange = rangeOverride = 250f;
                            hitEffect = Fx.hitLaserColor;
                            smokeEffect = Fx.shootBigSmoke2;
                        }}, "thunderbolt-rounds",
                        shootType = new DecayBulletType(2.5f, 30f){{
                            drag = 0.002f;
                            lifetime = 78f;
                            addDamageMultiplier(
                                    energy, 1f
                            );
                            inaccuracy = 7;
                            velocityRnd = 0.2f;
                            shootSound = Sounds.blaster;
                            backMinRadius = 3f;
                            frontMinRadius = 1.2f;
                            backRadius = 6f;
                            frontRadius = 2.75f;
                            sprite = "circle-bullet";
                            hittable = absorbable = collides = false;
                            backColor = trailColor = hitColor = lightColor = ExoPal.empyrean;
                            minInterval = 5f;
                            maxInterval = 8f;
                            shootEffect = ExoFx.empyreanStarHitSmaller;
                            smokeEffect = Fx.none;
                            hitEffect = Fx.colorSparkBig;
                            despawnEffect = ExoHitFx.lightHitLarge;
                            decayEffect = ExoFx.decayEffectLong;
                            height = 18f;
                            width = 12f;
                            decayBullet = new ExoBasicBulletType(4.8f, 24f){{
                                    drag = 0.04f;
                                    lifetime = 18f;
                                    addDamageMultiplier(
                                            energy, 1f

                                    );
                                    sprite = "circle-bullet";
                                    pierce = true;
                                    pierceCap = 3;
                                    height = 7f;
                                    width = 7f;
                                    backColor = trailColor = hitColor = lightColor = ExoPal.empyrean;
                                    hitEffect = Fx.hitLancer;
                                    despawnEffect = Fx.colorSparkBig;
                                    frontColor = ExoPal.empyrean;
                                    hittable = false;
                                }

                                @Override
                                public void draw(Bullet b){
                                    Draw.color(backColor);
                                    Fill.circle(b.x, b.y, 2.5f + (b.fout() * 3f));
                                    Draw.color(frontColor);
                                    Fill.circle(b.x, b.y, 1.75f + (b.fout() * 2.75f));
                                }

                                @Override
                                public void update(Bullet b){
                                    super.update(b);
                                    if(Mathf.chance(0.8f)){
                                        ExoFx.decayEffectYellow.at(b, b.rotation() + 180f);
                                    }
                                }
                            };
                            fragBullet = decayBullet;
                            fragBullets = 3;
                            fragVelocityMin = 0.75f;
                            fragVelocityMax = 1.25f;
                            fragLifeMin = 1.2f;
                            fragLifeMax = 1.3f;
                        }}, "ball-lightning-rounds"
                );;
            }};
            essence = new SpeedupTurret("essence"){{
                requirements(Category.turret, with(ExoItems.cobolt, 200, ExoItems.iron, 90, ExoItems.exoSilicon, 100, ExoItems.exoGraphite, 100, ExoItems.empyreanPlating, 80));
                researchCostMultiplier = 0.6f;
                range = 230f;
                recoil = 2f;
                reload = 40;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                scaledHealth = 280;
                shootSound = Sounds.blaster;
                inaccuracy = 3;
                shootY = 9;
                warmupMaintainTime = 120f;
                maxSpeedupScl = 13f;
                inaccuracyUp = 1;
                speedupPerShoot = 0.095f;
                overheatTime = 400f;
                shootCone = 30f;
                xRand = 2;
                velocityRnd = 0.15f;
                shoot = new ShootPattern(){{
                    shotDelay = 0;
                    shots = 2;
                }};
                rotateSpeed = 2.5f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.4f));
                consumePower(15f);
                drawer = new DrawTurret("elecian-");
                shootType = new ExoBasicBulletType(8, 17){{
                    lifetime = 30f;
                    width = 7;
                    height = 15;
                    sprite = "missile-large";
                    pierceArmor = true;
                    addDamageMultiplier(
                            kinetic, 1f
                    );
                    shootEffect = Fx.shootBigColor;
                    backColor = hitColor = trailColor = ExoPal.empyreanblue;
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 6;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }};
            purger = new SpeedupTurret("purger"){{
                requirements(Category.turret, with(ExoItems.exoSilicon, 100, ExoItems.cobolt, 240, ExoItems.quartz, 80, ExoItems.ameythystGeode, 100, ExoItems.empyreanPlating, 100, ExoItems.magnetite, 120));
                researchCostMultiplier = 0.6f;
                range = 210f;
                recoil = 0;
                reload = 52;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                smokeEffect = Fx.shootSmokeDisperse;
                outlineColor = ExoPal.empyreanOutline;
                size = 3;
                shake = 2;
                scaledHealth = 280;
                heatColor = Color.red;
                recoils = 2;
                shootSound = Sounds.shootBig;
                inaccuracy = 2;
                shootCone = 30f;
                shootY = 9;
                overheatCoolAmount = 0.75f;
                warmupMaintainTime = 120f;
                maxSpeedupScl = 8f;
                speedupPerShoot = 0.1f;
                overheatTime = 800f;

                shoot = new ShootAlternate(){{
                    barrels = 2;
                    spread = 12;
                }};
                rotateSpeed = 2f;
                consumeLiquid(ExoLiquids.ichorium, 0.13333336f);
                consumePower(20f);
                drawer = new DrawTurret("elecian-"){{
                    for(int i = 0; i < 2; i++){
                        int f = i;
                        parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                            progress = heatProgress = PartProgress.recoil;
                            recoilIndex = f;

                            under = true;
                            moveY = -4.5f;
                        }});
                    }
                }};
                shootType = new ArrowBulletType(18f, 100){{
                    lifetime = 49f;
                    addDamageMultiplier(
                            ExoDamageTypes.pierce, 0.5f,
                            energy, 0.5f
                    );
                    width = 6;
                    height = 16;
                    shrinkX = 0.8f;
                    shrinkInterp = Interp.slowFast;
                    drag = 0.08f;
                    shootEffect = Fx.shootBigColor;
                    backColor = hitColor = trailColor = ExoPal.empyreanPink;
                    trailChance = 0.44f;
                    rotationOffset = 90f;
                    trailRotation = true;
                    trailEffect = ExoFx.coolBulletTrail;

                    trailWidth = 2f;
                    trailLength = 6;
                    hitEffect = despawnEffect = ExoFx.blastExplosionColor;
                }};
            }};

            excalibur = new PowerShootTypeTurret("excalibur"){{
                requirements(Category.turret, with(ExoItems.cobolt, 320, ExoItems.oltuxium, 80, ExoItems.rustyCopper, 360, ExoItems.empyreanPlating, 100, ExoItems.ameythystGeode, 100, ExoItems.litusiumAlloy, 70));
                researchCostMultiplier = 0.7f;
                range = 670f;
                minRange = 150;
                recoil = 2f;
                reload = 360f;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 4;
                shootY = 10;
                scaledHealth = 280;
                targetAir = false;
                shootSound = Sounds.cannon;
                shoot = new ShootPattern(){{
                    shots = 7;
                    shotDelay = 5;
                }};
                velocityRnd = 0.3f;
                inaccuracy = 27f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.45f));

                consumePower(22f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new RegionPart("-body"){{
                                progress = PartProgress.recoil;
                                moveY = -6;
                                mirror = false;
                            }},
                            new RegionPart("-plate"){{
                                progress = PartProgress.recoil;
                                moveRot = -8;
                                mirror = true;
                            }}
                    );
                }};
                ammo(
                        new ExoArtilleryBulletType(){{
                            hitEffect = new MultiEffect(Fx.titanExplosion, ExoFx.empyreanExplosion, Fx.flakExplosionBig);
                            despawnEffect = Fx.none;
                            addDamageMultiplier(
                                    explosive, 0.8f,
                                    energy, 0.2f
                            );
                            speed = 4.5f;
                            damage = 50;
                            sprite = "shell";
                            knockback = 2f;
                            lifetime = 220f;
                            height = 27f;
                            width = 21f;
                            splashDamageRadius = 65f;
                            splashDamage = 150f;
                            scaledSplashDamage = true;
                            backColor = hitColor = trailColor = ExoPal.empyreanIndigo;
                            frontColor = Color.white;
                            hitSound = Sounds.titanExplosion;

                            status = StatusEffects.blasted;

                            trailLength = 32;
                            trailWidth = 3f;
                            trailSinScl = 2.5f;
                            trailSinMag = 0.5f;
                            despawnShake = 7f;

                            shootEffect = Fx.shootTitan;
                            smokeEffect = Fx.blastExplosion;

                            trailInterp = v -> Math.max(Mathf.slope(v), 0.8f);
                            shrinkX = 0.2f;
                            shrinkY = 0.1f;
                            buildingDamageMultiplier = 0.3f;
                        }}, "energy-artillery",
                        shootType = new ExoArtilleryBulletType(){{
                            hitEffect = new MultiEffect(Fx.titanExplosion, ExoHitFx.titanExplosionFragExo, Fx.flakExplosionBig);
                            despawnEffect = Fx.none;
                            addDamageMultiplier(
                                    explosive, 0.5f,
                                    thermal, 0.5f
                            );
                            reloadMultiplier = 0.5f;
                            inaccuracy = 4;
                            ammoMultiplier = 3;
                            lifeScaleRandMin = 0.6f;
                            lifeScaleRandMax = 1f;
                            speed = 4.5f;
                            damage = 50;
                            sprite = "shell";
                            lifetime = 220f;
                            height = 27f;
                            width = 21f;
                            splashDamageRadius = 85f;
                            splashDamage = 80f;
                            scaledSplashDamage = true;
                            backColor = hitColor = trailColor = ExoPal.cronusRedlight;
                            frontColor = Color.white;
                            hitSound = Sounds.titanExplosion;
                            status = StatusEffects.melting;
                            trailLength = 32;
                            trailWidth = 3f;
                            trailSinScl = 2.5f;
                            trailSinMag = 0.5f;
                            despawnShake = 7f;
                            shootEffect = Fx.shootTitan;
                            smokeEffect = Fx.blastExplosion;
                            trailInterp = v -> Math.max(Mathf.slope(v), 0.8f);
                            shrinkX = 0.2f;
                            shrinkY = 0.1f;
                            buildingDamageMultiplier = 0.3f;
                            fragBullets = 12;
                            fragOnAbsorb = true;
                            fragVelocityMin = 0.55f;
                            fragVelocityMax = 1f;
                            fragLifeMin = 0.45f;
                            fragBullet = new ExoArtilleryBulletType(){{
                                hitEffect = new MultiEffect(ExoHitFx.titanExplosionFragExo, ExoHitFx.titanLightSmallExo, new WaveEffect(){{
                                    lifetime = 8f;
                                    strokeFrom = 1f;
                                    sizeTo = 15f;
                                }});
                                sprite = "shell";
                                addDamageMultiplier(
                                        explosive, 0.5f,
                                        thermal, 0.5f
                                );
                                speed = 2.5f;
                                despawnEffect = Fx.hitBulletColor;
                                width = 12f;
                                height = 18f;
                                lifetime = 50f;
                                splashDamageRadius = 62f;
                                splashDamage = 20f;
                                scaledSplashDamage = true;
                                backColor = hitColor = ExoPal.cronusRedlight;
                                buildingDamageMultiplier = 0.25f;
                                shrinkY = 0.3f;
                            }};
                        }}, "thermal-bombardment"

                );
            }};
            aspect = new PowerTurret("aspect"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 160, ExoItems.cobolt, 200, ExoItems.iron, 100, ExoItems.ameythystGeode, 150, ExoItems.litusiumAlloy, 100, ExoItems.quartz, 80));
                researchCostMultiplier = 0.7f;
                range = 285f;
                recoil = 0f;
                reload = 200f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 4;
                shootY = 0;
                minWarmup = 0.99f;
                scaledHealth = 280;
                shootSound = Sounds.bolt;
                inaccuracy = 15;
                shootCone = 30f;
                velocityRnd = 0.2f;
                shoot = new ShootPattern(){{
                    shotDelay = 4.7f;
                    shots = 17;
                }};
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.45f));
                consumePower(20f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new HaloPart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                radius = 1.5f;
                                tri = true;
                                color = ExoPal.empyreanIndigo;
                                layer = Layer.effect;
                                haloRotateSpeed = -2.5f;
                                haloRadius = 0;
                                haloRadiusTo = 8;
                                stroke = 0f;
                                strokeTo = 2f;
                                shapes = 4;
                                triLengthTo = 9;
                                triLength = 0f;
                            }},
                            new HaloPart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                radius = 2.5f;
                                tri = true;
                                color = ExoPal.empyreanIndigo;
                                layer = Layer.effect;
                                haloRotateSpeed = -1f;
                                haloRadius = 0;
                                haloRadiusTo = 8;
                                stroke = 0f;
                                strokeTo = 2f;
                                shapes = 2;
                                triLengthTo = 13;
                                triLength = 0f;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = ExoPal.empyreanIndigoDark;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 8;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = Color.white;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0;
                                radiusTo = 4;
                            }}
                    );
                }};
                shootType = new BasicBulletType(7, 95){{
                    homingRange = 100;
                    homingPower = 0.075f;
                    homingDelay = 6;
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.life;
                                color1 = ExoPal.empyreanIndigo;
                                rotateSpeed = 2;
                                radius = 7;
                                radiusTo = 7;
                                stroke = 3.5f;
                            }}
                    );
                    lifetime = 45;
                    hitColor = trailColor = ExoPal.empyreanIndigo;
                    trailWidth = 2f;
                    trailLength = 6;
                    weaveScale = 4;
                    weaveMag = 2;
                    shootEffect = ExoFx.square45_6_45;
                    hitEffect = despawnEffect = ExoFx.empyreanStarHitSmall;
                    smokeEffect = Fx.colorSpark;
                }};
            }};
            eminence = new PowerTurret("eminence"){{
                requirements(Category.turret, with(ExoItems.cobolt, 350, ExoItems.luxiteStone, 150, ExoItems.magnetite, 140, ExoItems.iron, 250, ExoItems.empyreanPlating, 180, ExoItems.litusiumAlloy, 150));
                researchCostMultiplier = 0.7f;
                range = 300f;
                recoil = 2f;
                reload = 120f;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 4;
                scaledHealth = 280;
                xRand = 8;
                shoot = new ShootPattern(){{
                    shotDelay = 3f;
                    shots = 15;
                }};
                shootSound = Sounds.bolt;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.45f));
                consumePower(27f);
                drawer = new DrawTurret("elecian-");
                shootType = new BasicBulletType(0f, 0){{
                    shootEffect = Fx.shootBig;
                    hitColor = ExoPal.empyrean;
                    smokeEffect = Fx.shootSmokeMissile;
                    spawnUnit = new MissileUnitType("eminence-missile"){{
                        speed = 9.6f;
                        maxRange = 6f;
                        lifetime = 45f;
                        outlineColor = ExoPal.empyreanOutline;
                        engineColor = trailColor = ExoPal.empyrean;
                        engineLayer = Layer.effect;
                        engineSize = 1.7f;
                        engineOffset = 6f;
                        rotateSpeed = 0.9f;
                        trailLength = 6;
                        missileAccelTime = 20f;
                        lowAltitude = true;
                        loopSound = Sounds.missileTrail;
                        loopSoundVolume = 0.6f;
                        deathSound = Sounds.explosion;
                        fogRadius = 0f;
                        health = 210;

                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            deathExplosionEffect = shootEffect;
                            shootOnDeath = true;
                            shake = 2f;
                            bullet = new ExoExplosionBulletType(){{
                                hitColor = ExoPal.empyrean;
                                splashDamage = 35;
                                splashDamageRadius = 45;
                                addDamageMultiplier(
                                        explosive, 1f
                                );
                                shootEffect = new MultiEffect(ExoFx.coloredHitLarge, ExoFx.colorBombSmall);
                                collidesGround = true;
                                collidesTiles = false;
                                buildingDamageMultiplier = 0.3f;
                            }};
                        }});
                    }};
                }};
            }};
            godsent = new PowerTurret("godsent"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 260, ExoItems.cobolt, 150, ExoItems.lightningStone, 150, ExoItems.exoSilicon, 160, ExoItems.osmium, 160, ExoItems.litusiumAlloy, 100));
                researchCostMultiplier = 0.7f;
                range = 950f;
                recoil = 2f;
                reload = 80f;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 4;
                targetGround = false;
                minWarmup = 0.99f;
                scaledHealth = 280;
                shootY = 12;
                velocityRnd = 0.1f;
                shootSound = Sounds.shootSmite;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.45f));
                shoot = new ShootMulti(new ShootPattern(){{
                    shots = 2;
                    shotDelay = 3;
                }}, new ShootAlternate(){{
                    barrels = 3;
                    shots = 3;
                    spread = 6;
                }});
                consumePower(36f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new RegionPart("-side"){{
                                progress = PartProgress.recoil;
                                moveX = 3;
                                moveY = -3;
                                mirror = true;
                            }}
                    );
                }};
                shootType = new ArrowBulletType(12f, 155){{
                    lifetime = 49f;
                    collidesGround = collidesTiles = false;
                    addDamageMultiplier(
                            ExoDamageTypes.pierce, 0.5f,
                            kinetic, 0.5f
                    );
                    width = 6;
                    height = 16;
                    drag = -0.02f;
                    weaveMag = 1f;
                    weaveScale = 3f;
                    shootEffect = Fx.shootBigColor;
                    backColor = hitColor = trailColor = ExoPal.empyreanblue;
                    trailWidth = 3f;
                    trailLength = 6;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }};
            blessing = new PowerTurret("blessing"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 260, ExoItems.cobolt, 150, ExoItems.peridotite, 350, ExoItems.exoSilicon, 160, ExoItems.osmium, 160, ExoItems.litusiumAlloy, 100));
                researchCostMultiplier = 0.7f;
                range = 700f;
                recoil = 0;
                reload = 585;
                targetAir = false;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outlineColor = ExoPal.empyreanOutline;
                size = 4;
                cooldownTime = 220;
                scaledHealth = 280;
                heatColor = ExoPal.empyreanIndigo;
                shootSound = Sounds.shockBlast;
                shootCone = 20f;
                warmupMaintainTime = 30f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                shootY = 8;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.45f));
                consumePower(36f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyreanPeridot;
                                y = shootY;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 6f;
                                effectChance = 0.1f;
                            }},
                            new RegionPart("-plates-side"){{
                                progress = PartProgress.recoil.curve(Interp.bounceIn);
                                moves.add(new PartMove(PartProgress.smoothReload, 0f, -5f, 0f));
                                mirror = true;
                                moveY = 3f;
                                moveRot = -55f;
                            }},
                            new RegionPart("-plates-front"){{
                                progress = PartProgress.warmup;
                                moves.add(new PartMove(PartProgress.smoothReload, 0f, -5f, 0f));
                                moveY = 3f;
                            }},
                            new RegionPart("-plates-bottom"){{
                                progress = PartProgress.warmup;
                                moveX = 4;
                                mirror = true;
                            }}
                    );
                }};
                shootType = new FancyLaserBulletType(){{
                    damage = 175f;
                    addDamageMultiplier(
                            radiation, 0.3f,
                            energy, 0.7f
                    );
                    lifetime = 125;
                    largeHit = true;
                    boltNum = 5;
                    width = 62f;
                    length = 700f;
                    sideAngle = 150f;
                    sideWidth = 1.5f;
                    sideLength = 70f;
                    lightningSpacing = 50f;
                    lightningLength = 2;
                    lightningAngle = 70;
                    lightningAngleRand = 1;
                    lightningDelay = 0.8f;
                    lightningType = new ExoLaserBulletType(){{
                        damage = 45f;
                        sideWidth = 0f;
                        lifetime = 70;
                        addDamageMultiplier(
                                radiation, 0.3f,
                                energy, 0.7f
                        );
                        lightningSpacing = 35f;
                        lightningLength = 2;
                        lightningAngleRand = 0f;
                        lightningDelay = 0.8f;
                        lightningType = new ExoExplosionBulletType(){{
                            scaledSplashDamage = true;
                            addDamageMultiplier(
                                    explosive, 1f
                            );
                            splashDamage = 50;
                            splashDamageRadius = 65;
                            hitColor = ExoPal.empyreanPeridot;
                            status = StatusEffects.slow;
                            statusDuration = 100;
                            killShooter = false;
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
                                        lightColor = ExoPal.empyreanPeridot;
                                        waveColor = sparkColor = ExoPal.empyreanPeridot;
                                    }});
                        }};
                        width = 15f;
                        length = 120f;
                        hitColor = ExoPal.empyreanPeridot;
                        colors = new Color[]{ExoPal.empyreanPeridot.cpy().a(0.3f), ExoPal.empyreanPeridot, Color.white};
                    }};
                    shootEffect = ExoFx.square45_6_45;
                    hitColor = ExoPal.empyreanPeridot;
                    colors = new Color[]{ExoPal.empyreanPeridot.cpy().a(0.3f), ExoPal.empyreanPeridot, Color.white};
                }};
            }};
            //tier 3
            /*
            aeon = new PowerTurret("aeon"){{
                requirements(Category.turret, with(ExoItems.cobolt, 500, ExoItems.exoSilicon, 200, ExoItems.osmium, 200, ExoItems.vastanium, 200, ExoItems.neodymium, 320, ExoItems.peridotite, 250, ExoItems.vanstariumAlloy, 200, ExoItems.empyreanPlating, 150, ExoItems.litusiumAlloy, 150));
                researchCostMultiplier = 0.7f;
                range = 290f;
                recoil = 2f;
                reload = 3f;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.shootSmokeSmite;
                heatColor = ExoPal.radGreen;
                outlineColor = ExoPal.empyreanOutline;
                size = 5;
                minWarmup = 0.99f;
                shootY = 7;
                scaledHealth = 280;
                shootSound = Sounds.flame;

                inaccuracy = 4;
                shootCone = 20f;
                shoot.shots = 2;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(6f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new RegionPart("-bottom"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveY = -2f;
                                moveRot = -5;
                                mirror = true;
                                under = true;
                            }},
                            new RegionPart("-plate2"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveX = 4.5f;
                                recoilTime = 350;
                                mirror = true;
                            }},
                            new RegionPart("-barrelside"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveY = -2f;
                                moveX = 3f;
                                moveRot = -8;
                                mirror = true;
                                heatProgress = PartProgress.warmup;

                            }},
                            new RegionPart("-barrel"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                heatProgress = PartProgress.warmup;
                                moveY = -2f;
                                moveX = 3f;
                                moveRot = -5;
                                mirror = true;
                                under = true;
                            }},
                            new RegionPart("-wing"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                heatProgress = PartProgress.warmup;
                                moveY = -2f;
                                moveX = 2f;
                                mirror = true;
                                under = true;
                            }}
                    );
                }};
                shootType = new FireBulletType(6.6f, 75f){{
                    lifetime = 42f;
                    pierceCap = 6;
                    pierceBuilding = true;
                    collidesAir = true;
                    reflectable = false;
                    incendChance = 0.2f;
                    incendAmount = 1;
                    hitSize = 9f;
                    layer = Layer.bullet - 0.001f;
                    status = StatusEffects.melting;
                }};
            }};
             */
            grandeur = new ContinuousTurret("grandeur"){{
                requirements(Category.turret, with(ExoItems.cobolt, 350, ExoItems.exoSilicon, 280, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.ameythystGeode, 250, ExoItems.iron, 170, ExoItems.empyreanPlating, 200, ExoItems.litusiumAlloy, 150, ExoItems.vastanium, 170, ExoItems.vanstariumAlloy, 180));
                researchCostMultiplier = 0.7f;
                range = 660f;
                recoil = 2f;
                recoilTime = 100;
                shake = 2f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                smokeEffect = Fx.none;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 5;
                warmupMaintainTime = 30f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.04f;
                scaledHealth = 280;
                shootY = 34;
                rotateSpeed = 1;
                loopSound = ExoSounds.funnylaserloop;
                shootSound = ExoSounds.bigLaserShoot;
                loopSoundVolume = 0.9f;
                consumeLiquid(ExoLiquids.helium, 0.13333336f);
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(76f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new FlarePart(){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                color1 = ExoPal.empyreanIndigo;
                                y = 16;
                                followRotation = true;
                                rotation = 90;
                                sides = 2;
                                radius = 0;
                                radiusTo = 250;
                                stroke = 3.5f;
                            }},
                            new FlarePart(){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                color1 = ExoPal.empyreanIndigo;
                                y = 16;
                                spinSpeed = 2;
                                sides = 4;
                                radius = 0;
                                radiusTo = 120;
                                stroke = 2.5f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.recoil;
                                effectColor = ExoPal.empyreanIndigo;
                                y = 16;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 60f;
                                effectChance = 0.8f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.recoil;
                                effectColor = ExoPal.empyreanIndigo;
                                y = shootY;
                                effect = ExoFx.squareSpark;
                                randomEffectRot = 60f;
                                effectChance = 0.4f;
                            }},
                            new RegionPart("-back"){{
                                progress = PartProgress.warmup;
                                moveY = -4.5f;
                                moveX = 1;
                                mirror = true;
                                under = true;
                            }},
                            new RegionPart("-front2"){{
                                progress = PartProgress.warmup;
                                moveX = 4.5f;
                                moveY = 11f;
                                mirror = true;
                            }},
                            new RegionPart("-front"){{
                                progress = PartProgress.warmup;
                                moveX = 3.5f;
                                moveY = 3f;
                                moveRot = -28;
                                mirror = true;
                            }},
                            new RegionPart("-body"){{
                                progress = PartProgress.warmup;
                                moveY = -3.5f;
                                mirror = false;
                            }},
                            new RegionPart("-platefront"){{
                                progress = PartProgress.warmup;
                                moveX = 3f;
                                moveY = 3f;
                                mirror = true;
                            }},
                            new RegionPart("-plate"){{
                                progress = PartProgress.warmup;
                                moves.add(new PartMove(PartProgress.recoil, 2f, -4f, 0f));
                                moveX = 3.5f;
                                mirror = true;
                            }}
                    );
                }};
                shootType = new ExoContinuousLaserBulletType(){{
                    hitColor = ExoPal.empyreanIndigoDark;
                    damage = 75f;
                    addDamageMultiplier(
                            energy, 1f
                    );
                    length = 670f;
                    hitEffect = new MultiEffect(
                            new ParticleEffect(){{
                                line = true;
                                rotWithParent = true;
                                colorFrom = ExoPal.empyreanIndigo;
                                colorTo = ExoPal.empyreanIndigoDark;
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
                                colorFrom = ExoPal.empyreanIndigo;
                                colorTo = ExoPal.empyreanIndigoDark;
                                cone = 45;
                                particles = 2;
                                length = 85;
                                lifetime = 21f;
                                lenFrom = 10;
                                lenTo = 10;
                                strokeFrom = 2f;
                                strokeTo = 0.8f;
                            }});
                    drawSize = 420f;
                    backLength = 29f;
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
                    width = 19f;
                    shake = 2f;
                    largeHit = true;
                    colors = new Color[]{ExoPal.empyreanIndigoDark.cpy().a(.6f), ExoPal.empyreanIndigoDark, Color.white};
                    despawnEffect = Fx.none;
                }};
            }};
            aether = new PowerTurret("aether"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 420, ExoItems.exoSilicon, 300, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.lightningStone, 250, ExoItems.vanstariumAlloy, 200, ExoItems.empyreanPlating, 300, ExoItems.litusiumAlloy, 150));
                researchCostMultiplier = 0.7f;
                range = 290f;
                recoil = 5f;
                reload = 300f;
                shake = 4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = Fx.colorSparkBig;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                rotateSpeed = 1;
                size = 5;
                minWarmup = 0.99f;
                shootY = 12;
                scaledHealth = 280;
                cooldownTime = 320;
                shootSound = Sounds.pulseBlast;
                shootCone = 65f;

                shoot = new ShootSpread(){{
                    spread = 7f;
                    shots = 15;
                }};

                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(50f);
                drawer = new DrawTurret("elecian-"){{
                    parts.add(
                            new RegionPart("-plate2"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveX = 4.5f;
                                recoilTime = 350;
                                mirror = true;
                            }},
                            new RegionPart("-plate"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveX = 9f;
                                recoilTime = 350;
                                mirror = true;
                            }},
                            new RegionPart("-front"){{
                                progress = PartProgress.warmup;
                                under = true;
                            }},
                            new RegionPart("-back"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveY = -2f;
                                under = true;
                            }}
                    );
                }};
                /*
                shootType = new RayBulletType(){{
                    lifetime = 25f;
                    color = lightColor = lightningColor = trailColor = hitColor = ExoPal.empyreanblue;
                    impact = true;
                    knockback = 17f;
                    frac = 0.4f;
                    radius = 13f;
                    length = 260f;
                    damage = 137;
                    lightning = 2;
                    lightningLengthRand = 5;
                    lightningLength = 3;
                    lightningDamage = damage / 10f;
                    pierce = true;
                    intervalBullet = new LightningBulletType(){{
                        damage = 26;
                        lightningColor = ExoPal.empyreanblue;
                        lightningLength = 3;
                        lightningLengthRand = 7;
                        buildingDamageMultiplier = 0.25f;
                    }};
                    bulletInterval = 3f;
                    smokeEffect = ExoFx.square45_6_45;
                    hitEffect = ExoFx.square45_6_45;
                    despawnEffect = new Effect(35f, 70f, e -> {
                        Draw.color(e.color, Color.white, e.fout() * 0.7f);
                        for(int i : Mathf.signs){

                            Drawf.tri(e.x, e.y, radius * 1.5f * e.fout(), length * 0.885f * e.fout(), e.rotation + i * 90);
                            Drawf.tri(e.x, e.y, radius * 0.8f * e.fout(), length * 0.252f * e.fout(), e.rotation + 90 + i * 90);
                        }
                    });
                }};
                 */
                shootType = new ExoBasicBulletType(10f, 137){{
                    lifetime = 45f;
                    backColor = lightColor = lightningColor = trailColor = hitColor = ExoPal.empyreanblue;
                    addDamageMultiplier(
                            kinetic, 0.7f,
                            energy, 0.3f
                    );
                    impact = true;
                    knockback = 27f;
                    drag = 0.017f;
                    sprite = "circle-bullet";
                    hitSize = 12f;
                    lightning = 2;
                    lightningLengthRand = 5;
                    lightningLength = 3;
                    lightningDamage = damage / 10f;
                    width = 155f;
                    height = 7;
                    shrinkX = 0.45f;
                    shrinkY = -2.48f;
                    shrinkInterp = Interp.reverse;
                    pierce = true;
                    intervalBullet = new ExoLightningBulletType(){{
                        damage = 26;
                        addDamageMultiplier(
                                energy, 1f
                        );
                        lightningColor = ExoPal.empyreanblue;
                        lightningLength = 3;
                        lightningLengthRand = 7;
                        buildingDamageMultiplier = 0.25f;
                    }};
                    bulletInterval = 3f;
                    smokeEffect = ExoFx.square45_6_45;
                    hitEffect = ExoFx.square45_6_45;
                    despawnEffect = new Effect(35f, 70f, e -> {
                        Draw.color(e.color, Color.white, e.fout() * 0.7f);
                        for(int i : Mathf.signs){

                            Drawf.tri(e.x, e.y, height * 1.5f * e.fout(), width * 0.885f * e.fout(), e.rotation + i * 90);
                            Drawf.tri(e.x, e.y, height * 0.8f * e.fout(), width * 0.252f * e.fout(), e.rotation + 90 + i * 90);
                        }
                    });
                }};
            }};
            sacrosanct = new ItemTurret("sacrosanct"){{
                requirements(Category.turret, with(ExoItems.rustyCopper, 420, ExoItems.exoSilicon, 300, ExoItems.osmium, 200, ExoItems.neodymium, 320, ExoItems.lightningStone, 250, ExoItems.vanstariumAlloy, 200, ExoItems.empyreanPlating, 300, ExoItems.litusiumAlloy, 150));
                researchCostMultiplier = 0.7f;
                range = 330f;
                recoil = 5f;
                reload = 100f;
                shake = 4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                rotateSpeed = 2;
                size = 5;
                minWarmup = 0.99f;
                shootY = 12;
                scaledHealth = 280;
                cooldownTime = 100;
                shootSound = Sounds.shotgun;
                shootCone = 40f;
                inaccuracy = 10;
                velocityRnd = 0.5f;
                shoot = new ShootMulti(new ShootPattern(){{
                    shots = 3;
                    shotDelay = 3.5f;
                }}, new ShootSpread(){{
                    spread = 2.0f;
                    shots = 15;
                }});
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                drawer = new DrawTurret("elecian-"){{
                    parts.add(
                            new RegionPart("-back-plate"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveY = -3f;
                                moveX = 2f;
                                mirror = true;
                            }},
                            new RegionPart("-plate"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2In), 0f, -4f, 0f));
                                moveX = 2f;
                                layerOffset = -0.001f;
                                mirror = true;
                            }},
                            new RegionPart("-barrel-piece"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveY = -3f;
                                mirror = false;
                            }},
                            new RegionPart("-decal"){{
                                progress = PartProgress.warmup;
                                outlineLayerOffset = -0.002f;
                                layerOffset = 0.001f;
                            }}
                    );
                }};
                ammo(
                        //kinetic
                        ExoItems.litusiumAlloy, new BasicBulletType(28, 58){{
                            knockback = 4f;
                            width = 9f;
                            hitSize = 10f;
                            height = 20f;
                            reloadMultiplier = 0.86f;
                            ammoMultiplier = 1;
                            hitColor = backColor = trailColor = ExoPal.cronusRedlight;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }},
                        //pierce
                        ExoItems.osmium, new BasicBulletType(28, 29){{
                            knockback = 2f;
                            width = 7f;
                            hitSize = 10f;
                            height = 20f;
                            ammoMultiplier = 1;
                            pierce = true;
                            shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = ExoPal.starWhite;
                                        colorFrom = Color.white;
                                        colorTo = ExoPal.starWhite;
                                    }}
                            );
                            hitColor = backColor = trailColor = ExoPal.starWhite;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }},
                        //explosive
                        Items.pyratite, new BasicBulletType(28, 30){{
                            knockback = 4f;
                            splashDamage = 10;
                            splashDamageRadius = 10;
                            width = 7f;
                            hitSize = 10f;
                            height = 20f;
                            ammoMultiplier = 1;
                            shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = Pal.orangeSpark;
                                        colorFrom = Color.white;
                                        colorTo = Pal.orangeSpark;
                                    }}
                            );
                            hitColor = backColor = trailColor = Pal.orangeSpark;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }},
                        //eneregy
                        ExoItems.lightningStone, new BasicBulletType(28, 36){{

                                width = 7f;
                                rangeChange = 25;
                                hitSize = 10f;
                                height = 20f;
                                ammoMultiplier = 1;
                                hitColor = backColor = trailColor = ExoPal.empyreanblueLight;
                                frontColor = Color.white;
                                shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = ExoPal.empyreanblue;
                                        colorFrom = Color.white;
                                        colorTo = ExoPal.empyreanblueLight;
                                    }}
                            );
                                lifetime = 11;
                                hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                            }},
                        //cryonic
                        ExoItems.cobolt, new BasicBulletType(28, 34){{
                            knockback = 2f;
                            width = 7f;
                            hitSize = 10f;
                            height = 20f;
                            status = StatusEffects.freezing;
                            statusDuration = 60;
                            ammoMultiplier = 1;
                            shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = Pal.lancerLaser;
                                        colorFrom = Color.white;
                                        colorTo = Pal.lancerLaser;
                                    }}
                            );
                            hitColor = backColor = trailColor = Pal.lancerLaser;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }},
                        //thermal
                        ExoItems.vousarStone, new BasicBulletType(28, 37){{
                            knockback = 2f;
                            width = 7f;
                            hitSize = 10f;
                            height = 20f;
                            ammoMultiplier = 1;
                            shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = ExoPal.empyreanPink;
                                        colorFrom = Color.white;
                                        colorTo = ExoPal.empyreanPink;
                                    }}
                            );
                            hitColor = backColor = trailColor = ExoPal.empyreanPink;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }},
                        //radiation
                        ExoItems.peridotite, new BasicBulletType(28, 34){{
                            knockback = 2f;
                            width = 7f;
                            hitSize = 10f;
                            height = 20f;
                            ammoMultiplier = 1;
                            shootEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 2;
                                        length = 46;
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 20;
                                        sizeFrom = 6;
                                        sizeTo = 1;
                                        colorFrom = Pal.gray;
                                        colorTo = Pal.gray.cpy().a(0.75f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 70;
                                        lifetime = 60;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 4;
                                        sizeTo = 1;
                                        layer = 99;
                                        cone = 40;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.4f);
                                    }},
                                    new ParticleEffect(){{
                                        particles = 4;
                                        length = 64;
                                        lifetime = 47;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.pow5In;
                                        layer = 99;
                                        cone = 50;
                                        sizeFrom = 5;
                                        sizeTo = 1;
                                        colorFrom = Pal.lightishGray;
                                        colorTo = Pal.gray.cpy().a(0.4f);
                                    }},
                                    ExoFx.randLifeSparkExo1,
                                    //other
                                    new ParticleEffect(){{
                                        particles = 3;
                                        length = 50;
                                        lifetime = 22;
                                        cone = 20;
                                        interp = Interp.circleOut;
                                        sizeFrom = 4;
                                        sizeTo = 0f;
                                        lightColor = ExoPal.radGreen;
                                        colorFrom = Color.white;
                                        colorTo = ExoPal.radGreen;
                                    }}
                            );
                            hitColor = backColor = trailColor = ExoPal.radGreen;
                            frontColor = Color.white;
                            lifetime = 11;
                            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor);
                        }}
                );
            }};
            profane = new ItemTurret("profane"){{
                requirements(Category.turret, with(ExoItems.cobolt, 400, ExoItems.rustyCopper, 300, ExoItems.osmium, 350, ExoItems.thermoCore, 300, ExoItems.iron, 400, ExoItems.neodymium, 200, ExoItems.vanstariumAlloy, 180, ExoItems.empyreanPlating, 150, ExoItems.litusiumAlloy, 250));
                researchCostMultiplier = 0.7f;
                range = 1500f;
                recoil = 0f;
                reload = 1000f;
                shake = 4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 5;
                scaledHealth = 280;
                cooldownTime = 320;
                shootSound = Sounds.mediumCannon;

                warmupMaintainTime = 30f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                shootY = 16f;
                rotateSpeed = 1;
                shootCone = 20f;
                unitSort = UnitSorts.strongest;

                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(6f);
                drawer = new DrawTurret("elecian-") {{
                    parts.addAll(
                            new RegionPart("-manbible") {{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                heatColor = Color.red;
                                heatProgress = PartProgress.warmup.add(-0.2f).add(p -> Mathf.sin(9f, 0.2f) * p.warmup);
                                moveX = 6f;
                                moveY = -2;
                                moveRot = 20;
                                children.add(new RegionPart("-maniblebits"){{
                                    progress = PartProgress.warmup.delay(0.6f);
                                    mirror = true;
                                    under = true;
                                    moveX = 5f;
                                }});
                                mirror = true;
                            }},
                            new RegionPart("-plate") {{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveX = 4f;
                                moveY = -2;
                                moveRot = 2;
                                moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                                mirror = true;
                            }},
                            new RegionPart("-nuke"){{
                                progress = PartProgress.reload.curve(Interp.pow2In);
                                y = 4;
                                colorTo = new Color(1f, 1f, 1f, 0f);
                                color = Color.white;
                                mixColorTo = Pal.accent;
                                mixColor = new Color(1f, 1f, 1f, 0f);
                                under = true;

                                moves.add(new PartMove(PartProgress.warmup.inv(), 0f, -10f, 0f));
                            }}
                    );
                }};
                ammo(
                        ExoItems.thermoCore, new BasicBulletType(0f, 1) {{
                            shootEffect = Fx.shootBig;
                            smokeEffect = Fx.shootSmokeMissile;
                            ammoMultiplier = 1f;
                            spawnUnit = new MissileUnitType("vousar-missile") {{
                                speed = 4.6f;
                                maxRange = 6f;
                                lifetime = 60f * 5.5f;
                                outlineColor = ExoPal.empyreanOutline;
                                engineColor = trailColor = ExoPal.cronusRed;
                                engineLayer = Layer.effect;
                                engineSize = 3.1f;
                                engineOffset = 16f;
                                rotateSpeed = 0.25f;
                                trailLength = 18;
                                missileAccelTime = 50f;
                                lowAltitude = true;
                                loopSound = Sounds.missileTrail;
                                loopSoundVolume = 0.6f;
                                deathSound = Sounds.largeExplosion;
                                targetAir = false;

                                fogRadius = 6f;

                                health = 210;

                                weapons.add(new Weapon() {{
                                    shootCone = 360f;
                                    mirror = false;
                                    reload = 1f;
                                    deathExplosionEffect = Fx.massiveExplosion;
                                    shootOnDeath = true;
                                    shake = 10f;
                                    bullet = new ExplosionBulletType(2800f, 100f) {{
                                        hitColor = ExoPal.cronusRed;
                                        shootEffect = new MultiEffect(Fx.massiveExplosion, ExoFx.colorBomb, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect() {{
                                            lifetime = 10f;
                                            strokeFrom = 4f;
                                            sizeTo = 130f;
                                        }});

                                        collidesAir = false;
                                        buildingDamageMultiplier = 0.3f;

                                        ammoMultiplier = 1f;
                                        fragLifeMin = 0.1f;
                                        fragBullets = 12;
                                        fragBullet = new ArtilleryBulletType(3.4f, 32) {{
                                            buildingDamageMultiplier = 0.3f;
                                            drag = 0.02f;
                                            hitEffect = Fx.massiveExplosion;
                                            despawnEffect = Fx.scatheSlash;
                                            knockback = 0.8f;
                                            lifetime = 23f;
                                            width = height = 18f;
                                            collidesTiles = false;
                                            splashDamageRadius = 40f;
                                            splashDamage = 80f;
                                            backColor = trailColor = hitColor = ExoPal.cronusRed;
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
                                abilities.add(new MoveEffectAbility() {{
                                    effect = Fx.missileTrailSmoke;
                                    rotation = 180f;
                                    y = -9f;
                                    color = Color.grays(0.6f).lerp(ExoPal.cronusRedlight, 0.5f).a(0.4f);
                                    interval = 7f;
                                }});
                            }};
                        }});
            }};
            agios = new PowerTurret("agios"){{
                requirements(Category.turret, with(ExoItems.cobolt, 400, ExoItems.exoSilicon, 300, ExoItems.gold, 150, ExoItems.luxiteStone, 300, ExoItems.lightningStone, 300, ExoItems.iron, 400, ExoItems.osmium, 200, ExoItems.vanstariumAlloy, 180, ExoItems.empyreanPlating, 250, ExoItems.litusiumAlloy, 150));
                researchCostMultiplier = 0.7f;
                range = 290f;
                recoil = 0f;
                reload = 330f;
                shake = 4f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = ExoFx.colorBomb;
                heatColor = Color.red;
                outlineColor = ExoPal.empyreanOutline;
                size = 5;
                scaledHealth = 280;
                cooldownTime = 320;
                shootSound = Sounds.pulseBlast;

                warmupMaintainTime = 30f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.03f;
                shootY = 16f;
                rotateSpeed = 1;
                shootCone = 50f;
                consumeLiquid(ExoLiquids.scalvaur, 0.23333336f);
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(80f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = ExoPal.empyrean;
                                y = shootY;
                                layer = Layer.effect;
                                circle = true;
                                radius = 2;
                                radiusTo = 7;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = Color.white;
                                y = shootY;
                                layer = Layer.effect;
                                circle = true;
                                radius = 0.5f;
                                radiusTo = 5f;
                            }},
                            new FlarePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color1 = ExoPal.empyrean;
                                y = shootY;
                                sides = 2;
                                radius = 0;
                                radiusTo = 70;
                                stroke = 2.5f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyrean;
                                y = shootY;
                                effect = ExoFx.supernovaSpark;
                                randomEffectRot = 360f;
                                effectChance = 0.2f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyrean;
                                y = shootY;
                                effect = ExoFx.squareSpark;
                                randomEffectRot = 360f;
                                effectChance = 0.2f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.recoil;
                                effectColor = ExoPal.empyrean;
                                y = shootY;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 360f;
                                effectChance = 0.1f;
                            }},
                            new RegionPart("-blade"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveX = 8f;
                                moveY = -2;
                                moveRot = 65;
                                mirror = true;
                            }},
                            new RegionPart("-blade"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveX = 11f;
                                moveY = -2;
                                moveRot = 95;
                                mirror = true;
                            }},
                            new RegionPart("-blade"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveX = 11f;
                                moveY = -2;
                                moveRot = 34;
                                mirror = true;
                            }},
                            new RegionPart("-plate"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                heatColor = Color.red;
                                heatProgress = PartProgress.warmup;
                                moveX = 2f;
                                moveY = 3;
                                moveRot = 20;
                                mirror = true;
                            }},
                            new RegionPart("-bottom"){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                moveX = 4f;
                                under = true;
                                mirror = true;
                            }}
                    );
                }};
                shootType = new DestructionBulletType(1f, 160){{
                    size /= 2.2f;
                    addDamageMultiplier(
                            explosive, 0.3f,
                            energy, 0.7f
                    );
                    trailWidth = 9.5f;
                    trailLength = 57;
                    spreadEffect = slopeEffect = Fx.none;
                    backColor = trailColor = hitColor = lightColor = lightningColor = ExoPal.empyrean;
                    frontColor = Color.white;
                    scaleLife = false;
                    randomLightningChance = 1f;
                    randomGenerateRange = linkRange = 200f;
                    randomLightningNum = 5;
                    maxHit = 6;
                    range = 200f;
                    drawSize = 20f;
                    hitSound = Sounds.explosionbig;
                    splashDamageRadius = 100f;
                    splashDamage = 300;
                    lightningDamage = 11f;
                    intervalBullets = 1;
                    bulletInterval = 2;
                    trailEffect = new Effect(30f, e -> {
                        color(ExoPal.empyrean);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 5.5f, 44f * e.fslope(), e.rotation + 90f * s);
                        }
                    });
                    homingRange = 80;
                    homingPower = 0.01f;
                    trailRotation = true;
                    trailInterval = 7f;
                    intervalBullet = new ExoLightningBulletType(){{
                        damage = 25;
                        addDamageMultiplier(
                                energy, 1f
                        );
                        ammoMultiplier = 1f;
                        lightningColor = ExoPal.empyrean;
                        lightningLength = 5;
                        lightningLengthRand = 10;
                    }};
                    pierce = false;
                    collides = false;
                    lifetime = 650;
                    despawnEffect = hitEffect = ExoFx.empyreanExplosion;
                    shootEffect = ExoFx.square45_6_45;
                    hitSpacing = 3;
                    fragVelocityMin = 0.4f;
                    fragLifeMin = 0f;
                    fragBullets = 15;
                    fragBullet = new ExoBasicBulletType(4f, 70){{
                        width = height = 1f;
                        addDamageMultiplier(
                                explosive, 0.3f,
                                energy, 0.7f
                        );
                        parts.addAll(
                                new FlarePart(){{
                                    progress = PartProgress.reload;
                                    color1 = ExoPal.empyrean;
                                    y = 6;
                                    radius = 22;
                                    radiusTo = 0;
                                    stroke = 3.5f;
                                }}
                        );
                        backColor = trailColor = lightColor = lightningColor = hitColor = ExoPal.empyrean;
                        frontColor = Color.white;
                        trailEffect = Fx.missileTrail;
                        trailParam = 3.5f;
                        splashDamage = 80;
                        splashDamageRadius = 40;
                        lifetime = 58f;
                        lightning = 2;
                        lightningLength = lightningLengthRand = 4;
                        lightningDamage = 20;
                        hitSoundVolume /= 2.2f;
                        despawnShake = hitShake = 4f;
                        despawnSound = hitSound = Sounds.dullExplosion;
                        trailWidth = 3f;
                        trailLength = 7;
                        trailInterp = Interp.slope;
                        despawnEffect = ExoFx.colorBombSmall;
                        hitEffect = ExoFx.hitSparkHuge;
                    }};
                    fragVelocityMax = 1f;
                    fragVelocityMin = 0.35f;
                }};
            }};
            //tier 4
            sin = new ContinuousTurret("sin"){{
                requirements(Category.turret, with(Items.carbide, 50, Items.tungsten, 200, ExoItems.neodymium, 150, ExoItems.litusiumAlloy, 75));
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyreanIndigo;
                                y = 16;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 60f;
                                effectChance = 0f;
                            }},
                            //Exhuast particles
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                mirror = false;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.cronusRed;
                                y = -18.25f;
                                effect = ExoFx.supernovaSpark;
                                randomEffectRot = 20f;
                                effectChance = 0.15f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                mirror = true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.cronusRed;
                                x = 8.5f;
                                y = -11f;
                                effect = ExoFx.supernovaSpark;
                                randomEffectRot = 20f;
                                effectChance = 0.15f;
                            }},
                            //barrel particles
                            new RegionPart("-exhuast-glow"){{
                                mirror = false;
                                layer = Layer.effect;
                                color = new Color(1f, 1f, 1f, 0f);
                                colorTo = ExoPal.cronusRed;
                                blending = Blending.additive;
                                outline = false;
                                progress = PartProgress.warmup;
                            }},
                            new RegionPart("-exhuast-glowExtra"){{
                                mirror = false;
                                layer = Layer.effect;
                                color = new Color(1f, 1f, 1f, 0f);
                                colorTo = ExoPal.cronusRed;
                                blending = Blending.additive;
                                outline = false;
                                progress = PartProgress.warmup;
                            }},
                            new EffectSpawnPart() {{
                                useProgress = mirror = true;
                                progress = PartProgress.warmup;
                                y = 0f;
                                x = 3.5f;
                                effectColor = ExoPal.cronusRed;
                                effect = ExoFx.railgunSpark;
                                randomEffectRot = 1;
                                effectChance = 0.01f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.cronusRed;
                                y = shootY;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 35f;
                                effectChance = 0.3f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.cronusRed;
                                y = shootY;
                                effect = ExoFx.supernovaSpark;
                                randomEffectRot = 60f;
                                effectChance = 0.8f;
                            }},
                            new RegionPart("-bars-bottom"){{
                                mirror = false;
                                under = true;
                            }},
                            new RegionPart("-bottom-barrel"){{
                                progress = PartProgress.warmup.curve(Interp.fastSlow).delay(0.92f);
                                moveX = 2f;
                                under = true;
                                mirror = true;
                            }},
                            new RegionPart("-bars"){{
                                mirror = false;
                                under = true;
                            }},
                            new RegionPart("-barrel"){{
                                progress = PartProgress.warmup.curve(Interp.fastSlow);
                                moveX = 3f;
                                moves.add(new PartMove(PartProgress.warmup.delay(0.9f), 0f, -2f, 0f));
                                under = true;
                                mirror = true;
                            }},
                            new RegionPart("-body-charger"){{
                        progress = PartProgress.warmup.curve(Interp.sineOut);
                        moveY = -2.5f;
                        mirror = false;
                    }}

                    );
                }};
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootSound = Sounds.none;
                recoil = 0;
                loopSoundVolume = 1f;
                loopSound = Sounds.laserbeam;

                shootWarmupSpeed = 0.08f;
                shootCone = 10f;

                aimChangeSpeed = 0.9f;
                rotateSpeed = 0.9f;
                canOverdrive = false;

                shootY = 0f;
                minWarmup = 0.8f;
                warmupMaintainTime = 45;
                shootWarmupSpeed /= 2;
                outlineColor = ExoPal.empyreanOutline;
                size = 7;
                range = 300f;
                scaledHealth = 300;
                armor = 10;

                unitSort = UnitSorts.strongest;

                consumePower(16);
                consumeLiquid(Liquids.cryofluid, 12f / 60f);
                shootType = new ContinuousFlameBulletType(){{
                    damage = 60f;
                    length = 300;
                    drawFlare = false;
                    width = 9;
                    knockback = 1f;
                    pierceCap = 6;
                    intervalBullets = 1;
                    intervalRandomSpread = 30;
                    bulletInterval = 5;
                    buildingDamageMultiplier = 0.3f;
                    colors = new Color[]{ExoPal.cronusRedDark.a(0.55f), ExoPal.cronusRed.a(0.7f), ExoPal.cronusRed.a(0.8f), ExoPal.cronusRedlight, Color.white};
                    intervalBullet = new ContinuousFlameBulletType(){{
                        damage = 6f;
                        length = 50;
                        drawFlare = false;
                        width = 2;
                        lifetime = 40;
                        weaveMag = 2;
                        weaveScale = 4;
                        trailChance = 0.5f;
                        speed = 3;
                        pierceCap = 6;
                        buildingDamageMultiplier = 0.7f;
                        colors = new Color[]{ExoPal.cronusRedDark.a(0.65f), ExoPal.cronusRed, Color.white};
                    }};
                }};
            }};
            haborym = new ContinuousTurret("haborym"){{
                requirements(Category.turret, with(ExoItems.cobolt, 400, ExoItems.rustyCopper, 300, ExoItems.osmium, 350, ExoItems.thermoCore, 300, ExoItems.iron, 400, ExoItems.neodymium, 200, ExoItems.vanstariumAlloy, 180, ExoItems.empyreanPlating, 150, ExoItems.litusiumAlloy, 250));
                range = 770f;
                recoil = 0f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                shootEffect = ExoShootFx.HaborymShoot;
                smokeEffect = Fx.none;
                outlineColor = ExoPal.empyreanOutline;
                size = 8;
                shootY = 0;
                shootCone = 360f;

                unitSort = UnitSorts.strongest;
                loopSoundVolume = 1f;
                loopSound = Sounds.laserbeam;
                rotateSpeed = 0.5f;
                aimChangeSpeed = 0.9f;
                linearWarmup = true;
                warmupMaintainTime = 25f;
                minWarmup = 0.96f;
                shootWarmupSpeed = 0.04f;
                scaledHealth = 280;
                shootSound = Sounds.none;
                consumeLiquid(ExoLiquids.helium, 0.13333336f);
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(100f);
                drawer = new DrawTurret("elecian-"){{
                    parts.addAll(
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyreanPinkDark;
                                y = shootY;
                                effect = ExoFx.randLifeSparkExo1;
                                randomEffectRot = 360f;
                                effectChance = 0.1f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                progress = PartProgress.warmup;
                                effectColor = ExoPal.empyreanPinkDark;
                                y = shootY;
                                effect = ExoFx.randLifeSparkExo;
                                randomEffectRot = 360f;
                                effectChance = 0.07f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                width = 45;
                                height = 60;
                                y = 30;
                                progress = PartProgress.recoil;
                                effectColor = ExoPal.empyreanPinkDark;
                                effect = ExoFx.singleSparkLong;
                                randomEffectRot = 0f;
                                effectChance = 0.7f;
                            }},
                            new EffectSpawnPart() {{
                                useProgress =  true;
                                width = 45;
                                height = 60;
                                y = 30;
                                progress = PartProgress.recoil;
                                effectColor = ExoPal.empyreanPinkDark;
                                effect = new ParticleEffect(){{
                                    particles = 2;
                                    length = 50;
                                    lifetime = 22;
                                    interp = Interp.circleOut;
                                    sizeFrom = 5;
                                    sizeTo = 0.5f;
                                    cone = 1;
                                    lightColor = ExoPal.cronusRed;
                                    colorFrom = Color.white;
                                    colorTo = ExoPal.empyreanPinkDark;
                                }};
                                randomEffectRot = 0f;
                                effectChance = 0.7f;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = ExoPal.empyreanPinkDark;
                                layer = Layer.effect;
                                circle = true;
                                radius = 6;
                                radiusTo = 16;
                            }},
                            new ShapePart(){{
                                progress = PartProgress.warmup.curve(Interp.pow2In);
                                color = Color.white;
                                layer = Layer.effect;
                                circle = true;
                                radius = 3;
                                radiusTo = 10f;
                            }}
                    );
                }};
                shootType = new ExoPointLaserBulletType(){{
                    hitColor = trailColor = ExoPal.empyreanPinkDark;
                    color = Color.white;
                    addDamageMultiplier(
                            thermal, 1f
                    );
                    laserSize = 3;
                    lifetime = 45;
                    beamEffectInterval = 2;
                    sprite = "exogenesis-haborym-laser";
                    beamEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 1;
                                length = 36;
                                lifetime = 80;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                layer = 99;
                                sizeFrom = 7;
                                sizeTo = 1;
                                colorFrom = Pal.gray;
                                colorTo = Pal.gray.cpy().a(0.4f);
                            }},
                            new ParticleEffect(){{
                                particles = 2;
                                length = 50;
                                lifetime = 60;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 4;
                                sizeTo = 1;
                                layer = 99;
                                colorFrom = Pal.lightishGray;
                                colorTo = Pal.gray.cpy().a(0.4f);
                            }},
                            new ParticleEffect(){{
                                particles = 2;
                                length = 44;
                                lifetime = 47;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                layer = 99;
                                sizeFrom = 5;
                                sizeTo = 1;
                                colorFrom = Pal.lightishGray;
                                colorTo = Pal.gray.cpy().a(0.4f);
                            }},
                            ExoFx.randLifeSparkCone,
                            //other
                            new ParticleEffect(){{
                                particles = 2;
                                length = 50;
                                lifetime = 22;
                                interp = Interp.circleOut;
                                sizeFrom = 3;
                                sizeTo = 0.5f;
                                lightColor = ExoPal.empyreanPink;
                                colorFrom = Color.white;
                                colorTo = ExoPal.empyreanPink;
                            }}
                    );
                    incendChance = 0.5f;
                    incendAmount = 10;
                    incendSpread = 6;
                    shake = 1.7f;
                    oscMag = 0.1f;
                    trailWidth = 5;
                    trailLength = 8;
                    intervalBullets = 5;
                    bulletInterval = 15;
                    intervalBullet = new ShrapnelBulletType(){{
                        width = 10f;
                        length = 45;
                        lightColor = toColor = lightningColor = hitColor = ExoPal.empyreanPink;
                        damage = 100;
                        lifetime = 26f;
                        despawnEffect = Fx.none;
                        hitEffect = ExoFx.hitMeltColor;
                    }};

                    buildingDamageMultiplier = 0.3f;
                    damage = 155;
                    hitEffect = ExoFx.randLifeSparkExo1;
                    smokeEffect = Fx.colorSparkBig;
                }};
            }};
            demiurge = new PowerTurret("demiurge"){{
                requirements(Category.turret, with(Items.silicon, 80, Items.beryllium, 50, ExoItems.magnetite, 85));
                range = 300f;
                recoil = 3;
                reload = 485;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outlineColor = ExoPal.empyreanOutline;
                size = 10;
                cooldownTime = 220;
                scaledHealth = 280;
                moveWhileCharging = false;
                chargeSound = Sounds.lasercharge;
                heatColor = ExoPal.empyreanIndigo;
                shootSound = Sounds.shockBlast;
                shootCone = 60f;
                shootY = 41;
                shoot = new ShootSpread(){{
                    spread = 18;
                    shots = 7;
                    firstShotDelay = 100;
                }};
                rotateSpeed = 0.7f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(6f);
                drawer = new DrawTurret("elecian-"){{
                        parts.addAll(
                                new RegionPart("-demiurge-charge1"){{
                                    mirror = false;
                                    layer = Layer.effect;
                                    color = new Color(1f, 1f, 1f, 0f);
                                    colorTo = ExoPal.empyreanIndigo;
                                    blending = Blending.additive;
                                    outline = false;
                                    progress = PartProgress.charge;
                                }},
                                new RegionPart("-demiurge-charge2"){{
                                    mirror = false;
                                    layer = Layer.effect;
                                    color = new Color(1f, 1f, 1f, 0f);
                                    colorTo = ExoPal.empyreanIndigo;
                                    blending = Blending.additive;
                                    outline = false;
                                    progress = PartProgress.charge.delay(0.99f);
                                }},
                                new RegionPart("-demiurge-charge3"){{
                                    mirror = false;
                                    layer = Layer.effect;
                                    color = new Color(1f, 1f, 1f, 0f);
                                    colorTo = ExoPal.empyreanIndigo;
                                    blending = Blending.additive;
                                    outline = false;
                                    progress = PartProgress.charge.delay(0.98f);
                                }},
                                new RegionPart("-side-plate"){{
                                    progress = PartProgress.recoil.curve(Interp.bounceIn);
                                    mirror = true;
                                    x = 21.75f;
                                    y = -17.75f;
                                    moveRot = -65f;
                                }},
                                new RegionPart("-barrel-plate"){{
                                    progress = PartProgress.recoil.curve(Interp.bounceIn);
                                    mirror = true;
                                    x = 12.25f;
                                    y = -8.25f;
                                    moveRot = -25f;
                                }},
                                new RegionPart("-body-plate"){{
                                    progress = PartProgress.recoil.curve(Interp.circleOut);
                                    moveX = 4;
                                    mirror = true;
                                }}
                        );
                }};
                shootType = new FancyLaserBulletType(){{
                    damage = 275f;
                    addDamageMultiplier(
                            graviton, 0.9f,
                            energy, 0.1f
                    );
                    chargeEffect = ExoChargeFx.demiurgeCharge;
                    lifetime = 45;
                    sideWidth = 0f;
                    largeHit = true;
                    boltNum = 3;
                    width = 62f;
                    length = 300f;
                    hitColor = ExoPal.empyreanIndigoDark;
                    shootEffect = ExoFx.square45_6_45;
                    colors = new Color[]{ExoPal.empyreanIndigoDark.cpy().a(0.3f), ExoPal.empyreanIndigo, Color.white};
                }};
            }};
            arbiter = new SpeedupTurret("arbiter"){{
                requirements(Category.turret, with(Items.silicon, 80, Items.beryllium, 50, ExoItems.magnetite, 85));
                range = 350f;
                recoil = 0;
                reload = 92;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                outlineColor = ExoPal.empyreanOutline;
                size = 8;
                scaledHealth = 280;
                recoilPow = 2;
                heatColor = Color.red;
                recoils = 2;
                shootSound = Sounds.laser;
                shootCone = 20f;
                shootY = 24;
                warmupMaintainTime = 120f;
                maxSpeedupScl = 8f;
                speedupPerShoot = 0.1f;
                overheatTime = 800f;
                shoot = new ShootAlternate(){{
                    barrels = 2;
                    spread = 20;
                }};
                rotateSpeed = 1f;
                coolant = consume(new ConsumeLiquid(ExoLiquids.ichorium, 0.5f));
                consumePower(85f);
                drawer = new DrawTurret("elecian-"){{
                    for(int i = 0; i < 2; i++){
                        int f = i;
                        parts.addAll(
                                new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                                    progress = PartProgress.recoil.curve(Interp.fastSlow);
                                    recoilIndex = f;
                                    moveY = -6.5f;
                                }},
                                new RegionPart("-barrel-body"){{
                                    progress = PartProgress.recoil.curve(Interp.fastSlow);
                                    under = true;
                                    mirror = true;
                                }},
                                new RegionPart("-barrel-plate-" + (i == 0 ? "l" : "r")){{
                                    progress = PartProgress.recoil.curve(Interp.fastSlow);
                                    recoilIndex = f;
                                    moveY = -8.5f;
                                }}
                        );
                    }
                }};
                shootType = new ExoBasicBulletType(19, 370){{
                    lifetime = 20f;
                    splashDamageRadius = 40;
                    splashDamage = 60;
                    addDamageMultiplier(
                            explosive, 0.3f,
                            energy, 0.7f
                    );
                    status = StatusEffects.unmoving;
                    statusDuration = 1;
                    width = 14;
                    height = 21;
                    sprite = "circle-bullet";
                    pierceArmor = true;
                    shootEffect = new MultiEffect( ExoFx.randLifeSparkExo, ExoShootFx.arbitorShoot);
                    smokeEffect = new Effect(20,e->{
                        Draw.z(Layer.effect);
                        Draw.color(ExoPal.empyreanblue,e.fout());
                        Tmp.v1.trns(e.rotation, e.fin()*20f);
                        Lines.ellipse(Tmp.v1.x + e.x, Tmp.v1.y + e.y , 0.8f*e.fin()+0.1f, 8,16, e.rotation);
                        Tmp.v2.trns(e.rotation, e.fin()*10f);
                        Lines.ellipse(Tmp.v2.x + e.x, Tmp.v2.y + e.y , 0.6f*e.fin()+0.1f,8f*0.75f, 12,  e.rotation);
                        Lines.stroke(6f*e.fout());
                    });
                    backColor = hitColor = trailColor = ExoPal.empyreanblue;
                    frontColor = Color.white;
                    trailWidth = 3.5f;
                    trailLength = 10;
                    despawnHit = true;
                    hitEffect = new MultiEffect(ExoFx.empyreanStarHitLarge, ExoFx.empyreanExplosionSplash, Fx.flakExplosionBig);
                }};
            }};
            //Units
            empyreanFactory = new UnitFactory("empyrean-factory"){{
                researchCostMultiplier = 0.5f;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                requirements(Category.units, with(ExoItems.rustyCopper, 60, ExoItems.cobolt, 70, ExoItems.exoSilicon, 70));
                plans = Seq.with(
                        new UnitPlan(ExoUnitTypes.lux, 60f * 15, with(ExoItems.exoSilicon, 20, ExoItems.oltuxium, 55)),
                        new UnitPlan(ExoUnitTypes.flicker, 60f * 15, with(ExoItems.exoSilicon, 25, ExoItems.oltuxium, 25, ExoItems.cobolt, 15)),
                        new UnitPlan(ExoUnitTypes.soul, 60f * 15, with(ExoItems.exoSilicon, 25, ExoItems.rustyCopper, 40)),
                        new UnitPlan(ExoUnitTypes.prayer, 60f * 15, with(ExoItems.exoSilicon, 32, ExoItems.cobolt, 25))
                );
                size = 3;
                consumePower(2.2f);
            }};
            //Defence
            stormGuard = new LightningRod("storm-guard"){{
                requirements(Category.effect, with(ExoItems.oltuxium, 65, ExoItems.cobolt, 80, ExoItems.litusiumAlloy, 50));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                size = 2;
                sides = 360;
                shieldRotation = 22.5f;
                radius = 200;
            }};
            arielRestrictor = new ExoIronDome("ariel-restrictor"){{
                requirements(Category.effect, with(ExoItems.oltuxium, 65, ExoItems.cobolt, 80, ExoItems.peridotite, 50));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                size = 3;
                sides = 360;
                radius = 68.7f;
                shieldHealth = 2700f;
                cooldownBrokenBase = 2.35f;
                consumePower(25f);
            }};
            medicusProjector = new RegenProjector("medicus-projector"){{
                requirements(Category.effect, with(ExoItems.oltuxium, 65, ExoItems.cobolt, 80, ExoItems.peridotite, 50));
                size = 2;
                range = 12;
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                baseColor = ExoPal.empyreanPeridot;

                consumePower(5f);

                healPercent = 4f / 90f;

                Color col = ExoPal.empyreanPeridot;;

                drawer = new DrawMulti(new DrawDefault(), new DrawGlowRegion(){{
                    color = ExoPal.empyreanPeridot;
                }}, new DrawPulseShape(false){{
                    layer = Layer.effect;
                    color = col;
                }});
            }};
            //cores
            coreBelief = new CoreBlock("core-belief"){{
                requirements(Category.effect, with(ExoItems.rustyCopper, 1000, ExoItems.cobolt, 800));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                alwaysUnlocked = true;
                thrusterLength = -3.5f;
                fogRadius = 15;
                lightRadius = 15;
                isFirstTier = true;
                unitType = ExoUnitTypes.priest;
                health = 1100;
                itemCapacity = 4000;
                size = 4;

                unitCapModifier = 20;
            }};
            coreHope = new CoreBlock("core-hope"){{
                requirements(Category.effect, with(ExoItems.rustyCopper, 3000, ExoItems.cobolt, 3000, ExoItems.exoSilicon, 2000));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                unitType = ExoUnitTypes.bishop;
                health = 3500;
                itemCapacity = 9000;
                size = 5;
                fogRadius = 25;
                lightRadius = 25;
                thrusterLength = 15f;
                unitCapModifier = 30;
                researchCostMultiplier = 0.04f;
            }};
            coreReliance = new CoreBlock("core-reliance"){{
                requirements(Category.effect, with(ExoItems.rustyCopper, 8000, ExoItems.cobolt, 8000, ExoItems.exoSilicon, 5000, ExoItems.neodymium, 4000));
                envEnabled = ExoEnv.stormWorld | Env.terrestrial;
                unitType = ExoUnitTypes.apostle;
                health = 6000;
                itemCapacity = 13000;
                size = 6;
                fogRadius = 35;
                lightRadius = 35;
                thrusterLength = 24f;

                unitCapModifier = 40;
                researchCostMultiplier = 0.07f;
            }};
        }
    }
