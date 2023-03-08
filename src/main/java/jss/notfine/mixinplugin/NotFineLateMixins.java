package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import jss.notfine.NotFine;

import java.util.ArrayList;
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
        NotFine.logger.info("Kicking off NotFine late mixins.");

        List<String> mixins = new ArrayList<>();

        if(loadedMods.contains("Thaumcraft")) {
            mixins.add("thaumcraft.leaves.MixinBlockMagicalLeaves");
        }

        if(loadedMods.contains("witchery")) {
            mixins.add("witchery.leaves.MixinBlockWitchLeaves");
        }

        return mixins;
    }

}
