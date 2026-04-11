package exogenesis.maps.planets;

import exogenesis.maps.ColorPass;
import exogenesis.maps.HeightPass;
import arc.graphics.*;
import arc.math.geom.*;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Loadouts;
import mindustry.game.Schematics;
import mindustry.game.Team;
import mindustry.maps.generators.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class AxinPlanetGenerator extends BlankPlanetGenerator {
    @Override
    protected void generate() {
        pass((x, y) -> {
            floor = Blocks.grass;
            block = ore = Blocks.air;
        });

        Schematics.place(Loadouts.basicShard, width / 2, height / 2, Team.sharded);
    }
}