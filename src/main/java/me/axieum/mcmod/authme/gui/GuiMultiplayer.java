package me.axieum.mcmod.authme.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axieum.mcmod.authme.AuthMe;
import me.axieum.mcmod.authme.api.Status;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class GuiMultiplayer
{
    static Status status = Status.OFFLINE;

    @SubscribeEvent
    public static void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if (!(event.getGui() instanceof MultiplayerScreen)) return;

        AuthMe.LOGGER.debug("Injecting authentication widgets into multiplayer screen");

        // Inject authenticate button at top left, using lock texture or fallback text
        event.addWidget(new ImageButton(6,
                                        6,
                                        20,
                                        20,
                                        0,
                                        146,
                                        20,
                                        new ResourceLocation("minecraft:textures/gui/widgets.png"),
                                        256,
                                        256,
                                        button -> status = status == Status.OFFLINE ? Status.ONLINE : Status.OFFLINE,
                                        I18n.format("gui.authme.button.multiplayer")));
    }

    @SubscribeEvent
    public static void onGuiPostDraw(GuiScreenEvent.DrawScreenEvent.Post event)
    {
        if (!(event.getGui() instanceof MultiplayerScreen)) return;

        final MultiplayerScreen screen = (MultiplayerScreen) event.getGui();
        final FontRenderer fontRenderer = screen.getMinecraft().fontRenderer;

        // Draw status text/icon on button
        screen.drawString(fontRenderer, ChatFormatting.BOLD + status.toString(), 20, 5, status.color);
    }
}
