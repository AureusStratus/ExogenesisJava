package exogenesis.content;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

public class ExoStats {
    public static final Stat
            damageReduction = new Stat("damage-reduction", StatCat.general),
            powerConsModifier = new Stat("power-cons-modifier", StatCat.function),
            minerBoosModifier = new Stat("miner-boost-modifier", StatCat.function),
            itemConvertList = new Stat("item-convert-list", StatCat.function),
            maxBoostPercent = new Stat("max-boost-percent", StatCat.function),
            maxModules = new Stat("max-modules", StatCat.function),
            increaseWhenShooting = new Stat("increase-when-shooting", StatCat.function),
            decreaseNotShooting = new Stat("decrease-not-shooting", StatCat.function),
            fireRateMax = new Stat("fire-rate-max", StatCat.function),
            overheatCooldown = new Stat("overheat-cooldown", StatCat.function),
            payloadCapacity = new Stat("payload-capacity", StatCat.general),
            gravityRange = new Stat("gravity-gully-range", StatCat.general),
            maxLinks = new Stat("max-build-links", StatCat.function),
            resistance = new Stat("resistance", StatCat.general);
}
