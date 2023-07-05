package com.integerlimit.deprecatedapi.mixin;

import com.anthonyhilyard.itemborders.config.ItemBordersConfig;
import com.anthonyhilyard.itemborders.util.ColorUtil;
import com.integerlimit.deprecatedapi.api.DeprecatedItems;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import java.util.function.Supplier;

@Mixin(value = ItemBordersConfig.class, remap = false)
public class ItemBordersMixin {
    @Inject(method = "getBorderColorForItem", at = @At("HEAD"), cancellable = true)
    public void setRedBorder(ItemStack item, CallbackInfoReturnable<Pair<Supplier<Integer>, Supplier<Integer>>> cir) {
        if (DeprecatedItems.getItem(item.getItem().getRegistryName(), item.getMetadata()) != null) {
            int color = ColorUtil.parseColor("red");
            cir.setReturnValue(Pair.of(() -> color, () -> color));
        }
    }
}
