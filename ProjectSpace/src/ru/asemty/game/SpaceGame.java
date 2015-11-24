package ru.asemty.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ru.asemty.game.objects.GameObject;
import ru.asemty.game.objects.PlayerShip;
import ru.asemty.lib.MainClass;
import ru.asemty.lib.sprite.SpriteSheet;

public class SpaceGame extends MainClass {

	private static final long serialVersionUID = -7664833964055274861L;
	public static SpriteSheet sprSheet;
	public static ArrayList<GameObject> gameObjects;
	public static ArrayList<GameObject> evilGameObjects;
	public static ArrayList<GameObject> newGameObjects;
	public static ArrayList<GameObject> newEvilGameObjects;
	public static ArrayList<GameObject> delGameObjects;

	public static void main(String[] args) {
		SpaceGame.mainFunc(args, GameConstatnts.screenWidth, GameConstatnts.screenHeights, 20, new SpaceGame());
		GraphicHelper.init();
		Paralax.initParalaxLayer();
		SaveUnit.loadConfig();
		SpawnRule.rulesInit();
		SpaceGame.frame.setTitle(GameConstatnts.gameName);
		gameObjects = new ArrayList<GameObject>();
		evilGameObjects = new ArrayList<GameObject>();
	}

	public SpaceGame() {
		GameState.init();
	}

	@Override
	public void Step() {

		super.Step();
		newGameObjects = new ArrayList<GameObject>();
		newEvilGameObjects = new ArrayList<GameObject>();
		delGameObjects = new ArrayList<GameObject>();

		if (gameObjects != null && evilGameObjects != null && !GameState.pause) {
			for (GameObject go : gameObjects) {
				go.step();
				for (GameObject go1 : evilGameObjects) {
					if (go.rect.intersects(go1.rect)) {
						go.touched(go1);
					}
				}
			}
			for (GameObject go : evilGameObjects) {
				go.step();
				for (GameObject go1 : gameObjects) {
					if (go.rect.intersects(go1.rect)) {
						go.touched(go1);
					}
				}
			}
			gameObjects.addAll(newGameObjects);
			evilGameObjects.addAll(newEvilGameObjects);
			gameObjects.removeAll(delGameObjects);
			evilGameObjects.removeAll(delGameObjects);
		}
		GameState.step();
	}

	@Override
	public void Draw(Graphics gr) {
		super.Draw(gr);
		gr.setColor(GameConstatnts.backgroundColor);
		gr.fillRect(0, 0, GameConstatnts.screenWidth, GameConstatnts.screenHeights);
		Paralax.draw(gr);
		if (gameObjects != null && evilGameObjects != null && !GameState.state.equals("playerShipDead")) {
			for (GameObject go : gameObjects) {
				if (!(go instanceof PlayerShip) || ((PlayerShip) go).hurt <= 0 || ((PlayerShip) go).hurt % 3 != 0)
					sprSheet.draw(gr, go.key, go.rect.x + go.offset.x, go.rect.y + go.offset.y, GameConstatnts.shipSize
							* go.sizeMult);

				if (GameConstatnts.debug) {
					gr.setColor(Color.blue);
					gr.drawRect(go.rect.x, go.rect.y, go.rect.width, go.rect.height);
				}
			}
			for (GameObject go : evilGameObjects) {
				sprSheet.draw(gr, go.key, go.rect.x + go.offset.x, go.rect.y + go.offset.y, GameConstatnts.shipSize
						* go.sizeMult);

				if (GameConstatnts.debug) {
					gr.setColor(Color.red);
					gr.drawRect(go.rect.x, go.rect.y, go.rect.width, go.rect.height);
				}
			}
		}
		GameState.draw(gr);
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.KeyPressed(e);
		GameState.KeyPressed(e);
		if (gameObjects != null &&  evilGameObjects != null && !GameState.pause) {
			for (GameObject go : gameObjects) {
				go.pressKey(e);
			}
			for (GameObject go : evilGameObjects) {
				go.pressKey(e);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
		}
		if (e.getKeyCode() == KeyEvent.VK_T && e.isShiftDown()) {
			GameConstatnts.debug = !GameConstatnts.debug;
		}
	}

	@Override
	public void exit() {
		SaveUnit.saveData();
		SaveUnit.saveConfig();
		super.exit();
	}

}
