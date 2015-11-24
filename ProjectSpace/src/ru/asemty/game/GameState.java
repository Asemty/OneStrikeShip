package ru.asemty.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ru.asemty.game.objects.BonusCapAction;
import ru.asemty.game.objects.GameObject;
import ru.asemty.game.objects.PlayerShip;
import ru.asemty.game.objects.enemy.Enemy;
import ru.asemty.game.objects.enemy.EnemyShip;
import ru.asemty.game.objects.enemy.HnBoss;
import ru.asemty.game.objects.enemy.LastBoss;
import ru.asemty.game.objects.enemy.PartableBoss;
import ru.asemty.lib.utils.MathHelper;
import ru.asemty.lib.utils.TextHelper;

public class GameState {
	public static EnemyShip boss;
	public static int maxBossHealth;
	public static int score;
	public static boolean pause;
	public static PlayerShip player;

	public static int level;
	public static int levelPart;
	public static int waitNextLevel;

	static String state;
	static int currentChoice;
	static int masterVolume;
	static final int lineInOnePage = 10;
	static ArrayList<String> playerShips;
	public static ArrayList<BonusCapAction> bonusCapList;

	static int screenDiscriptionTime;
	static String screenDiscription;

	public static void setScreenDiscription(String scrDisc) {
		screenDiscription = scrDisc;
		screenDiscriptionTime = 192;
	}

	public static int getDifficult() {
		return (int) Math.min(MathHelper.log(5, score / 5), 5);
	}

	public static void init() {
		boss = null;
		score = 0;
		pause = false;

		SaveUnit.loadData();
		state = "pressAK";
		masterVolume = 4;
	}

	public static void draw(Graphics gr) {
		if (boss != null) {
			gr.setColor(Color.red);
			gr.fillRect(8, 3, (GameConstatnts.screenWidth - 16) * boss.hp / maxBossHealth, 6);
		}

		drawGui(gr);
	}

