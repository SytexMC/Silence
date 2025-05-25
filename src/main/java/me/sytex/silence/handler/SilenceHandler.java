package me.sytex.silence.handler;

import me.sytex.silence.Silence;
import org.bukkit.entity.Entity;

public class SilenceHandler {

  public static void handle(Entity entity) {
    if (entity.getCustomName() == null)
      return;

    boolean shouldBeSilent = entity.getCustomName().toLowerCase().contains(Silence.config.muteTag().toLowerCase());

    if (entity.isSilent() != shouldBeSilent) {
      entity.setSilent(shouldBeSilent);
    }
  }
}

