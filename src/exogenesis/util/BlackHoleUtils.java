package exogenesis.util;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import blackhole.entities.bullet.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class BlackHoleUtils{
    private static final IntSet collidedBlocks = new IntSet();

    /**
     * Add bullet types you want to be immune to suction to this Seq.
     * This Seq can also be referenced for anything else you do involving suction.
     */
    public static final ObjectSet<Class<?>> immuneBulletTypes = ObjectSet.with(
        ContinuousBulletType.class,
        LaserBulletType.class,
        SapBulletType.class,
        ShrapnelBulletType.class,
        BlackHoleBulletType.class
    );

    /**
     * Add specific bullets you want to be immune to suction to this Seq.
     * This Seq can also be referenced for anything else you do involving suction.
     */
    public static final ObjectSet<BulletType> immuneBullets = new ObjectSet<>();

    /**
     * Add unit types you want to be immune to suction to this Seq.
     * This Seq can also be referenced for anything else you do involving suction.
     */
    public static final ObjectSet<Class<?>> immuneUnitTypes = new ObjectSet<>();

    /**
     * Add unit components you want to be immune to suction to this Seq.
     * This Seq can also be referenced for anything else you do involving suction.
     */
    public static final ObjectSet<Class<?>> immuneUnitComps = ObjectSet.with(
        BlockUnitUnit.class
    );


    /**
     * Add specific units you want to be immune to suction to this Seq.
     * This Seq can also be referenced for anything else you do involving suction.
     */
    public static final ObjectSet<UnitType> immuneUnits = ObjectSet.with(
        UnitTypes.block
    );

    /**
     * Handles the suction and damage dealt by black holes
     *
     * @param team Team of the black hole. Suction & damage affects other teams.
     * @param source What the black hole is updated from. Used to prevent a unit/bullet from affecting itself.
     * @param offsetX x offset from the source's position
     * @param offsetY y offset from the source's position
     * @param damageRadius Radius where units and bullets are damaged
     * @param suctionRadius Radius where units and bullets are sucked towards the black hole
     * @param damage Damage dealt to units
     * @param pierceArmor Damage ignores armor
     * @param buildingDamageMultiplier Damage multiplier against buildings
     * @param bulletDamage Damage dealt to bullets
     * @param repel If true, pushes away instead of pulls in
     * @param force Base amount of force applied to units
     * @param scaledForce Scaled amount of force applied to units. As units get closer to the center, more of scaledForce is added to force.
     * @param bulletForce Base amount of force applied to bullets.
     * @param scaledBulletForce Scaled amount of force applied to bullets. As bullets get closer to the center, more of scaledForce is added to force.
     */
    public static void blackHoleUpdate(
        Team team, Posc source, float offsetX, float offsetY,
        float damageRadius, float suctionRadius,
        float damage, boolean pierceArmor, float buildingDamageMultiplier, float bulletDamage,
        boolean repel, float force, float scaledForce, float bulletForce, float scaledBulletForce
    ){
        float x = source.x() + offsetX, y = source.y() + offsetY;

        if(damage > 0f) completeDamage(team, x, y, damageRadius, damage, buildingDamageMultiplier, pierceArmor);

        Units.nearbyEnemies(team, x - suctionRadius, y - suctionRadius, suctionRadius * 2f, suctionRadius * 2f, unit -> {
            float rad = suctionRadius + unit.hitSize / 2f;
            if(!unit.type.internal && !isUnitImmune(unit) && unit.hittable() && unit.within(x, y, rad) && source != unit){
                Vec2 impulse = Tmp.v1.trns(unit.angleTo(x, y), force + (1f - unit.dst(x, y) / rad) * scaledForce);
                if(repel) impulse.rotate(180f);
                unit.impulseNet(impulse);
            }
        });

        Groups.bullet.intersect(x - suctionRadius, y - suctionRadius, suctionRadius * 2f, suctionRadius * 2f, other -> {
            if(other != null && !isBulletImmune(other.type) && Mathf.within(x, y, other.x, other.y, suctionRadius) && source != other && team != other.team && other.type.speed > 0.01f){
                Vec2 impulse = Tmp.v1.trns(other.angleTo(x, y), bulletForce + (1f - other.dst(x, y) / suctionRadius) * scaledBulletForce);
                if(repel) impulse.rotate(180f);

                //Replicate unit impulseNet
                other.vel().add(impulse);

                if(other.isRemote()){
                    other.move(impulse.x, impulse.y);
                }

                //Damage/absorb bullets
                if(bulletDamage > 0f && other.type.hittable && Mathf.within(x, y, other.x, other.y, damageRadius)){
                    if(other.damage > bulletDamage){
                        other.damage(other.damage - bulletDamage);
                    }else{
                        other.remove();
                    }
                }
            }
        });
    }

    /**
     * Handles the suction and damage dealt by black holes
     *
     * @param team Team of the black hole. Suction & damage affects other teams.
     * @param source What the black hole is updated from. Used to prevent a unit/bullet from affecting itself.
     * @param offsetX x offset from the source's position
     * @param offsetY y offset from the source's position
     * @param damageRadius Radius where units and bullets are damaged
     * @param suctionRadius Radius where units and bullets are sucked towards the black hole
     * @param damage Damage dealt to units
     * @param bulletDamage Damage dealt to bullets
     * @param repel If true, pushes away instead of pulls in
     * @param force Base amount of force applied to units
     * @param scaledForce Scaled amount of force applied to units. As units get closer to the center, more of scaledForce is added to force.
     * @param bulletForce Base amount of force applied to bullets.
     * @param scaledBulletForce Scaled amount of force applied to bullets. As bullets get closer to the center, more of scaledForce is added to force.
     */
    public static void blackHoleUpdate(
        Team team, Posc source, float offsetX, float offsetY,
        float damageRadius, float suctionRadius,
        float damage, float bulletDamage,
        boolean repel, float force, float scaledForce, float bulletForce, float scaledBulletForce
    ){
        blackHoleUpdate(team, source, offsetX, offsetY, damageRadius, suctionRadius, damage, false, 1f, bulletDamage, repel, force, scaledForce, bulletForce, scaledBulletForce);
    }

    /**
     * Handles the suction and damage dealt by black holes
     *
     * @param team Team of the black hole. Suction & damage affects other teams.
     * @param source What the black hole is updated from. Used to prevent a unit/bullet from affecting itself.
     * @param damageRadius Radius where units and bullets are damaged
     * @param suctionRadius Radius where units and bullets are sucked towards the black hole
     * @param damage Damage dealt to units
     * @param pierceArmor Damage ignores armor
     * @param buildingDamageMultiplier Damage multiplier against buildings
     * @param bulletDamage Damage dealt to bullets
     * @param repel If true, pushes away instead of pulls in
     * @param force Base amount of force applied to units
     * @param scaledForce Scaled amount of force applied to units. As units get closer to the center, more of scaledForce is added to force.
     * @param bulletForce Base amount of force applied to bullets.
     * @param scaledBulletForce Scaled amount of force applied to bullets. As bullets get closer to the center, more of scaledForce is added to force.
     */
    public static void blackHoleUpdate(
        Team team, Posc source,
        float damageRadius, float suctionRadius,
        float damage, boolean pierceArmor, float buildingDamageMultiplier, float bulletDamage,
        boolean repel, float force, float scaledForce, float bulletForce, float scaledBulletForce
    ){
        blackHoleUpdate(team, source, 0f, 0f, damageRadius, suctionRadius, damage, pierceArmor, buildingDamageMultiplier, bulletDamage, repel, force, scaledForce, bulletForce, scaledBulletForce);
    }

    /**
     * Handles the suction and damage dealt by black holes
     *
     * @param team Team of the black hole. Suction & damage affects other teams.
     * @param source What the black hole is updated from. Used to prevent a unit/bullet from affecting itself.
     * @param damageRadius Radius where units and bullets are damaged
     * @param suctionRadius Radius where units and bullets are sucked towards the black hole
     * @param damage Damage dealt to units
     * @param bulletDamage Damage dealt to bullets
     * @param repel If true, pushes away instead of pulls in
     * @param force Base amount of force applied to units
     * @param scaledForce Scaled amount of force applied to units. As units get closer to the center, more of scaledForce is added to force.
     * @param bulletForce Base amount of force applied to bullets.
     * @param scaledBulletForce Scaled amount of force applied to bullets. As bullets get closer to the center, more of scaledForce is added to force.
     */
    public static void blackHoleUpdate(
        Team team, Posc source,
        float damageRadius, float suctionRadius,
        float damage, float bulletDamage,
        boolean repel, float force, float scaledForce, float bulletForce, float scaledBulletForce
    ){
        blackHoleUpdate(team, source, 0f, 0f, damageRadius, suctionRadius, damage, bulletDamage, repel, force, scaledForce, bulletForce, scaledBulletForce);
    }

    /** @return Whether the bullet type is immune to suction. */
    public static boolean isBulletImmune(BulletType type){
        return immuneBulletTypes.toSeq().contains(c -> c.isAssignableFrom(type.getClass()))
            || immuneBullets.contains(type);
    }

    /** @return Whether the unit type is immune to suction. */
    public static boolean isUnitImmune(Unit unit){
        return immuneUnitTypes.toSeq().contains(c -> c.isAssignableFrom(unit.type.getClass()))
            || immuneUnitComps.toSeq().contains(c -> c.isAssignableFrom(unit.getClass()))
            || immuneUnits.contains(unit.type);
    }

    public static void completeDamage(Team team, float x, float y, float radius, float damage){
        completeDamage(team, x, y, radius, damage, 1f, false);
    }

    public static void completeDamage(Team team, float x, float y, float radius, float damage, float buildingDamageMultiplier, boolean pierceArmor){
        Units.nearbyEnemies(team, x - radius, y - radius, radius * 2f, radius * 2f, unit -> {
            if(!unit.dead && unit.hittable() && unit.within(x, y, radius + unit.hitSize / 2f)){
                if(pierceArmor){
                    unit.damagePierce(damage);
                }else{
                    unit.damage(damage);
                }
            }
        });

        trueEachBlock(x, y, radius, build -> {
            if(build.team != team && !build.dead && build.block != null){
                if(pierceArmor){
                    build.damagePierce(damage * buildingDamageMultiplier);
                }else{
                    build.damage(damage * buildingDamageMultiplier);
                }
            }
        });
    }

    public static void trueEachBlock(float wx, float wy, float range, Cons<Building> cons){
        collidedBlocks.clear();
        int tx = World.toTile(wx);
        int ty = World.toTile(wy);

        int tileRange = Mathf.floorPositive(range / tilesize);

        for(int x = tx - tileRange - 2; x <= tx + tileRange + 2; x++){
            for(int y = ty - tileRange - 2; y <= ty + tileRange + 2; y++){
                if(Mathf.within(x * tilesize, y * tilesize, wx, wy, range)){
                    Building other = world.build(x, y);
                    if(other != null && !collidedBlocks.contains(other.pos())){
                        cons.get(other);
                        collidedBlocks.add(other.pos());
                    }
                }
            }
        }
    }
}
