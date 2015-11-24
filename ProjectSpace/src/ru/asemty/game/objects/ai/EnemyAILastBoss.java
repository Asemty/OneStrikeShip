package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAILastBoss extends EnemyAI {

	static int shoot1Offset = (int) (8 * GameConstatnts.shipSize);
	static int shoot2Offset = (int) (11 * GameConstatnts.shipSize);
	static int droneOffset = (int) (3 * GameConstatnts.shipSize);
	EnumEnemyAIState state;
	int tempMemory;
	int height;

	public EnemyAILastBoss(Enemy e) {
		super(e);
		state = EnumEnemyAIState.down;
		height = GameConstatnts.screenHeights / 8 + SpaceGame.rand.nextInt(GameConstatnts.screenHeights / 8);
	}

	@Override
	protected void stepAI() {
		if (owner.rect.y > -92) {
			if(currentStep%100==0){
				this.createDrone(-droneOffset, -24, Math.random()>0.9);
				this.createDrone(droneOffset, -24, Math.random()>0.9);
				this.createDrone(-droneOffset, 0, Math.random()>0.9);
				this.createDrone(droneOffset, 0, Math.random()>0.9);
				this.createDrone(-droneOffset, 24, Math.random()>0.9);
				this.createDrone(droneOffset, 24, Math.random()>0.9);
			}
			if (state == EnumEnemyAIState.follow) {
				if (this.currentStep % (100 - GameState.getDifficult() * 10) == 0) {
					this.shot(shoot1Offset, 24, 0, 0);
					this.shot(-shoot1Offset, 24, 0, 0);
					this.shot(shoot2Offset, 24, 0, 0);
					this.shot(-shoot2Offset, 24, 0, 0);
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
						tempMemory = currentStep + 30-GameState.getDifficult()*5;
					} else {
						owner.rect.x -= Math.signum(owner.rect.x - GameState.player.rect.x)*GameState.getDifficult()*3;
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
