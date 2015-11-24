package ru.asemty.game;

import ru.asemty.lib.sprite.SpriteSheet;
import ru.asemty.lib.sprite.Sprites;

public class GraphicHelper {
	public static void init() {
		Sprites.init();
		Sprites.putToList("sprsh", "/res/sprsheet.png", true);
		SpaceGame.sprSheet = new SpriteSheet(Sprites.getImage("sprsh"));
		SpaceGame.sprSheet.add("bigShip0", 64, 32, 32, 16);
		SpaceGame.sprSheet.add("bigShip1", 64, 48, 32, 16);
		SpaceGame.sprSheet.add("boss1", 96, 0, 32, 16);
		SpaceGame.sprSheet.add("boss2", 96, 32, 32, 32);
		SpaceGame.sprSheet.add("stone1", 8, 10, 0);
		SpaceGame.sprSheet.add("stone2", 8, 11, 0);
		SpaceGame.sprSheet.add("drone0", 8, 10, 1);
		SpaceGame.sprSheet.add("drone1", 8, 11, 1);
		SpaceGame.sprSheet.add("droid", 8, 2, 1);
		SpaceGame.sprSheet.add("enShot0", 8, 2, 0);
		SpaceGame.sprSheet.add("enShot1", 8, 3, 0);
		SpaceGame.sprSheet.add("lazer", 8, 3, 1);
		SpaceGame.sprSheet.add("shieldDroid", 8, 6, 1);
		for (int i = 0; i < 3; i++) {
			SpaceGame.sprSheet.add("boss0part" + i, 16, 1 + i, 3);
		}
		for (int i = 0; i < 4; i++) {
			SpaceGame.sprSheet.add("star" + i, 8, 4 + i / 2, 0 + i % 2);
			SpaceGame.sprSheet.add((i % 2 == 0 ? "pl" : "mt") + "Shot" + (i / 2 == 0 ? "" : "Dead"), 8, 0 + i / 2,
					0 + i % 2);
			SpaceGame.sprSheet.add((i % 2 == 0 ? "en" : "lz") + "Shot" + (i / 2 == 0 ? "0" : "1"), 8, 2 + i / 2,
					0 + i % 2);
			SpaceGame.sprSheet.add("cap" + i, 8, 8 + i%2, i/2);
			SpaceGame.sprSheet.add("ship" + i, 16, i, 1);
			SpaceGame.sprSheet.add("explode" + i, 16, 4 + i, 1);
		}
		for (int i = 0; i < 5; i++) {
			SpaceGame.sprSheet.add("enemyShip" + i, 16, i % 4, 2 + i / 4);
		}
	}
}
