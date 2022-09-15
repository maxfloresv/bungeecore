package cl.max.core.events;

import cl.max.core.config.MessagesConfig;
import cl.max.core.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String leave_msg = MessagesConfig.get().getString("leave_msg")
                .replace("%player%", player.getDisplayName());

        event.setQuitMessage(StringUtils.colorize(leave_msg));
    }
}
