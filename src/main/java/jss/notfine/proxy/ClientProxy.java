package jss.notfine.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import jss.notfine.config.NotFineConfig;
import jss.notfine.core.LoadMenuButtons;
import jss.notfine.core.Settings;
import jss.notfine.core.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NotFineConfig config = new NotFineConfig();
        config.loadSettings();

        if(!NotFineConfig.allowAdvancedOpenGL) {
            Minecraft.getMinecraft().gameSettings.advancedOpengl = false;
        }

        for(Settings setting : Settings.values()) {
            setting.ready();
        }

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(LoadMenuButtons.INSTANCE);
    }

    @Override
    public void init(FMLInitializationEvent event) { }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        SettingsManager.settingsFile.loadSettings();
    }

    @SubscribeEvent
    public void onFOVModifierUpdate(FOVUpdateEvent event) {
        if (!(boolean)Settings.DYNAMIC_FOV.option.getStore()){
            event.newfov = 1.0F;
        }
    }

}
