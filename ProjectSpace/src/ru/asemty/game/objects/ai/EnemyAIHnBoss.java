package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIHnBoss extends EnemyAI {
	public static int shootOffset = (int) (4 * GameConstatnts.shipSize);
	int s1 = (int) (9 * GameConstatnts.shipSize);
	int s2 = (int) (13 * GameConstatnts.shipSize);

	EnumEnemyAIState state;
	int tempMemory;
	int height;

	public EnemyAIHnBoss(Enemy e) {
		super(e);
		state = EnumEnemyAIState.down;
		height = GameConstatnts.screenHeights / 12 + SpaceGame.rand.nextInt(GameConstatnts.screenHeights / 12);
	}

	@Override
	public void stepAI() {
		if (state != EnumEnemyAIState.down) {
			if (this.currentStep % (100 - GameState.getDifficult() * 10) == 0) {
				this.shot(s1, 0, 0, 0);
				this.shot(s2, 0, 0, 0);
				this.shot(-s1, 0, 0, 0);
				this.shot(-s2, 0, 0, 0);
			}
		}
		switch (state) {
		case down:
			if (owner.rect.y > height) {
				state = EnumEnemyAIState.follow;
				owner.vSpd = 0;
			}
			break;
		case follow:
			if (GameState.player != null) {
				if (Math.abs(GameState.player.rect.x - owner.rect.x) < owner.rect.width / 4) {

					state = EnumEnemyAIState.shot;

					this.createDrone(-24, -25, false);
					this.createDrone(24, -25, false);
					this.createDrone(-24, 25, false);
					this.createDrone(24, 25, false);

					tempMemory = currentStep + 40;
				} else {
					owner.rect.x -= Math.signum(owner.rect.x - GameState.player.rect.x)
							* (GameState.getDifficult() + 5);
				}
			}
			break;
		case shot:
			if (currentStep == tempMemory) {
				state = EnumEnemyAIState.follow;
			} else {
				this.laserShot(0, 0);

			}
			break;
		}
	}

	@Override
	public void initAI() {
		owner.setSpeed(0, 2 + GameState.getDifficult());
	}

}
