package ru.asemty.game.objects.ai;

import ru.asemty.game.GameState;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIFlyer extends EnemyAI {

	public EnemyAIFlyer(Enemy e) {
		super(e);
	}

	@Override
	public void stepAI() {
		
	}

	@Override
	public void initAI() {
		owner.setSpeed(0, 4+GameState.getDifficult());
	}

}
