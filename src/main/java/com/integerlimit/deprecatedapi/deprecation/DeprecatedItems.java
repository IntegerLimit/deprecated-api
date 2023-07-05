package com.integerlimit.deprecatedapi.deprecation;

import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedItems {
    private static final Map<ResourceLocation, DeprecatedItem> items = new Object2ObjectOpenHashMap<>();

    @SuppressWarnings("unused")
    public static DeprecatedItem addDeprecatedItem(ResourceLocation resourceLocation) {
        DeprecatedItem item = new DeprecatedItem();
        addDeprecatedItem(resourceLocation, item);
        return item;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedItem(ResourceLocation resourceLocation, DeprecatedItem item) {
        items.put(resourceLocation, item);
        DeprecatedApi.LOGGER.info("[DeprecatedAPI]: Item with resource location " + resourceLocation + " has been marked as deprecated.");
    }

    /**
     * @param resourceLocation The resource location of the item
     * @return null if resource location is not deprecated, otherwise, the DeprecatedItem Object
     */
    @Nullable
    public static DeprecatedItem getItem(ResourceLocation resourceLocation) {
        return items.get(resourceLocation);
    }
}
