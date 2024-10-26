package exogenesis.world.blocks;

import arc.math.geom.Geometry;
import exogenesis.content.ExoAttribute;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.world.Tile;

import static mindustry.Vars.tilesize;

public class PowerHarvester extends Block {
    public int range;
    public float powerProduction = 1;

    public PowerHarvester(String name) {
        super(name);
        this.hasPower = true;
        this.outputsPower = true;
        this.consumesPower = false;
        this.update = true;
        this.solid = true;
        this.destructible = true;
        this.range = 50;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
    }
        static float returnCount = 0;
        static float countCrystal(Tile tile, float range){
            Geometry.circle(tile.x, tile.y, (int)range/tilesize, (x, y) -> {
                Tile currentTile = Vars.world.tile(x, y);
                if(currentTile == null) return;
                returnCount += currentTile.block().attributes.get(ExoAttribute.power);
                Fx.smoke.at(x * tilesize, y * tilesize);
            });
            return returnCount;
        }

    public class PowerHarvesterBuild extends Building {
        public float sum;
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, team.color);
        }
        public void onProximityAdded() {
            super.onProximityAdded();
            sum = countCrystal(tile, range);
        }
        @Override
        public float getPowerProduction() {
            return powerProduction * sum;
        }
    }
}