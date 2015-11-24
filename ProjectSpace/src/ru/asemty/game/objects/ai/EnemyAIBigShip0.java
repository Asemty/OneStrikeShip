package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIBigShip0 extends EnemyAI {

	static int shootOffset = (int) (12 * GameConstatnts.shipSize);
	EnumEnemyAIState state;
	int tempMemory;
	int height;

	public EnemyAIBigShip0(Enemy e) {
		super(e);
		state = EnumEnemyAIState.down;
		height = GameConstatnts.screenHeights / 8 + SpaceGame.rand.nextInt(GameConstatnts.screenHeights / 8);
	}

	@Override
	protected void stepAI() {
		if (owner.rect.y > -64) {
			if (state == EnumEnemyAIState.follow) {
				if (this.currentStep % (30 - GameState.getDifficult() * 4) == 0) {
					this.shot(shootOffset, 0, 0, 0);
					this.shot(-shootOffset, 0, 0, 0);
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
						tempMemory = currentStep + (SpaceGame.rand.nextInt(5) + 3) * 18;
					} else {
						owner.rect.x -= Math.signum(owner.rect.x - GameState.player.rect.x);
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
	}

	@Override
	protected void initAI() {
		// TODO Auto-generated method stub

	}

}
