package exogenesis.content;

import arc.graphics.Color;
import arc.util.Time;
import mindustry.content.StatusEffects;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;
import mindustry.type.weather.RainWeather;
import mindustry.world.meta.Attribute;

public class ExoWeathers {
    public static Weather
            heavyRain,
            heavyFog,
            galeStorm;

    public static void load() {
        heavyRain = new RainWeather("heavy-rain"){{
            attrs.set(Attribute.light, -0.5f);
            attrs.set(Attribute.water, 0.4f);
            yspeed = 12f;
            xspeed = 2f;
            padding = 16f;
            density = 400f;
            stroke = 0.85f;
            sizeMin = 10f;
            sizeMax = 40f;
            opacityMultiplier = 0.47f;
            splashTimeScale = 22f;
            status = StatusEffects.wet;
            sound = Sounds.rain;
            soundVol = 0.55f;
        }};
        heavyFog = new ParticleWeather("heavy-fog"){{
            duration = 15f * Time.toMinutes;
            noiseLayers = 6;
            noiseLayerSclM = 0.8f;
            noiseLayerAlphaM = 0.7f;
            noiseLayerSpeedM = 2f;
            noiseLayerSclM = 0.6f;
            baseSpeed = 0.09f;
            color = noiseColor = Color.grays(0.4f);
            noiseScale = 1100f;
            noisePath = "fog";
            drawParticles = false;
            drawNoise = true;
            useWindVector = false;
            xspeed = 1f;
            yspeed = 0.01f;
            attrs.set(Attribute.light, -0.6f);
            attrs.set(Attribute.water, 0.1f);
            opacityMultiplier = 0.47f;
        }};
        galeStorm = new ParticleWeather("gale"){{
            color = Color.grays(0.4f).a(0.3f);
            density = 500f;
            attrs.set(Attribute.light, -0.1f);
            opacityMultiplier = 0.15f;
            force = 0.4f;
            particleRegion = "particle";
            drawNoise = false;
            useWindVector = true;
            sizeMax = 140f;
            sizeMin = 70f;
            minAlpha = 0f;
            maxAlpha = 0.2f;
            baseSpeed = 5.4f;
            attrs.set(Attribute.light, -0.1f);
            sound = Sounds.wind;
            soundVol = 1f;
            duration = 7f * Time.toMinutes;
        }};
    }
}
