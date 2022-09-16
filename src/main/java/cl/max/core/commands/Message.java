package cl.max.core.commands;

import cl.max.core.config.MessagesConfig;
import cl.max.core.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {
    private String format = "/msg <jugador> <mensaje>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length <= 1) {
                String err = MessagesConfig.get().getString("use")
                                .replace("%cmd%", format);
                player.sendMessage(StringUtils.colorize(err));
                return true;
            }

            boolean is_online = Bukkit.getPlayer(args[0]) != null;
            if (is_online) {
                Player receiver = Bukkit.getPlayer(args[0]);
                if (receiver.getDisplayName().equals(player.getDisplayName())) {
                    String err = MessagesConfig.get().getString("cant_send_yourself");
                    player.sendMessage(StringUtils.colorize(err));
                    return true;
                }

                String format = MessagesConfig.get().getString("msg_format")
                        .replace("%sender%", player.getDisplayName())
                        .replace("%receiver%", receiver.getDisplayName())
                        .replace("%message%", StringUtils.listToStr(args, 1, args.length - 1));
                player.sendMessage(StringUtils.colorize(format));
                receiver.sendMessage(StringUtils.colorize(format));
            } else {
                String err = MessagesConfig.get().getString("not_connected");
                player.sendMessage(StringUtils.colorize(err));
            }
        } else {
            String err = MessagesConfig.get().getString("no_player_instance");
            sender.sendMessage(err);
        }
        return true;
    }
}
