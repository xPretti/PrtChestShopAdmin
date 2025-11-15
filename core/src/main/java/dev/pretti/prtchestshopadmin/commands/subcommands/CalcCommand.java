package dev.pretti.prtchestshopadmin.commands.subcommands;

import dev.pretti.prtchestshopadmin.commands.base.CalcCommandArgument;
import dev.pretti.prtchestshopadmin.commands.base.Command;
import dev.pretti.prtchestshopadmin.managers.PricingManager;
import dev.pretti.prtchestshopadmin.model.CalcDetails;
import dev.pretti.prtchestshopadmin.model.CalcValue;
import dev.pretti.prtchestshopadmin.utils.ReplaceUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CalcCommand extends Command
{
  private final PricingManager pricingManager;

  private final CalcCommandArgument buyArg;
  private final CalcCommandArgument sellArg;

  /**
   * Construtor da classe
   */
  public CalcCommand(PricingManager pricingManager, @Nullable String command, @Nullable String permission) {
    super(command, permission, true);
    this.pricingManager = pricingManager;

    buyArg  = new CalcCommandArgument("§4§lMC §4» §cO valor do calculo de compra esta incorreto.");
    sellArg = new CalcCommandArgument("§4§lMC §4» §cO valor do calculo de venda esta incorreto.");
  }

  @Override
  public boolean run(CommandSender sender, String command, String[] args) {
    if(isCommand(command)) {
      if(!canUse(sender)) {
        String message = ReplaceUtils.toColorMessage("§4§lMC §4» §cApenas jogadores podem usar este comando!");
        sender.sendMessage(message);
        return true;
      }
      if(hasPermission(sender)) {
        Player player = (Player) sender;
        if(args.length > 0) {
          CalcValue buyPrice  = buyArg.run(player, args[0]);
          CalcValue sellPrice = null;
          if(args.length > 1) {
            sellPrice = sellArg.run(player, args[1]);
          }

          if(buyPrice != null || sellPrice != null) {
            pricingManager.addSignDetails(player.getUniqueId(), new CalcDetails(buyPrice, sellPrice));
            String[] message = {
                    "",
                    "§6§lMC §6» §aVocê iniciou o modo de configuração de lojas!",
                    "§6§lMC §6» §7Bata em uma placa para recalcular os preços.",
                    "§6§lMC §6» §7Caso deseje parar, digite §c/csa stop",
                    ""
            };
            player.sendMessage(message);
            return true;
          }
          return true;
        }
        String message = ReplaceUtils.toColorMessage("§4§lUse: §c/csa <buy price | 0> [sell price] §4Exemplo: §c/csa calc +1 *2");
        player.sendMessage(message);
        return true;
      }
      else {
        String message = ReplaceUtils.toColorMessage("§4§lMC §4» §cVocê não tem acesso para usar este comando!");
        sender.sendMessage(message);
      }
      return true;
    }
    return false;
  }
}
