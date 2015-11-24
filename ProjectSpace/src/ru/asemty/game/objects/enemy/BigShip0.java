package ru.asemty.game.objects.enemy;

import ru.asemty.game.objects.ai.EnemyAIBigShip0;

public class BigShip0 extends EnemyShip{

	public BigShip0() {
		super("bigShip0");
		this.hp=1000;
		this.killCost=150;
		this.setSize(32, 16).setSpeed(0, 3);
		this.ai= new EnemyAIBigShip0(this);
	}

}
