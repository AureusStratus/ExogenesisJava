package exogenesis.content;


import mindustry.type.*;

import static exogenesis.content.ExoPlanets.*;

public class ExoSectorPresets {
    public static SectorPreset
            StormFront, canyon, ferricCrator, Fortress, marbleHills, typhoon, castle;

    public static void load(){
        //region Vanstar

        StormFront = new SectorPreset("StormFront", ExoPlanets.vanstar, 665){{
            alwaysUnlocked = true;
            addStartingItems = true;
            captureWave = 10;
            difficulty = 1;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        //endregion
    }
}
