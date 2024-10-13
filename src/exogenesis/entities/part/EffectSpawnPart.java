package exogenesis.entities.part;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.part.DrawPart;
import mindustry.graphics.*;

import static arc.math.Mathf.random;
import static arc.util.Tmp.v1;
import static arc.util.Tmp.v2;

public class EffectSpawnPart extends DrawPart {
public float x, y, width, height, rotation;
public boolean mirror = false;
public float  effectChance = 0.1f, effectRot, randomEffectRot = -1f;
public Effect effect = Fx.colorSpark;
public Color effectColor = Color.white;
public boolean useProgress = true;
public PartProgress progress = PartProgress.warmup;

public boolean debugDraw = false;
@Override
public void draw(PartParams params) {
    for (int i = 0; i < (mirror ? 2 : 1 ); i++){
        float sign = (i == 0 ? 1f : -1), rot = params.rotation + (rotation *  sign);

        v1.set(x * sign, y).rotate(params.rotation - 90).add(params.x, params.y);

        if (debugDraw) {
            float z = Draw.z();
            Draw.z(Layer.buildBeam);
            Draw.color(Color.red);
            Draw.rect("error", v1.x, v1.y, width, height, rot - 90f);
            Draw.color();
            Draw.z(z);
        }
        if (!Vars.state.isPaused() && Mathf.chanceDelta(effectChance * (useProgress ? progress.getClamp(params) : 1f))){
            v1.add(v2.set(random(-height * 0.5f, height * 0.5f), random(-width * 0.5f, width * 0.5f)).rotate(rot));

            effect.at(v1.x, v1.y, rot + (effectRot * sign) + random(-randomEffectRot, randomEffectRot), effectColor);
        }
    }
}
@Override
public void load(String name){}
}
