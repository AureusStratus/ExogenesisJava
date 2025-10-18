package exogenesis.content;

import arc.func.Cons;
import arc.func.Prov;
import arc.graphics.Color;
import exogenesis.content.effects.ExoShootFx;
import exogenesis.graphics.ExoPal;
import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.bullet.vanilla.*;
import mindustry.content.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;

import static exogenesis.content.ExoDamageTypes.*;

public class ExoVanillaOverride {
    public static void load(){
        overrideTurretBullet(Blocks.duo, Items.copper, () -> new ExoBasicBulletType(){{
            speed = 2.5f;
            damage = 8f;
            width = 7f;
            height = 9f;
            lifetime = 60f;
            ammoMultiplier = 2;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.copperAmmoBack;
            frontColor = Pal.copperAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.duo, Items.graphite, () -> new ExoBasicBulletType(){{
            speed = 3.5f;
            damage = 19f;
            width = 9f;
            height = 12f;
            ammoMultiplier = 4;
            lifetime = 60f;
            rangeChange = 16f;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
            frontColor = Pal.graphiteAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.duo, Items.silicon, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 12f;
            width = 7f;
            height = 9f;
            homingPower = 0.2f;
            reloadMultiplier = 1.5f;
            ammoMultiplier = 5;
            lifetime = 60f;

            trailLength = 5;
            trailWidth = 1.5f;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.siliconAmmoBack;
            frontColor = Pal.siliconAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));

        overrideTurretBullet(Blocks.scatter, Items.scrap, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 3f;
            lifetime = 60f;
            ammoMultiplier = 5f;
            shootEffect = Fx.shootSmall;
            reloadMultiplier = 0.5f;
            width = 6f;
            height = 8f;
            hitEffect = Fx.flakExplosion;
            splashDamage = 22f * 1.5f;
            splashDamageRadius = 24f;

            frontColor = Pal.scrapAmmoFront;
            backColor = hitColor = Pal.scrapAmmoBack;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.scatter, Items.lead, () -> new ExoFlakBulletType(){{
            speed = 4.2f;
            damage = 3f;
            lifetime = 60f;
            ammoMultiplier = 4f;
            shootEffect = Fx.shootSmall;
            width = 6f;
            height = 8f;
            hitEffect = Fx.flakExplosion;
            splashDamage = 27f * 1.5f;
            splashDamageRadius = 15f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.scatter, Items.metaglass, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 3f;
            backColor = trailColor = Pal.glassAmmoBack;
            hitColor = frontColor = Pal.glassAmmoFront;
            despawnEffect = Fx.hitBulletColor;

            lifetime = 60f;
            ammoMultiplier = 5f;
            shootEffect = Fx.shootSmall;
            reloadMultiplier = 0.8f;
            width = 6f;
            height = 8f;
            hitEffect = Fx.flakExplosion;
            splashDamage = 30f * 1.5f;
            splashDamageRadius = 20f;
            fragBullets = 6;
            fragBullet = new ExoBasicBulletType(){{
                width = 5f;
                height = 12f;
                speed = 3f;
                damage = 5f;
                addDamageMultiplier(
                        kinetic, 1f
                );
                shrinkY = 1f;
                lifetime = 20f;
                backColor = trailColor = Pal.glassAmmoBack;
                hitColor = frontColor = Pal.glassAmmoFront;
                despawnEffect = Fx.none;
                collidesGround = false;
            }};
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));

        overrideTurretBullet(Blocks.scorch, Items.coal, () -> new ExoBulletType(){{
            speed = 3.35f;
            damage = 17f;
            ammoMultiplier = 3f;
            hitSize = 7f;
            lifetime = 18f;
            pierce = true;
            collidesAir = false;
            statusDuration = 60f * 4;
            shootEffect = Fx.shootSmallFlame;
            hitEffect = Fx.hitFlameSmall;
            despawnEffect = Fx.none;
            status = StatusEffects.burning;
            keepVelocity = false;
            hittable = false;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.thermal, 1f));
        overrideTurretBullet(Blocks.scorch, Items.pyratite, () -> new ExoBulletType(){{
            speed = 4f;
            damage = 60f;
            ammoMultiplier = 6f;
            hitSize = 7f;
            lifetime = 18f;
            pierce = true;
            collidesAir = false;
            statusDuration = 60f * 10;
            shootEffect = Fx.shootPyraFlame;
            hitEffect = Fx.hitFlameSmall;
            despawnEffect = Fx.none;
            status = StatusEffects.burning;
            hittable = false;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.thermal, 1f));

        overrideTurretBullet(Blocks.hail, Items.graphite, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 20f;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 11f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 33f;

            hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
            frontColor = Pal.graphiteAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f,ExoDamageTypes.kinetic, 0.2f));
        overrideTurretBullet(Blocks.hail, Items.silicon, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 20f;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 11f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 33f;
            reloadMultiplier = 1.2f;
            ammoMultiplier = 3f;
            homingPower = 0.08f;
            homingRange = 50f;

            trailLength = 7;
            trailWidth = 3f;

            hitColor = backColor = trailColor = Pal.siliconAmmoBack;
            frontColor = Pal.siliconAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.hail, Items.pyratite, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 25f;
            hitEffect = Fx.blastExplosion;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 13f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 45f;
            status = StatusEffects.burning;
            statusDuration = 60f * 12f;
            frontColor = trailColor = hitColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            makeFire = true;
            trailEffect = Fx.incendTrail;
            ammoMultiplier = 4f;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f,ExoDamageTypes.thermal, 0.2f));

        overrideTurretBullet(Blocks.swarmer, Items.blastCompound, () -> new ExoMissileBulletType(){{
            speed = 3.7f;
            damage = 10f;
            width = 8f;
            height = 8f;
            shrinkY = 0f;
            splashDamageRadius = 30f;
            splashDamage = 30f * 1.5f;
            ammoMultiplier = 5f;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;

            status = StatusEffects.blasted;
            statusDuration = 60f;

            hitColor = backColor = trailColor = Pal.blastAmmoBack;
            frontColor = Pal.blastAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.swarmer, Items.pyratite, () -> new ExoMissileBulletType(){{
            speed = 3.7f;
            damage = 12f;
            frontColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            width = 7f;
            height = 8f;
            shrinkY = 0f;
            homingPower = 0.08f;
            splashDamageRadius = 20f;
            splashDamage = 30f * 1.5f;
            makeFire = true;
            ammoMultiplier = 5f;
            hitEffect = Fx.blastExplosion;
            status = StatusEffects.burning;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f, ExoDamageTypes.thermal, 0.2f));
        overrideTurretBullet(Blocks.swarmer, Items.surgeAlloy, () -> new ExoMissileBulletType(){{
            speed = 3.7f;
            damage = 18f;
            width = 8f;
            height = 8f;
            shrinkY = 0f;
            splashDamageRadius = 25f;
            splashDamage = 25f * 1.4f;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;
            ammoMultiplier = 4f;
            lightningDamage = 10;
            lightning = 2;
            lightningLength = 10;

            hitColor = backColor = trailColor = Pal.surgeAmmoBack;
            frontColor = Pal.surgeAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f,ExoDamageTypes.energy, 0.2f));

        overrideTurretBullet(Blocks.salvo, Items.copper, () -> new ExoBasicBulletType(){{
            speed = 2.5f;
            damage = 11f;
            width = 7f;
            height = 9f;
            lifetime = 60f;
            ammoMultiplier = 2;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.copperAmmoBack;
            frontColor = Pal.copperAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.salvo, Items.graphite, () -> new ExoBasicBulletType(){{
            speed = 3.5f;
            damage = 20f;
            width = 9f;
            height = 12f;
            ammoMultiplier = 4;
            lifetime = 60f;

            rangeChange = 4f * 8f;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
            frontColor = Pal.graphiteAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.salvo, Items.pyratite, () -> new ExoBasicBulletType(){{
            speed = 3.2f;
            damage = 18f;
            width = 10f;
            height = 12f;
            frontColor = hitColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            status = StatusEffects.burning;
            hitEffect = new MultiEffect(Fx.hitBulletColor, Fx.fireHit);

            ammoMultiplier = 5;

            splashDamage = 12f;
            splashDamageRadius = 22f;

            makeFire = true;
            lifetime = 60f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 0.6f,ExoDamageTypes.explosive, 0.2f ,ExoDamageTypes.thermal, 0.2f));
        overrideTurretBullet(Blocks.salvo, Items.silicon, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 15f;
            sprite = "bullet";
            width = 8f;
            height = 10f;
            homingPower = 0.2f;
            reloadMultiplier = 1.5f;
            ammoMultiplier = 5;
            lifetime = 60f;

            trailLength = 5;
            trailWidth = 1.5f;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.siliconAmmoBack;
            frontColor = Pal.siliconAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.salvo, Items.thorium, () -> new ExoBasicBulletType(){{
            speed = 4f;
            damage = 29f;
            sprite = "bullet";
            width = 8f;
            height = 13f;
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            ammoMultiplier = 4;
            lifetime = 60f;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            backColor = hitColor = trailColor = Pal.thoriumAmmoBack;
            frontColor = Pal.thoriumAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));

        overrideTurretBullet(Blocks.fuse, Items.titanium, () -> new ExoShrapnelBulletType(){{
            length = 100;
            damage = 66f;
            ammoMultiplier = 4f;
            width = 17f;
            reloadMultiplier = 1.3f;

        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.pierce, 1f));
        overrideTurretBullet(Blocks.fuse, Items.thorium, () -> new ExoShrapnelBulletType(){{
            length = 100;
            damage = 105f;
            ammoMultiplier = 5f;
            toColor = Pal.thoriumPink;
            shootEffect = smokeEffect = Fx.thoriumShoot;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.pierce, 1f));

        overrideTurretBullet(Blocks.ripple, Items.graphite, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 20f;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 11f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 33f;

            backColor = hitColor = trailColor = Pal.graphiteAmmoBack;
            frontColor = Pal.graphiteAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.ripple, Items.silicon, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 20f;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 11f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 33f;
            reloadMultiplier = 1.2f;
            ammoMultiplier = 3f;
            homingPower = 0.08f;
            homingRange = 50f;

            trailLength = 9;
            trailWidth = 3.1f;

            despawnEffect = Fx.hitBulletColor;
            backColor = hitColor = trailColor = Pal.siliconAmmoBack;
            frontColor = Pal.siliconAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.ripple, Items.pyratite, () -> new ExoArtilleryBulletType(){{
            speed = 3f;
            damage = 24f;
            hitEffect = Fx.blastExplosion;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 13f;
            collidesTiles = false;
            splashDamageRadius = 25f * 0.75f;
            splashDamage = 45f;
            status = StatusEffects.burning;
            statusDuration = 60f * 12f;
            frontColor = Pal.lightishOrange;
            backColor = hitColor = Pal.lightOrange;
            makeFire = true;
            trailEffect = Fx.incendTrail;
            ammoMultiplier = 4f;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f ,ExoDamageTypes.thermal, 0.2f));
        overrideTurretBullet(Blocks.ripple, Items.blastCompound, () -> new ExoArtilleryBulletType(){{
            speed = 2f;
            damage = 20f;
            hitEffect = Fx.blastExplosion;
            knockback = 0.8f;
            lifetime = 80f;
            width = height = 14f;
            collidesTiles = false;
            ammoMultiplier = 4f;
            splashDamageRadius = 45f * 0.75f;
            splashDamage = 55f;

            status = StatusEffects.blasted;

            despawnEffect = Fx.hitBulletColor;
            backColor = hitColor = trailColor = Pal.blastAmmoBack;
            frontColor = Pal.blastAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.ripple, Items.plastanium, () -> new ExoArtilleryBulletType(){{
            speed = 3.4f;
            damage = 20f;
            hitEffect = Fx.plasticExplosion;
            knockback = 1f;
            lifetime = 80f;
            width = height = 13f;
            collidesTiles = false;
            splashDamageRadius = 35f * 0.75f;
            splashDamage = 45f;
            fragBullet = new ExoBasicBulletType(){{
                speed = 2.5f;
                damage = 10f;
                sprite = "bullet";
                addDamageMultiplier(
                        kinetic, 1f
                );
                width = 10f;
                height = 12f;
                shrinkY = 1f;
                lifetime = 15f;
                backColor = Pal.plastaniumBack;
                frontColor = Pal.plastaniumFront;
                despawnEffect = Fx.none;
                collidesAir = false;
            }};
            fragBullets = 10;
            backColor = Pal.plastaniumBack;
            frontColor = Pal.plastaniumFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));

        overrideTurretBullet(Blocks.cyclone, Items.metaglass, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 6f;
            ammoMultiplier = 2f;
            shootEffect = Fx.shootSmall;
            reloadMultiplier = 0.8f;
            width = 6f;
            height = 11f;
            hitEffect = Fx.flakExplosion;
            splashDamage = 45f;
            splashDamageRadius = 25f;
            fragBullet = new ExoBasicBulletType(){{
                speed = 3f;
                damage = 12f;
                sprite = "bullet";
                addDamageMultiplier(
                        kinetic, 1f
                );
                width = 5f;
                height = 12f;
                shrinkY = 1f;
                lifetime = 20f;
                backColor = Pal.gray;
                frontColor = Color.white;
                despawnEffect = Fx.none;
            }};
            fragBullets = 4;
            explodeRange = 20f;
            collidesGround = true;

            backColor = hitColor = trailColor = Pal.glassAmmoBack;
            frontColor = Pal.glassAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.cyclone, Items.blastCompound, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 8f;
            shootEffect = Fx.shootBig;
            ammoMultiplier = 5f;
            splashDamage = 45f;
            splashDamageRadius = 60f;
            collidesGround = true;

            status = StatusEffects.blasted;
            statusDuration = 60f;

            backColor = hitColor = trailColor = Pal.blastAmmoBack;
            frontColor = Pal.blastAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.cyclone, Items.plastanium, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 8f;
            ammoMultiplier = 4f;
            splashDamageRadius = 40f;
            splashDamage = 37.5f;
            fragBullet = new ExoBasicBulletType(){{
                speed = 3f;
                damage = 12f;
                sprite = "bullet";
                addDamageMultiplier(
                        kinetic, 1f
                );
                width = 10f;
                height = 12f;
                shrinkY = 1f;
                lifetime = 15f;
                backColor = Pal.plastaniumBack;
                frontColor = Pal.plastaniumFront;
                despawnEffect = Fx.none;
            }};
            fragBullets = 6;
            hitEffect = Fx.plasticExplosion;
            frontColor = Pal.plastaniumFront;
            backColor = Pal.plastaniumBack;
            shootEffect = Fx.shootBig;
            collidesGround = true;
            explodeRange = 20f;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f));
        overrideTurretBullet(Blocks.cyclone, Items.surgeAlloy, () -> new ExoFlakBulletType(){{
            speed = 4.5f;
            damage = 13f;
            ammoMultiplier = 5f;
            splashDamage = 50f * 1.5f;
            splashDamageRadius = 38f;
            lightning = 2;
            lightningLength = 7;
            shootEffect = Fx.shootBig;
            collidesGround = true;
            explodeRange = 20f;

            backColor = hitColor = trailColor = Pal.surgeAmmoBack;
            frontColor = Pal.surgeAmmoFront;
            despawnEffect = Fx.hitBulletColor;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.8f,ExoDamageTypes.energy, 0.2f));

        overrideTurretBullet(Blocks.foreshadow, Items.surgeAlloy, () -> new ExoRailBulletType(){{
            shootEffect = Fx.instShoot;
            hitEffect = Fx.instHit;
            pierceEffect = Fx.railHit;
            smokeEffect = Fx.smokeCloud;
            pointEffect = Fx.instTrail;
            despawnEffect = Fx.instBomb;
            pointEffectSpace = 20f;
            damage = 1350;
            buildingDamageMultiplier = 0.2f;
            maxDamageFraction = 0.6f;
            pierceDamageFactor = 1f;
            length = 500;
            hitShake = 6f;
            ammoMultiplier = 1f;
        }}, bullet -> bullet.addDamageMultiplier(pierce, 0.8f,ExoDamageTypes.energy, 0.2f));
        overrideTurretBullet(Blocks.foreshadow, Items.thorium, () -> new ExoRailBulletType(){{
            shootEffect = new MultiEffect(Fx.instShoot, ExoShootFx.HaborymShootColor);
            hitEffect = Fx.instHit;
            pierceEffect = Fx.railHit;
            smokeEffect = Fx.smokeCloud;
            pointEffect = Fx.instTrail;
            despawnEffect = Fx.instBomb;
            hitColor = ExoPal.indigoFront;
            pointEffectSpace = 20f;
            damage = 350;
            reloadMultiplier = 5;
            buildingDamageMultiplier = 0.2f;
            maxDamageFraction = 0.6f;
            pierceDamageFactor = 1f;
            length = 500;
            hitShake = 6f;
            ammoMultiplier = 1f;
        }}, bullet -> bullet.addDamageMultiplier(pierce, 0.6f, radiation, 0.4f));

        overrideTurretBullet(Blocks.spectre, Items.graphite, () -> new ExoBasicBulletType(){{
            speed = 7.5f;
            damage = 50f;
            hitSize = 4.8f;
            width = 15f;
            height = 21f;
            shootEffect = Fx.shootBig;
            ammoMultiplier = 4;
            reloadMultiplier = 1.7f;
            knockback = 0.3f;

            hitEffect = despawnEffect = Fx.hitBulletColor;
            hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
            frontColor = Pal.graphiteAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.spectre, Items.thorium, () -> new ExoBasicBulletType(){{
            speed = 8f;
            damage = 80f;
            hitSize = 5;
            width = 16f;
            height = 23f;
            shootEffect = Fx.shootBig;
            pierceCap = 2;
            pierceBuilding = true;
            knockback = 0.7f;

            backColor = hitColor = trailColor = Pal.thoriumAmmoBack;
            frontColor = Pal.thoriumAmmoFront;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 0.7f, pierce, 0.3f));
        overrideTurretBullet(Blocks.spectre, Items.pyratite, () -> new ExoBasicBulletType(){{
            speed = 7f;
            damage = 70f;
            hitSize = 5;
            width = 16f;
            height = 21f;
            frontColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            status = StatusEffects.burning;
            hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
            shootEffect = Fx.shootBig;
            makeFire = true;
            pierceCap = 2;
            pierceBuilding = true;
            knockback = 0.6f;
            ammoMultiplier = 3;
            splashDamage = 20f;
            splashDamageRadius = 25f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 0.6f,ExoDamageTypes.explosive, 0.2f ,ExoDamageTypes.thermal, 0.2f));

        overrideTurretBullet(Blocks.lancer, () -> new ExoLaserBulletType(){{
            colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
            //TODO merge
            chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);

            buildingDamageMultiplier = 0.25f;
            hitEffect = Fx.hitLancer;
            hitSize = 4;
            lifetime = 16f;
            drawSize = 400f;
            collidesAir = false;
            length = 173f;
            ammoMultiplier = 1f;
            pierceCap = 4;
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.energy, 1f));
        overrideTurretBullet(Blocks.arc, () -> new ExoLightningBulletType(){{
            damage = 20;
            lightningLength = 25;
            collidesAir = false;
            ammoMultiplier = 1f;

            //for visual stats only.
            buildingDamageMultiplier = 0.25f;

            lightningType = new BulletType(0.0001f, 0f){{
                lifetime = Fx.lightning.lifetime;
                hitEffect = Fx.hitLancer;
                despawnEffect = Fx.none;
                status = StatusEffects.shocked;
                statusDuration = 10f;
                hittable = false;
                lightColor = Color.white;
                collidesAir = false;
                buildingDamageMultiplier = 0.25f;
            }};
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.energy, 1f));
        overrideTurretBullet(Blocks.meltdown, () -> new ExoContinuousLaserBulletType(){{
            damage = 78f;
            length = 200f;
            hitEffect = Fx.hitMeltdown;
            hitColor = Pal.meltdownHit;
            status = StatusEffects.melting;
            drawSize = 420f;
            timescaleDamage = true;

            incendChance = 0.4f;
            incendSpread = 5f;
            incendAmount = 1;
            ammoMultiplier = 1f;
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.thermal, 1f));

        //test for Unit Display (gamma)
        //BulletType bullet = new ExoFlakBulletType();
        //bullet.damage = 200;
        //((TypedBulletType) bullet).addDamageMultiplier(ExoDamageTypes.explosive, 5f, ExoDamageTypes.pierce, 2.5f);
        //UnitTypes.gamma.weapons.each(weapon -> weapon.bullet = bullet);
    }

    /**
     * @param block the block that to replace. should be an item turret. else do nothing
     * @param toReplace the unlock-able content key. or null if not necessary.
     * @param bullet new bullet type that are created.
     * @param modifier extra config as a TypedBulletType (damage type config)
     */
    public static void overrideTurretBullet(Block block, Object toReplace, Prov<? extends BulletType> bullet, Cons<TypedBulletType> modifier){
        BulletType type = bullet.get();
        if (type instanceof TypedBulletType typedBulletType) modifier.get(typedBulletType);
        if (block instanceof ItemTurret itemTurret && toReplace instanceof Item item) itemTurret.ammoTypes.put(item, type);
        if (block instanceof LiquidTurret liquidTurret && toReplace instanceof Liquid liquid) liquidTurret.ammoTypes.put(liquid, type);
        if (block instanceof PowerTurret powerTurret) powerTurret.shootType = type;
        if (block instanceof ContinuousTurret continuousTurret) continuousTurret.shootType = type;
        if (block instanceof ContinuousLiquidTurret continuousLiquidTurret && toReplace instanceof Liquid liquid) continuousLiquidTurret.ammoTypes.put(liquid, type);
    }

    public static void overrideTurretBullet(Block block, Prov<? extends BulletType> bullet, Cons<TypedBulletType> modifier){
        overrideTurretBullet(block, null, bullet, modifier);
    }
}
