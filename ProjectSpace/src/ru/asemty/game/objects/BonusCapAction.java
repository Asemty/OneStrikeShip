package ru.asemty.game.objects;

import ru.asemty.game.GameState;
import ru.asemty.game.SpaceGame;
import ru.asemty.game.objects.enemy.Enemy;

public abstract class BonusCapAction {
	public abstract void action();

	public String discription;

	public BonusCapAction(String s) {
		discription = s;
	}

	public static BonusCapAction heal = new BonusCapAction("Shild power up") {

		@Override
		public void action() {
			GameState.player.hp+=2;
		}
	};
	public static BonusCapAction weaponUp = new BonusCapAction("Weapon power up") {

		@Override
		public void action() {
			if (GameState.player.weaponLevel < 5){
				GameState.player.weaponLevel++;}
			else{
				PlayerShotObject.globalDamage++;
			}
		}
	};
	
	public static BonusCapAction speedUp = new BonusCapAction("Engine lubrication") {

		@Override
		public void action() {
			GameState.player.speed++;
		}
	};

	public static BonusCapAction allEnemyDamage = new BonusCapAction("SABOTAGE!") {

		@Override
		public void action() {
			for (GameObject go : SpaceGame.evilGameObjects) {
				if (go instanceof Enemy) {
					((Enemy) go).hp -= GameState.getDifficult() * 25;
				}
			}
		}
	};

}
