package exogenesis.type.unit;

import mindustry.type.unit.ErekirUnitType;

public class ExoErekirUnitType extends ErekirUnitType implements TypeMultiplierUnitType{
    public float[] multipliers;

    public ExoErekirUnitType(String name, float kinetic, float explosive, float pierce, float energy, float thermal, float cryogenic, float radiation){
        super(name);
        multipliers = new float[]{kinetic, explosive, pierce, energy, thermal, cryogenic, radiation};
    }

    @Override
    public float[] multipliers(){
        return multipliers;
    }
}
