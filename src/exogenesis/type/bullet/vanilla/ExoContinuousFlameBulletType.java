package exogenesis.type.bullet.vanilla;

import arc.scene.ui.layout.Table;
import arc.struct.ObjectFloatMap;
import arc.struct.OrderedMap;
import arc.util.Strings;
import exogenesis.type.DamageType;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;

import static exogenesis.content.ExoStatValues.buildSharedBulletTypeStat;

public class ExoContinuousFlameBulletType extends ContinuousFlameBulletType implements TypedBulletType{
    public OrderedMap<DamageType, Float> damageMultiplier = new OrderedMap<>();

    @Override
    public OrderedMap<DamageType, Float> typedDamageMultipliers() {
        return damageMultiplier;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        typedHitEntity(this, b, entity, health);
    }

    @Override
    public void createSplashDamage(Bullet b, float x, float y) {
        typedCreateSplash(this, b, x, y);
    }
}
