package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;
import ru.asemty.game.objects.enemy.EnemyDrone;
import ru.asemty.game.objects.enemy.EnemyLaser;
import ru.asemty.game.objects.enemy.EnemyShotObject;

public abstract class EnemyAI {
	Enemy owner;
	int currentStep;

	public EnemyAI(Enemy e) {
		owner = e;
		currentStep = SpaceGame.rand.nextInt(100);
		initAI();
	}

	public void step() {
		currentStep++;
		stepAI();
	}

	protected abstract void stepAI();

	protected abstract void initAI();

	public void shot(int xo, int yo, int horisontalSpeed, int verticalSpeed) {
		SpaceGame.newEvilGameObjects
				.add(new EnemyShotObject()
						.setSpeed(horisontalSpeed, 10 + verticalSpeed)
						.setLocation(
								(int) (owner.rect.x + xo + horisontalSpeed + owner.rect.width / 2 - GameConstatnts.shipSize * 4),
								owner.rect.y + owner.rect.height + yo + verticalSpeed));
	}

	public void createDrone(int xo, int yo, boolean laser) {
		SpaceGame.newEvilGameObjects.add(new EnemyDrone(laser).setLocation((int) (owner.rect.x + xo + owner.rect.width
				/ 2 - GameConstatnts.shipSize * 4), (int) (owner.rect.y + owner.rect.height / 2
				- GameConstatnts.shipSize * 4 + yo)));
	}

	public void laserShot(int xo, int yo) {
		SpaceGame.newEvilGameObjects.add(new EnemyLaser().setSpeed(0, 25).setLocation(
				(int) (owner.rect.x + xo + owner.rect.width / 2 - GameConstatnts.shipSize * 4),
				owner.rect.y + owner.rect.height + yo));
	}
}
