package me.axieum.mcmod.authme.gui.widget;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.TextFormatting;

public class PasswordFieldWidget extends TextFieldWidget
{
    public PasswordFieldWidget(FontRenderer font, int x, int y, int width, int height, String msg)
    {
        super(font, x, y, width, height, null, msg);
        setMaxStringLength(256);
        setTextFormatter((value, limit) -> TextFormatting.OBFUSCATED + value);

//        // NB: Overriding the rendered characters affects interaction, as the
//        // actual rendered characters have different widths to the actual text.
//        setTextFormatter((value, limit) -> StringUtils.repeat('\u204E', value.length()));
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_)
    {
        // Prevent copy/cut
        if (!this.func_212955_f() || Screen.isCopy(p_keyPressed_1_) || Screen.isCut(p_keyPressed_1_))
            return false;

        return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }
}
