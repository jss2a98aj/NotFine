package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jss.notfine.config.NotFineConfig;
import jss.notfine.asm.AsmTransformers;
import jss.notfine.asm.mappings.Namer;
import jss.notfine.config.MCPatcherForgeConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        NotFineConfig.loadSettings();
        //This may be possible to handle differently or fix.
        if(loadedCoreMods.contains("cofh.asm.LoadingPlugin")) {
            MCPatcherForgeConfig.instance().hdFont = false;
        }
        return IMixins.getEarlyMixins(Mixins.class, loadedCoreMods);
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
