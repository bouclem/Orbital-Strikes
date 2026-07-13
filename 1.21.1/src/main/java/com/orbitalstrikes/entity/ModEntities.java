package com.orbitalstrikes.entity;

import com.orbitalstrikes.OrbitalStrikes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OrbitalStrikes.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<OrbitalStrikeEntity>> ORBITAL_STRIKE =
            ENTITIES.register("orbital_strike", () -> EntityType.Builder
                    .<OrbitalStrikeEntity>of(OrbitalStrikeEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .build("orbital_strike"));

    public static final DeferredHolder<EntityType<?>, EntityType<OrbitalStrikeFallEntity>> ORBITAL_STRIKE_FALL =
            ENTITIES.register("orbital_strike_fall", () -> EntityType.Builder
                    .<OrbitalStrikeFallEntity>of(OrbitalStrikeFallEntity::new, MobCategory.MISC)
                    .sized(2.0f, 2.0f)
                    .clientTrackingRange(6)
                    .build("orbital_strike_fall"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
