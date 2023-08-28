package com.integerlimit.deprecatedapi;

import com.integerlimit.deprecatedapi.api.DeprecatedItem;
import com.integerlimit.deprecatedapi.api.DeprecatedItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    public void tooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        DeprecatedItem item = DeprecatedItems.getItem(itemStack);
        if (item != null) {
            event.getToolTip().add(TextFormatting.RED + item.getTooltipMessage());
        }
    }

}
