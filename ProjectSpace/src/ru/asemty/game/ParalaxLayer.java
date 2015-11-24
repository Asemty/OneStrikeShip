package ru.asemty.game;

import java.awt.Graphics;
import java.awt.Image;

public class ParalaxLayer {
	public Image img;
	public float speed;
	public float var;

	public ParalaxLayer(Image img, float speed) {
		this.img = img;
		this.speed = speed;
	}

	public void draw(Graphics gr, boolean isMove) {
		if (isMove) {
			var += speed;
			if (var > GameConstatnts.screenHeights) {
				var -= GameConstatnts.screenHeights;
			}
		}
		gr.drawImage(img, 0, (int) var, null);
		gr.drawImage(img, 0, (int) var - GameConstatnts.screenHeights, null);
	}
}
