package dev.pretti.prtchestshopadmin.commands;

import dev.pretti.prtchestshopadmin.PrtChestShopAdmin;
import dev.pretti.prtchestshopadmin.commands.base.Commands;
import dev.pretti.prtchestshopadmin.commands.subcommands.CalcCommand;
import dev.pretti.prtchestshopadmin.commands.subcommands.StartCommand;
import dev.pretti.prtchestshopadmin.commands.subcommands.StopCommand;
import dev.pretti.prtchestshopadmin.constants.PermissionsConstants;
import dev.pretti.prtchestshopadmin.utils.ReplaceUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class MainCommand extends Commands implements CommandExecutor, TabCompleter
{
  private final PrtChestShopAdmin plugin;

  public MainCommand(PrtChestShopAdmin plugin) {
    super(null, PermissionsConstants.PERM_COMMAND_CSA);
    this.plugin = plugin;

    register(new StartCommand(plugin.getPricingManager(), "start", PermissionsConstants.PERM_COMMAND_CSA_START));
    register(new CalcCommand(plugin.getPricingManager(), "calc", PermissionsConstants.PERM_COMMAND_CSA_CALC));
    register(new StopCommand(plugin.getPricingManager(), "stop", PermissionsConstants.PERM_COMMAND_CSA_START));
  }


  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if(hasPermission(sender)) {
      if(!run(sender, s, strings)) {
        String[] message = {
                "",
                "§6§lPrtChestShopAdmin §7- §eComandos de ajuda:",
                "",
                "§6* §e/csa start <amount> <buy | 0> <sell>§f= §7Inicia a criação de loja por iteração;",
                "§6* §e/csa calc <buy | 0> <sell>§f= §7Altera o preço da loja; (+1, *2, /2, =220)",
                "§6* §e/csa stop §f= §7Para a criação de loja por iteração;",
                ""
        };
        sender.sendMessage(message);
      }
    }
    else {
      String message = "§4§lMC §4» §cVocê não possui acesso a este comando!";
      sender.sendMessage(ReplaceUtils.toColorMessage(message));
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if(args.length == 1) {
      return getCommandsNames(args[0]);
    }
    else if(args.length > 1) {
      return runAutoComplete(sender, args[args.length - 2]);
    }
    return null;
  }
}
