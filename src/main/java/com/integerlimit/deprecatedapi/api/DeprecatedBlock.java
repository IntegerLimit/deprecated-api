package com.integerlimit.deprecatedapi.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * A deprecated block. Has a TOP/WAILA tooltip + all tooltips in DeprecatedItem Object.
 */
public class DeprecatedBlock extends DeprecatedItem{
    private String TOPWailaMessage;
    protected Block block;

    @SuppressWarnings("unused")
    public DeprecatedBlock() {
        super();
        TOPWailaMessage = defaultMessage;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public DeprecatedBlock setTOPWailaMessage(String message) {
        TOPWailaMessage = message;
        return this;
    }

    public DeprecatedBlock setBlock(Block block, int meta) {
        this.block = block;
        this.setItem(new ItemStack(block, 1, meta));
        return this;
    }

    @SuppressWarnings("unused")
    public String getTOPWailaMessage() {
        return TOPWailaMessage;
    }

    public boolean matches(Block block, int meta) {
        return this.stack.getItemDamage() == DeprecatedItems.WILDCARD_META ? Block.isEqualTo(block, this.block) : (Block.isEqualTo(block, this.block) && meta == stack.getItemDamage());
    }

    @Override
    public DeprecatedBlock setMessages(String message) {
        setTOPWailaMessage(message);
        super.setMessages(message);
        return this;
    }
}
