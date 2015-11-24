package ru.asemty.game.objects.enemy;

import ru.asemty.game.objects.ai.EnemyAIDrone;

public class EnemyDrone extends EnemyShip{
boolean laser;
	public EnemyDrone(boolean l) {
		super("drone"+(l?"0":"1"));
		laser=l;
		this.ai=new EnemyAIDrone(this, l);
		this.hp=10;
		this.killCost=5;
		this.setSize(8, 8);
	}

}
