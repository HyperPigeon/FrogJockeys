package net.hyper_pigeon.frogjockeys.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.EntityLookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(FrogEntity.class)
public abstract class FrogEntityMixin extends AnimalEntity implements VariantHolder<FrogVariant> {

    private int catchTicks = 0;


    @Shadow public abstract Optional<Entity> getFrogTarget();

    @Shadow public abstract Brain<FrogEntity> getBrain();

    protected FrogEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
//
    public double getHeightOffset() {
        return 0.45;
    }
//
//    private void eat(ServerWorld world, FrogEntity frog, Entity entity) {
//        world.playSoundFromEntity(null, frog, SoundEvents.ENTITY_FROG_TONGUE, SoundCategory.NEUTRAL, 2.0F, 1.0F);
//        if(entity.isAlive()){
//            entity.remove(RemovalReason.DISCARDED);
//        }
//    }
//
//    public void eatProjectile(ProjectileEntity projectileEntity){
//        catchTicks = 5;
//        if(!this.getEntityWorld().isClient()) {
//            eat((ServerWorld) this.getEntityWorld(),(FrogEntity)(Object)this, projectileEntity);
//        }
//    }
//
//    public void catchProjectile(ProjectileEntity projectileEntity, @Nullable Entity mount) {
//        System.out.println(projectileEntity != null);
//        System.out.println(projectileEntity);
//        if(projectileEntity != null) {
//            System.out.println("reached2");
//            if(mount != null || projectileEntity instanceof PotionEntity)  {
//                System.out.println("reached4");
//                this.lookAtEntity(projectileEntity,30,60);
//                eatProjectile(projectileEntity);
//            }
//        }
//    }
//
//    @Inject(method = "tick", at = @At("HEAD"))
//    public void projectileCatchingTick(CallbackInfo ci){
//        Entity mount = getVehicle();
//        ProjectileEntity projectileEntity;
//        try {
//            projectileEntity = this.getEntityWorld().
//                    getEntitiesByClass(ProjectileEntity.class,
//                            this.getBoundingBox().expand(10),
//                            projectile -> !projectile.isOnGround()).stream().findFirst().get();
//        } catch(Exception e){
//            projectileEntity = null;
//        }
//
//        catchProjectile(projectileEntity, mount);
//
//    }

//    @Inject(method = "tick", at = @At("HEAD"))
//    public void projectileCatchingTick(CallbackInfo ci){
//        List<ProjectileEntity> projectileEntityList = this.getEntityWorld().getEntitiesByClass(ProjectileEntity.class,
//                this.getBoundingBox().expand(10),
//                projectile -> !projectile.isOnGround() && this.canSee(projectile));
//        if(!projectileEntityList.isEmpty()) {
//            this.getBrain().remember(MemoryModuleType.LOOK_TARGET, new EntityLookTarget(projectileEntityList.get(0), true));
//            this.setPose(EntityPose.USING_TONGUE);
//        }
//    }

}
