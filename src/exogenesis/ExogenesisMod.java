package exogenesis;

import arc.Events;
import arc.math.Mathf;
import arc.util.Log;
import exogenesis.entities.EntityRegister;
import exogenesis.graphics.ExoShaders;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.bullet.vanilla.ExoContinuousFlameBulletType;
import exogenesis.type.bullet.vanilla.ExoContinuousLaserBulletType;
import exogenesis.type.bullet.vanilla.ExoFlakBulletType;
import exogenesis.type.bullet.vanilla.ExoLaserBulletType;
import exogenesis.util.func.DrawFunc;
import exogenesis.world.ExoTeams;
import mindustry.content.Blocks;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BasicBulletType;
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
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
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

        //for debug use, test for display. can remove safely
        if (DEBUG){
            //test for PowerTurret display (lancer)
            BulletType laser = new ExoLaserBulletType();
            laser.damage = 500f;
            ((TypedBulletType) laser).addDamageMultiplier(ExoDamageTypes.kinetic, 1.2f, ExoDamageTypes.energy, 0.8f, ExoDamageTypes.graviton, 0.5f);
            ((PowerTurret)Blocks.lancer).shootType = laser;

            //test for ContinuousLiquidTurret display (sublimate)
            BulletType flame = new ExoContinuousFlameBulletType();
            flame.damage = 1000f / 12f;
            ((TypedBulletType) flame).addDamageMultiplier(ExoDamageTypes.kinetic, 2f, ExoDamageTypes.energy, 0.3f, ExoDamageTypes.thermal, 5f);
            ((ContinuousLiquidTurret)Blocks.sublimate).ammoTypes.put(Liquids.cyanogen, flame);

            //test for ContinuousTurret display (meltdown)
            BulletType beam = new ExoContinuousLaserBulletType();
            beam.damage = 5000f / 12f;
            ((TypedBulletType) beam).addDamageMultiplier(ExoDamageTypes.energy, 2f, ExoDamageTypes.cryogenic, 4f);
            ((LaserTurret)Blocks.meltdown).shootType = beam;

            //test for Unit Display (gamma)
            BulletType bullet = new ExoFlakBulletType();
            bullet.damage = 200;
            ((TypedBulletType) bullet).addDamageMultiplier(ExoDamageTypes.explosive, 5f, ExoDamageTypes.pierce, 2.5f);
            UnitTypes.gamma.weapons.each(weapon -> weapon.bullet = bullet);
        }
    }
}
