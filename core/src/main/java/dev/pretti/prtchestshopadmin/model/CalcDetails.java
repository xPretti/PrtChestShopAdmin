package dev.pretti.prtchestshopadmin.model;

import dev.pretti.prtchestshopadmin.interfaces.ISignDetails;
import org.jetbrains.annotations.Nullable;

public class CalcDetails implements ISignDetails
{
  private final CalcValue buyCalc;
  private final CalcValue sellCalc;

  public CalcDetails(@Nullable CalcValue buyCalc, @Nullable CalcValue sellCalc) {
    this.buyCalc  = buyCalc;
    this.sellCalc = sellCalc;
  }

  @Nullable
  public CalcValue getBuyCalc() {
    return buyCalc;
  }

  @Nullable
  public CalcValue getSellCalc() {
    return sellCalc;
  }

  public Double calculateBuyPrice(Double value) {
    if(buyCalc == null) {
      return value;
    }
    return buyCalc.calculate(value);
  }

  public Double calculateSellPrice(Double value) {
    if(sellCalc == null) {
      return value;
    }
    return sellCalc.calculate(value);
  }
}
