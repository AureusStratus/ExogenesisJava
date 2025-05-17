package exogenesis.content;

import arc.struct.ObjectFloatMap;
import exogenesis.type.DamageType;
import arc.struct.ObjectMap;
import mindustry.type.UnitType;

import static exogenesis.content.ExoDamageTypes.*;
import static mindustry.Vars.content;
import static mindustry.content.UnitTypes.*;

public class ExoUnitTypeResistances {
    //resistance, unit type - resistances map.
    //0.1 resistance means deal 0.9x damage, -0.1 means deal 1.1x damage.
    public static ObjectMap<UnitType, ObjectFloatMap<DamageType>> resistancesMap = new ObjectMap<>(content.units().size);

    public static void load(){

        //assault
        applyResistance(dagger, kinetic, 0.2f, explosive, 0.2f, pierce, 0.1f);
        applyResistance(mace, kinetic, 0.2f, thermal, -0.2f, radiation, 0.1f);
        applyResistance(fortress, kinetic, 0.4f, explosive, 0.2f, cryogenic, -0.1f);
        applyResistance(scepter, kinetic, 0.6f, cryogenic, 0.2f, pierce, 0.1f);
        applyResistance(reign, kinetic, 0.8f, energy, -0.5f, pierce, 0.1f);

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
        ObjectFloatMap<DamageType> resistances = resistancesMap.get(unit, new ObjectFloatMap<>());
        resistances.put(type, resistance);
        resistancesMap.put(unit, resistances);
    }

    public static float getResistance(UnitType unit, DamageType damageType){
        return resistancesMap.get(unit, new ObjectFloatMap<>()).get(damageType, 0f);
    }
}
