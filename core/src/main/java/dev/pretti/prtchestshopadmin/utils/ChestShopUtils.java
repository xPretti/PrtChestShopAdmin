package dev.pretti.prtchestshopadmin.utils;

import dev.pretti.prtchestshopadmin.model.BoardPrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChestShopUtils
{
  public static BoardPrice extractPrices(String line) {
    if(line == null) {
      return null;
    }

    Double buy  = null;
    Double sell = null;

    Pattern pattern = Pattern.compile("(B|S)\\s*([+-]?\\d+(?:\\.\\d+)?)");
    Matcher matcher = pattern.matcher(line);

    while(matcher.find()) {
      String type  = matcher.group(1);
      double value = Double.parseDouble(matcher.group(2));

      if(type.equals("B")) {
        buy = value;
      }
      else {
        sell = value;
      }
    }

    return new BoardPrice(buy, sell);
  }
}
