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
    Color c1 = Color.valueOf("5057a6");
    Color c2 = Color.valueOf("272766");
    Block[][] arr;

    public TauTiamasPlanetGenerator() {
        this.arr = new Block[][]{{Blocks.redmat, Blocks.redmat, Blocks.darksand, Blocks.bluemat, Blocks.bluemat}};
        this.baseSeed = 1;
    }

    public float getHeight(Vec3 position) {
        return 0.0F;
    }

    public void getColor(Vec3 position, Color out) {
        float depth = Simplex.noise3d(this.seed, 2.0, 0.56, 1.7000000476837158, (double)position.x, (double)position.y, (double)position.z) / 2.0F;
        out.set(this.c1).lerp(this.c2, Mathf.clamp(Mathf.round(depth, 0.15F))).a(0.8F).toFloatBits();
    }

    public float getSizeScl() {
        return 2000.0F;
    }

    public void addWeather(Sector sector, Rules rules) {
    }

    public void genTile(Vec3 position, TileGen tile) {
        tile.floor = this.getBlock(position);
        if (tile.floor == Blocks.redmat && this.rand.chance(0.1)) {
            tile.block = Blocks.redweed;
        }

        if (tile.floor == Blocks.bluemat && this.rand.chance(0.03)) {
            tile.block = Blocks.purbush;
        }

        if (tile.floor == Blocks.bluemat && this.rand.chance(0.002)) {
            tile.block = Blocks.yellowCoral;
        }

    }

    protected void generate() {
        this.pass((x, y) -> {
            float max = 0.0F;
            Point2[] var4 = Geometry.d8;
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Point2 p = var4[var6];
                max = Math.max(max, Vars.world.getDarkness(x + p.x, y + p.y));
            }

            if (max > 0.0F) {
                this.block = this.floor.asFloor().wall;
            }

            if ((double)this.noise((float)x, (float)y, 40.0, 1.0) > 0.9) {
            }

        });
        Schematics.placeLaunchLoadout(this.width / 2, this.height / 2);
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(this.seed, 8.0, 0.699999988079071, 1.0, (double)position.x, (double)position.y, (double)position.z);
    }

    Block getBlock(Vec3 position) {
        float height = this.rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(2.0F);
        float temp = Simplex.noise3d(this.seed, 8.0, 0.6, 0.5, (double)position.x, (double)(position.y + 99.0F), (double)position.z);
        height *= 1.2F;
        height = Mathf.clamp(height);
        return this.arr[Mathf.clamp((int)(temp * (float)this.arr.length), 0, this.arr[0].length - 1)][Mathf.clamp((int)(height * (float)this.arr[0].length), 0, this.arr[0].length - 1)];
    }
}
