package exogenesis.content;

import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.bullet.vanilla.*;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class ExoVanillaOverride {
    public static void load(){

        //Item turrets
        //dou
        BulletType item = new ExoBasicBulletType(2.5f, 9);
        item.damage = 9;
        ((TypedBulletType) item).addDamageMultiplier(ExoDamageTypes.kinetic, 1f);
        ((ItemTurret)Blocks.duo).ammoTypes.put(Items.copper, item);
        item = new ExoBasicBulletType(3.5f, 18);
        item.damage = 18;
        ((TypedBulletType) item).addDamageMultiplier(ExoDamageTypes.kinetic, 1f);
        ((ItemTurret)Blocks.duo).ammoTypes.put(Items.graphite, item);
        item = new ExoBasicBulletType(3f, 12);
        item.damage = 12;
        ((TypedBulletType) item).addDamageMultiplier(ExoDamageTypes.kinetic, 1f);
        ((ItemTurret)Blocks.duo).ammoTypes.put(Items.silicon, item);

        //test for PowerTurret display (lancer)
        BulletType laser = new ExoLaserBulletType();
        laser.damage = 140f;
        ((TypedBulletType) laser).addDamageMultiplier(ExoDamageTypes.energy, 1f);
        ((PowerTurret) Blocks.lancer).shootType = laser;
        //Arc
        BulletType power = new ExoLightningBulletType();
        power.damage = 20;
        ((TypedBulletType) power).addDamageMultiplier(ExoDamageTypes.energy, 1f);
        ((PowerTurret) Blocks.arc).shootType = power;


        //test for ContinuousLiquidTurret display (sublimate)
        BulletType flame = new ExoContinuousFlameBulletType();
        flame.damage = 130;
        ((TypedBulletType) flame).addDamageMultiplier(ExoDamageTypes.thermal, 1f);
        ((ContinuousLiquidTurret)Blocks.sublimate).ammoTypes.put(Liquids.cyanogen, flame);
        flame.damage = 60;
        ((TypedBulletType) flame).addDamageMultiplier(ExoDamageTypes.thermal, 1f);
        ((ContinuousLiquidTurret)Blocks.sublimate).ammoTypes.put(Liquids.ozone, flame);

        //test for ContinuousTurret display (meltdown)
        BulletType beam = new ExoContinuousLaserBulletType();
        beam.damage = 78f;
        ((TypedBulletType) beam).addDamageMultiplier(ExoDamageTypes.thermal, 1f);
        ((LaserTurret)Blocks.meltdown).shootType = beam;

        //test for Unit Display (gamma)
        BulletType bullet = new ExoFlakBulletType();
        bullet.damage = 200;
        ((TypedBulletType) bullet).addDamageMultiplier(ExoDamageTypes.explosive, 5f, ExoDamageTypes.pierce, 2.5f);
        UnitTypes.gamma.weapons.each(weapon -> weapon.bullet = bullet);
    }
}
