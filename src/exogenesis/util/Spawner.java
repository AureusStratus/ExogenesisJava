package exogenesis.util;

import arc.Events;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exogenesis.content.ExoFx;
import exogenesis.entities.EntityRegister;
import mindustry.Vars;
import mindustry.ai.types.CommandAI;
import mindustry.audio.SoundLoop;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Units;
import mindustry.entities.units.StatusEntry;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.Trail;
import mindustry.io.TypeIO;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;

import java.nio.FloatBuffer;

import static mindustry.gen.Call.effect;
/*
public class Spawner extends ExoBaseEntity implements Syncc, Timedc, Rotc{
	public Team team = Team.derelict;
	public UnitType type = UnitTypes.alpha;
	public float time = 0, lifetime;
	public float surviveTime, surviveLifetime = 3000f;
	public float rotation;

	public double flagToApply = Double.NaN;

	public StatusEntry statusEntry = new StatusEntry().set(StatusEffects.none, 0);

	public Interval timer = new Interval();

	public float trailProgress = Mathf.random(360);

	public long lastUpdated, updateSpacing;

	public SoundLoop soundLoop;
	public Unit toSpawn;
	public Vec2 commandPos = new Vec2(Float.NaN, Float.NaN);

	public final Seq<Trail> trails = Seq.with(new Trail(30), new Trail(50), new Trail(70));
	public float trailWidth = 3f;

	@Override
	public float clipSize(){
		return drawSize + 500;
	}

	public Spawner init(UnitType type, Team team, Position pos, float rotation, float lifetime){
		this.type = type;
		this.lifetime = lifetime;
		this.rotation = rotation;
		this.team = team;
		this.drawSize = type.hitSize;
		trailWidth = Mathf.clamp(drawSize / 15f, 1.25f, 4f);
		set(pos);

		return this;
	}

	public Spawner setStatus(StatusEffect status, float statusDuration){
		statusEntry.effect = status;
		statusEntry.time = statusDuration;

		return this;
	}

	public Spawner setFlagToApply(double flagToApply){
		this.flagToApply = flagToApply;
		return this;
	}

	public Spawner setFlagToApply(long flagToApply){
		this.flagToApply = Double.longBitsToDouble(flagToApply);
		return this;
	}

	@Override
	public void add(){
		super.add();
		Groups.sync.add(this);

		ExoFx.spawnWave.at(x, y, drawSize * 1.1f, team.color);
	}

	@Override
	public void afterReadAll() {

	}

	@Override
	public void beforeWrite() {

	}

	@Override
	public void remove(){
		super.remove();
		Groups.sync.remove(this);

		if(Vars.net.client()){
			Vars.netClient.addRemovedEntity(id());
		}

		if(soundLoop != null)soundLoop.update(x, y, false);
	}

/*
	public void effect(){
		Effect.shake(type.hitSize / 3f, type.hitSize / 4f, toSpawn);
		ExoSounds.jumpIn.at(toSpawn.x, toSpawn.y);
		if(type.flying){
			ExoFx.jumpTrail.at(toSpawn.x, toSpawn.y, rotation(), team.color, type);
			toSpawn.apply(StatusEffects.slow, ExoFx.jumpTrail.lifetime);
		}else{
			ExoFx.spawn.at(x, y, type.hitSize, team.color);
			Fx.unitSpawn.at(toSpawn.x, toSpawn.y, rotation(), type);
			Time.run(Fx.unitSpawn.lifetime, () -> {
				for(int j = 0; j < 3; j++){
					Time.run(j * 8, () -> Fx.spawn.at(toSpawn));
				}
				ExoFx.spawnGround.at(toSpawn.x, toSpawn.y, type.hitSize / tilesize * 3, team.color);
				ExoFx.circle.at(toSpawn.x, toSpawn.y, type.hitSize * 4, team.color);
			});
		}

	}


	public void dump(){
		toSpawn = type.create(team);
		toSpawn.set(x, y);
		toSpawn.rotation = rotation();
		if(!Double.isNaN(flagToApply)){
			toSpawn.flag(flagToApply);
		}
		if(!Vars.net.client()) toSpawn.add();
		toSpawn.apply(StatusEffects.unmoving, Fx.unitSpawn.lifetime);
		toSpawn.apply(statusEntry.effect, statusEntry.time);
		if(commandPos != null && !commandPos.isNaN()){
			if(toSpawn.isCommandable()){
				toSpawn.command().commandPosition(commandPos);
			}else{
				CommandAI ai = new CommandAI();
				ai.commandPosition(commandPos);
				toSpawn.controller(ai);
			}
		}

		Events.fire(new EventType.UnitCreateEvent(toSpawn, null));
	}

	public boolean canCreate(){
		return Units.canCreate(team, type) || team == Vars.state.rules.waveTeam;
	}

	@Override
	public void draw(){
		if(type.health > 8000 && team != Vars.player.team())NHSounds.alertLoop();

		TextureRegion pointerRegion = NHContent.pointerRegion, arrowRegion = NHContent.arrowRegion;

		Drawf.light(x, y, clipSize() * fout(), team.color, 0.7f);
		Draw.z(Layer.effect - 1f);

		boolean can = canCreate();

		float regSize = ExoFunc.regSize(type);
		Draw.color(can ? team.color : Tmp.c1.set(team.color).lerp(Pal.ammo, Mathf.absin(Time.time * DrawFunc.sinScl, 8f, 0.3f) + 0.1f));

		for(int i = -4; i <= 4; i++){
			if(i == 0) continue;
			Tmp.v1.trns(rotation, i * tilesize * 2);
			float f = (100 - (Time.time - 12.5f * i) % 100) / 100;
			Draw.rect(arrowRegion, x + Tmp.v1.x, y + Tmp.v1.y, arrowRegion.width * (regSize / 2f + Draw.scl) * f, arrowRegion.height * (regSize / 2f + Draw.scl) * f, rotation() - 90);
		}


		if(can)
			DrawFunc.overlayText(Fonts.tech, String.valueOf(Mathf.ceil((lifetime - time) / 60f)), x, y, 0, 0, 0.25f, team.color, false, true);
		else{
			Draw.z(Layer.effect);
			Draw.color(Pal.ammo);

			float s = Mathf.clamp(drawSize / 4f, 12f, 20f);
			Draw.rect(Icon.warning.getRegion(), x, y, s, s);
		}

		Draw.reset();
	}


	@Override
	public void write(Writes write){
		super.write(write);
		write.f(lifetime);
		write.f(time);
		write.f(rotation);
		write.f(surviveTime);
		write.d(flagToApply);
		TypeIO.writeUnitType(write, type);
		TypeIO.writeTeam(write, team);
		TypeIO.writeStatus(write, statusEntry);

		TypeIO.writeVec2(write, commandPos);
	}

	@Override
	public void read(Reads read){
		super.read(read);
		lifetime = read.f();
		time = read.f();
		rotation = read.f();
		surviveTime = read.f();
		flagToApply = read.d();

		type = TypeIO.readUnitType(read);
		team = TypeIO.readTeam(read);
		statusEntry = TypeIO.readStatus(read);

		commandPos = TypeIO.readVec2(read);

		afterRead();
	}

	@Override
	public boolean serialize(){return true;}

	@Override
	public int classId(){
		return EntityRegister.getID(getClass());
	}

	@Override
	public void snapSync(){}

	@Override
	public void snapInterpolation(){}

	@Override
	public void readSync(Reads read){
		x = read.f();
		y = read.f();
		lifetime = read.f();
		time = read.f();
		rotation = read.f();
		surviveTime = read.f();

		type = TypeIO.readUnitType(read);
		team = TypeIO.readTeam(read);
		if(commandPos != null)commandPos = TypeIO.readVec2(read);
		else commandPos = new Vec2(Float.NaN, Float.NaN);

		afterSync();
	}

	@Override
	public void writeSync(Writes write){
		write.f(x);
		write.f(y);
		write.f(lifetime);
		write.f(time);
		write.f(rotation);
		write.f(surviveTime);

		TypeIO.writeUnitType(write, type);
		TypeIO.writeTeam(write, team);
		TypeIO.writeVec2(write, commandPos);
	}

	@Override
	public void readSyncManual(FloatBuffer floatBuffer){

	}

	@Override
	public void writeSyncManual(FloatBuffer floatBuffer){

	}

	@Override
	public void afterSync(){

	}

	@Override
	public void handleSyncHidden(){

	}

	@Override
	public void interpolate(){

	}

	@Override
	public boolean isSyncHidden(Player player){
		return false;
	}

	@Override
	public long lastUpdated(){return lastUpdated;}

	@Override
	public void lastUpdated(long l){lastUpdated = l;}

	@Override
	public long updateSpacing(){return updateSpacing;}

	@Override
	public void updateSpacing(long l){updateSpacing = l;}

	@Override
	public float fin(){return time / lifetime;}

	@Override
	public float time(){return time;}

	@Override
	public void time(float v){time = v;}

	@Override
	public float lifetime(){return lifetime;}

	@Override
	public void lifetime(float v){lifetime = v;}

	@Override
	public float rotation(){return rotation;}

	@Override
	public void rotation(float v){rotation = v;}

	@Override
	public Building buildOn(){
		return Vars.world.buildWorld(x, y);
	}
}

 */