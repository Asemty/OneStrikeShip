package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIBigShip1 extends EnemyAI {

	static int shootOffset = (int) (7 * GameConstatnts.shipSize);
	static int droneOffset = (int) (13 * GameConstatnts.shipSize);
	EnumEnemyAIState state;
	int tempMemory;
	int height;

	public EnemyAIBigShip1(Enemy e) {
		super(e);
		state = EnumEnemyAIState.down;
		height = GameConstatnts.screenHeights / 8 + SpaceGame.rand.nextInt(GameConstatnts.screenHeights / 8);
	}

	@Override
	protected void stepAI() {
		if (owner.rect.y > -64) {
			if (state == EnumEnemyAIState.follow) {
				if (this.currentStep % (100 - GameState.getDifficult() * 10) == 0) {
					this.shot(shootOffset, 0, 0, 0);
					this.shot(-shootOffset, 0, 0, 0);
					this.shot(0, shootOffset, 0, 0);
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
					if(currentStep%150==0){
						this.createDrone(-droneOffset, 0, false);
						this.createDrone(droneOffset, 0, false);
					}
					if (Math.abs(-GameState.player.rect.x+GameConstatnts.screenWidth - owner.rect.getCenterX()) > owner.rect.width / 4) {
						owner.rect.x += Math.signum(-GameState.player.rect.x+GameConstatnts.screenWidth - owner.rect.getCenterX())*10;
					}
				}
				break;
			case shot:
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void initAI() {
		// TODO Auto-generated method stub

	}

}
