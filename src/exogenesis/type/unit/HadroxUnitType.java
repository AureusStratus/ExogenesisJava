package exogenesis.type.unit;

import exogenesis.content.ExoItems;
import exogenesis.graphics.ExoPal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.Env;

public class HadroxUnitType extends UnitType {

    public HadroxUnitType(String name) {
        super(name);
        outlineColor = ExoPal.geoComplexOutline;
        envDisabled = Env.space;
        outlineRadius = 4;
        ammoType = new ItemAmmoType(ExoItems.volcanite);
        researchCostMultiplier = 6f;
    }
}