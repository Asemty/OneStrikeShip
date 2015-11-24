package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.ai.EnemyAIPartableBoss;

public class PartableBoss extends EnemyShip{

	public EnemyShip l,r,ld,cd,rd;
	public static int partSize;
	public int dir=-1;
	public PartableBoss() {
		super("boss0part0");
		this.hp=500;
		this.killCost=100;
		this.setSize(16, 16);
		l=(EnemyShip) new EnemyShip("boss0part1").setSize(16, 16);
		l.hp=250;
		l.killCost=60;
		r=l.cloner();
		ld=(EnemyShip) new EnemyShip("boss0part2").setSize(16, 16);
		ld.hp=175;
		ld.killCost=45;
		cd=ld.cloner();
		cd.hp=250;
		rd=ld.cloner();
		SpaceGame.evilGameObjects.add(l);
		SpaceGame.evilGameObjects.add(r);
		SpaceGame.evilGameObjects.add(ld);
		SpaceGame.evilGameObjects.add(cd);
		SpaceGame.evilGameObjects.add(rd);
		partSize=(int) (16*GameConstatnts.shipSize);
		GameState.boss=this;
		GameState.maxBossHealth=this.hp;
		this.deadTime=100;
		this.ai=new EnemyAIPartableBoss(this);
		
	}
	@Override
	public void dead() {
		GameState.boss=null;
		if(l!=null)
		l.hp=0;
		if(r!=null)
		r.hp=0;
		if(ld!=null)
		ld.hp=0;
		if(rd!=null)
		rd.hp=0;
		if(cd!=null)
		cd.hp=0;
		super.dead();
	}
	@Override
	public void step() {
		super.step();
		
		
		if(l!=null)
		l.setLocation(this.rect.x-partSize, this.rect.y);
		if(r!=null)
		r.setLocation(this.rect.x+partSize, this.rect.y);
		
		if(ld!=null)
		ld.setLocation(this.rect.x-partSize, this.rect.y+partSize);
		if(cd!=null)
		cd.setLocation(this.rect.x, this.rect.y+partSize);
		if(rd!=null)
		rd.setLocation(this.rect.x+partSize, this.rect.y+partSize);
	}

}
