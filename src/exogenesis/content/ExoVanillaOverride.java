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
        overrideTurretBullet(Blocks.duo, Items.beryllium, () -> new ExoBasicBulletType(){{
            speed = 5f;
            damage = 20f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 2f));

        overrideTurretBullet(Blocks.lancer, () -> new ExoLaserBulletType(){{
            damage = 50f;
            length = 200f;
        }}, bullet -> bullet.addDamageMultiplier(ExoDamageTypes.kinetic, 0.5f, ExoDamageTypes.energy, 3f));

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
