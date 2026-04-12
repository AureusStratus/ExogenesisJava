package exogenesis.maps.planets;


import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import exogenesis.content.ExoEnvironmentBlocks;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class TauTiamasPlanetGenerator extends PlanetGenerator{

    public TauTiamasPlanetGenerator() {
        baseSeed = 12345;
    }

    public Color makeColor(Vec3 position) {
        float t = (Simplex.noise3d(
                12, 5, 1,
                Mathf.absin(1+Time.globalTime / 10f,1,  1f),
                position.x, position.y, position.z
        ));

        return Color.valueOf("6d7cdf").lerp(Color.valueOf("6058ba"), t);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        out.set(makeColor(position));
    }
}