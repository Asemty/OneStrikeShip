package ru.asemty.game.objects;

import ru.asemty.game.SpaceGame;

public class Spark extends GameObject{
	int currentStep;
	public Spark(int x,int y) {
		super("plShotDead");
		currentStep=0;
		this.setLocation(x, y);
		this.sizeMult=0.5f;
	}
	@Override
	public void step() {
		super.step();
		currentStep++;
		if(currentStep==10){
			SpaceGame.delGameObjects.add(this);
		}
	}
}
