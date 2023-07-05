package com.integerlimit.deprecatedapi.integration;

import com.integerlimit.deprecatedapi.integration.top.TOP;
import net.minecraftforge.fml.common.Loader;

public class Registry {
    public static void init() {
        if (Loader.isModLoaded("theoneprobe"))
            TOP.registerProviders();
    }
}
