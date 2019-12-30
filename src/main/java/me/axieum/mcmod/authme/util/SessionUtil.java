package me.axieum.mcmod.authme.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.util.UUIDTypeAdapter;
import me.axieum.mcmod.authme.AuthMe;
import me.axieum.mcmod.authme.api.Status;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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

    /**
     * Attempts to login and set a new session for the current Minecraft instance.
     *
     * @param username Minecraft account username
     * @param password Minecraft account password
     * @throws AuthenticationException unable to communicate with authentication services
     * @throws IllegalAccessException  unable to replace current session on the Minecraft instance
     */
    public static void login(String username, String password) throws AuthenticationException, IllegalAccessException
    {
        // Set credentials and login
        yua.setUsername(username);
        yua.setPassword(password);
        yua.logIn();

        // Fetch useful session data
        final String name = yua.getSelectedProfile().getName();
        final String uuid = UUIDTypeAdapter.fromUUID(yua.getSelectedProfile().getId());
        final String token = yua.getAuthenticatedToken();
        final String type = yua.getUserType().getName();

        // Logout after fetching what is needed
        yua.logOut();

        // Persist the new session to the Minecraft instance
        setSession(new Session(name, uuid, token, type));

        AuthMe.LOGGER.info("Session login successful.");
    }

    /**
     * Replaces the session on the Minecraft instance.
     *
     * @param session new session with updated properties
     */
    private static void setSession(Session session) throws IllegalAccessException
    {
        // NB: Minecraft#session is a final property - use reflection
        ObfuscationReflectionHelper.findField(Minecraft.class, "session")
                                   .set(Minecraft.getInstance(), session);
        lastStatusCheck = 0; // check again
    }
}
