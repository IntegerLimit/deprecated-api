package com.integerlimit.deprecatedapi;

import com.integerlimit.deprecatedapi.api.DeprecatedItem;
import com.integerlimit.deprecatedapi.api.DeprecatedItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.Map;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    public void tooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        DeprecatedItem item = DeprecatedItems.getItem(itemStack);
        if (item != null) {
            // TODO make it configurable whether tooltip is below, or above, mod name
            int location = event.getToolTip().size();
            if (isModNamePresent(event.getToolTip(), event.getItemStack()))
                location -= 1;
            //event.getToolTip().add(TextFormatting.RED + item.getTooltipMessage()); // For below mod name
            event.getToolTip().add(location, TextFormatting.RED + item.getTooltipMessage());
        }
    }

    // Thanks to https://github.com/mezz/ModNameTooltip for these!
    private static boolean isModNamePresent(List<String> tooltip, ItemStack stack) {
        String modName = getModName(stack);
        if (tooltip.size() > 1) {
            String lastTooltipLine = tooltip.get(tooltip.size() - 1);
            lastTooltipLine = TextFormatting.getTextWithoutFormattingCodes(lastTooltipLine);
            assert lastTooltipLine != null;
            return lastTooltipLine.equals(modName);
        }
        return false;
    }
    private static String getModName(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();

            String modId = item.getCreatorModId(itemStack);
            if (modId != null) {
                Map<String, ModContainer> indexedModList = Loader.instance().getIndexedModList();
                ModContainer modContainer = indexedModList.get(modId);
                if (modContainer != null) {
                    return modContainer.getName();
                }
            }

            if (item.getRegistryName() != null) {
                return WordUtils.capitalize(item.getRegistryName().getNamespace());
            }
        }
        return null;
    }
}
