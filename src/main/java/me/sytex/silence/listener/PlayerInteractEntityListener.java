package me.sytex.silence.listener;

import me.sytex.silence.Silence;
import me.sytex.silence.handler.SilenceHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractEntityListener implements Listener {

  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

    if (item.getType() == Material.NAME_TAG && item.hasItemMeta()) {
      ItemMeta meta = item.getItemMeta();
      if (meta.hasDisplayName()) {
        new BukkitRunnable() {
          @Override
          public void run() {
            SilenceHandler.handle(event.getRightClicked());
          }
        }.runTaskLater(Silence.plugin, 1L);
      }
    }
  }
}
