package exogenesis.maps.planets;


import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.Seq;
import arc.util.*;
import arc.util.noise.*;
import exogenesis.content.ExoEnvironmentBlocks;
import exogenesis.maps.ColorPass;
import exogenesis.maps.HeightPass;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class YlanMoonGenerator extends PlanetGenerator{
    Color c1 = Color.valueOf("5057a6"), c2 = Color.valueOf("272766");
    public YlanMoonGenerator() { baseSeed = 12345; }

    @Override
    public void getColor(Vec3 pos, Color out){
        float depth = Simplex.noise3d(seed, 8, 0.56, 1.7f, pos.x, pos.y, pos.z) / 2f;
        out.set(c1).lerp(c2, Mathf.clamp(Mathf.round(depth, 0.15f))).a(1f - 0.2f).toFloatBits();
    }
}
