package me.axieum.mcmod.authme.gui;

import com.mojang.authlib.exceptions.AuthenticationException;
import me.axieum.mcmod.authme.AuthMe;
import me.axieum.mcmod.authme.util.SessionUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiAuth extends Screen
{
    private final Screen parentScreen;

    private TextFieldWidget usernameField, passwordField;
    private GuiButtonExt loginButton, cancelButton;
    private ITextComponent message;

    public GuiAuth(Screen parentScreen)
    {
        super(new TranslationTextComponent("gui.authme.auth.title"));
        this.parentScreen = parentScreen;
        this.minecraft = parentScreen.getMinecraft();
    }

    @Override
    protected void init()
    {
        super.init();
        this.getMinecraft().keyboardListener.enableRepeatEvents(true);

        // Username Text Field
        usernameField = new TextFieldWidget(this.font,
                                            this.width / 2 - 100,
                                            66,
                                            200,
                                            20,
                                            I18n.format("gui.authme.auth.field.username"));
        usernameField.func_212954_a(value -> this.updateFormState());
        this.children.add(usernameField);

        // Password Text Field
        passwordField = new TextFieldWidget(this.font,
                                            this.width / 2 - 100,
                                            106,
                                            200,
                                            20,
                                            I18n.format("gui.authme.auth.field.password"));
        passwordField.func_212954_a(value -> this.updateFormState());
        passwordField.setFocused2(true); // Focus password initially (as we've already suggested a username)
        this.children.add(passwordField);

        // Login Button
        loginButton = new GuiButtonExt(this.width / 2 - 100,
                                       this.height / 4 + 96 + 18,
                                       200,
                                       20,
                                       I18n.format("gui.authme.auth.button.login"),
                                       button -> this.submit());
        this.addButton(loginButton);

        // Cancel Button
        cancelButton = new GuiButtonExt(this.width / 2 - 100,
                                        this.height / 4 + 120 + 18,
                                        200,
                                        20,
                                        I18n.format("gui.authme.auth.button.cancel"),
                                        button -> this.onClose());
        this.addButton(cancelButton);

        this.updateFormState();
    }

    @Override
    public boolean shouldCloseOnEsc()
    {
        return !this.usernameField.isFocused() && !this.passwordField.isFocused();
    }

    @Override
    public void onClose()
    {
        this.passwordField.setText("");
        this.getMinecraft().displayGuiScreen(parentScreen);
    }

    @Override
    public void removed()
    {
        this.getMinecraft().keyboardListener.enableRepeatEvents(false);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_)
    {
        this.renderBackground();

        this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 17, 16777215);

        if (this.message != null)
            this.drawCenteredString(this.font, this.message.getFormattedText(), this.width / 2, 34, 16777215);

        this.drawString(this.font,
                        I18n.format("gui.authme.auth.field.username.title"),
                        this.width / 2 - 100,
                        53,
                        10526880);
        this.drawString(this.font,
                        I18n.format("gui.authme.auth.field.password.title"),
                        this.width / 2 - 100,
                        94,
                        10526880);

        this.usernameField.render(p_render_1_, p_render_2_, p_render_3_);
        this.passwordField.render(p_render_1_, p_render_2_, p_render_3_);

        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    /**
     * Handle updating the authentication form state. This handles submit
     * button enabling/disabling.
     */
    public void updateFormState()
    {
        // Clear username suggestion if they're typing something else
        this.usernameField.setSuggestion(
                this.usernameField.getText().isEmpty() ? this.getMinecraft().getSession().getUsername() : "");

        // Enable/disable the login button if the form is valid
        this.loginButton.active = !this.passwordField.getText().isEmpty();
    }

    /**
     * Submits the current form.
     */
    public void submit()
    {
        if (!this.loginButton.active)
            return;

        try {
            SessionUtil.login(usernameField.getText(), passwordField.getText());
            this.message = new TranslationTextComponent(
                    "gui.authme.auth.message.success")
                    .setStyle(new Style().setBold(true)
                                         .setColor(TextFormatting.GREEN));
            AuthMe.LOGGER.warn("Logged into '{}' Minecraft account", SessionUtil.getSession().getUsername());
        } catch (AuthenticationException | IllegalAccessException e) {
            this.message = new TranslationTextComponent(
                    "gui.authme.auth.message.failure")
                    .setStyle(new Style().setBold(true)
                                         .setColor(TextFormatting.RED));

            AuthMe.LOGGER.warn("Unable to login to session: {}", e.getMessage());
        }
    }
}
