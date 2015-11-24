package ru.asemty.game.objects.ai;

import ru.asemty.game.GameState;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIPartBoss0 extends EnemyAI {

	public EnemyAIPartBoss0(Enemy e) {
		super(e);
	}

	@Override
	public void stepAI() {
		if(this.currentStep%(50-5*GameState.getDifficult())==0){
			this.shot(-24, 0, 0, 0);
			this.shot(24, 0, 0, 0);
		}
	}

	@Override
	public void initAI() {
	}

}
