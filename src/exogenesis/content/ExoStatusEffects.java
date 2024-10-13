package exogenesis.content;

import arc.Events;
import arc.graphics.*;
import mindustry.game.EventType;
import mindustry.type.*;
import exogenesis.graphics.ExoPal;
import mindustry.entities.effect.*;

import static mindustry.Vars.*;
import static mindustry.content.StatusEffects.sapped;

public class ExoStatusEffects{
    public static StatusEffect toxin1, toxin2, toxin3, superBlasted, RheaBuff, energyZapped, LetoBuff;
    public static void load(){
        LetoBuff = new StatusEffect("leto-buff"){{
            color = ExoPal.letoColor;
            damageMultiplier = 1.15f;
            effectChance = 0.07f;
            effect = new WaveEffect(){{
                colorFrom = ExoPal.letoColorLight;
                colorTo = ExoPal.letoColor;
                sides = 4;
                sizeFrom = 2;
                sizeTo = 0f;
                lifetime = 9f;
                strokeTo = 1;
                strokeFrom = 0f;
            }};
        }};
        RheaBuff = new StatusEffect("rhea-buff"){{
            color = ExoPal.erekirPink;
            reloadMultiplier = 1.25f;
            damage = -0.2f;
            effectChance = 0.07f;
            effect = new WaveEffect(){{
                colorFrom = Color.valueOf("ffcbdd");
                colorTo = ExoPal.erekirPink;
                sizeFrom = 3;
                sizeTo = 0f;
                lifetime = 15f;
                strokeTo = 1;
                strokeFrom = 0f;
            }};
        }};
        energyZapped = new StatusEffect("energy-zapped"){{
            effectChance = 0.35f;
            damage = 0.25f;
            transitionDamage = 15;
            init(() -> {
                affinity(sapped, (unit, result, time) -> {
                    float pierceFraction = 0.3f;

                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                    if (unit.team == state.rules.waveTeam) {
                        Events.fire(EventType.Trigger.shock);
                    }
                });
                opposite();
            });
            effect = ExoFx.supernovaSpark;
        }};
        superBlasted = new StatusEffect("superblasted"){{
            color = Color.valueOf("e35140");
            healthMultiplier = 0.8f;
            reactive = true;
        }};
        toxin1 = new StatusEffect("toxin1"){{
            effectChance = 0.15f;
            damage = 0.15f;
            speedMultiplier = 0.95f;
            transitionDamage = 5;
            init(() -> {
                affinity(sapped, (unit, result, time) -> {
                    float pierceFraction = 0.3f;

                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                    if (unit.team == state.rules.waveTeam) {
                        Events.fire(EventType.Trigger.shock);
                    }
                });
                opposite();
            });
            effect = ExoFx.toxicified;
        }};
        toxin2 = new StatusEffect("toxin2"){{
            effectChance = 0.15f;
            damage = 0.22f;
            speedMultiplier = 0.90f;
            transitionDamage = 8;
            init(() -> {
                affinity(toxin1, (unit, result, time) -> {
                    float pierceFraction = 0.3f;

                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                    if (unit.team == state.rules.waveTeam) {
                        Events.fire(EventType.Trigger.shock);
                    }
                });
                opposite();
            });
            effect = ExoFx.toxicified;
        }};
        toxin3 = new StatusEffect("toxin3"){{
            effectChance = 0.15f;
            damage = 0.28f;
            speedMultiplier = 0.85f;
            transitionDamage = 12;
            init(() -> {
                affinity(toxin2, (unit, result, time) -> {
                    float pierceFraction = 0.3f;

                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                    if (unit.team == state.rules.waveTeam) {
                        Events.fire(EventType.Trigger.shock);
                    }
                });
                opposite();
            });
            effect = ExoFx.toxicified;
        }};
    }
}

