package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameState;
import ru.asemty.game.objects.ai.EnemyAIHnBoss;

public class HnBoss extends EnemyShip{

	public HnBoss() {
		super("boss1");
		this.hp=4000;
		this.killCost=500;
		this.setSize(32, 16);
		this.ai= new EnemyAIHnBoss(this);
		GameState.boss=this;
		GameState.maxBossHealth=this.hp;
	}


}
