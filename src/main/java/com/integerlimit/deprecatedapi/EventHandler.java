package com.integerlimit.deprecatedapi;

import com.integerlimit.deprecatedapi.deprecation.DeprecatedItem;
import com.integerlimit.deprecatedapi.deprecation.DeprecatedItems;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    public void tooltipEvent(ItemTooltipEvent event) {
        DeprecatedItem item = DeprecatedItems.getItem(event.getItemStack().getItem().getRegistryName());
        if (item != null) {
            event.getToolTip().add(TextFormatting.RED + item.getTooltipMessage());
        }
    }

}
