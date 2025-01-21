package exogenesis.content;
import arc.graphics.*;
import mindustry.content.StatusEffects;
import mindustry.type.*;

public class ExoLiquids {
    public static Liquid coldPlasma, axinian, impurePlasma, urgas, pyrothiul,
            scalvaur, helium, krypton, ichorium;

    public static void load(){
        coldPlasma = new Liquid("cold-plasma", Color.valueOf("62b4ea")){{
            effect = StatusEffects.freezing;
            boilPoint = 0.5f;
            coolant = false;
            gas = true;
            gasColor = Color.grays(0.9f);
        }};
        ichorium = new Liquid("ichorium", Color.valueOf("62e6ff")){{
            boilPoint = 0.5f;
            coolant = true;
            heatCapacity = 0.7f;
            capPuddles = false;
        }};

        helium = new Liquid("helium", Color.valueOf("a0b0c8")){{
            boilPoint = 0.5f;
            gas = true;
            coolant = false;
            gasColor = Color.grays(0.9f);
        }};
        impurePlasma = new Liquid("impure-plasma", Color.valueOf("8cdf64")){{
            effect = StatusEffects.corroded;
            boilPoint = 0.5f;
            gas = true;
            coolant = false;
            gasColor = Color.grays(0.9f);
        }};
        axinian = new Liquid("axinian", Color.valueOf("2250ff")){{
            effect = StatusEffects.freezing;
            boilPoint = 0.5f;
            gas = true;
            coolant = false;
            gasColor = Color.grays(0.9f);
        }};
    }
}
