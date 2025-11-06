package dev.pretti.prtchestshopadmin.commands.base;

import dev.pretti.prtchestshopadmin.commands.interfaces.ICommandArgument;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

public class IntCommandArgument implements ICommandArgument<Integer>
{
  private final String error;

  public IntCommandArgument(String error)
  {
    this.error = error;
  }

  @Override
  @Nullable
  public Integer run(CommandSender sender, String argument)
  {
    try
      {
        return Integer.valueOf(argument);
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
