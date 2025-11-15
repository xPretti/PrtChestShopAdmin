package dev.pretti.prtchestshopadmin.model;

import dev.pretti.prtchestshopadmin.enums.EnumCalcType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CalcValue
{
  private final EnumCalcType calcType;
  private final Double       value;


  public CalcValue(EnumCalcType calcType, Double value) {
    this.calcType = calcType;
    this.value    = value;
  }

  public EnumCalcType getCalcType() {
    return calcType;
  }

  public Double getValue() {
    return value;
  }

  public Double calculate(Double value) {
    switch(calcType) {
      case ADD:
        return value + this.value;
      case SUB:
        return value - this.value;
      case MUL:
        return value * this.value;
      case DIV:
        return value / this.value;
      case DEF:
        return this.value;
    }
    return value;
  }
}
