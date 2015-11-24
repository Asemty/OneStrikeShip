package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAITank extends EnemyAI {
	public static int shootOffset = (int) (3 * GameConstatnts.shipSize);

	public EnemyAITank(Enemy e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void stepAI() {
		if (owner.rect.y > -64) {
			int a = this.currentStep % (60 - 6 * GameState.getDifficult());
			if (a < 7 && a % 2 == 0) {
				this.shot(-shootOffset, 0, 0, 0);
				this.shot(shootOffset, 0, 0, 0);
			}
			if (GameState.player != null) {
				owner.rect.x -= Math.signum(owner.rect.x - GameState.player.rect.x) * (GameState.getDifficult() + 1)
						* 2;
			}
		}
	}

	@Override
	public void initAI() {
		owner.setSpeed(0, 1);
	}

}
