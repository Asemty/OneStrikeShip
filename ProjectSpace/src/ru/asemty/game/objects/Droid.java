package ru.asemty.game.objects;

import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;
import ru.asemty.game.objects.enemy.EnemyShotObject;

public class Droid extends GameObject {
	int lifeTime;
	public static int attackRete = 5;
	int currentStep;

	public Droid(int lt) {
		super("droid");
		lifeTime = lt;
		this.vSpd=-1;
	}

	@Override
	public void step() {
		currentStep++;
		if (currentStep % attackRete == 0) {
			SpaceGame.newGameObjects.add(new PlayerShotObject().setSpeed(0, -30).setLocation(this.rect.x, this.rect.y));
		}
		
		lifeTime--;
		if(lifeTime<0){
			this.dead();
		}
		super.step();
	}

	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof Enemy || neightbot instanceof EnemyShotObject ){
			this.dead();
		}
		super.touched(neightbot);
	}

}
