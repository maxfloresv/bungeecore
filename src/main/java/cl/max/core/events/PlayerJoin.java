package cl.max.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println("Hola, se unio un jugador");
        Player player = event.getPlayer();
        if (player.hasPermission("core.join")) {
            for (Player p: Bukkit.getOnlinePlayers()) {
                p.sendMessage(p.getDisplayName());
            }
        }
        System.out.println(player);
    }
}