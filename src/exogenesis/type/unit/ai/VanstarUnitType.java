package exogenesis.type.unit.ai;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import exogenesis.world.meta.ExoEnv;
import mindustry.type.*;
import mindustry.type.ammo.*;

public class VanstarUnitType extends UnitType {

    public VanstarUnitType(String name) {
        super(name);
        outlineColor = ExoPal.empyreanOutline;
        envDisabled = ExoEnv.stormWorld;
        outlineRadius = 4;
        ammoType = new ItemAmmoType(ExoItems.oltuxium);
        researchCostMultiplier = 10f;
    }
}