package com.integerlimit.deprecatedapi.integration.colorfultooltips;

import colorfultooltips.AbstractTooltipColor;
import com.integerlimit.deprecatedapi.api.DeprecatedItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ColorfulTooltips {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColor(colorfultooltips.ColorfulTooltips.RegisterTooltipColorsEvent event) {
        event.addTooltipColor(new AbstractTooltipColor(0xFF0000) {
            @Override
            public boolean matches(ItemStack itemStack, List<String> list) {
                return DeprecatedItems.getItem(itemStack) != null;
            }
        });
    }
}
