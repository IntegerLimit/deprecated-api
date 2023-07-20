package com.integerlimit.deprecatedapi.mixin;

import com.integerlimit.deprecatedapi.DeprecatedAPI;
import com.integerlimit.deprecatedapi.DeprecatedRarity;
import com.integerlimit.deprecatedapi.api.DeprecatedItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getForgeRarity", at = @At("HEAD"), cancellable = true, remap = false)
    private void setDeprecatedRarity(ItemStack stack, CallbackInfoReturnable<IRarity> cir) {
        if (DeprecatedItems.getItem(stack) != null) {
            DeprecatedAPI.LOGGER.debug("Set Item " + stack.getItem().getRegistryName()
                    + ", with meta " + stack.getMetadata() + " to Deprecated Rarity.");
            cir.setReturnValue(DeprecatedRarity.DEPRECATED_RARITY);
        }
    }
}
