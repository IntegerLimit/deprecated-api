package com.integerlimit.deprecatedapi;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

/* Copied from vanilla's EnumRarity */
public enum DeprecatedRarity implements IRarity
{
    DEPRECATED_RARITY(TextFormatting.RED, "deprecated");

    /** The color assigned to this rarity type. */
    public final TextFormatting color;

    /** Rarity name. */
    public final String rarityName;

    DeprecatedRarity(TextFormatting color, String name)
    {
        this.color = color;
        this.rarityName = name;
    }

    @Override
    public TextFormatting getColor()
    {
        return this.color;
    }

    @Override
    public String getName()
    {
        return this.rarityName;
    }
}