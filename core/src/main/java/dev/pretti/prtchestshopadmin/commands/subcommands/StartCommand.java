package dev.pretti.prtchestshopadmin.commands.subcommands;

import dev.pretti.prtchestshopadmin.commands.base.Command;
import dev.pretti.prtchestshopadmin.commands.base.DoubleCommandArgument;
import dev.pretti.prtchestshopadmin.commands.base.IntCommandArgument;
import dev.pretti.prtchestshopadmin.managers.PricingManager;
import dev.pretti.prtchestshopadmin.model.PricingDetails;
import dev.pretti.prtchestshopadmin.utils.ReplaceUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class StartCommand extends Command
{
  private final PricingManager pricingManager;

  private final IntCommandArgument    amountArg;
  private final DoubleCommandArgument buyArg;
  private final DoubleCommandArgument sellArg;

  /**
   * Construtor da classe
   */
  public StartCommand(PricingManager pricingManager, @Nullable String command, @Nullable String permission) {
    super(command, permission, true);
    this.pricingManager = pricingManager;

    amountArg = new IntCommandArgument("§4§lMC §4» §cA quantidade precisa ser um numero inteiro.");
    buyArg    = new DoubleCommandArgument("§4§lMC §4» §cO preço de compra precisa ser um número válido!");
    sellArg   = new DoubleCommandArgument("§4§lMC §4» §cO preço de venda precisa ser um número válido!");
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
          Integer amount = amountArg.run(player, args[0]);
          if(amount != null && args.length > 1) {
            Double buyPrice  = buyArg.run(player, args[1]);
            Double sellPrice = null;
            if(args.length > 2) {
              sellPrice = sellArg.run(player, args[2]);
              if(sellPrice == null) {
                return true;
              }
            }
            if(buyPrice != null && buyPrice <= 0) {
              buyPrice = null;
            }
            if(sellPrice != null && sellPrice <= 0) {
              sellPrice = null;
            }
            if(buyPrice != null || sellPrice != null) {
              pricingManager.addSignDetails(player.getUniqueId(), new PricingDetails(amount, buyPrice, sellPrice));
              String[] message = {
                      "",
                      "§6§lMC §6» §aVocê iniciou o modo de configuração de lojas!",
                      String.format("§6§lMC §6» §7Quantidade: §b%s §7| Preço compra: §a%s§7 | Preço venda: §c%s", amount, buyPrice == null ? "0" : buyPrice, sellPrice == null ? "0" : sellPrice),
                      "§6§lMC §6» §7Segure um item na mão e bata em uma placa.",
                      "§6§lMC §6» §7Caso deseje parar, digite §c/csa stop",
                      ""
              };
              player.sendMessage(message);
              return true;
            }
            return true;
          }
        }
        String message = ReplaceUtils.toColorMessage("§4§lUse: §c/csa start <amount> <buy price> [sell price]");
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
