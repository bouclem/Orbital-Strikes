package com.orbitalstrikes.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.orbitalstrikes.entity.OrbitalStrikeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class OrbitalStrikeRenderer extends EntityRenderer<OrbitalStrikeEntity> {
    public OrbitalStrikeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(OrbitalStrikeEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(OrbitalStrikeEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("orbitalstrikes", "textures/entity/orbital_strike.png");
    }
}
