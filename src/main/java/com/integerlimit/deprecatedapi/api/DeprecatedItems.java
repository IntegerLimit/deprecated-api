package com.integerlimit.deprecatedapi.api;

import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.Item;
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
        items.put(itemMetaPair, item);
        DeprecatedApi.LOGGER.info("Item with resource location " + itemMetaPair.getLeft().getRegistryName() + " has been marked as deprecated.");
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
}
