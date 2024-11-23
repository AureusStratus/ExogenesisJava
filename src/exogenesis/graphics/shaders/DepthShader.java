package exogenesis.graphics.shaders;

import arc.graphics.g3d.*;
import arc.graphics.gl.*;

import static exogenesis.graphics.ExoShaders.*;

/**
 * Specialized mesh shader to capture fragment depths.
 * @author GlFolker
 */
public class DepthShader extends Shader {
    public Camera3D camera;

    public DepthShader(){
        super(file("depth.vert"), file("depth.frag"));
    }

    @Override
    public void apply(){
        setUniformf("u_camPos", camera.position);
        setUniformf("u_camRange", camera.near, camera.far - camera.near);
    }
}
