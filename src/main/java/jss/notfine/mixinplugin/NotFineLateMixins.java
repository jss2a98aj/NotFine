package jss.notfine.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.InvalidVersionSpecificationException;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import jss.notfine.NotFine;

import java.util.ArrayList;
import java.util.Collections;
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
        List<String> mixins = new ArrayList<>();
        if(!FMLLaunchHandler.side().isClient()) {
            return mixins;
        }

        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Mixins.Phase.LATE) {
                if (mixin.shouldLoad(Collections.emptySet(), loadedMods)) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        NotFine.logger.info("Not loading the following LATE mixins: {}", notLoading.toString());

        //TODO: Add better support for version checking?
        if(loadedMods.contains("TwilightForest")) {
            mixins.add("leaves.twilightforest.MixinBlockTFLeaves");
            mixins.add("leaves.twilightforest.MixinBlockTFLeaves3");

            //Non-GTNH Twilight Forest builds will break horribly with this mixin.
            boolean modernBuild = false;
            try {
                ArtifactVersion accepted = new DefaultArtifactVersion("TwilightForest", VersionRange.createFromVersionSpec("[2.3.8.18,)"));
                ModContainer mc = Loader.instance().getIndexedModList().get("TwilightForest");
                if(mc != null) modernBuild = accepted.containsVersion(mc.getProcessedVersion());
            } catch (InvalidVersionSpecificationException ignored) {
            }
            if(modernBuild) {
                mixins.add("leaves.twilightforest.MixinBlockTFMagicLeaves");
            }
        }

        return mixins;
    }

}
