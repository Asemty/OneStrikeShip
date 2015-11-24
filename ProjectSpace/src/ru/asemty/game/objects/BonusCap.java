package ru.asemty.game.objects;

import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;

public class BonusCap extends GameObject{

	BonusCapAction bca;
	public BonusCap(int i) {
		super("cap"+i);
		bca=GameState.bonusCapList.get(i);
		this.setSize(8, 8).setSpeed(0, 4);
	}
	public BonusCap() {
		this(SpaceGame.rand.nextInt(4));
	}

	@Override
	public void touched(GameObject neightbot) {
		super.touched(neightbot);
		if(neightbot instanceof PlayerShip){
			dead();
			if (bca!=null){
				GameState.setScreenDiscription(bca.discription);
				bca.action();
			}
		}
	}
	
	
}
