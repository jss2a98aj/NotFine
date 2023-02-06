package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class NotFineEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.notfine.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        List<String> mixins = new ArrayList<>();

        mixins.add("minecraft.MixinBlockLeavesBase");
        mixins.add("minecraft.MixinBlockLeaves");
        mixins.add("minecraft.MixinBlockOldLeaf");
        mixins.add("minecraft.MixinBlockNewLeaf");
        mixins.add("minecraft.MixinRenderGlobal");
        mixins.add("minecraft.MixinWorldType");
        mixins.add("minecraft.MixinBlockEnchantmentTable");
        mixins.add("minecraft.MixinEffectRenderer");
        mixins.add("minecraft.MixinRenderItem");
        mixins.add("minecraft.MixinItemRenderer");

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
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
