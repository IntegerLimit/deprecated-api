package com.integerlimit.deprecatedapi.api;

import com.integerlimit.deprecatedapi.DeprecatedAPI;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedItems {
    private static final Map<Pair<Item, Integer>, DeprecatedItem> items = new Object2ObjectOpenHashMap<>();
    public static final int WILDCARD_META = -1;

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(Item item) {
        return addDeprecatedItem(item, WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(Item item, int meta) {
        DeprecatedItem deprecatedItem = new DeprecatedItem();
        addDeprecatedItem(Pair.of(item, meta), deprecatedItem);
        return deprecatedItem;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedItem(Pair<Item, Integer> itemMetaPair, DeprecatedItem item) {
        if (DeprecatedAPI.pastPostInit()) {
            DeprecatedAPI.LOGGER.fatal("Could not deprecate item " + itemMetaPair.getLeft().getRegistryName() + " as this must be done before postInit!");
            return;
        }

        items.put(itemMetaPair, item);
        DeprecatedAPI.LOGGER.info("Item " + itemMetaPair.getLeft().getRegistryName() + " has been marked as deprecated.");
    }

    /**
     * @param item The Item.
     * @param meta The Meta. Usually found from the ItemStack.
     * @return Null if not deprecated, otherwise, the DeprecatedItem Object.
     */
    @Nullable
    public static DeprecatedItem getItem(Item item, int meta) {
        // Check proper meta first
        DeprecatedItem deprecatedItem = items.get(Pair.of(item, meta));

        // If not exist, use wild meta
        if (deprecatedItem == null)
            deprecatedItem = items.get(Pair.of(item, WILDCARD_META));

        return deprecatedItem;
    }

    public static void logDeprecations() {
        if (items.isEmpty())
            DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed, but there are no deprecated items detected.");

        DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed. The following items are deprecated:");

        items.forEach((key, value) -> sayDeprecated(key.getLeft().getRegistryName(), key.getRight())
        );

        DeprecatedAPI.LOGGER.warn("End Deprecated Items.");
    }

    private static void sayDeprecated(ResourceLocation name, int meta) {
        if (meta == WILDCARD_META)
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with any meta.", name);

        else
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with meta {}.", name, meta);
    }
}
