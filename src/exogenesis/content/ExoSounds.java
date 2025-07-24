package exogenesis.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import arc.struct.Seq;
import mindustry.Vars;

import java.lang.reflect.Field;

public class ExoSounds {
    public static Sound
            coolplasmaboom = new Sound(),
    energyRecharge = new Sound(), energyPew = new Sound(),
    heavyEnergyCannon = new Sound(), energyCannon = new Sound(), cannonFire = new Sound(),
    shotgun1 = new Sound(), energyShotgun = new Sound(), rampUpCharge = new Sound(),
    continousWeaponFire = new Sound(), energyBolt = new Sound(), laserPew = new Sound(),
    energyShoot1 = new Sound(), energyShoot2 = new Sound(), energyShoot3 = new Sound(),
    heavyRound = new Sound(), rocketDeploy1 = new Sound(), rocketDeploy = new Sound(),
    heavyShot1 = new Sound(), heavyShot = new Sound(), chargeup = new Sound(),
    red = new Sound(),
    matter = new Sound(),
    thunderstorm = new Sound(),
    jupiterImpact = new Sound(),
    jupiterShoot = new Sound(),
    funnylaserloop = new Sound(),
    bigLaserShoot = new Sound(),
    shockblast = new Sound();
    public static void load(){
                coolplasmaboom = loadSound("coolplasmaboom");
                energyRecharge = loadSound("energy-recharge");
                energyPew = loadSound("energy-pew");
                heavyEnergyCannon = loadSound("heavy-energy-cannon");
                energyCannon = loadSound("energy-cannon");
                cannonFire = loadSound("cannon-fire");
                shotgun1 = loadSound("shotgun1");
                energyShotgun = loadSound("energy-shotgun");
                rampUpCharge = loadSound("rampUp-charge");
                continousWeaponFire = loadSound("continousWeaponFire");
                energyBolt = loadSound("energy-bolt");
                laserPew = loadSound("laser-pew");
                energyShoot1 = loadSound("energy-shoot1");
                energyShoot2 = loadSound("energy-shoot2");
                energyShoot3 = loadSound("energy-shoot3");
                heavyRound = loadSound("heavy-round");
                rocketDeploy1 = loadSound("rocket-deploy1");
                rocketDeploy = loadSound("rocket-deploy");
                heavyShot1 = loadSound("heavy-shot1");
                heavyShot = loadSound("heavy-shot");
                chargeup = loadSound("chargeup");
                red = loadSound("red");
                matter = loadSound("matter");
                thunderstorm = loadSound("thunderstorm");
                jupiterImpact = loadSound("jupiterImpact");
                jupiterShoot = loadSound("jupiterShoot");
                funnylaserloop = loadSound("funnylaserloop");
                bigLaserShoot = loadSound("bigLaserShoot");
                shockblast = loadSound("shockblast");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless){
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        }else{
            return new Sound();
        }
    }
}