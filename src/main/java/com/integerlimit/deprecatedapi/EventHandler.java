package com.integerlimit.deprecatedapi;

import com.integerlimit.deprecatedapi.deprecation.DeprecatedItem;
import com.integerlimit.deprecatedapi.deprecation.DeprecatedItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    public void tooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        DeprecatedItem item = DeprecatedItems.getItem(itemStack.getItem().getRegistryName(), itemStack.getMetadata());
        if (item != null) {
            event.getToolTip().add(TextFormatting.RED + item.getTooltipMessage());
        }
    }

}
