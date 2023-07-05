package com.integerlimit.deprecatedapi.integration.top;

import com.integerlimit.deprecatedapi.Tags;
import com.integerlimit.deprecatedapi.api.DeprecatedBlock;
import com.integerlimit.deprecatedapi.api.DeprecatedBlocks;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TOP {
    public static void registerProviders() {
        ITheOneProbe TOP = TheOneProbe.theOneProbeImp;
        TOP.registerProvider(new TOPDeprecatedWarning());
    }

    public static class TOPDeprecatedWarning implements IProbeInfoProvider {
        @Override
        public String getID() {
            return Tags.MODID + "top_deprecated_warning";
        }

        @Override
        public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData probeHitData) {
            Block block = blockState.getBlock();
            DeprecatedBlock deprecatedBlock = DeprecatedBlocks.getBlock(block, block.getMetaFromState(blockState));
            if (deprecatedBlock != null) {
                String text = TextFormatting.RED + IProbeInfo.STARTLOC + deprecatedBlock.getTOPWailaMessage() + IProbeInfo.ENDLOC;
                probeInfo.text(text);
            }
        }
    }
}