	public static void drawGui(Graphics gr) {
		gr.setFont(TextHelper.font);
		gr.setColor(Color.LIGHT_GRAY);
		switch (state) {
		case "pressAK":
			gr.drawString("Press any key", GameConstatnts.screenWidth / 3, GameConstatnts.screenHeights / 2);
			break;
		case "mainMenu":
			gr.drawString("Start", GameConstatnts.screenWidth / 3, 220);
			gr.drawString("Option", GameConstatnts.screenWidth / 3, 240);
			gr.drawString("Obtained data", GameConstatnts.screenWidth / 3, 260);
			gr.drawString("Exit", GameConstatnts.screenWidth / 3, 280);
			gr.drawLine(GameConstatnts.screenWidth / 3 - 15, 215 + 20 * currentChoice,
					GameConstatnts.screenWidth / 3 - 5, 215 + 20 * currentChoice);
			break;
		case "choiceShip":
			SpaceGame.sprSheet.draw(gr, playerShips.get(currentChoice), GameConstatnts.screenWidth / 2 - 8 * 8, 300, 8);
			gr.drawString(PlayerShip.getName(playerShips.get(currentChoice)), 40, 450);
			String[] discLine = TextHelper.toLineCutter(PlayerShip.getDiscription(playerShips.get(currentChoice)),
					GameConstatnts.screenWidth - 80);
			for (int i = 0; i < discLine.length; i++) {
				gr.drawString(discLine[i], 40, 470 + i * 15);
			}
			if (currentChoice > 0) {
				SpaceGame.sprSheet.draw(gr, playerShips.get(currentChoice - 1), GameConstatnts.screenWidth / 6 - 8 * 4,
						200, 4);
			}
			if (currentChoice < playerShips.size() - 1) {
				SpaceGame.sprSheet.draw(gr, playerShips.get(currentChoice + 1), GameConstatnts.screenWidth / 6 * 5 - 8
						* 4, 200, 4);
			}
			break;
		case "option":
			gr.drawString("Volume", GameConstatnts.screenWidth / 3, 220);
			gr.drawString("FullScreen", GameConstatnts.screenWidth / 3, 240);
			gr.drawString("Delete data", GameConstatnts.screenWidth / 3, 260);
			gr.drawString("Main menu", GameConstatnts.screenWidth / 3, 280);
			gr.drawLine(GameConstatnts.screenWidth / 3 - 15, 215 + 20 * currentChoice,
					GameConstatnts.screenWidth / 3 - 5, 215 + 20 * currentChoice);
			for (int i = 0; i < 5; i++) {
				if (i < masterVolume)
					gr.fillRect(GameConstatnts.screenWidth / 2 + i * 22, 204, 16, 16);
				else {
					gr.drawRect(GameConstatnts.screenWidth / 2 + i * 22, 204, 16, 16);
				}
			}
			break;
		case "delSaveConfurmation":
			gr.drawString("Are you sure?", GameConstatnts.screenWidth / 3, GameConstatnts.screenHeights / 2);
			break;

		case "obtainedData":
			for (int i = 0; i < lineInOnePage; i++) {
				if (i + currentChoice / lineInOnePage * lineInOnePage < SaveUnit.getDataSize()) {
					gr.drawString(SaveUnit.getName(i + currentChoice / lineInOnePage * lineInOnePage), 20, 180 + 20 * i);
					if (i + currentChoice / lineInOnePage * lineInOnePage == currentChoice) {
						gr.drawLine(10, 175 + 20 * i, 20, 175 + 20 * i);
					}
				}
			}
			String[] dataDiscLine = TextHelper.toLineCutter(SaveUnit.getDiscription(currentChoice),
					GameConstatnts.screenWidth / 2);
			for (int i = 0; i < dataDiscLine.length; i++) {
				gr.drawString(dataDiscLine[i], GameConstatnts.screenWidth / 3, 60 + i * 15);
			}

			break;
		case "inGame":
			if (pause) {
				gr.setColor(Color.lightGray);
				gr.drawString("Press DEL to exit", GameConstatnts.screenWidth / 3, GameConstatnts.screenHeights / 2);
				gr.drawString("Score: " + score,
						GameConstatnts.screenWidth / 2 - TextHelper.getStringPixelLength("Score: " + score) / 2, 26);

				gr.drawString("Weapon", GameConstatnts.screenWidth - 60, GameConstatnts.screenHeights - 16);
				gr.drawString("Difficult", 3, GameConstatnts.screenHeights - 16);
			}
			if (player != null) {
				gr.setColor(Color.green.darker());
				for (int i = 0; i < player.hp; i++) {
					gr.fillRect(8 + i * ((GameConstatnts.screenWidth - 16) / player.hp),
							GameConstatnts.screenHeights - 6, (GameConstatnts.screenWidth - 16) / player.hp - 3, 6);
				}
				gr.drawString(level + " - " + levelPart, 3, 26);
			}
			for (int i = 0; i < 5; i++) {

				gr.setColor(Color.yellow.darker().darker());
				if (getDifficult() > i) {
					gr.fillRect(5, GameConstatnts.screenHeights - 12 * i - 38, 13, 5);
				}

				gr.setColor(Color.red.darker().darker());
				if (player.weaponLevel > i) {
					gr.fillRect(GameConstatnts.screenWidth - 18, GameConstatnts.screenHeights - 12 * i - 38, 13, 5);
				}

				gr.setColor(Color.darkGray);
				gr.drawRoundRect(3, GameConstatnts.screenHeights - 12 * i - 40, 16, 8, 4, 4);
				gr.drawRoundRect(GameConstatnts.screenWidth - 20, GameConstatnts.screenHeights - 12 * i - 40, 16, 8, 4,
						4);
			}
			gr.setColor(Color.lightGray);
			if (screenDiscription != null && screenDiscriptionTime > 0) {
				gr.drawString(screenDiscription,
						GameConstatnts.screenWidth / 2 - TextHelper.getStringPixelLength(screenDiscription) / 2,
						GameConstatnts.screenHeights - 16);
				screenDiscriptionTime--;
			}
			break;
		case "endGame":
			gr.drawString("Game Over", GameConstatnts.screenWidth / 2 - TextHelper.getStringPixelLength("Game Over")
					/ 2, GameConstatnts.screenHeights / 2);
			break;
		case "winGame":
			gr.drawString(
					"Congratulations! You do it!",
					GameConstatnts.screenWidth / 2 - TextHelper.getStringPixelLength("Congratulations! You do it!") / 2,
					GameConstatnts.screenHeights / 2);
			gr.drawString(
					"...but you try again. Maybe we learn",
					GameConstatnts.screenWidth / 2
							- TextHelper.getStringPixelLength("...but you try again. Maybe we learn") / 2,
					GameConstatnts.screenHeights / 2 + 20);
			gr.drawString(
					"something new from you obtained data",
					GameConstatnts.screenWidth / 2
							- TextHelper.getStringPixelLength("something new from you obtained data") / 2,
					GameConstatnts.screenHeights / 2 + 40);
			gr.drawString("Press ENTER to continue",
					GameConstatnts.screenWidth / 2 - TextHelper.getStringPixelLength("Press ENTER to continue") / 2,
					GameConstatnts.screenHeights / 2 + 80);
			break;
		}

	}

