package jss.notfine.asm.mappings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jss.notfine.mixinplugin.NotFineEarlyMixins;
import jss.notfine.asm.mappings.Names.Clas;
import jss.notfine.asm.mappings.Names.Fiel;
import jss.notfine.asm.mappings.Names.Meth;

public class NamerMcp extends NamerSrg {

    @Override
    public void setNames() {
        setNamesSrg();
        final String confPath = System.getProperty("net.minecraftforge.gradle.GradleStart.csvDir", "../conf") + "/";
        lookupReobfName(confPath);
        rename(confPath);
    }

    public void rename(String confPath) {
        Map<String, String> nameMap;
        nameMap = loadNameMapCSV(confPath + "fields.csv");
        for (Fiel f : af) {
            String s = nameMap.get(f.name);
            if (s != null) {
                f.name = s;
            }
        }
        nameMap = loadNameMapCSV(confPath + "methods.csv");
        for (Meth m : am) {
            String s = nameMap.get(m.name);
            if (s != null) {
                m.name = s;
            }
        }
    }

    Map<String, String> loadNameMapCSV(String fileName) {
        Map<String, String> map = new HashMap<>();
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader(fileName));
            String line;
            rd.readLine(); // skip first line;
            while ((line = rd.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 1) {
                    map.put(tokens[0], tokens[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    void lookupReobfName(String confPath) {
        Map<String, String> nameMap;
        nameMap = loadReobfMap(confPath + "packaged.srg");
        for (Clas c : ac) {
            String s = nameMap.get(c.name);
            NotFineEarlyMixins.mcpfLogger.trace("C {} {}", c.name, s);
        }
        for (Fiel f : af) {
            String s = nameMap.get(f.clas + "/" + f.name);
            NotFineEarlyMixins.mcpfLogger.trace("F {} {}", f.name, s);
        }
        for (Meth m : am) {
            String s = nameMap.get(m.clas + "/" + m.name + m.desc);
            NotFineEarlyMixins.mcpfLogger.trace("M {} {}", m.name, s);
        }
    }

    Map<String, String> loadReobfMap(String fileName) {
        Map<String, String> map = new HashMap<>();
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length > 1) {
                    if ("CL:".equals(tokens[0])) {
                        map.put(tokens[2], tokens[1]);
                    } else if ("FD:".equals(tokens[0])) {
                        map.put(tokens[2], tokens[1]);
                    } else if ("MD:".equals(tokens[0])) {
                        map.put(tokens[3] + tokens[4], tokens[1].substring(tokens[1].lastIndexOf('/') + 1));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
