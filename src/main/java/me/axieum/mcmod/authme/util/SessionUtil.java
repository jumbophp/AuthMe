package me.axieum.mcmod.authme.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.axieum.mcmod.authme.AuthMe;
import me.axieum.mcmod.authme.api.Status;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.util.UUID;

public class SessionUtil
{
    private static Status lastStatus = Status.UNKNOWN;
    private static long lastStatusCheck;

    /**
     * Authentication Services.
     */
    private static final YggdrasilAuthenticationService yas;
    private static final YggdrasilUserAuthentication yua;
    private static final YggdrasilMinecraftSessionService ymss;

    static {
        yas = new YggdrasilAuthenticationService(Minecraft.getInstance().getProxy(), UUID.randomUUID().toString());
        yua = (YggdrasilUserAuthentication) yas.createUserAuthentication(Agent.MINECRAFT);
        ymss = (YggdrasilMinecraftSessionService) yas.createMinecraftSessionService();
    }

    /**
     * Returns the current session.
     *
     * @return current session instance
     */
    public static Session getSession()
    {
        return Minecraft.getInstance().getSession();
    }

    /**
     * Checks and returns the current session status.
     *
     * @return status of the current session
     */
    public static Status getStatus()
    {
        if (System.currentTimeMillis() - lastStatusCheck < 1000 * 60 * 5)
            return lastStatus;

        final Session session = getSession();
        GameProfile gp = session.getProfile();
        String token = session.getToken();
        String id = UUID.randomUUID().toString();

        try {
            ymss.joinServer(gp, token, id);
            if (ymss.hasJoinedServer(gp, id, null).isComplete()) {
                AuthMe.LOGGER.info("Session validated.");
                lastStatus = Status.VALID;
            } else {
                AuthMe.LOGGER.info("Session invalidated.");
                lastStatus = Status.INVALID;
            }
        } catch (AuthenticationException e) {
            AuthMe.LOGGER.error("Unable to validate session: {}", e.getMessage());
            lastStatus = Status.UNKNOWN;
        }

        lastStatusCheck = System.currentTimeMillis();
        return lastStatus;
    }
}
