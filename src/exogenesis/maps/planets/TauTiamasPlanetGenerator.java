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
        Color c1 = Color.valueOf("5057a6"), c2 = Color.valueOf("272766");
        public TauTiamasPlanetGenerator() { baseSeed = 12345; }

        @Override
        public void getColor(Vec3 pos, Color out){
            float depth = Simplex.noise3d(seed, 2, 0.56, 1.7f, pos.x, pos.y, pos.z) / 2f;
            out.set(c1).lerp(c2, Mathf.clamp(Mathf.round(depth, 0.15f))).a(1f - 0.2f).toFloatBits();
        }
    }