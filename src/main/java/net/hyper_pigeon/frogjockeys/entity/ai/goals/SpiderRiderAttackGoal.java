package net.hyper_pigeon.frogjockeys.entity.ai.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.FrogEntity;

public class SpiderRiderAttackGoal extends MeleeAttackGoal {
    public SpiderRiderAttackGoal(SpiderEntity spider) {
        super(spider, 1.0, true);
    }

    public boolean canStart() {
        return super.canStart() && (this.mob.hasPassengers() && this.mob.getFirstPassenger() instanceof FrogEntity);
    }

    public boolean shouldContinue() {
        float f = this.mob.getBrightnessAtEyes();
        if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
            this.mob.setTarget(null);
            return false;
        } else {
            return super.shouldContinue();
        }
    }

    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return (double)(4.0F + entity.getWidth());
    }
}
