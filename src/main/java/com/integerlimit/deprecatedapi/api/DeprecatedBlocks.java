package com.integerlimit.deprecatedapi.api;


import com.integerlimit.deprecatedapi.DeprecatedAPI;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Map;

public class DeprecatedBlocks {
    private static final Map<Pair<Block, Integer>, DeprecatedBlock> blocks = new Object2ObjectOpenHashMap<>();
    public static final int WILDCARD_META = -1;

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(Block block) {
        return addDeprecatedBlock(block, WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(Block block, int meta) {
        DeprecatedBlock deprecatedBlock = new DeprecatedBlock();
        addDeprecatedBlock(Pair.of(block, meta), deprecatedBlock);
        return deprecatedBlock;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedBlock(Pair<Block, Integer> blockMetaPair, DeprecatedBlock block) {
        if (DeprecatedAPI.pastPostInit()) {
            DeprecatedAPI.LOGGER.fatal("Could not deprecate block " + blockMetaPair.getLeft().getRegistryName() + " as this must be done before postInit!");
        }

        DeprecatedItems.addDeprecatedItem(
                Pair.of(Item.getItemFromBlock(blockMetaPair.getLeft()), blockMetaPair.getRight()), block);
        blocks.put(blockMetaPair, block);
        DeprecatedAPI.LOGGER.info("Block with resource location " + blockMetaPair.getLeft().getRegistryName() + " has been marked as deprecated.");
    }

    /**
     * @param block The Block.
     * @param meta The Meta. Usually found from the IBlockState.
     * @return Null if not deprecated, otherwise, the DeprecatedBlock Object.
     */
    @Nullable
    public static DeprecatedBlock getBlock(Block block, int meta) {
        // Check proper meta first
        DeprecatedBlock deprecatedBlock = blocks.get(Pair.of(block, meta));

        // If not exist, use wild meta
        if (deprecatedBlock == null)
            deprecatedBlock = blocks.get(Pair.of(block, WILDCARD_META));

        return deprecatedBlock;
    }

    public static void logDeprecations() {
        if (blocks.isEmpty())
            DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed, but there are no deprecated blocks detected.");

        DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed. The following blocks are deprecated:");

        blocks.forEach((key, value) -> sayDeprecated(key.getLeft().getRegistryName(), key.getRight())
        );

        DeprecatedAPI.LOGGER.warn("End Deprecated Blocks.");
    }

    private static void sayDeprecated(ResourceLocation name, int meta) {
        if (meta == WILDCARD_META)
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with any meta.", name);

        else
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with meta {}.", name, meta);
    }
}
