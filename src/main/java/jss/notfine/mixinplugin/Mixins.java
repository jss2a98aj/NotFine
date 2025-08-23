package jss.notfine.mixinplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import jss.notfine.config.NotFineConfig;
import jss.notfine.config.MCPatcherForgeConfig;

// Adapted from Hodgepodge
public enum Mixins {

    NOTFINE_CORE(new Builder("NotFine")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("minecraft.",
            "clouds.MixinEntityRenderer",
            "clouds.MixinGameSettings",
            "clouds.MixinRenderGlobal",
            "clouds.MixinWorldType",

            "fix.MixinRenderItem",

            "gui.MixinGuiSlot",

            "glint.MixinRenderBiped",
            "glint.MixinRenderPlayer",

            "optimization.MixinRenderItemFrame",

            "leaves.MixinBlockLeaves",
            "leaves.MixinBlockLeavesBase",

            "particles.MixinBlockEnchantmentTable",
            "particles.MixinEffectRenderer",
            "particles.MixinWorldClient",

            "renderer.MixinRenderGlobal",

            "toggle.MixinEntityRenderer",
            "toggle.MixinGuiIngame",
            "toggle.MixinRender",
            "toggle.MixinRenderItem",

            "interpolatedtexturemap.MixinTextureMap"
        ))
    ),
    BETTER_FACE_CULLING(new Builder("Better face culling")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> NotFineConfig.betterBlockFaceCulling)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("minecraft.faceculling.",
            "MixinBlock",
            "MixinBlockCactus",
            "MixinBlockCarpet",
            "MixinBlockEnchantmentTable",
            "MixinBlockFarmland",
            "MixinBlockSlab",
            "MixinBlockSnow",
            "MixinBlockStairs",
            "MixinRenderBlocks"
        ))
    ),
    NO_DYNAMIC_SURROUNDINGS(new Builder("No Dynamic Surroundings")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.VANILLA)
        .addExcludedMod(TargetedMod.DYNAMIC_SURROUNDINGS_MIST)
        .addExcludedMod(TargetedMod.DYNAMIC_SURROUNDINGS_ORIGINAL)
        .addMixinClasses("minecraft.toggle.MixinEntityRenderer$RenderRainSnow")
    ),
    NO_CUSTOM_ITEM_TEXTURES(new Builder("No Custom Item Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> !MCPatcherForgeConfig.instance().customItemTexturesEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("minecraft.glint.",
            "MixinItemRenderer",
            "MixinRenderItem"
        ))
    ),
    NATURA(new Builder("Natura compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.NATURA)
        .addMixinClasses(addPrefix("leaves.natura.",
            "MixinBerryBush",
            "MixinNetherBerryBush"
        ))
    ),
    THAUMCRAFT(new Builder("Thaumcraft compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.THAUMCRAFT)
        .addMixinClasses("leaves.thaumcraft.MixinBlockMagicalLeaves")
    ),
    THAUMCRAFT_BETTER_FACE_CULLING(new Builder("Better face culling Thaumcraft compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> NotFineConfig.betterBlockFaceCulling)
        .addTargetedMod(TargetedMod.THAUMCRAFT)
        .addMixinClasses(addPrefix("faceculling.thaumcraft.",
            "MixinBlockWoodenDevice",
            "MixinBlockStoneDevice",
            "MixinBlockTable"
        ))
    ),
    TINKERS_CONSTRUCT(new Builder("Tinker's Construct compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.TINKERS_CONSTRUCT)
        .addMixinClasses("leaves.tconstruct.MixinOreberryBush")
    ),
    WITCHERY(new Builder("Witchery compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.WITCHERY)
        .addMixinClasses("leaves.witchery.MixinBlockWitchLeaves")
    ),
    MCPATCHER_FORGE(new Builder("MCPatcher Forge")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.",
              "base.MixinBlockGrass",
              "base.MixinBlockMycelium",

              "base.MixinAbstractTexture",
              "base.MixinTextureAtlasSprite",

              "base.MixinSimpleReloadableResourceManager",

              "base.MixinMinecraft"
        ))
    ),
    MCPATCHER_FORGE_CUSTOM_COLORS(new Builder("MCP:F Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().customColorsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.cc.",
            "block.material.MixinMapColor",

            "block.MixinBlock",
            "block.MixinBlockDoublePlant",
            "block.MixinBlockGrass",
            "block.MixinBlockLeaves",
            "block.MixinBlockLilyPad",
            "block.MixinBlockLiquid",
            "block.MixinBlockOldLeaf",
            "block.MixinBlockRedstoneWire",
            "block.MixinBlockReed",
            "block.MixinBlockStem",
            "block.MixinBlockTallGrass",
            "block.MixinBlockVine",

            "client.particle.MixinEntityAuraFX",
            "client.particle.MixinEntityBubbleFX",
            "client.particle.MixinEntityDropParticleFX",
            "client.particle.MixinEntityPortalFX",
            "client.particle.MixinEntityRainFX",
            "client.particle.MixinEntityRedDustFX",
            "client.particle.MixinEntitySplashFX",
            "client.particle.MixinEntitySuspendFX",

            "client.renderer.entity.MixinRenderWolf",
            "client.renderer.entity.MixinRenderXPOrb",

            "client.renderer.tileentity.MixinTileEntitySignRenderer",

            "client.renderer.MixinEntityRenderer",
            "client.renderer.MixinItemRenderer",
            "client.renderer.MixinRenderBlocks",
            "client.renderer.MixinRenderGlobal",

            "entity.MixinEntityList",

            "item.crafting.MixinRecipesArmorDyes",

            "item.MixinItemArmor",
            "item.MixinItemBlock",
            "item.MixinItemMonsterPlacer",

            "potion.MixinPotion",
            "potion.MixinPotionHelper",

            "world.MixinWorld",
            "world.MixinWorldProvider",
            "world.MixinWorldProviderEnd",
            "world.MixinWorldProviderHell"
        ))
    ),
    MCPATCHER_FORGE_CUSTOM_ITEM_TEXTURES(new Builder("MCP:F Custom Item Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().customItemTexturesEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.cit.",
            "client.renderer.entity.MixinRenderBiped",
            "client.renderer.entity.MixinRenderEntityLiving",
            "client.renderer.entity.MixinRenderItem",
            "client.renderer.entity.MixinRenderPlayer",
            "client.renderer.entity.MixinRenderSnowball",
            "client.renderer.MixinItemRenderer",
            "client.renderer.MixinRenderGlobal",
            "entity.MixinEntityLivingBase",
            "item.MixinItem",
            "nbt.MixinNBTTagCompound",
            "nbt.MixinNBTTagList",
            "world.MixinWorld"
        ))
    ),
    MCPATCHER_FORGE_CONNECTED_TEXTURES(new Builder("MCP:F Connected Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses("mcpatcherforge.ctm.MixinRenderBlocks")
    ),
    MCPATCHER_FORGE_EXTENDED_HD(new Builder("MCP:F Extended hd")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().extendedHDEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.hd.",
            "MixinTextureClock",
            "MixinTextureCompass",
            "MixinTextureManager"
        ))
    ),
    MCPATCHER_FORGE_EXTENDED_HD_FONT(new Builder("MCP:F Extended HD Font")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> (MCPatcherForgeConfig.instance().extendedHDEnabled && MCPatcherForgeConfig.instance().hdFont))
        .addTargetedMod(TargetedMod.VANILLA)
        .addExcludedMod(TargetedMod.COFHCORE)
        .addMixinClasses("mcpatcherforge.hd.MixinFontRenderer")
    ),
    MCPATCHER_FORGE_RANDOM_MOBS(new Builder("MCP:F Random Mobs")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().randomMobsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.mob.",
            "MixinRender",
            "MixinRenderEnderman",
            "MixinRenderFish",
            "MixinRenderLiving",
            "MixinRenderMooshroom",
            "MixinRenderSheep",
            "MixinRenderSnowMan",
            "MixinRenderSpider",
            "MixinRenderWolf",
            "MixinEntityLivingBase"
        ))
    ),
    MCPATCHER_FORGE_SKY(new Builder("MCP:F Sky")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().betterSkiesEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses(addPrefix("mcpatcherforge.sky.",
            "MixinEffectRenderer",
            "MixinRenderGlobal"
        ))
    ),
    MCPATCHER_FORGE_CC_NO_CTM(new Builder("MCP:F Custom Colors, no Connected Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> !MCPatcherForgeConfig.instance().connectedTexturesEnabled
                && MCPatcherForgeConfig.instance().customColorsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses("mcpatcherforge.cc_ctm.MixinRenderBlocksNoCTM")
    ),
    MCPATCHER_FORGE_CTM_NO_CC(new Builder("MCP:F Connected Textures, no Custom Colours")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
                && !MCPatcherForgeConfig.instance().customColorsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses("mcpatcherforge.ctm_cc.MixinRenderBlocksNoCC")
    ),
    MCPATCHER_FORGE_CTM_AND_CC(new Builder("MCP:F Connected Textures and Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
            && MCPatcherForgeConfig.instance().customColorsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses("mcpatcherforge.ctm_cc.MixinRenderBlocks")
    ),
    MCPATCHER_FORGE_CTM_OR_CC(new Builder("MCP:F Connected Textures or Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
                || MCPatcherForgeConfig.instance().customColorsEnabled)
        .addTargetedMod(TargetedMod.VANILLA)
        .addMixinClasses("mcpatcherforge.ctm_cc.MixinTextureMap")
    );

    public final String name;
    public final List<String> mixinClasses;
    private final Supplier<Boolean> applyIf;
    public final Phase phase;
    public final List<TargetedMod> targetedMods;
    public final List<TargetedMod> excludedMods;

    private static class Builder {

        private final String name;
        private final List<String> mixinClasses = new ArrayList<>();
        private Supplier<Boolean> applyIf;
        private Phase phase = Phase.LATE;
        private final List<TargetedMod> targetedMods = new ArrayList<>();
        private final List<TargetedMod> excludedMods = new ArrayList<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addMixinClasses(String... mixinClasses) {
            this.mixinClasses.addAll(Arrays.asList(mixinClasses));
            return this;
        }

        public Builder setPhase(Phase phase) {
            this.phase = phase;
            return this;
        }

        public Builder setApplyIf(Supplier<Boolean> applyIf) {
            this.applyIf = applyIf;
            return this;
        }

        public Builder addTargetedMod(TargetedMod mod) {
            this.targetedMods.add(mod);
            return this;
        }

        public Builder addExcludedMod(TargetedMod mod) {
            this.excludedMods.add(mod);
            return this;
        }
    }

    Mixins(Builder builder) {
        this.name = builder.name;
        this.mixinClasses = builder.mixinClasses;
        this.applyIf = builder.applyIf;
        this.targetedMods = builder.targetedMods;
        this.excludedMods = builder.excludedMods;
        this.phase = builder.phase;
        if (this.targetedMods.isEmpty()) {
            throw new RuntimeException("No targeted mods specified for " + this.name);
        }
        if (this.applyIf == null) {
            throw new RuntimeException("No ApplyIf function specified for " + this.name);
        }
    }

    private boolean allModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return false;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && !loadedCoreMods.contains(target.coreModClass)) return false;
            else if (!loadedMods.isEmpty() && target.modId != null && !loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    private boolean noModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return true;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && loadedCoreMods.contains(target.coreModClass)) return false;
            else if (!loadedMods.isEmpty() && target.modId != null && loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    public boolean shouldLoad(Set<String> loadedCoreMods, Set<String> loadedMods) {
        return (applyIf.get()
            && allModsLoaded(targetedMods, loadedCoreMods, loadedMods)
            && noModsLoaded(excludedMods, loadedCoreMods, loadedMods));
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    private static String[] addPrefix(String prefix, String... values) {
        return Arrays.stream(values)
            .map(s -> prefix + s)
            .collect(Collectors.toList())
            .toArray(new String[values.length]);
    }

    public enum Phase {
        EARLY,
        LATE,
    }
}
