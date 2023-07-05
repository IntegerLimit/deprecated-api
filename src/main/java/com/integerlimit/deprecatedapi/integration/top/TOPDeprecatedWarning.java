package com.integerlimit.deprecatedapi.integration.top;

import com.integerlimit.deprecatedapi.deprecation.DeprecatedBlock;
import com.integerlimit.deprecatedapi.deprecation.DeprecatedBlocks;
import com.integerlimit.deprecatedapi.Tags;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TOPDeprecatedWarning implements IProbeInfoProvider {
    @Override
    public String getID() {
        return Tags.MODID + "top_deprecated_warning";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData probeHitData) {
        Block sourceBlock = blockState.getBlock();
        DeprecatedBlock block = DeprecatedBlocks.getBlock(sourceBlock.getRegistryName(), sourceBlock.getMetaFromState(blockState));
        if (block != null) {
            String text = TextFormatting.RED + IProbeInfo.STARTLOC + block.getTOPWailaMessage() + IProbeInfo.ENDLOC;
            probeInfo.text(text);
        }
    }
}
