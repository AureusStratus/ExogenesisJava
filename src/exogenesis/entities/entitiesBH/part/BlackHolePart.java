package exogenesis.entities.entitiesBH.part;

import arc.graphics.*;
import arc.math.*;
import arc.util.*;
import blackhole.graphics.*;
import mindustry.entities.part.*;
import mindustry.graphics.*;

public class BlackHolePart extends DrawPart{
    /** Progress function for determining position/rotation. */
    public PartProgress progress = PartProgress.warmup;
    /** Progress function for scaling. */
    public PartProgress growProgress = PartProgress.warmup;
    public float x, y, size, sizeTo, edge, edgeTo;
    public float starWidth = -1, starHeight = -1, starAngle;
    public @Nullable Color starIn, starOut;
    public float moveX, moveY;
    public Color color = Pal.accent;
    public @Nullable Color colorTo;
    public boolean mirror = false;

    public void setConstant(){
        progress = PartProgress.constant(1f);
        growProgress = PartProgress.constant(1f);
    }

    @Override
    public void draw(PartParams params){
        float prog = progress.getClamp(params), sclProg = growProgress.getClamp(params);

        int len = mirror && params.sideOverride == -1 ? 2 : 1;

        for(int s = 0; s < len; s++){
            //use specific side if necessary
            int i = params.sideOverride == -1 ? s : params.sideOverride;

            float sign = (i == 0 ? 1 : -1) * params.sideMultiplier;
            Tmp.v1.set((x + moveX * prog) * sign, y + moveY * prog).rotate(params.rotation - 90);

            float
                rx = params.x + Tmp.v1.x,
                ry = params.y + Tmp.v1.y;

            Tmp.c1.set(color);
            if(colorTo != null) Tmp.c1.lerp(colorTo, prog);

            BlackHoleRenderer.addBlackHole(rx, ry, Mathf.lerp(size, sizeTo, sclProg), Mathf.lerp(edge, edgeTo, sclProg), Tmp.c1);
        }
    }

    @Override
    public void load(String name){
    }
}
