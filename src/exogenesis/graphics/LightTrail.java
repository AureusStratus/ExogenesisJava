package exogenesis.graphics;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import mindustry.graphics.Drawf;
import mindustry.graphics.Trail;

public class LightTrail extends Trail {
    public float lightOpacity;

    public LightTrail(int length, float lightOpacity) {
        super(length);
        this.lightOpacity = lightOpacity;
    }

    public exogenesis.graphics.LightTrail copy() {
        exogenesis.graphics.LightTrail out = new exogenesis.graphics.LightTrail(this.length, this.lightOpacity);
        out.points.addAll(this.points);
        out.lastX = this.lastX;
        out.lastY = this.lastY;
        out.lastAngle = this.lastAngle;
        return out;
    }

    public void draw(Color color, float width, float light) {
        Draw.color(color);
        float[] items = this.points.items;
        float lastAngle = this.lastAngle;
        float size = width / (float)(this.points.size / 3);

        for(int i = 0; i < this.points.size; i += 3) {
            float x1 = items[i];
            float y1 = items[i + 1];
            float w1 = items[i + 2];
            float x2;
            float y2;
            float w2;
            if (i < this.points.size - 3) {
                x2 = items[i + 3];
                y2 = items[i + 4];
                w2 = items[i + 5];
            } else {
                x2 = this.lastX;
                y2 = this.lastY;
                w2 = this.lastW;
            }

            float z2 = -Angles.angleRad(x1, y1, x2, y2);
            float z1 = i == 0 ? z2 : lastAngle;
            if (!(w1 <= 0.001F) && !(w2 <= 0.001F)) {
                float cx = Mathf.sin(z1) * (float)i / 3.0F * size * w1;
                float cy = Mathf.cos(z1) * (float)i / 3.0F * size * w1;
                float nx = Mathf.sin(z2) * ((float)i / 3.0F + 1.0F) * size * w2;
                float ny = Mathf.cos(z2) * ((float)i / 3.0F + 1.0F) * size * w2;
                Fill.quad(x1 - cx, y1 - cy, x1 + cx, y1 + cy, x2 + nx, y2 + ny, x2 - nx, y2 - ny);
                Drawf.light(x1, y1, x2, y2, ((float)i / 3.0F + 1.0F) * size * w2 * 6.0F, color.cpy(), light * this.lightOpacity);
                lastAngle = z2;
            }
        }

        Draw.reset();
    }

    public void draw(Color color, float width) {
        this.draw(color, width, 1.0F);
    }
}
