package com.integerlimit.deprecatedapi.api;


import com.integerlimit.deprecatedapi.DeprecatedApi;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
        DeprecatedItems.addDeprecatedItem(
                Pair.of(Item.getItemFromBlock(blockMetaPair.getLeft()), blockMetaPair.getRight()), block);
        blocks.put(blockMetaPair, block);
        DeprecatedApi.LOGGER.info("Block with resource location " + blockMetaPair.getLeft().getRegistryName() + " has been marked as deprecated.");
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
}
