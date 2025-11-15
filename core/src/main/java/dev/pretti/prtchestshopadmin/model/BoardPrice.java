package dev.pretti.prtchestshopadmin.model;

public class BoardPrice
{
  private final Double buy;
  private final Double sell;

  public BoardPrice(Double buy, Double sell) {
    this.buy  = buy;
    this.sell = sell;
  }

  public Double getBuy() {
    return buy;
  }

  public Double getSell() {
    return sell;
  }

  @Override
  public String toString() {
    return "BoardPrice{buy=" + buy + ", sell=" + sell + "}";
  }
}
