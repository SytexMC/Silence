package me.sytex.silence.listener;

import me.sytex.silence.handler.SilenceHandler;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {

  @EventHandler
  public void onChunkLoad(ChunkLoadEvent event) {
    if (event.isNewChunk()) return;

    for (Entity entity : event.getChunk().getEntities()) {
      SilenceHandler.handle(entity);
    }
  }
}

