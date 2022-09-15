package cl.max.core.events;

import cl.max.core.config.MessagesConfig;
import cl.max.core.utils.StringUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);

        String prefix = user.getCachedData().getMetaData().getPrefix();
        String chat_format = MessagesConfig.get().getString("chat_format")
                .replace("{PREFIX}", prefix)
                .replace("{USER}", player.getDisplayName())
                .replace("{MESSAGE}", event.getMessage());
        event.setFormat(StringUtils.colorize(chat_format));
    }
}
