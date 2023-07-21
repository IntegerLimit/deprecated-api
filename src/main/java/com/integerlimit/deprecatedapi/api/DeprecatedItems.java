package com.integerlimit.deprecatedapi.api;

import com.integerlimit.deprecatedapi.DeprecatedAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DeprecatedItems {
    private static final List<DeprecatedItem> items = new ArrayList<>();
    public static final int WILDCARD_META = -1;

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(Item item) {
        return addDeprecatedItem(item, WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedItem addDeprecatedItem(Item item, int meta) {
        DeprecatedItem deprecatedItem = new DeprecatedItem().setItem(new ItemStack(item, 1, meta), meta);
        addDeprecatedItem(deprecatedItem);
        return deprecatedItem;
    }

    public static DeprecatedItem addDeprecatedItem(ItemStack item) {
        DeprecatedItem deprecatedItem = new DeprecatedItem().setItem(item, item.getMetadata());
        addDeprecatedItem(deprecatedItem);
        return deprecatedItem;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedItem(DeprecatedItem item) {
        if (item.stack.isEmpty()) {
            DeprecatedAPI.LOGGER.fatal("Could not deprecate item as you can't deprecate an empty item.");
            return;
        }
        if (DeprecatedAPI.pastPostInit()) {
            DeprecatedAPI.LOGGER.fatal("Could not deprecate item " + item.stack.getItem().getRegistryName() + " as this must be done before postInit!");
            return;
        }

        items.add(item);
        DeprecatedAPI.LOGGER.info("Item " + item.stack.getItem().getRegistryName() + " has been marked as deprecated.");
    }

    /**
     * @param item The Item
     * @return Null if not deprecated, otherwise, the DeprecatedItem Object.
     */
    @Nullable
    public static DeprecatedItem getItem(ItemStack item) {
        DeprecatedItem found = items.stream().filter((dep) -> dep.matches(item, item.getMetadata())).findFirst().orElse(null);
        if (found == null) found = items.stream().filter((dep) -> dep.matches(item, WILDCARD_META)).findFirst().orElse(null);

        return found;
    }

    public static void logDeprecations() {
        if (items.isEmpty())
            DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed, but there are no deprecated items detected.");

        DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed. The following items are deprecated:");

        items.forEach((dep) -> sayDeprecated(dep.stack.getItem().getRegistryName(), dep.stack.getMetadata()));

        DeprecatedAPI.LOGGER.warn("End Deprecated Items.");
    }

    private static void sayDeprecated(ResourceLocation name, int meta) {
        if (meta == WILDCARD_META)
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with any meta.", name);

        else
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with meta {}.", name, meta);
    }
}
