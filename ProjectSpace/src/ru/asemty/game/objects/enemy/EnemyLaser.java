package ru.asemty.game.objects.enemy;

import ru.asemty.game.objects.GameObject;
import ru.asemty.game.objects.PlayerShip;


public class EnemyLaser extends EnemyShotObject{
	int deadTime;
	public EnemyLaser() {
		super();
		this.key="lazer";
		this.setSize(8, 8).setSpeed(0, 32);
		deadTime = -1;
	}

	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof PlayerShip)
		if (((PlayerShip)(neightbot)).hp > 0 && ((PlayerShip)(neightbot)).hurt<=0) {
			((PlayerShip)(neightbot)).attacked(1);
		}
	}
}
