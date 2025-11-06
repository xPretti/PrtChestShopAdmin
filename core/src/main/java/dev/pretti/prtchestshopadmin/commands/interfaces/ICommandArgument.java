package dev.pretti.prtchestshopadmin.commands.interfaces;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

public interface ICommandArgument<T>
{
  @Nullable
  T run(CommandSender sender, String argument);
}
