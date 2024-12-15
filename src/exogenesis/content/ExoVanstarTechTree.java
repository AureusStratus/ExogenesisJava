package exogenesis.content;

import arc.Core;

import mindustry.game.Objectives;
import mindustry.type.SectorPreset;

import static exogenesis.content.ExoUnitTypes.*;
import static exogenesis.content.ExoVanstarBlocks.*;
import static exogenesis.content.ExoItems.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.TechTree.nodeProduce;

public class ExoVanstarTechTree {
    public static void load() {
        ExoPlanets.vanstar.techTree = nodeRoot("exogenesis-vanstar", coreBelief, () -> {
            node(coreHope);

            node(ductEmpyrean, () -> {
                node(empyreanRouter, () -> {
                    node(empyreanJunction, () -> {
                        node(empyreanSorter);
                        node(ductEmpyreanBridge);
                    });
                });
            });

            node(pulsarDrill, () -> {

                node(drainPipe, () -> {
                    node(drainPipeRouter, () -> {
                        node(liquidCup, () -> {
                            node(pulsePump);
                            node(liquidTankEmpyrean);
                        });
                    });
                });
                node(pulseImpactDrill);
                node(pulsarWallDrill);

                node(smallWallGrinder, () -> {
                    node(wallGrinder, () -> {

                    });
                });
                node(platingFactory, () -> {

                    node(sandSift);
                    node(rockGrinder);

                    node(ironFurnace, () -> {

                        node(listusiumForge, () -> {
                            node(vanstaniumOven);
                        });

                        node(alloyForge, () -> {
                            node(metaglassForger);
                            node(osmiumBlastForge);
                        });


                    });
                });
            });

            nodeProduce(cobolt, () -> {

                nodeProduce(erythritePowder, () -> {});

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
                                        nodeProduce(gold, () -> {
                                        });
                                    });
                                });
                            });
                        });

                    });
                    nodeProduce(vanstarBasalt, () -> {

                        nodeProduce(sand, () -> {
                            nodeProduce(water, () -> {});
                        });

                        nodeProduce(quartz, () -> {
                            nodeProduce(exoMetaglass, () -> {});
                            nodeProduce(exoSilicon, () -> {});

                            nodeProduce(chronophite, () -> {
                                nodeProduce(vousarStone, () -> {});
                                nodeProduce(lightningStone, () -> {});
                                nodeProduce(luxiteStone, () -> {});
                            });
                        });
                    });
                });
            });

            node(energyExtractor, () -> {
                node(luxNode, () -> {
                    node(luxTower);

                    node(oltuxiumBattery, () -> {
                        node(oltuxiumBatteryLarge, () -> {

                        });
                    });

                });
            });

            node(gale, () -> {
                node(aether);
                node(cobolt, () -> {
                    node(largeCoboltWall, () -> {
                        node(oltuxiumWall, () -> {
                            node(largeOltuxiumWall);
                            /*
                            node(door, () -> {
                                node(doorLarge);
                            });
                            node(plastaniumWall, () -> {
                                node(plastaniumWallLarge, () -> {

                                });
                            });
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
                node(glory, () -> {
                    node(eminence, () -> {
                        node(profane);

                    });
                });
            node(light, () -> {
                node(focalPoint, () -> {
                    node(prism, () -> {
                        node(aspect, () -> {
                            node(haborym);
                        });

                        node(purger, () -> {
                            node(grandeur, () -> {
                                node(demiurge);
                            });
                        });

                        node(essence, () -> {
                            node(godsent, () -> {
                                node(arbiter);
                            });
                            node(tanons, () -> {
                                node(agios);
                            });
                            node(excalibur, () -> {
                                node(sacrosanct);
                            });
                        });
                    });
                });
            });
        });

            node(empyreanFactory, () -> {
                node(soul, () -> {
                    node(pneuma, () -> {
                        node(psyche, () -> {
                            node(myalo, () -> {
                                node(acheron, () -> {

                                });
                            });
                        });
                    });
                    node(lux, () -> {
                        node(glimmer, () -> {
                            node(shine, () -> {
                                node(auric, () -> {
                                    node(radiance, () -> {

                                    });
                                });
                            });
                        });
                    });
                    node(prayer, () -> {
                        node(apprise, () -> {
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
