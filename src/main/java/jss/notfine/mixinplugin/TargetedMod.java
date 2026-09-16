package jss.notfine.mixinplugin;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

import cpw.mods.fml.common.versioning.ComparableVersion;

public enum TargetedMod implements ITargetMod {

    BOP("BiomesOPlenty"),
    COFHCORE("cofh.asm.LoadingPlugin", "CoFHCore"),
    DYNAMIC_SURROUNDINGS_MIST("org.blockartistry.mod.DynSurround.mixinplugin.DynamicSurroundingsEarlyMixins", "dsurround"),
    DYNAMIC_SURROUNDINGS_ORIGINAL("org.blockartistry.mod.DynSurround.asm.TransformLoader", "dsurround"),
    NATURA("Natura"),
    THAUMCRAFT("Thaumcraft"),
    TINKERS_CONSTRUCT("TConstruct"),
    TWILIGHT_FOREST_ANY("TwilightForest"),
    TWILIGHT_FOREST_ORIGINAL(new TargetModBuilder().setTargetClass("twilightforest.TwilightForestMod")
        .setModId("TwilightForest").testModVersion("TwilightForest", version -> isVersionLessThan(version, "2.4.3"))),
    WITCHERY("witchery");

    private final TargetModBuilder builder;

    TargetedMod(TargetModBuilder builder) {
        this.builder = builder;
    }

    TargetedMod(String modId) {
        this(null, modId, null);
    }

    TargetedMod(String coreModClass, String modId) {
        this(coreModClass, modId, null);
    }

    TargetedMod(String coreModClass, String modId, String targetClass) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass).setModId(modId).setTargetClass(targetClass);
    }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }

    private static boolean isVersionLessThan(String version, String target) {
        return new ComparableVersion(version).compareTo(new ComparableVersion(target)) < 0;
    }
}
