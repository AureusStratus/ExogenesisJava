package exogenesis.content.effects;

import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static exogenesis.graphics.Draw3D.hMul;
import static mindustry.Vars.*;
import static exogenesis.graphics.Draw3D.*;

public class Pseudo3DFx{
    public static Effect

    absorbedSmall = new Effect(38f, e -> {
        color(Pal.missileYellow);
        float z = e.rotation;
        float zScl = 1f + hMul(z);

        e.scaled(13f, s -> {
            stroke(2f * s.fout() * zScl);

            randLenVectors(e.id, 8, (2f + 34f * s.fin()) * zScl, (x, y) -> {
                lineAngle(x(e.x + x, z), y(e.y + y, z), angle(x, y), (2f + s.fout() * 8f) * zScl);
            });
        });

        float in = Interp.pow2Out.apply(Mathf.curve(e.fin(), 0f, 0.6f));
        float out = 1f - Interp.pow2In.apply(Mathf.curve(e.fin(), 0.6f));

        stroke((0.5f * out + e.fout()) * zScl);

        Lines.circle(x(e.x, z), y(e.y, z), (2f * out + 13f * in * out) * zScl);
    }),

    absorbed = new Effect(52f, e -> {
        color(Pal.missileYellow);
        float z = e.rotation;
        float zScl = 1f + hMul(z);

        e.scaled(24f, s -> {
            stroke(3f * s.fout() * zScl);

            randLenVectors(e.id, 14, (2f + 53f * s.fin()) * zScl, (x, y) -> {
                lineAngle(x(e.x + x, z), y(e.y + y, z), angle(x, y), (2f + s.fout() * 13f) * zScl);
            });
        });

        float in = Interp.pow2Out.apply(Mathf.curve(e.fin(), 0f, 0.6f));
        float out = 1f - Interp.pow2In.apply(Mathf.curve(e.fin(), 0.6f));

        stroke((out + 2f * e.fout()) * zScl);

        Lines.circle(x(e.x, z), y(e.y, z), (6f * out + 31f * in * out) * zScl);
    }),

    absorbedLarge = new Effect(74f, e -> {
        color(Pal.missileYellow);
        float z = e.rotation;
        float zScl = 1f + hMul(z);

        e.scaled(32f, s -> {
            stroke(5f * s.fout() * zScl);

            randLenVectors(e.id, 20, (4f + 114f * s.fin() * zScl), (x, y) -> {
                lineAngle(x(e.x + x, z), y(e.y + y, z), angle(x, y), (3f + s.fout() * 18f) * zScl);
            });
        });

        float in = Interp.pow2Out.apply(Mathf.curve(e.fin(), 0f, 0.6f));
        float out = 1f - Interp.pow2In.apply(Mathf.curve(e.fin(), 0.6f));

        stroke((2f * out + 3f * e.fout()) * zScl);

        Lines.circle(x(e.x, z), y(e.y, z), (6f * out + 57f * in * out) * zScl);
    });

    public static class ShieldSizeData{
        public int sides;
        public float height;
        public float rotation;

        public ShieldSizeData(int sides, float rotation, float height){
            this.sides = sides;
            this.rotation = rotation;
            this.height = height;
        }
    }
}
