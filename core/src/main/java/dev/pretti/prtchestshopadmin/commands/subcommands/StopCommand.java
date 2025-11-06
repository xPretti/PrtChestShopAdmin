package dev.pretti.prtchestshopadmin.commands.subcommands;

import dev.pretti.prtchestshopadmin.commands.base.Command;
import dev.pretti.prtchestshopadmin.commands.base.DoubleCommandArgument;
import dev.pretti.prtchestshopadmin.commands.base.IntCommandArgument;
import dev.pretti.prtchestshopadmin.managers.PricingManager;
import dev.pretti.prtchestshopadmin.types.PricingDetails;
import dev.pretti.prtchestshopadmin.utils.ReplaceUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class StopCommand extends Command
{
  private final PricingManager pricingManager;

  /**
   * Construtor da classe
   */
  public StopCommand(PricingManager pricingManager, @Nullable String command, @Nullable String permission)
  {
    super(command, permission, true);
    this.pricingManager = pricingManager;
  }

  @Override
  public boolean run(CommandSender sender, String command, String[] args)
  {
    if(isCommand(command))
      {
        if(!canUse(sender))
          {
            String message = ReplaceUtils.toColorMessage("§4§lMC §4» §cApenas jogadores podem usar este comando!");
            sender.sendMessage(message);
            return true;
          }
        if(hasPermission(sender))
          {
            Player player = (Player) sender;
            pricingManager.removePricingDetails(player.getUniqueId());
            String message = ReplaceUtils.toColorMessage("§6§lMC §6» §aVocê parou o modo de configuração de lojas!");
            player.sendMessage(message);
            return true;
          }
        else
          {
            String message = ReplaceUtils.toColorMessage("§4§lMC §4» §cVocê não tem acesso para usar este comando!");
            sender.sendMessage(message);
          }
        return true;
      }
    return false;
  }
}
