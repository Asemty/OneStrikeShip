package ru.asemty.game.objects;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class PlayerShotObject extends GameObject {
	
	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof Enemy)
		if (((Enemy)(neightbot)).hp > 0 && this.deadTime == -1) {
			key = key.substring(0, 2)+"ShotDead";
			deadTime = 10;
			vSpd=-3;
			hSpd=SpaceGame.rand.nextInt(9)-4;
			((Enemy)(neightbot)).hp-=this.damage;
		}
		super.touched(neightbot);
	}

	int deadTime;
	int damage;
	public static int  globalDamage=1;
	public PlayerShotObject() {
		super("plShot");
		this.setSize(8, 8);
		deadTime = -1;
		damage=globalDamage;
	}

	@Override
	public void step() {
		if (deadTime > 0) {
			deadTime--;
		}
		if (this.rect.y + this.rect.height < 0 || this.rect.width+this.rect.x<0 || this.rect.x>GameConstatnts.screenWidth || this.rect.y>GameConstatnts.screenHeights*2) {
			SpaceGame.delGameObjects.add(this);
		}
		if(deadTime == 0){
			this.dead();
		}
		super.step();
	}

}
