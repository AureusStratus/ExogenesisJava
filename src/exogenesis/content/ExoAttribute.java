package exogenesis.content;
import mindustry.world.meta.Attribute;

public class ExoAttribute {
    public static Attribute
    power,
    erythric,
    rocky,
    ferric;
    public static void load() {
        power = Attribute.add("power");
        erythric = Attribute.add("erythrite");
        rocky = Attribute.add("rocky");
        ferric = Attribute.add("ferric");
    }
}
