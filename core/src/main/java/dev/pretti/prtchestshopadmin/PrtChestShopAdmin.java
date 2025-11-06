package dev.pretti.prtchestshopadmin;

import com.Acrobot.ChestShop.ChestShop;
import dev.pretti.prtchestshopadmin.commands.MainCommand;
import dev.pretti.prtchestshopadmin.listeners.PlayerListener;
import dev.pretti.prtchestshopadmin.managers.PricingManager;
import dev.pretti.prtchestshopadmin.services.ChestShopService;
import dev.pretti.prtchestshopadmin.utils.LogUtils;
import dev.pretti.prtchestshopadmin.utils.SystemUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PrtChestShopAdmin extends JavaPlugin
{
  private static PrtChestShopAdmin instance;

  private PricingManager   pricingManager = new PricingManager();
  private ChestShopService chestShopService;

  private boolean isInitialized;

  /**
   * Implementações
   */
  @Override
  public void onLoad()
  {
    loadInstances();
  }

  @Override
  public void onEnable()
  {
    registerEvents();
    registerCommands();

    load();
  }

  @Override
  public void onDisable()
  {
    LogUtils.log("");
    LogUtils.logNormal("Finishing...");
    LogUtils.log("");

    pricingManager.clear();
  }

  /**
   * Inicializadores
   */
  public boolean reload()
  {
    return load();
  }

  protected boolean load()
  {
    String initMessage = isInitialized ? "Re-Initializing..." : "Initializing...";
    LogUtils.log("");
    LogUtils.logNormal(initMessage);
    LogUtils.logNormal("Plugin version: §e" + getDescription().getVersion());
    LogUtils.logNormal("Server version: §e" + SystemUtils.getServerVersion());

    boolean success = loadIntegrations();

    if(success)
      {
        LogUtils.logNormal("");
        LogUtils.logNormal("Everything initialized correctly.");
        LogUtils.log("");
      }
    else
      {
        LogUtils.logNormal("");
        LogUtils.logError("&4Something went wrong during the initialization process.");
        LogUtils.log("");
      }

    isInitialized = true;

    Bukkit.getScheduler().runTaskLater(this, this::delayedLoad, 20L);

    return success;
  }

  /**
   * Método de carregamento atrasado
   */
  public void delayedLoad()
  {
  }

  /**
   * Métodos de carregamentos da instância
   */
  protected void loadInstances()
  {
    instance         = this;
    chestShopService = new ChestShopService(this);
  }

  private boolean loadIntegrations()
  {
    Plugin chestShop = getServer().getPluginManager().getPlugin("ChestShop");
    if(chestShop == null)
      {
        LogUtils.logError("&cChestShop not found!");
        getServer().getPluginManager().disablePlugin(this);
        return false;
      }
    else
      {
        LogUtils.logNormal(String.format("&7ChestShop found, version: &6%s", chestShop.getDescription().getVersion()));
      }
    return true;
  }

  /**
   * Métodos de registros de eventos
   */
  protected void registerEvents()
  {
    Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
  }

  /**
   * Métodos de registros de comandos
   */
  protected void registerCommands()
  {
    getCommand("PrtChestShopAdmin").setExecutor(new MainCommand(this));
  }

  /**
   * Métodos de retornos
   */
  public static PrtChestShopAdmin getInstance()
  {
    return instance;
  }

  public PricingManager getPricingManager()
  {
    return pricingManager;
  }

  public ChestShopService getChestShopService()
  {
    return chestShopService;
  }
}
