package com.integerlimit.deprecatedapi.deprecation;


import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedBlocks {
    private static final Map<Pair<ResourceLocation, Integer>, DeprecatedBlock> blocks = new Object2ObjectOpenHashMap<>();
    public static final int WILDCARD_META = -1;

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(ResourceLocation resourceLocation) {
        return addDeprecatedBlock(resourceLocation, WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(ResourceLocation resourceLocation, int meta) {
        DeprecatedBlock block = new DeprecatedBlock();
        addDeprecatedBlock(Pair.of(resourceLocation, meta), block);
        return block;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedBlock(Pair<ResourceLocation, Integer> locationMetaPair, DeprecatedBlock block) {
        DeprecatedItems.addDeprecatedItem(locationMetaPair, block);
        blocks.put(locationMetaPair, block);
        DeprecatedApi.LOGGER.info("[DeprecatedAPI]: Block with resource location " + locationMetaPair.getLeft() + " has been marked as deprecated.");
    }

    /**
     * @param resourceLocation the resource location
     * @param meta The meta of the block
     * @return Null if not deprecated, otherwise, the DeprecatedBlock Object
     */
    @Nullable
    public static DeprecatedBlock getBlock(ResourceLocation resourceLocation, int meta) {
        // Check proper meta first
        DeprecatedBlock block = blocks.get(Pair.of(resourceLocation, meta));

        // If not exist, use wild meta
        if (block == null)
            block = blocks.get(Pair.of(resourceLocation, WILDCARD_META));

        return block;
    }
}
