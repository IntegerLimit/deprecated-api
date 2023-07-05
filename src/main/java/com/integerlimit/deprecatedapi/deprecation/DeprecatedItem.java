package com.integerlimit.deprecatedapi.deprecation;

/**
 * A deprecated item. Has a tooltip message.
 */
public class DeprecatedItem {
    public static final String defaultMessage = "Deprecated";
    private String tooltipMessage;

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

    public String getTooltipMessage() {
        return tooltipMessage;
    }
}
