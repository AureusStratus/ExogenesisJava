package exogenesis.util;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.audio.SoundLoop;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.units.StatusEntry;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Trail;
import mindustry.io.TypeIO;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.ui.Fonts;
import exogenesis.content.*;
import exogenesis.content.ExoFx;
import exogenesis.util.func.ExoFunc;
import exogenesis.util.func.DrawFunc;

import java.nio.FloatBuffer;

import static mindustry.Vars.headless;
import static mindustry.Vars.tilesize;

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
	public Unit toSpawn = Nulls.unit;
	
	public final Seq<Trail> trails = Seq.with(new Trail(30), new Trail(50), new Trail(70));
	public float trailWidth = 3f;
	
	@Override
	public float clipSize(){
		return drawSize + 500;
	}

	@Override
	public void draw() {

	}

	public Spawner init(UnitType type, Team team, Position pos, float rotation, float lifetime){
		this.type = type;
		this.lifetime = lifetime;
		this.rotation = rotation;
		this.team = team;
		this.drawSize = type.hitSize;
		trailWidth = Mathf.clamp(drawSize / 15f, 1.25f, 4f);
		set(pos);
		ExoFx.spawnWave.at(x, y, drawSize * 1.1f, team.color);
		
		return this;
	}
	
	public Spawner setStatus(StatusEffect status, float statusDuration){
		statusEntry.effect = status;
		statusEntry.time = statusDuration;
		
		return this;
	}
	
	@Override
	public void add(){
		super.add();
		Groups.sync.add(this);
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

	@Override
	public void update() {

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
	}
	
	public boolean canCreate(){
		return Units.canCreate(team, type) || team == Vars.state.rules.waveTeam;
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
		
		afterRead();
	}
	
	@Override
	public boolean serialize(){return true;}

	
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
