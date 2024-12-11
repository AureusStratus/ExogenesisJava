package exogenesis.graphics.graphicsBH;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.graphics.*;

public class LightTrail extends Trail{
    private final Color drawColor = new Color();
    public float lightOpacity;

    public LightTrail(int length, float lightOpacity){
        super(length);
        this.lightOpacity = lightOpacity;
    }

    @Override
    public LightTrail copy(){
        LightTrail out = new LightTrail(length, lightOpacity);
        out.points.addAll(points);
        out.lastX = lastX;
        out.lastY = lastY;
        out.lastAngle = lastAngle;
        return out;
    }

    public void draw(Color color, float width, float light){
        drawColor.set(color);
        Draw.color(color);
        float[] items = points.items;
        float lastAngle = this.lastAngle;
        float size = width / (points.size / 3);

        for(int i = 0; i < points.size; i += 3){
            float x1 = items[i], y1 = items[i + 1], w1 = items[i + 2];
            float x2, y2, w2;

            //last position is always lastX/Y/W
            if(i < points.size - 3){
                x2 = items[i + 3];
                y2 = items[i + 4];
                w2 = items[i + 5];
            }else{
                x2 = lastX;
                y2 = lastY;
                w2 = lastW;
            }

            float z2 = -Angles.angleRad(x1, y1, x2, y2);
            //end of the trail (i = 0) has the same angle as the next.
            float z1 = i == 0 ? z2 : lastAngle;
            if(w1 <= 0.001f || w2 <= 0.001f) continue;

            float
                cx = Mathf.sin(z1) * i/3f * size * w1,
                cy = Mathf.cos(z1) * i/3f * size * w1,
                nx = Mathf.sin(z2) * (i/3f + 1) * size * w2,
                ny = Mathf.cos(z2) * (i/3f + 1) * size * w2;

            Fill.quad(
                x1 - cx, y1 - cy,
                x1 + cx, y1 + cy,
                x2 + nx, y2 + ny,
                x2 - nx, y2 - ny
            );
            Drawf.light(x1, y1, x2, y2, (i/3f + 1) * size * w2 * 6f, drawColor, light * lightOpacity);

            lastAngle = z2;
        }

        Draw.reset();
    }

    @Override
    public void draw(Color color, float width){
        draw(color, width, 1f);
    }
}
