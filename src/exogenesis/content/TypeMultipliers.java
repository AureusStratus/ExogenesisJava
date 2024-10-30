package exogenesis.content;

import exogenesis.type.DamageType;
import exogenesis.type.unit.TypeMultiplierUnitType;
import exogenesis.world.meta.ExoStats;
import arc.struct.ObjectMap;
import arc.util.Structs;
import mindustry.Vars;
import mindustry.type.UnitType;

import static mindustry.content.UnitTypes.*;

public class TypeMultipliers{
    public static ObjectMap<UnitType, float[]> map = new ObjectMap<>(32);

    public static void load(){
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

    public static void addMultipliers(String name, float kinetic, float explosive, float pierce, float energy, float thermal, float cryogenic, float radiation){
        if(Vars.content.unit(name) != null){
            map.put(Vars.content.unit(name), new float[]{kinetic, explosive, pierce, energy, thermal, cryogenic, radiation});
        }
    }

    public static void addMultipliers(UnitType unit, float kinetic, float explosive, float pierce, float energy, float thermal, float cryogenic, float radiation){
        map.put(unit, new float[]{kinetic, explosive, pierce, energy, thermal, cryogenic, radiation});
    }

    public static float getMultiplier(UnitType unit, DamageType type){
        int index = Structs.indexOf(DamageType.values(), type);

        if(unit instanceof TypeMultiplierUnitType u){
            return u.multipliers()[index];
        }else if(map.containsKey(unit)){
            return map.get(unit)[index];
        }

        return 1f;
    }

    public static float[] getMultipliers(UnitType unit){
        if(unit instanceof TypeMultiplierUnitType u){
            return u.multipliers();
        }else if(map.containsKey(unit)){
            return map.get(unit);
        }

        return null;
    }
}
