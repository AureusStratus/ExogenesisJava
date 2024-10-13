package exogenesis.content;
import exogenesis.graphics.ExoPal;
import exogenesis.maps.ColorPass.*;
import exogenesis.maps.HeightPass.*;
import exogenesis.maps.planets.AxinPlanetGenerator;
import exogenesis.maps.planets.HadroxaPlanetGenerator;
import exogenesis.maps.planets.TauTiamasPlanetGenerator;
import exogenesis.maps.planets.VanstarPlanetGenerator;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Vec3;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.SunMesh;
import mindustry.type.Planet;
import mindustry.ui.dialogs.PlanetDialog;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Env;
import mindustry.content.Blocks;

public class ExoPlanets{

    public static Planet zetaTitanus, hadroxa, tauTiamas, vanstar, axin;
    public static void load(){
        PlanetDialog.debugSelect = true;
        zetaTitanus = new Planet("zetaTitanus", null, 6f){{
            bloom = true;
            accessible = false;
            solarSystem = this;
            meshLoader = () -> new SunMesh(
                    this, 5,
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
        hadroxa = new Planet("hadroxa", ExoPlanets.zetaTitanus, 1f, 2){{
            generator = new HadroxaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
                cloudMeshLoader = () -> new MultiMesh(
                        new HexSkyMesh(this, 2, 0.10f, 0.14f, 5, Color.valueOf("eba768").a(0.75f), 2, 0.42f, 1f, 0.23f),
                        new HexSkyMesh(this, 3, 0.2f, 0.15f, 5, Color.valueOf("eea293").a(0.75f), 2, 0.42f, 1.2f, 0.25f)
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
            hiddenItems.addAll (Items.serpuloItems).removeAll(Items.erekirItems);
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
        vanstar = new Planet("vanstar", ExoPlanets.zetaTitanus, 1f, 3){{
            generator = new VanstarPlanetGenerator() {{
                baseHeight = -1f;
                baseColor = ExoEnvironmentBlocks.vanstarock.mapColor;
                heights.addAll(
                        new AngleInterpHeight() {{
                            interp = new Interp.Exp(6, 6);
                            dir.set(4f, 10f, 0f);
                            magnitude = 1;
                        }},
                        new AngleInterpHeight() {{
                            interp = Interp.swingIn;
                            dir.set(0f, 20f, 0.3f);
                            magnitude = 6;
                        }},
                        new AngleInterpHeight() {{
                            interp = Interp.linear;
                            dir.set(0f, 0f, 0.1f);
                            magnitude = 6;
                        }},
                        new ClampHeight(-0.2f, 0.7f),
                        new NoiseHeight() {{
                            scale = 4;
                            persistence = 0.5f;
                            seed = 8;
                            octaves = 5;
                            magnitude = 0.7f;
                            heightOffset = -1f;
                            offset.set(0f, 100f, -100f);
                        }},
                        new ClampHeight(-0.2f, 1.5f),
                        //mountain
                        new CraterHeight(new Vec3(-0.3f, 0.5f, 0.5f), 0.37f, 0.2f) {{
                            set = true;
                        }},
                        new CraterHeight(new Vec3(-0.6f, 5.8f, 0.4f), 0.2f, 0.5f) {{
                            set = true;
                        }},
                        new CraterHeight(new Vec3(8f, 0f, 0.6f), 0.17f, 0.1f) {{
                            set = true;
                        }},
                        //crator?
                        new CraterHeight(new Vec3(1f, 0f, -0.2f), 0.30f, -0.3f)
                );
                colors.addAll(
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 3.5;
                            persistence = 0.5f;
                            octaves = 5;
                            magnitude = 1.6f;
                            minNoise = 0.1f;
                            maxNoise = 0.55f;
                            out = ExoEnvironmentBlocks.lightningStoneCharged.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 8;
                            scale = 7.5;
                            persistence = 1;
                            octaves = 5;
                            magnitude = 1.2f;
                            minNoise = 0.1f;
                            maxNoise = 0.55f;
                            out = ExoEnvironmentBlocks.lightningStoneDim.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 4;
                            scale = 2.5;
                            persistence = 0.5;
                            octaves = 7;
                            magnitude = 1.2f;
                            minNoise = 0.1f;
                            maxNoise = 0.5f;
                            out = ExoEnvironmentBlocks.yellowGrass.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new FlatColorPass() {{
                            minHeight = -1f;
                            maxHeight = -0.19f;
                            out = ExoEnvironmentBlocks.vansterWater.mapColor;
                        }},
                        new CraterColorPass(new Vec3(-0.3f, 0.5f, 0.8f), 0.5f, ExoEnvironmentBlocks.ferricIronWall.mapColor),
                        new CraterColorPass(new Vec3(-0.3f, 0.5f, 0.8f), 0.3f, ExoEnvironmentBlocks.vanstarock.mapColor),
                        new CraterColorPass(new Vec3(4f, 0f, 0.6f), 0.2f, ExoEnvironmentBlocks.yellowGrass.mapColor),
                        new CraterColorPass(new Vec3(1f, 0f, -0.2f), 0.4f, ExoEnvironmentBlocks.ferricIronWall.mapColor)
                );
            }};
            /*
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.white).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.48f),
                    new HexSkyMesh(this, 5, 0.15f, 0.17f, 5, new Color().set(Color.white).mul(0.9f).a(0.45f), 6, 0.35f, 0.4f, 0.18f)
            );
             */
            meshLoader = () -> new HexMesh(this, 7);
            launchCapacityMultiplier = 0.5f;
            solarSystem = zetaTitanus;
            sectorSeed = 2;
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
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            iconColor = Color.valueOf("ffc63c");
            atmosphereColor = Color.valueOf("0e5fa0");
            atmosphereRadIn = -0.03f;
            atmosphereRadOut = 0.3f;
            startSector = 15;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};
        tauTiamas = new Planet("tauTiamas", Planets.sun, 1f ,2){{
            generator = new TauTiamasPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 4);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.95f, 0.11f, 5, new Color().set(ExoPal.genesisLight).mul(0.9f).a(0.75f), 8, 0.45f, 1.6f, 0.5f),
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
        axin = new Planet("axin", ExoPlanets.zetaTitanus, 1f, 3){{
            generator = new AxinPlanetGenerator() {{
                baseHeight = -1f;
                baseColor = Color.valueOf("212630");
                heights.addAll(
                        new AngleInterpHeight() {{
                            interp = new Interp.Exp(10, 10);
                            dir.set(10f, 0f, 5f);
                            magnitude = 1.5f;
                        }},
                        new ClampHeight(-0.2f, 0.7f),
                        new NoiseHeight() {{
                            scale = 4;
                            seed = 3;
                            persistence = 1f;
                            octaves = 1;
                            magnitude = 1f;
                            heightOffset = -1f;
                            offset.set(1500f, 100f, -500f);
                        }},
                        new ClampHeight(-0.2f, 1.5f),
                        new CraterHeight(new Vec3(-1.5f, 0.5f, 0.6f), 0.4f, 0.2f) {{
                            set = true;
                        }},
                        new CraterHeight(new Vec3(1f, 12f, 0.6f), 0.47f, 0.1f) {{
                            set = true;
                        }},
                        new CraterHeight(new Vec3(1f, 5f, 1f), 0.17f, -0.2f)
                );
                colors.addAll(
                        new NoiseColorPass() {{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 3;
                            magnitude = 1.2f;
                            minNoise = 0.3f;
                            maxNoise = 0.6f;
                            out = ExoEnvironmentBlocks.axinCrystalStone.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 4.5;
                            persistence = 0.2;
                            octaves = 1;
                            magnitude = 3.2f;
                            minNoise = 0.1f;
                            maxNoise = 0.7f;
                            out = ExoEnvironmentBlocks.axinCrystalRock.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 8;
                            scale = 4.5;
                            persistence = 1;
                            octaves = 2;
                            magnitude = 6f;
                            minNoise = 0.1f;
                            maxNoise = 0.4f;
                            out = Blocks.carbonStone.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new FlatColorPass() {{
                            minHeight = -1f;
                            maxHeight = -0.19f;
                            out = Color.valueOf("adbbcf");
                        }},
                        new CraterColorPass(new Vec3(-6.5f, 0.25f, 0f), 1f, ExoEnvironmentBlocks.axinCarvakStoneWall.mapColor),
                        new CraterColorPass(new Vec3(-0.3f, 0.8f, 0.8f), 0.1f, ExoEnvironmentBlocks.axincarbonStone.mapColor),
                        new CraterColorPass(new Vec3(1f, 0f, 0.6f), 0.2f, ExoEnvironmentBlocks.axinCrystalStoneWall.mapColor),
                        new CraterColorPass(new Vec3(1f, 0f, 0f), 0.25f, ExoEnvironmentBlocks.axinCarvakStoneWall.mapColor)
                );
            }};
            solarSystem = ExoPlanets.zetaTitanus;
            meshLoader = () -> new HexMesh(this, 7);
            cloudMeshLoader = () -> new MultiMesh(
                   new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.blue).mul(0.9f).a(0.55f), 2, 0.45f, 0.9f, 0.38f),
                   new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Color.blue, 0.55f).a(0.25f), 2, 0.45f, 1f, 0.61f)
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
            atmosphereColor = Color.valueOf("1c037c");
            atmosphereRadIn = -0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 15;
            alwaysUnlocked = true;
            landCloudColor = Color.blue.cpy().a(0.5f);
            hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};
    }

}
