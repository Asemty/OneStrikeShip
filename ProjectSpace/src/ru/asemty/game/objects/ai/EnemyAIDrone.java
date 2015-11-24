package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIDrone extends EnemyAI {
	boolean laser;

	public EnemyAIDrone(Enemy e, boolean l) {
		super(e);
		laser = l;
		this.owner.vSpd=Math.random()*3;
	}

	@Override
	protected void stepAI() {

		if (laser && this.currentStep % 100 <= 5 + GameState.getDifficult() * 5) {
			this.laserShot(0, 0);
		} else {
			if (!laser && this.currentStep % (100 - GameState.getDifficult()*10) == 0) {
				this.shot(0, 0, 0, 0);
			}
			if (GameState.player != null
					&& Math.abs(GameState.player.rect.getCenterX() - owner.rect.getCenterX()) > GameConstatnts.shipSize * 4) {
				owner.rect.x += Math.signum(GameState.player.rect.getCenterX() - owner.rect.getCenterX())*GameConstatnts.shipSize;
			}
		}

	}

	@Override
	protected void initAI() {

	}

}
