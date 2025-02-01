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
        canyon = new SectorPreset("canyon", ExoPlanets.vanstar, 431){{
            addStartingItems = true;
            captureWave = 20;
            difficulty = 2;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        ferricCrator = new SectorPreset("ferricCrator", ExoPlanets.vanstar, 408){{
            addStartingItems = true;
            captureWave = 35;
            difficulty = 2;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        Fortress = new SectorPreset("Fortress", ExoPlanets.vanstar, 657){{
            addStartingItems = true;
            difficulty = 4;
            overrideLaunchDefaults = true;
        }};
        //MarbleHills 43
        //typhoon 166
        //castle 622
        //endregion
    }
}
