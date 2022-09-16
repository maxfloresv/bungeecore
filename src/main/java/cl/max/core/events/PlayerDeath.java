package cl.max.core.events;

import cl.max.core.config.MessagesConfig;
import cl.max.core.utils.StringUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        int X = (int) player.getLocation().getX();
        int Y = (int) player.getLocation().getY();
        int Z = (int) player.getLocation().getZ();

        String msg = MessagesConfig.get().getString("last_death")
                        .replace("%x%", String.valueOf(X))
                        .replace("%y%", String.valueOf(Y))
                        .replace("%z%", String.valueOf(Z));

        if (player.hasPermission("core.last_death.tp")) {
            TextComponent message = new TextComponent(StringUtils.colorize(msg));

            String tp_click = StringUtils.colorize(MessagesConfig.get().getString("last_death_tp"));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(tp_click)));

            String XYZ = X + " " + Y + " " + Z;
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + XYZ));

            player.spigot().sendMessage(message);
        } else {
            player.sendMessage(StringUtils.colorize(msg));
        }
    }
}
