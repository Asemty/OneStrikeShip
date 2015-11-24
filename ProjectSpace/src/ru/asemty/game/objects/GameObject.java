package ru.asemty.game.objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.SpaceGame;

public class GameObject {
	public Rectangle rect;
	public Point offset;
	public double vSpd,hSpd;
	public String key;
	public float sizeMult=1;

	public GameObject(String key) {
		this.key = key;
		rect= new Rectangle();
		this.offset= new Point();
	}

	public GameObject setLocation(int x, int y) {
		this.rect.x = x;
		this.rect.y = y;
		return this;
	}
	public GameObject setOffset(int xOffset, int yOffset) {
		this.offset= new Point((int) (xOffset*GameConstatnts.shipSize), (int) (yOffset*GameConstatnts.shipSize));
		return this;
	}

	public GameObject setSize(int w, int h) {
		this.rect.width = (int) (w*GameConstatnts.shipSize);
		this.rect.height = (int) (h*GameConstatnts.shipSize);
		return this;
	}

	public GameObject setSpeed(int hS, int vS) {
		this.vSpd = vS;
		this.hSpd = hS;
		return this;
	}

	public GameObject setMotion(double dir, float speed) {
		this.vSpd = Math.sin(dir) * speed;
		this.hSpd = Math.cos(dir) * speed;
		return this;
	}

	public void step() {
		// TODO Auto-generated method stub
		this.rect.x += this.hSpd;
		this.rect.y += this.vSpd;
	}

	public void pressKey(KeyEvent e) {
	}
	
	public void touched(GameObject neightbot){
		
	}
	public void dead(){
		SpaceGame.delGameObjects.add(this);
	}
}
