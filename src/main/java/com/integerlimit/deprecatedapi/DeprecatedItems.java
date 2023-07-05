package com.integerlimit.deprecatedapi;

import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class DeprecatedItems {
    private static final Set<ResourceLocation> items = new HashSet<>();
    public static void addDeprecatedItem(ResourceLocation resourceLocation) {
        items.add(resourceLocation);
        DeprecatedApi.LOGGER.info("[DeprecatedAPI]: Item with resource location " + resourceLocation + " has been marked as deprecated.");
    }
    public static boolean itemDisabled(ResourceLocation resourceLocation) {
        return items.contains(resourceLocation);
    }
}
