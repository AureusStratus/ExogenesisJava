package exogenesis.type.unit;

import exogenesis.graphics.ExoPal;
import mindustry.type.UnitType;
import mindustry.world.meta.Env;

public class HadroxUnitType extends UnitType {

    public HadroxUnitType(String name) {
        super(name);
        outlineColor = ExoPal.geoComplexOutline;
        envDisabled = Env.space;
        outlineRadius = 4;
        researchCostMultiplier = 6f;
    }
}