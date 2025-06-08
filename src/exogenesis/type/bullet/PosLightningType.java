package exogenesis.type.bullet;

import exogenesis.type.bullet.vanilla.ExoBulletType;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.gen.*;
import exogenesis.util.feature.PositionLightning;

public class PosLightningType extends ExoBulletType {
	public int boltNum = 2;
	public float hitEffectRotation = 12f;
	
	public PosLightningType(float damage){
		this.damage = damage;
		speed = 0.0001f;
		scaleLife = true;
		hitShake = 2f;
		hitSound = Sounds.spark;
		absorbable = keepVelocity = false;
		instantDisappear = true;
		collides = false;
		collidesAir = collidesGround = true;
		lightningDamage = damage;
		hitEffect = shootEffect = smokeEffect = Fx.none;
		despawnEffect = Fx.none;
	}

	@Override
	public void init(){
		super.init();
		drawSize = Math.max(drawSize, maxRange * 2);
		if(smokeEffect == Fx.none)smokeEffect = Fx.shootBigSmoke;
	}
	
	public float range(){
		return maxRange;
	}
	
	@Override
	public void init(Bullet b){
		float length = b.lifetime * range() / lifetime;
		
		Healthc target = Damage.linecast(b, b.x, b.y, b.rotation(), length + 4f);
		b.data = target;
		
		if(target instanceof Hitboxc){
			Hitboxc hit = (Hitboxc)target;
			hit.collision(b, hit.x(), hit.y());
			b.collision(hit, hit.x(), hit.y());
		}else if(target instanceof Building){
			Building tile = (Building)target;
			if(tile.collide(b)){
				tile.collision(b);
				hit(b, tile.x, tile.y);
			}
		}
		
		PositionLightning.createLength(b, b.team, b, length, b.rotation(), lightningColor, true, 0, 0, PositionLightning.WIDTH, boltNum, p -> {
			hitEffect.at(p.getX(), p.getY(), hitEffectRotation, hitColor);
			Effect.shake(hitShake, hitShake, p);
		});
		super.init(b);
	}
	
	@Override
	public void despawned(Bullet b){
		despawnEffect.at(b.x, b.y, b.rotation(), lightningColor);
	}
	
	@Override
	public void hit(Bullet b){}
	
	@Override
	public void hit(Bullet b, float x, float y){}
	
	@Override
	public void draw(Bullet b){}
	
	@Override
	public void drawLight(Bullet b){}

}
