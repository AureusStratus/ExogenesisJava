package exogenesis.content;

import arc.func.Cons;
import arc.func.Prov;
import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.bullet.vanilla.*;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BulletType;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;

public class ExoVanillaOverride {
    public static void load(){
        overrideTurretBullet(Blocks.duo, Items.copper, () -> new ExoBasicBulletType(){{
            speed = 2.5f;
            damage = 8f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.duo, Items.graphite, () -> new ExoBasicBulletType(){{
            speed = 3.5f;
            damage = 19f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));
        overrideTurretBullet(Blocks.duo, Items.silicon, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 12f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 1f));

        overrideTurretBullet(Blocks.scatter, Items.scrap, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 3f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.scatter, Items.lead, () -> new ExoFlakBulletType(){{
            speed = 4.2f;
            damage = 3f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.scatter, Items.metaglass, () -> new ExoFlakBulletType(){{
            speed = 4f;
            damage = 3f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));

        overrideTurretBullet(Blocks.scorch, Items.coal, () -> new ExoBulletType(){{
            speed = 3.35f;
            damage = 17f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.thermal, 1f));
        overrideTurretBullet(Blocks.scorch, Items.pyratite, () -> new ExoBulletType(){{
            speed = 4f;
            damage = 60f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.thermal, 1f));

        overrideTurretBullet(Blocks.hail, Items.graphite, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 20f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f,ExoDamageTypes.kinetic, 0.2f));
        overrideTurretBullet(Blocks.hail, Items.silicon, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 20f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 0.5f,ExoDamageTypes.kinetic, 0.5f));
        overrideTurretBullet(Blocks.hail, Items.pyratite, () -> new ExoBasicBulletType(){{
            speed = 3f;
            damage = 25f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.explosive, 1f,ExoDamageTypes.thermal, 0.2f));

        overrideTurretBullet(Blocks.lancer, () -> new ExoLaserBulletType(){{
            damage = 150f;
            length = 173;
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.energy, 1f));
        overrideTurretBullet(Blocks.arc, () -> new ExoLightningBulletType(){{
            damage = 20f;
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.energy, 1f));
        overrideTurretBullet(Blocks.meltdown, () -> new ExoContinuousLaserBulletType(){{
            damage = 78f;
            length = 200f;
        }}, bullet -> bullet.addDamageMultiplier( ExoDamageTypes.thermal, 1f));

        //test for Unit Display (gamma)
        BulletType bullet = new ExoFlakBulletType();
        bullet.damage = 200;
        ((TypedBulletType) bullet).addDamageMultiplier(ExoDamageTypes.explosive, 5f, ExoDamageTypes.pierce, 2.5f);
        UnitTypes.gamma.weapons.each(weapon -> weapon.bullet = bullet);
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
