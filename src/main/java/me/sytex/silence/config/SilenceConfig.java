package me.sytex.silence.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface SilenceConfig {

  @ConfKey("tag")
  @ConfComments("The tag that will be used to silence entities")
  @ConfDefault.DefaultString("silent")
  String muteTag();
}

