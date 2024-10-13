package exogenesis.content;

import arc.func.Floatp;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Tmp;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static mindustry.content.Fx.rand;

public class ExoFxf {
    private static float percent = 0;
    public static Effect

    chainLightning = new Effect(15f, 300f, e -> {
        if(!(e.data instanceof VisualLightningHolder)) return;
        VisualLightningHolder p = (VisualLightningHolder) e.data;

        int seed = e.id;
        //get the start and ends of the lightning, then the distance between them
        float tx = Tmp.v1.set(p.start()).x, ty = Tmp.v1.y, dst = Mathf.dst(Tmp.v2.set(p.end()).x, Tmp.v2.y, tx, ty);


        Tmp.v3.set(p.end()).sub(p.start()).nor();
        float normx = Tmp.v3.x, normy = Tmp.v3.y;

        rand.setSeed(seed);

        float arcWidth = rand.range(dst * p.arc());

        seed = e.id - (int) e.time;

        float angle = Tmp.v1.angleTo(Tmp.v2);

        Floatp arcX = () -> Mathf.sinDeg(percent * 180) * arcWidth;

        //range of lightning strike's vary depending on turret
        float range = p.segLength();
        int links = Mathf.ceil(dst / p.segLength());
        float spacing = dst / links;

        Lines.stroke(p.width() * e.fout());
        Draw.color(Color.white, e.color, e.finpow());
        Fill.circle(Tmp.v2.x, Tmp.v2.y, p.width() * e.fout()/2);

        //begin the line
        Lines.beginLine();

        Lines.linePoint(Tmp.v1.x, Tmp.v1.y);
        float lastx = Tmp.v1.x, lasty = Tmp.v1.y;

        for(int i = 0; i < links; i++){
            float nx, ny;
            if(i == links - 1){
                //line at end
                nx = Tmp.v2.x;
                ny = Tmp.v2.y;
            }else{
                float len = (i + 1) * spacing;
                rand.setSeed(seed + i);
                Tmp.v3.trns(rand.random(360), range/2);
                percent = ((float) (i + 1))/links;

                nx = tx + normx * len + Tmp.v3.x + Tmp.v4.set(0, arcX.get()).rotate(angle).x;
                ny = ty + normy * len + Tmp.v3.y + Tmp.v4.y;
            }

            Drawf.light(lastx, lasty, nx, ny, Lines.getStroke(), Draw.getColor(), Draw.getColor().a);
            lastx = nx;
            lasty = ny;
            Lines.linePoint(nx, ny);
        }

        Lines.endLine();
    });

    public interface VisualLightningHolder{
        Vec2 start();

        Vec2 end();

        float width();

        float segLength();

        float arc();
    }
}