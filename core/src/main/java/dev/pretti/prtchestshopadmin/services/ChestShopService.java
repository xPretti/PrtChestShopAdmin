package dev.pretti.prtchestshopadmin.services;

import com.Acrobot.Breeze.Utils.MaterialUtil;
import com.Acrobot.ChestShop.ChestShop;
import com.Acrobot.ChestShop.Configuration.Properties;
import dev.pretti.prtchestshopadmin.PrtChestShopAdmin;
import dev.pretti.prtchestshopadmin.model.BoardPrice;
import dev.pretti.prtchestshopadmin.model.CalcDetails;
import dev.pretti.prtchestshopadmin.model.PricingDetails;
import dev.pretti.prtchestshopadmin.utils.ChestShopUtils;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ChestShopService
{
  public final PrtChestShopAdmin plugin;
  public final ChestShop         chestShop;

  public ChestShopService(PrtChestShopAdmin plugin) {
    this.plugin    = plugin;
    this.chestShop = ChestShop.getPlugin();
  }

  public void setPricingDetails(@NotNull Player player, @NotNull Sign sign, @NotNull PricingDetails pricingDetails) {
    String               store     = getStore();
    String               itemId    = getItemId(player.getItemInHand());
    Integer              amount    = pricingDetails.getAmount();
    Double               buyPrice  = pricingDetails.getBuyPrice();
    Double               sellPrice = pricingDetails.getSellPrice();
    DecimalFormatSymbols symbols   = new DecimalFormatSymbols(Locale.US);
    DecimalFormat        df        = new DecimalFormat("#.##", symbols);
    sign.setLine(0, store);
    sign.setLine(1, amount.toString());
    sign.setLine(3, itemId);
    if(sellPrice != null && buyPrice != null) {
      sign.setLine(2, String.format("B %s : S %s", df.format(buyPrice), df.format(sellPrice)));
    }
    else if(buyPrice != null) {
      sign.setLine(2, String.format("B %s", df.format(buyPrice)));
    }
    else if(sellPrice != null) {
      sign.setLine(2, String.format("S %s", df.format(sellPrice)));
    }

    if(sign.update()) {
      player.sendMessage(String.format("§6§lMC §6» §7Loja criada com sucesso, id do item §6%s", itemId));
    }
    else {
      player.sendMessage("§4§lMC §4» §cErro ao criar loja!");
    }
  }

  public void updatePriceDetails(@NotNull Player player, @NotNull Sign sign, @NotNull CalcDetails calcDetails) {
    String priceLine = sign.getLine(2);

    BoardPrice oldPrice = ChestShopUtils.extractPrices(priceLine);

    Double oldBuy  = oldPrice.getBuy();
    Double oldSell = oldPrice.getSell();

    Double newBuy  = oldBuy != null ? calcDetails.calculateBuyPrice(oldBuy) : null;
    Double newSell = oldSell != null ? calcDetails.calculateSellPrice(oldSell) : null;

    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat        df      = new DecimalFormat("#.##", symbols);

    String newLine = "";
    if(newSell != null && newBuy != null) {
      newLine = String.format("B %s : S %s", df.format(newBuy), df.format(newSell));
    }
    else if(newBuy != null) {
      newLine = String.format("B %s", df.format(newBuy));
    }
    else if(newSell != null) {
      newLine = String.format("S %s", df.format(newSell));
    }

    if(newLine.length() > 16) {
      player.sendMessage("§4§lMC §4» §cErro, o preço excedeu o limite de caracteres.");
      return;
    }
    else {
      sign.setLine(2, newLine);
    }

    if(sign.update()) {
      player.sendMessage("§6§lMC §6» §7Preços atualizado com sucesso!");
    }
    else {
      player.sendMessage("§4§lMC §4» §cErro ao tentar atualizar a loja.");
    }
  }


  public String getStore() {
    return Properties.ADMIN_SHOP_NAME.replace(" ", "");
  }

  public String getItemId(ItemStack itemStack) {
    return MaterialUtil.getSignName(itemStack);
  }
}
