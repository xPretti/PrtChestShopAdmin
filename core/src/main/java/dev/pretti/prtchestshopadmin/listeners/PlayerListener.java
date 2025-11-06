package dev.pretti.prtchestshopadmin.listeners;

import dev.pretti.prtchestshopadmin.PrtChestShopAdmin;
import dev.pretti.prtchestshopadmin.constants.PermissionsConstants;
import dev.pretti.prtchestshopadmin.managers.PricingManager;
import dev.pretti.prtchestshopadmin.services.ChestShopService;
import dev.pretti.prtchestshopadmin.types.PricingDetails;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener
{
  private final PricingManager   pricingManager;
  private final ChestShopService chestShopService;

  public PlayerListener(PrtChestShopAdmin plugin)
  {
    this.pricingManager   = plugin.getPricingManager();
    this.chestShopService = plugin.getChestShopService();
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event)
  {
    Player player = event.getPlayer();
    pricingManager.removePricingDetails(player.getUniqueId());
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerInteract(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if(player.isOp() || player.hasPermission(PermissionsConstants.PERM_COMMAND_CSA_START))
      {
        ItemStack handItem = player.getItemInHand();
        if(handItem == null || handItem.getType() == Material.AIR)
          {
            return;
          }
        PricingDetails pricingDetails = pricingManager.getPricingDetails(player.getUniqueId());
        if(pricingDetails == null)
          {
            return;
          }
        Block block = event.getClickedBlock();
        if(block != null && block.getState() instanceof Sign)
          {
            Sign sign = (Sign) block.getState();
            chestShopService.setPricingDetails(player, sign, pricingDetails);
            event.setCancelled(true);
          }
      }
  }
}
