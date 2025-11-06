package dev.pretti.prtchestshopadmin.commands.base;

import dev.pretti.prtchestshopadmin.commands.interfaces.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Command implements ICommand
{
  private final String  command;
  private final String  permission;
  private       boolean playerOnly;

  /**
   * Construtor da classe
   */
  public Command(@Nullable String command, @Nullable String permission)
  {
    this(command, permission, false);
  }

  public Command(@Nullable String command, @Nullable String permission, boolean playerOnly)
  {
    this.command    = command;
    this.permission = permission;
    this.playerOnly = playerOnly;
  }

  /**
   * Retornos
   */
  @Override
  public boolean isEnabled()
  {
    return true;
  }

  @Override
  public String getCommand()
  {
    return command;
  }

  @Override
  public String getPermission()
  {
    return permission;
  }

  @Override
  public boolean hasPermission(CommandSender sender)
  {
    if(this.permission == null)
      {
        return true;
      }
    return !(sender instanceof Player) || sender.hasPermission(this.permission);
  }

  @Override
  public boolean isCommand(String subCommand)
  {
    if(this.command == null)
      {
        return false;
      }
    return (subCommand != null && subCommand.equalsIgnoreCase(this.command));
  }

  @Override
  public boolean canUse(CommandSender sender)
  {
    return (sender instanceof Player) || !isPlayerOnly();
  }

  @Override
  public List<String> runAutoComplete(CommandSender sender, String command)
  {
    return null;
  }

  public boolean isPlayerOnly()
  {
    return playerOnly;
  }

  public void setPlayerOnly(boolean playerOnly)
  {
    this.playerOnly = playerOnly;
  }
}
