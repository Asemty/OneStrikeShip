package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameState;
import ru.asemty.game.objects.ai.EnemyAILastBoss;

public class LastBoss extends EnemyShip{

	public LastBoss() {
		super("boss2");
		this.hp=10000;
		this.killCost=1250;
		this.setSize(32, 32).setSpeed(0, 5);
		this.ai= new EnemyAILastBoss(this);
		GameState.boss=this;
		GameState.maxBossHealth=this.hp;
	}

}
