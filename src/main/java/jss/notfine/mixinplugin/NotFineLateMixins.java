package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;

import java.util.List;
import java.util.Set;

public class NotFineLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.notfine.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return null;
    }

}
