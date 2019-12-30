package me.axieum.mcmod.authme.api;

import net.minecraft.client.resources.I18n;

public enum Status
{
    VALID("gui.authme.status.validated", 0xFF00FF00),
    INVALID("gui.authme.status.invalidated", 0xFFFF0000),
    UNKNOWN("gui.authme.status.unknown", 0xFFFF0000);

    public final String langKey;
    public final int color;

    Status(String langKey, int color)
    {
        this.langKey = langKey;
        this.color = color;
    }

    @Override
    public String toString()
    {
        return I18n.format(langKey);
    }
}
