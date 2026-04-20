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

import static arc.util.Tmp.c1;
import static arc.util.Tmp.c2;
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
    public void getColor(Vec3 position, Color out){
        float depth = Simplex.noise3d(seed, 2, 0.56, 1.7f, position.x, position.y, position.z) / 2f;
        out.set(c1).lerp(c2, Mathf.clamp(Mathf.round(depth, 0.15f))).a(1f - 0.2f).toFloatBits();
    }
}