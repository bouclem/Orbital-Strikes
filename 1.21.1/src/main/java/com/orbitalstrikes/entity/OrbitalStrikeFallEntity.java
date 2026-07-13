package com.orbitalstrikes.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OrbitalStrikeFallEntity extends Entity {
    private static final float FINAL_EXPLOSION_RADIUS = 20.0f;
    private static final double DESCENT_SPEED = 0.8;
    private static final int BEDROCK_Y = -64;
    private static final double CYLINDER_RADIUS = 8.0;
    private static final int CARVE_LAYERS_PER_TICK = 5;

    private boolean detonated = false;
    private boolean carving = false;
    private int carveY = Integer.MIN_VALUE;
    private int carveCX = 0;
    private int carveCZ = 0;

    public OrbitalStrikeFallEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public OrbitalStrikeFallEntity(EntityType<?> type, Level level, Vec3 position) {
        super(type, level);
        this.setPos(position.x, position.y, position.z);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(ParticleTypes.FLAME,
                        this.getX() + (this.random.nextDouble() - 0.5) * 2.0,
                        this.getY() + (this.random.nextDouble() - 0.5) * 2.0,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 2.0,
                        0.0, 0.0, 0.0);
            }
            for (int i = 0; i < 4; i++) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                        this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
                        this.getY() + (this.random.nextDouble() - 0.5) * 3.0,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
                        0.0, 0.1, 0.0);
            }
        }

        if (!this.level().isClientSide()) {
            if (carving) {
                carveTick();
                return;
            }

            if (!detonated) {
                this.setPos(this.getX(), this.getY() - DESCENT_SPEED, this.getZ());

                BlockState stateBelow = this.level().getBlockState(
                        BlockPos.containing(this.getX(), this.getY() - 1, this.getZ()));
                if (!stateBelow.isAir() || this.getY() <= BEDROCK_Y) {
                    detonate();
                }
            }
        }
    }

    private void detonate() {
        if (detonated) return;
        detonated = true;

        if (!this.level().isClientSide()) {
            Vec3 pos = this.position();
            this.level().explode(null, pos.x, pos.y, pos.z,
                    FINAL_EXPLOSION_RADIUS, true, Level.ExplosionInteraction.TNT);

            carving = true;
            carveY = (int) pos.y;
            carveCX = (int) pos.x;
            carveCZ = (int) pos.z;
        }
    }

    private void carveTick() {
        if (!carving) return;
        int layersDone = 0;
        while (layersDone < CARVE_LAYERS_PER_TICK && carveY >= BEDROCK_Y) {
            for (int x = (int) -CYLINDER_RADIUS; x <= CYLINDER_RADIUS; x++) {
                for (int z = (int) -CYLINDER_RADIUS; z <= CYLINDER_RADIUS; z++) {
                    if (x * x + z * z <= CYLINDER_RADIUS * CYLINDER_RADIUS) {
                        BlockPos blockPos = new BlockPos(carveCX + x, carveY, carveCZ + z);
                        BlockState state = this.level().getBlockState(blockPos);
                        if (!state.isAir() && state.getBlock().defaultDestroyTime() >= 0) {
                            this.level().removeBlock(blockPos, false);
                        }
                    }
                }
            }
            carveY--;
            layersDone++;
        }
        if (carveY < BEDROCK_Y) {
            carving = false;
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }
}
