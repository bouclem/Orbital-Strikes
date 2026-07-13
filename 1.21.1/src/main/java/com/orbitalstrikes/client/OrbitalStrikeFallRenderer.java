package com.orbitalstrikes.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.orbitalstrikes.entity.OrbitalStrikeFallEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class OrbitalStrikeFallRenderer extends EntityRenderer<OrbitalStrikeFallEntity> {
    public OrbitalStrikeFallRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(OrbitalStrikeFallEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(OrbitalStrikeFallEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("orbitalstrikes", "textures/entity/orbital_strike_fall.png");
    }
}
