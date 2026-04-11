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

public class YlanMoonGenerator extends BlankPlanetGenerator {
    @Override
    protected void generate() {
        pass((x, y) -> {
            floor = Blocks.grass;
            block = ore = Blocks.air;
        });

        Schematics.place(Loadouts.basicShard, width / 2, height / 2, Team.sharded);
    }
}
