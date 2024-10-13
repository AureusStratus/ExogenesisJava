package exogenesis.type.unit;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import exogenesis.world.meta.ExoEnv;
import mindustry.type.*;
import mindustry.type.ammo.*;

/** Config class for special Erekir unit properties. */
public class AxinUnitType extends UnitType{

    public AxinUnitType(String name){
        super(name);
        outlineColor = ExoPal.genesisOutline;
        envDisabled = ExoEnv.freezing;
        outlineRadius = 4;
        ammoType = new ItemAmmoType(ExoItems.curtuses);
        researchCostMultiplier = 10f;
    }
}