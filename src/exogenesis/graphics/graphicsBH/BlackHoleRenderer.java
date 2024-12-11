package exogenesis.graphics.graphicsBH;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.graphics.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Handles rendering of gravitational lensing and the glow around the center.
 * @author MEEPofFaith
 * */
public class BlackHoleRenderer{
    private final Seq<BlackHoleZone> zones = new Seq<>(BlackHoleZone.class);
    private final Seq<BlackHoleStar> stars = new Seq<>(BlackHoleStar.class);
    private static BlackHoleRenderer bRenderer;
    private int zonesIndex;
    private int starsIndex;
    private boolean advanced = true;

    private FrameBuffer buffer;

    protected BlackHoleRenderer(boolean advanced){
        exogenesis.graphics.graphicsBH.BHShaders.createBlackHoleShaders();
        advanced(advanced);

        Events.run(Trigger.draw, () -> {
            if(this.advanced){
                advancedDraw();
            }else{
                simplifiedDraw();
            }
        });
    }

    public static void init(boolean advanced){
        if(bRenderer == null) bRenderer = new BlackHoleRenderer(advanced);
    }

    public static void toggleAdvanced(boolean advanced){
        if(bRenderer != null) bRenderer.advanced(advanced);
    }

    /**
     * Adds a black hole to the renderer.
     *
     * @param x x-coordinate of the center
     * @param y y-coordinate of the center
     * @param inRadius size of the black hole (radius of where it's black)
     * @param outRadius end of the gravitational lensing range
     * @param color color of the glowing rim
     */
    public static void addBlackHole(float x, float y, float inRadius, float outRadius, Color color){
        bRenderer.addBH(x, y, inRadius, outRadius, color);
    }

    public static void addStar(float x, float y, float w, float h, float angleOffset, Color in, Color out){
        bRenderer.addS(x, y, w, h, angleOffset, in, out);
    }

    public static void addStar(float x, float y, float w, float h, Color in, Color out){
        addStar(x, y, w, h, 0, in, out);
    }

    private void advancedDraw(){
        Draw.draw(exogenesis.graphics.graphicsBH.BHLayer.begin, () -> {
            buffer.resize(graphics.getWidth(), graphics.getHeight());
            buffer.begin();
        });

        Draw.draw(BHLayer.end, () -> {
            buffer.end();

            if(zones.size >= exogenesis.graphics.graphicsBH.BHShaders.maxCount) exogenesis.graphics.graphicsBH.BHShaders.createBlackHoleShaders();

            float[] blackholes = new float[zonesIndex * 4];
            float[] colors = new float[zonesIndex * 4];
            for(int i = 0; i < zonesIndex; i++){
                BlackHoleZone zone = zones.items[i];
                blackholes[i * 4] = zone.x;
                blackholes[i * 4 + 1] = zone.y;
                blackholes[i * 4 + 2] = zone.inRadius;
                blackholes[i * 4 + 3] = zone.outRadius;

                Tmp.c1.abgr8888(zone.color);
                colors[i * 4] = Tmp.c1.r;
                colors[i * 4 + 1] = Tmp.c1.g;
                colors[i * 4 + 2] = Tmp.c1.b;
                colors[i * 4 + 3] = Tmp.c1.a;
            }
            exogenesis.graphics.graphicsBH.BHShaders.lensingShader.blackHoles = blackholes;
            buffer.blit(exogenesis.graphics.graphicsBH.BHShaders.lensingShader);

            exogenesis.graphics.graphicsBH.BHShaders.rimShader.blackHoles = blackholes;
            exogenesis.graphics.graphicsBH.BHShaders.rimShader.colors = colors;
            buffer.begin();
            Draw.rect();
            buffer.end();

            Bloom bloom = renderer.bloom;
            if(bloom != null){
                bloom.capture();
                buffer.blit(exogenesis.graphics.graphicsBH.BHShaders.rimShader);
                drawStars();
                bloom.render();
            }else{
                buffer.blit(BHShaders.rimShader);
                drawStars();
            }

            zonesIndex = 0;
        });
    }

