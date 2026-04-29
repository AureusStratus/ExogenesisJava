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
            allowLaunchSchematics = true;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        canyon = new SectorPreset("canyon", ExoPlanets.vanstar, 770){{
            addStartingItems = true;
            captureWave = 20;
            difficulty = 2;
            allowLaunchSchematics = true;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        ferricCrator = new SectorPreset("ferricCrator", ExoPlanets.vanstar, 655){{
            addStartingItems = true;
            captureWave = 35;
            difficulty = 2;
            allowLaunchSchematics = true;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        Fortress = new SectorPreset("Fortress", ExoPlanets.vanstar, 451){{
            addStartingItems = true;
            difficulty = 4;
            allowLaunchSchematics = true;
            overrideLaunchDefaults = true;
        }};
        /*
        marbleHills = new SectorPreset("marbleHills", ExoPlanets.vanstar, 15){{
            addStartingItems = true;
            difficulty = 5;
            captureWave = 40;
            allowLaunchSchematics = true;
            overrideLaunchDefaults = true;
        }};
         */
        //MarbleHills 43
        //typhoon 417
        //castle 375
        //endregion
    }
}
