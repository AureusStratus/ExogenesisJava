package exogenesis.world.blocks.unit;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;
import mindustry.type.UnitType;
import mindustry.ui.ReqImage;
import mindustry.ui.Styles;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItemDynamic;
import mindustry.world.consumers.ConsumeLiquidsDynamic;
import mindustry.world.consumers.ConsumePayloadDynamic;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import static mindustry.Vars.state;

public class UnitCrafter extends UnitAssembler {
    public UnitCrafter(String name) {
        super(name);
        configurable = true;
        copyConfig = true;

        config(Integer.class, (UnitCrafterBuild e, Integer value) -> e.currentTier = (value < 0 || value > plans.size - 1)? 0: value);
        config(UnitType.class, (UnitCrafterBuild e, UnitType value) -> {
            if(!configurable) return;
            for (int i = 0; i < plans.size; i++) {
                UnitType unit = plans.get(i).unit;
                if (value == unit){
                    e.currentTier = i;
                    return;
                }
            }
            e.currentTier = 0;
        });

        configClear((UnitCrafterBuild e) -> e.currentTier = 0);
    }

    public ExtendUnitPlan addPlan(UnitType unit, float time){
        ExtendUnitPlan plan = new ExtendUnitPlan(unit, time);
        plans.add(plan);
        return plan;
    }

    public static class ExtendUnitPlan extends AssemblerUnitPlan {
        public ExtendUnitPlan(UnitType unit, float time) {
            super(unit, time, PayloadStack.list());
            item(ItemStack.empty);
            liquid(LiquidStack.empty);
        }

        public ExtendUnitPlan item(ItemStack[] items) {
            this.itemReq = items;
            return this;
        }

        public ExtendUnitPlan liquid(LiquidStack[] liquids) {
            this.liquidReq = liquids;
            return this;
        }

        public ExtendUnitPlan payload(Seq<PayloadStack> payloads) {
            this.requirements = payloads;
            return this;
        }
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.output);

        stats.add(Stat.output, table -> {
            table.row();
            for(var plan : plans){
                table.table(Styles.grayPanel, t -> {

                    if(plan.unit.isBanned()){
                        t.image(Icon.cancel).color(Pal.remove).size(40).pad(10);
                        return;
                    }

                    if(plan.unit.unlockedNow()){
                        t.image(plan.unit.uiIcon).scaling(Scaling.fit).size(40).pad(10f).left().with(i -> StatValues.withTooltip(i, plan.unit));
                        t.table(info -> {
                            info.defaults().left();
                            info.add(plan.unit.localizedName);
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();

                        t.table(req -> {
                            req.add().grow(); //it refuses to go to the right unless I do this. please help.

                            req.table(solid -> {
                                int length = 0;
                                if(plan.itemReq != null){
                                    for(int i = 0; i < plan.itemReq.length; i++){
                                        if(length % 6 == 0){
                                            solid.row();
                                        }
                                        solid.add(StatValues.stack(plan.itemReq[i])).pad(5);
                                        length++;
                                    }
                                }

                                for(int i = 0; i < plan.requirements.size; i++){
                                    if(length % 6 == 0){
                                        solid.row();
                                    }
                                    solid.add(StatValues.stack(plan.requirements.get(i))).pad(5);
                                    length++;
                                }
                            }).right();

                            LiquidStack[] stacks = plan.liquidReq;
                            if(stacks != null){
                                for(int i = 0; i < plan.liquidReq.length; i++){
                                    req.row();

                                    req.add().grow(); //another one.

                                    req.add(StatValues.displayLiquid(stacks[i].liquid, stacks[i].amount * 60f, true)).right();
                                }
                            }
                        }).grow().pad(10f);
                    }else{
                        t.image(Icon.lock).color(Pal.darkerGray).size(40).pad(10);
                    }
                }).growX().pad(5);
                table.row();
            }
        });
    }

    public class UnitCrafterBuild extends UnitAssemblerBuild {
        @Override
        public void checkTier() {}

        @Override
        public void buildConfiguration(Table table) {
            Seq<UnitType> unitTypes = new Seq<>();
            plans.each(plan -> unitTypes.add(plan.unit));
            ItemSelection.buildTable(block, table, unitTypes, () -> plans.get(currentTier).unit, this::configure);
        }

        @Override
        public UnitType config() {
            if (currentTier < 0 || currentTier >= plans.size - 1) return UnitTypes.alpha;
            return plans.get(currentTier).unit;
        }

        @Override
        public void displayConsumption(Table table) {
            if (currentTier < 0 || currentTier >= plans.size - 1) return;
            table.update(() -> {
                table.clear();
                table.left();

                for (Consume cons: consumers){
                    if (!cons.optional || !cons.booster) {
                        if (cons instanceof ConsumeItemDynamic) continue;
                        if (cons instanceof ConsumeLiquidsDynamic) continue;
                        if (cons instanceof ConsumePayloadDynamic) continue;
                        cons.build(this, table);
                    }
                }
                table.row();

                ItemStack[] currentItem = plans.get(currentTier).itemReq;
                LiquidStack[] currentLiquid = plans.get(currentTier).liquidReq;
                PayloadStack[] currentPayload = plans.get(currentTier).requirements.toArray(PayloadStack.class);
                float multiplier = state.rules.unitCost(team);
                table.table(cont -> {
                    int i = 0;
                    if (currentItem != null) {
                        for (ItemStack stack : currentItem) {
                            cont.add(new ReqImage(StatValues.stack(stack.item, Math.round(stack.amount * multiplier)),
                                    () -> items != null && items.has(stack.item, Math.round(stack.amount * multiplier)))).padRight(8).left();
                            if (++i % 4 == 0) cont.row();
                        }
                    }

                    if (currentLiquid != null) {
                        for (LiquidStack stack : currentLiquid) {
                            cont.add(new ReqImage(stack.liquid.uiIcon,
                                    () -> liquids != null && liquids.get(stack.liquid) > 0)).size(Vars.iconMed).padRight(8);
                            if (++i % 4 == 0) cont.row();
                        }
                    }

                    if (currentPayload != null) {
                        for (PayloadStack stack : currentPayload) {
                            cont.add(new ReqImage(StatValues.stack(stack.item, Math.round(stack.amount * multiplier)),
                                    () -> getPayloads() != null && getPayloads().contains(stack.item, Math.round(stack.amount * multiplier)))).padRight(8);
                            if (++i % 4 == 0) cont.row();
                        }
                    }

                });
            });
        }
    }
}
