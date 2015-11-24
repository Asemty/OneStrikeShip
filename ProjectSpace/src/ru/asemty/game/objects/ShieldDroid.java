package ru.asemty.game.objects;

import java.awt.event.KeyEvent;

import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.EnemyLaser;
import ru.asemty.game.objects.enemy.EnemyShotObject;

public class ShieldDroid extends GameObject{

	public boolean visible;
	public ShieldDroid() {
		super("shieldDroid");
		visible=false;
		this.setSize(8, 6);
		this.setOffset(0, 3);
	}

	@Override
	public void step() {
		visible=SpaceGame.keyboard[KeyEvent.VK_C];
		this.key=visible?"shieldDroid":"";
		super.step();
	}

	@Override
	public void touched(GameObject neightbot) {
		if(visible && neightbot instanceof EnemyShotObject && !(neightbot instanceof EnemyLaser)){
			((EnemyShotObject)neightbot).destroy();
		}
		super.touched(neightbot);
	}

}
