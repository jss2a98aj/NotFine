package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

import java.util.List;
import java.util.Set;

@LateMixin
public class NotFineLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.notfine.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(Mixins.class, loadedMods);
    }

}
