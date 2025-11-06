package dev.pretti.prtchestshopadmin.utils;

import dev.pretti.prtchestshopadmin.PrtChestShopAdmin;
import dev.pretti.prtchestshopadmin.enums.EnumLoggerType;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class LogUtils
{
  private static final ConsoleCommandSender CONSOLE;
  private static final String               PREFIX;
  private static final String               LOG_FORMAT_NORMAL;
  private static final String               LOG_FORMAT_ERROR;
  private static final String               LOG_FORMAT_WARN;
  private static final String               LOG_FORMAT_SUCCESS;

  /**
   * Construtor estático
   */
  static
    {
      CONSOLE            = Bukkit.getConsoleSender();
      PREFIX             = PrtChestShopAdmin.class.getSimpleName();
      LOG_FORMAT_NORMAL  = "§7[%s] - §8%s§r";
      LOG_FORMAT_ERROR   = "§7[%s] §7- §c<Error> §8%s§r";
      LOG_FORMAT_WARN    = "§7[%s] §7- §e<Warn> §8%s§r";
      LOG_FORMAT_SUCCESS = "§7[%s] §7- §a<Success> §8%s§r";
    }

  /**
   * Métodos de envio de logs
   */
  public static void log(String message)
  {
    CONSOLE.sendMessage(ReplaceUtils.toColorMessage(message));
  }

  public static void log(EnumLoggerType type, String message)
  {
    switch(type)
      {
        case ERROR:
          logError(message);
          break;
        case SUCCESS:
          logSuccess(message);
          break;
        case WARN:
          logWarn(message);
          break;
        default:
          logNormal(message);
          break;
      }
  }

  public static void logNormal(String message)
  {
    message = String.format(LOG_FORMAT_NORMAL, PREFIX, message);
    log(message);
  }

  public static void logWarn(String message)
  {
    message = String.format(LOG_FORMAT_WARN, PREFIX, message);
    log(message);
  }

  public static void logError(String message)
  {
    message = String.format(LOG_FORMAT_ERROR, PREFIX, message);
    log(message);
  }

  public static void logSuccess(String message)
  {
    message = String.format(LOG_FORMAT_SUCCESS, PREFIX, message);
    log(message);
  }
}
