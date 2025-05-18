package exogenesis.content;

import exogenesis.type.DamageType;

public class ExoDamageTypes {
    public static DamageType kinetic, explosive, pierce, energy, thermal, cryogenic, radiation, graviton;

    public static void load(){
        kinetic = new DamageType("kinetic");
        explosive = new DamageType("explosive");
        pierce = new DamageType("pierce");
        energy = new DamageType("energy");
        thermal = new DamageType("thermal");
        cryogenic = new DamageType("cryogenic");
        radiation = new DamageType("radiation");
    }
}
