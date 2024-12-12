package exogenesis.content;

import exogenesis.graphics.ExoPal;
import exogenesis.world.blocks.ExoPowerProp;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.Attribute;

public class ExoEnvironmentBlocks {
    public static Block
    //mic
    oreOsmium, stellarIron,
    //Empyrean
    //env tiles
    powerCrystal,
    //vanstar
    deepVansterWater, vansterWater, shallowVansterWater, vansterSandyWater, yellowIce, yellowGrass,
    lightningStoneRedPower, lightningStoneBluePower, lightningStoneYellowPower, vousarCrystal,
     lightningStoneDim,lightningStoneDimWall, lightningStoneDimWater, lightningStonePurple, lightningSlatePurple, lightningSlateSmoothPurple, purpleLightningStoneWall,
     lightningStoneCharged, lightningStoneChargedWall, redLightningStone, redLightningStoneWall, blueLightningStone, blueLightningStoneWall, purpleBoulder,

    vanstarLargeTree, vanstarStandardTree, vanstarDeadTree,

    blackSand, ferricSand, ferricSlate, ferricSandWater, ferricStoneWater,

    marble, marbleWall, turraka, phosleStone, turrakaWater, phosleStoneWater, turrakaBoulder, gildedMarble,

    erythriteFloor, erythriteRouphFloor, crystallineCoboltStone, coboltCrystalFloor, coboltCrystallineBoulder, crystallineCoboltCrystals, erythriteFloorWater, coboltCrystalFloorWater,
    erythriteWall, coboltCrystalWall, coboltCrystallineWall, coboltDeposit, coboltCrystal, coboltDepositWall,

