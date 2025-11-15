package dev.pretti.prtchestshopadmin.enums;

import java.util.HashMap;

public enum EnumCalcType
{
  ADD("+"),
  SUB("-"),
  MUL("*"),
  DEF("="),
  DIV("/");

  public static final EnumCalcType[]                VALUES       = values();
  public static final HashMap<String, EnumCalcType> VALUES_NAMES = new HashMap<>();
  public final        String                        symbol;


  /**
   * Inicialização
   */
  static {
    for(EnumCalcType type : VALUES) {
      VALUES_NAMES.put(type.getSymbol(), type);
    }
  }

  EnumCalcType(String symbol) {
    this.symbol = symbol;

  }

  public String getSymbol() {
    return symbol;
  }

  public static EnumCalcType fromSymbol(String symbol) {
    EnumCalcType type = VALUES_NAMES.get(symbol);
    return type == null ? ADD : type;
  }
}
