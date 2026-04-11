package exogenesis.type.planet;

import arc.*;
import exogenesis.type.BetterPlanet;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class ExoPlanet extends BetterPlanet {
    public boolean loadIcon = true;

    public ExoPlanet(String name, Planet parent, float radius, int sectorSize){
        super(name, parent, radius, sectorSize);
    }

    @Override
    public void createIcons(MultiPacker packer){
        if(loadIcon){
            Icon.icons.put(name, Core.atlas.getDrawable(name));
            icon = name;
        }
    }
}
