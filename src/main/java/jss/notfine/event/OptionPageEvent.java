package jss.notfine.event;

import cpw.mods.fml.common.eventhandler.Event;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;

import java.util.List;

public class OptionPageEvent extends Event {

    public final String pageName;
    public final List<OptionGroup> groups;

    public OptionPageEvent(String pageName, List<OptionGroup> groups) {
        this.pageName = pageName;
        this.groups = groups;
    }

}
