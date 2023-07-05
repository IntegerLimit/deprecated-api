package com.integerlimit.deprecatedapi.api;

import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedItems {
    private static final Map<Pair<ResourceLocation, Integer>, DeprecatedItem> items = new Object2ObjectOpenHashMap<>();
    public static final int WILDCARD_META = -1;

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(ResourceLocation resourceLocation) {
        return addDeprecatedItem(resourceLocation, WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(ResourceLocation resourceLocation, int meta) {
        DeprecatedItem item = new DeprecatedItem();
        addDeprecatedItem(Pair.of(resourceLocation, meta), item);
        return item;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedItem(Pair<ResourceLocation, Integer> locationMetaPair, DeprecatedItem item) {
        items.put(locationMetaPair, item);
        DeprecatedApi.LOGGER.info("Item with resource location " + locationMetaPair.getLeft() + " has been marked as deprecated.");
    }

    /**
     * @param resourceLocation the resource location
     * @param meta The meta of the item
     * @return Null if not deprecated, otherwise, the DeprecatedItem Object.
     */
    @Nullable
    public static DeprecatedItem getItem(ResourceLocation resourceLocation, int meta) {
        // Check proper meta first
        DeprecatedItem item = items.get(Pair.of(resourceLocation, meta));

        // If not exist, use wild meta
        if (item == null)
            item = items.get(Pair.of(resourceLocation, WILDCARD_META));

        return item;
    }
}
