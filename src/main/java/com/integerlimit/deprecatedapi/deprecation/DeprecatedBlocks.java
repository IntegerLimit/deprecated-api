package com.integerlimit.deprecatedapi.deprecation;


import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedBlocks {
    private static final Map<ResourceLocation, DeprecatedBlock> blocks = new Object2ObjectOpenHashMap<>();

    @SuppressWarnings("unused")
    public static DeprecatedBlock addDeprecatedBlock(ResourceLocation resourceLocation) {
        DeprecatedBlock block = new DeprecatedBlock();
        addDeprecatedBlock(resourceLocation, block);
        return block;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedBlock(ResourceLocation resourceLocation, DeprecatedBlock block) {
        DeprecatedItems.addDeprecatedItem(resourceLocation, block);
        blocks.put(resourceLocation, block);
        DeprecatedApi.LOGGER.info("[DeprecatedAPI]: Block with resource location " + resourceLocation + " has been marked as deprecated.");
    }

    /**
     * @param resourceLocation The resource location of the block
     * @return null if resource location is not deprecated, otherwise, the DeprecatedBlock Object
     */
    @Nullable
    public static DeprecatedBlock getBlock(ResourceLocation resourceLocation) {
        return blocks.get(resourceLocation);
    }
}
