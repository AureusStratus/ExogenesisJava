package exogenesis.type.unit;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import mindustry.type.*;
import mindustry.world.meta.Env;

public class VanstarUnitType extends UnitType {

    public VanstarUnitType(String name) {
        super(name);
        outlineColor = ExoPal.empyreanOutline;
        envDisabled = Env.space;
        outlineRadius = 4;
        researchCostMultiplier = 10f;
    }
}