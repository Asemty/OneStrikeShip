package ru.asemty.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paralax {

	public static ArrayList<ParalaxLayer> layers;
	public static Color fog;
	static float startSpd;
	static float spdFactor;
	public static boolean isMove;

	public static void draw(Graphics gr) {
		if(layers!=null){
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).draw(gr,isMove);
		}
		}
	}

	public static void initParalaxLayer() {
		layers = new ArrayList<ParalaxLayer>();
		startSpd=0.1f;
		spdFactor=2f;
		isMove=true;
		fog= new Color(GameConstatnts.backgroundColor.getRed(),GameConstatnts.backgroundColor.getGreen(),GameConstatnts.backgroundColor.getBlue(),100);

		BufferedImage[] bImg = new BufferedImage[3];
		for (int l = 0; l < 3; l++) {
			bImg[l]=new BufferedImage(GameConstatnts.screenWidth, GameConstatnts.screenHeights,
					BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < -l*15+40; i++) {
				SpaceGame.sprSheet.draw(bImg[l].getGraphics(), "star" + SpaceGame.rand.nextInt(4),
						SpaceGame.rand.nextInt(bImg[l].getWidth()), SpaceGame.rand.nextInt(bImg[l].getHeight()), SpaceGame.rand.nextFloat() +l);
			}
			for(int ll=0;ll<l;ll++){
			Graphics gr= bImg[l].getGraphics();
			gr.setColor(fog);
			gr.fillRect(0, 0,GameConstatnts.screenWidth, GameConstatnts.screenHeights);}
			layers.add(new ParalaxLayer(bImg[l], l*spdFactor+startSpd));
		}
	}
	public static void setSpeed(float startSpeed,float speedFactor){
		startSpd=startSpeed;
		spdFactor=speedFactor;
		for(int i=0;i<layers.size();i++){
			layers.get(i).speed=i*spdFactor+startSpd;
		}
	}
	
	public static void pause(){
		isMove=!isMove;
	}
	public static void pause(boolean flag){
		isMove=!flag;
	}
}
