package dev.pretti.prtchestshopadmin.model;

import dev.pretti.prtchestshopadmin.interfaces.ISignDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PricingDetails implements ISignDetails
{
  private final Integer amount;
  private final Double  buyPrice;
  private final Double  sellPrice;

  public PricingDetails(@NotNull Integer amount, @Nullable Double buyPrice, @Nullable Double sellPrice)
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
