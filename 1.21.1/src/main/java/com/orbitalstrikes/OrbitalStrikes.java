package com.orbitalstrikes;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import com.orbitalstrikes.item.ModItems;
import com.orbitalstrikes.entity.ModEntities;

@Mod(OrbitalStrikes.MOD_ID)
public class OrbitalStrikes {
    public static final String MOD_ID = "orbitalstrikes";

    public OrbitalStrikes(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        modEventBus.addListener(OrbitalStrikes::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.ORBITAL_CANNON);
        }
    }
}
