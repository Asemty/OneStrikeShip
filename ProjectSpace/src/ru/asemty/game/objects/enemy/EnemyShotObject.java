package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.GameObject;
import ru.asemty.game.objects.PlayerShip;

public class EnemyShotObject extends GameObject{
int deadTime;
	public EnemyShotObject() {
		super("enShot0");
		this.setSize(8, 8);
		deadTime = -1;
	}

	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof PlayerShip)
		if (((PlayerShip)(neightbot)).hp > 0 && this.deadTime == -1 && ((PlayerShip)(neightbot)).hurt<=0) {
			destroy();
			((PlayerShip)(neightbot)).attacked(1);
		}
		super.touched(neightbot);
	}

	public void destroy(){
		key = "enShot1";
		deadTime = 10;
		vSpd=3;
		hSpd=SpaceGame.rand.nextInt(9)-4;
	}

	@Override
	public void step() {
		if (deadTime > 0) {
			deadTime--;
		}
		if (this.rect.y + this.rect.height < -GameConstatnts.screenHeights || this.rect.width+this.rect.x<0 || this.rect.x>GameConstatnts.screenWidth || this.rect.y>GameConstatnts.screenHeights) {
			SpaceGame.delGameObjects.add(this);
		}
		if(deadTime == 0){
			this.dead();
		}
		super.step();
	}

}
