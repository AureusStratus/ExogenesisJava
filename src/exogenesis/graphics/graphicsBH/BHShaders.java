package exogenesis.graphics.graphicsBH;

import arc.*;
import arc.graphics.gl.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class BHShaders{
    public static int maxCount = 4;
    public static LensingShader lensingShader;
    public static RimShader rimShader;

    public static void createBlackHoleShaders(){
        if(maxCount >= 510) return; //Exceeds maximum number of registers for a single shader

        if(lensingShader != null){
            maxCount = Math.min(510, maxCount * 2);
            lensingShader.dispose();
            rimShader.dispose();
        }

        String oldPrepend = Shader.prependFragmentCode;
        Shader.prependFragmentCode = oldPrepend + "\n#define MAX_COUNT " + maxCount + "\n";
        lensingShader = new LensingShader();
        rimShader = new RimShader();
        Shader.prependFragmentCode = oldPrepend;
    }

    public static class LensingShader extends Shader{
        public float[] blackHoles;

        LensingShader(){
            super(
                files.internal("shaders/screenspace.vert"),
                tree.get("shaders/gravitationallensing.frag")
            );
        }

        @Override
        public void apply(){
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);

            setUniformi("u_blackholecount", blackHoles.length / 4);
            setUniform4fv("u_blackholes", blackHoles, 0, blackHoles.length);
        }
    }

    public static class RimShader extends Shader{
        public float[] blackHoles;
        public float[] colors;

        RimShader(){
            super(
                files.internal("shaders/screenspace.vert"),
                tree.get("shaders/blackholerim.frag")
            );
        }

        @Override
        public void apply(){
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);

            setUniformi("u_blackholecount", blackHoles.length / 4);
            setUniform4fv("u_blackholes", blackHoles, 0, blackHoles.length);
            setUniform4fv("u_colors", colors, 0, colors.length);
        }
    }
}
