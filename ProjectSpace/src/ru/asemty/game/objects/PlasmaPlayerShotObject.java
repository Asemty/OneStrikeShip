package ru.asemty.game.objects;

import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public class PlasmaPlayerShotObject extends PlayerShotObject {

	Enemy noAim;
	public PlasmaPlayerShotObject(int s) {
		super();
		this.key = "mtShot";
		this.setSize((int) (8 * (1 + 0.05f * s)), (int) (8 * (1 + 0.05f * s)));
		deadTime = -1;
		damage = s*globalDamage;
		this.sizeMult = 1 + 0.05f * s;
	}

	@Override
	public void dead() {
		super.dead();
		if (this.damage > 1) {
			for (int i = 0; i < this.damage/2; i++) {
				SpaceGame.newGameObjects.add(new PlasmaPlayerShotObject(1).setNoAim(noAim).setLocation(this.rect.x, this.rect.y).setSpeed(SpaceGame.rand.nextInt(11)-5, -15));
			}
		}
	}
	
	public PlasmaPlayerShotObject setNoAim(Enemy e){
		noAim=e;
		return this;
	}
	
	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof Enemy)
		if (((Enemy)(neightbot)).hp > 0 && this.deadTime == -1 && !neightbot.equals(noAim)) {
			key = key.substring(0, 2)+"ShotDead";
			deadTime = 10;
			vSpd=-3;
			hSpd=SpaceGame.rand.nextInt(9)-4;
			noAim=(Enemy) neightbot;
			((Enemy)(neightbot)).hp-=this.damage;
		}
		//super.touched(neightbot);
	}
}