	public static void step() {
		if (state.equals("inGame")) {
			if (player == null) {
				state = "endGame";
			}
			checkEnemy();

		}
	}

	public static void KeyPressed(KeyEvent e) {
		switch (state) {
		case "pressAK":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				SpaceGame.instance.exit();
			} else {
				state = "mainMenu";
				currentChoice = 0;
			}
			break;
		case "mainMenu":

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				currentChoice--;
				if (currentChoice < 0) {
					currentChoice = 3;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				currentChoice++;
				if (currentChoice > 3) {
					currentChoice = 0;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				switch (currentChoice) {
				case 0:
					state = "choiceShip";
					currentChoice = 0;
					playerShips = new ArrayList<String>();
					playerShips.add("ship0");
					if (SaveUnit.isComplite("CHASR"))
						playerShips.add("ship1");
					if (SaveUnit.isComplite("SKNOT"))
						playerShips.add("ship2");
					if (SaveUnit.isComplite("PLONG"))
						playerShips.add("ship3");
					break;
				case 1:
					state = "option";
					currentChoice = 0;
					break;
				case 2:
					state = "obtainedData";
					currentChoice = 0;
					break;
				case 3:
					SpaceGame.instance.exit();
					break;
				}
			}
			break;
		case "choiceShip":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				state = "mainMenu";
				currentChoice = 0;
			} else {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					currentChoice--;
					/*
					 * ћесто дл€ изменени€ количества короблей.≈сли меньше
					 * нул€-сделать<максимальный корабль>
					 */
					if (currentChoice < 0) {
						currentChoice = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					currentChoice++;
					if (currentChoice > playerShips.size() - 1) {
						currentChoice = playerShips.size() - 1;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					state = "inGame";
					gameInit();
				}
			}
			break;
		case "option":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				state = "mainMenu";
				currentChoice = 0;
			} else {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					currentChoice--;
					if (currentChoice < 0) {
						currentChoice = 3;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					currentChoice++;
					if (currentChoice > 3) {
						currentChoice = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					switch (currentChoice) {
					case 0:
						break;
					case 1:
						SpaceGame.setFullScreen(!SpaceGame.isFullScreen, false);
						break;
					case 2:
						state = "delSaveConfurmation";
						currentChoice = 0;
						break;
					case 3:
						state = "mainMenu";
						currentChoice = 0;
						break;
					}
				}
				if (currentChoice == 0) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						masterVolume--;
						if (masterVolume < 0) {
							masterVolume = 0;
						}
						setVolume();
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						masterVolume++;
						if (masterVolume > 5) {
							masterVolume = 5;
						}
						setVolume();
					}
				}
			}
			break;
		case "delSaveConfurmation":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				state = "option";
				currentChoice = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				state = "option";
				currentChoice = 0;
				deleteSave();
			}

			break;

		case "obtainedData":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				state = "mainMenu";
				currentChoice = 0;
			} else {
				// lineInOnePage
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (SaveUnit.getDataSize() > lineInOnePage) {
						currentChoice -= lineInOnePage;
						if (currentChoice < 0) {
							currentChoice = 0;
						}
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (SaveUnit.getDataSize() > lineInOnePage) {
						currentChoice += lineInOnePage;
						if (currentChoice > SaveUnit.getDataSize() - 1) {
							currentChoice = SaveUnit.getDataSize() - 1;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					currentChoice--;
					if (currentChoice < 0) {
						currentChoice = SaveUnit.getDataSize() - 1;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					currentChoice++;
					if (currentChoice > SaveUnit.getDataSize() - 1) {
						currentChoice = 0;

					}
				}
			}
			break;
		case "pressAK2q":
			break;
		case "inGame":
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				pause = !pause;
				Paralax.pause();
			}
			if (e.getKeyCode() == KeyEvent.VK_DELETE) {
				if (pause) {
					goToMainMenu();
				}
			}
			break;
		case "endGame":
			goToMainMenu();
			break;
		case "winGame":
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				goToMainMenu();
			}
			break;
		}

	}

	private static void gameInit() {

		player = new PlayerShip(playerShips.get(currentChoice));
		SpaceGame.gameObjects.add(player);
		bonusCapList = new ArrayList<BonusCapAction>();
		bonusCapList.add(BonusCapAction.speedUp);
		bonusCapList.add(BonusCapAction.allEnemyDamage);
		bonusCapList.add(BonusCapAction.weaponUp);
		bonusCapList.add(BonusCapAction.heal);

		level = 1;
		levelPart = 0;
		nextPart();
		Achivments.init();
	}

	private static void goToMainMenu() {
		pause = false;
		Paralax.pause(false);
		state = "mainMenu";
		currentChoice = 0;
		SpaceGame.gameObjects = new ArrayList<GameObject>();
		SpaceGame.evilGameObjects = new ArrayList<GameObject>();
		player = null;
		boss = null;
		score = 0;
		maxBossHealth = 0;
	}

	private static void deleteSave() {
		SaveUnit.delData();
	}

	private static void setVolume() {
		// TODO Auto-generated method stub
		masterVolume = masterVolume;
	}

	public static void checkEnemy() {
		for (GameObject go : SpaceGame.evilGameObjects) {
			if (go instanceof Enemy) {
				return;
			}
		}
		nextPart();
	}

	public static void nextPart() {
		if (!pause) {
			waitNextLevel--;
		}
		if (waitNextLevel <= 0) {
			Achivments.nextLevel();
			if (level == 5 && levelPart == 5) {
				if (!SaveUnit.isComplite("SKNOT")) {
					GameState.state = "winGame";
					SaveUnit.setComplite("SKNOT");
					return;
				}
			}
			if (level == 10 && levelPart == 5) {
				if (!SaveUnit.isComplite("CHASR")) {
					GameState.state = "winGame";
					SaveUnit.setComplite("CHASR");
					return;
				}
			}
			if (level == 15 && levelPart == 3) {GameState.state = "winGame";
				if (!SaveUnit.isComplite("PLONG")) {
					SaveUnit.setComplite("PLONG");
					return;
				}
			}
			if (levelPart == 5 || (level > 10 && levelPart == 3)) {
				level++;
				levelPart = 1;
			} else {
				levelPart++;
			}

			if (level == 5 && levelPart == 5) {
				SpaceGame.evilGameObjects.add(new PartableBoss().setLocation(GameConstatnts.screenWidth / 2 - 32, -64));
			} else if (level == 10 && levelPart == 5) {
				SpaceGame.evilGameObjects.add(new HnBoss().setLocation(GameConstatnts.screenWidth / 2 - 32, -64));
			} else if (level == 15 && levelPart == 3) {
				SpaceGame.evilGameObjects.add(new LastBoss().setLocation(GameConstatnts.screenWidth / 2 - 32, -64));
			} else {
				if (level <= 10) {
					SpawnRule.randSpawn((level - 1) / 5);
				} else {
					SpawnRule.spawn(2, level - 11 + (levelPart - 1) * 3);
				}
			}
			if (levelPart == level) {
				waitNextLevel = 40;
			} else {
				waitNextLevel = 10;
			}
		}
	}
}
