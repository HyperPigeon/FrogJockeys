package net.hyper_pigeon.frogjockeys.entity.ai.tasks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;

public class FrogEatProjectileTask extends MultiTickTask<FrogEntity> {
    public static final int RUN_TIME = 100;
    public static final int CATCH_DURATION = 6;
    public static final int EAT_DURATION = 10;
    private static final float MAX_DISTANCE = 1.75F;
    private static final float VELOCITY_MULTIPLIER = 0.75F;
    public static final int UNREACHABLE_TONGUE_TARGETS_START_TIME = 100;
    public static final int MAX_UNREACHABLE_TONGUE_TARGETS = 5;
    private int eatTick;
    private int moveToTargetTick;
    private final SoundEvent tongueSound;
    private final SoundEvent eatSound;
    private Vec3d targetPos;
    private FrogEatProjectileTask.Phase phase = FrogEatProjectileTask.Phase.DONE;

    public FrogEatProjectileTask(SoundEvent tongueSound, SoundEvent eatSound) {
        super(
                ImmutableMap.of(
                        MemoryModuleType.WALK_TARGET,
                        MemoryModuleState.VALUE_ABSENT,
                        MemoryModuleType.LOOK_TARGET,
                        MemoryModuleState.REGISTERED,
                        MemoryModuleType.ATTACK_TARGET,
                        MemoryModuleState.VALUE_PRESENT,
                        MemoryModuleType.IS_PANICKING,
                        MemoryModuleState.VALUE_ABSENT
                ),
                100
        );
        this.tongueSound = tongueSound;
        this.eatSound = eatSound;
    }

    static enum Phase {
        MOVE_TO_TARGET,
        CATCH_ANIMATION,
        EAT_ANIMATION,
        DONE;

        private Phase() {
        }
    }
}
