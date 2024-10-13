package exogenesis.type.unit;

import mindustry.type.UnitType;

//technically not necessary but it keeps the multiplier map smaller to have this
public class ExoUnitType extends UnitType implements TypeMultiplierUnitType{
    public float[] multipliers;

    public ExoUnitType(String name, float kinetic, float explosive, float pierce, float energy, float thermal, float cryogenic, float radiation){
        super(name);
        multipliers = new float[]{kinetic, explosive, pierce, energy, thermal, cryogenic, radiation};
    }


    @Override
    public float[] multipliers(){
        return multipliers;
    }
}
