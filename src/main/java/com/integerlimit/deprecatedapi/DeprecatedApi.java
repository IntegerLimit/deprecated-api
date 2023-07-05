package com.integerlimit.deprecatedapi;

import com.integerlimit.deprecatedapi.deprecation.DeprecatedBlocks;
import com.integerlimit.deprecatedapi.deprecation.DeprecatedItems;
import com.integerlimit.deprecatedapi.integration.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required:forge@[14.23.5.2847,);"
                + "after:jei@[4.15.0,);"
                + "after:theoneprobe;"
                + "after:hwyla;")
public class DeprecatedApi {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void postInit(FMLPostInitializationEvent event) {
        Registry.init();
        /* Tests */
        DeprecatedItems.addDeprecatedItem(new ResourceLocation("minecraft:cooked_beef")).setTooltipMessage("Steak Sucks!");
        DeprecatedBlocks.addDeprecatedBlock(new ResourceLocation("minecraft:dirt")).setTOPWailaMessage("Use Coarse Dirt instead!").setTooltipMessage("Dirt Sucks!");
        DeprecatedItems.addDeprecatedItem(new ResourceLocation("minecraft:diamond_sword"));
        DeprecatedBlocks.addDeprecatedBlock(new ResourceLocation("minecraft:concrete")).setMessages("Concrete is disabled for now.");
        DeprecatedBlocks.addDeprecatedBlock(new ResourceLocation("minecraft:concrete"), 1).setMessages("This Concrete is disabled forever.");
        DeprecatedBlocks.addDeprecatedBlock(new ResourceLocation("minecraft:stone")).setMessages("Dead");
        DeprecatedItems.addDeprecatedItem(new ResourceLocation("minecraft:stone_sword")).setTooltipMessage("Worst");
    }

    /* Might be wanted later if making command for reload
    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }
    */
}
