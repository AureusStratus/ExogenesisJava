package exogenesis.util.util;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.graphics.*;

public class GraphicUtils{
    static Vec3 v = new Vec3();
    static Vec2 v2 = new Vec2();
    static FloatSeq tf = new FloatSeq(4 * 2);
    public static float[] verts = new float[4 * 6];
    static TextureRegion chain;

    static boolean drawing3D = false;
    static Batch last;
    static Mat3D mat3 = new Mat3D();

    public static Blending invert = new Blending(Gl.oneMinusDstColor, Gl.oneMinusSrcAlpha, Gl.srcAlpha, Gl.oneMinusSrcAlpha);
    public static Blending multiply = new Blending(Gl.dstColor, Gl.oneMinusSrcAlpha, Gl.srcAlpha, Gl.oneMinusSrcAlpha);


    public static void drawShockWave(float x, float y, float rx, float ry, float rz, float size, float width, int iter){
        drawShockWave(x, y, rx, ry, rz, size, width, iter, 0.02f);
    }

    public static void drawShockWave(float x, float y, float rx, float ry, float rz, float size, float width, int iter, float zRange){
        float off = (360f / iter);
        //float scl = size + width;
        float zz = Draw.z();

        for(int i = 0; i < iter; i++){
            float angle1 = off * i;
            float angle2 = off * (i + 1);
            float z = 0f;

            tf.clear();
            for(int j = 0; j < 4; j++){
                float w = j == 0 || j == 3 ? width : -width;
                float a = j <= 1 ? angle1 : angle2;

                v2.trns(a, size + w);
                v.set(v2.x, v2.y, 0f).rotate(Vec3.X, rx).rotate(Vec3.Y, ry).rotate(Vec3.Z, rz);
                float sz = 700f / (700f - v.z);
                v.x *= sz;
                v.y *= sz;
                //v.add(x, y, 0f);

                z += v.z;
                tf.add(v.x + x, v.y + y);
            }
            //float tz = Mathf.clamp((z / 4f) / sizeDepth) * zRange + zz;
            float tz = (z < 0f ? -zRange : zRange) + zz;
            Draw.z(tz);
            Fill.polyBegin();
            for(int j = 0; j < 4; j++){
                float vx = tf.items[j * 2];
                float vy = tf.items[j * 2 + 1];
                Fill.polyPoint(vx, vy);
            }
            Fill.polyEnd();
        }
        Draw.z(zz);
    }

}
