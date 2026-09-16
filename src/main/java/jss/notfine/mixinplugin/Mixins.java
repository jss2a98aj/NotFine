package jss.notfine.mixinplugin;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

import jss.notfine.config.NotFineConfig;
import jss.notfine.config.MCPatcherForgeConfig;

public enum Mixins implements IMixins {

    NOTFINE_CORE(new MixinBuilder("NotFine")
        .setPhase(Phase.EARLY)
        .addClientMixins(
            "minecraft.clouds.MixinEntityRenderer",
            "minecraft.clouds.MixinGameSettings",
            "minecraft.clouds.MixinRenderGlobal",
            "minecraft.clouds.MixinWorldType",

            "minecraft.fix.MixinRenderItem",

            "minecraft.gui.MixinGuiSlot",

            "minecraft.glint.MixinRenderBiped",
            "minecraft.glint.MixinRenderPlayer",

            "minecraft.optimization.MixinRenderItemFrame",

            "minecraft.leaves.MixinBlockLeaves",
            "minecraft.leaves.MixinBlockLeavesBase",

            "minecraft.particles.MixinBlockEnchantmentTable",
            "minecraft.particles.MixinEffectRenderer",
            "minecraft.particles.MixinWorldClient",

            "minecraft.renderer.MixinRenderGlobal",

            "minecraft.toggle.MixinEntityRenderer",
            "minecraft.toggle.MixinGuiIngame",
            "minecraft.toggle.MixinRender",
            "minecraft.toggle.MixinRenderItem",

            "minecraft.interpolatedtexturemap.MixinTextureMap"
        )
    ),
    BETTER_FACE_CULLING(new MixinBuilder("Better face culling")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> NotFineConfig.betterBlockFaceCulling)
        .addClientMixins(
            "minecraft.faceculling.MixinBlock",
            "minecraft.faceculling.MixinBlockCactus",
            "minecraft.faceculling.MixinBlockCarpet",
            "minecraft.faceculling.MixinBlockEnchantmentTable",
            "minecraft.faceculling.MixinBlockFarmland",
            "minecraft.faceculling.MixinBlockSlab",
            "minecraft.faceculling.MixinBlockSnow",
            "minecraft.faceculling.MixinBlockStairs",
            "minecraft.faceculling.MixinRenderBlocks"
        )
    ),
    NO_DYNAMIC_SURROUNDINGS(new MixinBuilder("No Dynamic Surroundings")
        .setPhase(Phase.EARLY)
        .addExcludedMod(TargetedMod.DYNAMIC_SURROUNDINGS_MIST)
        .addExcludedMod(TargetedMod.DYNAMIC_SURROUNDINGS_ORIGINAL)
        .addClientMixins("minecraft.toggle.MixinEntityRenderer$RenderRainSnow")
    ),
    NO_CUSTOM_ITEM_TEXTURES(new MixinBuilder("No Custom Item Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> !MCPatcherForgeConfig.instance().customItemTexturesEnabled)
        .addClientMixins(
            "minecraft.glint.MixinItemRenderer",
            "minecraft.glint.MixinRenderItem"
        )
    ),
    BOP(new MixinBuilder("Biomes O' Plenty compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.BOP)
        .addClientMixins(
            "leaves.bop.MixinBlockBOPAppleLeaves",
            "leaves.bop.MixinBlockBOPColorizedLeaves",
            "leaves.bop.MixinBlockBOPLeaves"
        )
    ),
    NATURA(new MixinBuilder("Natura compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.NATURA)
        .addClientMixins(
            "leaves.natura.MixinBerryBush",
            "leaves.natura.MixinNetherBerryBush"
        )
    ),
    THAUMCRAFT(new MixinBuilder("Thaumcraft compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.THAUMCRAFT)
        .addClientMixins("leaves.thaumcraft.MixinBlockMagicalLeaves")
    ),
    THAUMCRAFT_BETTER_FACE_CULLING(new MixinBuilder("Better face culling Thaumcraft compat")
        .setPhase(Phase.LATE)
        .setApplyIf(() -> NotFineConfig.betterBlockFaceCulling)
        .addRequiredMod(TargetedMod.THAUMCRAFT)
        .addClientMixins(
            "faceculling.thaumcraft.MixinBlockWoodenDevice",
            "faceculling.thaumcraft.MixinBlockStoneDevice",
            "faceculling.thaumcraft.MixinBlockTable"
        )
    ),
    TINKERS_CONSTRUCT(new MixinBuilder("Tinker's Construct compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.TINKERS_CONSTRUCT)
        .addClientMixins("leaves.tconstruct.MixinOreberryBush")
    ),
    TWILIGHT_FOREST_ANY(new MixinBuilder("Twilight Forest compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.TWILIGHT_FOREST_ANY)
        .addClientMixins(
            "leaves.twilightforest.MixinBlockTFLeaves",
            "leaves.twilightforest.MixinBlockTFLeaves3"
        )
    ),
    TWILIGHT_FOREST_GTNH(new MixinBuilder("Twilight Forest GTNH fork compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.TWILIGHT_FOREST_ANY)
        .addExcludedMod(TargetedMod.TWILIGHT_FOREST_ORIGINAL)
        .addClientMixins("leaves.twilightforest.MixinBlockTFMagicLeaves")
    ),
    WITCHERY(new MixinBuilder("Witchery compat")
        .setPhase(Phase.LATE)
        .addRequiredMod(TargetedMod.WITCHERY)
        .addClientMixins("leaves.witchery.MixinBlockWitchLeaves")
    ),
    MCPATCHER_FORGE(new MixinBuilder("MCPatcher Forge")
        .setPhase(Phase.EARLY)
        .addClientMixins(
            "mcpatcherforge.base.MixinBlockGrass",
            "mcpatcherforge.base.MixinBlockMycelium",

            "mcpatcherforge.base.MixinAbstractTexture",
            "mcpatcherforge.base.MixinTextureAtlasSprite",

            "mcpatcherforge.base.MixinSimpleReloadableResourceManager",

            "mcpatcherforge.base.MixinMinecraft"
        )
    ),
    MCPATCHER_FORGE_CUSTOM_COLORS(new MixinBuilder("MCP:F Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().customColorsEnabled)
        .addClientMixins(
            "mcpatcherforge.cc.block.material.MixinMapColor",

            "mcpatcherforge.cc.block.MixinBlock",
            "mcpatcherforge.cc.block.MixinBlockDoublePlant",
            "mcpatcherforge.cc.block.MixinBlockGrass",
            "mcpatcherforge.cc.block.MixinBlockLeaves",
            "mcpatcherforge.cc.block.MixinBlockLilyPad",
            "mcpatcherforge.cc.block.MixinBlockLiquid",
            "mcpatcherforge.cc.block.MixinBlockOldLeaf",
            "mcpatcherforge.cc.block.MixinBlockRedstoneWire",
            "mcpatcherforge.cc.block.MixinBlockReed",
            "mcpatcherforge.cc.block.MixinBlockStem",
            "mcpatcherforge.cc.block.MixinBlockTallGrass",
            "mcpatcherforge.cc.block.MixinBlockVine",

            "mcpatcherforge.cc.client.particle.MixinEntityAuraFX",
            "mcpatcherforge.cc.client.particle.MixinEntityBubbleFX",
            "mcpatcherforge.cc.client.particle.MixinEntityDropParticleFX",
            "mcpatcherforge.cc.client.particle.MixinEntityPortalFX",
            "mcpatcherforge.cc.client.particle.MixinEntityRainFX",
            "mcpatcherforge.cc.client.particle.MixinEntityRedDustFX",
            "mcpatcherforge.cc.client.particle.MixinEntitySplashFX",
            "mcpatcherforge.cc.client.particle.MixinEntitySuspendFX",

            "mcpatcherforge.cc.client.renderer.entity.MixinRenderWolf",
            "mcpatcherforge.cc.client.renderer.entity.MixinRenderXPOrb",

            "mcpatcherforge.cc.client.renderer.tileentity.MixinTileEntitySignRenderer",

            "mcpatcherforge.cc.client.renderer.MixinEntityRenderer",
            "mcpatcherforge.cc.client.renderer.MixinItemRenderer",
            "mcpatcherforge.cc.client.renderer.MixinRenderBlocks",
            "mcpatcherforge.cc.client.renderer.MixinRenderGlobal",

            "mcpatcherforge.cc.entity.MixinEntityList",

            "mcpatcherforge.cc.item.crafting.MixinRecipesArmorDyes",

            "mcpatcherforge.cc.item.MixinItemArmor",
            "mcpatcherforge.cc.item.MixinItemBlock",
            "mcpatcherforge.cc.item.MixinItemMonsterPlacer",

            "mcpatcherforge.cc.potion.MixinPotion",
            "mcpatcherforge.cc.potion.MixinPotionHelper",

            "mcpatcherforge.cc.world.MixinWorld",
            "mcpatcherforge.cc.world.MixinWorldProvider",
            "mcpatcherforge.cc.world.MixinWorldProviderEnd",
            "mcpatcherforge.cc.world.MixinWorldProviderHell"
        )
    ),
    MCPATCHER_FORGE_CUSTOM_ITEM_TEXTURES(new MixinBuilder("MCP:F Custom Item Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().customItemTexturesEnabled)
        .addClientMixins(
            "mcpatcherforge.cit.client.renderer.entity.MixinRenderBiped",
            "mcpatcherforge.cit.client.renderer.entity.MixinRenderEntityLiving",
            "mcpatcherforge.cit.client.renderer.entity.MixinRenderItem",
            "mcpatcherforge.cit.client.renderer.entity.MixinRenderPlayer",
            "mcpatcherforge.cit.client.renderer.entity.MixinRenderSnowball",
            "mcpatcherforge.cit.client.renderer.MixinItemRenderer",
            "mcpatcherforge.cit.client.renderer.MixinRenderGlobal",
            "mcpatcherforge.cit.entity.MixinEntityLivingBase",
            "mcpatcherforge.cit.item.MixinItem",
            "mcpatcherforge.cit.nbt.MixinNBTTagCompound",
            "mcpatcherforge.cit.nbt.MixinNBTTagList",
            "mcpatcherforge.cit.world.MixinWorld"
        )
    ),
    MCPATCHER_FORGE_CONNECTED_TEXTURES(new MixinBuilder("MCP:F Connected Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled)
        .addClientMixins("mcpatcherforge.ctm.MixinRenderBlocks")
    ),
    MCPATCHER_FORGE_EXTENDED_HD(new MixinBuilder("MCP:F Extended hd")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().extendedHDEnabled)
        .addClientMixins(
            "mcpatcherforge.hd.MixinTextureClock",
            "mcpatcherforge.hd.MixinTextureCompass",
            "mcpatcherforge.hd.MixinTextureManager"
        )
    ),
    MCPATCHER_FORGE_EXTENDED_HD_FONT(new MixinBuilder("MCP:F Extended HD Font")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> (MCPatcherForgeConfig.instance().extendedHDEnabled && MCPatcherForgeConfig.instance().hdFont))
        .addExcludedMod(TargetedMod.COFHCORE)
        .addClientMixins("mcpatcherforge.hd.MixinFontRenderer")
    ),
    MCPATCHER_FORGE_RANDOM_MOBS(new MixinBuilder("MCP:F Random Mobs")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().randomMobsEnabled)
        .addClientMixins(
            "mcpatcherforge.mob.MixinRender",
            "mcpatcherforge.mob.MixinRenderEnderman",
            "mcpatcherforge.mob.MixinRenderFish",
            "mcpatcherforge.mob.MixinRenderLiving",
            "mcpatcherforge.mob.MixinRenderMooshroom",
            "mcpatcherforge.mob.MixinRenderSheep",
            "mcpatcherforge.mob.MixinRenderSnowMan",
            "mcpatcherforge.mob.MixinRenderSpider",
            "mcpatcherforge.mob.MixinRenderWolf",
            "mcpatcherforge.mob.MixinEntityLivingBase"
        )
    ),
    MCPATCHER_FORGE_SKY(new MixinBuilder("MCP:F Sky")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().betterSkiesEnabled)
        .addClientMixins(
            "mcpatcherforge.sky.MixinEffectRenderer",
            "mcpatcherforge.sky.MixinRenderGlobal"
        )
    ),
    MCPATCHER_FORGE_CC_NO_CTM(new MixinBuilder("MCP:F Custom Colors, no Connected Textures")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> !MCPatcherForgeConfig.instance().connectedTexturesEnabled
                && MCPatcherForgeConfig.instance().customColorsEnabled)
        .addClientMixins("mcpatcherforge.cc_ctm.MixinRenderBlocksNoCTM")
    ),
    MCPATCHER_FORGE_CTM_NO_CC(new MixinBuilder("MCP:F Connected Textures, no Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
                && !MCPatcherForgeConfig.instance().customColorsEnabled)
        .addClientMixins("mcpatcherforge.ctm_cc.MixinRenderBlocksNoCC")
    ),
    MCPATCHER_FORGE_CTM_AND_CC(new MixinBuilder("MCP:F Connected Textures and Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
            && MCPatcherForgeConfig.instance().customColorsEnabled)
        .addClientMixins("mcpatcherforge.ctm_cc.MixinRenderBlocks")
    ),
    MCPATCHER_FORGE_CTM_OR_CC(new MixinBuilder("MCP:F Connected Textures or Custom Colors")
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> MCPatcherForgeConfig.instance().connectedTexturesEnabled
                || MCPatcherForgeConfig.instance().customColorsEnabled)
        .addClientMixins("mcpatcherforge.ctm_cc.MixinTextureMap")
    );

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
