package exogenesis.entities.effect;

import arc.graphics.Color;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.pooling.Pools;
import exogenesis.graphics.LightTrail;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.gen.EffectState;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.graphics.Trail;
import mindustry.world.blocks.defense.turrets.BaseTurret;

public class SwirlEffect extends Effect {
    public int length;
    public float width;
    public float minRot;
    public float maxRot;
    public float minDst;
    public float maxDst;
    public float lightOpacity;
    @Nullable
    public Color colorFrom;
    @Nullable
    public Color colorTo;
    public Interp fallterp;
    public Interp spinterp;
    public float spinDirectionOverride;

    public SwirlEffect(float lifetime, float clipsize, Color colorFrom, int length, float width, float minRot, float maxRot, float minDst, float maxDst) {
        this.length = 8;
        this.width = 3.0F;
        this.minRot = 120.0F;
        this.maxRot = 480.0F;
        this.minDst = -1.0F;
        this.maxDst = -1.0F;
        this.lightOpacity = 0.8F;
        this.fallterp = Interp.pow2Out;
        this.spinterp = Interp.pow3Out;
        this.spinDirectionOverride = 0.0F;
        this.lifetime = 90.0F;
        this.followParent = this.rotWithParent = true;
        this.layer = 110.005F;
        this.lifetime = lifetime;
        this.clip = clipsize;
        this.colorFrom = colorFrom;
        this.length = length;
        this.width = width;
        this.minRot = minRot;
        this.maxRot = maxRot;
        this.minDst = minDst;
        this.maxDst = maxDst;
    }

    public SwirlEffect(float lifetime, Color colorFrom, int length, float width, float minRot, float maxRot, float minDst, float maxDst) {
        this(lifetime, 400.0F, colorFrom, length, width, minRot, maxRot, minDst, maxDst);
    }

    public SwirlEffect(float lifetime, int length, float width, float minRot, float maxRot) {
        this(lifetime, Color.black, length, width, minRot, maxRot, -1.0F, -1.0F);
    }

    public SwirlEffect(Color colorFrom) {
        this();
        this.colorFrom = colorFrom;
    }

    public SwirlEffect() {
        this.length = 8;
        this.width = 3.0F;
        this.minRot = 120.0F;
        this.maxRot = 480.0F;
        this.minDst = -1.0F;
        this.maxDst = -1.0F;
        this.lightOpacity = 0.8F;
        this.fallterp = Interp.pow2Out;
        this.spinterp = Interp.pow3Out;
        this.spinDirectionOverride = 0.0F;
        this.lifetime = 90.0F;
        this.followParent = this.rotWithParent = true;
        this.layer = 110.005F;
    }

    public exogenesis.entities.effect.SwirlEffect setInterps(Interp fallterp, Interp spinterp) {
        this.fallterp = fallterp;
        this.spinterp = spinterp;
        return this;
    }

    public exogenesis.entities.effect.SwirlEffect setInterps(Interp interp) {
        return this.setInterps(interp, interp);
    }

    public void render(EffectContainer e) {
        float lifetime = e.lifetime - (float)this.length;
        float dst;
        if (!(this.minDst < 0.0F) && !(this.maxDst < 0.0F)) {
            dst = Mathf.randomSeed((long)e.id, this.minDst, this.maxDst);
        } else {
            dst = Math.abs(e.rotation);
        }

        float l = Mathf.clamp(e.time / lifetime);
        if (this.colorFrom == null && this.colorTo == null) {
            Tmp.c1.set(e.color);
        } else {
            Tmp.c1.set(this.colorFrom == null ? e.color : this.colorFrom).lerp(this.colorTo == null ? e.color : this.colorTo, l);
        }

        float width = Mathf.clamp(e.time / (e.lifetime - (float)this.length)) * this.width;
        float dir = this.spinDirectionOverride != 0.0F ? (float)Mathf.sign(this.spinDirectionOverride) : (float)Mathf.sign(e.rotation);
        float baseRot = Mathf.randomSeed((long)(e.id + 1), 360.0F);
        float addRot = Mathf.randomSeed((long)(e.id + 2), this.minRot, this.maxRot) * dir;
        Trail trail = (Trail)e.data;
        if (!Vars.state.isPaused()) {
            float f = 1.0F - e.time / lifetime;
            if (f > 0.0F) {
                Tmp.v1.trns(baseRot + addRot * this.spinterp.apply(f), Mathf.maxZero(dst * this.fallterp.apply(f))).add(e.x, e.y);
                trail.update(Tmp.v1.x, Tmp.v1.y);
            } else {
                trail.shorten();
            }
        }

        trail.drawCap(Tmp.c1, width);
        if (trail instanceof LightTrail) {
            LightTrail lightTrail = (LightTrail)trail;
            lightTrail.draw(Tmp.c1, width, l);
        } else {
            trail.draw(Tmp.c1, width);
        }

    }

