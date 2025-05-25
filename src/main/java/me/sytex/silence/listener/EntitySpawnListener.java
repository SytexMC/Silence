package me.sytex.silence.listener;

import me.sytex.silence.handler.SilenceHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

  @EventHandler
  public void onEntitySpawn(EntitySpawnEvent event) {
    SilenceHandler.handle(event.getEntity());
  }
}
