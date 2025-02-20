package exogenesis.util.util;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;

public class MathU{
    private static final Vec2 vec = new Vec2();
    private static final Rand seedr = new Rand();

    public static void randLenVectors(long seed, int amount, float in, float inRandMin, float inRandMax, float lengthRand, FloatFloatf length, UParticleConsumer cons){
        seedr.setSeed(seed);
        for(int i = 0; i < amount; i++){
            float r = seedr.random(inRandMin, inRandMax);
            float offset = r > 0 ? seedr.nextFloat() * r : 0f;

            float fin = Mathf.curve(in, offset, (1f - r) + offset);
            float f = length.get(fin) * (lengthRand <= 0f ? 1f : seedr.random(1f - lengthRand, 1f));
            vec.trns(seedr.random(360f), f);
            cons.get(vec.x, vec.y, fin);
        }
    }

    public static float slope(float fin, float bias){
        return (fin < bias ? (fin / bias) : 1f - (fin - bias) / (1f - bias));
    }

    public static Vec2 addLength(Vec2 vec, float add){
        float len = vec.len();
        vec.x += add * (vec.x / len);
        vec.y += add * (vec.y / len);
        return vec;
    }

    public interface UParticleConsumer{
        void get(float x, float y, float fin);
    }
}
