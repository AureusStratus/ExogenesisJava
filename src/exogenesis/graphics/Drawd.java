package exogenesis.graphics;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;

public class Drawd {
    public static void lightArc(float x, float y, int sides, float radius, float rotation, float frac, Color center, Color edge) {
        float centerf = center.toFloatBits(), edgef = edge.toFloatBits();

        sides = Mathf.ceil(sides / 2f) * 2;

        float space = frac / sides;

        for (int i = 0; i < sides; i += 2) {
            float px = Angles.trnsx(space * i + rotation, radius);
            float py = Angles.trnsy(space * i + rotation, radius);
            float px2 = Angles.trnsx(space * (i + 1) + rotation, radius);
            float py2 = Angles.trnsy(space * (i + 1) + rotation, radius);
            float px3 = Angles.trnsx(space * (i + 2) + rotation, radius);
            float py3 = Angles.trnsy(space * (i + 2) + rotation, radius);
            Fill.quad(x, y, centerf, x + px, y + py, edgef, x + px2, y + py2, edgef, x + px3, y + py3, edgef);
        }
    }
}
