package net.hyper_pigeon.frogjockeys.mixin;

import net.hyper_pigeon.frogjockeys.entity.ai.goals.SpiderRiderAttackGoal;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpiderEntity.class)
public abstract class SpiderEntityMixin extends HostileEntity {

    protected SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void addSpiderRiderAttackGoal(CallbackInfo ci){
        this.goalSelector.add(4, new SpiderRiderAttackGoal((SpiderEntity)(Object)this));
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    public void initializeFrogRider(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir){
        SpiderEntity spiderEntity = ((SpiderEntity)(Object)this);
        Random random = world.getRandom();
        if (!spiderEntity.hasPassengers()
                && (world.getBiome(spiderEntity.getBlockPos()).getKey().get().equals(BiomeKeys.SWAMP)
                || world.getBiome(spiderEntity.getBlockPos()).getKey().get().equals(BiomeKeys.MANGROVE_SWAMP))
                ) {
            FrogEntity frogEntity = EntityType.FROG.create(((SpiderEntity)(Object)this).getWorld());
            if (frogEntity != null) {
                frogEntity.refreshPositionAndAngles(((SpiderEntity)(Object)this).getX(), ((SpiderEntity)(Object)this).getY(), ((SpiderEntity)(Object)this).getZ(), ((SpiderEntity)(Object)this).getYaw(), 0.0F);
                frogEntity.initialize(world, difficulty, spawnReason, null, null);
                frogEntity.startRiding(((SpiderEntity)(Object)this));
            }
        }
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        if(this.hasPassengers() && this.getFirstPassenger() instanceof FrogEntity) {
            return null;
        }
        return super.getControllingPassenger();
    }



}
