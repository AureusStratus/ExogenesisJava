package exogenesis.type.unit;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import exogenesis.world.meta.ExoEnv;
import mindustry.type.*;

/** Config class for special Erekir unit properties. */
public class AxinUnitType extends UnitType{

    public AxinUnitType(String name){
        super(name);
        outlineColor = ExoPal.genesisOutline;
        envDisabled = ExoEnv.freezing;
        outlineRadius = 4;
        researchCostMultiplier = 10f;
    }
}