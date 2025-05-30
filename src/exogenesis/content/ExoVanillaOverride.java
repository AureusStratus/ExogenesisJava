package exogenesis.content;

import exogenesis.type.bullet.TypedBulletType;
import exogenesis.type.bullet.vanilla.ExoContinuousFlameBulletType;
import exogenesis.type.bullet.vanilla.ExoContinuousLaserBulletType;
import exogenesis.type.bullet.vanilla.ExoFlakBulletType;
import exogenesis.type.bullet.vanilla.ExoLaserBulletType;
import mindustry.content.Blocks;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class ExoVanillaOverride {
    public static void load(){
        //test for PowerTurret display (lancer)
        BulletType laser = new ExoLaserBulletType();
        laser.damage = 500f;
        ((TypedBulletType) laser).addDamageMultiplier(ExoDamageTypes.kinetic, 1.2f, ExoDamageTypes.energy, 0.8f, ExoDamageTypes.graviton, 0.5f);
        ((PowerTurret) Blocks.lancer).shootType = laser;

        //test for ContinuousLiquidTurret display (sublimate)
        BulletType flame = new ExoContinuousFlameBulletType();
        flame.damage = 1000f / 12f;
        ((TypedBulletType) flame).addDamageMultiplier(ExoDamageTypes.kinetic, 2f, ExoDamageTypes.energy, 0.3f, ExoDamageTypes.thermal, 5f);
        ((ContinuousLiquidTurret)Blocks.sublimate).ammoTypes.put(Liquids.cyanogen, flame);

        //test for ContinuousTurret display (meltdown)
        BulletType beam = new ExoContinuousLaserBulletType();
        beam.damage = 5000f / 12f;
        ((TypedBulletType) beam).addDamageMultiplier(ExoDamageTypes.energy, 2f, ExoDamageTypes.cryogenic, 4f);
        ((LaserTurret)Blocks.meltdown).shootType = beam;

        //test for Unit Display (gamma)
        BulletType bullet = new ExoFlakBulletType();
        bullet.damage = 200;
        ((TypedBulletType) bullet).addDamageMultiplier(ExoDamageTypes.explosive, 5f, ExoDamageTypes.pierce, 2.5f);
        UnitTypes.gamma.weapons.each(weapon -> weapon.bullet = bullet);
    }
}
