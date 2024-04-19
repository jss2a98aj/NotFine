package jss.notfine.mixinplugin;

// Adapted from hodgepodge
public enum TargetedMod {

    COFHCORE("CoFHCore", "cofh.asm.LoadingPlugin", "CoFHCore"),
    DYNAMIC_SURROUNDINGS_MIST("Dynamic Surroundings", "org.blockartistry.mod.DynSurround.mixinplugin.DynamicSurroundingsEarlyMixins", "dsurround"),
    DYNAMIC_SURROUNDINGS_ORIGINAL("Dynamic Surroundings", "org.blockartistry.mod.DynSurround.asm.TransformLoader", "dsurround"),
    THAUMCRAFT("Thaumcraft", null, "Thaumcraft"),
    TWILIGHT_FOREST("TwilightForest", null, "TwilightForest"),
    VANILLA("Minecraft", null),
    WITCHERY("Witchery", null, "witchery");

    /** The "name" in the @Mod annotation */
    public final String modName;
    /** Class that implements the IFMLLoadingPlugin interface */
    public final String coreModClass;
    /** The "modid" in the @Mod annotation */
    public final String modId;

    TargetedMod(String modName, String coreModClass) {
        this(modName, coreModClass, null);
    }

    TargetedMod(String modName, String coreModClass, String modId) {
        this.modName = modName;
        this.coreModClass = coreModClass;
        this.modId = modId;
    }

    @Override
    public String toString() {
        return "TargetedMod{modName='" + modName + "', coreModClass='" + coreModClass + "', modId='" + modId + "'}";
    }
}
