package ru.asemty.game.objects.ai;

import ru.asemty.game.GameConstatnts;
import ru.asemty.game.objects.enemy.Enemy;
import ru.asemty.game.objects.enemy.PartableBoss;

public class EnemyAIPartableBoss extends EnemyAI{

	PartableBoss pb;
	public EnemyAIPartableBoss(Enemy e) {
		super(e);
		pb=(PartableBoss)owner;
	}

	@Override
	protected void stepAI() {
		if(this.owner.vSpd!=0 && this.owner.rect.y>GameConstatnts.screenHeights/8){
			this.owner.vSpd=0;
		}else{
			if(this.owner.rect.x<(int) (16*GameConstatnts.shipSize)){
				pb.dir=1;
			}
			if(this.owner.rect.x>GameConstatnts.screenWidth-(int) (32*GameConstatnts.shipSize)){
				pb.dir=-1;
			}
			this.owner.rect.x+=((PartableBoss)owner).dir;
			if(pb.ld.hp<=0 && pb.l.ai==null){
				pb.l.ai= new EnemyAIPartBoss0(pb.l);
			}
			if(pb.rd.hp<=0 && pb.r.ai==null){
				pb.r.ai= new EnemyAIPartBoss0(pb.r);
			}
			if(pb.cd.hp<=0){
				if(this.currentStep%100>=80 && this.currentStep%3==0)
				this.shot(0, 0, 0, 0);
			}else{
				if(this.currentStep%40>30 && this.currentStep%4==0)
					this.shot(0, 0, 0, 0);
			}
		}
	}

	@Override
	protected void initAI() {
		this.owner.setSpeed(0, 2);
	}

}
