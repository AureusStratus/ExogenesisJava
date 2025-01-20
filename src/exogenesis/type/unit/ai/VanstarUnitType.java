package exogenesis.type.unit.ai;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import exogenesis.world.meta.ExoEnv;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.Env;

public class VanstarUnitType extends UnitType {

    public VanstarUnitType(String name) {
        super(name);
        outlineColor = ExoPal.empyreanOutline;
        envDisabled = Env.space;
        outlineRadius = 4;
        ammoType = new ItemAmmoType(ExoItems.oltuxium);
        researchCostMultiplier = 10f;
    }
}