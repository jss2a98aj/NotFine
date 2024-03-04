# NotFine

Extra video settings and performance improvements for Minecraft 1.7.10, implemented with Mixins.

# Future Plans

- Adding several similar features as compared to Optifine.
- More mod support for new leaf rendering modes.
- Shader support if used as part of [Angelica](https://github.com/GTNewHorizons/Angelica) (WIP).

# Requirements

- [UniMixins](https://github.com/LegacyModdingMC/UniMixins)

# Known Issues

- Always Translucent and Default(vanilla) cloud translucency will cause clouds to render over liquids.

# Known Conflicts

- [Angelica](https://github.com/GTNewHorizons/Angelica) | Incompatible because Angelica includes NotFine.
- [Leaf Culling](https://modrinth.com/mod/leafculling) | Incompatible due to overlap. Suggested if you just want "Smart" leaf rendering.
- Optifine | Cannot be made compatible.

# New Leaf Rendering Mode Mod Compatibility

## Mods that properly implement leaf blocks and do not need patched

- [Et Futurum Requiem](https://modrinth.com/mod/etfuturum)
- [ExtrabiomesXL](https://github.com/jss2a98aj/ExtrabiomesXL) 3.16.7 or later

## Mods with leaf blocks that have been patched

- [Thaumcraft 4](https://www.curseforge.com/minecraft/mc-mods/thaumcraft/files/2227552)
- [Twilight Forest](https://www.curseforge.com/minecraft/mc-mods/the-twilight-forest) 2.4 or later for full support
- [Witchery](https://www.curseforge.com/minecraft/mc-mods/witchery)

# Mod Suggestions
- [Thaumcraft 4 Tweaks](https://modrinth.com/mod/tc4tweaks) when using Thaumcraft
- [WitcheryExtras](https://github.com/GTNewHorizons/WitcheryExtras) when using Witchery

# Credits

* Improvements brought over from Angelica (LGPL 3)
  * mitchej123, Omni, Makamys, Embeddedt, NanoLive, Caedis, Cardinalstar, Alexdoru, Eigenraven, mist475, Clepto
  * Everyone else mentioned in [Angelica's credits](https://github.com/GTNewHorizons/Angelica/tree/master?tab=readme-ov-file#credits)
* Some Sodium menu code that came with Angelica improvements (LGPL 3)
  * JellySquid and the CaffeineMC team
* Additional localizations
  * Darkbum, Radk6, and fireorice5
* Includes MCPatcherForge:
  * Original [MCPatcher](https://bitbucket.org/prupe/mcpatcher/src/master/): Paul Rupe
  * Mixin port: [MCPatcherForge](https://github.com/mist475/MCPatcherForge): Mist475, textures by Darkbum under CC-BY-SA
