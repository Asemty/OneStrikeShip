package ru.asemty.game;

public class Achivments {
	public static boolean noDamegeBoss;
	public static boolean noSkipEnemy;
	public static void init() {
		noDamegeBoss = true;
	}

	public static void nextLevel() {
		if(GameState.level==5 && GameState.level==5 && noDamegeBoss){
			switch(GameState.player.key){
			case "ship0":SaveUnit.setComplite("SHPND");break;
			case "ship1":SaveUnit.setComplite("CHAND");break;
			case "ship2":SaveUnit.setComplite("SKNND");break;
			case "ship3":SaveUnit.setComplite("PLOND");break;
			}
		}
	}
}
