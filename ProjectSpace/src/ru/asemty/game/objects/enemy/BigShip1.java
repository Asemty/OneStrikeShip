package ru.asemty.game.objects.enemy;

import ru.asemty.game.objects.ai.EnemyAIBigShip1;

public class BigShip1 extends EnemyShip{

	public BigShip1() {
		super("bigShip1");
		this.hp=1000;
		this.killCost=150;
		this.setSize(32, 16).setSpeed(0, 3);
		this.ai= new EnemyAIBigShip1(this);
	}
	
}
