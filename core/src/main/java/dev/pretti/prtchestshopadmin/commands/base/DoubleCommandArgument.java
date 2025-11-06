package dev.pretti.prtchestshopadmin.commands.base;

import dev.pretti.prtchestshopadmin.commands.interfaces.ICommandArgument;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

public class DoubleCommandArgument implements ICommandArgument<Double>
{
  private final String error;

  public DoubleCommandArgument(String error)
  {
    this.error = error;
  }

  @Override
  @Nullable
  public Double run(CommandSender sender, String argument)
  {
    try
      {
        return Double.valueOf(argument);
      } catch(NumberFormatException e)
      {
        sendError(sender);
        return null;
      }
  }

  void sendError(CommandSender sender)
  {
    sender.sendMessage(error);
  }
}
