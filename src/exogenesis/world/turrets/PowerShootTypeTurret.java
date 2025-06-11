package exogenesis.world.turrets;

import arc.Core;
import arc.Graphics;
import arc.math.Mathf;
import arc.scene.ui.layout.Collapser;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.ArcRuntimeException;
import arc.util.Strings;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exogenesis.type.bullet.TypedBulletType;
import mindustry.content.Fx;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Icon;
import mindustry.gen.Sounds;
import mindustry.ui.Styles;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

import static exogenesis.content.ExoStatValues.buildSharedBulletTypeStat;
import static mindustry.Vars.player;

public class PowerShootTypeTurret extends PowerTurret {
    public Seq<BulletType> shootTypes = new Seq<>();
    public Seq<String> shootTypeNames = new Seq<>();

    public PowerShootTypeTurret(String name) {
        super(name);

        saveConfig = true;
        copyConfig = true;

        config(Integer.class, (PowerShootTypeTurretBuild e, Integer idx) -> {
            e.current = idx;
        });
    }

    @Override
    public void init() {
        super.init();

        if (shootTypes.size != shootTypeNames.size) throw new ArcRuntimeException("Mismatch between shoot types & type name in :" + name);
        if (shootTypes.isEmpty()) throw new ArcRuntimeException("Empty shoot types in :" + name);
    }

    public void ammo(Object... objects) {
        for (int i = 0; i < objects.length; i += 2) {
            if (objects[i] instanceof BulletType bulletType && objects[i + 1] instanceof String stName) {
                shootTypes.add(bulletType);
                shootTypeNames.add(stName);
            }
        }
    }

    public class PowerShootTypeTurretBuild extends PowerTurretBuild {
        public int current;

        @Override
        public Graphics.Cursor getCursor(){
            return interactable(player.team()) ? Graphics.Cursor.SystemCursor.hand : Graphics.Cursor.SystemCursor.arrow;
        }

        @Override
        public void tapped(){
            super.tapped();
            Fx.placeBlock.at(this, size);
            Sounds.click.at(this);
            configure((current + 1) % shootTypes.size);
        }

        @Override
        public Integer config() {
            return current;
        }

        @Override
        public void updateTile() {
            current = Mathf.clamp(current, 0, shootTypes.size);
            super.updateTile();
        }

        @Override
        public BulletType useAmmo() {
            return shootTypes.get(current);
        }

        @Override
        public BulletType peekAmmo() {
            return shootTypes.get(current);
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            drawPlaceText(getCurrentName(), tileX(), tileY(), true);
        }

        public String getCurrentName() {
            String key = shootTypeNames.get(current);
            return Core.bundle.get("bullet." + name + "-" + key, key);
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.i(current);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            current = read.i();
        }
    }
}
