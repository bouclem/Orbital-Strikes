# Orbital Strikes - Project Overview

## What It Is

Orbital Strikes is a Minecraft mod that adds an "Orbital Cannon" item. When right-clicked, it raycasts up to 100 blocks in the player's look direction and creates a TNT-sized explosion at the target location. A 5-second (100 tick) cooldown prevents spamming.

## Architecture

### Package Structure (both versions)

```
com.orbitalstrikes
├── OrbitalStrikes.java       — Mod entrypoint, creative tab registration
└── item
    ├── ModItems.java          — Deferred item registration
    └── OrbitalCannonItem.java — Cannon item logic (raycast + explosion + cooldown)
```

### Resource Structure

```
assets/orbitalstrikes/
├── lang/en_us.json                    — English localization
├── models/item/orbital_cannon.json    — Item model (handheld_rod parent)
└── textures/item/orbital_cannon.png   — Custom texture (user-provided)
```

### Platform Differences

| Aspect              | 1.21.1 (NeoForge)              | 1.20.1 (Forge)                   |
|---------------------|--------------------------------|----------------------------------|
| Java                | 21                             | 21                               |
| Build Tool          | ModDevGradle 0.1.110           | ForgeGradle 6.x                  |
| Registration        | `DeferredRegister.Items`       | `DeferredRegister` + `ForgeRegistries.ITEMS` |
| Item Holder         | `DeferredItem<Item>`           | `RegistryObject<Item>`           |
| Mod Constructor     | `(IEventBus, ModContainer)`    | `FMLJavaModLoadingContext.get()`  |
| Metadata File       | `neoforge.mods.toml`           | `mods.toml`                      |
| Gradle              | 8.8                            | 8.8                              |

## Design Direction

- **v0.1.0**: Basic item + creeper boom effect (current)
- **Future**: 3D model, laser visual effect, sound design, targeting reticle, ammo system
