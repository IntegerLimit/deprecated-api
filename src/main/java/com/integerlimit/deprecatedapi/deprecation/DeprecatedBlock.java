package com.integerlimit.deprecatedapi.deprecation;

/**
 * A deprecated block. Has a TOP/WAILA tooltip + all tooltips in DeprecatedItem Object.
 */
public class DeprecatedBlock extends DeprecatedItem{
    private String TOPWailaMessage;

    @SuppressWarnings("unused")
    public DeprecatedBlock() {
        TOPWailaMessage = defaultMessage;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public DeprecatedBlock setTOPWailaMessage(String message) {
        TOPWailaMessage = message;
        return this;
    }

    @SuppressWarnings("unused")
    public String getTOPWailaMessage() {
        return TOPWailaMessage;
    }

    @Override
    public DeprecatedBlock setMessages(String message) {
        setTOPWailaMessage(message);
        super.setMessages(message);
        return this;
    }
}
