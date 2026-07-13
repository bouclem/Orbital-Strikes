package com.orbitalstrikes.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.orbitalstrikes.OrbitalStrikes;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OrbitalStrikes.MOD_ID);

    public static final DeferredItem<Item> ORBITAL_CANNON = ITEMS.register("orbital_cannon",
            () -> new OrbitalCannonItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
