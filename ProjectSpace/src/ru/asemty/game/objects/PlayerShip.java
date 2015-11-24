package ru.asemty.game.objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ru.asemty.game.Achivments;
import ru.asemty.game.GameConstatnts;
import ru.asemty.game.GameState;
import ru.asemty.game.SaveUnit;
import ru.asemty.game.SpaceGame;

public class PlayerShip extends GameObject {
	private int shootTimer = 0;
	private int maxShootTime;
	private int shotState;
	public int speed = 15;
	int deadTime;
	public int weaponLevel;
	public int hp;
	public int hurt;
	public boolean firstDefenceUpgrade;
	private boolean altMode = false;
	public ArrayList<GameObject> orbitels;

	public PlayerShip(String key) {
		super(key);
		this.setSize(12, 14).setOffset(-2, -1);
		this.setLocation((GameConstatnts.screenWidth - this.rect.width) / 2, GameConstatnts.screenHeights
				- this.rect.height);
		orbitels = new ArrayList<GameObject>();
		this.shipParamInit();
		this.weaponLevel = 0;
		deadTime = 120;
	}

	@Override
	public void dead() {
		GameState.player = null;
		super.dead();
	}

	public void attacked(int dmg) {
		if (this.hurt <= 0) {
			hurt = 35;
			hp -= dmg;
			GameState.score *= 0.75;
			Achivments.noDamegeBoss = false;
			// this.weaponLevel=Math.max(0, weaponLevel-1);
		}
	}

	private void shipParamInit() {
		switch (this.key) {
		case "ship0":
			this.speed = 15;
			this.maxShootTime = 4;
			this.hp = 5;
			firstDefenceUpgrade = SaveUnit.isComplite("SHPND");
			if (firstDefenceUpgrade) {
				for (int i = 0; i < 3; i++) {
					GameObject go = new ShieldDroid();
					orbitels.add(go);
					SpaceGame.gameObjects.add(go);
				}
			}
			break;
		case "ship1":
			this.speed = 25;
			this.maxShootTime = 12;
			this.hp = 3;
			firstDefenceUpgrade = SaveUnit.isComplite("CHAND");
			break;
		case "ship2":
			this.speed = 10;
			this.maxShootTime = 9;
			this.hp = 10;
			firstDefenceUpgrade = SaveUnit.isComplite("SKNND");
			break;
		case "ship3":
			this.speed = 20;
			this.hp = 5;
			this.maxShootTime = 1;
			firstDefenceUpgrade = SaveUnit.isComplite("PLOND");
			break;
		}
	}

