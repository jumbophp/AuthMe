package me.axieum.mcmod.authme.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import me.axieum.mcmod.authme.gui.widget.PasswordFieldWidget;
import me.axieum.mcmod.authme.util.SessionUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Session;
import net.minecraft.util.text.*;

public class GuiAuth extends Screen
{
    private final Screen parentScreen;

    private TextFieldWidget usernameField, passwordField;
    private Button loginButton, cancelButton;
    private ITextComponent greeting, message;
    private String lastUsername;

    public GuiAuth(Screen parentScreen)
    {
        super(new TranslationTextComponent("gui.authme.auth.title"));
        this.parentScreen = parentScreen;
        minecraft = parentScreen.getMinecraft();
        lastUsername = SessionUtil.getSession().getUsername();
        greeting = getGreeting(lastUsername);
    }

    @Override
    protected void init()
    {
        super.init();
        getMinecraft().keyboardListener.enableRepeatEvents(true);

        // Username Text Field
        usernameField = new TextFieldWidget(font,
                                            width / 2 - 100,
                                            76,
                                            200,
                                            20,
                                            I18n.format("gui.authme.auth.field.username"));
        usernameField.setMaxStringLength(128);
        usernameField.setSuggestion(lastUsername); // Suggest their current username
        usernameField.func_212954_a(value -> {
            // Clear username suggestion if they're typing something else
            usernameField.setSuggestion(value.isEmpty() ? lastUsername : "");
            // Update the login button submission state
            loginButton.active = canSubmit();
        });
        children.add(usernameField);

        // Password Text Field
        passwordField = new PasswordFieldWidget(font,
                                                width / 2 - 100,
                                                116,
                                                200,
                                                20,
                                                I18n.format("gui.authme.auth.field.password"));
        passwordField.setFocused2(true); // Focus password initially (as we've already suggested a username)
        passwordField.func_212954_a(value -> {
            // Tweak the login button depending if password is given or not
            loginButton.setMessage(I18n.format("gui.authme.auth.button.login."
                                               + (value.isEmpty() ? "offline" : "online")));
            loginButton.active = canSubmit();
        });
        children.add(passwordField);

        // Login Button
        loginButton = new Button(width / 2 - 100,
                                 height / 4 + 96 + 18,
                                 200,
                                 20,
                                 I18n.format("gui.authme.auth.button.login.offline"),
                                 button -> submit());
        loginButton.active = false;
        addButton(loginButton);

        // Cancel Button
        cancelButton = new Button(width / 2 - 100,
                                  height / 4 + 120 + 18,
                                  200,
                                  20,
                                  I18n.format("gui.authme.auth.button.cancel"),
                                  button -> onClose());
        addButton(cancelButton);
    }

    @Override
    public boolean shouldCloseOnEsc()
    {
        return !usernameField.isFocused() && !passwordField.isFocused();
    }

    @Override
    public void onClose()
    {
        passwordField.setText("");
        getMinecraft().displayGuiScreen(parentScreen);
    }

    @Override
    public void removed()
    {
        getMinecraft().keyboardListener.enableRepeatEvents(false);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_)
    {
        renderBackground();

        drawCenteredString(font, title.getFormattedText(), width / 2, 17, 16777215);
        drawCenteredString(font, greeting.getFormattedText(), width / 2, 34, 16777215);

        if (message != null)
            drawCenteredString(font, message.getFormattedText(), width / 2, height / 4 + 86, 16777215);

        drawString(font, I18n.format("gui.authme.auth.field.username"), width / 2 - 100, 64, 10526880);
        drawString(font, I18n.format("gui.authme.auth.field.password"), width / 2 - 100, 104, 10526880);

        usernameField.render(p_render_1_, p_render_2_, p_render_3_);
        passwordField.render(p_render_1_, p_render_2_, p_render_3_);

        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    /**
     * Sets the flash status message.
     *
     * @param message text component to show
     */
    public void setMessage(ITextComponent message)
    {
        this.message = message;
    }

    /**
     * Determines if the current form can be submitted.
     *
     * @return true if the form is ready for submission
     */
    protected boolean canSubmit()
    {
        return !usernameField.getText().isEmpty() || !passwordField.getText().isEmpty();
    }

    /**
     * Submits the current form, logging the credentials in.
     */
    public void submit()
    {
        // Prevent pre-mature submissions
        if (!loginButton.active) return;
        loginButton.active = false; // disable login button while logging in

        final String username = usernameField.getText().isEmpty() ? lastUsername : usernameField.getText();
        final String password = passwordField.getText();

        if (password.isEmpty()) {
            // Play offline
            Session offlineSession = SessionUtil.login(username);

            lastUsername = offlineSession.getUsername();
            greeting = getGreeting(lastUsername);
            message = new TranslationTextComponent("gui.authme.auth.message.success.offline")
                    .setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));

            // Reset form
            usernameField.setText("");
            passwordField.setText("");
        } else {
            // Login
            SessionUtil.login(username, password)
                       .thenAccept(session -> {
                           // Successful login attempt
                           lastUsername = session.getUsername();
                           greeting = getGreeting(lastUsername);

                           // Set the message contents and style it as successful
                           message = new TranslationTextComponent("gui.authme.auth.message.success")
                                   .setStyle(new Style().setBold(true).setColor(TextFormatting.GREEN));

                           // Reset form
                           usernameField.setText("");
                           passwordField.setText("");
                       })
                       .exceptionally(e -> {
                           // Failed login attempt
                           loginButton.active = true; // re-enable login button to try again with same credentials

                           // Set the message contents and style it as an error
                           if (e.getCause() instanceof InvalidCredentialsException)
                               message
                                       = new TranslationTextComponent("gui.authme.auth.message.failed.credentials");
                           else
                               message = new TranslationTextComponent("gui.authme.auth.message.failed.generic",
                                                                      e.getCause().getMessage());

                           message.setStyle(new Style().setBold(true).setColor(TextFormatting.RED));

                           return null;
                       });
        }
    }

    /**
     * Formats and returns a greeting text component.
     *
     * @param username username in greeting
     * @return formatted translatable text component greeting
     */
    protected ITextComponent getGreeting(String username)
    {
        return new TranslationTextComponent("gui.authme.auth.greeting",
                                            new StringTextComponent(username)
                                                    .setStyle(new Style().setColor(TextFormatting.YELLOW)))
                .setStyle(new Style().setColor(TextFormatting.GRAY));
    }
}
