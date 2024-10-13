package exogenesis.content;

import arc.Core;
import mindustry.content.Items;
import mindustry.game.Objectives;
import mindustry.type.SectorPreset;

import static exogenesis.content.ExoVanstarBlocks.*;
import static exogenesis.content.ExoItems.*;
import static arc.struct.Seq.with;
import static mindustry.content.TechTree.*;
import static mindustry.content.TechTree.nodeProduce;

public class ExoVanstarTechTree {
    public static void load() {
        ExoPlanets.vanstar.techTree = nodeRoot("exogenesis-vanstar", coreBelief, () -> {
            node(coreBelief);

            node(ductEmpyrean, () -> {
                node(empyreanRouter, () -> {
                    node(empyreanJunction, () -> {
                        node(empyreanSorter);
                    });
                });
            });
            nodeProduce(cobolt, () -> {
                nodeProduce(rustyCopper, () -> {
                    nodeProduce(oltuxium, () -> {
                        nodeProduce(magnetite, () -> {});
                        nodeProduce(empyreanPlating, () -> {});
                    });
                    nodeProduce(quartz, () -> {});
                });
            });
        });
    }

    public static class AtWave implements Objectives.Objective {
        public SectorPreset sector;
        public int wave;

        public AtWave(SectorPreset sector,int wave) {
            this.sector = sector;
            this.wave = wave;
        }

        @Override public boolean complete() {
            return sector.sector.hasSave() && sector.sector.save.getWave() >= wave;
        }

        @Override public String display() {
            return Core.bundle.format("requirement.omaloon-at-wave", wave, sector.localizedName);
        }
    }
}