	@Override
	public void step() {
		super.step();
		shootTimer++;
		hurt--;
		boolean normalSpeed = !this.key.equals("ship1") || !altMode;
		if (hp > 0) {
			if (this.key.equals("ship0")) {
				for (int i = 0; i < orbitels.size(); i++) {
					orbitels.get(i).setLocation(
							(int) (rect.x - GameConstatnts.shipSize * 6 + GameConstatnts.shipSize * 8 * i),
							(int) (rect.y - GameConstatnts.shipSize * 12 - (i % 2 == 1 ? GameConstatnts.shipSize * 4
									: 0)));

				}
			}
			if (SpaceGame.keyboard[KeyEvent.VK_UP]) {
				this.rect.y = Math.max(this.rect.y - speed / (normalSpeed ? 1 : 2), 0);
			}
			if (SpaceGame.keyboard[KeyEvent.VK_DOWN]) {
				this.rect.y = Math.min(this.rect.y + speed / (normalSpeed ? 1 : 2), GameConstatnts.screenHeights
						- this.rect.height);
			}
			if (SpaceGame.keyboard[KeyEvent.VK_LEFT]) {
				this.rect.x = Math.max(this.rect.x - speed / (normalSpeed ? 1 : 2), 0);
			} else if (SpaceGame.keyboard[KeyEvent.VK_RIGHT]) {
				this.rect.x = Math.min(this.rect.x + speed / (normalSpeed ? 1 : 2), GameConstatnts.screenWidth
						- this.rect.width);
			}

			if (SpaceGame.keyboard[KeyEvent.VK_Z]) {
				if (shootTimer > maxShootTime && !this.key.equals("ship2")) {

					switch (this.key) {
					case "ship0":
						if (!firstDefenceUpgrade || !
								
								
								SpaceGame.keyboard[KeyEvent.VK_C]) {
							leveledShot0(weaponLevel);
							shootTimer = -1;
							shotState++;
						}
						break;
					case "ship1":
						if (altMode) {
							if (shootTimer % 4 == 0) {
								leveledShot1(weaponLevel);
								shotState++;
							}
						} else {
							altLeveledShot1(weaponLevel);
							shootTimer = -1;
							shotState++;
						}
						break;
					case "ship3":
						leveledShot3(weaponLevel);
						shootTimer = -1;
						shotState++;
						break;
					}

				} else if (this.key.equals("ship2")) {
					shotState++;
					if (SpaceGame.rand.nextFloat() > 0.7) {
						if (altMode) {
							if (shotState * (weaponLevel * 0.25 + 1) > Math.pow(2, weaponLevel + 1)) {
								SpaceGame.newGameObjects.add(new Spark(
										(int) (this.rect.x + 8 * GameConstatnts.shipSize) + SpaceGame.rand.nextInt(33)
												- 16, this.rect.y + SpaceGame.rand.nextInt(33) - 16));
							}
						} else {
							if (shotState > 40) {
								SpaceGame.newGameObjects.add(new Spark(
										(int) (this.rect.x + 8 * GameConstatnts.shipSize) + SpaceGame.rand.nextInt(33)
												- 16, this.rect.y + SpaceGame.rand.nextInt(33) - 16));
							}
						}
					}
				}

			} else {
				if (this.key.equals("ship2")) {
					leveledShot2(weaponLevel, shotState);
				}
			}
		} else {
			deadTime--;
			if (SpaceGame.rand.nextInt(2) == 0) {
				SpaceGame.newGameObjects.add(new ExplodeObject((int) (SpaceGame.rand.nextInt(16)
						* GameConstatnts.shipSize + this.rect.x - 8 * GameConstatnts.shipSize), (int) (SpaceGame.rand
						.nextInt(16) * GameConstatnts.shipSize + this.rect.y - 8 * GameConstatnts.shipSize)));
			}
			if (deadTime == 0) {
				dead();
			}
		}
	}

	private void altLeveledShot1(int l) {
		createDroid(0, 0, (l + 1) * 50);
	}

	private void leveledShot0(int l) {
		switch (l) {
		case 0:
			shot0(0, 0, 0, 0);
			shot0(0, 0, 7, 0);
			shot0(0, 0, -7, 0);
			break;
		case 1:
			shot0(-12, 0, 0, 0);
			shot0(12, 0, 0, 0);
			shot0(0, 0, 12, 0);
			shot0(0, 0, -12, 0);
			break;
		case 2:
			shot0(-16, 8, -5, 0);
			shot0(16, 8, 5, 0);
			shot0(0, -8, 0, 0);

			shot0(16, 0, 12, 0);
			shot0(0, -12, 12, 0);
			shot0(-16, 0, -12, 0);
			shot0(0, -12, -12, 0);
			break;
		case 3:
			shot0(-16, 8, -5, 0);
			shot0(16, 8, 5, 0);
			shot0(-24, 16, -24, 0);
			shot0(24, 16, 24, 0);
			shot0(0, -8, 0, 0);

			shot0(16, 0, 12, 0);
			shot0(0, -12, 16, 0);
			shot0(-16, 0, -12, 0);
			shot0(0, -12, -16, 0);
			break;
		case 4:
			shot0(-16, 8, -5, 0);
			shot0(16, 8, 5, 0);

			shot0(-24, 16, -24, 0);
			shot0(24, 16, 24, 0);
			shot0(-24, 16, -28, 0);
			shot0(24, 16, 28, 0);

			shot0(0, -8, 0, 0);
			shot0(16, -8, 0, 0);
			shot0(-16, -8, 0, 0);

			shot0(16, 0, 12, 0);
			shot0(-16, 0, -12, 0);
			shot0(32, 0, 16, 0);
			shot0(-32, 0, -16, 0);

			shot0(0, -12, 16, 0);
			shot0(0, -12, -16, 0);
			break;
		case 5:
			for (int i = 0; i < 2; i++) {
				shot0(-16, 8 - i * 18, -5, 0);
				shot0(16, 8 - i * 18, 5, 0);

				shot0(-24, 16 - i * 18, -24, 0);
				shot0(24, 16 - i * 18, 24, 0);
				shot0(-24, 16 - i * 18, -28, 0);
				shot0(24, 16 - i * 18, 28, 0);

				shot0(0, -8 - i * 18, 0, 0);
				shot0(16, -8 - i * 18, 0, 0);
				shot0(-16, -8 - i * 18, 0, 0);

				shot0(16, 0 - i * 18, 12, 0);
				shot0(-16, 0 - i * 18, -12, 0);
				shot0(32, 0 - i * 18, 16, 0);
				shot0(-32, 0 - i * 18, -16, 0);

				shot0(0, -12 - i * 18, 16, 0);
				shot0(0, -12 - i * 18, -16, 0);
			}
			break;
		}

	}

