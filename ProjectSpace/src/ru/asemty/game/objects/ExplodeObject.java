package ru.asemty.game.objects;

import ru.asemty.game.SpaceGame;

public class ExplodeObject extends GameObject{
	int currentStep;
	public ExplodeObject(int x,int y) {
		super("explode0");
		currentStep=0;
		this.setLocation(x, y);
	}
	@Override
	public void step() {
		super.step();
		currentStep++;
		this.key="explode"+currentStep/5;
		if(currentStep==20){
			SpaceGame.delGameObjects.add(this);
		}
	}

}