    protected void add(float x, float y, float rotation, Color color, Object data) {
        exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState entity = exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState.create();
        entity.effect = this;
        entity.rotation = this.baseRotation + rotation;
        entity.lifetime = this.lifetime;
        entity.set(x, y);
        entity.color.set(color);
        if (this.followParent && data instanceof Posc) {
            Posc p = (Posc)data;
            entity.parent = p;
            entity.rotWithParent = this.rotWithParent;
        }

        entity.data = this.lightOpacity > 0.0F ? new LightTrail(this.length, this.lightOpacity) : new Trail(this.length);
        entity.add();
    }

    public static class ExogenesisEffectState extends EffectState {
        public ExogenesisEffectState() {
        }

        public static exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState create() {
            return (exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState)Pools.obtain(exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState.class, exogenesis.entities.effect.SwirlEffect.ExogenesisEffectState::new);
        }

        public void add() {
            if (!this.added) {
                this.index__all = Groups.all.addIndex(this);
                this.index__draw = Groups.draw.addIndex(this);
                if (this.parent != null) {
                    this.offsetX = this.x - this.parent.x();
                    this.offsetY = this.y - this.parent.y();
                    if (this.rotWithParent) {
                        Posc var3 = this.parent;
                        if (var3 instanceof Rotc) {
                            Rotc r = (Rotc)var3;
                            this.offsetPos = -r.rotation();
                            this.offsetRot = this.rotation - r.rotation();
                        } else {
                            var3 = this.parent;
                            if (var3 instanceof BaseTurret.BaseTurretBuild) {
                                BaseTurret.BaseTurretBuild build = (BaseTurret.BaseTurretBuild)var3;
                                this.offsetPos = -build.rotation;
                                this.offsetRot = this.rotation - build.rotation;
                            }
                        }
                    }
                }

                this.added = true;
            }

        }

        public void update() {
            if (this.parent != null) {
                label21: {
                    if (this.rotWithParent) {
                        Posc var3 = this.parent;
                        if (var3 instanceof Rotc) {
                            Rotc r = (Rotc)var3;
                            this.x = this.parent.x() + Angles.trnsx(r.rotation() + this.offsetPos, this.offsetX, this.offsetY);
                            this.y = this.parent.y() + Angles.trnsy(r.rotation() + this.offsetPos, this.offsetX, this.offsetY);
                            break label21;
                        }

                        var3 = this.parent;
                        if (var3 instanceof BaseTurret.BaseTurretBuild) {
                            BaseTurret.BaseTurretBuild build = (BaseTurret.BaseTurretBuild)var3;
                            this.x = this.parent.x() + Angles.trnsx(build.rotation + this.offsetPos, this.offsetX, this.offsetY);
                            this.y = this.parent.y() + Angles.trnsy(build.rotation + this.offsetPos, this.offsetX, this.offsetY);
                            break label21;
                        }
                    }

                    this.x = this.parent.x() + this.offsetX;
                    this.y = this.parent.y() + this.offsetY;
                }
            }

            this.time = Math.min(this.time + Time.delta, this.lifetime);
            if (this.time >= this.lifetime) {
                this.remove();
            }

        }
    }
}
