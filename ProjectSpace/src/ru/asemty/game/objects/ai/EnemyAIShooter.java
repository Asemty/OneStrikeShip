package ru.asemty.game.objects.ai;

import ru.asemty.game.GameState;
import ru.asemty.game.objects.enemy.Enemy;

public class EnemyAIShooter extends EnemyAI {

	public EnemyAIShooter(Enemy e) {
		super(e);
	}

	@Override
	public void stepAI() {
		if (owner.rect.y > -64) {
		if(this.currentStep%(60-6*GameState.getDifficult())==0){
			this.shot(0, 0, 0, 0);
		}
		if(GameState.player!=null){
			owner.rect.x-=Math.signum(owner.rect.x-GameState.player.rect.x)*GameState.getDifficult();
		}}
	}

	@Override
	public void initAI() {
		owner.setSpeed(0, 4);
	}

}
