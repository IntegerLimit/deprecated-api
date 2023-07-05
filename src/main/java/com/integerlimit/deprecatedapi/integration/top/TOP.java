package com.integerlimit.deprecatedapi.integration.top;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TOP {
    public static void registerProviders() {
        ITheOneProbe TOP = TheOneProbe.theOneProbeImp;
        TOP.registerProvider(new TOPDeprecatedWarning());
    }
}
