package exogenesis;

import arc.Events;
import arc.math.Mathf;
import arc.util.Log;
import exogenesis.entities.EntityRegister;
import exogenesis.graphics.ExoShaders;
import exogenesis.type.bullet.TypedBulletType;
import exogenesis.util.func.DrawFunc;
import exogenesis.world.ExoTeams;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BulletType;
import mindustry.game.EventType;
import exogenesis.util.util.Utils;
import exogenesis.content.ExoBlocks;
import exogenesis.content.ExoVanstarBlocks;
import exogenesis.content.ExoUnitTypes;
import exogenesis.content.ExoStatusEffects;
import exogenesis.content.*;
import mindustry.gen.Unit;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
//import exogenesis.gen.*;

import static arc.Core.app;

public class ExogenesisMod extends Mod{
    public static Mods.LoadedMod MOD;
    public static final boolean DEBUG = true;

    public ExogenesisMod(){
        Events.on(EventType.FileTreeInitEvent.class, e -> app.post(ExoShaders::load));

        Events.on(EventType.DisposeEvent.class, e -> ExoShaders.dispose());

        Events.on(EventType.ContentInitEvent.class, e -> {
            ExoPostProcess.load();
        });

        if (DEBUG){
            //also one thing: splash damage not apply to typed damage properly, this is a todo
            Events.on(EventType.UnitDamageEvent.class, event -> {
                BulletType type = event.bullet.type();
                Unit unit = event.unit;
                float damage = type.damage;
                if (type instanceof TypedBulletType typedBulletType) damage = typedBulletType.getTotalDamageToUnit(type.damage, unit);
                float finalDamage = Mathf.round(damage);
                Effect effect = new Effect(30, e -> DrawFunc.drawText("<" + finalDamage + ">", unit.x, unit.y));
                effect.at(unit);
            });
        }
    }

    @Override
    public void loadContent(){
        //EntityRegistry.register();
        EntityRegister.load();
        Utils.init();
        ExoDamageTypes.load();
        ExoTeams.load();
        ExoStatusEffects.load();
        ExoSounds.load();
        ExoWeathers.load();
        ExoAttribute.load();
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
        ExoVanillaOverride.load();
    }
}
