package com.integerlimit.deprecatedapi;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    public void tooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.isEmpty() && DeprecatedItems.itemDisabled(itemStack.getItem().getRegistryName())) {
            List<String> tooltips = event.getToolTip();
            tooltips.add(TextFormatting.RED + "Deprecated");
        }
    }

}
