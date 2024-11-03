package exogenesis.content.effects;

import arc.util.*;
import exogenesis.graphics.trails.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import exogenesis.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static mindustry.Vars.*;

public class TrailFadeFx{
    public static Effect

    PMTrailFade = new Effect(400f, e -> {
        if(!(e.data instanceof ExoTrail trail)) return;
        //lifetime is how many frames it takes to fade out the trail
        e.lifetime = trail.length * 1.4f;

        if(!state.isPaused()){
            trail.shorten();
        }
        trail.draw(e.color, e.rotation);
        trail.drawCap(e.color, e.rotation);
    }),

    driftTrailFade = new Effect(400f, e -> {
        if(!(e.data instanceof DriftTrail trail)) return;
        //lifetime is how many frames it takes to fade out the trail
        e.lifetime = trail.length * 1.4f;

        if(!state.isPaused()){
            trail.shorten();
            trail.drift();
        }
        trail.draw(e.color, e.rotation);
        trail.drawCap(e.color, e.rotation);
    }),

    heightTrailFade = new Effect(400f, e -> {
        if(!(e.data instanceof HeightTrail trail)) return;
        //lifetime is how many frames it takes to fade out the trail
        e.lifetime = trail.length * 1.4f;

        if(!state.isPaused()){
            trail.shorten();
        }
        int col = e.color.rgba8888();
        float size = e.rotation;
        Draw3D.highBloom(() -> {
            Tmp.c1.rgba8888(col);
            trail.draw(Tmp.c1, size);
            trail.drawCap(Tmp.c1, size);
        });
    }).layer(Layer.flyingUnit + 1.9f);
}