	private void leveledShot1(int l) {
		switch (l) {
		case 0:
			shot1(0, 0, 0, 0);
			break;
		case 1:
			shot1(-16, 0, 0, 0);
			shot1(0, -8, 0, 0);
			shot1(16, 0, 0, 0);
			break;
		case 2:
			shot1(-16, 0, 0, 0);
			shot1(0, -8, 0, 0);
			shot1(16, 0, 0, 0);
			if (shotState % 2 == 0) {
				shot1(24, 24, 0, 0);
				shot1(32, 48, 0, 0);
			} else {
				shot1(-24, 24, 0, 0);
				shot1(-32, 48, 0, 0);
			}
			break;
		case 3:
			shot1(-16, 0, 0, 0);
			shot1(0, -8, 0, 0);
			shot1(16, 0, 0, 0);
			if (shotState % 2 == 0) {
				shot1(24, 24, 0, 0);
				shot1(32, 48, 0, 0);
				shot1(40, 72, 0, 0);

				shot1(40, 24, 0, 0);
				shot1(48, 48, 0, 0);
			} else {
				shot1(-24, 24, 0, 0);
				shot1(-32, 48, 0, 0);
				shot1(-40, 72, 0, 0);

				shot1(-40, 24, 0, 0);
				shot1(-48, 48, 0, 0);
			}
			break;
		case 4:
			shot1(-16, 0, 0, 0);
			shot1(0, -8, 0, 0);
			shot1(16, 0, 0, 0);
			shot1(-16, 16, 0, 0);
			shot1(0, 8, 0, 0);
			shot1(16, 16, 0, 0);

			if (shotState % 2 == 0) {
				shot1(24, 24, 0, 0);
				shot1(32, 48, 0, 0);
				shot1(40, 72, 0, 0);

				shot1(40, 24, 0, 0);
				shot1(48, 48, 0, 0);

				shot1(64, 24, 0, 0);
				shot1(72, 48, 0, 0);
				shot1(80, 72, 0, 0);

				shot1(80, 24, 0, 0);
				shot1(88, 48, 0, 0);
			} else {
				shot1(-24, 24, 0, 0);
				shot1(-32, 48, 0, 0);
				shot1(-40, 72, 0, 0);

				shot1(-40, 24, 0, 0);
				shot1(-48, 48, 0, 0);

				shot1(-64, 24, 0, 0);
				shot1(-72, 48, 0, 0);
				shot1(-80, 72, 0, 0);

				shot1(-80, 24, 0, 0);
				shot1(-88, 48, 0, 0);
			}
			break;
		case 5:
			shot1(-16, 0, 0, 0);
			shot1(0, -8, 0, 0);
			shot1(16, 0, 0, 0);
			shot1(-16, 16, 0, 0);
			shot1(0, 8, 0, 0);
			shot1(16, 16, 0, 0);

			shot1(24, 24, 0, 0);
			shot1(32, 48, 0, 0);
			shot1(40, 72, 0, 0);

			shot1(40, 24, 0, 0);
			shot1(48, 48, 0, 0);

			shot1(64, 24, 0, 0);
			shot1(72, 48, 0, 0);
			shot1(80, 72, 0, 0);

			shot1(80, 24, 0, 0);
			shot1(88, 48, 0, 0);

			shot1(-24, 24, 0, 0);
			shot1(-32, 48, 0, 0);
			shot1(-40, 72, 0, 0);

			shot1(-40, 24, 0, 0);
			shot1(-48, 48, 0, 0);

			shot1(-64, 24, 0, 0);
			shot1(-72, 48, 0, 0);
			shot1(-80, 72, 0, 0);

			shot1(-80, 24, 0, 0);
			shot1(-88, 48, 0, 0);

			break;
		}
	}

