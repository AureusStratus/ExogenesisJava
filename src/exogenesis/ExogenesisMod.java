package exogenesis;

import arc.Events;
import exogenesis.graphics.ExoShaders;
import mindustry.game.EventType;
import mindustry.mod.*;
import exogenesis.util.util.Utils;
import exogenesis.content.ExoBlocks;
import exogenesis.content.ExoVanstarBlocks;
import exogenesis.content.ExoUnitTypes;
import exogenesis.content.ExoStatusEffects;
import exogenesis.content.*;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import exogenesis.gen.*;

import static arc.Core.app;

public class
ExogenesisMod extends Mod{
    public static Mods.LoadedMod modInfo;

    public ExogenesisMod(){
        super();

        Events.on(EventType.FileTreeInitEvent.class, e ->
                app.post(ExoShaders::load)
        );

        Events.on(EventType.DisposeEvent.class, e ->
                ExoShaders.dispose()
        );

    }
    @Override
    public void loadContent(){
        EntityRegistry.register();
        Utils.init();
        ExoStatusEffects.load();
        ExoWeathers.load();
        ExoAttribute.load();
        ExoSounds.load();
        ExoUnitTypes.load();
        ExoVanillaUnitTypes.load();
        ExoLiquids.load();
        ExoItems.load();
        ExoEnvironmentBlocks.load();
        ExoBlocks.load();
        ExoVanstarBlocks.load();
        TypeMultipliers.load();
        ExoPlanets.load();
        ExoVanstarTechTree.load();
    }
}
