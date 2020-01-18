package me.axieum.mcmod.authme.gui;

import me.axieum.mcmod.authme.AuthMe;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class GuiDisconnected
{
    @SubscribeEvent
    public static void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if (!(event.getGui() instanceof DisconnectedScreen)) return;
        final DisconnectedScreen screen = (DisconnectedScreen) event.getGui();

        // Determine if the disconnection reason is session related
        final ITextComponent reason = getDisconnectReason(screen);
        if (reason == null || !getTranslationKey(reason).startsWith("disconnect.loginFailed")) return;

        final Screen prevScreen = getPreviousScreen(screen);
        final Widget backButton = event.getWidgetList().get(0);

        // Inject the authentication button where the back button was
        AuthMe.LOGGER.debug("Injecting authentication button into disconnection screen");
        event.addWidget(new GuiButtonExt(backButton.x,
                                         backButton.y,
                                         backButton.getWidth(),
                                         backButton.getHeight(),
                                         I18n.format("gui.authme.disconnect.button.auth"),
                                         button -> screen.getMinecraft().displayGuiScreen(new GuiAuth(prevScreen))));

        // Move back button below
        backButton.y += 26;
    }

    /**
     * Retrieves the disconnect reason message from a disconnection screen
     * instance.
     *
     * @param screen disconnection screen instance
     * @return disconnect reason text component or null
     */
    @Nullable
    private static ITextComponent getDisconnectReason(DisconnectedScreen screen)
    {
        try {
            return (ITextComponent) ObfuscationReflectionHelper.findField(DisconnectedScreen.class, "field_146304_f")
                                                               .get(screen);
        } catch (IllegalAccessException e) {
            AuthMe.LOGGER.warn("Unable to determine disconnection reason: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the previous screen instance from a disconnection screen
     * instance.
     *
     * @param screen disconnection screen instance
     * @return previous screen instance
     */
    @Nonnull
    private static Screen getPreviousScreen(DisconnectedScreen screen)
    {
        try {
            return (Screen) ObfuscationReflectionHelper.findField(DisconnectedScreen.class, "field_146307_h")
                                                       .get(screen);
        } catch (IllegalAccessException e) {
            AuthMe.LOGGER.warn("Unable to determine the previous screen: {}", e.getMessage());
            return new MainMenuScreen();
        }
    }

    /**
     * Returns the translation key for a text component.
     *
     * @param component text component
     * @return translation key of translation text component else empty string
     */
    private static String getTranslationKey(ITextComponent component)
    {
        return component instanceof TranslationTextComponent ? ((TranslationTextComponent) component).getKey()
                                                             : "";
    }
}