    private void simplifiedDraw(){
        Draw.draw(Layer.max, () -> {
            Draw.color(Color.black);
            for(int i = 0; i < zonesIndex; i++){
                BlackHoleZone zone = zones.items[i];
                Fill.circle(zone.x, zone.y, zone.inRadius);
            }
            Draw.color();

            Bloom bloom = renderer.bloom;
            if(bloom != null){
                bloom.capture();
                simplifiedRims();
                drawStars();
                bloom.render();
            }else{
                simplifiedRims();
                drawStars();
            }

            zonesIndex = 0;
        });
    }

    private void simplifiedRims(){
        for(int i = 0; i < zonesIndex; i++){
            BlackHoleZone zone = zones.items[i];
            float rad = Mathf.lerp(zone.inRadius, zone.outRadius, 0.125f);
            int vert = Lines.circleVertices(rad);
            float space = 360f / vert;

            Tmp.c1.abgr8888(zone.color);
            float c1 = Tmp.c1.toFloatBits();
            float c2 = Tmp.c1.a(0).toFloatBits();

            for(int j = 0; j < vert; j++){
                float sin1 = Mathf.sinDeg(j * space), sin2 = Mathf.sinDeg((j + 1) * space);
                float cos1 = Mathf.cosDeg(j * space), cos2 = Mathf.cosDeg((j + 1) * space);

                Fill.quad(
                    zone.x + cos1 * zone.inRadius, zone.y + sin1 * zone.inRadius, c1,
                    zone.x + cos2 * zone.inRadius, zone.y + sin2 * zone.inRadius, c1,
                    zone.x + cos2 * rad, zone.y + sin2 * rad, c2,
                    zone.x + cos1 * rad, zone.y + sin1 * rad, c2
                );
            }
        }
    }

    private void drawStars(){
        for(int i = 0; i < starsIndex; i++){
            BlackHoleStar star = stars.items[i];
            BHDrawf.drawStar(star.x, star.y, star.w, star.h, star.angleOffset, star.inColor, star.outColor);
        }
        starsIndex = 0;
    }

    private void advanced(boolean advanced){
        this.advanced = advanced;
        if(advanced){
            buffer = new FrameBuffer();
        }else{
            if(buffer != null) buffer.dispose();
        }
    }

    private void addBH(float x, float y, float inRadius, float outRadius, Color color){
        if(inRadius > outRadius || outRadius <= 0) return;
        if(zones.size <= zonesIndex) zones.add(new BlackHoleZone());

        float res = Color.toFloatBits(color.r, color.g, color.b, 1);

        BlackHoleZone zone = zones.items[zonesIndex];
        zone.set(x, y, res, inRadius, outRadius);

        zonesIndex++;
    }

    private void addS(float x, float y, float w, float h, float angleOffset, Color in, Color out){
        if(w <= 0 || h <= 0) return;
        if(stars.size <= starsIndex) stars.add(new BlackHoleStar());

        BlackHoleStar star = stars.items[starsIndex];
        star.set(x, y, w, h, angleOffset, in.toFloatBits(), out.toFloatBits());

        starsIndex++;
    }

    private static class BlackHoleZone{
        float x, y, color, inRadius, outRadius;

        public void set(float x, float y, float color, float inRadius, float outRadius){
            this.x = x;
            this.y = y;
            this.color = color;
            this.inRadius = inRadius;
            this.outRadius = outRadius;
        }
    }


    private static class BlackHoleStar{
        float x, y, w, h, angleOffset, inColor, outColor;

        public void set(float x, float y, float w, float h, float angleOffset, float inColor, float outColor){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.angleOffset = angleOffset;
            this.inColor = inColor;
            this.outColor = outColor;
        }
    }
}
