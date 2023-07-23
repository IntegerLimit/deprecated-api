package com.integerlimit.deprecatedapi.integration;

import com.integerlimit.deprecatedapi.integration.colorfultooltips.ColorfulTooltips;
import com.integerlimit.deprecatedapi.integration.top.TOP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class Registry {
    public static void init() {
        if (Loader.isModLoaded("theoneprobe"))
            TOP.registerProviders();
    }

    public static void preInit() {
        if (Loader.isModLoaded("colorfultooltips"))
            MinecraftForge.EVENT_BUS.register(ColorfulTooltips.class);
    }
}
