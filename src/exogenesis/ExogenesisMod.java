package exogenesis;

import arc.Events;
import exogenesis.entities.EntityRegister;
import exogenesis.graphics.ExoShaders;
import exogenesis.world.ExoTeams;
import mindustry.game.EventType;
import exogenesis.util.util.Utils;
import exogenesis.content.ExoBlocks;
import exogenesis.content.ExoVanstarBlocks;
import exogenesis.content.ExoUnitTypes;
import exogenesis.content.ExoStatusEffects;
import exogenesis.content.*;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
//import exogenesis.gen.*;

import static arc.Core.app;

public class ExogenesisMod extends Mod{
    public static Mods.LoadedMod MOD;

    public ExogenesisMod(){
        Events.on(EventType.FileTreeInitEvent.class, e -> app.post(ExoShaders::load));

        Events.on(EventType.DisposeEvent.class, e -> ExoShaders.dispose());

        Events.on(EventType.ContentInitEvent.class, e -> {
            ExoPostProcess.load();
        });
    }

    @Override
    public void loadContent(){
        //EntityRegistry.register();
        EntityRegister.load();
        Utils.init();
        ExoDamageTypes.load();
        ExoTeams.load();
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
        ExoUnitTypeResistances.load();
        ExoPlanets.load();
        ExoSectorPresets.load();
        ExoVanstarTechTree.load();
    }
}
