package exogenesis.type.weapons;

import arc.math.*;
import arc.util.*;
import exogenesis.type.bullet.ArcMissileBulletType;
import exogenesis.util.ExoMathf;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;

import static mindustry.core.Version.type;
import static mindustry.ctype.ContentType.team;

/** A weapon which sets the shot bullet's owner to the missile unit's shooter. */
public class MissileOwnerWeapon extends Weapon{ //TODO remove after my PR
    @Override
    protected void shoot(Unit unit, WeaponMount mount, float shootX, float shootY, float rotation){
        unit.apply(shootStatus, shootStatusDuration);

        if(shoot.firstShotDelay > 0){
            mount.charging = true;
            chargeSound.at(shootX, shootY, Mathf.random(soundPitchMin, soundPitchMax));
            bullet.chargeEffect.at(shootX, shootY, rotation, bullet.keepVelocity || parentizeEffects ? unit : null);
        }


        shoot.shoot(mount.barrelCounter, (xOffset, yOffset, angle, delay, mover) -> {
            //this is incremented immediately, as it is used for total bullet creation amount detection
            mount.totalShots ++;
            int barrel = mount.barrelCounter;

            if(delay > 0f){
                Time.run(delay, () -> {
                    //hack: make sure the barrel is the same as what it was when the bullet was queued to fire
                    int prev = mount.barrelCounter;
                    mount.barrelCounter = barrel;
                    bullet(unit, mount, xOffset, yOffset, angle, mover);
                    mount.barrelCounter = prev;
                });
            }else{
                bullet(unit, mount, xOffset, yOffset, angle, mover);
            }
            /*
            float
                    xSpread = Mathf.range(xRand),
                    bulletX = x + Angles.trnsx(rotation - 90, shootX + xOffset + xSpread, shootY + yOffset),
                    bulletY = y + Angles.trnsy(rotation - 90, shootX + xOffset + xSpread, shootY + yOffset),
                    shootAngle = rotation + angleOffset + Mathf.range(inaccuracy + type.inaccuracy),
                    accScl = 1f + Mathf.range(accelRnd / 2f),
                    velScl = 1f + Mathf.range(velocityRnd / 2f);

            if(!type.scaleLife) targetPos.sub(this).setLength(range()).add(this);
            float dst = Math.max(Math.min(Mathf.dst(bulletX, bulletY, targetPos.x, targetPos.y), range()), minRange);
            ArcMissileBulletType m = (ArcMissileBulletType)type;
            float time = ExoMathf.quadPos(m.accel / 2f, m.speed, -dst);
            float zVel = -0.5f * -m.gravity * time;
            handleBullet(m.create3DVel(this, team, bulletX, bulletY, 0f, shootAngle, zVel, m.accel * accScl, m.speed * velScl, targetPos.x, targetPos.y), xOffset, yOffset, shootAngle - rotation);
            */
        }, () -> mount.barrelCounter++);

    }
    @Override
    protected void bullet(Unit unit, WeaponMount mount, float xOffset, float yOffset, float angleOffset, Mover mover){
        if(!unit.isAdded()) return;

        mount.charging = false;
        float
            xSpread = Mathf.range(xRand),
            weaponRotation = unit.rotation - 90 + (rotate ? mount.rotation : baseRotation),
            mountX = unit.x + Angles.trnsx(unit.rotation - 90, x, y),
            mountY = unit.y + Angles.trnsy(unit.rotation - 90, x, y),
            bulletX = mountX + Angles.trnsx(weaponRotation, this.shootX + xOffset + xSpread, this.shootY + yOffset),
            bulletY = mountY + Angles.trnsy(weaponRotation, this.shootX + xOffset + xSpread, this.shootY + yOffset),
            shootAngle = bulletRotation(unit, mount, bulletX, bulletY) + angleOffset,
            lifeScl = bullet.scaleLife ? Mathf.clamp(Mathf.dst(bulletX, bulletY, mount.aimX, mount.aimY) / bullet.range) : 1f,
            angle = angleOffset + shootAngle + Mathf.range(inaccuracy + bullet.inaccuracy);

        Teamc owner = unit.controller() instanceof MissileAI ai ? ai.shooter : unit; //Set the shooter to be the bullet's owner so that missile unit frags have the proper shooter
        mount.bullet = bullet.create(owner, unit.team, bulletX, bulletY, angle, -1f, (1f - velocityRnd) + Mathf.random(velocityRnd), lifeScl, null, mover, mount.aimX, mount.aimY);
        handleBullet(unit, mount, mount.bullet);
        if(owner != unit && bullet.killShooter && !unit.dead()) unit.kill(); //Since this unit technically isn't the owner of the bullet, handle killing the shooter here. Also bullets with a spawnUnit don't kill shooter anyways.

        if(!continuous){
            shootSound.at(bulletX, bulletY, Mathf.random(soundPitchMin, soundPitchMax));
        }

        ejectEffect.at(mountX, mountY, angle * Mathf.sign(this.x));
        bullet.shootEffect.at(bulletX, bulletY, angle, bullet.hitColor, unit);
        bullet.smokeEffect.at(bulletX, bulletY, angle, bullet.hitColor, unit);

        unit.vel.add(Tmp.v1.trns(shootAngle + 180f, bullet.recoil));
        Effect.shake(shake, shake, bulletX, bulletY);
        mount.recoil = 1f;
        mount.heat = 1f;
    }
}
