package ru.asemty.game.objects.enemy;

import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.BonusCap;

public class StoneMeteor extends Enemy{

	public StoneMeteor(boolean soft) {
		super(soft?"stone1":"stone2");
		hp=soft?1:50;
		this.setSize(8, 8);
	}
	@Override
	public void dead() {
		super.dead();
		if(Math.random()<0.01*(GameState.getDifficult()/2)){
			SpaceGame.newEvilGameObjects.add( new BonusCap().setLocation(rect.x+rect.width/2-16, rect.y+rect.height/2-16));
		}
	}
}
