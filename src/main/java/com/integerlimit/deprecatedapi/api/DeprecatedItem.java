package com.integerlimit.deprecatedapi.api;

import net.minecraft.item.ItemStack;

/**
 * A deprecated item. Has a tooltip message.
 */
public class DeprecatedItem {
    public static final String defaultMessage = "Deprecated";
    private String tooltipMessage;
    protected ItemStack stack = ItemStack.EMPTY;
    protected int meta = 0;

    @SuppressWarnings("unused")
    public DeprecatedItem() {
        tooltipMessage = defaultMessage;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public DeprecatedItem setTooltipMessage(String message) {
        this.tooltipMessage = message;
        return this;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public DeprecatedItem setMessages(String message) {
        setTooltipMessage(message);
        return this;
    }

    public DeprecatedItem setItem(ItemStack item, int meta) {
        this.stack = item;
        this.meta = meta;
        return this;
    }

    public String getTooltipMessage() {
        return tooltipMessage;
    }

    public boolean matches(ItemStack item) {
        return meta == DeprecatedItems.WILDCARD_META ? item.getItem() == stack.getItem() : ItemStack.areItemStacksEqual(stack, item);
    }
}
