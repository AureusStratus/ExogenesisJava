package exogenesis.content;
import mindustry.world.meta.Attribute;

public class ExoAttribute {
    public static Attribute
    power,
    erythric,
    ferric;
    public static void load() {
        power = Attribute.add("power");
        erythric = Attribute.add("erythrite");
        ferric = Attribute.add("ferric");
    }
}
