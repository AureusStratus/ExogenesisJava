package exogenesis.type.bullet;

import arc.math.Angles;
import arc.util.Log;
import arc.util.Time;
import exogenesis.type.bullet.vanilla.ExoBasicBulletType;
import exogenesis.type.bullet.vanilla.ExoBulletType;
import mindustry.entities.Units;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Hitboxc;
import mindustry.gen.Unit;

/** bullet type that ricochets on collision. */
public class RicochetBulletType extends ExoBasicBulletType {
    public int maxJumps = 5;
    public float jumpRange = 60f;

    public RicochetBulletType(float speed, float damage) {
        super(speed, damage);
        pierce = true;
    }

    @Override
    public void init() {
        super.init();
        pierceCap = maxJumps;
        removeAfterPierce = false;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health) {
        super.hitEntity(b, entity, health);
        if (entity instanceof Unit unit){
            Unit next = Units.closest(unit.team, unit.x, unit.y, jumpRange, b::collides);
            if (next != null) b.vel.setAngle(b.angleTo(next));
        }
    }
}
