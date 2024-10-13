package exogenesis.world.draw;

import arc.graphics.g2d.Draw;
import mindustry.gen.Building;
import mindustry.world.draw.DrawRegion;

public class DrawLoopPart extends DrawRegion {
    float xMove, yMove, speedMultiplier;
    boolean alphaPulse;
    public DrawLoopPart(String suffix, float xMove, float yMove, boolean alphaPulse, float speedMultiplier){
        this.suffix = suffix;
        this.xMove = xMove;
        this.yMove = yMove;
        this.alphaPulse = alphaPulse;
        this.speedMultiplier = speedMultiplier;
    }
    @Override
    public void draw(Building build){
        if(alphaPulse){
            Draw.alpha(build.progress()*(float)Math.PI*2 - (float) Math.PI/2);
        }
        float x = xMove < 0 ? Math.max(xMove*build.progress()*speedMultiplier, xMove) : Math.min(xMove*build.progress()*speedMultiplier, xMove);
        float y = yMove < 0 ? Math.max(yMove*build.progress()*speedMultiplier, yMove) : Math.min(yMove*build.progress()*speedMultiplier, yMove);
        Draw.rect(region, build.x+x, build.y+y);
        Draw.alpha(1);
    }
}