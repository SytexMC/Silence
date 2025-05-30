package me.sytex.silence.config;

import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.hocon.HoconConfigurationFactory;
import space.arim.dazzleconf.ext.hocon.HoconOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public final class ConfigManager<C> {

  private final ConfigurationHelper<C> configHelper;
  private volatile C configData;

  private ConfigManager(ConfigurationHelper<C> configHelper) {
    this.configHelper = configHelper;
  }

  public static <C> ConfigManager<C> create(Path configFolder, String fileName, Class<C> configClass) {
    HoconOptions hoconOptions = new HoconOptions.Builder()
        .build();
    ConfigurationFactory<C> configFactory = HoconConfigurationFactory.create(
        configClass,
        ConfigurationOptions.defaults(),
        hoconOptions);
    return new ConfigManager<>(new ConfigurationHelper<>(configFolder, fileName, configFactory));
  }

  public void reloadConfig() {
    try {
      configData = configHelper.reloadConfigData();
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);

    } catch (ConfigFormatSyntaxException ex) {
      configData = configHelper.getFactory().loadDefaults();
      System.err.println("The hocon syntax in your configuration is invalid. "
          + "Check your hocon syntax with a tool such as https://toolbox.helpch.at/validators/hocon");
      ex.printStackTrace();

    } catch (InvalidConfigException ex) {
      configData = configHelper.getFactory().loadDefaults();
      System.err.println("One of the values in your configuration is not valid. "
          + "Check to make sure you have specified the right data types.");
      ex.printStackTrace();
    }
  }

  public C getConfigData() {
    C configData = this.configData;
    if (configData == null) {
      throw new IllegalStateException("Configuration has not been loaded yet");
    }
    return configData;
  }

}
