package exogenesis.content;
import arc.math.Mathf;
import arc.struct.Seq;
import exogenesis.graphics.ExoPal;
import exogenesis.graphics.g3d.CircleMesh;
import exogenesis.maps.ColorPass.*;
import exogenesis.maps.HeightPass;
import exogenesis.maps.HeightPass.*;
import exogenesis.maps.planets.*;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Vec3;
import exogenesis.world.ExoTeams;
import exogenesis.world.meta.ExoEnv;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.type.Planet;
import mindustry.type.Weather;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;
import mindustry.world.meta.Env;
import mindustry.content.Blocks;

import static arc.Core.atlas;

public class ExoPlanets{

    public static Planet zetaTitanus, hadroxa, tauTiamas, ylan, vanstar, axin;
    public static void load(){
        //PlanetDialog.debugSelect = true;
        zetaTitanus = new Planet("zetaTitanus", null, 6f){{
            bloom = true;
            accessible = false;
            solarSystem = this;
            meshLoader = () -> new SunMesh(
                    this, 6,
                    5, 0.3, 2.7, 1.2, 1,
                    1.6f,
                    Color.valueOf("1c5dff"),
                    Color.valueOf("3f7fff"),
                    Color.valueOf("47b0ff"),
                    Color.valueOf("47b0ff"),
                    Color.valueOf("71c9ff"),
                    Color.valueOf("a0dfff")
            );
        }};
        hadroxa = new Planet("hadroxa", ExoPlanets.zetaTitanus, 1f, 4){{
            generator = new HadroxaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
                cloudMeshLoader = () -> new MultiMesh(
                        new HexSkyMesh(this, 2, 0.10f, 0.14f, 5, Color.valueOf("eba768").a(0.75f), 2, 0.42f, 1f, 0.23f),
                        new HexSkyMesh(this, 3, 0.2f, 0.15f, 6, Color.valueOf("eea293").a(0.75f), 2, 0.42f, 1.2f, 0.25f)
                );
            alwaysUnlocked = true;
            landCloudColor = Color.valueOf("ed6542");
            atmosphereColor = Color.valueOf("f01822");
            defaultEnv = Env.scorching | Env.terrestrial;
            solarSystem = zetaTitanus;
            startSector = 10;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            tidalLock = true;
            orbitRadius = 22f;
            lightSrcTo = 0.5f;
            lightDstFrom = 0.2f;
            clearSectorOnLose = true;
            defaultCore = Blocks.coreBastion;
            iconColor = Color.valueOf("ff1010");
            enemyBuildSpeedMultiplier = 0.4f;

            allowLaunchToNumbered = false;
            updateLighting = false;

            defaultAttributes.set(Attribute.heat, 1f);

            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = true;
                r.staticFog = true;
                r.lighting = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };

            unlockedOnLand.add(Blocks.coreBastion);
        }};
        vanstar = new Planet("vanstar", ExoPlanets.zetaTitanus, 1f, 4){{
            Vec3 ringPos = new Vec3(0,1,0).rotate(Vec3.X, 25);
            meshLoader = () -> new HexMesh(this, 6);
            generator = new VanstarPlanetGenerator() {{
                baseHeight = 0f;

                baseColor = ExoEnvironmentBlocks.vanstarock.mapColor;
                heights.add(new HeightPass.NoiseHeight() {{
                    offset.set(1000, 0, 0);
                    octaves = 8;
                    persistence = 0.55;
                    magnitude = 1.15f;
                    heightOffset = -0.5f;
                }});
                heights.add(new HeightPass.ClampHeight(0f, 0.95f));
                Mathf.rand.setSeed(8);
                Seq<HeightPass> mountains = new Seq<>();
                for (int i = 0; i < 3; i++) {
                    mountains.add(new HeightPass.DotHeight() {{
                        dir.setToRandomDirection().y = Mathf.random(1f, 5f);
                        min = -1f;
                        max = 1f;
                        magnitude = 0.13f;
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new HeightPass.MultiHeight(mountains, MultiHeight.MixType.max, MultiHeight.Operation.add));
                heights.add(new HeightPass.ClampHeight(0f, 0.75f));
                /*
                heights.add(new HeightPass.ClampHeight(0f, 0.75f));
                mountains = new Seq<>();
                for (int i = 0; i < 10; i++) {
                    mountains.add(new HeightPass.DotHeight() {{
                        dir.setToRandomDirection().y = Mathf.random(-1f, -4f);
                        min = 0.49f;
                        max = 0.9f;
                        magnitude = 0.13f;
                        dir.rotate(Vec3.X, 0f);
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new HeightPass.MultiHeight(mountains, MultiHeight.MixType.max, MultiHeight.Operation.add));
                heights.add(new HeightPass.ClampHeight(0f, 0.75f));
                */
                heights.add(new HeightPass.ClampHeight(0f, 0.76f));

                colors.addAll(
                        new NoiseColorPass() {{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 3;
                            magnitude = 1.2f;
                            min = 0f;
                            max = 0.5f;
                            out = ExoEnvironmentBlocks.yellowGrass.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 1.5;
                            persistence = 0.3;
                            octaves = 5;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.lightningStoneDim.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 1.5;
                            persistence = 0.3;
                            octaves = 5;
                            magnitude = 0.95f;
                            min = 0.1f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.lightningStoneCharged.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 3;
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 7;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.redLightningStone.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 7;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.blueLightningStone.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 8;
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 7;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.marble.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }}
                );
                for(int i = 0; i < 5; i++) {
                    colors.add(new SphereColorPass(new Vec3().setToRandomDirection(), 0.06f, ExoEnvironmentBlocks.vanstarock.mapColor));
                }
                colors.add(
                        new FlatColorPass() {{
                            min = 0;
                            max = 0.07f;
                            out = ExoEnvironmentBlocks.vansterWater.mapColor;
                        }},
                        new FlatColorPass() {{
                            min = 0.3f;
                            max = 0.5f;
                            out = ExoEnvironmentBlocks.crystallineCoboltStone.mapColor;
                        }},
                        new FlatColorPass() {{
                            max = 1f;
                            min = 0.52f;
                            out = ExoEnvironmentBlocks.yellowIce.mapColor;
                        }}
                );
            }};

            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 1, 0.65f, 0.19f, 4, new Color().set(Color.white).mul(0.9f).a(0.25f), 7, 0.45f, 0.6f, 0.20f),
                    new HexSkyMesh(this, 2, 0.85f, 0.17f, 5, new Color().set(Color.white).mul(0.9f).a(0.65f), 6, 0.45f, 0.7f, 0.30f),
                    new HexSkyMesh(this, 3, 1.15f, 0.15f, 6, new Color().set(Color.white).mul(0.9f).a(0.95f), 6, 0.65f, 0.35f, 0.6f)
            );
            launchCapacityMultiplier = 0.5f;
            solarSystem = zetaTitanus;
            defaultEnv = ExoEnv.stormWorld | Env.terrestrial;
            sectorSeed = 2;
            defaultCore = ExoVanstarBlocks.coreBelief;
            orbitRadius = 40;
            tidalLock = true;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = ExoTeams.empyrean;
                    r.weather.add(new Weather.WeatherEntry(Weathers.rain){{
                        always = true;
                    }});
                r.fog = true;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            //hasAtmosphere = false;
            iconColor = Color.valueOf("ffc63c");
            atmosphereColor = Color.valueOf("d58917");
            atmosphereRadIn = -0.03f;
            atmosphereRadOut = 0.3f;
            startSector = 665;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            ruleSetter = r -> {
                r.blockWhitelist = true;
                r.hideBannedBlocks = true;
                r.bannedBlocks.clear();
                r.bannedBlocks.addAll(Vars.content.blocks().select(block -> {
                    boolean VanstarOnly = block.minfo.mod != null && block.minfo.mod.name.equals("exogenesis");
                    boolean sandboxOnly = block.buildVisibility == BuildVisibility.sandboxOnly;

                    return VanstarOnly || sandboxOnly;
                }));
            };
            /*
            ruleSetter = r -> r.bannedBlocks.addAll(new Seq<Block>().addAll(
                    Vars.content.blocks().select(block -> {
                        boolean notExo = block.minfo.mod == null || !block.minfo.mod.name.equals("exogenesis");
                        return notExo;
                    })
            ));
             */
        }};
        tauTiamas = new Planet("tauTiamas", Planets.sun, 1f ,3){{
            Vec3 ringPos = new Vec3(0,2.2f,0).rotate(Vec3.X, 0);
            Vec3 ringPos1 = new Vec3(0,0.35f,0).rotate(Vec3.X, 0);
            generator = new TauTiamasPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 4),

                    new CircleMesh(atlas.find("exogenesis-ring3"), this, 80, 3.5f, 2.6f, ringPos),

                    new CircleMesh(atlas.find("exogenesis-ring1"), this,80, 1.9f, 2.1f, ringPos1)
                    );
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.95f, 0.11f, 6, new Color().set(ExoPal.genesisLight).mul(0.9f).a(0.75f), 8, 0.45f, 1.6f, 0.5f),
                    new HexSkyMesh(this, 1, 1.3f, 0.15f, 6, Color.white.cpy().lerp(ExoPal.genesisLight, 0.55f).a(0.75f), 6, 0.45f, 0.6f, 0.21f)
            );
            atmosphereColor = Color.valueOf("021042");
            iconColor = Color.valueOf("1a1f73");
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            orbitRadius = 44;
            orbitSpacing = 1f;
            startSector = 10;
            totalRadius = 5.9f;
            atmosphereRadIn = -0.01f;
            atmosphereRadOut = 0.3f;
            defaultEnv = Env.underwater | Env.terrestrial;
            alwaysUnlocked = true;
            ruleSetter = r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
        }};
        /*
        ylan = new Planet("yulan", ExoPlanets.zetaTitanus, 0.08f){{
            Block base = ExoEnvironmentBlocks.coboltCrystalFloor, tint = ExoEnvironmentBlocks.yellowIce;
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, PlanetGrid.Ptile.empty));
            camRadius = 0.68f * 2f;
            minZoom = 0.6f;
            orbitRadius = 4.8f;
            drawOrbit = true;
            accessible = false;
            clipRadius = 2f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                        this, 0, 2, radius, 2, 0.55f, 0.45f, 14f,
                        color, tinted, 3, 0.6f, 0.38f, 0.5f
                ));

                for(int j = 0; j < 8; j++){
                    meshes.add(new MatMesh(
                            new NoiseMesh(this, j + 1, 1, 0.022f + rand.random(0.039f) * 2f, 2, 0.6f, 0.38f, 20f,
                                    color, tinted, 3, 0.6f, 0.38f, 0.5f),
                            new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * 2f)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
         */
        axin = new Planet("axin", ExoPlanets.zetaTitanus, 1f, 4){{
            Vec3 ringPos = new Vec3(0,1,0).rotate(Vec3.X, 25);
            Vec3 ringPos1 = new Vec3(0,1,0).rotate(Vec3.X, 75);
            generator = new AxinPlanetGenerator() {{
                baseHeight = 0f;
                baseColor = Color.valueOf("212630");

                heights.addAll(
                        new NoiseHeight() {{
                            seed = 6;
                            persistence = 0.62f;
                            octaves = 4;
                            scale = 1.2;
                            magnitude = 1.35f;
                            heightOffset = -0.7f;
                            offset.set(500f, 0f, -500f);
                        }}
                );
                heights.add(new HeightPass.ClampHeight(0f, 0.85f));
                Mathf.rand.setSeed(10);
                Seq<HeightPass> mountains = new Seq<>();
                for (int i = 0; i < 3; i++) {
                    mountains.add(new HeightPass.DotHeight() {{
                        dir.setToRandomDirection().y = Mathf.random(5f, 1f);
                        min = -1f;
                        magnitude = 0.06f;
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new HeightPass.MultiHeight(mountains, MultiHeight.MixType.max, MultiHeight.Operation.add));
                heights.add(new HeightPass.ClampHeight(0f, 0.95f));
                colors.addAll(
                        new NoiseColorPass() {{
                            seed = 7;
                            scale = 1.4;
                            persistence = 1;
                            octaves = 3;
                            magnitude = 1.2f;
                            min = 0f;
                            max = 0.55f;
                            out = Color.valueOf("8d9ac3");
                            offset.set(1500f, 0f, 0f);
                        }},
                        new NoiseColorPass() {{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 3;
                            magnitude = 1.2f;
                            min = 0f;
                            max = 0.6f;
                            out = ExoEnvironmentBlocks.axinCrystalStone.mapColor;
                            offset.set(1500f, 0f, 0f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 1.5;
                            persistence = 0.2;
                            octaves = 1;
                            magnitude = 1.2f;
                            min = 0f;
                            max = 0.35f;
                            out = ExoEnvironmentBlocks.axinCrystalRock1.mapColor;
                            offset.set(1500f, 0f, 0f);
                        }},
                        new NoiseColorPass() {{
                            seed = 9;
                            scale = 1.5;
                            persistence = 0.8f;
                            octaves = 9;
                            magnitude = 1f;
                            min = 0f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.axinCarvakStone.mapColor;
                            offset.set(1500f, 0f, 0f);
                        }},
                        new NoiseColorPass() {{
                            seed = 8;
                            scale = 4.5;
                            persistence = 1;
                            octaves = 2;
                            magnitude = 6f;
                            min = 0f;
                            max = 0.4f;
                            out = ExoEnvironmentBlocks.axinSlate2.mapColor;
                            offset.set(1500f, 0f, 0f);
                        }},
                        new FlatColorPass() {{
                            max = 1f;
                            min = 0.52f;
                            out = Color.valueOf("99adc9");
                        }}
                        /*
                        new FlatColorPass() {{
                            min = 0;
                            max = 0.02f;
                            out = ExoEnvironmentBlocks.axinCrystalStone.mapColor;
                        }}
                         */
                );
            }};
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 6),
                    new CircleMesh(atlas.find("exogenesis-ring1"), this, 80, 2.55f, 2.6f, ringPos),
                    new CircleMesh(atlas.find("exogenesis-ring3"), this,80, 2.2f, 2.5f, ringPos),
                    new CircleMesh(atlas.find("exogenesis-ring3"), this,80, 1.9f, 2.1f, ringPos1)
            );
            solarSystem = ExoPlanets.zetaTitanus;
            cloudMeshLoader = () -> new MultiMesh(
                   new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.blue).mul(0.9f).a(0.55f), 2, 0.45f, 0.9f, 0.38f),
                   new HexSkyMesh(this, 1, 0.6f, 0.16f, 6, Color.white.cpy().lerp(Color.blue, 0.55f).a(0.25f), 2, 0.45f, 1f, 0.61f)
            );
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 2;
            orbitRadius = 80;
            orbitSpacing = 30;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            iconColor = Color.valueOf("0044ff");
            //hasAtmosphere = false;
            atmosphereColor = Color.valueOf("4d4372");
            atmosphereRadIn = -0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 15;
            alwaysUnlocked = true;
            landCloudColor = Color.blue.cpy().a(0.5f);
        }};
    }

}
