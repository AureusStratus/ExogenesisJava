package exogenesis.maps.planets;

import exogenesis.maps.ColorPass;
import exogenesis.maps.HeightPass;
import arc.graphics.*;
import arc.math.geom.*;
import arc.struct.Seq;
import mindustry.maps.generators.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class AxinPlanetGenerator extends PlanetGenerator {
    public Seq<HeightPass> heights = new Seq<>();
    public Seq<ColorPass> colors = new Seq<>();
    public float baseHeight = 1;
    public Color baseColor = Color.white;

    public float rawHeight(Vec3 position) {
        float height = baseHeight;
        for (HeightPass h : heights) {
            height = h.height(position, height);
        }
        return height;
    }

    @Override
    public void generateSector(Sector sector) {

    }

    @Override
    public float getHeight(Vec3 position) {
        return rawHeight(position);
    }

    @Override
    public Color getColor(Vec3 position) {
        Color color = baseColor;
        for (ColorPass c : colors) {
            if (c.color(position, rawHeight(position)) != null) color = c.color(position, rawHeight(position));
        }
        return color;
    }
}