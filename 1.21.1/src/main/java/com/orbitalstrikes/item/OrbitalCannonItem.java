package com.orbitalstrikes.item;

import com.orbitalstrikes.entity.ModEntities;
import com.orbitalstrikes.entity.OrbitalStrikeEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class OrbitalCannonItem extends Item {
    private static final int COOLDOWN_TICKS = 40;
    private static final int MAX_RANGE = 100;

    public OrbitalCannonItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!player.isCreative() && player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(itemStack);
        }

        if (!level.isClientSide()) {
            Vec3 eyePosition = player.getEyePosition();
            Vec3 lookVector = player.getLookAngle();
            Vec3 endPosition = eyePosition.add(lookVector.scale(MAX_RANGE));

            BlockHitResult hitResult = level.clip(new ClipContext(
                    eyePosition,
                    endPosition,
                    ClipContext.Block.OUTLINE,
                    ClipContext.Fluid.NONE,
                    player
            ));

            Vec3 targetPosition;
            if (hitResult.getType() != HitResult.Type.MISS) {
                targetPosition = hitResult.getLocation();
            } else {
                targetPosition = endPosition;
            }

            OrbitalStrikeEntity projectile = new OrbitalStrikeEntity(
                    ModEntities.ORBITAL_STRIKE.get(), player, level);
            projectile.shootToward(targetPosition);
            level.addFreshEntity(projectile);

            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
