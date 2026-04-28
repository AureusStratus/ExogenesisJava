package exogenesis.maps.planets;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.maps.generators.*;

import static arc.graphics.Color.valueOf;

public class AxinNewPlanetGenerator extends PlanetGenerator {
    public double octaves = 2, persistence = 0.3, scl = 7.0, pow = 1.2, mag = 1;
    public float rotationScl = 360;

    public Color[] colors = new Color[]{
            valueOf("222058"),
            valueOf("0f0e25"),
            valueOf("606688"),
            valueOf("96b1b7"),
            valueOf("5c71f1"),
            valueOf("1d1898")
    };
    @Override
    public float getHeight(Vec3 position){
        return 0;
    }

    @Override
    public void getColor(Vec3 position, Color out){
        Tmp.v31.set(position).rotate(Vec3.Y, position.y * rotationScl).add(1000f, 0f, 500f);
        double height = Math.pow(Simplex.noise3d(0, octaves, persistence, scl, Tmp.v31.x, Tmp.v31.y, Tmp.v31.z), pow) * mag;
        out.set(colors[Mathf.clamp((int)(height * colors.length), 0, colors.length - 1)]);
    }
}