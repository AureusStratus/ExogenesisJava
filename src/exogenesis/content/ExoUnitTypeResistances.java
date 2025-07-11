package exogenesis.content;

import arc.struct.ObjectFloatMap;
import arc.struct.OrderedMap;
import exogenesis.type.DamageType;
import arc.struct.ObjectMap;
import mindustry.type.UnitType;

import static exogenesis.content.ExoDamageTypes.*;
import static mindustry.Vars.content;
import static mindustry.content.UnitTypes.*;

public class ExoUnitTypeResistances {
    //resistance, unit type - resistances map.
    //0.1 resistance means deal 0.9x damage, -0.1 means deal 1.1x damage.
    public static OrderedMap<UnitType, OrderedMap<DamageType, Float>> resistancesMap = new OrderedMap<>(content.units().size);

    public static void load(){

        //assault
        applyResistance(dagger, kinetic, 0.2f, energy, -0.2f, explosive, 0.2f, pierce, -0.2);
        applyResistance(mace, kinetic, 0.2f, energy, -0.2f, explosive, 0.2f, pierce, -0.2);
        applyResistance(fortress, kinetic, 0.4f, energy, -0.3f, explosive, 0.3, pierce, -0.3);
        applyResistance(scepter, kinetic, 0.6f, energy, -0.4f, explosive, 0.4f, pierce, -0.4);
        applyResistance(reign, kinetic, 0.8f, energy, -0.5f, explosive, 0.5f, pierce, -0.5);

        applyResistance(flare, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        applyResistance(horizon, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        applyResistance(zenith, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        applyResistance(antumbra, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        applyResistance(eclipse, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        // Vanstar units
        applyResistance(ExoUnitTypes.soul, energy, 0.2f, kinetic, 0.2f, pierce, -0.15f);
        applyResistance(ExoUnitTypes.pneuma, energy, 0.2f, kinetic, 0.2f, pierce, -0.15f);
        applyResistance(ExoUnitTypes.psyche, energy, 0.2f, kinetic, 0.3f, pierce, -0.25f);
        applyResistance(ExoUnitTypes.myalo, energy, 0.2f, kinetic, 0.4f, pierce, -0.35f);
        applyResistance(ExoUnitTypes.acheron, energy, 0.2f, kinetic, 0.5f, pierce, -0.35f);

        applyResistance(ExoUnitTypes.lux, energy, -0.15f, kinetic, 0.2f, pierce, 0.2f);
        applyResistance(ExoUnitTypes.glimmer, energy, -0.15f, kinetic, 0.2f, pierce, 0.2f);
        applyResistance(ExoUnitTypes.shine, energy, -0.25f, kinetic, 0.3f, pierce, 0.2f);
        applyResistance(ExoUnitTypes.auric, energy, -0.35f, kinetic, 0.4f, pierce, 0.2f);
        applyResistance(ExoUnitTypes.radiance, energy, -0.35f, kinetic, 0.5f, pierce, 0.2f);

        applyResistance(ExoUnitTypes.prayer, kinetic, -0.15f, explosive, 0.2f, energy, 0.2f);
        applyResistance(ExoUnitTypes.apprise, kinetic, -0.15f, explosive, 0.2f, energy, 0.2f);
        applyResistance(ExoUnitTypes.revelation, kinetic, -0.25f, explosive, 0.3f, energy, 0.2f);
        applyResistance(ExoUnitTypes.enlightenment, kinetic, -0.35f, explosive, 0.4f, energy, 0.2f);
        applyResistance(ExoUnitTypes.excelsus, kinetic, -0.35f, explosive, 0.5f, energy, 0.2f);

        applyResistance(ExoUnitTypes.flicker, explosive, -0.15f, thermal, 0.2f, energy, 0.2f);
        applyResistance(ExoUnitTypes.ember, explosive, -0.15f, thermal, 0.2f, energy, 0.2f);
        applyResistance(ExoUnitTypes.blaze, explosive, -0.25f, thermal, 0.3f, energy, 0.2f);
        applyResistance(ExoUnitTypes.pyric, explosive, -0.35f, thermal, 0.4f, energy, 0.2f);
        applyResistance(ExoUnitTypes.phlogiston, explosive, -0.35f, thermal, 0.5f, energy, 0.2f);
        /*
        //assault
        addMultipliers(dagger, 0.8f, 0.8f, 1.1f, 1f, 1.2f, 1f, 1f);
        addMultipliers(mace, 0.8f, 1.2f, 1.2f, 1f, 0.5f, 1.5f, 1f);
        addMultipliers(fortress, 0.6f, 0.2f, 1.2f, 1.3f, 1.3f, 1f, 1f);
        addMultipliers(scepter, 0.5f, 0.8f, 1.3f, 1f, 1.2f, 1f, 1f);
        addMultipliers(reign, 0.2f, 0.8f, 1.5f, 1f, 1.2f, 1f, 1f);
        //assault air
        addMultipliers(flare, 0.9f, 1.1f, 1f, 1f, 1f, 1f, 1f);
        addMultipliers(horizon, 0.9f, 0.9f, 1.1f, 1f, 1f, 1f, 1f);
        addMultipliers(zenith, 0.9f, 1f, 1.2f, 1.3f, 1.3f, 1f, 1f);
        addMultipliers(antumbra, 0.6f, 0.8f, 1.3f, 1f, 1f, 1f, 1f);
        addMultipliers(eclipse, 0.2f, 0.8f, 1.5f, 1f, 1f, 1f, 1f);
        //assault boat
        addMultipliers(risso, 0.9f, 1f, 1f, 1f, 1f, 1f, 1f);
        addMultipliers(minke, 0.9f, 1f, 1.1f, 1f, 1f, 1f, 1f);
        addMultipliers(bryde, 0.9f, 1f, 1.2f, 1f, 1f, 1f, 1f);
        addMultipliers(sei, 0.9f, 1f, 1.3f, 1f, 1f, 1f, 1f);
        addMultipliers(omura, 0.2f, 1f, 0.2f, 1f, 1f, 1f, 1f);
        //erekir tank
        addMultipliers(stell, 0.8f, 1f, 0.5f, 1.3f, 1f, 1f, 1f);
        addMultipliers(locus, 0.8f, 1f, 0.5f, 1.3f, 1f, 1f, 1f);
        addMultipliers(precept, 0.6f, 1f, 0.5f, 1.3f, 1f, 1f, 1f);
        addMultipliers(vanquish, 0.6f, 1f, 0.5f, 1.3f, 1f, 1f, 1f);
        addMultipliers(conquer, 0.4f, 1f, 0.5f, 1.3f, 1f, 1f, 1f);
        //specialist
        addMultipliers(crawler, 1f, 0.1f, 1f, 1f, 1f, 1f, 1f);
        addMultipliers(atrax, 1f, 1f, 1f, 1f, 0.4f, 1.2f, 0.9f);
        addMultipliers(spiroct, 1f, 1f, 1f, 1f, 1f, 1.3f, 0.8f);
        addMultipliers(arkyid, 1f, 1f, 1f, 1f, 1f, 1.3f, 0.7f);
        addMultipliers(toxopid, 0.8f, 1f, 1.2f, 1f, 1.5f, 1.5f, 0.5f);
        //erekir air
        addMultipliers(elude, 1f, 0.6f, 1f, 1.2f, 1f, 1f, 0.4f);
        addMultipliers(avert, 1f, 0.6f, 1f, 1.2f, 1f, 1f, 0.4f);
        addMultipliers(obviate, 1f, 0.6f, 1f, 1.3f, 1f, 1f, 0.4f);
        addMultipliers(quell, 1f, 0.6f, 1f, 1.3f, 1f, 1f, 0.4f);
        addMultipliers(disrupt, 1f, 0.6f, 1f, 1.5f, 1f, 1f, 0.4f);
        //support
        //Support mech
        addMultipliers(nova, 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 1.4f);
        addMultipliers(pulsar, 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 1.4f);
        addMultipliers(quasar, 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 1.4f);
        addMultipliers(vela, 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 1.4f);
        addMultipliers(corvus, 1.2f, 1f, 1f, 0.25f, 0.8f, 1f, 1.4f);
        //support air
        addMultipliers(mono, 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f);
        addMultipliers(poly, 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f);
        addMultipliers(mega, 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f);
        addMultipliers(quad, 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f);
        addMultipliers(oct, 1.2f, 1f, 1f, 0.5f, 1f, 1f, 1.4f);
        //support boat
        addMultipliers(retusa, 0.8f, 1f, 1f, 0.35f, 1.1f, 1f, 1.2f);
        addMultipliers(oxynoe, 0.8f, 1f, 1f, 0.35f, 1.1f, 1f, 1.2f);
        addMultipliers(cyerce, 0.8f, 1f, 1f, 0.35f, 1.1f, 1f, 1.2f);
        addMultipliers(aegires, 0.7f, 1f, 1f, 0.35f, 1.1f, 1f, 1.2f);
        addMultipliers(navanax, 0.5f, 1f, 1f, 0.35f, 1.1f, 1f, 1.2f);
        //erekir support mech
        addMultipliers(merui, 1f, 1.3f, 1f, 0.2f, 1f, 1f, 1.2f);
        addMultipliers(cleroi, 1f, 1.3f, 1f, 0.2f, 1f, 1f, 1.2f);
        addMultipliers(anthicus, 0.9f, 1.3f, 1f, 0.2f, 1f, 1f, 1.2f);
        addMultipliers(tecta, 0.8f, 1.3f, 1f, 0.2f, 1f, 1f, 1.2f);
        addMultipliers(collaris, 0.7f, 1.3f, 1f, 0.2f, 1f, 1f, 1.2f);
        ExoStats.addTypeStatsUnit();
        */
    }

    public static void applyResistance(String name, DamageType type, float resistance){
        applyResistance(content.unit(name), type, resistance);
    }

    public static void applyResistance(Object...objects){
        if (objects.length < 3) return;
        UnitType unitType = objects[0] instanceof UnitType type? type : objects[0] instanceof String name? content.unit(name) : null;
        for (int i = 1; i < objects.length; i += 2){
            if (objects[i] instanceof DamageType damageType && objects[i + 1] instanceof Float resistance){
                applyResistance(unitType, damageType, resistance);
            }
        }
    }

    public static void applyResistance(UnitType unit, DamageType type, float resistance){
        if (unit == null) return;
        OrderedMap<DamageType, Float> resistances = resistancesMap.get(unit, new OrderedMap<>());
        resistances.put(type, resistance);
        resistancesMap.put(unit, resistances);
    }

    public static float getResistance(UnitType unit, DamageType damageType){
        return resistancesMap.get(unit, new OrderedMap<>()).get(damageType, 0f);
    }
}
