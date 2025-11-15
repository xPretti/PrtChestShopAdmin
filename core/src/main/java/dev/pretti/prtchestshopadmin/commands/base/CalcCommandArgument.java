package dev.pretti.prtchestshopadmin.commands.base;

import dev.pretti.prtchestshopadmin.commands.interfaces.ICommandArgument;
import dev.pretti.prtchestshopadmin.enums.EnumCalcType;
import dev.pretti.prtchestshopadmin.model.CalcValue;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

public class CalcCommandArgument implements ICommandArgument<CalcValue>
{
  private final String error;

  public CalcCommandArgument(String error) {
    this.error = error;
  }

  @Override
  @Nullable
  public CalcValue run(CommandSender sender, String argument) {

    if(argument == "0") {
      return null;
    }
    if(argument == null || argument.isEmpty()) {
      sendError(sender);
      return null;
    }

    EnumCalcType calcType;
    double       value;

    String op = argument.substring(0, 1);

    if(EnumCalcType.VALUES_NAMES.containsKey(op)) {
      calcType = EnumCalcType.fromSymbol(op);
      value    = Double.parseDouble(argument.substring(1));
    }
    else {
      calcType = EnumCalcType.ADD; // default
      value    = Double.parseDouble(argument);
    }

    return new CalcValue(calcType, value);
  }

  void sendError(CommandSender sender) {
    sender.sendMessage(error);
  }
}
