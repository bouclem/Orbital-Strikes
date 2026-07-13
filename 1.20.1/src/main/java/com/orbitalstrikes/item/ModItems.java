package com.orbitalstrikes.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.orbitalstrikes.OrbitalStrikes;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OrbitalStrikes.MOD_ID);

    public static final RegistryObject<Item> ORBITAL_CANNON = ITEMS.register("orbital_cannon",
            () -> new OrbitalCannonItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
