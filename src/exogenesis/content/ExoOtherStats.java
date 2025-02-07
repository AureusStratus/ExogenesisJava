package exogenesis.content;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

public class ExoOtherStats {
    public static final Stat
            maxBoostPercent = new Stat("max-boost-percent", StatCat.function),
            increaseWhenShooting = new Stat("increase-when-shooting", StatCat.function),
            decreaseNotShooting = new Stat("decrease-not-shooting", StatCat.function);
}
