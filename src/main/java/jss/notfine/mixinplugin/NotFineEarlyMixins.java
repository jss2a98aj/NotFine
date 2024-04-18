package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jss.notfine.NotFine;
import jss.notfine.config.NotFineConfig;
import jss.notfine.asm.AsmTransformers;
import jss.notfine.asm.mappings.Namer;
import jss.notfine.config.MCPatcherForgeConfig;
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
        List<String> mixins = new ArrayList<>();
        if(!FMLLaunchHandler.side().isClient()) {
            return mixins;
        }
        NotFineConfig.loadSettings();

        //This may be possible to handle differently.
        if(loadedCoreMods.contains("cofh.asm.LoadingPlugin")) {
            MCPatcherForgeConfig.instance().hdFont = false;
        }

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
        NotFine.logger.info("Not loading the following EARLY mixins: {}", notLoading.toString());

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
