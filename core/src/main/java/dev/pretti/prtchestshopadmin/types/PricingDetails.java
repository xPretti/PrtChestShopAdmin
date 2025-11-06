package dev.pretti.prtchestshopadmin.types;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PricingDetails
{
  private final Integer amount;
  private final Double  buyPrice;
  private final Double  sellPrice;

  public PricingDetails(@NotNull Integer amount, @NotNull Double buyPrice, @Nullable Double sellPrice)
  {
    this.amount    = amount;
    this.buyPrice  = buyPrice;
    this.sellPrice = sellPrice;
  }

  public Integer getAmount()
  {
    return amount;
  }

  public Double getBuyPrice()
  {
    return buyPrice;
  }

  public Double getSellPrice()
  {
    return sellPrice;
  }
}
