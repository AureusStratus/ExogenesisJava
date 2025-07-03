package exogenesis.content;

import arc.Core;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.game.Objectives;
import mindustry.type.SectorPreset;

import static exogenesis.content.ExoSectorPresets.*;
import static exogenesis.content.ExoUnitTypes.*;
import static exogenesis.content.ExoVanstarBlocks.*;
import static exogenesis.content.ExoItems.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.TechTree.nodeProduce;
import static mindustry.content.UnitTypes.*;

public class ExoVanstarTechTree {
    public static void load() {
        ExoPlanets.vanstar.techTree = nodeRoot("exogenesis-vanstar", coreBelief, () -> {
            node(coreHope, Seq.with(new Objectives.SectorComplete(ferricCrator)), () -> {
                node(coreReliance, Seq.with(new Objectives.Research(neodymium)), () -> {

                });
            });

            node(ductEmpyrean, () -> {
                node(empyreanRouter, () -> {
                    node(empyreanJunction, () -> {
                        node(empyreanSorter);
                        node(ductEmpyreanBridge);
                    });
                });
            });

            node(pulsarDrill, () -> {
                node(pulsarWallDrill, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {

                });
                node(drainPipe, Seq.with(new Objectives.SectorComplete(canyon)), () -> {
                    node(drainPipeRouter, () -> {
                        node(drainPipeBridge);
                        node(liquidCup, () -> {
                            node(pulsePump);
                            node(liquidTankEmpyrean);
                        });
                    });
                });
                node(pulseImpactDrill, Seq.with(new Objectives.SectorComplete(Fortress)), () -> {
                    node(quaryDrill, Seq.with(new Objectives.SectorComplete(Fortress), new Objectives.Produce(neodymium)), () -> {

                    });
                });

                node(smallWallGrinder, () -> {
                    node(wallGrinder, Seq.with(new Objectives.SectorComplete(canyon)), () -> {

                    });
                });
                node(platingFactory, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {

                    node(rockGrinder, Seq.with(new Objectives.SectorComplete(canyon)), () -> {
                        node(sandSift, Seq.with(new Objectives.Research(pulsePump)), () -> {
                        });
                    });

                    node(ironFurnace, Seq.with(new Objectives.SectorComplete(canyon)), () -> {
                        node(metaglassForger);

                        node(listusiumForge, () -> {
                            node(vanstaniumOven);
                        });

                        node(alloyForge, Seq.with(new Objectives.SectorComplete(ferricCrator), new Objectives.Research(ironFurnace), new Objectives.Research(metaglassForger)), () -> {

                            node(osmiumBlastForge);
                        });


                    });
                });
            });

            nodeProduce(cobolt, () -> {

                nodeProduce(rustyCopper, () -> {
                    nodeProduce(oltuxium, () -> {

                        nodeProduce(empyreanPlating, () -> {
                            nodeProduce(litusiumAlloy, () -> {
                                nodeProduce(vastanium, () -> {
                                    nodeProduce(vanstariumAlloy, () -> {
                                    });
                                });
                            });
                        });

                        nodeProduce(magnetite, () -> {
                            nodeProduce(neodymium, () -> {
                            });
                            nodeProduce(exoGraphite, () -> {
                            });
                            nodeProduce(ferricPowder, () -> {
                                nodeProduce(iron, () -> {
                                    nodeProduce(osmium, () -> {
                                    });
                                });
                            });
                        });

                    });
                    nodeProduce(vanstarBasalt, () -> {
                        nodeProduce(alumina, Seq.with(new Objectives.Research(smallWallGrinder)), () -> {
                            nodeProduce(thermite, Seq.with(new Objectives.Research(aluminaProcessor)), () -> {

                            });
                        });
                        nodeProduce(sand, () -> {
                            nodeProduce(Liquids.water, () -> {
                                nodeProduce(ExoLiquids.ichorium, Seq.with(new Objectives.Research(energyExtractor)), () -> {

                                });
                                nodeProduce(ExoLiquids.scalvaur, () -> {
                                    nodeProduce(ExoLiquids.krypton, () -> {

                                    });
                                });
                            });
                        });

                        nodeProduce(quartz, () -> {
                            nodeProduce(exoMetaglass, () -> {});
                            nodeProduce(exoSilicon, () -> {});
                            nodeProduce(exoThorium, () -> {
                                nodeProduce(ExoLiquids.helium, () -> {

                                });
                                nodeProduce(chronophite, () -> {

                                });
                            });
                        });
                    });
                });
            });

            node(energyExtractor, () -> {
                node(luxTower, Seq.with(new Objectives.Produce(neodymium)), () -> {
                });
                node(luxNode, () -> {
                    node(energyExtractorMedium, Seq.with(new Objectives.Produce(osmium) /*,new Objectives.SectorComplete(typhoon)*/), () -> {
                    });
                    node(harvesterSmall, Seq.with(new Objectives.SectorComplete(ferricCrator)), () -> {
                    });
                    node(oltuxiumBattery, () -> {
                        node(oltuxiumBatteryLarge, () -> {

                        });
                    });
                });
            });

            node(gale, () -> {
                node(bliss, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {
                    node(aether, () -> {

                    });
                });
                node(coboltWall, () -> {
                    node(medicusProjector, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {
                    });
                    node(largeCoboltWall, () -> {
                        node(oltuxiumWall, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {
                            node(largeOltuxiumWall);

                            node(ironWall, Seq.with(new Objectives.SectorComplete(canyon)), () -> {
                                node(largeIronWall, () -> {

                                });
                            });

                            /*

                            node(thoriumWall, () -> {
                                node(thoriumWallLarge);
                                node(surgeWall, () -> {
                                    node(surgeWallLarge);
                                    node(phaseWall, () -> {
                                        node(phaseWallLarge);
                                    });
                                });
                            });
                             */

                        });
                    });
                });
                node(glory, Seq.with(new Objectives.SectorComplete(canyon), new Objectives.Research(iron)), () -> {
                    node(eminence, () -> {
                        node(profane);

                    });
                });
            node(light, () -> {

                node(tanons, Seq.with(new Objectives.SectorComplete(Fortress), new Objectives.Research(exoSilicon)), () -> {
                    node(agios);
                });

                node(essence, Seq.with(new Objectives.SectorComplete(Fortress), new Objectives.Research(exoSilicon)), () -> {
                    node(godsent, () -> {
                        node(arbiter);
                    });
                    node(excalibur, () -> {
                        node(sacrosanct);
                    });
                });

                node(purger, Seq.with(new Objectives.SectorComplete(Fortress), new Objectives.Research(exoSilicon)),  () -> {
                    node(grandeur, () -> {
                        node(demiurge);
                    });
                });

                node(focalPoint, Seq.with(new Objectives.SectorComplete(StormFront)), () -> {
                    node(cleanser, Seq.with(new Objectives.Research(peridotite)), () -> {

                        node(sanctify, Seq.with(new Objectives.SectorComplete(ferricCrator), new Objectives.Research(iron)), ()-> {

                        });

                        node(prism, Seq.with(new Objectives.SectorComplete(ferricCrator), new Objectives.Research(iron)), () -> {
                            node(aspect, () -> {
                                node(haborym);
                            });
                        });
                    });

                });
            });
        });

            node(unitManufactory, Seq.with(new Objectives.SectorComplete(ferricCrator)),  () -> {
                node(soul, () -> {
                    node(pneuma, Seq.with(new Objectives.Research(neodymium)),  () -> {
                        node(psyche, () -> {
                            node(myalo, () -> {
                                node(acheron, () -> {

                                });
                            });
                        });
                    });
                    node(flicker, () -> {
                        node(ember, Seq.with(new Objectives.Research(neodymium)),  () -> {
                            node(blaze, () -> {
                                node(pyric, () -> {
                                    node(phlogiston, () -> {

                                    });
                                });
                            });
                        });
                    });
                    node(lux, () -> {
                        node(glimmer, Seq.with(new Objectives.Research(neodymium)),() -> {
                            node(shine, () -> {
                                node(auric, () -> {
                                    node(radiance, () -> {

                                    });
                                });
                            });
                        });
                    });
                    node(prayer, () -> {
                        node(apprise, Seq.with(new Objectives.Research(neodymium)),() -> {
                            node(revelation, () -> {
                                node(enlightenment, () -> {
                                    node(excelsus, () -> {

                                    });
                                });
                            });
                        });
                    });
                });
            });
            node(StormFront, () -> {
                node(canyon, Seq.with(new Objectives.SectorComplete(StormFront), new Objectives.Research(pulsarWallDrill), new Objectives.Research(largeCoboltWall)), () -> {
                    node(ferricCrator, Seq.with(new Objectives.SectorComplete(canyon), new Objectives.Research(wallGrinder)), () -> {
                        node(Fortress, Seq.with(new Objectives.SectorComplete(ferricCrator), new Objectives.Research(alloyForge)), () -> {

                        });
                    });
                });
            });
        });
    }

    public static class AtWave implements Objectives.Objective {
        public SectorPreset sector;
        public int wave;

        public AtWave(SectorPreset sector,int wave) {
            this.sector = sector;
            this.wave = wave;
        }

        @Override public boolean complete() {
            return sector.sector.hasSave() && sector.sector.save.getWave() >= wave;
        }

        @Override public String display() {
            return Core.bundle.format("requirement.omaloon-at-wave", wave, sector.localizedName);
        }
    }
}
