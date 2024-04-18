package jss.notfine.config;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import jss.notfine.NotFine;

import java.io.File;

public class NotFineConfig {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_TOGGLE = "toggle";

    public static boolean allowAdvancedOpenGL = true;

    public static boolean betterBlockFaceCulling = true;

    public NotFineConfig() {
    }

    public static void loadSettings() {
        File configFile = new File(Launch.minecraftHome + File.separator + "config" + File.separator + NotFine.MODID + File.separator + "notfine.cfg");
        Configuration notFineConfig = new Configuration(configFile);

        allowAdvancedOpenGL = notFineConfig.getBoolean("allowAdvancedOpenGL", CATEGORY_GENERAL, true,
            "Allow or always disable Advanced OpenGL");

        notFineConfig.setCategoryComment(CATEGORY_TOGGLE, "Enable or disable various mod features.");
        betterBlockFaceCulling = notFineConfig.getBoolean("betterBlockFaceCulling", CATEGORY_TOGGLE, true,
            "Use more accurate block face culling when building chunk meshes.");

        if(notFineConfig.hasChanged()) {
            notFineConfig.save();
        }
    }

}
