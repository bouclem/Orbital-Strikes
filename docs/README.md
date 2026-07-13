# Orbital Strikes

A Minecraft mod that adds the **Orbital Cannon** — a weapon that calls down explosive strikes at a targeted location.

## Versions

| Minecraft | Loader  | Project Folder |
|-----------|---------|----------------|
| 1.21.1    | NeoForge| `1.21.1/`      |
| 1.20.1    | Forge   | `1.20.1/`      |

## Features (v0.1.0)

- **Orbital Cannon** item with a custom fishing rod-style texture
- Right-click to fire a raycast (100 block range) and create a creeper-sized explosion at the target
- 5-second cooldown between shots

## Setup

1. Navigate to the desired version folder (`1.21.1/` or `1.20.1/`)
2. Run `gradlew build` to compile
3. Run `gradlew runClient` to test in-game

## Texture

Place your custom texture at:
```
src/main/resources/assets/orbitalstrikes/textures/item/orbital_cannon.png
```

## License

Apache License 2.0

## Author

sqersters