	private void leveledShot2(int l, int s) {
		if (altMode) {
			for (int i = 0; i < Math.min(s * (l * 0.25 + 1), Math.pow(2, l + 1)); i++) {
				shot1(0,
						0,
						(SpaceGame.rand.nextInt(15) + SpaceGame.rand.nextInt(15) + SpaceGame.rand.nextInt(15)
								+ SpaceGame.rand.nextInt(15) + SpaceGame.rand.nextInt(15) + SpaceGame.rand.nextInt(15)) / 6 - 7,
						10 - SpaceGame.rand.nextInt(5 + l));
			}
			shotState = 0;
		} else {
			if (s > 40) {
				shotState = -15;
			} else if (s < 0) {
				for (int i = 0; i < l + 1; i++) {
					shot1(((-shotState + i * 4) % 5 - 2) * 4, 0, 0, 0);
				}
				shotState++;
			}
		}
	}

	private void leveledShot3(int l) {
		if (altMode) {
			switch (l) {
			case 0:
				shot1(16 * (shotState % 5 - 2), 0, 0, 15);
				shot1(-16 * (shotState % 5 - 2), 0, 0, 15);
				break;
			case 1:
				for (int i = 0; i < 2; i++) {
					shot1(16 * (shotState % 5 - 2), 24 * i, 0, 15);
					shot1(-16 * (shotState % 5 - 2), 24 * i, 0, 15);
				}
				break;
			case 2:
				for (int i = 0; i < 3; i++) {
					shot1(16 * (shotState % 5 - 2 - i), 8 * i, 0, 15);
					shot1(-16 * (shotState % 5 - 2 - i), 8 * i, 0, 15);
				}
				break;
			case 3:
				for (int i = 0; i < 3; i++) {
					shot1(16 * ((shotState) % 11 - 5 - i), i * 12, (shotState % 11 - 5), 15);
					shot1(-16 * ((shotState) % 11 - 5 - i), i * 12, -(shotState % 11 - 5), 15);

					shot1(16 * ((shotState + 5) % 11 - 5 - i), i * 12, (shotState % 11 - 5), 15);
					shot1(-16 * ((shotState + 5) % 11 - 5 - i), i * 12, -(shotState % 11 - 5), 15);
				}
				break;
			case 4:
				for (int j = 0; j < 2; j++)
					for (int i = 0; i < 2; i++) {
						shot1(16 * ((shotState) % 11 - 5 - i), i * 12 + j * 24, (shotState % 11 - 5), 15 + j * 5);
						shot1(-16 * ((shotState) % 11 - 5 - i), i * 12 + j * 24, -(shotState % 11 - 5), 15 + j * 5);

						shot1(16 * ((shotState + 5) % 11 - 5 - i), i * 12 + j * 24, (shotState % 11 - 5), 15 + j * 5);
						shot1(-16 * ((shotState + 5) % 11 - 5 - i), i * 12 + j * 24, -(shotState % 11 - 5), 15 + j * 5);
					}
				break;
			case 5:
				for (int j = 0; j < 2; j++)
					for (int i = 0; i < 3; i++) {
						shot1(16 * ((shotState) % 11 - 5 - i), i * 12 + j * 24, (shotState % 11 - 5), 15 + j * 5);
						shot1(-16 * ((shotState) % 11 - 5 - i), i * 12 + j * 24, -(shotState % 11 - 5), 15 + j * 5);

						shot1(16 * ((shotState + 5) % 11 - 5 - i), i * 12 + j * 24, (shotState % 11 - 5), 15 + j * 5);
						shot1(-16 * ((shotState + 5) % 11 - 5 - i), i * 12 + j * 24, -(shotState % 11 - 5), 15 + j * 5);
					}
				break;
			}
		} else {
			switch (l) {
			case 0:
				shot2(0, 0, -15, 2);
				break;
			case 1:
				shot2(12, 0, -15, 2);
				shot2(-12, 0, -15, 2);
				break;
			case 2:
				shot2(16, 0, -20, 3);
				shot2(-16, 0, -20, 3);
				break;
			case 3:
				shot2(0, 0, -30, 6);
				break;
			case 4:
				shot2(16, 0, -30, 6);
				shot2(-16, 0, -30, 6);
				break;
			case 5:
				shot2(0, 0, -40, 18);
				break;
			}
		}
	}

