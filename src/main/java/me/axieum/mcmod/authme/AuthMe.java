package me.axieum.mcmod.authme;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("authme")
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class AuthMe
{
    public static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event)
    {
        LOGGER.info("AuthMe is setting up...");
    }
}
