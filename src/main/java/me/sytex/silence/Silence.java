package me.sytex.silence;

import static org.bukkit.Bukkit.getPluginManager;

import me.sytex.silence.config.ConfigManager;
import me.sytex.silence.config.SilenceConfig;
import me.sytex.silence.handler.SilenceHandler;
import me.sytex.silence.listener.EntitySpawnListener;
import me.sytex.silence.listener.PlayerInteractEntityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Silence extends JavaPlugin {

  public static SilenceConfig config;
  public static Silence plugin;

  @Override
  public void onEnable() {
    Silence.plugin = this;

    ConfigManager<SilenceConfig> configManager = ConfigManager.create(getDataFolder().toPath(), "config.conf", SilenceConfig.class);
    configManager.reloadConfig();

    config = configManager.getConfigData();

    getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
    getPluginManager().registerEvents(new EntitySpawnListener(), this);

    Silence.plugin.getServer().getWorlds().forEach(world -> {
      world.getEntities().stream()
          .filter(entity -> entity.getCustomName() != null)
          .forEach(SilenceHandler::handle);
    });
  }
}
