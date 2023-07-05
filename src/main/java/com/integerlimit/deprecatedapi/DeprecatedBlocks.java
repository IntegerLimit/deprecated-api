package com.integerlimit.deprecatedapi;


import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class DeprecatedBlocks {
    private static final Set<ResourceLocation> blocks = new HashSet<>();
    public static void addDeprecatedBlock(ResourceLocation resourceLocation) {
        DeprecatedItems.addDeprecatedItem(resourceLocation);
        blocks.add(resourceLocation);
        DeprecatedApi.LOGGER.info("[DeprecatedAPI]: Block with resource location " + resourceLocation + " has been marked as deprecated.");
    }
    public static boolean blockDisabled(ResourceLocation resourceLocation) {
        return blocks.contains(resourceLocation);
    }
}