    skystonegrey, skystone, vanstarock, vanstarockCratered, vanstarockSlate, vanstarockWall, vanstarockRound, skystonebright,vanstarockWater,
    voltCrystalRed, voltCrystalBlue, voltCrystalYellow,
    //Axin
    axinCrystal, poolAxinPlasma , axinIce, axinPurpleStone, axinPurpleStoneMineral,  axinStone, axincarbonStone, axinRock, axinStoneWall,
    thenmialPlasma, thenmialPlasmaShallow, thenmialPlasmaDeep, thenmialPlasmaAbyssal, axinCyanSlate, axinSlate, axinCrystalStone, axinPurpleRock, axinPurpleSlate,
    axinStoneMinerals, alignPlating, axinCrystalBlue, axinCrystalPurple, axinCrystalTile, colossalAxinMonolith, largeAxinMonolith, mediumAxinMonolith, smallAxinMonolith, diamondGrowth, diamondTile,
    diamondWall, axinPurpleWall, axinCrystalStoneWall, axinCarvakStone, axinSlate2, axinCrystalRockBoulder, curtusesGeode, axinBoulder, axinCarvakStoneWall, axinCrystalRock, thermakronxCrystal, axinCrystalRock1,
    //ore
    oreOltuxium, oreGraphite, oreCobolt, rustyCopperOre, oreChronophite, oreGold, oreNeodymium, oreVousar, oreLightningStone, oreRadite, oreViliolite, oreLuxite, oreAxiradamite, oreUrbium, oreLanosium, ferricIronWall,
    magnetiteOreWall, magnetiteCrystal, lightningCrystal, lightningStoneCrystal, luxiteCrystal, voilitCrystal, nickelGeode, curtusesOre ;
    public static void load() {
        oreOsmium = new OreBlock(ExoItems.osmium) {{
            variants = 5;
            wallOre = true;
        }};
        oreGraphite = new OreBlock("graphite-oreWall", ExoItems.exoGraphite){{
            wallOre = true;
        }};
        stellarIron = new OreBlock("stellar-iron",ExoItems.stellarIron) {{
            variants = 3;
        }};
        oreAxiradamite = new OreBlock("ore-siradamite",ExoItems.axidamite) {{
            variants = 3;
        }};
        oreUrbium = new OreBlock("ore-urbium",ExoItems.urbium) {{
            variants = 3;
        }};
        oreLanosium = new OreBlock("oreLanosium",ExoItems.lanosium) {{
            variants = 3;
        }};
        curtusesOre = new TallBlock("curtusesOre-boulder") {{
            variants = 3;
            itemDrop = ExoItems.curtuses;
            clipSize = 128f;
        }};
        nickelGeode = new StaticWall("nickel-geode") {{
            itemDrop = ExoItems.nickel;
            variants = 2;
        }};
        curtusesGeode = new StaticWall("curtuses-geode") {{
            itemDrop = ExoItems.curtuses;
            variants = 3;
        }};

        oreOltuxium = new OreBlock(ExoItems.oltuxium);
        oreCobolt = new OreBlock("cobolt-ore",ExoItems.cobolt) {{
            variants = 3;
        }};
        rustyCopperOre = new OreBlock("rustryCopperOre",ExoItems.rustyCopper) {{
            variants = 3;
        }};
        oreChronophite = new OreBlock(ExoItems.chronophite);
        oreGold = new OreBlock(ExoItems.gold);
        oreNeodymium = new OreBlock("neodymium-ore",ExoItems.neodymium) {{
            variants = 4;
        }};
        oreLuxite = new OreBlock("luxite-ore",ExoItems.luxiteStone) {{
            variants = 3;
        }};
        oreLightningStone = new OreBlock("lightningstone-ore-wall",ExoItems.lightningStone) {{
            variants = 3;
        }};
        oreRadite = new OreBlock("radite-ore",ExoItems.peridotite) {{
            variants = 3;
        }};
        oreViliolite = new OreBlock("viliolite-ore",ExoItems.ameythystGeode) {{
            variants = 3;
        }};
        oreVousar = new OreBlock("vousar-ore",ExoItems.vousarStone) {{
            variants = 3;
        }};

        magnetiteOreWall = new StaticWall("magnetite-ore-wall") {{
            itemDrop = ExoItems.magnetite;
            variants = 3;
        }};
        magnetiteCrystal = new TallBlock("magnetite-crystal-blocks") {{
            variants = 3;
            itemDrop = ExoItems.magnetite;
            clipSize = 128f;
        }};
        //Vanstar Tiles

        //Ferric Biome
        ferricIronWall = new StaticWall("ferric-iron-wall") {{
            attributes.set(ExoAttribute.ferric, 3f);
            variants = 3;
        }};
        ferricSand = new Floor("ferricSand") {{
            itemDrop = Items.sand;
        }};
        ferricSlate = new Floor("ferric-slate") {{
            variants = 6;
        }};
        ferricSandWater = new Floor("ferricSand-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 4;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};
        ferricStoneWater = new Floor("ferric-stone-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 4;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};

        //Cobolt Biome
        coboltCrystalWall = new StaticWall("cobolt-crystal-wall"){{
            variants = 4;
            itemDrop = ExoItems.iron;
        }};
        coboltCrystalFloor = new Floor("cobolt-crystal-floor"){{
            variants = 4;
        }};
        crystallineCoboltStone = new Floor("crystalline-cobolt-stone"){{
            variants = 5;
        }};
        coboltDeposit = new Floor("cobolt-deposit"){{
            itemDrop = ExoItems.cobolt;
            playerUnmineable = true;
            variants = 6;
        }};
        coboltDepositWall = new StaticWall("cobolt-desposite-wall") {{
            itemDrop = ExoItems.cobolt;
            variants = 3;
        }};
        coboltCrystalFloorWater = new Floor("cobolt-crystal-floor-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 4;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};
        coboltCrystallineWall = new StaticWall("cobolt-crystalline-wall"){{
            variants = 4;
        }};
        crystallineCoboltCrystals = new TallBlock("crystalline-cobolt-crystals") {{
            variants = 3;
            clipSize = 128f;
        }};
        coboltCrystal = new TallBlock("cobolt-crystal") {{
            variants = 3;
            rotationRand = 40f;
            clipSize = 128f;
        }};

        coboltCrystallineBoulder = new Prop("cobolt-crystalline-boulder") {{
            variants = 2;
        }};

        erythriteFloor = new Floor("erythrite-floor") {{
            variants = 6;
        }};
        erythriteRouphFloor = new Floor("erythrite-rouph-floor") {{
            variants = 5;
        }};
        erythriteWall = new StaticWall("erythrite-wall") {{
            itemDrop = ExoItems.erythritePowder;
            attributes.set(ExoAttribute.erythric, 1f);
            variants = 3;
        }};
        erythriteFloorWater = new Floor("erythrite-floor-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 6;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};

        //Vanstar Rock field
        vanstarock = new Floor("vanstarock") {{
            variants = 7;
        }};
        vanstarockCratered = new Floor("vanstar-rock-cratered") {{
            variants = 5;
        }};
        vanstarockSlate = new Floor("vanstar-rock-slate") {{
            variants = 4;
        }};

        vanstarockRound = new Floor("vanstarock-round") {{
            variants = 4;
        }};
        skystonegrey = new Floor("skystonegrey") {{
            variants = 5;
        }};
        skystone = new Floor("skystone") {{
            variants = 4;
        }};
        skystonebright = new Floor("skystonebright") {{
            variants = 4;
        }};
        vanstarockWall = new StaticWall("vnastarRock-wall") {{
            attributes.set(ExoAttribute.rocky, 3f);
            variants = 3;
        }};
        vanstarockWater = new Floor("vanstarock-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 7;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};

        deepVansterWater = new Floor("deep-vanster-water") {{
            speedMultiplier = 0.2f;
            variants = 0;
            liquidDrop = Liquids.water;
            liquidMultiplier = 1.5f;
            isLiquid = true;
            status = StatusEffects.wet;
            statusDuration = 120f;
            drownTime = 200f;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        vansterWater = new Floor("vanster-water") {{
            speedMultiplier = 0.5f;
            variants = 0;
            status = StatusEffects.wet;
            statusDuration = 90f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        shallowVansterWater = new Floor("shallow-vanster-water") {{
            speedMultiplier = 0.65f;
            variants = 0;
            status = StatusEffects.wet;
            liquidDrop = Liquids.water;
            statusDuration = 50f;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.9f;
        }};
        vansterSandyWater = new Floor("vanster-sandy-water") {{
            speedMultiplier = 0.8f;
            statusDuration = 50f;
            variants = 0;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};
        yellowIce = new Floor("yellow-ice") {{
            dragMultiplier = 0.35f;
            variants = 2;
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.4f);
            albedo = 0.65f;
        }};
        yellowGrass = new Floor("yellow-grass") {{
            variants = 4;
        }};
        purpleBoulder = new Prop("purple-boulder") {{
            variants = 2;
        }};

        //lightning stone biome
        redLightningStone = new Floor("red-lightning-stone") {{
            variants = 4;
        }};
        blueLightningStone = new Floor("lightning-stone-blue") {{
            variants = 4;
        }};

        lightningStonePurple = new Floor("lightning-stone-purple") {{
            variants = 8;
        }};
        lightningSlatePurple = new Floor("lightning-slate-purple") {{
            variants = 5;
        }};
        lightningSlateSmoothPurple = new Floor("lightning-slate-smooth-purple") {{
            variants = 4;
        }};
        lightningStoneCharged = new Floor("lightning-stone-charged") {{
            variants = 5;
        }};
        lightningStoneDim = new Floor("lightning-stone-dim") {{
            variants = 5;
        }};
        lightningStoneDimWater = new Floor("lightning-stone-dim-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 5;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};

        //marble biome
        marble = new Floor("marble") {{
            variants = 6;
        }};
        marbleWall = new StaticWall("marble-wall") {{
            variants = 3;
            marble.asFloor().wall = this;
        }};
        gildedMarble = new StaticWall("gilded-marble-wall") {{
            itemDrop = ExoItems.gold;
            variants = 3;
        }};
        phosleStone = new Floor("phosle-stone") {{
            variants = 4;
        }};
        turraka = new Floor("turraka") {{
            variants = 4;
        }};
        phosleStoneWater = new Floor("phosle-stone-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 4;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};
        turrakaWater = new Floor("turraka-water") {{
            speedMultiplier = 0.9f;
            statusDuration = 20f;
            variants = 4;
            overlayAlpha = 0.35f;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.2f;
        }};
        turrakaBoulder = new Prop("turraka-boulder") {{
            variants = 2;
        }};

        blackSand = new Floor("blacksand") {{
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        lightningStoneYellowPower = new Floor("lightning-stone-charged-power"){{
            attributes.set(ExoAttribute.power, 0.05f);
            blendGroup = lightningStoneCharged;
            variants = 5;
            emitLight = true;
            lightRadius = 30f;
            lightColor = ExoPal.empyrean.cpy().a(0.15f);
        }};
        lightningStoneBluePower = new Floor("lightning-stone-blue-power"){{
            attributes.set(ExoAttribute.power, 0.1f);
            blendGroup = blueLightningStone;
            variants = 5;
            emitLight = true;
            lightRadius = 30f;
            lightColor = ExoPal.empyreanblue.cpy().a(0.15f);
        }};
        lightningStoneRedPower = new Floor("red-lightning-stone-power"){{
            attributes.set(ExoAttribute.power, 0.15f);
            blendGroup = redLightningStone;
            variants = 4;
            emitLight = true;
            lightRadius = 30f;
            lightColor = ExoPal.cronusRed.cpy().a(0.15f);
        }};

        lightningStoneChargedWall = new StaticWall("lightning-stone-wall-charged") {{
            lightningStoneCharged.asFloor().wall = this;
        }};
        lightningStoneDimWall = new StaticWall("lightning-stone-wall-dim") {{
            lightningStoneDim.asFloor().wall = this;
        }};
        redLightningStoneWall = new StaticWall("red-lightning-stone-wall") {{
            redLightningStone.asFloor().wall = this;
        }};
        purpleLightningStoneWall = new StaticWall("lightning-stone-purple-wall") {{
            lightningStonePurple.asFloor().wall = this;
        }};
        blueLightningStoneWall = new StaticWall("blue-lightning-stone-wall") {{
            blueLightningStone.asFloor().wall = this;
        }};

        vanstarLargeTree = new TreeBlock("vanster-large-tree");
        vanstarStandardTree = new TreeBlock("vanster-standerd-tree");
        vanstarDeadTree = new TreeBlock("vanster-standerd-tree-dead");
        lightningCrystal = new ExoPowerProp("lightning-crystal") {{
            variants = 3;
            attributes.set(ExoAttribute.power, 1f);
            clipSize = 128f;
        }};
        lightningStoneCrystal = new TallBlock("lightning-stone-crystal") {{
            variants = 3;
            rotationRand = 40f;
            itemDrop = ExoItems.lightningStone;
            clipSize = 128f;
        }};
        luxiteCrystal = new TallBlock("luxite-stone-crystal") {{
            variants = 3;
            rotationRand = 40f;
            itemDrop = ExoItems.luxiteStone;
            clipSize = 128f;
        }};
        voilitCrystal = new TallBlock("violit-stone-crystal") {{
            variants = 3;
            rotationRand = 40f;
            itemDrop = ExoItems.ameythystGeode;
            clipSize = 128f;
        }};
        vousarCrystal = new TallBlock("vousar-crystal-blocks") {{
            variants = 3;
            rotationRand = 8f;
            itemDrop = ExoItems.vousarStone;
            clipSize = 128f;
        }};

        voltCrystalRed = new ExoPowerProp("volt-crystal-red") {{
            variants = 2;
            destructible = false;
            attributes.set(ExoAttribute.power, 3.5f);
            clipSize = 128f;
        }};
        voltCrystalBlue = new ExoPowerProp("volt-crystal-blue") {{
            variants = 2;
            destructible = false;
            attributes.set(ExoAttribute.power, 2f);
            clipSize = 128f;
        }};
        voltCrystalYellow = new ExoPowerProp("volt-crystal-yellow") {{
            variants = 2;
            destructible = false;
            attributes.set(ExoAttribute.power, 1f);
            clipSize = 128f;
        }};


        //Axin Tiles
        thenmialPlasmaAbyssal = new Floor("thenmial-plasma-abyssal") {{
            speedMultiplier = 0.2f;
            variants = 0;
            liquidDrop = ExoLiquids.coldPlasma;
            liquidMultiplier = 1.5f;
            isLiquid = true;
            status = StatusEffects.freezing;
            statusDuration = 120f;
            drownTime = 200f;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        thenmialPlasmaDeep = new Floor("thenmial-plasma-deep") {{
            speedMultiplier = 0.5f;
            variants = 0;
            status = StatusEffects.freezing;
            statusDuration = 90f;
            liquidDrop = ExoLiquids.coldPlasma;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        thenmialPlasma = new Floor("thenmial-plasma") {{
            speedMultiplier = 0.65f;
            status = StatusEffects.freezing;
            liquidDrop = ExoLiquids.coldPlasma;
            statusDuration = 50f;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            albedo = 0.9f;
        }};
        thenmialPlasmaShallow = new Floor("thenmial-plasma-shallow") {{
            speedMultiplier = 0.8f;
            statusDuration = 50f;
            liquidDrop = ExoLiquids.coldPlasma;
            isLiquid = true;
            albedo = 0.9f;
        }};
        axincarbonStone = new Floor("axincarbon-stone") {{
            variants = 6;
        }};
        axinCyanSlate = new Floor("axinCyan-slate") {{
            variants = 3;
        }};
        axinSlate = new Floor("axin1-slate") {{
            variants = 3;
            blendGroup = axinCyanSlate;
        }};
        axinCarvakStone = new Floor("axincarvak-stone") {{
            variants = 5;
        }};
        axinCarvakStoneWall = new StaticWall("axincarvak-stone-wall") {{
            axinCarvakStone.asFloor().wall = this;
        }};
        axinCrystalStone = new Floor("axin-crystalStone") {{
            variants = 7;
        }};
        axinCrystalRock = new Floor("axincrystalrock") {{
            variants = 6;
        }};
        axinCrystalRock1 = new Floor("axin-crystalrock") {{
            variants = 6;
        }};
        axinCrystalStoneWall = new StaticWall("axin-crystalStone-wall") {{
            axinCrystalStone.asFloor().wall = this;
        }};

        axinStone = new Floor("axin-stone") {{
            variants = 6;
        }};
        axinRock = new Floor("axin-rock") {{
            variants = 5;
        }};
        axinSlate2 = new Floor("axin-slate") {{
            variants = 9;
        }};
        axinStoneWall = new StaticWall("axin-stone-wall") {{
            variants = 2;
            axinStone.asFloor().wall = this;
        }};
        axinStoneMinerals = new Floor("axin-stoneMinerals") {{
            variants = 6;
            blendGroup = axinStone;
        }};

        axinPurpleStone = new Floor("axinpurple-stone") {{
            variants = 6;
        }};
        axinPurpleWall = new StaticWall("axin-purple-wall") {{
            axinPurpleStone.asFloor().wall = this;
        }};
        axinPurpleSlate = new Floor("axinpurple-slate") {{
            variants = 3;
        }};
        axinPurpleRock = new Floor("axinpurple-rock") {{
            variants = 5;
        }};
        axinPurpleStoneMineral = new Floor("axinpurpleMineral-stone") {{
            variants = 6;
            blendGroup = axinPurpleStone;
        }};

        axinCrystalTile = new Floor("axin-crystaltile") {{
            variants = 4;
        }};
        axinCrystalBlue = new Floor("axin-crystalblue") {{
            variants = 4;
        }};
        axinCrystalPurple = new Floor("axin-crystalPurple") {{
            variants = 4;
        }};
        thermakronxCrystal = new Floor("Thermakronx-crystal"){{
            itemDrop = ExoItems.thermkronxite;
            variants = 0;
            playerUnmineable = true;
        }};

        diamondTile = new Floor("axin-diamondTile") {{
            variants = 4;
        }};
        diamondWall = new StaticWall("axin-diamond-wall") {{
            diamondTile.asFloor().wall = this;
        }};
        diamondGrowth = new TallBlock("axin-diamond-growths") {{
            clipSize = 88f;
            itemDrop = ExoItems.axinDiamond;
            variants = 3;
        }};
        alignPlating = new StaticWall("align-plating") {{
            variants = 14;
        }};
        axinIce = new Floor("axin-ice") {{
            dragMultiplier = 0.4f;
            speedMultiplier = 0.9f;
            variants = 3;
        }};
        poolAxinPlasma = new Floor("pooled-axinPlasma") {{
            drownTime = 150f;
            status = StatusEffects.freezing;
            statusDuration = 240f;
            speedMultiplier = 0.5f;
            variants = 0;
            liquidDrop = ExoLiquids.axinian;
            liquidMultiplier = 0.5f;
            isLiquid = true;
            cacheLayer = CacheLayer.water;

            emitLight = true;
            lightRadius = 25f;
            lightColor = ExoPal.genesisDark.cpy().a(0.19f);
        }};
        axinCrystal = new Prop("axin-crystal") {{
            variants = 2;
        }};
        axinCrystalRockBoulder = new Prop("axincrystalrock-boulder") {{
            variants = 6;
            axinCrystalStone.asFloor().decoration = axinStoneMinerals.asFloor().decoration = this;
        }};
        axinBoulder = new Prop("axin-boulder") {{
            variants = 6;
            axinStone.asFloor().decoration = axinRock.asFloor().decoration = axinSlate2.asFloor().decoration = this;
        }};
        colossalAxinMonolith = new TallBlock("colossal-AxinMonolith") {{
            clipSize = 228f;
            layer = 77;
            shadowOffset = -25f;
        }};
        largeAxinMonolith = new TallBlock("large-AxinMonolith") {{
            clipSize = 228f;
            layer = 76;
            shadowOffset = -25f;
        }};
        mediumAxinMonolith = new TallBlock("medium-AxinMonolith") {{
            clipSize = 148f;
            shadowOffset = -13f;
        }};
        smallAxinMonolith = new TallBlock("small-AxinMonolith") {{
            clipSize = 88f;
        }};
    }
}
