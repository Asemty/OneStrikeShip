package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAITriple extends EnemyAI {
	static int shootOffset = (int) (4 * GameConstatnts.shipSize);
	EnumEnemyAIState state;
	int tempMemory;
	int height;

	public EnemyAITriple(Enemy e) {
		super(e);
		state = EnumEnemyAIState.down;
		height = GameConstatnts.screenHeights / 8 + SpaceGame.rand.nextInt(GameConstatnts.screenHeights / 8);
	}

	@Override
	public void stepAI() {
		if (owner.rect.y > -64) {
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
						tempMemory = currentStep + (SpaceGame.rand.nextInt(5) + 2) * 8;
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
					int a = this.currentStep % (12);
					if (a == 0) {
						this.shot(-shootOffset, 0, 0, 0);
					} else if (a == 3 || a == 9) {
						this.shot(0, 0, 0, 0);
					} else if (a == 6) {
						this.shot(shootOffset, 0, 0, 0);
					}
				}
				break;
			}
		}
	}

	@Override
	public void initAI() {
		owner.setSpeed(0, 2 + GameState.getDifficult());
	}

}
