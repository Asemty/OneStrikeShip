package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.BonusCap;
import ru.asemty.game.objects.ai.EnemyAIFlyer;
import ru.asemty.game.objects.ai.EnemyAIShooter;
import ru.asemty.game.objects.ai.EnemyAITank;
import ru.asemty.game.objects.ai.EnemyAITriple;

public class EnemyShip extends Enemy{
	@Override
	public void dead() {
		super.dead();
		if(Math.random()<0.01*(GameState.getDifficult()*2+3)){
			SpaceGame.newEvilGameObjects.add( new BonusCap().setLocation(rect.x+rect.width/2-16, rect.y+rect.height/2-16));
		}
	}
	int shipClass;
	boolean isBoss;
	
	int killCost;
	
	public EnemyShip(int c) {
		super("enemyShip" + c);
		this.setSpeed(3, 0);
		this.setSize(16, 16);
		this.shipClass = c;
		isBoss = false;
		shipInit(c);
		deadTime =20;
	}
	public EnemyShip(String key) {
		super(key);
		isBoss = false;
		deadTime =20;
	}
	

	private void shipInit(int c) {
		switch (c) {
		case 0:
			this.hp=3;
			killCost=1;
			this.ai=new EnemyAIFlyer(this);
			break;
		case 1:
			this.hp=12;
			killCost=3;
			this.ai=new EnemyAIFlyer(this);
			break;
		case 2:
			this.hp=12;
			killCost=10;
			this.ai=new EnemyAIShooter(this);
			break;
		case 3:
			this.hp=50;
			killCost=20;
			this.ai=new EnemyAITank(this);
			break;
		case 4:
			this.hp=35;
			killCost=25;
			this.ai=new EnemyAITriple(this);
			break;
			
		}
		this.hp*=GameState.getDifficult()*0.2+1;
		this.killCost*=GameState.getDifficult()*0.2+1;
	}

	protected EnemyShip cloner(){
		EnemyShip clone= new EnemyShip(this.key);
		clone.hp=this.hp;
		clone.deadTime=this.deadTime;
		clone.killCost=this.killCost;
		clone.isBoss=this.isBoss;
		clone.setSize((int) (this.rect.width/GameConstatnts.shipSize), (int) (this.rect.height/GameConstatnts.shipSize));
		return clone;
	}
	@Override
	public void step() {
		super.step();
		if(hp<=0 && deadTime<=0){
			GameState.score+=this.killCost;
		}
		
	}
	
}
