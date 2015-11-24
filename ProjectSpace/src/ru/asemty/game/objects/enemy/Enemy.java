package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.ExplodeObject;
import ru.asemty.game.objects.GameObject;
import ru.asemty.game.objects.PlayerShip;
import ru.asemty.game.objects.ai.EnemyAI;

public class Enemy extends GameObject{
	int deadTime;
	public int hp;
	public EnemyAI ai;
	public Enemy(String key) {
		super(key);
		deadTime=1;
		
	}
	@Override
	public void touched(GameObject neightbot) {
		if(neightbot instanceof PlayerShip)
		if (((PlayerShip)(neightbot)).hp > 0 && this.hp>0 && ((PlayerShip)(neightbot)).hurt<=0) {
			this.hp--;
			((PlayerShip)(neightbot)).attacked(1);
		}
		super.touched(neightbot);
	}
	@Override
	public void step() {
		super.step();
		if(ai!=null){
			ai.step();
		}
		if (hp <= 0) {
			this.setSpeed(0, 0);
			deadTime--;
			if (SpaceGame.rand.nextInt(2) == 0) {
				SpaceGame.newGameObjects.add(new ExplodeObject((int) (SpaceGame.rand.nextInt(16)
						* GameConstatnts.shipSize + this.rect.x - 8 * GameConstatnts.shipSize), (int) (SpaceGame.rand
						.nextInt(16) * GameConstatnts.shipSize + this.rect.y - 8 * GameConstatnts.shipSize)));
			}
			if(deadTime==0){
				dead();
			}
		}
		if(this.rect.y>GameConstatnts.screenHeights){
			dead();
		}
	}
}
