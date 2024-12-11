package exogenesis.world;

import arc.graphics.*;
import arc.util.*;
import exogenesis.graphics.graphicsBH.BlackHoleRenderer;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.draw.*;

import static exogenesis.graphics.graphicsBH.BHDrawf.teamColor;

public class DrawBlackHole extends DrawBlock{
    public float x, y, size, edge;
    public @Nullable Color color;
    public float starWidth = -1, starHeight = -1, starAngle;
    public @Nullable Color starIn, starOut;
    public boolean warmup = true;

    public DrawBlackHole(float size, float edge){
        this.size = size;
        this.edge = edge;
    }

    public DrawBlackHole(){
    }

    @Override
    public void draw(Building build){
        float scl = warmup ? build.warmup() : 1f;
        BlackHoleRenderer.addBlackHole(
            build.x + x, build.y + y,
            size * scl, edge * scl,
            teamColor(build, color)
        );
        if(starWidth > 0){
            BlackHoleRenderer.addStar(
                build.x + x, build.y + y,
                starWidth * scl, starHeight * scl, starAngle,
                teamColor(build, starIn), teamColor(build, starOut)
            );
        }
    }

    @Override
    public void load(Block block){
        if(starWidth > 0 && starHeight < 0) starHeight = starWidth / 2;
    }
}