	public void shot0(int xo, int yo, int horisontalSpeed, int verticalSpeed) {
		SpaceGame.newGameObjects.add(new PlayerShotObject().setSpeed(

				!altMode ? horisontalSpeed
						+ (SpaceGame.keyboard[KeyEvent.VK_RIGHT] ? speed / 2
								: SpaceGame.keyboard[KeyEvent.VK_LEFT] ? -speed / 2 : 0) : 0

				, -30 + (!altMode ? (verticalSpeed) : 0)).setLocation(
				(int) (rect.x + xo + (altMode ? horisontalSpeed : 0) + 4 * GameConstatnts.shipSize) + this.offset.x,
				rect.y - rect.height + yo + (altMode ? verticalSpeed * 2 : 0) + this.offset.y));
	}

	public void shot1(int xo, int yo, int horisontalSpeed, int verticalSpeed) {
		SpaceGame.newGameObjects.add(new PlayerShotObject().setSpeed(horisontalSpeed, -30 + verticalSpeed).setLocation(
				this.offset.x + (int) (rect.x + xo + 4 * GameConstatnts.shipSize),
				rect.y - rect.height + yo + this.offset.y));
	}

	public void createDroid(int xo, int yo, int lifeTime) {
		SpaceGame.newGameObjects.add(new Droid(lifeTime).setLocation(this.offset.x
				+ (int) (rect.x + xo + 4 * GameConstatnts.shipSize), rect.y - rect.height + yo + this.offset.y));
	}

	public void shot2(int xo, int yo, int spd, int size) {
		SpaceGame.newGameObjects.add(new PlasmaPlayerShotObject(size).setSpeed(0, spd).setLocation(
				this.offset.x
						+ (int) (rect.x + xo + (8 - (int) (8 * (1 + 0.05f * size) / 2)) * GameConstatnts.shipSize),
				rect.y - rect.height + yo + this.offset.y));
	}

	@Override
	public void pressKey(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_X) {
			altMode = !altMode;
		}
		if (e.getKeyCode() == KeyEvent.VK_1) {
			weaponLevel++;
			if (weaponLevel > 5) {
				weaponLevel = 5;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			weaponLevel--;
			if (weaponLevel < 0) {
				weaponLevel = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_3) {
			GameState.score += 1;
			GameState.score *= 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			GameState.score /= 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_5) {
			this.hp *= 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_6) {
			GameState.level++;
		}
		super.pressKey(e);
	}

	public static String getName(String string) {
		switch (string) {
		case "ship0":
			return "SHPIN";
		case "ship1":
			return "CHASR";
		case "ship2":
			return "SKNOT";
		case "ship3":
			return "PLONG";
		}
		return "NaN";
	}

	public static String getDiscription(String string) {
		switch (string) {
		case "ship0":
			return "Speed: Normal/nWeapon power: Normal/nDefence: Normal/nOther: Normal";
		case "ship1":
			return "It has one generator and two consumers. Make your choice!";
		case "ship2":
			return "Somebody, tighten the lock nuts!";
		case "ship3":
			return "I hope it is more dangerous to his enemies than for me ...";
		}
		return "NaN";
	}

}
