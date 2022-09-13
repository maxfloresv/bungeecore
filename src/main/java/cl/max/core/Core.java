package cl.max.core;

import cl.max.core.commands.Fly;
import cl.max.core.commands.Message;
import cl.max.core.config.MessagesConfig;
import org.bukkit.plugin.java.JavaPlugin;
import cl.max.core.events.*;

public final class Core extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[Core] v1.0 iniciado. Autor: vMaximo.");

        MessagesConfig.setup();

        System.out.println("[Core] Configuración cargada.");

        // Registramos cada evento en /events/
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        // Y cada comando de /commands/
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("msg").setExecutor(new Message());
    }

    @Override
    public void onDisable() {
        System.out.println("[Core] v1.0 detenido. Autor: vMaximo.");
    }
}
