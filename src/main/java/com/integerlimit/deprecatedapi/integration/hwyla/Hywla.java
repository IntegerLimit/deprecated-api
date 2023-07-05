package com.integerlimit.deprecatedapi.integration.hwyla;

import com.integerlimit.deprecatedapi.api.DeprecatedBlock;
import com.integerlimit.deprecatedapi.api.DeprecatedBlocks;
import mcp.mobius.waila.api.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@WailaPlugin
@SuppressWarnings("unused")
public class Hywla implements IWailaPlugin {
    @Override
    public void register(IWailaRegistrar iWailaRegistrar) {
        HywlaDeprecatedWarning.INSTANCE.register(iWailaRegistrar);
    }

    public static class HywlaDeprecatedWarning implements IWailaDataProvider {

        public static final HywlaDeprecatedWarning INSTANCE = new HywlaDeprecatedWarning();

        public void register(@NotNull IWailaRegistrar registrar) {
            // Register for all blocks
            registrar.registerTailProvider(this, Block.class);
        }

        @NotNull
        @Override
        public List<String> getWailaTail(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
            DeprecatedBlock block = DeprecatedBlocks.getBlock(Block.getBlockFromItem(itemStack.getItem()), itemStack.getMetadata());
            if (block != null)
                tooltip.add(TextFormatting.RED + block.getTOPWailaMessage());
            return tooltip;
        }
    }
}
