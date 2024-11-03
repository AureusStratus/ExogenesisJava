package exogenesis.type.bullet;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import exogenesis.graphics.*;

import static exogenesis.graphics.Draw3D.*;

public abstract class ArcBasicBulletType extends ArcBulletType{
    public String sprite;
    public boolean bloomSprite = true;
    public boolean drawShadow = false, spinShade = true;
    public TextureRegion region, shadowRegion;
    public TextureRegion[] regions;

    public ArcBasicBulletType(float speed, float damage, String sprite){
        super(speed, damage);
        this.sprite = sprite;
    }

    public ArcBasicBulletType(float speed, float damage){
        this(speed, damage, "bullet");
        spinShade = false;
    }

    public ArcBasicBulletType(float speed){
        this(speed, 0f);
    }

    @Override
    public void load(){
        region = Core.atlas.find(sprite);

        if(spinShade){
            regions = new TextureRegion[3];
            regions[0] = region;
            for(int i = 1; i < 3; i++){
                regions[i] = Core.atlas.find(sprite + "-" + i);
            }
        }

        shadowRegion = Core.atlas.find(sprite + "-shadow", region);
    }

    @Override
    public void draw(Bullet b){
        drawTargetZone(b);

        ArcBulletData data = (ArcBulletData)b.data;
        float lastHX = Draw3D.x(b.lastX, data.lastZ),
            lastHY = Draw3D.y(b.lastY, data.lastZ);
        float hX = Draw3D.x(b.x, data.z),
            hY = Draw3D.y(b.y, data.z);
        float rot = Angles.angle(lastHX, lastHY, hX, hY);
        if(drawShadow && data.z < shadowMax){
            float scl = shadowScale(data.z),
                sX = Angles.trnsx(225f, data.z) + b.x,
                sY = Angles.trnsy(225f, data.z) + b.y,
                sRot = Angles.angle(b.originX, b.originY, b.aimX, b.aimY),
                sAlpha = Draw3D.shadowAlpha(data.z);

            float pitch = Tmp.v1.set(b.vel.len(), data.zVel).angle(); //0 - 90 or 270-360
            if(pitch <= 90){ //Going vertical, aim away from current point.
                sRot = Mathf.lerp(sRot, sRot < 45f ? -135f : 225f, pitch / 90f);
            }else if(pitch >= 270f){ //Falling down, aim towards current point.
                sRot = Mathf.lerp(sRot, sRot > 225f ? 405f : 45f, (360f - pitch) / 90f);
            }
            float fsRot = sRot; //I love Java

            Draw3D.shadow(() -> {
                Draw.scl(scl);
                ExoOtherDrawf.shadow(shadowRegion, sX, sY, fsRot, sAlpha);
                Draw.scl();
            });
        }

        Draw.z(layer + data.z / 3000f); //Higher elevation should draw above
        drawTrail(b);
        Draw3D.highBloom(bloomSprite, () -> {
            Draw.scl(1f + hMul(data.z));
            float alpha = Draw3D.scaleAlpha(data.z);
            if(spinShade){
                ExoOtherDrawf.spinSprite(regions, hX, hY, rot, alpha);
            }else{
                Draw.alpha(alpha);
                Draw.rect(region, hX, hY, rot);
            }
            Draw.scl();
        });
    }
}
