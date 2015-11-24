package ru.asemty.game;

import java.util.ArrayList;

import ru.asemty.game.objects.enemy.BigShip0;
import ru.asemty.game.objects.enemy.EnemyShip;
import ru.asemty.game.objects.enemy.StoneMeteor;

public abstract class SpawnRule {
	public abstract void spawn();

	public static ArrayList<ArrayList<SpawnRule>> rules;

	public static void rulesInit() {
		rules = new ArrayList<ArrayList<SpawnRule>>();
		for (int i = 0; i < 3; i++) {
			rules.add(new ArrayList<SpawnRule>());
		}
		gen0();
		gen1();
		gen2();
	}

	public static void gen0() {

		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 12; i++) {
					create(1, GameConstatnts.screenWidth / 2, -64 * i);
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(2, GameConstatnts.screenWidth / 3, 0);
				create(2, GameConstatnts.screenWidth * 2 / 3, 0);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(0, GameConstatnts.screenWidth / 2, -128);
				create(0, GameConstatnts.screenWidth / 2, -64);
				create(0, GameConstatnts.screenWidth / 2, 0);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(0, GameConstatnts.screenWidth / 3, 0);
				create(1, GameConstatnts.screenWidth * 2 / 3, 0);
				create(1, GameConstatnts.screenWidth / 9 * 2, -64);
				create(0, GameConstatnts.screenWidth * 7 / 9, -64);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(0, GameConstatnts.screenWidth / 4, 0);
				create(2, GameConstatnts.screenWidth / 2, 0);
				create(0, GameConstatnts.screenWidth / 4 * 3, 0);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(2, GameConstatnts.screenWidth / 4, -64);
				create(0, GameConstatnts.screenWidth / 2, 0);
				create(2, GameConstatnts.screenWidth / 4 * 3, -64);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(1, GameConstatnts.screenWidth / 8, -64);
				create(2, GameConstatnts.screenWidth / 2, 0);
				create(1, GameConstatnts.screenWidth / 8 * 9, -64);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					create(0, GameConstatnts.screenWidth / 7 * i, 0);
				}
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(0, GameConstatnts.screenWidth / 4, 0);
				create(1, GameConstatnts.screenWidth / 2, 0);
				create(0, GameConstatnts.screenWidth / 4 * 3, 0);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(2, GameConstatnts.screenWidth / 4, -64);
				create(0, GameConstatnts.screenWidth / 4, 0);
				create(1, GameConstatnts.screenWidth / 2, 0);
				create(0, GameConstatnts.screenWidth / 4 * 3, 0);
				create(2, GameConstatnts.screenWidth / 4 * 3, -64);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(2, GameConstatnts.screenWidth / 4, -128);
				create(1, GameConstatnts.screenWidth / 4, -64);
				create(2, GameConstatnts.screenWidth / 2, 0);
				create(1, GameConstatnts.screenWidth / 4 * 3, -64);
				create(2, GameConstatnts.screenWidth / 4 * 3, -128);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(1, GameConstatnts.screenWidth / 4, -64);
				create(2, GameConstatnts.screenWidth / 2, 0);
				create(1, GameConstatnts.screenWidth / 4 * 3, -64);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(2, GameConstatnts.screenWidth / 2, 0);
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(1, GameConstatnts.screenWidth / 4, -64);
				create(1, GameConstatnts.screenWidth / 2, -128);
				create(1, GameConstatnts.screenWidth / 4 * 3, -64);
				for (int i = 1; i < 7; i++) {
					create(0, GameConstatnts.screenWidth / 7 * i, 0);
				}
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					create(1, GameConstatnts.screenWidth / 7 * i, 0);
				}
			}

		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 4; j++) {
					for (int i = 1; i < 7; i++) {
						create(0, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 3; j++) {
					for (int i = 1; i < 4; i++) {
						create(1, GameConstatnts.screenWidth / 4 * i, j * -64);
					}
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 8 * i, SpaceGame.rand.nextInt(6) * -128, 5, 0.2f);
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 8 * i, SpaceGame.rand.nextInt(6) * -128, 5, 0.5f);
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 3; i++) {
					createMetheor(4, GameConstatnts.screenWidth / 4 * i, i * -300, 5, 1f);
				}
			}
		});
		rules.get(0).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					createMetheor(5, GameConstatnts.screenWidth / 3 * (SpaceGame.rand.nextInt(2) + 1), -280 * i, 2, 1f);
				}
			}
		});
	}

	public static void gen1() {
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 5; i++) {
					createMetheor(6, GameConstatnts.screenWidth / 8 * (SpaceGame.rand.nextInt(7) + 1), -384 * i, 4,
							0.95f);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 3; j++) {
					for (int i = 1; i < 4; i++) {
						create(2, GameConstatnts.screenWidth / 4 * i, j * -64);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 4; j++) {
					for (int i = 1; i < 7; i++) {
						create(1, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(3, GameConstatnts.screenWidth / 4, -64);
				create(4, GameConstatnts.screenWidth / 2, -128);
				create(3, GameConstatnts.screenWidth / 4 * 3, -64);
				for (int i = 1; i < 7; i++) {
					create(1, GameConstatnts.screenWidth / 7 * i, 0);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				create(4, GameConstatnts.screenWidth / 4, -64);
				create(4, GameConstatnts.screenWidth / 2, -128);
				create(4, GameConstatnts.screenWidth / 4 * 3, -64);
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					create(2, GameConstatnts.screenWidth / 7 * i, 0);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 16; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 16 * i, SpaceGame.rand.nextInt(10) * -128, 5, 0.5f);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 12; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 16 * i, SpaceGame.rand.nextInt(10) * -128, 5, 0.5f);
				}
				create(4, GameConstatnts.screenWidth / 4, -64 - 128);
				create(4, GameConstatnts.screenWidth / 2, -128 - 128);
				create(4, GameConstatnts.screenWidth / 4 * 3, -64 - 128);
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 6; j++) {
					for (int i = 1; i < 7; i++) {
						if (j < 5) {
							create(1, GameConstatnts.screenWidth / 7 * i, j * -64);
						} else {
							create(2, GameConstatnts.screenWidth / 7 * i, j * -64);
						}
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 3; j++) {
					for (int i = 1; i < 2; i++) {
						create(2, GameConstatnts.screenWidth / 3 * i, j * -64);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 12; i++) {
					createMetheor(SpaceGame.rand.nextInt(2) + 2,
							GameConstatnts.screenWidth / 16 * SpaceGame.rand.nextInt(16), SpaceGame.rand.nextInt(10)
									* -128, 10, 1f);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 3; j++) {
					for (int i = 1; i < 10; i++) {
						create(2, GameConstatnts.screenWidth / 4 * j, i * -128);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 5; i++) {
					create(3, GameConstatnts.screenWidth / 2, i * -64);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 5; i++) {
					create(4, GameConstatnts.screenWidth / 2, i * -64);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 4; i++) {
					create(3, GameConstatnts.screenWidth / 8 * i, -256 * i);
				}
				for (int i = 1; i < 7; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 8 * i, SpaceGame.rand.nextInt(6) * -128, 5, 0);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 7; i++) {
					createMetheor(1, GameConstatnts.screenWidth / 8 * i, SpaceGame.rand.nextInt(6) * -128, 12, 0);
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 5; j++) {
					for (int i = 1; i < 4; i++) {
						create(j, GameConstatnts.screenWidth / 3 * i, -64 * j);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 5; j++) {
					for (int i = 1; i < 4; i++) {
						create(j, GameConstatnts.screenWidth / 6 * (i + 1), -64 * j);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 1; j < 3; j++) {
					for (int i = 1; i < 4; i++) {
						create(1, GameConstatnts.screenWidth / 4 * i, j * -64);
					}
				}
			}
		});
		rules.get(1).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 12; j++) {
					for (int i = 1; i < 7; i++) {
						create(0, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});

	}

	public static void gen2() {
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 6; j++) {
					for (int i = 1; i < 7; i++) {
						create(1, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 4; j++) {
					for (int i = 1; i < 7; i++) {
						create(2, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					for (int i = 1; i < 7; i++) {
						create(3, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					for (int i = 1; i < 7; i++) {
						create(4, GameConstatnts.screenWidth / 7 * i, j * -64);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				createBigShip0(GameConstatnts.screenWidth / 2, -64);
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				createBigShip1(GameConstatnts.screenWidth / 2, -64);
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				createBigShip0(GameConstatnts.screenWidth / 3, -64);
				createBigShip1(GameConstatnts.screenWidth / 3 * 2, -64);
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					createBigShip0(GameConstatnts.screenWidth / 4 * (j + 1), -64);
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					createBigShip1(GameConstatnts.screenWidth / 4 * (j + 1), -64);
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 2; j++) {
					createBigShip1(GameConstatnts.screenWidth / 3 * (j + 1), -64);
				}
				for (int j = 0; j < 2; j++) {
					for (int i = 1; i < 7; i++) {
						create(4, GameConstatnts.screenWidth / 7 * i, j * -64 - 128);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					createBigShip0(GameConstatnts.screenWidth / 4 * (j + 1), -64);
				}
				for (int j = 0; j < 2; j++) {
					for (int i = 1; i < 7; i++) {
						create(3, GameConstatnts.screenWidth / 7 * i, j * -64 - 128);
					}
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 5; j++) {
					for (int i = 1; i < 7; i++) {
						create(2, GameConstatnts.screenWidth / 7 * i, j * -64 - 128);
					}
				}
				for (int j = 0; j < 3; j++) {
					createBigShip0(GameConstatnts.screenWidth / 4 * (j + 1), -64);
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int j = 0; j < 3; j++) {
					createBigShip1(GameConstatnts.screenWidth / 4 * (j + 1), -64);
				}
				for (int j = 0; j < 3; j++) {
					createBigShip0(GameConstatnts.screenWidth / 4 * (j + 1), -64-128);
				}
			}
		});
		rules.get(2).add(new SpawnRule() {

			@Override
			public void spawn() {
				for (int i = 1; i < 25; i++) {
					createMetheor(6, GameConstatnts.screenWidth / 8 * (SpaceGame.rand.nextInt(7) + 1), -384 * i, 7,
							0.95f);
				}
			}
		});
	}

	public static void create(int n, int x, int y) {
		SpaceGame.evilGameObjects.add(new EnemyShip(n).setLocation((int) (x - 8 * GameConstatnts.shipSize),
				(int) (y - 8 * GameConstatnts.shipSize)));
	}

	public static void createBigShip0(int x, int y) {
		SpaceGame.evilGameObjects.add(new BigShip0().setLocation((int) (x - 16 * GameConstatnts.shipSize),
				(int) (y - 8 * GameConstatnts.shipSize)));
	}

	public static void createBigShip1(int x, int y) {
		SpaceGame.evilGameObjects.add(new BigShip0().setLocation((int) (x - 16 * GameConstatnts.shipSize),
				(int) (y - 8 * GameConstatnts.shipSize)));
	}

	public static void createMetheor(int s, int x, int y, int speed, float softChance) {
		for (int i = -s + 1; i < s; i++) {
			for (int j = -s + 1; j < s; j++) {
				if ((Math.abs(i) + Math.abs(j)) <= (s - 1) * 1.5)
					SpaceGame.evilGameObjects.add(new StoneMeteor(Math.random() < softChance).setLocation(x + i * 32,
							y + j * 32).setSpeed(0, speed));
			}
		}
	}

	public static void randSpawn(int floor) {
		int c = Math.min(floor, 2);
		while (SpawnRule.rules.get(c).size() == 0) {
			c--;
			if (c == -1) {
				return;
			}
		}
		SpawnRule.rules.get(c).get(SpaceGame.rand.nextInt(SpawnRule.rules.get(c).size())).spawn();
	}

	public static void spawn(int floor, int n) {
		if (SpawnRule.rules != null && floor < SpawnRule.rules.size() && n < SpawnRule.rules.get(floor).size()) {
			SpawnRule.rules.get(floor).get(n).spawn();
		}
	}
}
