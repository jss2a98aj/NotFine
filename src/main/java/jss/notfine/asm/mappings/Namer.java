package jss.notfine.asm.mappings;

import java.util.ArrayList;

import jss.notfine.mixinplugin.NotFineEarlyMixins;
import net.minecraft.launchwrapper.Launch;

/**
 * This class und inherited classes are adapted from
 * <a href=
 * "https://github.com/GTNewHorizons/Angelica/blob/master/src/main/java/com/gtnewhorizons/angelica/transform/Namer.java">Angelica/ShadersMod</a>
 */
public class Namer {

    ArrayList<Names.Clas> ac = new ArrayList<>();
    ArrayList<Names.Fiel> af = new ArrayList<>();
    ArrayList<Names.Meth> am = new ArrayList<>();

    Names.Clas c(String name) {
        Names.Clas x = new Names.Clas(name);
        if (ac != null) ac.add(x);
        return x;
    }

    Names.Fiel f(Names.Clas clas, String name, String desc) {
        Names.Fiel x = new Names.Fiel(clas, name, desc);
        if (af != null) af.add(x);
        return x;
    }

    Names.Fiel f(Names.Clas clas, Names.Fiel fiel) {
        Names.Fiel x = new Names.Fiel(clas, fiel.name, fiel.desc);
        if (af != null) af.add(x);
        return x;
    }

    Names.Meth m(Names.Clas clas, String name, String desc) {
        Names.Meth x = new Names.Meth(clas, name, desc);
        if (am != null) am.add(x);
        return x;
    }

    Names.Meth m(Names.Clas clas, Names.Meth meth) {
        Names.Meth x = new Names.Meth(clas, meth.name, meth.desc);
        if (am != null) am.add(x);
        return x;
    }

    public static void initNames() {
        final boolean obfuscated = !(Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        NotFineEarlyMixins.mcpfLogger.info("Environment obfuscated: {}", obfuscated);
        if (obfuscated) {
            new NamerObf().setNames();
        } else {
            new NamerMcp().setNames();
        }
    }
}
