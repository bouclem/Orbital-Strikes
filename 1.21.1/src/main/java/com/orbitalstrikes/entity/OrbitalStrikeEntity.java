package com.orbitalstrikes.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class OrbitalStrikeEntity extends ThrowableProjectile {
    private static final int CLUSTER_COUNT = 40;
    private static final double CLUSTER_RADIUS = 10.0;
    private static final float SMALL_EXPLOSION_RADIUS = 5.0f;
    private static final float PROJECTILE_SPEED = 3.0f;
    private static final int MAX_LIFETIME = 200;

    private boolean detonated = false;

    public OrbitalStrikeEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public OrbitalStrikeEntity(EntityType<? extends ThrowableProjectile> type, LivingEntity owner, Level level) {
        super(type, owner, level);
        this.setNoGravity(true);
    }

    public void shootToward(Vec3 targetPosition) {
        Vec3 startPos = this.position();
        Vec3 direction = targetPosition.subtract(startPos).normalize();
        this.shoot(direction.x, direction.y, direction.z, PROJECTILE_SPEED, 0.0f);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        detonate();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        detonate();
    }

    private void detonate() {
        if (detonated) return;
        detonated = true;

        if (!this.level().isClientSide()) {
            Vec3 pos = this.position();
            for (int i = 0; i < CLUSTER_COUNT; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * CLUSTER_RADIUS * 2;
                double offsetY = (this.random.nextDouble() - 0.5) * CLUSTER_RADIUS * 2;
                double offsetZ = (this.random.nextDouble() - 0.5) * CLUSTER_RADIUS * 2;
                this.level().explode(null, pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                        SMALL_EXPLOSION_RADIUS, false, Level.ExplosionInteraction.TNT);
            }
        }
        this.discard();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            for (int i = 0; i < 4; i++) {
                this.level().addParticle(ParticleTypes.FLAME,
                        this.getX() + (this.random.nextDouble() - 0.5) * 0.5,
                        this.getY() + (this.random.nextDouble() - 0.5) * 0.5,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 0.5,
                        0.0, 0.0, 0.0);
            }
        }
        if (!this.level().isClientSide() && this.tickCount > MAX_LIFETIME) {
            detonate();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }
}
