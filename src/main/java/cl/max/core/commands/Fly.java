package cl.max.core.commands;

import cl.max.core.config.MessagesConfig;
import cl.max.core.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("core.fly")) {
                if (player.isFlying()) {
                    String chatMsg = MessagesConfig.get().getString("fly_off")
                            .replace("%player%", player.getDisplayName());
                    player.sendMessage(StringUtils.colorize(chatMsg));
                    player.setAllowFlight(false);
                    player.setFlying(false);
                } else {
                    String chatMsg = MessagesConfig.get().getString("fly_on")
                            .replace("%player%", player.getDisplayName());
                    player.sendMessage(StringUtils.colorize(chatMsg));
                    player.setAllowFlight(true);
                    player.setFlying(true);
                }
            } else {
                String err = MessagesConfig.get().getString("no_perms");
                player.sendMessage(StringUtils.colorize(err));
            }
        } else {
            String err = MessagesConfig.get().getString("no_player_instance");
            sender.sendMessage(err);
        }
        return true;
    }
}
