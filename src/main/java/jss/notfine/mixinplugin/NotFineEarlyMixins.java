package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jss.notfine.NotFine;
import mist475.mcpatcherforge.asm.AsmTransformers;
import mist475.mcpatcherforge.asm.mappings.Namer;
import mist475.mcpatcherforge.mixins.Mixins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Some code adapted from Hodgepodge
@IFMLLoadingPlugin.Name("NotFineEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class NotFineEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

    public static final Logger mcpfLogger = LogManager.getLogger("MCPatcher");

    private String[] transformerClasses;

    @Override
    public String getMixinConfig() {
        return "mixins.notfine.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        NotFine.logger.info("Kicking off NotFine early mixins.");

        List<String> mixins = new ArrayList<>();

        mixins.add("minecraft.clouds.MixinEntityRenderer");
        mixins.add("minecraft.clouds.MixinGameSettings");
        mixins.add("minecraft.clouds.MixinRenderGlobal");
        mixins.add("minecraft.clouds.MixinWorldType");

        mixins.add("minecraft.faceculling.MixinBlock");
        mixins.add("minecraft.faceculling.MixinBlockSlab");
        mixins.add("minecraft.faceculling.MixinBlockSnow");
        mixins.add("minecraft.faceculling.MixinBlockStairs");
        mixins.add("minecraft.faceculling.MixinRenderBlocks");

        mixins.add("minecraft.glint.MixinItemRenderer");
        mixins.add("minecraft.glint.MixinRenderBiped");
        mixins.add("minecraft.glint.MixinRenderItem");
        mixins.add("minecraft.glint.MixinRenderPlayer");

        mixins.add("minecraft.gui.MixinGuiSlot");

        mixins.add("minecraft.leaves.MixinBlockLeaves");
        mixins.add("minecraft.leaves.MixinBlockLeavesBase");

        mixins.add("minecraft.particles.MixinBlockEnchantmentTable");
        mixins.add("minecraft.particles.MixinEffectRenderer");
        mixins.add("minecraft.particles.MixinWorldClient");
        mixins.add("minecraft.particles.MixinWorldProvider");

        mixins.add("minecraft.toggle.MixinGuiIngame");
        mixins.add("minecraft.toggle.MixinEntityRenderer");
        mixins.add("minecraft.toggle.MixinRender");
        mixins.add("minecraft.toggle.MixinRenderItem");

        mixins.add("minecraft.MixinGameSettings");
        mixins.add("minecraft.MixinRenderGlobal");

        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Mixins.Phase.EARLY) {
                if (mixin.shouldLoad(loadedCoreMods, Collections.emptySet())) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        mcpfLogger.info("Not loading the following EARLY mixins: {}", notLoading.toString());

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        if (transformerClasses == null) {
            Namer.initNames();
            transformerClasses = AsmTransformers.getTransformers();
        }
        return transformerClasses;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
