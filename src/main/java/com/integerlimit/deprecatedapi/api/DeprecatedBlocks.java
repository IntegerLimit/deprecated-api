package com.integerlimit.deprecatedapi.api;


import com.integerlimit.deprecatedapi.DeprecatedAPI;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DeprecatedBlocks {
    private static final List<DeprecatedBlock> blocks = new ArrayList<>();

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(Block block) {
        return addDeprecatedBlock(block, DeprecatedItems.WILDCARD_META);
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static DeprecatedBlock addDeprecatedBlock(Block block, int meta) {
        DeprecatedBlock deprecatedBlock = new DeprecatedBlock().setBlock(block, meta);
        addDeprecatedBlock(deprecatedBlock);
        return deprecatedBlock;
    }

    @SuppressWarnings("unused")
    public static void addDeprecatedBlock(DeprecatedBlock block) {
        if (DeprecatedAPI.pastPostInit()) {
            DeprecatedAPI.LOGGER.fatal("Could not deprecate block " + block.block.getRegistryName() + " as this must be done before postInit!");
        }

        DeprecatedItems.addDeprecatedItem(block);
        blocks.add(block);
        DeprecatedAPI.LOGGER.info("Block with resource location " + block.block.getRegistryName() + " has been marked as deprecated.");
    }

    /**
     * @param block The Block.
     * @param meta The Meta. Usually found from the IBlockState.
     * @return Null if not deprecated, otherwise, the DeprecatedBlock Object.
     */
    @Nullable
    public static DeprecatedBlock getBlock(Block block, int meta) {
        return blocks.stream().filter((dep) -> dep.matches(block, meta)).findFirst().orElse(null);
    }

    public static void logDeprecations() {
        if (blocks.isEmpty())
            DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed, but there are no deprecated blocks detected.");

        DeprecatedAPI.LOGGER.warn("DeprecatedAPI is installed. The following blocks are deprecated:");

        blocks.forEach((dep) -> sayDeprecated(dep.block.getRegistryName(), dep.stack.getItemDamage()));

        DeprecatedAPI.LOGGER.warn("End Deprecated Blocks.");
    }

    private static void sayDeprecated(ResourceLocation name, int meta) {
        if (meta == DeprecatedItems.WILDCARD_META)
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with any meta.", name);

        else
            DeprecatedAPI.LOGGER.warn("Registry Name: {}, with meta {}.", name, meta);
    }
}
